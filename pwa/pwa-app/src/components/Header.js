import "./Header.css"
import { Link } from 'react-router-dom';

export function Header() {
    return(
        <div className="header">
            <Link to="/" className="link linkGrid">
                <img src="logo192.png" alt="logo"></img>
                <p className="header-title">Todo</p>
            </Link>
            <Link to="/about" className="link">About me</Link>
        </div>
    )
}