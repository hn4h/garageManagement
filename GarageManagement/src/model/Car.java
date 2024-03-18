package model;

public class Car {
    private String numberPlates;
    private String type;
    private String maintenanceSchedule;
    private String companyCar;
    private int year;
    private String status;
    private int id;
    public Car() {
    }

    public Car(int id, String numberPlates, String type, String maintenanceSchedule, String companyCar, int year, String status) {
        this.id = id;
        this.numberPlates = numberPlates;
        this.type = type;
        this.maintenanceSchedule = maintenanceSchedule;
        this.companyCar = companyCar;
        this.year = year;
        this.status = status;
    }

    public String getNumberPlates() {
        return numberPlates;
    }

    public void setNumberPlates(String numberPlates) {
        this.numberPlates = numberPlates;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMaintenanceSchedule() {
        return maintenanceSchedule;
    }

    public void setMaintenanceSchedule(String maintenanceSchedule) {
        this.maintenanceSchedule = maintenanceSchedule;
    }

    public String getCompanyCar() {
        return companyCar;
    }

    public void setCompanyCar(String companyCar) {
        this.companyCar = companyCar;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
