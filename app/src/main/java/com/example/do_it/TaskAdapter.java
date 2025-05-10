package com.example.do_it;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class TaskAdapter extends ArrayAdapter<Task> {
    private Context context;
    private List<Task> tasks;
    private String a;
    public TaskAdapter(Context context, List<Task> tasks) {
        super(context, 0, tasks);
        this.context = context;
        this.tasks = tasks;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.task_item, parent, false);
        }

        Task task = tasks.get(position);

        TextView textViewTask = convertView.findViewById(R.id.textViewTask);
        textViewTask.setText(task.getTask());

        Button buttonDelete = convertView.findViewById(R.id.buttonDelete);
        buttonDelete.setOnClickListener(v -> {
            // Supprimer la tâche de la base de données et mettre à jour l'affichage
            DatabaseHelper dbHelper = new DatabaseHelper(context);
            dbHelper.deleteTask(task.getId());
            tasks.remove(position);
            notifyDataSetChanged();
        });

        Button buttonEdit = convertView.findViewById(R.id.buttonEdit);
        buttonEdit.setOnClickListener(v -> {
            // Ouvrir l'activité de modification avec l'ID de la tâche
            Intent intent = new Intent(context, EditTaskActivity.class);
            intent.putExtra("TASK_ID", task.getId()); // Passer l'ID de la tâche à modifier
            context.startActivity(intent);
        });

        return convertView;
    }
}

