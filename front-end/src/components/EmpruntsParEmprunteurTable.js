import React, {useEffect, useState} from 'react';
import {Link, useParams} from 'react-router-dom';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome'
import {faUndo, faForward, faMoneyBillWave} from '@fortawesome/free-solid-svg-icons'

const EmpruntsEmprunteurTable = () => {
    const [emprunts, setEmprunts] = useState([]);
    const { id } = useParams();

    useEffect(() => {
        fetch(`/emprunts-emprunteur/${id}`)
            .then(response => response.json())
            .then(data => setEmprunts(data));
    }, [id]);

    const Return = (empruntId) => {
        fetch(`/retourner-livre/${empruntId}`, {
            method: 'POST',
        })
            .then(() => {
                fetch(`/emprunts-emprunteur/${id}`)
                    .then(response => response.json())
                    .then(data => setEmprunts(data));
            });
    };

    const ReturnWithDelay = (empruntId) => {
        fetch(`/retourner-livre-retard/${empruntId}`, {
            method: 'POST',
        })
            .then(() => {
                fetch(`/emprunts-emprunteur/${id}`)
                    .then(response => response.json())
                    .then(data => setEmprunts(data));
            });
    };

    const PayFine = (empruntId) => {
        fetch(`/payer-amende/${empruntId}`, {
            method: 'POST',
        })
            .then(() => {
                fetch(`/emprunts-emprunteur/${id}`)
                    .then(response => response.json())
                    .then(data => setEmprunts(data));
            });
    };

    return (
        <div>
            <h1>Emprunts par Emprunteur</h1>
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Copies Louées</th>
                        <th>Date d'Emprunt</th>
                        <th>Date de Retour Prévue</th>
                        <th>Date de Retour Effectué</th>
                        <th>Frais de Retard</th>
                        <th>Titre du Document</th>
                        <th>Nom de l'Emprunteur</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    {emprunts.map(emprunt => (
                        <tr key={emprunt.id}>
                            <td>{emprunt.id}</td>
                            <td>{emprunt.copiesLouees}</td>
                            <td>{emprunt.dateEmprunt}</td>
                            <td>{emprunt.dateRetourPrevue}</td>
                            <td>{emprunt.dateRetourEffectue || 'N/A'}</td>
                            <td>{emprunt.fraisRetard}</td>
                            <td>{emprunt.document.title}</td>
                            <td>{emprunt.emprunteur.prenom} {emprunt.emprunteur.nom}</td>
                            <td>
                                <div className="action-icon-container">
                                    {!emprunt.dateRetourEffectue && <FontAwesomeIcon icon={faUndo} onClick={() =>
                                        Return(emprunt.id)} className="action-icon" title="Retourner" />}

                                    {!emprunt.dateRetourEffectue && <FontAwesomeIcon icon={faForward} onClick={() =>
                                        ReturnWithDelay(emprunt.id)} className="action-icon" title="Retourner avec retard" />}

                                    {emprunt.fraisRetard > 0 && <FontAwesomeIcon icon={faMoneyBillWave} onClick={() =>
                                        PayFine(emprunt.id)} className="action-icon" title="Payer amende"/>}
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

export default EmpruntsEmprunteurTable;