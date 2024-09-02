import React from 'react';
import './App.css';
import {BrowserRouter, Route, Routes} from "react-router-dom";

import HomePage from './components/HomePage';
import Livres from './components/Livres';
import CreerLivre from "./components/CreerLivre";
import Emprunteurs from './components/Emprunteurs';
import CreerEmprunteur from './components/CreerEmprunteur';
import EmpruntsParEmprunteurForm from './components/EmpruntsParEmprunteurForm';
import EmprunterLivreForm from './components/EmprunterLivreForm';
import EmpruntsParEmprunteurTable from './components/EmpruntsParEmprunteurTable';
import ErrorPage from "./components/ErrorPage";

function App() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<HomePage/>}/>
                <Route path="/livres" element={<Livres/>}/>
                <Route path="/creer-livre" element={<CreerLivre/>}/>
                <Route path="/emprunteurs" element={<Emprunteurs/>}/>
                <Route path="/creer-emprunteur" element={<CreerEmprunteur/>}/>
                <Route path="/emprunts-par-emprunteur" element={<EmpruntsParEmprunteurForm/>}/>
                <Route path="/emprunter-livre" element={<EmprunterLivreForm/>}/>
                <Route path="/emprunts-emprunteur/:id" element={<EmpruntsParEmprunteurTable/>}/>
                <Route path="/error/:message" element={<ErrorPage/>}/>
            </Routes>
        </BrowserRouter>
    );
}

export default App;