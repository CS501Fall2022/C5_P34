package com.example.todo_list;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    private ListView todoListView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidgets();
        loadFromDBToMemory();
        setTodoAdapter();
        setOnClickListener();
    }


    private void initWidgets()
    {
        todoListView = findViewById(R.id.todoListView);
    }

    private void loadFromDBToMemory()
    {
        DatabaseManager sqLiteManager = DatabaseManager.instanceOfDatabase(this);
        sqLiteManager.populateTodoListArray();
    }

    private void setTodoAdapter()
    {
        todoAdapter todoAdapter = new todoAdapter(getApplicationContext(), todo.nonDeletedTodos());
        todoListView.setAdapter(todoAdapter);
    }


    private void setOnClickListener()
    {
        todoListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l)
            {
                todo selectedTodo = (todo) todoListView.getItemAtPosition(position);
                Intent editTodoIntent = new Intent(getApplicationContext(), TodoDetailActivity.class);
                editTodoIntent.putExtra(todo.TODO_EDIT_EXTRA, selectedTodo.getId());
                startActivity(editTodoIntent);
            }
        });
    }


    public void newTodo(View view)
    {
        Intent newTodoIntent = new Intent(this, TodoDetailActivity.class);
        startActivity(newTodoIntent);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        setTodoAdapter();
    }
}