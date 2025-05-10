package com.example.do_it;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddTaskActivity extends AppCompatActivity {
    private EditText editTextTask;
    private Button buttonAddTask;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        editTextTask = findViewById(R.id.editTextTask);
        buttonAddTask = findViewById(R.id.buttonAddTask);
        dbHelper = new DatabaseHelper(this);

        buttonAddTask.setOnClickListener(v -> {
            String taskText = editTextTask.getText().toString();
            if (!taskText.isEmpty()) {
                // Ajouter la tâche dans la base de données
                dbHelper.addTask(taskText, 0); // 0 pour non terminé
                // Retourner à MainActivity
                Intent intent = new Intent(AddTaskActivity.this, MainActivity.class);
                startActivity(intent);
                finish(); // Fermer l'activité AddTaskActivity
            } else {
                Toast.makeText(AddTaskActivity.this, "Please enter a task", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
