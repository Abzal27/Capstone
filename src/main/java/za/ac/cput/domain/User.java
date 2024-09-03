
package za.ac.cput.domain;

/**
 * User.java
 *Domain of User Class
 *Author:Moegamat Isgak Abzal
 *Student Number: 221321810
 * */

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String userName;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String password;
    private String email;
    private String pictureUrl;

    public User() {
    }

    public User(int id, String userName, String firstName, String lastName, String phoneNumber, String password, String email, String pictureUrl) {
        this.id = id;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.email = email;
        this.pictureUrl = pictureUrl;
    }

    // Getters

    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return getId() == user.getId() &&
                Objects.equals(getUserName(), user.getUserName()) &&
                Objects.equals(getFirstName(), user.getFirstName()) &&
                Objects.equals(getLastName(), user.getLastName()) &&
                Objects.equals(getPhoneNumber(), user.getPhoneNumber()) &&
                Objects.equals(getPassword(), user.getPassword()) &&
                Objects.equals(getEmail(), user.getEmail()) &&
                Objects.equals(getPictureUrl(), user.getPictureUrl());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUserName(), getFirstName(), getLastName(), getPhoneNumber(), getPassword(), getEmail(), getPictureUrl());
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", pictureUrl='" + pictureUrl + '\'' +
                '}';
    }

    public static class Builder {
        private int id;
        private String userName;
        private String firstName;
        private String lastName;
        private String phoneNumber;
        private String password;
        private String email;
        private String pictureUrl;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setUserName(String userName) {
            this.userName = userName;
            return this;
        }

        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setPictureUrl(String pictureUrl) {
            this.pictureUrl = pictureUrl;
            return this;
        }

        // Copy method
        public Builder copy(User user) {
            this.id = user.getId();
            this.userName = user.getUserName();
            this.firstName = user.getFirstName();
            this.lastName = user.getLastName();
            this.phoneNumber = user.getPhoneNumber();
            this.password = user.getPassword();
            this.email = user.getEmail();
            this.pictureUrl = user.getPictureUrl();
            return this;
        }

        // Build method
        public User build() {
            return new User(id, userName, firstName, lastName, phoneNumber, password, email, pictureUrl);
        }
    }
}

