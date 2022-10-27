
import { Form } from './components/Form';
import { TodoList } from "./components/TodoList"
import React, {useState, useEffect} from 'react';
import "./Frontpage.css"

const STORAGE_KEY = "unique-key";


export default function FrontPage() {
    const [todos, setTodos] = useState([]);

    useEffect(()=>{
        console.log("I fire once")
        const storage = JSON.parse(localStorage.getItem(STORAGE_KEY));
        if(storage) {
            setTodos(storage);
            console.log(storage)
        }
    }, []);

    useEffect(()=>{
        localStorage.setItem(STORAGE_KEY, JSON.stringify(todos));
        console.log("Stored" + JSON.stringify(todos));
    }, [todos]);

    function addTodo(todo) {
        setTodos([todo, ...todos]);
    }

    function toggleComplete(id) {
        setTodos(
            todos.map(todo => {
                if(todo.id === id) {
                    return {
                        ...todo,
                        completed: !todo.completed
                    };
                }
                return todo;
            })
        )
    }
    function removeTodo(id) {
        setTodos(todos.filter(todo => todo.id !== id));
    }

    return(
        <>
            <h2 className='title'>Todo List</h2>
            <div className='container'>
                <Form addTodo={addTodo}/>
                <hr></hr>
                <TodoList todos={todos} toggleComplete={toggleComplete} removeTodo={removeTodo}/>
            </div>
        </>
    );
}