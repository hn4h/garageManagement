package controller;

import model.Booking;
import model.Car;
import model.Customer;
import model.Driver;
import view.Screen;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.stream.Collectors;

public class GarageManagement implements ActionListener {
    public ListOfCustomers lcus;
    public ListOfBookings lBookings;
    public ListOfCars lcars;
    public ListOfDrivers ldrivers;
    private Screen screen;
    public GarageManagement(Screen screen){
        lcus = new ListOfCustomers();
        lBookings = new ListOfBookings();
        lcars = new ListOfCars();
        ldrivers = new ListOfDrivers();
        this.screen = screen;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cm = e.getActionCommand();
        if (cm.equals("Add Booking")){
            this.addBooking();
        } else if (cm.equals("Update Booking")){
            this.updateBooking();
        } else if (cm.equals("Remove Booking")){
            this.removeBooking();
        } else if (cm.equals("Search Booking")){
            this.searchBooking();
        } else if (cm.equals("Show Booking List")) {
            this.showBookingList();
        } else if (cm.equals("Update Car")){
            this.updateCar();
        } else if (cm.equals("Remove Car")){
            this.removeCar();
        } else if (cm.equals("Search Car")){
            this.searchCar();
        } else if (cm.equals("Show Car List")) {
            this.showCarList();
        } else if(cm.equals("Add Car")) {
            this.addCar();
        } else if (cm.equals("Update Driver")){
            this.updateDriver();
        } else if (cm.equals("Remove Driver")){
            this.removeDriver();
        } else if (cm.equals("Search Driver")){
            this.searchDriver();
        } else if (cm.equals("Show Driver List")) {
            this.showDriverList();
        } else if(cm.equals("Add Driver")) {
            this.addDriver();
        } else if (cm.equals("Update Customer")){
            this.updateCustomer();
        } else if (cm.equals("Remove Customer")){
            this.removeCustomer();
        } else if (cm.equals("Search Customer")){
            this.searchCustomer();
        } else if (cm.equals("Show Customer List")) {
            this.showCustomerList();
        } else if(cm.equals("Add Customer")) {
            this.addCustomer();
        }
    }

    public void addBooking(){
        String date = JOptionPane.showInputDialog(null, "Enter date of tour:");
        String start = JOptionPane.showInputDialog(null, "Enter start of tour:");
        String destination = JOptionPane.showInputDialog(null, "Enter destination of tour:");
        int distance = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter distance of tour:"));
        String idOfCustomer = JOptionPane.showInputDialog(null, "Enter id of customer:");
        String idOfDriver = JOptionPane.showInputDialog(null, "Enter id of driver:");
        String numplateOfCar = JOptionPane.showInputDialog(null, "Enter number plate of car:");
        String isDeposit = JOptionPane.showInputDialog(null, "Enter deposit:");
        String status = JOptionPane.showInputDialog(null, "Enter status of Tour:");
        Customer addedCus = lcus.getList().stream().filter(cus -> cus.getId() == Integer.parseInt(idOfCustomer)).collect(Collectors.toList()).get(0);
        Driver addedDriver = ldrivers.getList().stream().filter(driver -> driver.getId().equals(idOfDriver)).collect(Collectors.toList()).get(0);
        Car addedCar = lcars.getList().stream().filter(car -> car.getNumberPlates().equals(numplateOfCar)).collect(Collectors.toList()).get(0);
        lBookings.addItem(new Booking(lBookings.getNextID(),date,start,destination,
                distance,addedCus,addedDriver,addedCar,isDeposit,status));
        screen.showListBookings();
    }
    public void updateBooking(){
        lBookings.list.clear();
        for (int i = 0; i < screen.table.getRowCount(); i++) {
            String[] row = new String[screen.table.getColumnCount()];
            for (int j = 0; j < screen.table.getColumnCount(); j++) {
                row[j] = String.valueOf(screen.table.getValueAt(i, j));
            }
            Customer addedCus = lcus.getList().stream().filter(cus -> cus.getId() == Integer.parseInt(row[5])).collect(Collectors.toList()).get(0);
            Driver addedDriver = ldrivers.getList().stream().filter(driver -> driver.getId().equals(row[6])).collect(Collectors.toList()).get(0);
            Car addedCar = lcars.getList().stream().filter(car -> car.getNumberPlates().equals(row[7])).collect(Collectors.toList()).get(0);
            lBookings.list.add(new Booking(Integer.parseInt(row[0]), row[1], row[2],row[3], Integer.parseInt(row[4]),
                    addedCus,addedDriver, addedCar, row[8], row[9]));
        }
        lBookings.rewriteData();
        screen.showListBookings();
    }
    public void removeBooking(){
        int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter ID of removed booking: "));
        Booking booking = lBookings.getList().stream().filter(b -> b.getIDbooking() == id).collect(Collectors.toList()).get(0);
        lBookings.removeItem(booking);
        screen.showListBookings();
    }

