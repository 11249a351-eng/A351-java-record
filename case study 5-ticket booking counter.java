class Seats {
    private int available = 10;

    void book() {
        if (available > 0) {

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            available--;

            System.out.println(
                Thread.currentThread().getName()
                + " booked seat. Left = " + available
            );

        } else {
            System.out.println(
                Thread.currentThread().getName()
                + " -> HOUSE FULL"
            );
        }
    }
}

public class Booking {
    public static void main(String[] args) {

        Seats s = new Seats();

        for (int i = 1; i <= 3; i++) {

            Thread t = new Thread(() -> {
                for (int j = 0; j < 4; j++) {
                    s.book();
                }
            });

            t.setName("Counter-" + i);
            t.start();
        }
    }
}
