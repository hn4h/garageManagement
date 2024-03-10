package view;
import controller.GarageManagement;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Screen extends JFrame implements ActionListener {
    private GarageManagement manage;
    private JPanel panel;
    private Box selectListBox;
    private Box listTableBox;
    private Box functionBox;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Screen frame = new Screen();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Screen(){
        setTitle("Garage Manage Program");
        setSize(1200,800);
        this.manage = new GarageManagement(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ActionListener action = new GarageManagement(this);
        setLocationRelativeTo(null);

        panel = new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5,5));

        setContentPane(panel);
        panel.setLayout(null);

        selectListBox = Box.createHorizontalBox();
        selectListBox.setBorder(new TitledBorder(null,"List",
                TitledBorder.LEADING, TitledBorder.TOP,null,null));
        selectListBox.setBounds(5,10,500,40);
        panel.add(selectListBox);

        JButton showListBookingsbtn = new JButton("List Of Bookings");
        showListBookingsbtn.addActionListener(this);
        selectListBox.add(showListBookingsbtn);

        JButton showListDriversbtn = new JButton("List Of Drivers");
        showListDriversbtn.addActionListener(this);
        selectListBox.add(showListDriversbtn);

        JButton showListCarsbtn = new JButton("List Of Cars");
        showListCarsbtn.addActionListener(this);
        selectListBox.add(showListCarsbtn);

        JButton showListCustomersbtn = new JButton("List Of Customers");
        showListCustomersbtn.addActionListener(this);
        selectListBox.add(showListCustomersbtn);

        selectListBox = Box.createHorizontalBox();
        panel.add(selectListBox);
        this.showListBookings();

        functionBox = Box.createHorizontalBox();
        functionBox.setBorder(new TitledBorder(null, "Function", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        functionBox.setBounds(5,650,1170,80);
        panel.add(functionBox);
        setLayout(null);
        setVisible(true);
    }
    public void showListBookings(){
        selectListBox.setBorder(new TitledBorder(null,"List Of Bookings",
                TitledBorder.LEADING, TitledBorder.TOP,null,null));
        selectListBox.setBounds(5,50,1170,600);

        selectListBox.setBorder(new TitledBorder(null,"test",
                TitledBorder.LEADING, TitledBorder.TOP,null,null));
        String[] columnNames = {"No", "Date", "Start", "Destination",
                "Distance", "Customer", "Driver", "Car", "Deposit", "Status"};
        JTable table = new JTable();
        table.setModel(new DefaultTableModel(new Object[][]{}, columnNames));
        table.getColumnModel().getColumn(0).setPreferredWidth(5);
        table.getColumnModel().getColumn(1).setPreferredWidth(50);
        table.getColumnModel().getColumn(2).setPreferredWidth(100);
        table.getColumnModel().getColumn(3).setPreferredWidth(100);
        table.getColumnModel().getColumn(4).setPreferredWidth(50);
        table.getColumnModel().getColumn(5).setPreferredWidth(100);
        table.getColumnModel().getColumn(6).setPreferredWidth(100);
        table.getColumnModel().getColumn(7).setPreferredWidth(100);
        table.setRowHeight(20);
        JScrollPane sp = new JScrollPane(table);

        selectListBox.add(sp);
        selectListBox.
        panel.revalidate();
    }
    public void showListDrivers(){
        Box listDriverBox = Box.createHorizontalBox();
        listDriverBox.setBorder(new TitledBorder(null,"List Of Drivers",
                TitledBorder.LEADING, TitledBorder.TOP,null,null));
        listDriverBox.setBounds(5,50,1170,600);
        panel.add(listDriverBox);
        String[] columnNames = {"No", "Name", "id", "DOB",
                "Accommodation", "Driving License", "Status", "Salary"};
        JTable table = new JTable();
        table.setModel(new DefaultTableModel(new Object[][]{}, columnNames));
        table.getColumnModel().getColumn(0).setPreferredWidth(10);
        table.getColumnModel().getColumn(1).setPreferredWidth(150);
        table.getColumnModel().getColumn(2).setPreferredWidth(150);
        table.getColumnModel().getColumn(3).setPreferredWidth(100);
        table.getColumnModel().getColumn(4).setPreferredWidth(200);
        table.getColumnModel().getColumn(5).setPreferredWidth(100);
        table.getColumnModel().getColumn(6).setPreferredWidth(150);
        table.getColumnModel().getColumn(7).setPreferredWidth(150);
        table.setRowHeight(20);
        JScrollPane sp = new JScrollPane(table);

        listDriverBox.add(sp);
        panel.revalidate();
    }
    public void showListCars(){
        Box listDriverBox = Box.createHorizontalBox();
        listDriverBox.setBorder(new TitledBorder(null,"List Of Cars",
                TitledBorder.LEADING, TitledBorder.TOP,null,null));
        listDriverBox.setBounds(5,50,1170,600);
        panel.add(listDriverBox);
        String[] columnNames = {"No", "Number Plate", "Type", "Maintenance Schedule",
                "Car Maker", "Year of Manufacture", "Status"};
        JTable table = new JTable();
        table.setModel(new DefaultTableModel(new Object[][]{}, columnNames));
        table.getColumnModel().getColumn(0).setPreferredWidth(10);
        table.getColumnModel().getColumn(1).setPreferredWidth(150);
        table.getColumnModel().getColumn(2).setPreferredWidth(150);
        table.getColumnModel().getColumn(3).setPreferredWidth(100);
        table.getColumnModel().getColumn(4).setPreferredWidth(200);
        table.getColumnModel().getColumn(5).setPreferredWidth(100);
        table.getColumnModel().getColumn(6).setPreferredWidth(150);
        table.setRowHeight(20);
        JScrollPane sp = new JScrollPane(table);

        listDriverBox.add(sp);
        panel.revalidate();
    }

    public void showListCustomers(){
        Box listDriverBox = Box.createHorizontalBox();
        listDriverBox.setBorder(new TitledBorder(null,"List Of Customers",
                TitledBorder.LEADING, TitledBorder.TOP,null,null));
        listDriverBox.setBounds(5,50,1170,600);
        panel.add(listDriverBox);
        String[] columnNames = {"No", "Name", "Phone Number"};
        JTable table = new JTable();
        table.setModel(new DefaultTableModel(new Object[][]{}, columnNames));
        table.getColumnModel().getColumn(0).setPreferredWidth(10);
        table.getColumnModel().getColumn(1).setPreferredWidth(150);
        table.getColumnModel().getColumn(2).setPreferredWidth(150);
        table.setRowHeight(20);
        JScrollPane sp = new JScrollPane(table);

        listDriverBox.add(sp);
        panel.revalidate();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String cm = e.getActionCommand();
        if(cm.equals("List Of Bookings")){
            this.showListBookings();
        } else if(cm.equals("List Of Drivers")){
            this.showListDrivers();
        } else if(cm.equals("List Of Cars")){
            this.showListCars();
        } else if(cm.equals("List Of Customers")){
            this.showListCustomers();
        }
    }
}
