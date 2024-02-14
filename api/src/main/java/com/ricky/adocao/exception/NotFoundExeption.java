package com.ricky.adocao.exception;

public class NotFoundExeption extends RuntimeException{
    public NotFoundExeption() {
        super("Registro não encontrado");
    }
}
