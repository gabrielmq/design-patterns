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

### Flyweight
Permite que objetos sejam compartilhados, reduzindo a quantidade de
memória e melhorando a performance.
Atua como uma especie de cache para reduzir a quantidade de objetos em memória.

```java
public class Font {
    private final String name;
    private final int size;
    private final boolean bold;
    private final boolean italic;

    public Font(final String name, final int size, final boolean bold, final boolean italic) {
        this.name = name;
        this.size = size;
        this.bold = bold;
        this.italic = italic;
    }

    // aplicando o padrão flyweight criando uma especie de cache para compartilhar objetos
    // e reduzir a quantidade de memória utilizada
    public static class FontFactory {
        private static final Map<String, Font> FONTS = new HashMap<>();

        public static Font create(final String name, final int size, final boolean bold, final boolean italic) {
            final var key = name + size + bold + italic;
            return FONTS.computeIfAbsent(key, k -> new Font(name, size, bold, italic));
        }
    }
}
```
---
### Adapter
Atua como um intermediário entre dois objetos incompativeis que não conseguem se comunicar diretamente,
criando uma compatibilidade entre esses objetos.

```java
public interface PaymentGateway {
    String pay(Payment payment);
}

public enum PaymentType {
    PIX, CREDIT
}

public class PixPaymentGateway implements PaymentGateway {
    @Override
    public String pay(final Payment payment) {
        System.out.println("Realizando pagamento via Pix");
    }
}

public class CreditCardPaymentGateway implements PaymentGateway {
    @Override
    public String pay(final Payment payment) {
        System.out.println("Realizando pagamento via Cartão de Crédito");
    }
}

public class PaymentGatewayFactory {
    public static PaymentGateway create(final PaymentType type) {
        return switch (type) {
            case PIX -> new PixPaymentGateway();
            case CREDIT -> new CreditCardPaymentGateway();
            default -> throw new IllegalArgumentException("Tipo de pagamento não suportado");
        };
    }
}

public class PaymentService {
    public void pay(final Payment payment) {
        final var gateway = PaymentGatewayFactory.create(payment.getType());
        gateway.pay(payment);
    }
}
```
---
### Bridge
Desacoplar uma abstração de sua implementação, permitindo que ambas possam variar independentemente.
Faz uma separação conceitual para ter variações independentes.

```java
public interface Password {
    String value();
    boolean validate(String password);
}

public record PlainTextPassword(String value) implements Password {
    public static PlainTextPassword create(final String value) {
        return new PlainTextPassword(value);
    }

    @Override
    public boolean validate(final String password) {
        return value().equalsIgnoreCase(password);
    }
}

public record SHA1Password(String value) implements Password {
    public static SHA1Password create(final String value) {
        return new SHA1Password(hash(value));
    }

    @Override
    public boolean validate(final String password) {
        return value().equalsIgnoreCase(hash(password));
    }

    private static String hash(final String value) {
        return Hashing.sha1().hashString(value, UTF_8).toString();
    }
}

public enum PasswordType {
    PLAIN(PlainTextPassword::create),
    SHA1(SHA1Password::create);

    private final Function<String, Password> createFn;

    PasswordType(final Function<String, Password> createFn) {
        this.createFn = Objects.requireNonNull(createFn);
    }

    public Password create(final String value) {
        return createFn.apply(value);
    }
}

public abstract class Account {
    private String name;
    private String email;
    private String document;
    private Password password;

    protected Account(String name, String email, String document, PasswordType passwordType, String password) {
        this.name = name;
        this.email = email;
        this.document = document;
        this.password = passwordType.create(password);
    }

    public boolean isValidPassword(final String password) {
        return this.password.validate(password);
    }
}

public class Driver extends Account {
    private String carPlate;

    public Driver(String name, String email, String document, String carPlate, String password) {
        super(name, email, document, PasswordType.SHA1, password);
        if (!carPlate.matches("[A-Z]{3}[0-9]{4}")) throw new RuntimeException("Invalid car plate");
        this.carPlate = carPlate;
    }
}

public class Passenger extends Account {
    private String cardHolder;
    private String cardNumber;
    private String expirationDate;
    private String cvv;

    protected Passenger(
            String name,
            String email,
            String document,
            String cardHolder,
            String cardNumber,
            String expirationDate,
            String cvv,
            String password
    ) {
        super(name, email, document, PasswordType.SHA1, password);
        this.cardHolder = cardHolder;
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.cvv = cvv;
    }
}

```
---
### Decorator
Permite adicionar novos comportamentos a objetos existentes de forma dinamica sem alterar sua estrutura.