    public void searchBooking(){
        int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter ID of searched booking: "));
        DefaultTableModel modelE = (DefaultTableModel) screen.table.getModel();
        Booking booking = lBookings.getList().stream().filter(b -> b.getIDbooking() == id).collect(Collectors.toList()).get(0);
        int h = modelE.getRowCount();
        for(int j = 0;j < h ;j++) {
            modelE.removeRow(0);
        }
        for (Booking i : lBookings.list){
            if(i.getIDbooking() == booking.getIDbooking()){
                modelE.addRow(new Object[]{i.getIDbooking(), i.getDate(), i.getStart(),
                        i.getDestination(), i.getDistance(), i.getCustomer().getId(), i.getDriver().getId(),
                        i.getCar().getNumberPlates(), i.getIsDeposit(), i.getStatus()});
                break;
            }
        }
    }
    public void showBookingList(){
        screen.showListBookings();
    }
    public void addCar(){
        String numPlate = JOptionPane.showInputDialog(null,"Enter number plate of Car:");
        String type = JOptionPane.showInputDialog(null,"Enter type of Car:");
        String maintenanceSchedule = JOptionPane.showInputDialog(null,"Enter maintenance schedule of Car:");
        String carMaker = JOptionPane.showInputDialog(null,"Enter carmaker of Car:");
        int year = Integer.parseInt(JOptionPane.showInputDialog(null,"Enter year manufacture of Car:"));
        String status = JOptionPane.showInputDialog(null,"Enter status of Car:");
        lcars.addItem(new Car(numPlate, type, maintenanceSchedule, carMaker, year, status));
        screen.showListCars();
    }
    public void updateCar(){
        lcars.list.clear();
        for (int i = 0; i < screen.table.getRowCount(); i++) {
            String[] row = new String[screen.table.getColumnCount()];
            for (int j = 0; j < screen.table.getColumnCount(); j++) {
                row[j] = String.valueOf(screen.table.getValueAt(i, j));
            }
            lcars.list.add(new Car(row[1], row[2], row[3], row[4], Integer.parseInt(row[5]), row[6]));
        }
        lcars.rewriteData();
        screen.showListCars();
    }
    public void removeCar(){
        String id = JOptionPane.showInputDialog(null, "Enter number plate of removed Car: ");
        Car car = lcars.getList().stream().filter(b -> b.getNumberPlates().equals(id)).collect(Collectors.toList()).get(0);
        lcars.removeItem(car);
        screen.showListCars();
    }
    public void searchCar(){
        String id = JOptionPane.showInputDialog(null, "Enter number plate of searched Car: ");
        Car car = lcars.getList().stream().filter(b -> b.getNumberPlates().equals(id)).collect(Collectors.toList()).get(0);
        DefaultTableModel modelE = (DefaultTableModel) screen.table.getModel();
        int h = modelE.getRowCount();
        for(int j = 0;j < h ;j++) {
            modelE.removeRow(0);
        }
        for(int i = 0 ; i < lcars.getList().size();i++) {
            if (lcars.getList().get(i).getNumberPlates().equals(car.getNumberPlates())) {
                modelE.addRow(new Object[]{(i + 1), lcars.getList().get(i).getNumberPlates(),
                        lcars.getList().get(i).getType(), lcars.getList().get(i).getMaintenanceSchedule()
                        , lcars.getList().get(i).getCompanyCar(), lcars.getList().get(i).getYear(),
                        lcars.getList().get(i).getStatus()});
            }
        }
    }
    public void showCarList(){
        screen.showListCars();
    }
    public void addDriver(){
        String name = JOptionPane.showInputDialog(null,"Enter Name of Driver:");
        String phoneNum = JOptionPane.showInputDialog(null,"Enter Phone Number of Driver:");
        String id = JOptionPane.showInputDialog(null,"Enter ID of Driver:");
        String DOB = JOptionPane.showInputDialog(null,"Enter Date Of Birth of Driver:");
        String accommodation = JOptionPane.showInputDialog(null,"Enter Accommodation of Driver:");
        String license = JOptionPane.showInputDialog(null,"Enter Driving license of Driver:");
        String status = JOptionPane.showInputDialog(null,"Enter Status of Driver:");
        ldrivers.addItem(new Driver(name,phoneNum,id,DOB, accommodation, license, status,0));
        screen.showListDrivers();
    }
    public void updateDriver(){
        ldrivers.list.clear();
        for (int i = 0; i < screen.table.getRowCount(); i++) {
            String[] row = new String[screen.table.getColumnCount()];
            for (int j = 0; j < screen.table.getColumnCount(); j++) {
                row[j] = String.valueOf(screen.table.getValueAt(i, j));
            }
            ldrivers.list.add(new Driver(row[1], row[2], row[3], row[4], row[5], row[6],
                    row[7], Double.parseDouble(row[8]) ));
        }
        ldrivers.rewriteData();
        screen.showListDrivers();
    }
    public void removeDriver(){
        String id = JOptionPane.showInputDialog(null,"Enter ID of removed Driver:");
        Driver driver = ldrivers.getList().stream().filter(d -> d.getId().equals(id)).collect(Collectors.toList()).get(0);
        ldrivers.removeItem(driver);
        screen.showListDrivers();
    }
    public void searchDriver(){
        String id = JOptionPane.showInputDialog(null,"Enter ID of searched Driver:");
        Driver driver = ldrivers.getList().stream().filter(d -> d.getId().equals(id)).collect(Collectors.toList()).get(0);
        DefaultTableModel modelE = (DefaultTableModel) screen.table.getModel();
        int h = modelE.getRowCount();
        for(int j = 0;j < h ;j++) {
           modelE.removeRow(0);
        }
        modelE.addRow(new Object[]{1, driver.getName(), driver.getPhoneNumber(), driver.getId(), driver.getDOB(),
                   driver.getAccommodation(), driver.getDrivingLicense(),
                    driver.getStatus(), driver.getSalary()});
        }



