create table USUARIO (
    USER_ID varchar(255) primary key not null,
    EMAIL varchar(50),
    NOME varchar(50),
    SENHA varchar(255),
    TELEFONE varchar(10),
    CODVERIFICACAO int
);