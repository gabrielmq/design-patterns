package io.github.gabrielmsouza.creational.builder;

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

    public String getAirline() {
        return airline;
    }

    public String getFlightCode() {
        return flightCode;
    }

    public String getFromAirport() {
        return fromAirport;
    }

    public String getToAirport() {
        return toAirport;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public String getPassengerEmail() {
        return passengerEmail;
    }

    public String getPassengerDocument() {
        return passengerDocument;
    }

    public String getPassengerGender() {
        return passengerGender;
    }

    public String getEmergencyContactName() {
        return emergencyContactName;
    }

    public String getEmergencyContactTelephone() {
        return emergencyContactTelephone;
    }

    public String getSeat() {
        return seat;
    }

    public int getCheckedBags() {
        return checkedBags;
    }

    public boolean isHasCheckin() {
        return hasCheckin;
    }

    public String getTerminal() {
        return terminal;
    }

    public String getGate() {
        return gate;
    }

    public int getPriority() {
        return priority;
    }
}
