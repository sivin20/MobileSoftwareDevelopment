import React from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faTrash, faTrashCan } from "@fortawesome/free-solid-svg-icons";

export function Todo({todo, toggleComplete, removeTodo}) {

    function handleCheckbox() {
        toggleComplete(todo.id);
    }
    function handleRemove() {
        removeTodo(todo.id);
    }
    return(
        <>
            <td className="td_check">
                <input type="checkbox" onClick={handleCheckbox} defaultChecked={todo.completed ? true : false}/>
            </td>
            <td className="td_text" style={{textDecoration: todo.completed ? "line-through" : null}}>
                {todo.task}
            </td>
            <td className="td_btn">
                <button onClick={handleRemove} className="btn"><FontAwesomeIcon icon={faTrash}/></button>
            </td>
        </>
    );
}