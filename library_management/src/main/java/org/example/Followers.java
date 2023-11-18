package org.example;

import java.time.LocalDate;

public class Followers extends User {
    private int followerCount;

    public Followers(int id, String firstName, String lastName, String email, String phone, LocalDate creationDate, int followerCount) {
        super(id, firstName, lastName, email, phone, creationDate);
        this.followerCount = followerCount;
    }

    public int getFollowerCount() {
        return followerCount;
    }

    public void setFollowerCount(int followerCount) {
        this.followerCount = followerCount;
    }

    @Override
    public String toString() {
        return "Followers{" +
                "id='" + getId() + '\'' +
                ", firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", creationDate=" + getCreationDate() +
                ", followerCount=" + followerCount +
                '}';
    }
}

