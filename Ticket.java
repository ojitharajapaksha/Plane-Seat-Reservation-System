import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

// Class representing a ticket for a seat on the plane
public class Ticket {
    // Declaring the instance variables
    private char seatRow;
    private int seatNumber;
    private Person person;
    private double ticketPrice;

    // Constructor to initialize the Ticket object with the provided values
    public Ticket(char seatRow, int seatNumber, Person person, double ticketPrice) {
        this.seatRow = seatRow;
        this.seatNumber = seatNumber;
        this.person = person;
        this.ticketPrice = ticketPrice;

        // Saving the ticket information to a file when created
        save();
    }
    // Getter method to get row letter of seat
    public char getSeatRow() {
        return seatRow;
    }
    // Getter method to get the seat number
    public int getSeatNumber() {
        return seatNumber;
    }

    // Getter method to get the price of the ticket
    public double getTicketPrice() {
        return ticketPrice;
    }

    // Getter method to get the person associated with the ticket
    public Person getPerson() {
        return person;
    }

    // Setter method to set the seat row letter
    public void setSeatRow(char seatRow) {
        this.seatRow = seatRow;
    }

    // Setter method for setting the seat number
    public void setSeatNumber(String seatNumber) {
        this.seatNumber = Integer.parseInt(seatNumber);
    }

    // Setter method to set the person associated with the ticket
    public void setPerson(Person person) {
        this.person = person;
    }

    // Setter method to set the price of the ticket
    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    // Method that prints the details of the ticket
    public void printTicketDetails() {
        System.out.println("Row: " + seatRow);
        System.out.println("Seat: " + seatNumber);
        System.out.println("Price: "+ "£" + ticketPrice);
    }
    // Method to save ticket information to a file
    public void save() {
        // Create a file name according to row and seat number
        String filename = seatRow + String.valueOf(seatNumber) + ".txt";

        // Creating a new file object
        File file = new File(filename);

        try (FileWriter writer = new FileWriter(file)) {

            // Write ticket information to file
            writer.write("Row: " + seatRow + "\n");
            writer.write("Seat: " + seatNumber + "\n");
            writer.write("Price: "+ "£" + ticketPrice + "\n");
            writer.write("Person's details: " + getPerson());

            // Print the confirmation message
            System.out.println("Ticket information saved to " + filename);
        } catch (IOException e) {
            // Handle error if file write failed
            System.out.println("File cannot be created.");
        }
    }
}
