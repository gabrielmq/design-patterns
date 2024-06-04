package io.github.gabrielmsouza.creational.singleton;

public class Signup {
    private final UserRepository userRepository;

    public Signup() {
        this.userRepository = UserInMemoryRepository.getInstance();
    }

    public void execute(Input input) {
        final var user = User.create(input.name(), input.email(), input.password());
        userRepository.save(user);
    }

    public record Input(String name, String email, String password) {}
}
