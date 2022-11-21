import React, {Suspense} from "react";
import { Todo } from "./Todo";
import "../styles/TodoList.css"

export function TodoList({todos, toggleComplete, removeTodo}) {
    return(
        <table className="table">
                {todos.map(todo => (
                    <tr>
                        <Todo key={todo.id} todo={todo} toggleComplete={toggleComplete} removeTodo={removeTodo}/>
                    </tr>
                ))}
        </table>
    );
}