package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public abstract class ListController<T> {
        protected ArrayList<T> list;
        protected String filepath;
        Scanner scanner;

    public ListController(){
            list = new ArrayList<>();
        }
    public void addItem(T item) {
        list.add(item);
    }

    public void setItem(int index, T item){
            list.set(index,item);
    }

    public void removeItem(int index) {
        list.remove(index);
    }


    public ArrayList<T> getList() {
        return list;
    }
}
