create table CONFIGURACAO (
    CODCONFIGURACAO int not null primary key,
    CONFIGURACAO varchar(255)
);

insert into CONFIGURACAO (CODCONFIGURACAO, CONFIGURACAO) values
    (1, 'smtp.gmail.com'),
    (2, 'luizteles.devs@gmail.com'),
    (3, 'V@catoddy19'),
    (4, '587');
