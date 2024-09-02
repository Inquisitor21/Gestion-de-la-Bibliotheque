import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';

const Livres = () => {
    const [livres, setLivres] = useState([]);

    useEffect(() => {
        fetch('/livres')
            .then(response => response.json())
            .then(data => setLivres(data));
    }, []);

    return (
        <div>
            <h1>Liste des livres</h1>
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Titre</th>
                        <th>Auteur</th>
                        <th>Editeur</th>
                        <th>Année de publication</th>
                        <th>Nombre de pages</th>
                        <th>Genre</th>
                        <th>Stock</th>
                    </tr>
                </thead>
                <tbody>
                    {livres.map(livre => (
                        <tr key={livre.id}>
                            <td>{livre.id}</td>
                            <td>{livre.title}</td>
                            <td>{livre.author}</td>
                            <td>{livre.publisher}</td>
                            <td>{livre.publicationYear}</td>
                            <td>{livre.pages}</td>
                            <td>{livre.genre}</td>
                            <td>{livre.stock}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
            <nav>
                <ul>
                    <li><Link to="/">Retour à l'accueil</Link></li>
                </ul>
            </nav>
        </div>
    );
}

export default Livres;