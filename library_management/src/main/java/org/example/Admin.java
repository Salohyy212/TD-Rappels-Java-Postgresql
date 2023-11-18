package org.example;

import java.time.LocalDate;

public class Admin extends User {
    private String role;

    public Admin(int id, String firstName, String lastName, String email, String phone, LocalDate creationDate, String role) {
        super(id, firstName, lastName, email, phone, creationDate);
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
                ", email='" + getEmail() + '\'' +
                ", creationDate=" + getCreationDate() +
                ", role='" + role + '\'' +
                '}';
    }
}

