package io.github.gabrielmsouza.creational.prototype;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Form implements Prototype {
    private String id;
    private String category;
    private String description;
    private List<Field> fields;

    private Form(String id, String category, String description) {
        this.id = id;
        this.category = category;
        this.description = description;
        this.fields = new ArrayList<>();
    }

    public static Form create(String category, String description) {
        return new Form(UUID.randomUUID().toString(), category, description);
    }

    public void addField(String type, String title) {
        this.fields.add(Field.create(type, title));
    }

    // Implementação do pattern Prototype para clonar o objeto Form
    @Override
    public Form copy() {
        final var newForm = new Form(this.id, this.category, this.description);
        newForm.fields = this.fields.stream().map(Field::copy).toList();
        return newForm;
    }

    public String getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public List<Field> getFields() {
        return fields;
    }

    public void update(String newFormId, String newCategory, String newDescription) {
        this.id = newFormId;
        this.category = newCategory;
        this.description = newDescription;
    }
}
