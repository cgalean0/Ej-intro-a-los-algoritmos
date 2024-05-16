/**
 * TicketMachine models a ticket machine that issues
 * flat-fare tickets.
 * The price of a ticket is specified via the constructor.
 * Instances will check to ensure that a user only enters
 * sensible amounts of money, and will only print a ticket
 * if enough money has been input.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2016.02.29
 */
public class TicketMachine
{
    // The price of a ticket from this machine.
    private int price;
    // The amount of money entered by a customer so far.
    private int balance;
    // The total amount of money collected by this machine.
    private int total;

    /**
     * Create a machine that issues tickets of the given price.
     * Precondition: The @param cost must be major or equal to zero.
     * Postcondition: The constructor create a new ticket machine with de @param cost obtained by user.
     */
    public TicketMachine(int cost)
    {
        if (cost < 0) {
            throw new IllegalArgumentException("Argument cost invalid!, must be major or equal to zero.");
        }
        price = cost;
        balance = 0;
        total = 0;
        assert repOK();
        assert price == cost;
        assert balance == 0;
        assert total == 0;
    }

    /**
     * @Return The price of a ticket.
     */
    public int getPrice()
    {
        return price;
    }

    /**
     * Return The amount of money already inserted for the
     * next ticket.
     */
    public int getBalance()
    {
        return balance;
    }

    /**
     * Receive an amount of money from a customer.
     * Check that the amount is sensible.
     * Precondition: The @param amount must be major or equal to zero.
     * Postcondition: This method insert a new amount in the balance of the User.
     */
    public void insertMoney(int amount)
    {
        if (amount < 0) {
            throw new IllegalArgumentException("Argument amount invalid!, must be major or equal to zero.");
        }
        if(amount > 0) {
            balance = balance + amount;
        }
        else {
            System.out.println("Use a positive amount rather than: " + amount);
        }
        assert repOK();
        assert balance == (balance + amount);

    }

    /**
     * Print a ticket if enough money has been inserted, and
     * reduce the current balance by the ticket price. Print
     * an error message if more money is required.
     */
    public void printTicket()
    {
        if (balance <= 0) {

            throw new IllegalStateException("The balance in your account is insuficient!");
        }
        if(balance >= price) {
            // Simulate the printing of a ticket.
            System.out.println("##################");
            System.out.println("# The BlueJ Line");
            System.out.println("# Ticket");
            System.out.println("# " + price + " cents.");
            System.out.println("##################");
            System.out.println();

            // Update the total collected with the price.
            total = total + price;
            // Reduce the balance by the price.
            balance = balance - price;
        }
        else {
            System.out.println("You must insert at least: " + (price - balance) + " more cents.");
        }
        assert repOK();
        assert total == total + price;
        assert balance > 0;
    }

    /**
     * Return the money in the balance.
     * The balance is cleared.
     * Precondition: The state of balance must be major to zero for refund balance.
     * Postcondition: Set balance to zero and @return amountToRefund.
     */
    public int refundBalance()
    {
        if (balance <= 0) {
            throw new IllegalStateException("You don't have balance to refund :C.");
        }
        int amountToRefund;
        amountToRefund = balance;
        balance = 0;

        assert repOK();
        assert balance == 0;
        return amountToRefund;
    }

    private boolean repOK(){
        if (price < 0) {
            return false;
        }
        if (balance < 0) {
            return false;
        }
        return true;
    }
}
