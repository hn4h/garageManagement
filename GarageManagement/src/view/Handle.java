package view;/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import view.Screen;

import javax.swing.*;
import javax.xml.validation.Validator;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


/**
 *
 * @author AD
 */
public class Handle {
    public Screen screen;

    public Handle(Screen screen){
        this.screen = screen;
    }


//Driver
    public boolean checkIdDriver(String id){
        if (!id.matches("[0-9]{12}")) {
            return false;
        } else {
            return true;
        }
    }
    public String handleIdDriver(){
        String id = JOptionPane.showInputDialog(null, "Enter ID of Driverr: ");
        while (!checkIdDriver(id)){
            screen.alert();
            id = JOptionPane.showInputDialog(null, "Enter ID of Driverr: ");
        }
        return id;
    }
    public static boolean handleDrivingLicense(String license){
        boolean flag = false;
        String[] l = license.split(",");
    for (String lValue : l) {
        lValue = lValue.replaceAll("[\\s]","");
        switch(lValue.trim()) {
            case "A1":
                flag = true;
                break;
            case "A2":
                flag = true;
                break;
            case "A3":
                flag = true;
                break;
            case "A4":
                flag = true;
                break;
            case "B1":
                flag = true;
                break;
            case "B2":
                flag = true;
                break;
            case "C":
                flag = true;
                break;
            case "D":
                flag = true;
                break;
            case "E":
                flag = true;
                break;
            case "F":
                flag = true;
                break;
            default:
                flag = false;
                break;
        }
        if (!flag) {
            break;
        }
        }
        return flag;
    }
    public String handlePlace(String msg){
        String place = JOptionPane.showInputDialog(null, msg);
        while (!checkPlace(place)){
            screen.alert();
            place = JOptionPane.showInputDialog(null, msg);
        }
        return place;
    }
    public boolean checkPlace(String place) {
        if(place == null) {
            return false;
        }
        if(Character.isSpaceChar(place.charAt(0)) || Character.isSpaceChar(place.charAt(place.length()-1))){
            return false;
        }
        if (!place.matches("[0-9a-zA-Z-\\s]+")){
            return false;
        }
        return true;
    }

    public static boolean handleSalary(double salary) {
        if(salary < 0) {
            return false;
        }
        return true;
    }

    public String handleDate(String msg){
        String date = JOptionPane.showInputDialog(null,msg);
        while (!checkDate(date)){
            System.out.println(msg);
            screen.alert();
            date = JOptionPane.showInputDialog(null,msg);
        }
        return date;
    }

    public boolean checkDate(String date) {
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate dateL = LocalDate.parse(date, formatter);
            return true;
        } catch(DateTimeParseException e) {
            return false;
        }
    }

    public static boolean handleDriverStatus(String driverStatus){
        switch(driverStatus){
            case "Available":
                return true;
            case "Unavailable":
                return true;
            default:
                return false;
        }
    }

    public static boolean handleDriverName(String driverName) {
        if(driverName == null){
            return false;
        }
        for (int i = 0; i < driverName.length(); i++) {
        char ch = driverName.charAt(i);
        if (!Character.isLetter(ch) && ch != ' ') {
            return false;
        }
    }
    return true;
    }

    public boolean checkPhoneNumber(String phoneNumber){
        if (!phoneNumber.matches("0[0-9]{9}")){
            screen.alert();
            return false;
        }else return true;
    }
    public String handlePhoneNumber(String msg) {
        String phoneNumber = JOptionPane.showInputDialog(null, msg);
        while(!checkPhoneNumber(phoneNumber)){
            phoneNumber = JOptionPane.showInputDialog(null, msg);
        }
        return phoneNumber;
    }

//Person + Customer
    public boolean checkName(String name){
        if(name == null){
            screen.alert();
            return false;
        }
        if(Character.isSpaceChar(name.charAt(0)) || Character.isSpaceChar(name.charAt(name.length()-1))){
            screen.alert();
            return false;
        }
        if (!name.matches("[a-zA-Z\\s]+")){
            screen.alert();
            return false;
        }

        return true;
    }
    public String handleName(String msg) {
        String name = JOptionPane.showInputDialog(null, msg);
        while(!checkName(name)){
            name = JOptionPane.showInputDialog(null, msg);
        }
        return name;
    }

//Car
    public static boolean handleCarStatus(String carStatus) {
        switch(carStatus) {
            case "is maintained":
                return true;
            case "running":
                return true;
            case "was broken":
                return true;
            default:
                return false;
       }
    }
    

