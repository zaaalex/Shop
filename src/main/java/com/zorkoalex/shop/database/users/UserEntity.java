package com.zorkoalex.shop.database.users;

import com.zorkoalex.shop.database.orders.OrderEntity;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "USERINFO")
public class UserEntity {

    @Setter(AccessLevel.NONE)
    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;

    @Setter(AccessLevel.PROTECTED)
    @Column (name = "name")
    private String name;

    @Setter(AccessLevel.PROTECTED)
    @Column (name = "number",nullable = false, unique = true)
    @NaturalId(mutable = true) //разрешает изменение
    private String number;

    @Setter(AccessLevel.NONE)
    @ToString.Exclude
    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY, orphanRemoval = false)
    //cascade - что произойдет, если удалится строка пользователя cascade = CascadeType.DETACH
    //orphanRemoval - можно ли удалить заказ внутри usera
    private List<OrderEntity> orders = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UserEntity that = (UserEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

