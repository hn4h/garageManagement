package controller;

import model.Customer;
import model.Driver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ListOfCustomers extends ListController<Customer> {
    public ListOfCustomers(){
        super();
        filepath = "src/List/ListOfCustomers";
        try {
            scanner = new Scanner(new File(filepath));
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                String data[] = line.split("\\|");
                list.add(new Customer(data[0],data[1], Integer.parseInt(data[2])));
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
                writer.write(item.getName() + "|" + item.getPhoneNumber() +
                        "|" + this.getList().size());
            }
            else{
                writer.write( "\n" + item.getName() + "|" + item.getPhoneNumber()
                        + "|" + this.getList().size());
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
    public void removeItem(int index) {
        super.removeItem(index);
        this.rewriteData();
    }

    private void rewriteData(){
        try {
            FileWriter writer = new FileWriter(filepath);
            for(Customer i : list){
                writer.write( i.getName() + "|" + i.getPhoneNumber()
                        + "|" + i.getId() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
