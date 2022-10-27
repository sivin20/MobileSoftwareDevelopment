import "./Form.css"
import React, {useState} from "react";
import {v4 as uuid} from "uuid";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faTrash, faCheck } from "@fortawesome/free-solid-svg-icons";

export function Form({ addTodo }) {

    const [todo, setTodo] = useState({
        id: "",
        task: "",
        completed: false,
    });

    function handle(e) {
        setTodo({...todo, task: e.target.value})
    }

    function handleSubmit(e) {
        e.preventDefault();
        if(todo.task.trim()) {
            addTodo({...todo, id: uuid()});
            //reset input
            setTodo({...todo, task: ""});
        }
    }

    return(
        <form className="form" onSubmit={handleSubmit}>
            <div className="textBox">
                <FontAwesomeIcon icon={faCheck} className="fa"/>
                <input type="text" placeholder="Add todo..." className="textField" onChange={handle} value={todo.task}/>
            </div>
            <button type="submit" className="add-btn">Add</button>
        </form>
    )
}