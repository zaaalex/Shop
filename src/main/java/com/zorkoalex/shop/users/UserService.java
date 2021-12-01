package com.zorkoalex.shop.users;

import com.zorkoalex.shop.dto.User;
import com.zorkoalex.shop.exception.UserExistException;

public interface UserService {
    void addUser(User user) throws UserExistException;
}
