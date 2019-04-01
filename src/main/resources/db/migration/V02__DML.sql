insert into status_order(name) values ('Pending payment');
insert into status_order(name) values ('Closed');
insert into status_order(name) values ('Canceled');

insert into status_payment(name) values ('Paid');
insert into status_payment(name) values ('Refunded');


insert into store (name, address) values ('Mapron Materiais de Construção', 'Av. Crisógono Fernandes, 45');
insert into store (name, address) values ('Lojas Americanas', 'Av. JJ Seabra, S/N');
insert into store (name, address) values ('Decore Móveis planejados', 'Av. Alberto Passos, 21, Cruz das Almas, Bahia');

insert into order_payment(address, confirmation_date, id_status_order, id_store) 
values ('Rua Manoel Alves da Fonsceca, Cond. Vila Real, casa 66, Tabela, Cruz das Almas, Bahia', '2019-03-27', 1, 2);

insert into order_payment(address, confirmation_date, id_status_order, id_store) 
values ('Estrada das Barreiras, Cond. CHOPM 2, Apt, 201, Cabula, Salvador, Bahia', '2019-03-29', 1, 2);

insert into order_payment(address, confirmation_date, id_status_order, id_store) 
values ('Salvador, Bahia', '2019-03-20', 1, 1);

insert into order_payment_item (description, unit_price, quantity, id_order_payment)
values ('Mesa de jantar', 560.99, 1, 1);

insert into order_payment_item (description, unit_price, quantity, id_order_payment)
values ('Cadeira de jantar', 230.00, 6, 1);

insert into order_payment_item (description, unit_price, quantity, id_order_payment)
values ('Bicicleta', 1200.00, 1, 2);

insert into order_payment_item (description, unit_price, quantity, id_order_payment)
values ('Livro 1', 59.90, 1, 3);

insert into order_payment_item (description, unit_price, quantity, id_order_payment)
values ('Livro 2', 19.90, 1, 3);


commit;