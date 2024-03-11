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
        if( !id.matches("[0-9]+") || id.length()!= 12){
            return false;
        }
        else return true;
    }
    public static boolean handleDrivingLicense(String license){
        boolean flag = true;
        String[] l = license.split(",");
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
        }
        return flag;
    }
    public static boolean handlePlace(String place) {
        if(place == null) {
            return false;
        }
        if(Character.isSpaceChar(place.charAt(0)) || Character.isSpaceChar(place.charAt(place.length()-1))){
            return false;
        }
        if (!place.matches("[0-9a-zA-Z-]"))
        return false;

        return true;
    }
    
    public static boolean handleSalary(double salary) {
        if(salary < 0) {
            return false;
        }
        return true;
    }

    public static boolean handledate(String date) {
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDateTime dateL = LocalDateTime.parse(date, formatter);
            return true;
        } catch(DateTimeParseException e) {
            System.out.println("Wrong format Date, please reEnter date: ");
            return false;
        }
    }

    public static boolean handleStatus(String status){
        switch(status){
            case "Available":
                return true;
            case "Unavailable":
                return true;
            default:
                return false;
        }
    }

    public static boolean handleName(String name) {
        if(name == null){
            return false;
        }
        for (int i = 0; i < name.length(); i++) {
        char ch = name.charAt(i);
        if (!Character.isLetter(ch) && ch != ' ') {
            return false;
        }
    }
    return true;
    }

    public static boolean handlePhoneNumber(String phoneNumber) {
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


    public static boolean handleDistance(int distance) {
        if(distance < 0) {
            return false;
        }
            return true;
        }
        // Booking
    public static boolean handleStatusBooking(String status){
        switch(status) {
            case "Not Deposit":
                return true;
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
