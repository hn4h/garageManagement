package model;

public class Driver extends Person{
    private String id;
    private String DOB;
    private String accommodation;
    private String drivingLicense;
    private String status;
    private double salary;

    public Driver() {
    }

    public Driver(String name, String phoneNumber, String id, String DOB, String accommodation, String drivingLicense, String status, double salary) {
        super(name, phoneNumber);
        this.id = id;
        this.DOB = DOB;
        this.accommodation = accommodation;
        this.drivingLicense = drivingLicense;
        this.status = status;
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getAccommodation() {
        return accommodation;
    }

    public void setAccommodation(String accommodation) {
        this.accommodation = accommodation;
    }

    public String getDrivingLicense() {
        return drivingLicense;
    }

    public void setDrivingLicense(String drivingLicense) {
        this.drivingLicense = drivingLicense;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public void displayDriver(){
        System.out.printf(" ");
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
