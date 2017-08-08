create table account(
	id varchar(50) primary key,
	name varchar(255) not null,
	email varchar(100) not null UNIQUE,
	password varchar(20) not null,
	created timestamp not null default CURRENT_TIMESTAMP,
	modified timestamp null,
	last_login timestamp null
);

create table phone(
	id varchar(50) primary key,
	ddd int not null,
	number int not null
);

create table account_phone(
	id_account,
	id_phone,
	
	PRIMARY KEY(id_account, id_phone),
	FOREIGN KEY (id_account) REFERENCES account(id),
	FOREIGN KEY (id_phone) REFERENCES phone(id);
);