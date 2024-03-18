package controller;

import model.Booking;
import model.Car;
import model.Customer;
import model.Driver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ListOfBookings extends ListController<Booking> {
    ListOfDrivers ldrivers;
    ListOfCustomers lcus;
    ListOfCars lcar;
    private int nextID;
    public ListOfBookings(){
        super();
        ldrivers = new ListOfDrivers();
        lcus = new ListOfCustomers();
        lcar = new ListOfCars();
        filepath = "src/List/ListOfBookings";
        nextID = 1;
        this.readData();
    }

    public void readData(){
        list.clear();
        try {
            scanner = new Scanner(new File(filepath));
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                String[] data = line.split("\\|");
                Customer addedCustomer;
                try{
                    addedCustomer = lcus.getList().stream().filter(cus -> cus.getId() == Integer.parseInt(data[5])).collect(Collectors.toList()).get(0);
                } catch (IndexOutOfBoundsException e){
                    addedCustomer =     lcus.getList().get(0);
                }
                Driver addedDriver;
                try {
                    addedDriver = ldrivers.getList().stream().filter(d -> d.getId() == Integer.parseInt(data[6])).collect(Collectors.toList()).get(0);
                } catch (IndexOutOfBoundsException e){
                    addedDriver = ldrivers.getList().get(0);
                }
                Car addedCar;
                try {
                    addedCar = lcar.getList().stream().filter(c -> c.getNumberPlates().equals(data[7])).collect(Collectors.toList()).get(0);
                } catch (IndexOutOfBoundsException e){
                    addedCar = lcar.getList().get(0);
                }
                list.add(new Booking(Integer.parseInt(data[0]), data[1], data[2],data[3], Integer.parseInt(data[4]),
                        addedCustomer,addedDriver, addedCar, data[8], data[9]));
                nextID = Integer.parseInt(data[0]) + 1;
            }
            scanner.close();
        }catch (FileNotFoundException e){
            System.out.println("File not found");
            e.printStackTrace();
        }
    }

    @Override
    public void addItem(Booking item) {
        super.addItem(item);
        try {
            FileWriter writer = new FileWriter(filepath, true);
            scanner = new Scanner(new File(filepath));
            if(!scanner.hasNextLine()){
                writer.write( nextID + "|" + item.getDate() + "|"
                        + item.getStart() + "|" + item.getDestination() + "|" + item.getDistance() + "|" +
                        item.getCustomer().getId() + "|" + item.getDriver().getId() + "|" +
                        item.getCar().getNumberPlates() + "|" + item.getIsDeposit() + "|" + item.getStatus());
                nextID++;
            }
            else{
                writer.write("\n" + nextID + "|" + item.getDate() + "|"
                        + item.getStart() + "|" + item.getDestination() + "|" + item.getDistance() + "|" +
                        item.getCustomer().getId() + "|" + item.getDriver().getId() + "|" +
                        item.getCar().getNumberPlates() + "|" + item.getIsDeposit() + "|" + item.getStatus());
                nextID++;
            }
            scanner.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setItem(int index, Booking item) {
        super.setItem(index, item);
        this.rewriteData();
    }

    @Override
    public void removeItem(Booking item) {
        super.removeItem(item);
        this.rewriteData();
    }

    public void rewriteData(){
        try {
            FileWriter writer = new FileWriter(filepath);
            for(Booking i : list){
                if(list.get(list.size()-1).getIDbooking() == i.getIDbooking() ){
                    writer.write(i.getIDbooking() + "|" + i.getDate() + "|"
                            + i.getStart() + "|" + i.getDestination() + "|" + i.getDistance() + "|" +
                            i.getCustomer().getId() + "|" + i.getDriver().getId() + "|" +
                            i.getCar().getNumberPlates() + "|" + i.getIsDeposit() + "|" + i.getStatus());
                    nextID = i.getIDbooking() + 1;
                }else {
                writer.write(i.getIDbooking() + "|" + i.getDate() + "|"
                        + i.getStart() + "|" + i.getDestination() + "|" + i.getDistance() + "|" +
                        i.getCustomer().getId() + "|" + i.getDriver().getId() + "|" +
                        i.getCar().getNumberPlates() + "|" + i.getIsDeposit() + "|" + i.getStatus() + "\n");
            }}
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Booking> getList() {
        this.readData();
        return super.getList();
    }
    public void clearData(){
        list.clear();
        this.rewriteData();
    }

    public int getNextID() {
        return nextID;
    }
}
