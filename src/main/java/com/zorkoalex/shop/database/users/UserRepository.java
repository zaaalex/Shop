package com.zorkoalex.shop.database.users;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByNumber(String number);
    void deleteByNumber(String number);
    UserEntity findUserEntityByNumber(String Number);
}
