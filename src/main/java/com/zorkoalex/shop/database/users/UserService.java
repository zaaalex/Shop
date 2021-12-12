package com.zorkoalex.shop.database.users;

import com.zorkoalex.shop.dto.user.User;
import com.zorkoalex.shop.dto.user.Users;
import com.zorkoalex.shop.exception.UserExistException;

public interface UserService {
    void addUser(User user) throws UserExistException;
    void deleteUser(String number) throws UserExistException;
    Users getUsers();
}
