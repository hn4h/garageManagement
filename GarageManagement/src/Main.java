//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.


import controller.ListOfCars;
import model.Car;
import view.Handle;
import view.Screen;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Main {
    public static void main(String[] args) {
        String s = "B2";
        Handle handle = new Handle(new Screen());
        System.out.println(handle.checkDrivingLicense(s));
    }
}