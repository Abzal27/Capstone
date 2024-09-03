package za.ac.cput.factory;

/**
 * UserFactory.java
 *UserFactory of User Class
 *Author:Moegamat Isgak Abzal
 *Student Number: 221321810
 * */

import org.springframework.stereotype.Component;
import za.ac.cput.domain.User;


public class UserFactory implements IFactory<User>
{
    public User create(int id, String userName, String email, String pictureUrl, String firstName, String lastName, String phoneNumber, String password) {
        return new User.Builder()
                .setId(id)
                .setUserName(userName)
                .setEmail(email)
                .setPictureUrl(pictureUrl)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setPhoneNumber(phoneNumber)
                .setPassword(password)
                .build();
    }

    @Override
    public User create() {
        return new User.Builder().build();
    }

    public User create(User user) {
        return new User.Builder()
                .copy(user)
                .build();
    }
}
/*
 Author:Moegamat Isgak Abzal
 Date:7/5/2024
 */
