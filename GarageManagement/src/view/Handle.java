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
            screen.alert("Only have number and 12 Character");
            return false;
        } else {
            return true;
        }
    }
    public String handleIdDriver(String msg){
        String id = JOptionPane.showInputDialog(null, msg);
        while (!checkIdDriver(id)){
            id = JOptionPane.showInputDialog(null, msg);
        }
        return id;
    }
    public String handleDrivingLicense(String msg){
        String license = JOptionPane.showInputDialog(null, msg);
        while (!checkDrivingLicense(license)){
            license = JOptionPane.showInputDialog(null, msg);
        }
        return license;
    }
    public boolean checkDrivingLicense(String license){
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
                screen.alert("Driving license doesn't exist");
                break;
        }
        }
        return flag;
    }
    public String handlePlace(String msg){
        String place = JOptionPane.showInputDialog(null, msg);
        while (!checkPlace(place)){
            place = JOptionPane.showInputDialog(null, msg);
        }
        return place.trim();
    }
    public boolean checkPlace(String place) {
        if(place == null) {
            screen.alert("Place can't null");
            return false;
        }
        if (!place.matches("[0-9a-zA-Z-\\s]+")){
            screen.alert("Place can't have special character");
            return false;
        }
        return true;
    }

    public boolean checkSalary(String s) {
        try{
            double salary = Double.parseDouble(s);
            if(salary < 0) {
                screen.alert("Salary must >= 0");
                return false;
            }
            return true;
        }
        catch (NumberFormatException e){
            screen.alert("Salary only have number");
            return false;
        }
    }

    public String handleDate(String msg){
        String date = JOptionPane.showInputDialog(null,msg);
        while (!checkDate(date)){
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
            screen.alert("Date format: dd/mm/yyyy");
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
            screen.alert("Phone number only have number and 10 character");
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
            screen.alert("Name can't null");
            return false;
        }
        if (!name.matches("[a-zA-Z\\s]+")){
            screen.alert("Name only have letter");
            return false;
        }

        return true;
    }
    public String handleName(String msg) {
        String name = JOptionPane.showInputDialog(null, msg);
        while(!checkName(name)){
            name = JOptionPane.showInputDialog(null, msg);
        }
        return name.trim();
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
            screen.alert("No Plates can't null");
        return false;
    }
    if (!numberPlates.matches("[a-zA-Z-0-9]+")){
        screen.alert("No Plates must contain letter and number");
        return false;
    }
    return true;
    }
    public String handleNumberPlate(String msg){
        String numPlate = JOptionPane.showInputDialog(null,msg);
        while (!this.checkNumberPlates(numPlate)){
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
            type = JOptionPane.showInputDialog(null,msg);
        }
        return type;
    }
    public boolean checkType(String type) {
        if(type == null){
            screen.alert("Type of car can't null");
            return false;
        }
        if (!type.matches("[a-zA-Z0-9]+")) {
            screen.alert("Type of Car contain letter or number");
            return false;
        }

    return true;
    }
    public String handleCompanyCar(String msg){
        String comCar = JOptionPane.showInputDialog(null,msg);
        while (!this.checkCompanyCar(comCar)){
            comCar = JOptionPane.showInputDialog(null,msg);
        }
        return comCar;
    }
    public boolean checkCompanyCar(String companyCar) {
        if(companyCar == null){
            screen.alert("Carmaker can't null");
            return false;
        }
        if (!companyCar.matches("[a-zA-Z0-9]+")) {
            screen.alert("Carmaker contain letter or number");
            return false;
        }

    return true;
    }
    public String handleYearOfManufacture(String msg){
        String year = JOptionPane.showInputDialog(null,msg);
        while (!this.checkYearOfManufacture(year)){
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
             } else{
                 screen.alert("year must > 1900 and <= now");
                 return false;
             }
        } catch(NumberFormatException e) {
             screen.alert("Year only have number");
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

    public String handleID(String msg){
        String id = JOptionPane.showInputDialog(null,msg);
        while (!this.checkID(id)){
            id = JOptionPane.showInputDialog(null,msg);
        }
        return id;
    }
    public boolean checkID(String IDbooking) {
        if( !IDbooking.matches("[0-9]+")){
            screen.alert("ID must have number");
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
        String distance = JOptionPane.showInputDialog(null, msg);
        while(!checkDistance(d)){
        try {
            distance = JOptionPane.showInputDialog(null, msg);
            d = Integer.parseInt(distance);
            if (checkDistance(d)) break;
        }catch (NumberFormatException e){
            screen.alert("Distance only have number");
        }
    }
     return d;
    }
    public boolean checkDistance(int distance) {
        if(distance < 0) {
            screen.alert("Distance must >= 0");
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