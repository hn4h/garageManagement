//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import controller.ListOfCars;
import controller.ListOfCustomers;
import controller.ListOfDrivers;
import model.Car;
import model.Customer;
import model.Driver;

import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        ListOfDrivers n = new ListOfDrivers();
        Driver le = new Driver("Le","0339404584", "1234","1/1/2004","TN","A1","Ngu",0);
        Driver tu = new Driver("ATu" , "0123456789", "321", "25/09/2004", "HN","A2","Available",100);
//        n.removeItem(2);
        Customer hnah = new Customer("Hai Anh", "0339404584",1);
        ListOfCustomers c = new ListOfCustomers();
        c.addItem(hnah);

    }
}