import React, {useEffect, useState} from 'react';
import {Link, useNavigate} from 'react-router-dom';

const EmprunterLivreForm = () => {
    const [livres, setLivres] = useState([]);
    const [emprunteurs, setEmprunteurs] = useState([]);
    const [selectedLivre, setSelectedLivre] = useState('');
    const [selectedEmprunteur, setSelectedEmprunteur] = useState('');
    const navigate = useNavigate();

    useEffect(() => {
        fetch('/livres')
            .then(response => response.json())
            .then(data => setLivres(data));

        fetch('/emprunteurs')
            .then(response => response.json())
            .then(data => setEmprunteurs(data));
    }, []);

    const handleSubmit = (e) => {
        e.preventDefault();
        fetch('/emprunter-livre', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                emprunteurId: selectedEmprunteur,
                documentId: selectedLivre,
            }),
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Toutes les copies du document ont été empruntées');
                }
                return response;
            })
            .then(() => {
                navigate(`/emprunts-emprunteur/${selectedEmprunteur}`);
            })
            .catch(error => {
                navigate(`/error/${error.message}`);
            });
    };

    return (
        <div>
            <h1>Emprunter Livre</h1>
            <form onSubmit={handleSubmit}>
                <label htmlFor="livre">Livre:</label><br/>
                <select id="livre" value={selectedLivre} onChange={(e) => setSelectedLivre(e.target.value)} required>
                    <option value="" selected>Choisir Livre</option>
                    {livres.map(livre => (
                        <option key={livre.id} value={livre.id}>{livre.title}</option>
                    ))}
                </select><br/>
                <label htmlFor="emprunteur">Emprunteur:</label><br/>
                <select id="emprunteur" value={selectedEmprunteur}
                        onChange={(e) => setSelectedEmprunteur(e.target.value)} required>
                    <option value="" selected>Choisir Emprunteur</option>
                    {emprunteurs.map(emprunteur => (
                        <option key={emprunteur.id} value={emprunteur.id}>{emprunteur.prenom}</option>
                    ))}
                </select><br/>
                <input type="submit" value="Emprunter"/>
            </form>
            <nav>
                <ul>
                    <li><Link to="/">Retour à l'accueil</Link></li>
                </ul>
            </nav>
        </div>
    );
}

export default EmprunterLivreForm;