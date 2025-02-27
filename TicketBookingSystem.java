class Seat {
    private boolean isBooked;

    public synchronized boolean book() {
        if (isBooked) {
            return false;
        } else {
            isBooked = true;
            return true;
        }
    }

    public boolean isBooked() {
        return isBooked;
    }
}

class BookingThread extends Thread {
    private Seat[] seats;
    private String customerType;

    public BookingThread(Seat[] seats, String customerType) {
        this.seats = seats;
        this.customerType = customerType;
    }

    @Override
    public void run() {
        for (Seat seat : seats) {
            if (seat.book()) {
                System.out.println(customerType + " booked a seat successfully.");
                break;
            }
        }
    }
}

public class TicketBookingSystem {
    public static void main(String[] args) {
        int numberOfSeats = 10;
        Seat[] seats = new Seat[numberOfSeats];
        for (int i = 0; i < numberOfSeats; i++) {
            seats[i] = new Seat();
        }

        BookingThread vipCustomer = new BookingThread(seats, "VIP");
        BookingThread regularCustomer = new BookingThread(seats, "Regular");

        // Set priorities
        vipCustomer.setPriority(Thread.MAX_PRIORITY);
        regularCustomer.setPriority(Thread.MIN_PRIORITY);

        // Start threads
        vipCustomer.start();
        regularCustomer.start();

        try {
            vipCustomer.join();
            regularCustomer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
