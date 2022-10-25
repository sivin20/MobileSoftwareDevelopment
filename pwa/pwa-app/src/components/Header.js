import "./Header.css"
import {BrowserRouter, Routes, Route, Link } from 'react-router-dom';

export function Header() {
    return(
        <div className="header">
            <Link to="/" className="link"><p className="header-title">T0d0</p></Link>
            <Link to="/page2" className="link">MovieDetail</Link>
        </div>
    )
}