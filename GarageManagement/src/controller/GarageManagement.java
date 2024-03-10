package controller;

import view.Screen;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    }
}
