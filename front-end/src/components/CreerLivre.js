import React, {useState} from 'react';
import {Link, useNavigate} from "react-router-dom";

const CreerLivre = () => {
    const [livre, setLivre] = useState({
        title: '',
        author: '',
        publisher: '',
        publicationYear: '',
        pages: '',
        genre: '',
    });

    const navigate = useNavigate();

    const handleChange = (event) => {
        setLivre({...livre, [event.target.name]: event.target.value});
    };

    const handleSubmit = (event) => {
        event.preventDefault();

        fetch('/creer-livre', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(livre),
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Erreur lors de la création du livre');
                }
                return response;
            })
            .then(() => {
                navigate('/livres');
            })
            .catch(error => {
                console.error('Erreur:', error);
            });
    };

    return (
        <div>
            <h1>Créer Livre</h1>
            <form onSubmit={handleSubmit}>
                <label>Titre:</label>
                <input type="text" name="title" onChange={handleChange}/>

                <label>Auteur:</label>
                <input type="text" name="author" onChange={handleChange}/>

                <label>Editeur:</label>
                <input type="text" name="publisher" onChange={handleChange}/>

                <label>Année de publication:</label>
                <input type="text" name="publicationYear" onChange={handleChange}/>

                <label>Pages:</label>
                <input type="text" name="pages" onChange={handleChange}/>

                <label>Genre:</label>
                <input type="text" name="genre" onChange={handleChange}/>
                <br/>

                <input type="submit" value="Créer Livre"/>

                <nav>
                    <ul>
                        <li><Link to="/">Retour à l'accueil</Link></li>
                    </ul>
                </nav>
            </form>
        </div>
    );
};

export default CreerLivre;