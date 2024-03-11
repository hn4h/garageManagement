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
        String n = "Hai Anh";
        if(Validator.handleName(n)){
            System.out.println(n);
        }

    }
}