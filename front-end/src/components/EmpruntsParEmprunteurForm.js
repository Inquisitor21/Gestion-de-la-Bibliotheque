import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { Link } from 'react-router-dom';

const EmpruntsParEmprunteurForm = () => {
    const [emprunteurs, setEmprunteurs] = useState([]);
    const [selectedEmprunteur, setSelectedEmprunteur] = useState('');
    const navigate = useNavigate();

    useEffect(() => {
        fetch('/emprunts-par-emprunteur')
            .then(response => response.json())
            .then(data => setEmprunteurs(data));
    }, []);

    const handleSubmit = (e) => {
        e.preventDefault();
        navigate(`/emprunts-emprunteur/${selectedEmprunteur}`);
    };

    return (
        <div>
            <h1>Choisir un Emprunteur</h1>
            <form onSubmit={handleSubmit}>
                <label htmlFor="emprunteur">Emprunteur:</label><br/>
                <select id="emprunteur" value={selectedEmprunteur}
                        onChange={(e) => setSelectedEmprunteur(e.target.value)} required>
                    <option value="">Choisir Emprunteur</option>
                    {emprunteurs.map(emprunteur => (
                        <option key={emprunteur.id} value={emprunteur.id}>{emprunteur.prenom}</option>
                    ))}
                </select><br/>
                <input type="submit" value="Voir Emprunts"/>
            </form>
            <nav>
                <ul>
                    <li><Link to="/">Retour à l'accueil</Link></li>
                </ul>
            </nav>
        </div>
    );
}

export default EmpruntsParEmprunteurForm;