package com.example.todo_list;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Date;

public class TodoDetailActivity extends AppCompatActivity {
    private EditText titleEditText, descEditText;
    private Button deleteButton;
    private todo selectedTodo;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_detail);
        initWidgets();
        checkForEditTodo();
    }

    private void initWidgets()
    {
        titleEditText = findViewById(R.id.titleEditText);
        descEditText = findViewById(R.id.descriptionEditText);
        deleteButton = findViewById(R.id.deleteTodoButton);
    }

    private void checkForEditTodo()
    {
        Intent previousIntent = getIntent();

        int passedTodoID = previousIntent.getIntExtra(todo.TODO_EDIT_EXTRA, -1);
        selectedTodo = todo.getTodoForID(passedTodoID);

        if (selectedTodo != null)
        {
            titleEditText.setText(selectedTodo.getTitle());
            descEditText.setText(selectedTodo.getDescription());
        }
        else
        {
            deleteButton.setVisibility(View.INVISIBLE);
        }
    }

    public void saveTodo(View view)
    {
        DatabaseManager sqLiteManager = DatabaseManager.instanceOfDatabase(this);
        String title = String.valueOf(titleEditText.getText());
        String desc = String.valueOf(descEditText.getText());

        if(selectedTodo == null)
        {
            int id = todo.todoArrayList.size();
            todo newTodo = new todo(id, title, desc);
            todo.todoArrayList.add(newTodo);
            sqLiteManager.addTodoToDatabase(newTodo);
        }
        else
        {
            selectedTodo.setTitle(title);
            selectedTodo.setDescription(desc);
            sqLiteManager.updateTodoInDB(selectedTodo);
        }

        finish();
    }

    public void deleteTodo(View view)
    {
        selectedTodo.setDeleted(new Date());
        DatabaseManager databaseManager = DatabaseManager.instanceOfDatabase(this);
        databaseManager.updateTodoInDB(selectedTodo);
        finish();
    }

}