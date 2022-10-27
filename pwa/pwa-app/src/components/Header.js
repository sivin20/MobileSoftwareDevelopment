import "./Header.css"
import {BrowserRouter, Routes, Route, Link } from 'react-router-dom';

export function Header() {
    return(
        <div className="header">
            <Link to="/" className="link linkGrid">
                <img src="logo192.png"></img>
                <p className="header-title">Todo</p>
            </Link>
            <Link to="/page2" className="link">MovieDetail</Link>
        </div>
    )
}