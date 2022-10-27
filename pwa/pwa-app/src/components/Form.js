import "./Form.css"

export function Form() {
    return(
        <form className="form">
            <input type="text" placeholder="Add todo..." className="text"/>
            <button type="submit" className="add-btn">Add</button>
        </form>
    )
}