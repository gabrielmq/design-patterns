# Design Patterns

## Padrões criacionais

### Abstract Factory
Fornece um contrato (interface) para a criação de famílias de objetos relacionados.
A ideia desse padrão é ter um contrato para que possam ser criadas várias fábricas a partir desse contrato.
A fabrica não deve especificar as classes concretas.

```java
// Definição do contrato de uma abstract factory para a criação
// da familia de objetos relacionados a Loan
public interface LoanFactory {
    Loan createLoan(double amount, double income, int installments);
    InstallmentCalculator createInstallmentCalculator();
}

public class CarLoanFactory implements LoanFactory {
    @Override
    public Loan createLoan(double amount, double income, int installments) {
        return CarLoan.create(amount, income, installments);
    }

    @Override
    public InstallmentCalculator createInstallmentCalculator() {
        return new PriceInstallmentCalculator();
    }
}

public class MortgageLoanFactory implements LoanFactory {
    @Override
    public Loan createLoan(double amount, double income, int installments) {
        return MortgageLoan.create(amount, income, installments);
    }

    @Override
    public InstallmentCalculator createInstallmentCalculator() {
        return new SACInstallmentCalculator();
    }
}

// Definição do contrato de uma abstract factory para a criação
// da familia de objetos relacionados aos Gateways de Loan
public interface GatewaysFactory {
    LoanGateway createLoanGateway();
    InstallmentGateway createInstallmentGateway();
}

public class GatewayInMemoryFactory implements GatewaysFactory {
    @Override
    public LoanGateway createLoanGateway() {
        return LoanInMemoryGateway.getInstance();
    }

    @Override
    public InstallmentGateway createInstallmentGateway() {
        return InstallmentInMemoryGateway.getInstance();
    }
}

// Usando as abstract factories
public class ApplyForLoanUseCase {
    private final LoanGateway loanGateway;
    private final InstallmentGateway installmentGateway;
    private final LoanFactory loanFactory;

    // usando a abstract factory conseguimos desacoplar o use case das instancias, dessa forma
    // ele não precisa saber qual tipo de instancia dos gateways e loans esta recebendo
    public ApplyForLoanUseCase(final GatewaysFactory gatewaysFactory, final LoanFactory loanFactory) {
        this.loanGateway = Objects.requireNonNull(gatewaysFactory).createLoanGateway();
        this.installmentGateway = Objects.requireNonNull(gatewaysFactory).createInstallmentGateway();
        this.loanFactory = Objects.requireNonNull(loanFactory);
    }

    public Output execute(final Input input) {
        final var loan = this.loanFactory.createLoan(input.amount, input.income, input.installments);
        final var installments = this.loanFactory.createInstallmentCalculator().calculate(loan);
        this.loanGateway.save(loan);
        installments.forEach(this.installmentGateway::save);
        return new Output(loan.getId());
    }

    public record Input(double amount, double income, int installments, String type) {}

    public record Output(String id) {}
}

```

---

### Factory Method
Define uma interface para a criação de objetos, permitindo que as subclasses decidam qual instancia
deve ser criada. A super classe tem apenas o metodo abstrato de criação, delegando a responsabilidade
de quais instancias devem ser criadas para as subclasses.

```java
// Classe abstrata ou interface que define o contrato para a criação de objetos
public abstract class Ride {
    // factory method definido para as subclasses criarem suas instâncias
    // dever ser abstrato e delegar para a subclasse, segundo o gof
    public abstract Segment createSegment(String rideId, Location from, Location to);
}

// Subclasse que implementa o factory method
public class TimeRide extends Ride {
    // implementação do factory method
    @Override
    public Segment createSegment(final String rideId, final Location from, final Location to) {
        return TimeSegment.create(rideId, from, to);
    }
}

// Subclasse que implementa o factory method
public class DistanceRide extends Ride {
    // implementação do factory method
    @Override
    public Segment createSegment(final String rideId, final Location from, final Location to) {
        return DistanceSegment.create(rideId, from, to);
    }
}

// usando o factory method
public class UpdateLocationUseCase {
    ...
    
    public void execute(final Input input) {
        final var ride = this.rideRepository.findById(input.rideId).orElseThrow();
        final var newLocation = Location.create(input.latitude, input.longitude, input.date);

        // criando um segment fabricado por alguma subclasse de ride que implementa o factory method
        final var segment = ride.createSegment(ride.getId(), ride.getLastLocation(), newLocation);

        ...
    }
}

```
---
### Singleton
Garante que uma classe tenha apenas uma instância, fornecendo um ponto de acesso global a ela.

