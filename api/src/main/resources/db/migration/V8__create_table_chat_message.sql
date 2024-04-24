create table CHAT_MESSAGE (
    CHAT_MESSAGE_ID varchar(255) primary key not null ,
    SENDER_ID varchar(255),
    RECIPIENT_ID varchar(255),
    CONTENT varchar(255),
    TIMESTAMP timestamp,
    CHAT_ROOM_ID varchar(255),
    foreign key(CHAT_ROOM_ID) references CHAT_ROOM(CHAT_ROOM_ID)
);
