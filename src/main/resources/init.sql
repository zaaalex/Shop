CREATE TABLE cake
(
    id       BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name     VARCHAR(255),
    calories DECIMAL,
    image    VARCHAR(255),
    prise    DECIMAL,
    weight   DECIMAL,
    CONSTRAINT pk_cake PRIMARY KEY (id)
);

CREATE TABLE userinfo
(
    id     BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name   VARCHAR(255),
    number VARCHAR(255)                            NOT NULL,
    CONSTRAINT pk_userinfo PRIMARY KEY (id)
);

ALTER TABLE userinfo
    ADD CONSTRAINT uc_userinfo_number UNIQUE (number);

CREATE TABLE paymentinfo
(
    id           BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    amount       DOUBLE PRECISION,
    payment_date TIMESTAMP WITHOUT TIME ZONE,
    status       INTEGER,
    order_id     BIGINT                                  NOT NULL,
    CONSTRAINT pk_paymentinfo PRIMARY KEY (id)
);

ALTER TABLE paymentinfo
    ADD CONSTRAINT FK_PAYMENTINFO_ON_ORDER FOREIGN KEY (order_id) REFERENCES orderinfo (id);

CREATE TABLE orderinfo
(
    id               BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    user_id          BIGINT                                  NOT NULL,
    delivery         INTEGER,
    delivery_address VARCHAR(255),
    delivery_time    TIMESTAMP WITHOUT TIME ZONE,
    order_status     INTEGER,
    CONSTRAINT pk_orderinfo PRIMARY KEY (id)
);

ALTER TABLE orderinfo
    ADD CONSTRAINT FK_ORDERINFO_ON_USER FOREIGN KEY (user_id) REFERENCES userinfo (id);

CREATE TABLE purchase
(
    id       BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    cake_id  BIGINT                                  NOT NULL,
    order_id BIGINT                                  NOT NULL,
    count    INTEGER,
    CONSTRAINT pk_purchase PRIMARY KEY (id)
);

ALTER TABLE purchase
    ADD CONSTRAINT FK_PURCHASE_ON_CAKE FOREIGN KEY (cake_id) REFERENCES cake (id);

ALTER TABLE purchase
    ADD CONSTRAINT FK_PURCHASE_ON_ORDER FOREIGN KEY (order_id) REFERENCES orderinfo (id);