class InvalidAmountException extends Exception {
    InvalidAmountException(String m) {
        super(m);
    }
}

class LowBalanceException extends Exception {
    LowBalanceException(String m) {
        super(m);
    }
}

class DailyLimitException extends Exception {
    DailyLimitException(String m) {
        super(m);
    }
}

public class Atm {
    static double balance = 5000;
    static double dailyWithdrawn = 0;
    static final double DAILY_LIMIT = 5000;

    static void withdraw(double amt)
            throws InvalidAmountException, LowBalanceException, DailyLimitException {

        if (amt % 100 != 0)
            throw new InvalidAmountException(
                amt + " is not a multiple of 100"
            );

        if (amt > balance)
            throw new LowBalanceException(
                "Balance is only " + balance
            );

        if (dailyWithdrawn + amt > DAILY_LIMIT)
            throw new DailyLimitException(
                "Daily withdrawal limit of Rs. " + DAILY_LIMIT + " exceeded"
            );

        balance = balance - amt;
        dailyWithdrawn = dailyWithdrawn + amt;

        System.out.println(
            "Dispensed " + amt + ", balance " + balance
        );
    }

    public static void main(String[] args) {

        double[] tries = { 20, 3500, 9000, 1500 };

        for (double a : tries) {
            try {
                withdraw(a);
            }

            catch (InvalidAmountException e) {
                System.out.println(
                    "Invalid Amount : " + e.getMessage()
                );
            }

            catch (LowBalanceException e) {
                System.out.println(
                    "Low Balance : " + e.getMessage()
                );
            }

            catch (DailyLimitException e) {
                System.out.println(
                    "Daily Limit : " + e.getMessage()
                );
            }

            finally {
                System.out.println(
                    "Balance after attempt : " + balance
                );
                System.out.println();
            }
        }
    }
}
