package com.zorkoalex.shop.users;

import com.zorkoalex.shop.orders.OrderEntity;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "USER")
public class UserEntity {

    @Setter(AccessLevel.NONE)
    @ToString.Exclude
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.DETACH, orphanRemoval = false)
    //cascade - что произойдет, если удалится строка пользователя
    //orphanRemoval - можно ли удалить заказ внутри usera
    private List<OrderEntity> order;

    @Setter(AccessLevel.NONE)
    private @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Setter(AccessLevel.PROTECTED)
    @Column (name = "name")
    private String name;

    @Setter(AccessLevel.PROTECTED)
    @Column (name = "number",nullable = false, unique = true)
    @NaturalId(mutable = true) //разрешает изменение
    private Integer number;

    @Setter(AccessLevel.NONE)
    @ToString.Exclude
    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY, cascade = CascadeType.DETACH, orphanRemoval = false)
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