//Person + Customer
    public static boolean handleNo(int no, int id){
        if(no == id){
            return true;
        } else return false;
    }

//Car
    public static boolean setStatusCar(String status) {
        switch(status) {
            case "Operating":
                return true;
            case "Repairing":
                return true;
            case "Available":
                return true;
            default:
                return false;
        }
    }

    public boolean checkNumberPlates(String numberPlates) {
        if (numberPlates == null) {
            System.out.println(numberPlates);
        return false;
    }
    if (!numberPlates.matches("[a-zA-Z-0-9]+")){
        System.out.println(numberPlates);
        return false;
    }
    return true;
    }
    public String handleNumberPlate(String msg){
        String numPlate = JOptionPane.showInputDialog(null,msg);
        while (!this.checkNumberPlates(numPlate)){
            System.out.println(msg);
            screen.alert();
            numPlate = JOptionPane.showInputDialog(null,msg);
        }
        return numPlate;
    }

    public static boolean handleMaintainanceSchedule(String maintainenceSchedule) {
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate maintainenceSchedule1 = LocalDate.parse(maintainenceSchedule, formatter);
            return true;
        } catch(DateTimeParseException e) {
            return false;
        }
    }
    public String handleType(String msg){
        String type = JOptionPane.showInputDialog(null,msg);
        while (!this.checkType(type)){
            screen.alert();
            type = JOptionPane.showInputDialog(null,msg);
        }
        return type;
    }
    public boolean checkType(String type) {
        if(type == null){
            System.out.println(type);
            return false;
        }
        if (!type.matches("[a-zA-Z0-9]+")) {
            System.out.println(type);
            return false;
        }

    return true;
    }
    public String handleCompanyCar(String msg){
        String comCar = JOptionPane.showInputDialog(null,msg);
        while (!this.checkCompanyCar(comCar)){
            screen.alert();
            comCar = JOptionPane.showInputDialog(null,msg);
        }
        return comCar;
    }
    public boolean checkCompanyCar(String companyCar) {
        if(companyCar == null){
            System.out.println(companyCar);
            return false;
        }
        if (!companyCar.matches("[a-zA-Z0-9]+")) {
            System.out.println(companyCar);
            return false;
        }

    return true;
    }
    public String handleYearOfManufacture(String msg){
        String year = JOptionPane.showInputDialog(null,msg);
        while (!this.checkYearOfManufacture(year)){
            screen.alert();
            year = JOptionPane.showInputDialog(null,msg);
        }
        return year;
    }
    public boolean checkYearOfManufacture(String year) {
         try{
             LocalDate date = LocalDate.now();
             int y = Integer.parseInt(year);
             if (y <= date.getYear() && y > 1900){
                 return true;
             } else return false;
        } catch(NumberFormatException e) {
            return false;
        }
    }
//Booking
    public static boolean handleBookingDate(String bookingDate) {
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate bookingDateL = LocalDate.parse(bookingDate, formatter);
            return true;
        } catch(DateTimeParseException e) {
            System.out.println("Wrong format Date, please reEnter date: ");
            return false;
        }
    }
    public static boolean handleID(String IDbooking) {
        if( !IDbooking.matches("[0-9]+")){
            return false;
        }
        else return true;
    }

    public static boolean handleStart(String start) {
        if(start == null){
            return false;
        }
        for (int i = 0; i < start.length(); i++) {
        char ch = start.charAt(i);
        if (!Character.isLetter(ch) && ch != ' ') {
            return false;
        }
    }
    return true;
    }

    public static boolean handleDestination(String destination) {
        if(destination == null){
            return false;
        }
        for (int i = 0; i < destination.length(); i++) {
        char ch = destination.charAt(i);
        if (!Character.isLetter(ch) && ch != ' ') {
            return false;
        }
    }
    return true;
    }

    public int handleDistance(String msg){
        int d = -1;
        while(!checkDistance(d)){
        try {
            String distance = JOptionPane.showInputDialog(null, msg);
            d = Integer.parseInt(distance);
            if (checkDistance(d)) break;
            screen.alert();
        }catch (NumberFormatException e){
            screen.alert();
        }
    }
     return d;
    }
    public boolean checkDistance(int distance) {
        if(distance < 0) {
            return false;
        }
            return true;
        }


        // Booking
    public static boolean handleStatusBooking(String status){
        switch(status) {
            case "Not Started":
                return true;
            case "Running":
                return true;
            case "Done":
                return true;   
            default:
                return false;
        }
    }
    }