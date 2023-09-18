package com.gurudutt;

import java.util.*;



public class Main {
    public static void main(String[] args) {
        AirlineReservationSystem reservationSystem = new AirlineReservationSystem();

        Flight flight1 = new Flight("AA101", new StringBuilder("New York"), 150);
        Flight flight2 = new Flight("UA202", new StringBuilder("Los Angeles"), 200);
        Flight flight3 = new Flight("DL303", new StringBuilder("Chicago"), 180);

        reservationSystem.addFlight(flight1);
        reservationSystem.addFlight(flight2);
        reservationSystem.addFlight(flight3);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Display Flights");
            System.out.println("2. Book a Flight");
            System.out.println("3. Cancel a Booking");
            System.out.println("4. View Passenger List");
            System.out.println("5. Quit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    reservationSystem.displayFlights();
                    break;
                case 2:
                    System.out.print("Enter the flight number: ");
                    String bookFlightNumber = scanner.next();
                    System.out.print("Enter the number of seats to book: ");
                    int bookNumSeats = scanner.nextInt();
                    System.out.print("Enter passenger name: ");
                    String passengerName = scanner.next();
                    System.out.print("Enter passenger passport number: ");
                    String passportNumber = scanner.next();

                    Passenger passenger = new Passenger(passengerName, passportNumber);

                    if (reservationSystem.bookFlight(bookFlightNumber, bookNumSeats, passenger)) {
                        System.out.println("Booking successful!");
                    } else {
                        System.out.println("Booking failed. Not enough seats available.");
                    }
                    break;
                case 3:
                    System.out.print("Enter the flight number: ");
                    String cancelFlightNumber = scanner.next();
                    System.out.print("Enter the number of seats to cancel: ");
                    int cancelNumSeats = scanner.nextInt();
                    System.out.print("Enter passenger passport number: ");
                    String cancelPassportNumber = scanner.next();

                    Passenger cancelPassenger = new Passenger("", cancelPassportNumber);

                    if (reservationSystem.cancelFlight(cancelFlightNumber, cancelNumSeats, cancelPassenger)) {
                        System.out.println("Cancellation successful!");
                    } else {
                        System.out.println("Cancellation failed. Passenger not found or insufficient seats to cancel.");
                    }
                    break;
                case 4:
                    System.out.print("Enter the flight number: ");
                    String viewFlightNumber = scanner.next();
                    List<Passenger> passengerList = reservationSystem.getPassengerList(viewFlightNumber);

                    if (passengerList != null) {
                        System.out.println("Passenger List for Flight " + viewFlightNumber);
                        for (Passenger p : passengerList) {
                            System.out.println(p);
                        }
                    } else {
                        System.out.println("Flight not found.");
                    }
                    break;
                case 5:
                    System.out.println("Thank you for using the Airline Reservation System.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
