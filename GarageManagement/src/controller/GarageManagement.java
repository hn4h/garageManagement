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
    private ListOfCars lcars;
    private ListOfDrivers ldrivers;
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
}
