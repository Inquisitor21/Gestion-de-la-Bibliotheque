import React from 'react';
import { Link, useParams } from 'react-router-dom';

const ErrorPage = () => {
    const { message } = useParams();
    return (
        <div className="errorpage">
            <h1>{decodeURI(message)}</h1>
            <img src={process.env.PUBLIC_URL + "/assets/sad.png"} alt="Error Image" />
            <nav>
                <ul>
                    <li><Link to="/">Retour à l'accueil</Link></li>
                </ul>
            </nav>
        </div>
    );
}

export default ErrorPage;