package br.edu.ufape.topicos.catalogo.cadastro.mensagem;

import java.util.Date;

import lombok.Data;

import java.util.Date;

@Data
public class Event<K, T> {
    public enum Type {CREATE, DELETE, UPDATE}
    private Event.Type type;
    private K key;
    private T data;
    private Date createdAt;

    public Event (Type eventType, K key, T data) {
        this.type = eventType;
        this.key = key;
        this.data = data;
        this.createdAt = new Date ();
    }
}
