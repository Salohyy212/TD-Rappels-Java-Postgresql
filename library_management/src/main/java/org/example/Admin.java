package org.example;

import java.time.LocalDate;

public class Admin extends User {
    private String role;

    public Admin(int id, String firstName, String lastName, LocalDate creationDate, String role) {
        super(id, firstName, lastName, creationDate);
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id='" + getId() + '\'' +
                ", firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", creationDate=" + getCreationDate() +
                ", role='" + role + '\'' +
                '}';
    }
}

