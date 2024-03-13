package controller;

import com.sun.org.apache.xerces.internal.impl.xs.models.XSCMValidator;
import com.sun.xml.internal.ws.developer.MemberSubmissionAddressing;
import model.Booking;
import model.Car;
import model.Customer;
import model.Driver;
import view.Handle;
import view.Screen;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GarageManagement implements ActionListener {
    public ListOfCustomers lcus;
    public ListOfBookings lBookings;
    public ListOfCars lcars;
    public ListOfDrivers ldrivers;
    public Screen screen;

    public Handle handle;
    public GarageManagement(Screen screen){
        lcus = new ListOfCustomers();
        lBookings = new ListOfBookings();
        lcars = new ListOfCars();
        ldrivers = new ListOfDrivers();
        this.screen = screen;
        handle = new Handle(screen);
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
        String date = handle.handleDate("Enter date of Booking");
        String start = handle.handlePlace("Enter start of tour:");
        String destination = handle.handlePlace("Enter destination of tour:");
        int distance = handle.handleDistance("Enter distance of tour:");
        String idOfCustomer = JOptionPane.showInputDialog(null, "Enter id of customer:");
        String idOfDriver = JOptionPane.showInputDialog(null, "Enter id of driver:");
        String numplateOfCar = JOptionPane.showInputDialog(null, "Enter number plate of car:");

        Customer addedCus = lcus.getList().stream().filter(cus -> cus.getId() == Integer.parseInt(idOfCustomer)).collect(Collectors.toList()).get(0);
        Driver addedDriver = ldrivers.getList().stream().filter(driver -> driver.getId().equals(idOfDriver)).collect(Collectors.toList()).get(0);
        Car addedCar = lcars.getList().stream().filter(car -> car.getNumberPlates().equals(numplateOfCar)).collect(Collectors.toList()).get(0);
        lBookings.addItem(new Booking(lBookings.getNextID(),date,start,destination,
                distance,addedCus,addedDriver,addedCar,"No","Not Started"));
        screen.showListBookings();
    }
    public void updateBooking(){
        ArrayList<Booking> temp = new ArrayList<>(lBookings.getList());
        lBookings.list.clear();
        for (int i = 0; i < screen.table.getRowCount(); i++) {
            String[] row = new String[screen.table.getColumnCount()];
            for (int j = 0; j < screen.table.getColumnCount(); j++) {
                row[j] = String.valueOf(screen.table.getValueAt(i, j));
            }
            try {
                Integer.parseInt(row[0]);
                Customer addedCus = lcus.getList().stream().filter(cus -> cus.getId() == Integer.parseInt(row[5])).collect(Collectors.toList()).get(0);
                Driver addedDriver = ldrivers.getList().stream().filter(driver -> driver.getId().equals(row[6])).collect(Collectors.toList()).get(0);
                Car addedCar = lcars.getList().stream().filter(car -> car.getNumberPlates().equals(row[7])).collect(Collectors.toList()).get(0);
                if(Integer.parseInt(row[0]) == temp.get(i).getIDbooking() && handle.checkDate(row[1]) && handle.checkPlace(row[2]) &&
                handle.checkPlace(row[3]) && handle.checkDistance(Integer.parseInt(row[4]))){
                    lBookings.list.add(new Booking(Integer.parseInt(row[0]), row[1], row[2],row[3], Integer.parseInt(row[4]),
                            addedCus,addedDriver, addedCar, row[8], row[9]));
                }else {
                    screen.alert();
                    lBookings.list.clear();
                    lBookings.readData();
                    screen.showListBookings();
                    return;
                }
            } catch (IndexOutOfBoundsException e){
                screen.alert1();
                lBookings.list.clear();
                lBookings.readData();
                screen.showListBookings();
                return;
            } catch (NumberFormatException e){
                screen.alert();
                lBookings.list.clear();
                lBookings.readData();
                screen.showListBookings();
                return;
            }

        }
        this.getSalary();
        lBookings.rewriteData();
        screen.showListBookings();
    }
    public void removeBooking(){
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter ID of removed booking: "));
            Booking booking = lBookings.getList().stream().filter(b -> b.getIDbooking() == id).collect(Collectors.toList()).get(0);
            lBookings.removeItem(booking);
            screen.showListBookings();
        } catch (NoSuchElementException e){
            screen.alert1();
        } catch (NumberFormatException e){
            screen.alert();
        } catch (IndexOutOfBoundsException e){
            screen.alert1();
        }
    }

    public void searchBooking(){
        try {
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
        } catch (NoSuchElementException e){
            screen.alert1();
        } catch (NumberFormatException e){
            screen.alert();
        } catch (IndexOutOfBoundsException e){
            screen.alert1();
        }
    }
    public void showBookingList(){
        screen.showListBookings();
    }
    public void addCar(){
        String numPlate = handle.handleNumberPlate("Enter number plate of Car:");
        String type = handle.handleType("Enter type of Car:");
        String maintenanceSchedule = handle.handleDate("Enter maintenance schedule of Car:");
        String carMaker = handle.handleCompanyCar("Enter CarMaker of Car:");
        int year = Integer.parseInt(handle.handleYearOfManufacture("Enter year manufacture of Car:"));
        lcars.addItem(new Car(numPlate, type, maintenanceSchedule, carMaker, year, "Available"));
        screen.showListCars();
    }
    public void updateCar(){
        lcars.list.clear();
        for (int i = 0; i < screen.table.getRowCount(); i++) {
            String[] row = new String[screen.table.getColumnCount()];
            for (int j = 0; j < screen.table.getColumnCount(); j++) {
                row[j] = String.valueOf(screen.table.getValueAt(i, j));
            }
            try {
                if(handle.checkNumberPlates(row[1]) && handle.checkType(row[2]) && handle.checkDate(row[3]) &&
                        handle.checkCompanyCar(row[4]) && handle.checkYearOfManufacture(row[5]))
                lcars.list.add(new Car(row[1], row[2], row[3], row[4], Integer.parseInt(row[5]), row[6]));
                else {
                    screen.alert();
                    lcars.list.clear();
                    lcars.readData();
                    screen.showListCars();
                    return;
                }
            } catch (NumberFormatException e){
                screen.alert();
                lcars.list.clear();
                lcars.readData();
                screen.showListCars();
                return;
            }

        }
        lcars.rewriteData();
        screen.showListCars();
    }
    public void removeCar(){
        try {
            String id = handle.handleNumberPlate("Enter number plate of removed Car: ");
            Car car = lcars.getList().stream().filter(b -> b.getNumberPlates().equals(id)).collect(Collectors.toList()).get(0);
            lcars.removeItem(car);
            screen.showListCars();
        } catch (NoSuchElementException e){
            screen.alert1();
        } catch (NumberFormatException e){
            screen.alert();
        } catch (IndexOutOfBoundsException e){
            screen.alert1();
        }
    }
    public void searchCar(){
        try {
            String id = handle.handleNumberPlate("Enter number plate of searched Car: ");
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
        } catch (NoSuchElementException e){
            screen.alert1();
        } catch (NumberFormatException e){
            screen.alert();
        } catch (IndexOutOfBoundsException e){
            screen.alert1();
        }
    }
    public void showCarList(){
        screen.showListCars();
    }
    public void addDriver(){
        String name = handle.handleName("Enter Name of Driver");
        String phoneNum = handle.handlePhoneNumber("Enter Phone Number of Driver:");
        String id = handle.handleIdDriver("Enter ID of Driver:");
        String DOB = handle.handleDate("Enter Date Of Birth of Driver:");
        String accommodation = handle.handlePlace("Enter Accommodation of Driver:");
        String license = handle.handleDrivingLicense("Enter Driving license of Driver:");
        ldrivers.addItem(new Driver(name,phoneNum,id,DOB, accommodation, license, "Available",0));
        screen.showListDrivers();
    }
    public void updateDriver(){
        ldrivers.list.clear();
        for (int i = 0; i < screen.table.getRowCount(); i++) {
            String[] row = new String[screen.table.getColumnCount()];
            for (int j = 0; j < screen.table.getColumnCount(); j++) {
                row[j] = String.valueOf(screen.table.getValueAt(i, j));
            }
            try {
                if(handle.checkName(row[1]) && handle.checkPhoneNumber(row[2]) && handle.checkIdDriver(row[3]) && handle.checkDate(row[4]) &&
                        handle.checkPlace(row[5]) && handle.checkDrivingLicense(row[6]) && handle.checkSalary(row[8]))
                ldrivers.list.add(new Driver(row[1], row[2], row[3], row[4], row[5], row[6],
                        row[7], Double.parseDouble(row[8]) ));
                else {
                    screen.alert();
                    ldrivers.list.clear();
                    ldrivers.readData();
                    screen.showListDrivers();
                    return;
                }
            }catch (NumberFormatException e){
                screen.alert();
                ldrivers.list.clear();
                ldrivers.readData();
                screen.showListDrivers();
                return;
            }

        }
        ldrivers.rewriteData();
        screen.showListDrivers();
    }
    public void removeDriver(){
        try {
            String id = JOptionPane.showInputDialog(null,"Enter ID of removed Driver:");
            Driver driver = ldrivers.getList().stream().filter(d -> d.getId().equals(id)).collect(Collectors.toList()).get(0);
            ldrivers.removeItem(driver);
            screen.showListDrivers();
        }catch (NoSuchElementException e){
            screen.alert1();
        } catch (NumberFormatException e){
            screen.alert();
        } catch (IndexOutOfBoundsException e){
            screen.alert1();
        }
    }
    public void searchDriver(){
        try {
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
        } catch (NoSuchElementException e){
            screen.alert1();
        } catch (NumberFormatException e){
            screen.alert();
        } catch (IndexOutOfBoundsException e){
            screen.alert1();
        }
        }



    public void showDriverList(){
        screen.showListDrivers();
    }
    public void addCustomer(){
        String name = handle.handleName("Enter name of Customer");
        String phoneNumber = handle.handlePhoneNumber("Enter Phone Number of Customer");
        lcus.addItem(new Customer(lcus.getNextID(), name, phoneNumber));
        screen.showListCustomers();
    }
    public void updateCustomer(){
        ArrayList<Customer> temp = new ArrayList<>(lcus.getList());
        lcus.list.clear();
        for (int i = 0; i < screen.table.getRowCount(); i++) {
            String[] row = new String[screen.table.getColumnCount()];
            for (int j = 0; j < screen.table.getColumnCount(); j++) {
                row[j] = String.valueOf(screen.table.getValueAt(i, j));
            }
            try {
                Integer.parseInt(row[0]);
            } catch (NumberFormatException e){
                screen.alert();
                lcus.list.clear();
                lcus.readData();
                screen.showListCustomers();
                return;
            }
            if(Integer.parseInt(row[0]) == temp.get(i).getId() && handle.checkName(row[1]) && handle.checkPhoneNumber(row[2])){
                lcus.list.add(new Customer(Integer.parseInt(row[0]), row[1], row[2]));
            }
            else {
                screen.alert();
                lcus.list.clear();
                lcus.readData();
                screen.showListCustomers();
                return;
            }
        }
        lcus.rewriteData();
        screen.showListCustomers();
    }
    public void removeCustomer(){
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter ID of removed Customer: "));
            Customer cus = lcus.getList().stream().filter(c -> c.getId() == id).collect(Collectors.toList()).get(0);
            lcus.removeItem(cus);
            screen.showListCustomers();
        } catch (NoSuchElementException e){
            screen.alert1();
        } catch (NumberFormatException e){
            screen.alert();
        } catch (IndexOutOfBoundsException e){
            screen.alert1();
        }

    }
    public void searchCustomer(){
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter ID of searched Customer: "));
            Customer cus = lcus.getList().stream().filter(c -> c.getId() == id).collect(Collectors.toList()).get(0);
            DefaultTableModel modelE = (DefaultTableModel) screen.table.getModel();
            int h = modelE.getRowCount();
            for(int j = 0;j < h ;j++) {
                modelE.removeRow(0);
            }
            modelE.addRow(new Object[]{cus.getId(), cus.getName(), cus.getPhoneNumber()});
        } catch (NoSuchElementException e){
            screen.alert1();
        } catch (NumberFormatException e){
            screen.alert();
        } catch (IndexOutOfBoundsException e){
            screen.alert1();
        }
    }
    public void showCustomerList(){
        screen.showListCustomers();
    }
    public void getSalary(){
        for(Driver i: ldrivers.list){
            i.setSalary(0);
        }
        for( Booking i : lBookings.list){
            if(i.getStatus().equals("Done")){
                i.getDriver().setSalary(i.getDriver().getSalary() + (double)i.getDistance()*20000* 0.3);
//                int index = IntStream.range(0, ldrivers.list.size())
//                        .filter(driver -> ldrivers.list.get(driver).getId() == i.getDriver().getId())
//                        .findFirst()
//                        .orElse(-1);
                int index = -1;
                for (Driver j : ldrivers.list){
                    if(j.getId().equals(i.getDriver().getId())){
                        index = ldrivers.list.indexOf(j);
                    }
                }
                if (index != -1)
               ldrivers.list.set(index,i.getDriver());
            }
        }
        ldrivers.rewriteData();
    }
}
