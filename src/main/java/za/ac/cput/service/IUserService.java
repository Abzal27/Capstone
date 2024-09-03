package za.ac.cput.service;

/**
 * IUserServer.java
 *IServer of User Class
 *Author:Moegamat Isgak Abzal
 *Student Number: 221321810
 * */

import za.ac.cput.domain.User;

import java.util.ArrayList;

public interface IUserService extends IService<User, Integer> {
    User create(User user);

    User read(Integer id);

    User update(User user);

    boolean delete(Integer id);

    ArrayList<User> getAll();
}
/*
 Author:Moegamat Isgak Abzal
 Date:7/5/2024
 */

