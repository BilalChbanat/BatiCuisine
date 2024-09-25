package com.baticuisine.Models;

import com.baticuisine.Enums.ProjectStatus;

public class Project {
    private int id;
    private String name;
    private double profitMargin;
    private double totalCost;
    private ProjectStatus projectStatus;
    private int clientId;

    public Project(int id, String name, double profitMargin, double totalCost, ProjectStatus projectStatus, int clientId) {
        this.id = id;
        this.name = name;
        this.profitMargin = profitMargin;
        this.totalCost = totalCost;
        this.projectStatus = projectStatus;
        this.clientId = clientId;  // Fixed assignment
    }

    public Project(String name, double profitMargin, double totalCost, ProjectStatus projectStatus, int clientId) {
        this.name = name;
        this.profitMargin = profitMargin;
        this.totalCost = totalCost;
        this.projectStatus = projectStatus;
        this.clientId = clientId;  // Fixed assignment
    }

    // Getters and setters
    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getProfitMargin() {
        return profitMargin;
    }

    public void setProfitMargin(double profitMargin) {
        this.profitMargin = profitMargin;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public ProjectStatus getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(ProjectStatus projectStatus) {
        this.projectStatus = projectStatus;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", profitMargin=" + profitMargin +
                ", totalCost=" + totalCost +
                ", projectStatus=" + projectStatus +
                ", clientId=" + clientId +  // Fixed to clientId
                '}';
    }
}
