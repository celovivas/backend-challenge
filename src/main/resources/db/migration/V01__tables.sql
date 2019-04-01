	create table store(
			id bigint auto_increment not null,
			name    varchar(100) not null,
			address varchar(200) not null,
			
			primary key (id)
		);


		create table order_payment(
			id bigint auto_increment not null,
			address varchar(200) not null,
			confirmation_date timestamp null,
			id_status_order int not null,
			id_store bigint not null,
			
			primary key (id)
		);

		create table order_payment_item(
			id bigint auto_increment not null,
			description varchar(200) not null,
			unit_price decimal(10,2) not null, 
			quantity int not null,
		    id_order_payment bigint,
			
			primary key (id)
		);

		create table payment(
			id bigint auto_increment not null,
			cretid_cart varchar(50) not null,
			payment_date timestamp null,
			id_order_payment bigint not null,
			id_status_payment bigint,

			primary key (id)
		);

		create table status_order(
			id bigint auto_increment not null,
			name varchar(100) not null,
			
			primary key (id)
		);

		create table status_payment(
			id bigint auto_increment not null,
			name varchar(100) not null,
			
			primary key (id)
		);
			

		/*    
		alter table order_payment add constraint fk_order_payment_status_order
		    foreign key (id_status_order)
		    references status (id)
		    on delete no action;
		    
		alter table order_payment_item add constraint fk_order_payment_order_payment_item
		    foreign key (id_order_payment)
		    references order_payment (id)
		    on delete no action;    

		alter table payment add constraint fk_payment_order_payment
		    foreign key (id_order_payment)
		    references order_payment (id)
		    on delete no action;    
		    
		alter table payment add constraint fk_payment_status_payment
		    foreign key (id_status_payment)
		    references status (id)
		    on delete no action;    
		 */