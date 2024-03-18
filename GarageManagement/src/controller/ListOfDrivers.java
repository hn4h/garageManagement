package controller;

import model.Driver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ListOfDrivers extends ListController<Driver> {


        public ListOfDrivers(){
            super();
            filepath = "src/List/ListOfDrivers";
            nextID = 1;
            this.readData();
        }

        public void readData(){
            list.clear();
            try {
                scanner = new Scanner(new File(filepath));
                while(scanner.hasNextLine()){
                    String line = scanner.nextLine();
                    String data[] = line.split("\\|");
                    list.add(new Driver(Integer.parseInt(data[0]),data[1], data[2], data[3], data[4], data[5],data[6],data[7],Double.parseDouble(data[8])));
                    nextID = Integer.parseInt(data[0]) + 1;
                }
                scanner.close();
            }catch (FileNotFoundException e){
                System.out.println("File not found");
                e.printStackTrace();
            }
        }
    @Override
    public void addItem(Driver item) {
        super.addItem(item);
        try {
            FileWriter writer = new FileWriter(filepath, true);
            scanner = new Scanner(new File(filepath));
            if(!scanner.hasNextLine()){
                writer.write(nextID + "|" + item.getName() + "|" + item.getPhoneNumber() + "|"
                        + item.getNumberId() + "|" + item.getDOB() + "|" + item.getAccommodation() + "|" +
                        item.getDrivingLicense() + "|" + item.getStatus() + "|0");
                nextID ++;
            }
            else{
                writer.write( "\n" + nextID + "|" + item.getName() + "|" + item.getPhoneNumber() + "|"
                        + item.getNumberId() + "|" + item.getDOB() + "|" + item.getAccommodation() + "|" +
                        item.getDrivingLicense() + "|" + item.getStatus() + "|0");
                nextID++;
            }
            scanner.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setItem(int index, Driver item) {
        super.setItem(index, item);
        this.rewriteData();
    }

    @Override
    public void removeItem(Driver index) {
        super.removeItem(index);
        this.rewriteData();
    }
    public void rewriteData(){
        try {
            FileWriter writer = new FileWriter(filepath);
            for(Driver i : list){
                if(list.get(list.size() - 1).getId() == i.getId()){
                    writer.write( i.getId() + "|" + i.getName() + "|" + i.getPhoneNumber() + "|"
                            + i.getNumberId() + "|" + i.getDOB() + "|" + i.getAccommodation() + "|" +
                            i.getDrivingLicense() + "|" + i.getStatus() + "|" + i.getSalary());
                    nextID = i.getId() + 1;
                }else {
                    writer.write( i.getId() + "|" + i.getName() + "|" + i.getPhoneNumber() + "|"
                            + i.getNumberId() + "|" + i.getDOB() + "|" + i.getAccommodation() + "|" +
                            i.getDrivingLicense() + "|" + i.getStatus() + "|" + i.getSalary() + "\n");
                }
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Driver> getList() {
            this.readData();
        return list;
    }
    public int getNextID(){
            return nextID;
    }
}
