import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Ticket {
    public static Ticket[] soldTickets;
    int row;
    int seat;
    double price;
    Person person;

    public Ticket(int row, int seat, double price, Person person) {
        this.row = row;
        this.seat = seat;
        this.price = price;
        this.person = person;
    }

    // Getter for row
    public int getRow() {
        return row;
    }

    // Setter for row
    public void setRow(int row) {
        this.row = row;
    }

    // Getter for seat
    public int getSeat() {
        return seat;
    }

    // Setter for seat
    public void setSeat(int seat) {
        this.seat = seat;
    }

    // Getter for price
    public double getPrice() {
        return price;
    }

    // Setter for price
    public void setPrice(double price) {
        this.price = price;
    }

    // Getter for person
    public Person getPerson() {
        return person;
    }

    // Setter for person
    public void setPerson(Person person) {
        this.person = person;
    }

    // Getter for name
    public String getName() {
        return person.getName();
    }

    // Setter for name
    public void setName(String name) {
        person.setName(name);
    }

    // Getter for surname
    public String getSurname() {
        return person.getSurname();
    }

    // Setter for surname
    public void setSurname(String surname) {
        person.setSurname(surname);
    }

    // Getter for email
    public String getEmail() {
        return person.getEmail();
    }

    public void save() {
        String fileName = convertToRowString(row) + seat + ".txt"; // Construct file name
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            writer.println("Name: " + person.name);
            writer.println("Surname: " + person.surname);
            writer.println("Email: " + person.email);
            writer.println("Row " + convertToRowString(row) + " Seat " + seat); // Updated format
            writer.println("Price: £" + price);
        } catch (IOException e) {
            System.out.println("Error saving ticket: " + e.getMessage());
        }
    }

    // Convert row number to corresponding letter (1 -> A, 2 -> B, 3 -> C, 4 -> D)
    private String convertToRowString(int row) {
        return String.valueOf((char) (row + 'A' - 1));
    }
}