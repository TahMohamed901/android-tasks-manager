package com.example.do_it;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class EditTaskActivity extends AppCompatActivity {
    private EditText editTextTask;
    private Button buttonUpdateTask;
    private DatabaseHelper dbHelper;
    private int taskId;  // ID de la tâche à modifier

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);

        // Récupérer l'ID de la tâche à modifier via l'intention
        taskId = getIntent().getIntExtra("TASK_ID", -1);

        editTextTask = findViewById(R.id.editTextTask);
        buttonUpdateTask = findViewById(R.id.buttonUpdateTask);
        dbHelper = new DatabaseHelper(this);

        // Charger les données de la tâche
        loadTaskData();

        buttonUpdateTask.setOnClickListener(v -> {
            String taskText = editTextTask.getText().toString();
            if (!taskText.isEmpty()) {
                // Mettre à jour la tâche dans la base de données
                dbHelper.updateTask(taskId, taskText);
                // Retourner à MainActivity
                Intent intent = new Intent(EditTaskActivity.this, MainActivity.class);
                startActivity(intent);
                finish(); // Fermer l'activité EditTaskActivity
            } else {
                Toast.makeText(EditTaskActivity.this, "Please enter a task", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadTaskData() {
        // Récupérer les données de la tâche à partir de la base de données
        Cursor cursor = dbHelper.getTaskById(taskId);
        if (cursor != null && cursor.moveToFirst()) {
            String task = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_TASK));
            editTextTask.setText(task);
            cursor.close();
        }
    }
}
