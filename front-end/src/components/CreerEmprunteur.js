import React, {useState} from 'react';
import {Link, useNavigate} from "react-router-dom";

const CreerEmprunteur = () => {
    const [emprunteur, setEmprunteur] = useState({
        nom: '',
        prenom: '',
        adresse: '',
        telephone: '',
        email: '',
    });

    const navigate = useNavigate();

    const handleChange = (event) => {
        console.log('handleChange', event.target.name, event.target.value);
        setEmprunteur({...emprunteur, [event.target.name]: event.target.value});
    };

    const handleSubmit = (event) => {
        event.preventDefault();
        console.log('handleSubmit', emprunteur);

        fetch('/creer-emprunteur', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(emprunteur),
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Erreur lors de la création de l\'emprunteur');
                }
                return response;
            })
            .then(() => {
                navigate('/emprunteurs');
            })
            .catch(error => {
                console.error('Erreur:', error);
            });
    };

    return (
        <div>
            <h1>Créer Emprunteur</h1>
            <form onSubmit={handleSubmit}>
                <label>Nom:</label>
                <input type="text" name="nom" onChange={handleChange}/>

                <label>Prénom:</label>
                <input type="text" name="prenom" onChange={handleChange}/>

                <label>Adresse:</label>
                <input type="text" name="adresse" onChange={handleChange}/>

                <label>Téléphone:</label>
                <input type="text" name="telephone" onChange={handleChange}/>

                <label>Email:</label>
                <input type="text" name="email" onChange={handleChange}/>
                <br/>

                <input type="submit" value="Créer Emprunteur"/>

                <nav>
                    <ul>
                        <li><Link to="/">Retour à l'accueil</Link></li>
                    </ul>
                </nav>
            </form>
        </div>
    );
}
export default CreerEmprunteur;