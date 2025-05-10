package com.example.do_it;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.do_it.DatabaseHelper;
import com.example.do_it.R;
import com.example.do_it.Task;
import com.example.do_it.TaskAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button buttonAddNewTask;
    private ListView listViewTasks;
    private DatabaseHelper dbHelper;
    private TaskAdapter taskAdapter;
    private List<Task> taskList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonAddNewTask = findViewById(R.id.buttonAddNewTask);
        listViewTasks = findViewById(R.id.listViewTasks);

        dbHelper = new DatabaseHelper(this);
        taskList = new ArrayList<>();
        taskAdapter = new TaskAdapter(this, taskList);
        listViewTasks.setAdapter(taskAdapter);

        loadTasks();

        // Ouvrir AddTaskActivity lorsque l'utilisateur clique sur le bouton
        buttonAddNewTask.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddTaskActivity.class);
            startActivity(intent);
        });
    }

    private void loadTasks() {
        taskList.clear();
        Cursor cursor = dbHelper.getAllTasks();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_ID));
            String task = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_TASK));
            int status = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_STATUS));
            taskList.add(new Task(id, task, status));
        }
        cursor.close();
        taskAdapter.notifyDataSetChanged();
    }
}