```java
public class Singleton {
    private static Singleton instance;
    
    private Singleton() {}
    
    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
```
---
### Prototype
Especifica uma interface de protótipos que serve de base para a criação de cópias de novos objetos. 
Facilita a criação de novos objetos a partir da cópia de objetos existentes.

```java
// Criando uma interface Prototype que define o método copy que será
// implementado pelas classes que desejam ser clonadas
public interface Prototype {
    Prototype copy();
}

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

    // Implementação do pattern Prototype para clonar o objeto Form
    @Override
    public Form copy() {
        final var newForm = new Form(this.id, this.category, this.description);
        newForm.fields = this.fields.stream().map(Field::copy).toList();
        return newForm;
    }

    ...
}

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

    ...
}
```

### Builder
Separa a construção de um objeto complexo de sua representação, permitindo que o 
mesmo processo de construção crie diferentes representações. Esse padrão abstrai o passo  a passo
de uma abstração complexa.

```java
public class FlightTicket {
    private final String airline;
    private final String flightCode;
    private final String fromAirport;
    private final String toAirport;
    private final String passengerName;
    private final String passengerEmail;
    private final String passengerDocument;
    private final String passengerGender;
    private final String emergencyContactName;
    private final String emergencyContactTelephone;
    private final String seat;
    private final int checkedBags;
    private final boolean hasCheckin;
    private final String terminal;
    private final String gate;
    private final int priority;

    private FlightTicket(final Builder builder) {
        this.airline = builder.airline;
        this.flightCode = builder.flightCode;
        this.fromAirport = builder.fromAirport;
        this.toAirport = builder.toAirport;
        this.passengerName = builder.passengerName;
        this.passengerEmail = builder.passengerEmail;
        this.passengerDocument = builder.passengerDocument;
        this.passengerGender = builder.passengerGender;
        this.emergencyContactName = builder.emergencyContactName;
        this.emergencyContactTelephone = builder.emergencyContactTelephone;
        this.seat = builder.seat;
        this.checkedBags = builder.checkedBags;
        this.hasCheckin = builder.hasCheckin;
        this.terminal = builder.terminal;
        this.gate = builder.gate;
        this.priority = builder.priority;
    }

    public static Builder builder() {
        return new Builder();
    }

    // aplicando o padrão builder para a construção de objetos complexos
    public static class Builder {
        private String airline;
        private String flightCode;
        private String fromAirport;
        private String toAirport;
        private String passengerName;
        private String passengerEmail;
        private String passengerDocument;
        private String passengerGender;
        private String emergencyContactName;
        private String emergencyContactTelephone;
        private String seat;
        private int checkedBags;
        private boolean hasCheckin;
        private String terminal;
        private String gate;
        private int priority;

        public Builder() {
        }

        public Builder withFlight(final String airline, final String flightCode) {
            this.airline = airline;
            this.flightCode = flightCode;
            return this;
        }

        public Builder withTrip(final String from, final String to) {
            this.fromAirport = from;
            this.toAirport = to;
            return this;
        }

        public Builder withPassenger(final String name, final String email, final String document, final String gender) {
            this.passengerName = name;
            this.passengerEmail = email;
            this.passengerDocument = document;
            this.passengerGender = gender;
            return this;
        }

        public Builder withEmergencyContact(final String name, final String telephone) {
            this.emergencyContactName = name;
            this.emergencyContactTelephone = telephone;
            return this;
        }

        public Builder withSeat(final String seat) {
            this.seat = seat;
            return this;
        }

        public Builder withCheckedBags(final int checkedBags) {
            this.checkedBags = checkedBags;
            return this;
        }

        public Builder withCheckInInformation(final boolean hasCheckin, final String terminal, final String gate) {
            this.hasCheckin = hasCheckin;
            this.terminal = terminal;
            this.gate = gate;
            return this;
        }

        public Builder withPriority(final int priority) {
            this.priority = priority;
            return this;
        }

        public FlightTicket build() {
            return new FlightTicket(this);
        }
    }
    ...
}
```
---
## Padrões Estruturais