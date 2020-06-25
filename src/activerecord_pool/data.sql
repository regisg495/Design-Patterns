create table pessoa (
	cpf char(11) not null check (length(trim(cpf)) = 11),
	nome varchar(100) not null check (length(trim(nome)) > 0),
	nascimento date not null default now() check (nascimento <= now()),
	genero char(1) not null default 'M' check ((genero = 'M') or (genero = 'F')),
	endereco varchar(100) not null check (length(trim(endereco)) > 0), -- csv
	telefones varchar(100), -- csv
	emails varchar(100), -- csv
	primary key (cpf)
);

create table funcionario (
	cpf char(11) not null check (length(trim(cpf)) = 11) references pessoa(cpf),
	salario real not null check (salario > 0),
	dependentes integer not null check (dependentes >= 0),
	contratacao date not null default now() check (contratacao <= now()),
	primary key (cpf)
);