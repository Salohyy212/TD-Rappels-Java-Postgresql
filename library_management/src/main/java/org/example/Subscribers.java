package org.example;

import java.time.LocalDate;

public class Subscribers extends User {
    public Subscribers(int id, String firstName, String lastName, LocalDate creationDate) {
        super(id, firstName, lastName, creationDate);
    }

    public int getFollowerCount() {
        return subscriberCount;
    }

    public void setFollowerCount(int followerCount) {
        this. subscriberCount = followerCount;
    }

    @Override
    public String toString() {
        return "Followers{" +
                "id='" + getId() + '\'' +
                ", firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", creationDate=" + getCreationDate() +
                ", followerCount=" +  subscriberCount +
                '}';
    }
}

