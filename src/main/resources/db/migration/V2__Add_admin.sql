INSERT INTO user
    (id, active, email, password, username, first_name)
values (1, '1', 'admin@gmail.com', '$2a$11$SxLjGe1kDnjRapbFYuJzhu9uR89vU4CR4Tdk9VekH2J8EQSM7bDJm', 'admin', 'Admin');

INSERT INTO user_role
    (user_id, roles)
VALUES (1, 'ADMIN'),
       (1, 'USER');


INSERT INTO stats
    (id, code, name, description)
    VALUE
    (4, 'CREATED', 'Створено', 'Ваше замовлення створено, та чекає на підтвердження зі сторони продавця'),
    (5, 'CONFIRMED', 'Підтверджено',
     'Ваше замовлення підтверджено, продавець на даний момент обробляє замовлення, скоро воно буде відправлено'),
    (6, 'SHIPPED_OUT', 'Відправлено', 'Продавець відправив замовлення, воно прямує по зазначеній у замовленні адресі'),
    (7, 'COMPLETED', 'Виконано', 'Покупець отримав замовлення, та підтвердив факт отримання'),
    (8, 'CANCELED', 'Скасовано', 'Замовлення скасовано'),
    (9, 'REJECTED', 'Відхилено', 'Замовлення відхилено');

INSERT INTO measurement_unit
    (id, full_name, short_name)
    VALUE
    (10, 'Кілограм', 'кг.'),
    (11, 'Штуки', 'шт.'),
    (12, 'Літри', 'л.'),
    (13, 'Мілілітри', 'мо.'),
    (14, 'Міліграми', 'мг.');