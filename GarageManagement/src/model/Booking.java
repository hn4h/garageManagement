package model;

public class Booking {
    private int IDbooking;
    private String date;
    private String start;
    private String destination;
    private int distance;
    private Customer customer;
    private Driver driver;
    private Car car;
    private String isDeposit;
    private String status;

    public Booking() {
    }

    public Booking(int IDbooking, String date, String start, String destination, int distance, Customer customer, Driver driver, Car car, String isDeposit, String status) {
        this.IDbooking = IDbooking;
        this.date = date;
        this.start = start;
        this.destination = destination;
        this.distance = distance;
        this.customer = customer;
        this.driver = driver;
        this.car = car;
        this.isDeposit = isDeposit;
        this.status = status;
    }

    public int getIDbooking() {
        return IDbooking;
    }

    public void setIDbooking(int IDbooking) {
        this.IDbooking = IDbooking;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIsDeposit() {
        return isDeposit;
    }

    public void setIsDeposit(String isDeposit) {
        this.isDeposit = isDeposit;
    }
}
