import { Link } from 'react-router-dom';

export default function About() {
    return(
        <div style={{margin: "10px",}}>
            <div>This is the about page</div>
            <p>As you can see, there is a lot of exciting information about me</p>
            <p>To go back to the todo app, click the logo in the header, or click the button below</p>
            <button style={{backgroundColor: 'skyblue', border: "1px solid skyblue", borderRadius: 5}}>
                <Link to="/" style={{color:"white", textDecoration:"none"}}>
                    <p>go back</p>
                </Link>
            </button>
        </div>
    )
}