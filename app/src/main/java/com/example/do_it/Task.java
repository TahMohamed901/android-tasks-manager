package com.example.do_it;

public class Task {
    private int id;
    private String task;
    private int status; // 0: non terminé, 1: terminé

    // Constructeur
    public Task(int id, String task, int status) {
        this.id = id;
        this.task = task;
        this.status = status;
    }

    // Getter et Setter pour l'ID
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter et Setter pour la tâche
    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    // Getter et Setter pour le statut
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    // Méthode pour afficher la tâche en format lisible
    @Override
    public String toString() {
        return task + " (Statut: " + (status == 0 ? "Non terminé" : "Terminé") + ")";
    }
}

