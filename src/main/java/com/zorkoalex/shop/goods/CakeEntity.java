package com.zorkoalex.shop.goods;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

/*
@Data — это всё равно, что иметь неявные
@Getter, @Setter, @ToString, @EqualsAndHashCode и @RequiredArgsConstructor для класса
(с исключением что никаких конструкторов не генерируется, если уже есть явно написанный конструктор).
 */

//hibernate - берет jba синтаксис и преобразует его во все запросы в базы данных

//если есть list<Entity> и мы достаем один элемент, то
//если LAZY инициализация, то возвращает Entity, не подгружая остальные
//если



@Entity//описывает класс в базе данных
@Getter
@Setter
@ToString
@RequiredArgsConstructor //генерирует конструктор для каждого final or nonnull поля
@Table(name = "CAKE")
public class CakeEntity {

    @Setter(AccessLevel.NONE)
    private @Id @GeneratedValue (strategy = GenerationType.IDENTITY) Long id;

    @Setter(AccessLevel.PROTECTED)
    @Column (name = "name")
    private String name;

    @Setter(AccessLevel.PROTECTED)
    private BigDecimal calories;

    @Setter(AccessLevel.PROTECTED)
    private String image;

    @Setter(AccessLevel.PROTECTED)
    private BigDecimal prise;

    @Setter(AccessLevel.PROTECTED)
    private BigDecimal weight;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CakeEntity that = (CakeEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
