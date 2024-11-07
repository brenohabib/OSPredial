package org.atividadeJava.atividade3.Prova2e3;

import java.time.LocalDate;

public class OS {
    private int id;
    private String technician;
    private String description;
    private String lobby;
    private int block;
    private int apartment;
    private String priority;
    private String status;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private String finalizedAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTechnician() {
        return technician;
    }

    public void setTechnician(String technician) {
        this.technician = technician;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLobby() {
        return lobby;
    }

    public void setLobby(String lobby) {
        this.lobby = lobby;
    }

    public int getBlock() {
        return block;
    }

    public void setBlock(int block) {
        this.block = block;
    }

    public int getApartment() {
        return apartment;
    }

    public void setApartment(int apartment) {
        this.apartment = apartment;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getFinalizedAt() {
        return finalizedAt;
    }

    public void setFinalizedAt(String finalizedAt) {
        this.finalizedAt = finalizedAt;
    }

    public OS(int id, String technician, String description, String lobby, int block, int apartment,
              String priority, String status, LocalDate createdAt, LocalDate updatedAt, String finalizedAt) {
        this.id = id;
        this.technician = technician;
        this.description = description;
        this.lobby = lobby;
        this.block = block;
        this.apartment = apartment;
        this.priority = priority;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.finalizedAt = finalizedAt;
    }
}
