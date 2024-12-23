import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class PlaneManagement {

    public static int[] A = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
    public static int[] B = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
    public static int[] C = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
    public static int[] D = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

    // Array to store ticket objects
    private static Ticket[] tickets = new Ticket[100];

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println(
                "\n\n\n                                                           ****** Welcome to the Plane Management application *****");
        int choice = -1;

        do {
            DISPLAY_MENU();
            System.out.println(
                    "\n--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

            System.out.print("\nEnter the option you want to Enter: ");
            if (input.hasNextInt()) {
                choice = input.nextInt();
            } else {
                System.out.println("Invalid input. Please enter a valid integer.");
                input.next(); // Consume invalid input to avoid an infinite loop
                continue; // Restart the loop to prompt the user again
            }

            switch (choice) {
                case 1:
                    BUY_A_SEAT();
                    break;

                case 2:
                    CANCEL_A_SEAT();
                    break;

                case 3:
                    FIND_FIRST_AVAILABLE_SEAT();
                    break;

                case 4:
                    SHOW_SEATING_PLAN();
                    break;

                case 5:
                    PRINT_TICKET_INFORMATION_AND_TOTAL_SALES();
                    break;

                case 6:
                    SEARCH_TICKET();
                    break;

                case 0:
                    input.close();
                    break;

                default:
                    System.out.println("Menu not identified. Enter a valid option");
                    break;
            }

        } while (choice != 0);
        System.out.println("Successfully exit the program");
        input.close();

    }

    // Method to display the main menu
    private static void DISPLAY_MENU() {
        System.out.println(
                "\n\n\n\n                                                                 *********************************************");
        System.out.println(
                "\n                                                                 *                 MENU OPTIONS              *");
        System.out.println(
                "\n                                                                 *********************************************");
        System.out.println(
                "\n                                                                                1) Buy a seat");
        System.out.println(
                "\n                                                                                2) Cancel a seat");
        System.out.println(
                "\n                                                                                3) Find first available seat");
        System.out.println(
                "\n                                                                                4) Show seating plan");
        System.out.println(
                "\n                                                                                5) print_tickets_info");
        System.out.println(
                "\n                                                                                6) Search ticket");
        System.out
                .println("\n                                                                                0) Quit");

    }

    private static void BUY_A_SEAT() {
        boolean isBought = true;

        while (isBought) {
            try {
                isBought = false;
                System.out.print("\nEnter row Letter: ");
                String rowLetter = (new Scanner(System.in)).nextLine();

                // Validate row letter
                if (rowLetter.length() != 1 || !Character.isUpperCase(rowLetter.charAt(0))) {
                    System.out.println("Invalid row letter. Please enter a valid letter (A, B, C, or D).");
                    continue;
                }

                rowLetter = rowLetter.toUpperCase(); // Convert to uppercase for consistency

                // Choose seat based on row letter
                if (rowLetter.equals("A")) {
                    System.out.println("Seats (1-14)");
                    isBought = seat_booking(A, 1);
                } else if (rowLetter.equals("B")) {
                    System.out.println("Seats (1-12)");
                    isBought = seat_booking(B, 2);
                } else if (rowLetter.equals("C")) {
                    System.out.println("Seats (1-12)");
                    isBought = seat_booking(C, 3);
                } else if (rowLetter.equals("D")) {
                    System.out.println("Seats (1-14)");
                    isBought = seat_booking(D, 4);
                } else {
                    System.out.println("Invalid row letter. Please enter a valid row letter (A, B, C, or D).");
                    isBought = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid row letter (A, B, C, or D).");
                isBought = true;
            }
        }
    }

    private static boolean seat_booking(int[] row, int row_number) {
        // Loop until a valid seat is selected
        while (true) {
            try {
                System.out.print("Enter seat number: ");
                int seat_number = (new Scanner(System.in)).nextInt();

                // Validate seat number based on the row
                if ((row_number == 1 || row_number == 4) && (seat_number < 1 || seat_number > 14)) {
                    System.out.println("Invalid seat number. Seat number should be between 1 and 14 for rows A and D.");
                    continue;
                } else if ((row_number == 2 || row_number == 3) && (seat_number < 1 || seat_number > 12)) {
                    System.out.println("Invalid seat number. Seat number should be between 1 and 12 for rows B and C.");
                    continue;
                }

                if (0 < seat_number && seat_number <= row.length) {
                    if (row[seat_number - 1] != 0) {
                        System.out.println("This seat is already booked!");
                        return true;
                    }
                    // Prompt user for personal information
                    System.out.print("\nEnter your first name: ");
                    String name = (new Scanner(System.in)).next();
                    System.out.print("Enter your surname: ");
                    String surname = (new Scanner(System.in)).next();

                    // Prompt for a valid email address
                    String email;
                    do {
                        System.out.print("Enter your email: ");
                        email = (new Scanner(System.in)).next();
                        if (!Person.isValidEmail(email)) {
                            System.out.println("Invalid email format. Please enter a valid email address.");
                        }
                    } while (!Person.isValidEmail(email)); // Repeat until a valid email is entered

                    Person person = new Person(name, surname, email);
                    double price;

                    // Determine the price based on the seat number
                    if (seat_number >= 1 && seat_number <= 5) {
                        price = 200;
                    } else if (seat_number >= 6 && seat_number <= 9) {
                        price = 150;
                    } else if (seat_number >= 10 && seat_number <= 14) {
                        price = 180;
                    } else {
                        System.out.println("Invalid seat number. Please enter a valid seat number between 1 and 14.");
                        continue;
                    }

                    // Created Ticket object with the booking information
                    Ticket ticket = new Ticket(row_number, seat_number, price, person);
                    ticket.save(); // Save ticket information to a separate file

                    // Find the first available index in the tickets array and add the ticket
                    int index = 0;
                    for (int i = 0; i < tickets.length; i++) {
                        if (tickets[i] == null) {
                            index = i;
                            break;
                        }
                    }
                    tickets[index] = ticket;

                    // Mark the seat as booked in the row array
                    row[seat_number - 1] = 1;
                    System.out.println("Your seat has been successfully booked!");
                    System.out.println("\nThe price for this seat is: " + price);
                    return false;
                }
                System.out.println("This seat number does not exist");
            } catch (InputMismatchException var10) {
                System.out.println("Invalid Input. Seat number should be an integer");
            }
        }
    }


    private static void CANCEL_A_SEAT() {
        while (true) {     // Infinite loop until a valid cancellation is made
            Scanner input = new Scanner(System.in);
            try {
                System.out.print("Enter row Letter: ");
                String rowNumber = input.nextLine().toUpperCase();
                int seatNumber;
                int rowIndex;
                if (rowNumber.equalsIgnoreCase("A")) {
                    System.out.print("Enter seat number: ");
                    seatNumber = input.nextInt();
                    if (0 < seatNumber && seatNumber <= A.length) {  // Validate seat number for row A
                        rowIndex = 0;   // Set rowIndex for row A
                    } else {
                        System.out.println("Invalid seat number");
                        continue;  // Restart the loop if seat number is invalid
                    }
                } else if (rowNumber.equalsIgnoreCase("B")) { // Validate seat number for row B
                    System.out.print("Enter seat number: ");
                    seatNumber = input.nextInt();
                    if (0 < seatNumber && seatNumber <= B.length) {
                        rowIndex = 1;   // Set rowIndex for row B
                    } else {
                        System.out.println("Invalid seat number");
                        continue;
                    }
                } else if (rowNumber.equalsIgnoreCase("C")) {  // Validate seat number for row C
                    System.out.print("Enter seat number: ");
                    seatNumber = input.nextInt();
                    if (0 < seatNumber && seatNumber <= C.length) {
                        rowIndex = 2;   // Set rowIndex for row C
                    } else {
                        System.out.println("Invalid seat number");
                        continue;
                    }
                } else if (rowNumber.equalsIgnoreCase("D")) {  // Validate seat number for row D
                    System.out.print("Enter seat number: ");
                    seatNumber = input.nextInt();   // Read seat number
                    if (0 < seatNumber && seatNumber <= D.length) {
                        rowIndex = 3;   // Set rowIndex for row D
                    } else {
                        System.out.println("Invalid seat number");
                        continue;
                    }
                } else {
                    System.out.println("Invalid row letter");
                    continue;
                }

                // Find the index of the ticket to cancel
                int index = findTicketIndex(seatNumber, rowIndex + 1);
                if (index != -1) {     // Check if ticket is found
                    tickets[index] = null;  // Cancel the ticket
                    switch (rowIndex) {
                        case 0:
                            A[seatNumber - 1] = 0;  // Mark the seat as available in row A
                            break;
                        case 1:
                            B[seatNumber - 1] = 0;  // Mark the seat as available in row B
                            break;
                        case 2:
                            C[seatNumber - 1] = 0;  // Mark the seat as available in row C
                            break;
                        case 3:
                            D[seatNumber - 1] = 0;  // Mark the seat as available in row D
                            break;
                    }
                    System.out.println("Your ticket has been successfully cancelled!");
                    break;  // Exit the loop after successful cancellation
                }
                System.out.println("Invalid seat number");
            } catch (Exception var5) {
                System.out.println("Invalid input");
                input.nextLine();   // Consume invalid input
            }
        }
    }

    private static int findTicketIndex(int seatNumber, int rowNumber) {
        for (int i = 0; i < tickets.length; i++) {  // Iterate over the tickets array
            if (tickets[i] != null && tickets[i].row == rowNumber && tickets[i].seat == seatNumber) {
                // Check if the current ticket exists and matches the given seat and row numbers
                return i;   // Return the index of the matching ticket
            }
        }
        return -1;  // Return -1 if no matching ticket is found
    }

    private static int FIND_FIRST_AVAILABLE_SEAT() {
        int[][] rows = new int[][] { PlaneManagement.A, PlaneManagement.B, PlaneManagement.C, PlaneManagement.D };

        for (int i = 0; i < rows.length; ++i) {
            for (int j = 0; j < rows[i].length; ++j) {
                if (rows[i][j] == 0) {   // Check if the seat is available (0 represents an empty seat)
                    char rowLetter;
                    switch (i) {   // Determine the row letter based on the row index
                        case 0:
                            rowLetter = 'A';
                            break;
                        case 1:
                            rowLetter = 'B';
                            break;
                        case 2:
                            rowLetter = 'C';
                            break;
                        case 3:
                            rowLetter = 'D';
                            break;
                        default:
                            rowLetter = ' '; // Default to a space if index is out of range (for safety)
                    }
                    System.out.println("First available seat: Row " + rowLetter + ", seat " + (j + 1));
                    return (i + 1) * 10 + (j + 1); // return the seat number as an integer for demonstration purposes
                }
            }
        }
        System.out.println("No available seats found.");
        return -1; // Return -1 to indicate that no available seats were found
    }

    private static void SHOW_SEATING_PLAN() {

        System.out.println("\n    1  2  3  4  5  6  7  8  9  10 11 12 13 14");
        // Print the seat numbering for each row

        System.out.print("\nA  ");
        printRowSeats(A);   // Print seating status for row A
        System.out.print("\nB  ");
        printRowSeats(B);   // Print seating status for row B
        System.out.print("\nC  ");
        printRowSeats(C);   // Print seating status for row C
        System.out.print("\nD  ");
        printRowSeats(D);   // Print seating status for row D
        System.out.println();
    }

    private static void printRowSeats(int[] row) {
        for (int i = 0; i < row.length; ++i) {
            if (row[i] == 0) {
                System.out.print(" O ");
            } else {
                System.out.print(" X ");
            }
        }
    }

    private static void PRINT_TICKET_INFORMATION_AND_TOTAL_SALES() {
        double totalPrice = 0;

        // Iterate through each ticket
        for (Ticket ticket : tickets) {
            if (ticket != null) {
                System.out.println("Row: " + convertToRowString(ticket.row) + ", Seat: " + ticket.seat + ", Price: "
                        + ticket.price);
                totalPrice += ticket.price;
            }
        }

        System.out.println("Total price of the tickets sold during the session: £" + totalPrice);
    }

    // Convert row number to corresponding letter (1 -> A, 2 -> B, 3 -> C, 4 -> D)
    private static String convertToRowString(int row) {
        switch (row) {
            case 1:
                return "A";
            case 2:
                return "B";
            case 3:
                return "C";
            case 4:
                return "D";
            default:
                return ""; // Return an empty string for other cases
        }
    }


    private static void SEARCH_TICKET() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter row letter: ");
        String rowLetter = scanner.nextLine().toUpperCase(); // Convert input to uppercase for consistency

        // Validate if the row letter is valid (A, B, C, D)
        if (!rowLetter.matches("[A-D]")) {
            System.out.println("Invalid row letter. Please enter a row letter from A to D.");
            return;
        }

        System.out.print("Enter seat number: ");
        int seatNumber;
        try {
            seatNumber = scanner.nextInt(); // Read the seat number input
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Seat number should be an integer.");
            return;
        }

        int rowIndex = rowLetter.charAt(0) - 'A'; // Calculate row index (0 for A, 1 for B, etc.)

        // Check if the seat is within the valid range
        if (seatNumber < 1 || seatNumber > PlaneManagement.A.length) {
            System.out.println(
                    "Invalid seat number. Seat number should be between 1 and " + PlaneManagement.A.length + ".");
            return;
        }

        // Check if the seat is booked
        if (isSeatBooked(rowIndex, seatNumber)) {
            System.out.println("This seat is unavailable. It has been previously booked.");
        } else {
            System.out.println("This seat is available.");
        }
    }

    // Method to check if the seat is booked
    private static boolean isSeatBooked(int rowIndex, int seatNumber) {
        for (Ticket ticket : PlaneManagement.tickets) {
            if (ticket != null && ticket.row == rowIndex + 1 && ticket.seat == seatNumber) {
                return true; // Seat is booked
            }
        }
        return false; // Seat is available
    }
}