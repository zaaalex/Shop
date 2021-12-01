package com.zorkoalex.shop.users;

import com.zorkoalex.shop.dto.User;
import com.zorkoalex.shop.exception.UserExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl (UserRepository userRepository) {this.userRepository=userRepository;}

    @Override
    public void addUser(User user) throws UserExistException {
        if(userRepository.existsByNumber(user.getNumber())) {
            throw new UserExistException("");
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setName(user.getName());
        userEntity.setNumber(user.getNumber());
        userRepository.saveAndFlush(userEntity);
    }
}
