// Import the Scanner class to allow user input
import java.util.Scanner;

// Creating the PlaneManagement class
public class PlaneManagement {
    //Create the array to monitor seat availability.
    private static boolean[] seats = new boolean[56];
    // Create the array to store ticket objects
    private static Ticket[] tickets = new Ticket[56];
    // Variable to monitor the quantity of tickets purchased
    private static int ticketSold = 0;
    // Scanner object to receive user input
    private static Scanner scanner = new Scanner(System.in);

    // Main method to start the program
    public static void main(String[] args) {

        // Loop to continuously display menu options
        while (true) {
            // Display the welcome message
            System.out.println("Welcome to the Plane Management application");

            // Showing menu options
            System.out.println("**************************************************");
            System.out.println("*                MENU OPTIONS                    *");
            System.out.println("**************************************************");

            System.out.println("1) Buy a seat");
            System.out.println("2) Cancel a seat");
            System.out.println("3) Find first available seat");
            System.out.println("4) Show seating plan");
            System.out.println("5) Print tickets information and total sales");
            System.out.println("6) Search ticket");
            System.out.println("0) Quit");

            System.out.println("**************************************************");

            // Asking for input from the user
            System.out.print("Please select an option :");

            // Checking if user input is an integer
            if (!scanner.hasNextInt()) {
                System.out.println("Invalid option number please enter option number between (0-6).");
                scanner.next();
                continue;
            }

            // Reading user choice
            int choice = scanner.nextInt();

            // Validate user selection
            if (choice < 0 || choice > 6) {
                System.out.println("Invalid option number. Please enter a option number between (0-6).");
                continue;
            }

            // Handle user selection
            switch (choice) {
                case 1:
                    buy_seat();
                    break;
                case 2:
                    cancel_seat();
                    break;
                case 3:
                    find_first_available();
                    break;
                case 4:
                    show_seating_plan(seats);
                    break;
                case 5:
                    print_tickets_info(tickets, ticketSold);
                    break;
                case 6:
                    search_ticket();
                    break;
                case 0:
                    // Exit the program
                    System.exit(0);
            }
        }
    }
    // Method to purchase a seat
    private static void buy_seat() {

        // prompts the user to enter a row letter
        System.out.print("Enter the row letter between (A-D): ");
        char rowLetter = scanner.next().toUpperCase().charAt(0);

        // Validate row letter
        if (rowLetter < 'A' || rowLetter > 'D') {
            System.out.println("Invalid row letter. Please enter the row letter between (A-D).");
            return;
        }

        // Prompts the user to enter seat number
        System.out.print("Enter the seat number between (1-14): ");

        // Validate seat number input
        if (!scanner.hasNextInt()) {
            System.out.println("Invalid seat number please enter seat number between (1-14).");
            scanner.next();
            return;
        }

        // Reading the seat number
        int seatNumber = scanner.nextInt();

        // Validate seat number
        if (seatNumber < 1 || seatNumber > 14) {
            System.out.println("Invalid seat number. Please enter a number between (1-14).");
            return;
        }

        // Calculate the seat index in the array
        int seatIndex = get_seat_index(rowLetter, seatNumber);

        // Checking seats are available
        if (seats[seatIndex]) {
            System.out.println("Seat " + rowLetter + seatNumber + " is already reserved. Please choose another seat.");
        } else {
            // Seat reservation
            seats[seatIndex] = true;

            // Requesting the person's details
            System.out.print("Enter your First Name: ");
            String firstName = scanner.next();
            System.out.print("Enter your Surname: ");
            String surname = scanner.next();
            System.out.print("Enter your Email: ");
            String email = scanner.next();

            // Create a new Person object
            Person person = new Person(firstName, surname, email);

            // Determining ticket price by seat number
            double price;
            if (seatNumber <= 5){
                price=200.0;
            } else if (seatNumber<=9) {
                price=150.0;
            }else {
                price=180.0;
            }

            // Create a new Ticket object
            Ticket ticket = new Ticket(rowLetter, seatNumber, person, price);

            // Storing the ticket in the array
            tickets[ticketSold++]=ticket;

            // Display confirmation message for successful seat booking
            System.out.println("Seat reserved successfully. Your seat is " + rowLetter + seatNumber + ".");
        }
    }
    // Method to calculate the seat index in the array
    private static int get_seat_index(char rowLetter, int seatNumber) {
        int rowIndex = rowLetter - 'A';
        return rowIndex * 14 + (seatNumber - 1);
    }

