import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class TicketBookingSystem {
    private int availableSeats;
    private final Lock lock = new ReentrantLock();

    public TicketBookingSystem(int seats) {
        this.availableSeats = seats;
    }

    public void bookTicket(String name, int seats, int priority) {
        Thread thread = new Thread(() -> {
            try {
                lock.lock();
                if (availableSeats >= seats) {
                    System.out.println(name + " successfully booked " + seats + " seat(s).");
                    availableSeats -= seats;
                } else {
                    System.out.println(name + " booking failed. Not enough seats available.");
                }
            } finally {
                lock.unlock();
            }
        });
        thread.setPriority(priority);
        thread.start();
    }
}

public class TicketBooking {
    public static void main(String[] args) {
        TicketBookingSystem system = new TicketBookingSystem(5);
        
        system.bookTicket("VIP-1", 2, Thread.MAX_PRIORITY);
        system.bookTicket("VIP-2", 2, Thread.MAX_PRIORITY);
        system.bookTicket("User-1", 1, Thread.NORM_PRIORITY);
        system.bookTicket("User-2", 1, Thread.MIN_PRIORITY);
        system.bookTicket("User-3", 1, Thread.MIN_PRIORITY);
    }
}
