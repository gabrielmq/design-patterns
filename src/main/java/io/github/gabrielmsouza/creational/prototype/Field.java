package io.github.gabrielmsouza.creational.prototype;

import java.util.UUID;

public class Field implements Prototype {
    private String id;
    private String type;
    private String title;

    private Field(String id, String type, String title) {
        this.id = id;
        this.type = type;
        this.title = title;
    }

    public static Field create(String type, String title) {
        return new Field(UUID.randomUUID().toString(), type, title);
    }

    @Override
    public Field copy() {
        return new Field(this.id, this.type, this.title);
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }
}
