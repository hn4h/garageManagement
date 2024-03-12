package view;/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import view.Screen;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


/**
 *
 * @author AD
 */
public class Handle {


//Driver
    public static boolean handleIdPerson(String id){
<<<<<<< HEAD
        if (!id.matches("[0-9]{12}")) {
        return false;
    } else {
        return true;
    }
=======
        if( !id.matches("[0-9]+") || id.length()!= 12){
            return false;
        }
        else return true;
>>>>>>> AnhNLH
    }
    public static boolean handleDrivingLicense(String license){
        boolean flag = false;
        String[] l = license.split(",");
<<<<<<< HEAD
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
=======
        for (int i = 0; i < l.length;i++){
            l[i] = l[i].replaceAll("[\\s]","");
            switch(l[i]){
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
            }
>>>>>>> AnhNLH
        }
        return flag;
    }
    public static boolean handlePlace(String place) {
        if(place == null) {
            System.out.println("1");
            return false;
        }
        if(Character.isSpaceChar(place.charAt(0)) || Character.isSpaceChar(place.charAt(place.length()-1))){
            System.out.println("2");
            return false;
        }
        if (!place.matches("[0-9a-zA-Z-]+")){
            System.out.println("3");
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

    public static boolean handleDate(String date) {
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDateTime dateL = LocalDateTime.parse(date, formatter);
            return true;
        } catch(DateTimeParseException e) {
            System.out.println("Wrong format Date, please reEnter date: ");
            return false;
        }
    }

<<<<<<< HEAD
    public static boolean handleDriverStatus(String driverStatus){
        switch(driverStatus){
=======
    public static boolean handleStatus(String status){
        switch(status){
>>>>>>> AnhNLH
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

    public static boolean handlePhoneNumber(String phoneNumber) {
<<<<<<< HEAD
        if (!phoneNumber.matches("0[0-9]{9}")) {
        return false;
    } else {
        return true;
    }
    }

//Person + Customer
    public static boolean handleName(String name) {
        if(name == null){
            return false;
        }
        if(Character.isSpaceChar(name.charAt(0)) || Character.isSpaceChar(name.charAt(name.length()-1))){
            return false;
        }
        return true;
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

=======
        if(!phoneNumber.matches("[0-9]+") || phoneNumber.length() != 10) {
            return false;
        } else {
            return true;
        }
    }

//Person + Customer
    public static boolean handleNo(int no, int id){
        if(no == id){
            return true;
        } else return false;
    }

>>>>>>> AnhNLH
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
    public static boolean handleYear(int year){
        LocalDate date = LocalDate.now();
        if (year <= date.getYear() && year > 1900){
            return true;
        } else return false;
    }

    public static boolean handleNumberPlates(String numberPlates) {
        if (numberPlates == null) {
        return false;
    }
    for (int i = 0; i < numberPlates.length(); i++) {
        char ch = numberPlates.charAt(i);
        if (!Character.isLetterOrDigit(ch) && ch != '-') {
            return false;
        }
    }
    return true;
    }

<<<<<<< HEAD
    public static boolean handleMaintainanceSchedule(String maintainenceSchedule) {
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDateTime maintainenceSchedule1 = LocalDateTime.parse(maintainenceSchedule, formatter);
            return true;
        } catch(DateTimeParseException e) {
            System.out.println("Wrong format Date, please reEnter date: ");
            return false;
        }
    }

    public static boolean handleType(String type) {
        if(type == null){
            return false;
        }
        for (int i = 0; i < type.length(); i++) {
        char ch = type.charAt(i);
        if (!Character.isLetter(ch) && ch != ' ') {
            return false;
        }
    }
    return true;
    }

    public static boolean handleCompanyCar(String companyCar) {
        if(companyCar == null){
            return false;
        }
        for (int i = 0; i < companyCar.length(); i++) {
        char ch = companyCar.charAt(i);
        if (!Character.isLetter(ch) && ch != ' ') {
            return false;
        }
    }
    return true;
    }

    public static boolean handleYearOfManufacture(String yearOfManufacture) {
         try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");
            LocalDateTime yearOfManufacture1 = LocalDateTime.parse(yearOfManufacture, formatter);
            return true;
        } catch(DateTimeParseException e) {
            System.out.println("Wrong format Date, please reEnter date: ");
            return false;
        }
    }
//Booking
    public static boolean handleBookingDate(String bookingDate) {
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDateTime bookingDateL = LocalDateTime.parse(bookingDate, formatter);
            return true;
        } catch(DateTimeParseException e) {
            System.out.println("Wrong format Date, please reEnter date: ");
            return false;
        }
    }
    public static boolean handleIDBooking(String IDbooking) {
        if( !IDbooking.matches("[0-9]")){
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

=======

>>>>>>> AnhNLH
    public static boolean handleDistance(int distance) {
        if(distance < 0) {
            return false;
        }
            return true;
        }
<<<<<<< HEAD


=======
>>>>>>> AnhNLH
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
<<<<<<< HEAD
=======
    }
>>>>>>> AnhNLH
    }
    }