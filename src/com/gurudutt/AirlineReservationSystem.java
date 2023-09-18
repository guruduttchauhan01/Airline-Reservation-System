package com.gurudutt;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class Flight {
    private String flightNumber;
    private StringBuilder destination;
    private int capacity;
    private int bookedSeats;
    private List<Passenger> passengers;
    private int destinationStringLength = 30;

    public Flight(String flightNumber, StringBuilder destination, int capacity) {
        this.flightNumber = flightNumber;
        this.destination = destination;
        this.capacity = capacity;
        this.bookedSeats = 0;
        this.passengers = new ArrayList<>();
    }

    // Getter and setter methods

    public boolean book(int numSeats, Passenger passenger) {
        if (bookedSeats + numSeats <= capacity) {
            bookedSeats += numSeats;
            passengers.add(passenger);
            return true;
        }
        return false;
    }

    public boolean cancel(int numSeats, Passenger passenger) {
        if (passengers.contains(passenger) && numSeats <= passengers.size()) {
            bookedSeats -= numSeats;
            passengers.remove(passenger);
            return true;
        }
        return false;
    }
    private StringBuilder formattedDestination() {
        int currentLength = destination.length();

        if (currentLength < destinationStringLength) {
            // Append spaces to the destination string to reach the desired length
            destination.append(" ".repeat(destinationStringLength - currentLength));
        } else if (currentLength > destinationStringLength) {
            // Shrink the destination string to the desired length and format it
            destination.setLength(destinationStringLength);
        }

        return destination;
    }

    @Override
    public String toString() {
        return "Flight Number : " + flightNumber + String.format("%-5s", ",") + " to " + formattedDestination() + "\tCapacity: " + capacity + ",\t Booked: " + bookedSeats;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }
}

class Passenger {
    private String name;
    private String passportNumber;

    public Passenger(String name, String passportNumber) {
        this.name = name;
        this.passportNumber = passportNumber;
    }

    // Getter methods

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Passenger passenger = (Passenger) obj;
        return Objects.equals(passportNumber, passenger.passportNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(passportNumber);
    }

    @Override
    public String toString() {
        return name + " (Passport: " + passportNumber + ")";
    }
}

class AirlineReservationSystem {
    private List<Flight> flights;

    public AirlineReservationSystem() {
        flights = new ArrayList<>();
    }

    public void addFlight(Flight flight) {
        flights.add(flight);
    }

    public void displayFlights() {
        for (Flight flight : flights) {
            System.out.println(flight);
        }
    }

    public boolean bookFlight(String flightNumber, int numSeats, Passenger passenger) {
        for (Flight flight : flights) {
            if (flight.getFlightNumber().equals(flightNumber)) {
                return flight.book(numSeats, passenger);
            }
        }
        return false;
    }

    public boolean cancelFlight(String flightNumber, int numSeats, Passenger passenger) {
        for (Flight flight : flights) {
            if (flight.getFlightNumber().equals(flightNumber)) {
                return flight.cancel(numSeats, passenger);
            }
        }
        return false;
    }

    public List<Passenger> getPassengerList(String flightNumber) {
        for (Flight flight : flights) {
            if (flight.getFlightNumber().equals(flightNumber)) {
                return flight.getPassengers();
            }
        }
        return null;
    }
}
