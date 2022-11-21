import 'package:flutter/material.dart';

import '../todo.dart';

class HomePage extends StatefulWidget {
  const HomePage({Key? key}) : super(key: key);

  @override
  State<HomePage> createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  final TextEditingController _todoController = TextEditingController();
  int id = 0;
  final List<Todo> _todoList = <Todo>[];

  @override
  void dispose() {
    _todoController.dispose();
    super.dispose();
  }

  void addTodo() {
    setState(() {
      id++;
      _todoList.add(Todo(id: id, name: _todoController.text, checked: false));
    });
    print("id: $id");
    _todoController.clear();
  }

  void deleteTodo(Todo todo) {
    setState(() {
      _todoList.remove(todo);
    });
    print("list ${_todoList}");
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Center(child: Text("My Todo List")),
      ),
      body: SingleChildScrollView(
        padding: EdgeInsets.all(20),
        child: Center(
          child: Column(children: [
            Text("Create a list of todos my friend!"),
            SizedBox(
              height: 20,
            ),
            TextField(
              controller: _todoController,
              textInputAction: TextInputAction.send,
              decoration: InputDecoration(
                  labelText: "New Todo",
                  enabledBorder: OutlineInputBorder(
                    borderSide: BorderSide(color: Colors.grey),
                    borderRadius: BorderRadius.circular(25),
                  ),
                  focusedBorder: OutlineInputBorder(
                      borderSide: BorderSide(color: Colors.blue),
                      borderRadius: BorderRadius.circular(25))),
            ),
            ElevatedButton(
                onPressed: addTodo, child: Text('Create a new todo')),
            ListView(
              scrollDirection: Axis.vertical,
              shrinkWrap: true,
              children: _todoList.map((Todo todo) {
                return Row(
                  children: [
                    Expanded(
                      flex: 1,
                      child: Checkbox(
                          value: todo.checked,
                          onChanged: (value) {
                            setState(() {
                              todo.checked = value!;
                            });
                          }),
                    ),
                    Expanded(
                      flex: 7,
                      child: Text(todo.name,
                          style: todo.checked
                              ? TextStyle(
                                  decoration: TextDecoration.lineThrough)
                              : TextStyle(decoration: null)),
                    ),
                    Expanded(
                      flex: 2,
                      child: ElevatedButton(
                        onPressed: () {
                          deleteTodo(todo);
                        },
                        child: Icon(
                          Icons.delete,
                          color: Colors.white,
                        ),
                        style: ButtonStyle(
                          backgroundColor: MaterialStateProperty.all(Colors.red),
                        ),
                      ),
                    )
                  ],
                );
              }).toList(),
            ),
          ]),
        ),
      ),
    );
  }
}
