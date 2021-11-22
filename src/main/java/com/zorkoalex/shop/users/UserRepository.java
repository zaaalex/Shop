package com.zorkoalex.shop.users;

import com.zorkoalex.shop.goods.CakeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByNumber(Integer number);
    UserEntity findUserEntityByNumber(Integer Number);
}
