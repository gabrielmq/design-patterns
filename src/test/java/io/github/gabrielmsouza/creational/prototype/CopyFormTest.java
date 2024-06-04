package io.github.gabrielmsouza.creational.prototype;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CopyFormTest {

    private FormRepository formRepository;
    private CopyForm useCase;

    @BeforeEach
    void setUp() {
        this.formRepository = new FormInMemoryRepository();
        this.useCase = new CopyForm(formRepository);
    }

    @Test
    void shouldCopyForm() {
        // given
        final var form = Form.create("category", "description");
        form.addField("text", "name");
        form.addField("text", "email");
        formRepository.save(form);
        final var input = new CopyForm.Input(form.getId(), "newFormId", "newCategory", "newDescription");

        // when
        useCase.execute(input);

        // then
        final var newForm = formRepository.getById("newFormId").orElseThrow();
        assertEquals("newFormId", newForm.getId());
        assertEquals("newCategory", newForm.getCategory());
        assertEquals("newDescription", newForm.getDescription());
        assertEquals(2, newForm.getFields().size());
        assertEquals("text", newForm.getFields().get(0).getType());
        assertEquals("name", newForm.getFields().get(0).getTitle());
        assertEquals("text", newForm.getFields().get(1).getType());
        assertEquals("email", newForm.getFields().get(1).getTitle());
    }
}