import React, { useEffect, useState } from 'react';
import {Link, useNavigate} from 'react-router-dom';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome'
import { faSearch } from '@fortawesome/free-solid-svg-icons';

const Emprunteurs = () => {
    const [emprunteurs, setEmprunteurs] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        fetch('/emprunteurs')
            .then(response => response.json())
            .then(data => setEmprunteurs(data));
    }, []);

    const handleIconClick = (id) => {
        navigate(`/emprunts-emprunteur/${id}`);
    };

    return (
        <div>
            <h1>Liste des Emprunteurs</h1>
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nom</th>
                        <th>Prénom</th>
                        <th>Adresse</th>
                        <th>Téléphone</th>
                        <th>Email</th>
                        <th>Voir Emprunts</th>
                    </tr>
                </thead>
                <tbody>
                    {emprunteurs.map(emprunteur => (
                        <tr key={emprunteur.id}>
                            <td>{emprunteur.id}</td>
                            <td>{emprunteur.nom}</td>
                            <td>{emprunteur.prenom}</td>
                            <td>{emprunteur.adresse}</td>
                            <td>{emprunteur.telephone}</td>
                            <td>{emprunteur.email}</td>
                            <td>
                                <div className="action-icon-container">
                                    <FontAwesomeIcon icon={faSearch} onClick={() => handleIconClick(emprunteur.id)} />
                                </div>
                            </td>
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

export default Emprunteurs;