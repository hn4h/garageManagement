package controller;

import model.Customer;
import model.Driver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ListOfCustomers extends ListController<Customer> {
    private int nextID;
    public ListOfCustomers(){
        super();
        filepath = "src/List/ListOfCustomers";
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
                list.add(new Customer(Integer.parseInt(data[0]),data[1],data[2]));
                nextID = Integer.parseInt(data[0]) + 1;
            }
            scanner.close();
        }catch (FileNotFoundException e){
            System.out.println("File not found");
            e.printStackTrace();
        }
    }
    @Override
    public void addItem(Customer item) {
        super.addItem(item);
        try {
            FileWriter writer = new FileWriter(filepath, true);
            scanner = new Scanner(new File(filepath));
            if(!scanner.hasNextLine()){
                writer.write(nextID + "|" + item.getName() + "|" + item.getPhoneNumber());
                nextID++;
            }
            else{
                writer.write( "\n" + nextID + "|" + item.getName() + "|" + item.getPhoneNumber());
                nextID++;
            }
            scanner.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setItem(int index, Customer item) {
        super.setItem(index, item);
        this.rewriteData();
    }

    @Override
    public void removeItem(Customer item) {
        super.removeItem(item);
        this.rewriteData();
    }

    public void rewriteData(){
        try {
            FileWriter writer = new FileWriter(filepath);
            for(Customer i : list){
                if(list.get(list.size()-1).getId() == i.getId()){
                    writer.write(i.getId() + "|" + i.getName() + "|" + i.getPhoneNumber());
                    nextID = i.getId() + 1;
                }else {
                    writer.write( i.getId() + "|" + i.getName() + "|" + i.getPhoneNumber()
                            + "\n");
                }

            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Customer> getList() {
        this.readData();
        return list;
    }
    public void clearData(){
        list.clear();
        this.rewriteData();
    }
    public int getNextID(){
        return nextID;
    }
}
