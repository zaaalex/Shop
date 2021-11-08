CREATE TABLE cake_entity
(
    id       BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name     VARCHAR(255),
    calories DECIMAL,
    image    VARCHAR(255),
    prise    DECIMAL,
    weight   DECIMAL,
    CONSTRAINT pk_cakeentity PRIMARY KEY (id)
);