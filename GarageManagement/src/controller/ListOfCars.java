package controller;

import model.Car;
import model.Driver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ListOfCars extends ListController<Car>{
    public ListOfCars(){
        super();
        filepath = "src/List/ListOfCars";
        this.readData();
    }
    public void readData(){
        try {
            scanner = new Scanner(new File(filepath));
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                String data[] = line.split("\\|");
                list.add(new Car(data[0],data[1], data[2],data[3], Integer.parseInt(data[4]), data[5]));
            }
            scanner.close();
        }catch (FileNotFoundException e){
            System.out.println("File not found");
            e.printStackTrace();
        }
    }
    @Override
    public void addItem(Car item) {
        super.addItem(item);
        try {
            FileWriter writer = new FileWriter(filepath, true);
            scanner = new Scanner(new File(filepath));
            if(!scanner.hasNextLine()){
                writer.write( item.getNumberPlates() + "|" + item.getType() + "|"
                    + item.getMaintenanceSchedule() + "|" + item.getCompanyCar() + "|" + item.getYear() +
                    "|" + item.getStatus());
            }else {
                writer.write( "\n" + item.getNumberPlates() + "|" + item.getType() + "|"
                        + item.getMaintenanceSchedule() + "|" + item.getCompanyCar() + "|" + item.getYear() +
                        "|" + item.getStatus());
            }
            scanner.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setItem(int index, Car item) {
        super.setItem(index, item);
        this.rewriteData();
    }

    @Override
    public void removeItem(Car item) {
        super.removeItem(item);
        this.rewriteData();
    }
    private void rewriteData(){
        try {
            FileWriter writer = new FileWriter(filepath);
            for(Car i : list){
                writer.write( i.getNumberPlates() + "|" + i.getType() + "|"
                        + i.getMaintenanceSchedule() + "|" + i.getCompanyCar() + "|" +
                        i.getYear() + "|" + i.getStatus() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Car> getList() {
        this.readData();
        return super.getList();
    }
}