    public void showDriverList(){
        screen.showListDrivers();
    }
    public void addCustomer(){
        String name = JOptionPane.showInputDialog(null, "Enter Name of Customer:");
        String phoneNumber = JOptionPane.showInputDialog(null, "Enter Phone Number of Customer:");
        lcus.addItem(new Customer(lcus.getNextID(), name, phoneNumber));
        screen.showListCustomers();
    }
    public void updateCustomer(){
        lcus.list.clear();
        for (int i = 0; i < screen.table.getRowCount(); i++) {
            String[] row = new String[screen.table.getColumnCount()];
            for (int j = 0; j < screen.table.getColumnCount(); j++) {
                row[j] = String.valueOf(screen.table.getValueAt(i, j));
            }
            lcus.list.add(new Customer(Integer.parseInt(row[0]), row[1], row[2]));
        }
        lcus.rewriteData();
        screen.showListCustomers();
    }
    public void removeCustomer(){
        int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter ID of removed Customer: "));
        Customer cus = lcus.getList().stream().filter(c -> c.getId() == id).collect(Collectors.toList()).get(0);
        lcus.removeItem(cus);
        screen.showListCustomers();
    }
    public void searchCustomer(){
        int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter ID of searched Customer: "));
        Customer cus = lcus.getList().stream().filter(c -> c.getId() == id).collect(Collectors.toList()).get(0);
        DefaultTableModel modelE = (DefaultTableModel) screen.table.getModel();
        int h = modelE.getRowCount();
        for(int j = 0;j < h ;j++) {
            modelE.removeRow(0);
        }
        modelE.addRow(new Object[]{1, cus.getName(), cus.getPhoneNumber()});
    }
    public void showCustomerList(){
        screen.showListCustomers();
    }
}
