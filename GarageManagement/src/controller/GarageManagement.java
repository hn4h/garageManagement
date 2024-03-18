package controller;

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
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

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
            Customer addedCustomer = lcus.getList().get(0);
            Driver addedDriver = ldrivers.getList().get(0);
            Car addedCar = lcars.getList().get(0);
        lBookings.addItem(new Booking(lBookings.getNextID(),date,start,destination,
                distance,addedCustomer,addedDriver,addedCar,"No","Not Started"));
        screen.notifyAddSuccessfully();
        screen.showListBookings();
    }
    public void updateBooking(){
//        ArrayList<Booking> temp = new ArrayList<>(lBookings.getList());
//        lBookings.list.clear();
        for (int i = 0; i < screen.table.getRowCount(); i++) {
            String[] row = new String[screen.table.getColumnCount()];
            for (int j = 0; j < screen.table.getColumnCount(); j++) {
                row[j] = String.valueOf(screen.table.getValueAt(i, j));
            }
            try {
                Customer addedCustomer;
                try{
                    addedCustomer = lcus.getList().stream().filter(cus -> cus.getId() == Integer.parseInt(row[5])).collect(Collectors.toList()).get(0);
                } catch (IndexOutOfBoundsException e){
                    addedCustomer = lcus.getList().get(0);
                }
                Driver addedDriver;
                try {
                    addedDriver = ldrivers.getList().stream().filter(d -> d.getId() == Integer.parseInt(row[6])).collect(Collectors.toList()).get(0);
                } catch (IndexOutOfBoundsException e){
                    addedDriver = ldrivers.getList().get(0);
                }
                Car addedCar;
                try {
                    addedCar = lcars.getList().stream().filter(c -> c.getNumberPlates().equals(row[7])).collect(Collectors.toList()).get(0);
                } catch (IndexOutOfBoundsException e){
                    addedCar = lcars.getList().get(0);
                }
                Booking booking = lBookings.list.stream().filter(booking1 -> booking1.getIDbooking() == Integer.parseInt(row
                [0])).collect(Collectors.toList()).get(0);
                int index = lBookings.list.indexOf(booking);

                if( handle.checkDate(row[1]) && handle.checkPlace(row[2]) &&
                handle.checkPlace(row[3]) && handle.checkDistance(Integer.parseInt(row[4]))){
                    booking = new Booking(Integer.parseInt(row[0]), row[1], row[2],row[3], Integer.parseInt(row[4]),
                            addedCustomer,addedDriver, addedCar, row[8], row[9]);
                    System.out.println(booking.getDriver().getId());
                    lBookings.setItem(index,booking);
                }else {
                    lBookings.list.clear();
                    lBookings.readData();
                    screen.showListBookings();
                    return;
                }
            } catch (IndexOutOfBoundsException e){
                screen.alertIDNotExist();
                lBookings.list.clear();
                lBookings.readData();
                screen.showListBookings();
                return;
            } catch (NumberFormatException e){
                screen.alert("Distance only have number");
                lBookings.list.clear();
                lBookings.readData();
                screen.showListBookings();
                return;
            }

        }
        this.getSalary();
        lBookings.rewriteData();
        screen.notifyUpdateSuccessfully();
        screen.showListBookings();
    }
    public void removeBooking(){
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter ID of removed booking: "));
            Booking booking = lBookings.getList().stream().filter(b -> b.getIDbooking() == id).collect(Collectors.toList()).get(0);
            lBookings.removeItem(booking);
            screen.notifyRemoveSuccessfully();
            screen.showListBookings();
        } catch (NoSuchElementException e){
            screen.alertIDNotExist();
        } catch (NumberFormatException e){
            screen.alert("ID only have number");
        } catch (IndexOutOfBoundsException e){
            screen.alertIDNotExist();
        }
    }

    public void searchBooking(){
        try {
            String id = JOptionPane.showInputDialog(null, "Enter Information of searched booking: ");
            DefaultTableModel modelE = (DefaultTableModel) screen.table.getModel();
            List<Booking> searchedListBooking = lBookings.getList().stream().filter(b -> b.getDate().contains(id) ||
                    b.getStart().contains(id) || b.getDestination().contains(id)).collect(Collectors.toList());
            int h = modelE.getRowCount();
            for(int j = 0;j < h ;j++) {
                modelE.removeRow(0);
            }
            for (Booking i : searchedListBooking){
                    modelE.addRow(new Object[]{i.getIDbooking(), i.getDate(), i.getStart(),
                            i.getDestination(), i.getDistance(), i.getCustomer().getId(), i.getDriver().getId(),
                            i.getCar().getNumberPlates(), i.getIsDeposit(), i.getStatus()});
            }
        } catch (NoSuchElementException e){
            screen.alertIDNotExist();
        } catch (IndexOutOfBoundsException e){
            screen.alertIDNotExist();
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
        lcars.addItem(new Car(lcars.getNextID(), numPlate, type, maintenanceSchedule, carMaker, year, "Available"));
        screen.notifyAddSuccessfully();
        screen.showListCars();
    }
    public void updateCar(){
//        Car nullCar = lcars.getList().get(0);
//        lcars.list.clear();
//        lcars.addItem(nullCar);
        for (int i = 0; i < screen.table.getRowCount(); i++) {
            String[] row = new String[screen.table.getColumnCount()];
            for (int j = 0; j < screen.table.getColumnCount(); j++) {
                row[j] = String.valueOf(screen.table.getValueAt(i, j));
            }
            try {
                Car editedCar = lcars.list.stream().filter(car -> car.getId() == Integer.parseInt(row[0])).collect(Collectors.toList()).get(0);
                int index = lcars.list.indexOf(editedCar);
                if(handle.checkNumberPlates(row[1]) && handle.checkType(row[2]) && handle.checkDate(row[3]) &&
                        handle.checkCompanyCar(row[4]) && handle.checkYearOfManufacture(row[5])){
                    editedCar = new Car( Integer.parseInt(row[0]), row[1], row[2], row[3], row[4], Integer.parseInt(row[5]), row[6]);
                    lcars.setItem(index,editedCar);
                }
                else {
                    lcars.list.clear();
                    lcars.readData();
                    screen.showListCars();
                    return;
                }
            } catch (NumberFormatException e){
                screen.alert("Year format is yyyy");
                lcars.list.clear();
                lcars.readData();
                screen.showListCars();
                return;
            }

        }
        lcars.rewriteData();
        screen.notifyUpdateSuccessfully();
        screen.showListCars();
    }
    public void removeCar(){
        try {
            String id = handle.handleNumberPlate("Enter ID of removed Car: ");
            Car car = lcars.getList().stream().filter(b -> b.getId() == Integer.parseInt(id)).collect(Collectors.toList()).get(0);
            lcars.removeItem(car);
            screen.notifyRemoveSuccessfully();
            screen.showListCars();
        } catch (NoSuchElementException e){
            screen.alertIDNotExist();
        } catch (NumberFormatException e){
            screen.alert("ID only have number.");
        } catch (IndexOutOfBoundsException e){
            screen.alertIDNotExist();
        }
    }
    public void searchCar(){
        try {
            String id = handle.handleNumberPlate("Enter NoPlates or Type of searched Car: ");
            List<Car> cars = lcars.getList().stream().filter(b -> b.getNumberPlates().contains(id) || b.getType().contains(id)).collect(Collectors.toList());
            DefaultTableModel modelE = (DefaultTableModel) screen.table.getModel();
            int h = modelE.getRowCount();
            for(int j = 0;j < h ;j++) {
                modelE.removeRow(0);
            }
            for(Car i : cars) {
                    modelE.addRow(new Object[]{ i.getId(), i.getNumberPlates(),
                            i.getType(), i.getMaintenanceSchedule()
                            , i.getCompanyCar(), i.getYear(),
                            i.getStatus()});

            }
        } catch (NoSuchElementException e){
            screen.alertIDNotExist();
        } catch (IndexOutOfBoundsException e){
            screen.alertIDNotExist();
        }
    }
    public void showCarList(){
        screen.showListCars();
    }
    public void addDriver(){
        String name = handle.handleName("Enter Name of Driver");
        String phoneNum = handle.handlePhoneNumber("Enter Phone Number of Driver:");
        String id = handle.handleIdDriver("Enter NoID of Driver:");
        String DOB = handle.handleDate("Enter Date Of Birth of Driver:");
        String accommodation = handle.handlePlace("Enter Accommodation of Driver:");
        String license = handle.handleDrivingLicense("Enter Driving license of Driver:");
        ldrivers.addItem(new Driver(ldrivers.getNextID(), name,phoneNum,id,DOB, accommodation, license, "Available",0));
        screen.notifyAddSuccessfully();
        screen.showListDrivers();
    }
    public void updateDriver(){
//        Driver nullDriver = ldrivers.list.get(0);
//        ldrivers.list.clear();
//        ldrivers.addItem(nullDriver);
        for (int i = 0; i < screen.table.getRowCount(); i++) {
            String[] row = new String[screen.table.getColumnCount()];
            for (int j = 0; j < screen.table.getColumnCount(); j++) {
                row[j] = String.valueOf(screen.table.getValueAt(i, j));
            }
            try {
                Driver editedDriver = ldrivers.list.stream().filter(driver -> driver.getId() == Integer.parseInt(row[0])).collect(Collectors.toList()).get(0);
                int index = ldrivers.list.indexOf(editedDriver);
                if( handle.checkName(row[1]) && handle.checkPhoneNumber(row[2]) && handle.checkIdDriver(row[3]) && handle.checkDate(row[4]) &&
                        handle.checkPlace(row[5]) && handle.checkDrivingLicense(row[6]) && handle.checkSalary(row[8])) {
                    editedDriver = new Driver(Integer.parseInt(row[0]), row[1], row[2], row[3], row[4], row[5], row[6],
                            row[7], Double.parseDouble(row[8]));
                    ldrivers.setItem(index, editedDriver);
                }else {
                    ldrivers.list.clear();
                    ldrivers.readData();
                    screen.showListDrivers();
                    return;
                }
            }catch (NumberFormatException e){
                screen.alert("Only have number");
                ldrivers.list.clear();
                ldrivers.readData();
                screen.showListDrivers();
                return;
            }

        }
        ldrivers.rewriteData();
        screen.notifyUpdateSuccessfully();
        screen.showListDrivers();
    }
    public void removeDriver(){
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog(null,"Enter ID of removed Driver:"));
            Driver driver = ldrivers.getList().stream().filter(d -> d.getId() == id).collect(Collectors.toList()).get(0);
            ldrivers.removeItem(driver);
            screen.notifyRemoveSuccessfully();
            screen.showListDrivers();
        }catch (NoSuchElementException e){
            screen.alertIDNotExist();
        } catch (NumberFormatException e){
            screen.alert("Only have number");
        } catch (IndexOutOfBoundsException e){
            screen.alertIDNotExist();
        }
    }
    public void searchDriver(){
        try {
            String searchedName = handle.handleName("Enter Name of searched Driver: ");
            List<Driver> searchedListDriver = ldrivers.getList().stream().filter(c -> c.getName().toLowerCase().contains(searchedName.toLowerCase())).collect(Collectors.toList());
            DefaultTableModel modelE = (DefaultTableModel) screen.table.getModel();
            int h = modelE.getRowCount();
            for(int j = 0;j < h ;j++) {
                modelE.removeRow(0);
            }
            for( Driver driver : searchedListDriver){
                modelE.addRow(new Object[]{driver.getId(), driver.getName(), driver.getPhoneNumber(), driver.getNumberId(), driver.getDOB(),
                        driver.getAccommodation(), driver.getDrivingLicense(),
                        driver.getStatus(), driver.getSalary()});
            }
        } catch (NoSuchElementException e){
            screen.alertIDNotExist();
        } catch (NumberFormatException e){
            screen.alert("Only have number");
        } catch (IndexOutOfBoundsException e){
            screen.alertIDNotExist();
        }
        }



    public void showDriverList(){
        screen.showListDrivers();
    }
    public void addCustomer(){
        String name = handle.handleName("Enter name of Customer");
        String phoneNumber = handle.handlePhoneNumber("Enter Phone Number of Customer");
        lcus.addItem(new Customer(lcus.getNextID(), name, phoneNumber));
        screen.notifyAddSuccessfully();
        screen.showListCustomers();
    }
    public void updateCustomer(){
//        ArrayList<Customer> temp = new ArrayList<>(lcus.getList());
//        lcus.list.clear();
        for (int i = 0; i < screen.table.getRowCount(); i++) {
            String[] row = new String[screen.table.getColumnCount()];
            for (int j = 0; j < screen.table.getColumnCount(); j++) {
                row[j] = String.valueOf(screen.table.getValueAt(i, j));
            }
            try {
                Integer.parseInt(row[0]);
            } catch (NumberFormatException e){
                screen.alert("Only have number");
                lcus.list.clear();
                lcus.readData();
                screen.showListCustomers();
                return;
            }
            Customer editedCustomer = lcus.list.stream().filter(customer -> customer.getId() == Integer.parseInt(row[0])).collect(Collectors.toList()).get(0);
            int index = lcus.list.indexOf(editedCustomer);
            if(handle.checkName(row[1]) && handle.checkPhoneNumber(row[2])){
                editedCustomer.setName(row[1]);
                editedCustomer.setPhoneNumber(row[2]);
                lcus.list.set(index,editedCustomer);
            }
            else {
                lcus.list.clear();
                lcus.readData();
                screen.showListCustomers();
                return;
            }
        }
        lcus.rewriteData();
        screen.showListCustomers();
        screen.notifyUpdateSuccessfully();
    }
    public void removeCustomer(){
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter ID of removed Customer: "));
            Customer cus = lcus.getList().stream().filter(c -> c.getId() == id).collect(Collectors.toList()).get(0);
            lcus.removeItem(cus);
            screen.showListCustomers();
            screen.notifyRemoveSuccessfully();
        } catch (NoSuchElementException e){
            screen.alertIDNotExist();
        } catch (NumberFormatException e){
            screen.alert("Only have number");
        } catch (IndexOutOfBoundsException e){
            screen.alertIDNotExist();
        }
    }
    public void searchCustomer(){
        try {
            //int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter ID of searched Customer: "));
            String searchedName = handle.handleName("Enter Name of searched Customer: ");
            List<Customer> searchedListCustomer = lcus.getList().stream().filter(c -> c.getName().toLowerCase().contains(searchedName.toLowerCase())).collect(Collectors.toList());
          //  Customer cus = lcus.getList().stream().filter(c -> c.getId() == id).collect(Collectors.toList()).get(0);
            DefaultTableModel modelE = (DefaultTableModel) screen.table.getModel();
            int h = modelE.getRowCount();
            for(int j = 0;j < h ;j++) {
                modelE.removeRow(0);
            }
            for (Customer i : searchedListCustomer) {
                modelE.addRow(new Object[]{i.getId(), i.getName(), i.getPhoneNumber()});
            }
        //    modelE.addRow(new Object[]{cus.getId(), cus.getName(), cus.getPhoneNumber()});
        } catch (NoSuchElementException e){
            screen.alertIDNotExist();
        } catch (NumberFormatException e){
            screen.alert("Only have number");
        } catch (IndexOutOfBoundsException e){
            screen.alertIDNotExist();
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
                    if(j.getId() == i.getDriver().getId()){
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