```java
public interface Notification {
    void send(String message);
}

// adicionando comportamento dinamicamente em cima da interface Notification
// utilizando o padrão decorator
public class EmailNotification implements Notification {
    private final Notification notification;
    
    public EmailNotification(final Notification notification) {
        this.notification = notification;
    }
    
    @Override
    public void send(final String message) {
        System.out.println("Enviando email: " + message);
        this.notification.send(message);
    }
}

public class SMSNotification implements Notification {
    private final Notification notification;
    
    public SMSNotification(final Notification notification) {
        this.notification = notification;
    }
    
    @Override
    public void send(final String message) {
        System.out.println("Enviando SMS: " + message);
        this.notification.send(message);
    }
}

public class Main {
    public static void main(String[] args) {
        final var emailNotification = new EmailNotification();
        final Notification notification = new SMSNotification(emailNotification);
        notification.send("Olá, mundo!");
    }
}

```
---
## Padrões Comportamentais

### Strategy
Define uma família de algoritmos, que encapsula em cada um deles as regras que
podem ser intercambiáveis variando de maneira independente com base nas necessidades
de quem utiliza.
Isola em objeto diferentes, regras diferente, permitindo flexibilidade no código.

```java
public interface FareCalculator {
    double calculate(LocalDateTime checkInDate, LocalDateTime checkOutDate);
}

public class AirportFareCalculator implements FareCalculator {
    @Override
    public double calculate(final LocalDateTime checkInDate, final LocalDateTime checkOutDate) {
        final var diff = Duration.between(checkInDate, checkOutDate).toHours();
        return diff * 10.0;
    }
}

public class BeachFareCalculator implements FareCalculator {
    @Override
    public double calculate(final LocalDateTime checkInDate, final LocalDateTime checkOutDate) {
        return 10.0;
    }
}

public class ShoppingFareCalculator implements FareCalculator {
    @Override
    public double calculate(LocalDateTime checkInDate, LocalDateTime checkOutDate) {
        final var diff = Duration.between(checkInDate, checkOutDate).toHours();
        var fare = 10.0;
        final var remainingHours = diff - 3;
        if (remainingHours > 0) {
            fare += remainingHours * 10.0;
        }
        return fare;
    }
}

public class PublicFareCalculator implements FareCalculator {
    @Override
    public double calculate(final LocalDateTime checkInDate, final LocalDateTime checkOutDate) {
        return 0;
    }
}

public enum Location {
    AIRPORT(new AirportFareCalculator()),
    BEACH(new BeachFareCalculator()),
    SHOPPING(new ShoppingFareCalculator()),
    PUBLIC(new PublicFareCalculator());

    private final FareCalculator fareCalculator;

    Location(final FareCalculator fareCalculator) {
        this.fareCalculator = fareCalculator;
    }

    public double calculate(final LocalDateTime checkInDate, final LocalDateTime checkOutDate) {
        return fareCalculator.calculate(checkInDate, checkOutDate);
    }
}

public class ParkingTicket {
    private final String plate;
    private final LocalDateTime checkInDate;
    private LocalDateTime checkOutDate;
    private final Location location;
    private double fare;

    public void checkout(final LocalDateTime checkOutDate) {
        this.checkOutDate = checkOutDate;
        // quando novas tarifas forem necessárias, basta criar uma nova classe que implementa FareCalculator
        // e adicionar uma nova localização no enum Location
        // sem a necessidade de alterar esse método e sem violar o princípio Open/Closed
        this.fare = this.location.calculate(this.checkInDate, this.checkOutDate);
    }

    public double getFare() {
        return fare;
    }
}
```
---
### State

Permite que um objeto altere seu comportamento quando seu estado interno muda.
Cria uma maquina de estado e suas transições. É um objeto que representa estado, alterando 
seu comportamento conforme o seu estado interno muda.

