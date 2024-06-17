package io.github.gabrielmsouza.structural.decorator;

public class SMSNotification implements Notifier {
    private final Notifier notifier;

    public SMSNotification(Notifier notifier) {
        this.notifier = notifier;
    }

    @Override
    public void notify(Object message) {
        System.out.println("EmailNotifier: Notifying message");
        this.notifier.notify(message);
    }
}
