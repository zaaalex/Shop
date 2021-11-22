package com.zorkoalex.shop.users;

import com.zorkoalex.shop.dto.Purchase;
import com.zorkoalex.shop.dto.User;
import com.zorkoalex.shop.exception.UserExistException;

public interface UserService {
    void addUser(UserEntity user) throws UserExistException;
    Long getUserId(Integer number);
}
