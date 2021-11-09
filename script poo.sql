create table localizacao(
    id serial primary key,
    endereco varchar (50),
    bairro varchar (50),
    cidade varchar (50),
    estado varchar (50)
);


create table locadora(
    id serial primary key,
    id_localizacao integer,
    nome varchar (50),
    cnpj varchar (50),
    telefone varchar (50),
    constraint fk_locadora_localizacao foreign key (id_localizacao) references localizacao(id)
);


create table usuario(
    id serial primary key,
    id_locadora integer not null,
    username varchar (50),
    senha varchar (50),
    email varchar (50),
    nome varchar (50),
    sobrenome varchar(50),
    constraint fk_usuario_locadora foreign key (id_locadora) references locadora(id)
);

create table admin(
    id serial primary key,
    id_locadora integer not null,
    username varchar (50),
    senha varchar (50),
    email varchar (50),
    nome varchar (50),
    sobrenome varchar(50),
    constraint fk_admin_locadora foreign key (id_locadora) references locadora(id)
);

create table locadoraadmin(
    id serial primary key,
    id_locadora integer not null,
    username varchar (50),
    senha varchar (50),
    email varchar (50),
    nome varchar (50),
    sobrenome varchar(50),
    constraint fk_locadoraadmin_locadora foreign key (id_locadora) references locadora(id)
);

create table cliente(
    id serial primary key,
	id_veiculo integer,
    username varchar (50),
    senha varchar (50),
    email varchar (50),
    nome varchar (50),
    sobrenome varchar(50),
	constraint fk_cliente_veiculo foreign key (id_veiculo) references veiculo(id)
);

create table veiculo(
    id serial primary key,
    id_locadora integer not null,
    codigo varchar (50),
    marca varchar (50),
    modelo varchar (50),
    ano varchar (50),
    acessorios varchar (50),
    preco double precision,
    categoria varchar (50),
    constraint fk_veiculo_locadora foreign key (id_locadora) references locadora(id)
);
