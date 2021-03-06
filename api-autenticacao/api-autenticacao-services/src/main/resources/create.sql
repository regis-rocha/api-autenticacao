create table account(
	id varchar(50),
	name varchar(255) not null,
	email varchar(100) not null UNIQUE,
	password varchar(60) not null,
	created timestamp not null default CURRENT_TIMESTAMP,
	modified timestamp null,
	last_login timestamp null,
	token varchar(50) not null,
	timestamp_created_token timestamp not null default CURRENT_TIMESTAMP, 
	
	PRIMARY KEY(id),
	CONSTRAINT UNIQUE(email)
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
