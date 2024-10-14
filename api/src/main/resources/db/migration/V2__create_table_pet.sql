create table PET (
    PET_ID varchar(255) primary key not null,
    DATA_PUBLICACAO date,
    DESCRICAO varchar(255),
    FOTO blob,
    GENERO varchar(255),
    IDADE varchar(255),
    LOCALIZACAO DOUBLE,
    LATITUDE DOUBLE,
    LONGITUDE bigint,
    NOME varchar(255),
    STATUS varchar(255),
    TIPO_ANIMAL varchar(255),
    TAMANHO_ANIMAL varchar(255),
    USER_ID varchar(255) not null,
    foreign key(USER_ID) references USUARIO(USER_ID)
);