```java
// Definição do contrato para os estados de um ticket
public interface TicketStatus {
    void assign();
    void start();
    void close();

    enum Status {
        REQUESTED,
        ASSIGNED,
        IN_PROGRESS,
        CLOSED
    }
}

// Implementação dos estados de um ticket
public record RequestedStatus(Ticket ticket, TicketStatus.Status status) implements TicketStatus {

    public RequestedStatus(final Ticket ticket) {
        this(ticket, TicketStatus.Status.REQUESTED);
    }

    @Override
    public void assign() {
        this.ticket.changeStatus(new AssignedStatus(this.ticket));
    }

    @Override
    public void start() {
        throw new RuntimeException("Could not start ticket");
    }

    @Override
    public void close() {
        throw new RuntimeException("Could not close ticket");
    }
}


public record AssignedStatus(Ticket ticket, Status status) implements TicketStatus {

    public AssignedStatus(final Ticket ticket) {
        this(ticket, Status.ASSIGNED);
    }

    @Override
    public void assign() {
        throw new RuntimeException("Could not assign ticket");
    }

    @Override
    public void start() {
        this.ticket.changeStatus(new InProgressStatus(this.ticket));
    }

    @Override
    public void close() {
        throw new RuntimeException("Could not close ticket");
    }
}

public record ClosedStatus(Ticket ticket, Status status) implements TicketStatus {

    public ClosedStatus(final Ticket ticket) {
        this(ticket, Status.CLOSED);
    }

    @Override
    public void assign() {
        throw new RuntimeException("Could not assign ticket");
    }

    @Override
    public void start() {
        throw new RuntimeException("Could not start ticket");
    }

    @Override
    public void close() {
        throw new RuntimeException("Could not closed ticket");
    }
}

public record InProgressStatus(Ticket ticket, Status status) implements TicketStatus {

    public InProgressStatus(final Ticket ticket) {
        this(ticket, Status.IN_PROGRESS);
    }

    @Override
    public void assign() {
        throw new RuntimeException("Could not assign ticket");
    }

    @Override
    public void start() {
        throw new RuntimeException("Could not start ticket");
    }

    @Override
    public void close() {
        this.ticket.changeStatus(new ClosedStatus(this.ticket));
    }
}

public class Ticket {
    private TicketStatus status;
    private String employeeId;
    private String customerId;
    private LocalDateTime requestedAt;
    private LocalDateTime assignedAt;
    private LocalDateTime startedAt;
    private LocalDateTime closedAt;

    private Ticket(String customerId, LocalDateTime requestedAt) {
        this.status = new RequestedStatus(this);
        this.customerId = customerId;
        this.requestedAt = requestedAt;
    }
    
    public void changeStatus(final TicketStatus status) {
        this.status = status;
    }

    public void assign(String employeeId, LocalDateTime assignedAt) {
        this.employeeId = employeeId;
        this.assignedAt = assignedAt;
        // mudando o estado do ticket para ASSIGNED
        this.status.assign();
    }

    public void start(LocalDateTime startedAt) {
        this.startedAt = startedAt;
        this.status.start();
    }

    public void close(LocalDateTime closedAt) {
        this.closedAt = closedAt;
        this.status.close();
    }

    public TicketStatus getStatus() {
        return status;
    }
}

```
---
### Command

Encapsula uma solicitação como um objeto, permitindo parametrizar clientes com diferentes solicitações,
enfileirando e registrando essas solicitações, suportando operações de desfazer.

```java
public interface Command {
    void execute();
}

public class TransferCommand implements Command {
    private final BankAccount from;
    private final BankAccount to;
    private final double amount;

    public TransferCommand(BankAccount from, BankAccount bankAccount, double amount) {
        this.from = from;
        to = bankAccount;
        this.amount = amount;
    }


    @Override
    public void execute() {
        this.from.debit(this.amount);
        this.to.credit(this.amount);
    }
}

public class MakeTransfer {
    private final BankAccountRepository repository;

    public MakeTransfer(BankAccountRepository repository) {
        this.repository = repository;
    }

    public void execute(final Input input) {
        final var from = repository.findById(input.fromId);
        final var to = repository.findById(input.toId);
        final var transfer = new TransferCommand(from, to, input.amount);
        transfer.execute();
        repository.update(from);
        repository.update(to);
    }

    public record Input(String fromId, String toId, double amount) {}
}
```
---
### Chain of Responsibility

