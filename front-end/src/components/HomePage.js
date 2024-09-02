import React from "react";
import {Link} from "react-router-dom";

const HomePage = () => {
    return (
        <div>
            <h1>Bienvenue à la Bibliothèque de Vazgen</h1>
            <div>
                <nav>
                    <ul>
                        <li><Link to="/creer-emprunteur">Créer Emprunteur</Link></li>
                        <li><Link to="/emprunteurs">Liste des Emprunteurs</Link></li>
                        <li><Link to="/emprunts-par-emprunteur">Emprunts par Emprunteur</Link></li>
                    </ul>
                </nav>
                <nav>
                    <ul>
                        <li><Link to="/creer-livre">Créer Livre</Link></li>
                        <li><Link to="/livres">Liste des Livres</Link></li>
                        <li><Link to="/emprunter-livre">Emprunter Livre</Link></li>
                    </ul>
                </nav>
            </div>
        </div>
    );
}
export default HomePage;