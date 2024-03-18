package model;

public class Driver extends Person{
    private String NumberId;
    private String DOB;
    private String accommodation;
    private String drivingLicense;
    private String status;
    private double salary;
    private int id;
    public Driver() {
    }

    public Driver(int id ,String name, String phoneNumber, String NumberId, String DOB, String accommodation, String drivingLicense, String status, double salary) {
        super(name, phoneNumber);
        this.id = id;
        this.NumberId = NumberId;
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

    public int getId() {
        return this.id;
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

    public String getNumberId() {
        return NumberId;
    }

    public void setNumberId(String numberId) {
        NumberId = numberId;
    }



    @Override
    public String toString() {
        return super.toString();
    }
}