    // Method to cancel a seat reservation
    private static void cancel_seat() {

        // prompts the user to enter row letter
        System.out.print("Enter the row letter between (A-D): ");
        char rowLetter = scanner.next().toUpperCase().charAt(0);

        // Validate row letter
        if (rowLetter < 'A' || rowLetter > 'D') {
            System.out.println("Invalid row letter. Please enter a letter between (A-D).");
            return;
        }

        // Prompts the user to enter seat number
        System.out.print("Enter the seat number between 1 and 14: ");

        // Validate seat number input
        if (!scanner.hasNextInt()) {
            System.out.println("Invalid seat number please enter seat number between (1-14)).");
            scanner.next();
            return;
        }

        // Reading the seat number
        int seatNumber = scanner.nextInt();

        // Validating seat number
        if (seatNumber < 1 || seatNumber > 14) {
            System.out.println("Invalid seat number. Please enter a number between (1-14)).");
            return;
        }

        // Calculate the seat index in the array
        int seatIndex = get_seat_index(rowLetter, seatNumber);

        // Checking seats are available

        if (!seats[seatIndex]) {
            System.out.println("Seat " + rowLetter + seatNumber + " is not reserved.");

        }
        else {
            // Cancel the seat reservation
            seats[seatIndex] = false;
            for (int i = 0; i< ticketSold; i++){
                if (tickets[i].getSeatRow()==rowLetter && tickets[i].getSeatNumber()==seatNumber){
                    for (int j = i; j< ticketSold -1; j++){
                        tickets[j]= tickets[j+1];
                    }
                    ticketSold--;
                    break;
                }
            }
            System.out.println("You have successfully cancelled " + rowLetter + seatNumber + " seat reservation.");
        }

    }
    // Method to find the first available seat
    private static void find_first_available() {
        // Iterate over the row and seat numbers to find the first available seat
        for (char row = 'A'; row <= 'D'; row++) {
            for (int seatNumber = 1; seatNumber <= 14; seatNumber++) {
                int seatIndex = get_seat_index(row, seatNumber);
                if (!seats[seatIndex]) {
                    System.out.println("You can reserve " + row + seatNumber + " as the first available seat.");
                    return;
                }
            }
        }
        System.out.println("No available seats found.");
    }
    // Method to display the seat plan
    private static void show_seating_plan(boolean[] seats) {
        // Iterate over the row and seat numbers to display the seating plan
        for (char row = 'A'; row <= 'D'; row++) {
            System.out.print(row + " ");
            int seatsIntRow;
            if (row == 'A' || row == 'D') {
                seatsIntRow = 14;
            } else {
                seatsIntRow = 12;
            }

            for (int seatNumber = 1; seatNumber <= seatsIntRow; seatNumber++) {
                int seatIndex = get_seat_index(row, seatNumber);
                if (seats[seatIndex]) {
                    System.out.print("X");
                } else {
                    System.out.print("O");
                }
            }
            System.out.println();
        }
    }
    // Method to print ticket information and total sales
    private static void print_tickets_info(Ticket[] tickets, int ticketSold) {
        if(ticketSold==0){
            System.out.println("Ticket not sold.");
        }
        else {
            double totalTicketSold=0;
            for (int temp=0; temp<ticketSold; temp++){
                System.out.println("Ticket: "+(temp+1));
                tickets[temp].printTicketDetails();
                totalTicketSold += tickets[temp].getTicketPrice();
                tickets[temp].getPerson().printInformationOfPerson();
            }
            System.out.println("Total Sales: "+"£"+totalTicketSold);
        }
    }
    // Method to search for a specific ticket
    private static void search_ticket(){

        // Prompts the user to enter row letter
        System.out.print("Enter the row letter between (A-D): ");
        char rowLetter = scanner.next().toUpperCase().charAt(0);

        // Validate row letter
        if (rowLetter < 'A' || rowLetter > 'D') {
            System.out.println("Invalid row letter. Please enter a letter between (A-D)).");
            return;
        }

        // Prompts the user to enter seat number
        System.out.print("Enter the seat number between (1-14): ");

        // Validate seat number input
        if (!scanner.hasNextInt()) {
            System.out.println("Invalid seat number please enter seat number between (1-14).");
            scanner.next();
            return;
        }

        // Reading the seat number
        int seatNumber = scanner.nextInt();

        // Validating seat number
        if (seatNumber < 1 || seatNumber > 14) {
            System.out.println("Invalid seat number. Please enter a number between (1-14)).");
            return;
        }

        // Calculate the seat index in the array
        int seatIndex = get_seat_index(rowLetter, seatNumber);

        // Checking seats are available
        if (!seats[seatIndex]) {
            System.out.println("Seat " + rowLetter + seatNumber + " is not reserved.");
        }
        else{
            // Find the ticket associated with the seat
            for (int i = 0; i< ticketSold; i++){
                if (tickets[i].getSeatRow()==rowLetter && tickets[i].getSeatNumber()==seatNumber){
                    tickets[i].printTicketDetails();
                    tickets[i].getPerson().printInformationOfPerson();
                    return;
                }
            }
        }
    }
}
