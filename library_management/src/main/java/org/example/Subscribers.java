package org.example;

import java.time.LocalDate;

public class Subscribers extends User {
    public Subscribers(int id, String firstName, String lastName, LocalDate creationDate) {
        super(id, firstName, lastName, creationDate);
    }

    @Override
    public String toString() {
        return "Subscribers{" +
                "id='" + getId() + '\'' +
                ", firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", creationDate=" + getCreationDate() +
                '}';
    }
}

