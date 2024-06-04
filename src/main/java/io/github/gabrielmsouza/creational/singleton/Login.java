package io.github.gabrielmsouza.creational.singleton;

public class Login {
    private final UserRepository userRepository;

    public Login() {
        this.userRepository = UserInMemoryRepository.getInstance();
    }

    public Output execute(Input input) {
        return this.userRepository.findByEmail(input.email())
                .filter(user -> user.passwordMatches(input.password()))
                .map(user -> Output.SUCCESS)
                .orElse(Output.FAILURE);
    }

    public record Input(String email, String password) {}

    public record Output(boolean success) {
        public static final Output FAILURE = new Output(false);
        public static final Output SUCCESS = new Output(true);
    }
}
