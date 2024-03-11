package view;/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import view.Screen;

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
        if( !id.matches("[0-9]") || id.length()!= 12){
            return false;
        }
        else return true;
    }

    public static boolean handleAccommodation(String accommodation) {
        if(accommodation == null) {
            return false;
        }
        if(Character.isSpaceChar(accommodation.charAt(0)) || Character.isSpaceChar(accommodation.charAt(accommodation.length()-1))){
            return false;
        }
        if (!accommodation.matches("[0-9a-zA-Z-]"))
        return false;

        return true;
    }

    public static boolean handleDrivingLicense(String license){
        boolean flag = true;
        String[] l = license.split(",");
        return flag;
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

    public static boolean setStatus(String status){
        switch(status){
            case "unavailable":
                return true;
            case "available":
                return true;
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
    public static boolean setName(String name) {
        if(name == null){
            return false;
        }
        if(Character.isSpaceChar(name.charAt(0)) || Character.isSpaceChar(name.charAt(name.length()-1))){
            return false;
        }
        return true;
    }

//    public static boolean setPhoneNumber(String phoneNumber) {
//        if(phoneNumber.matches("0\\d+")) {
//            return true;
//        } else {
//            return false;
//        }
//    }

//Car
//    public static boolean setStatus(String status) {
//        switch(status) {
//            case "is maintained":
//                return true;
//            case "running":
//                return true;
//            case "was broken":
//                return true;
//            default:
//                return false;
//        }
//    }

    public static boolean setNumberPlates(String numberPlates) {
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

//Booking
//    public static boolean setDate(String date) {
//        String[] formats = {"dd-MM-yyyy", "dd/MM/yyyy"};
//
//        for (String format : formats) {
//            try {
//                ... dob = new ...(format);
//                date.setLenient(false);
//                date.parse(DOB);
//                return true;
//        } catch (ParseException e) {
//        }
//        }
//        return false;
//    }

    public static boolean setDistance(int distance) {
        if(distance < 0) {
            return false;
        }
            return true;
        }
    }