Cria uma cadeia de objetos, que recebem uma solicitação e passam para o próximo objeto da cadeia,
até que a solicitação seja atendida ou a cadeia termine.

```java
public interface FareCalculator {
    double calculate(Segment segment);
}

// aplicando o padrão para criar uma cadeia de calculo
public abstract class AbstractFareCalculator implements FareCalculator {
    // indica a execução de um proxima calculo
    protected FareCalculator next;

    public AbstractFareCalculator(final FareCalculator next) {
        this.next = next;
    }

    public boolean hasNext() {
        return Objects.nonNull(next);
    }
}

// cada implementação vai criando um nó na cadeia de execução do chain of responsibility
// e delegando para o próximo nó caso não consiga calcular o valor
public class NormalFareCalculator extends AbstractFareCalculator {
    private static final double FARE = 2.1;

    public NormalFareCalculator(final FareCalculator next) {
        super(next);
    }

    @Override
    public double calculate(final Segment segment) {
        if (!segment.isOvernight() && !segment.isSunday()) {
            return segment.getDistance() * FARE;
        }
        if (!hasNext())
            throw new RuntimeException();
        return this.next.calculate(segment);
    }
}

public class OvernightFareCalculator extends AbstractFareCalculator {
    private static final double FARE = 3.9;

    public OvernightFareCalculator(final FareCalculator next) {
        super(next);
    }

    @Override
    public double calculate(final Segment segment) {
        if (segment.isOvernight() && !segment.isSunday()) {
            return segment.getDistance() * FARE;
        }
        if (!hasNext())
            throw new RuntimeException();
        return this.next.calculate(segment);
    }
}

public class OvernightSundayFareCalculator extends AbstractFareCalculator {
    private static final double FARE = 5;

    public OvernightSundayFareCalculator(final FareCalculator next) {
        super(next);
    }

    @Override
    public double calculate(final Segment segment) {
        if (segment.isOvernight() && segment.isSunday()) {
            return segment.getDistance() * FARE;
        }
        if (!hasNext())
            throw new RuntimeException();
        return this.next.calculate(segment);
    }
}

public class SundayFareCalculator extends AbstractFareCalculator {
    private static final double FARE = 2.9;

    public SundayFareCalculator(final FareCalculator next) {
        super(next);
    }

    @Override
    public double calculate(final Segment segment) {
        if (!segment.isOvernight() && segment.isSunday()) {
            return segment.getDistance() * FARE;
        }
        if (!hasNext())
            throw new RuntimeException();
        return this.next.calculate(segment);
    }
}

public class Ride {
    private List<Segment> segments;
    private double fare;
    private FareCalculator fareCalculator;

    public Ride(final FareCalculator fareCalculator) {
        this.segments = new ArrayList<>();
        this.fareCalculator = fareCalculator;
    }

    public void addSegment(final double distance, final LocalDateTime date) {
        this.segments.add(new Segment(distance, date));
    }

    public void calculateFare() {
        this.fare = 0;
        for (Segment segment : segments) {
            // executando a cadeia de objetos para realizar o calculo
            // aqui se aplica o principio do OCP, pois podemos adicionar novas regras de calculo
            // sem precisar mexer nesse método
            this.fare += fareCalculator.calculate(segment);
        }
        this.fare = this.fare < 10 ? 10 : this.fare;
    }
}

public class Main {
    public static void main(String[] args) {
        final var overnightSundayFareCalculator = new OvernightSundayFareCalculator(null); // 4
        final var sundayFareCalculator = new SundayFareCalculator(overnightSundayFareCalculator); // 3
        final var overnightFareCalculator = new OvernightFareCalculator(sundayFareCalculator); // 2
        final var normalFareCalculator = new NormalFareCalculator(overnightFareCalculator); // 1
        ride = new Ride(normalFareCalculator);
        
        ride.addSegment(10, LocalDateTime.parse("2021-03-01T10:00:00"));
        ride.addSegment(10, LocalDateTime.parse("2021-03-01T23:00:00"));
        ride.addSegment(10, LocalDateTime.parse("2021-03-07T10:00:00"));
        ride.addSegment(10, LocalDateTime.parse("2021-03-07T23:00:00"));

        ride.calculateFare();
    }
}
```