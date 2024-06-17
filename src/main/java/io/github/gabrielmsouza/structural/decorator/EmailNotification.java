package io.github.gabrielmsouza.structural.decorator;

public class EmailNotification implements Notifier {
    private final Notifier notifier;

    public EmailNotification(Notifier notifier) {
        this.notifier = notifier;
    }

    @Override
    public void notify(Object message) {
        System.out.println("EmailNotifier: Notifying message");
        this.notifier.notify(message);
    }
}
