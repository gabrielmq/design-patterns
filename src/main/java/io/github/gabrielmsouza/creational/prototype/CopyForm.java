package io.github.gabrielmsouza.creational.prototype;

public class CopyForm {
    private final FormRepository formRepository;

    public CopyForm(FormRepository formRepository) {
        this.formRepository = formRepository;
    }

    public void execute(Input input) {
        final var form = this.formRepository.getById(input.fromFormId).orElseThrow();
        final var newForm = form.copy();
        newForm.update(input.newFormId, input.newCategory, input.newDescription);
        this.formRepository.save(newForm);
    }

    public record Input(String fromFormId, String newFormId, String newCategory, String newDescription) {

    }
}
