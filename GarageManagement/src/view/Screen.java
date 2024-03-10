package view;
import controller.GarageManagement;
import model.Booking;

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
     private JButton showListBookingsbtn;
    private JButton showListDriversbtn;
    private JButton showListCarsbtn;
    private JButton showListCustomersbtn;
    private JButton addBookingbtn;
    private JButton removeBookingbtn;
    private JButton updateBookingbtn;
    private JButton searchBookingbtn;
    private JButton showBookingListbtn;
    private JLabel lblSpace;
    private JLabel lblSpace1;
    private JLabel lblSpace2;
    private JLabel lblSpace3;
    public JTable table;
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

        showListBookingsbtn = new JButton("List Of Bookings");
        showListBookingsbtn.addActionListener(this);
        selectListBox.add(showListBookingsbtn);

        showListDriversbtn = new JButton("List Of Drivers");
        showListDriversbtn.addActionListener(this);
        selectListBox.add(showListDriversbtn);

        showListCarsbtn = new JButton("List Of Cars");
        showListCarsbtn.addActionListener(this);
        selectListBox.add(showListCarsbtn);

        showListCustomersbtn = new JButton("List Of Customers");
        showListCustomersbtn.addActionListener(this);
        selectListBox.add(showListCustomersbtn);

        selectListBox = Box.createHorizontalBox();
        panel.add(selectListBox);


        functionBox = Box.createHorizontalBox();
        functionBox.setBorder(new TitledBorder(null, "Function", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        functionBox.setBounds(5,650,1170,80);
        panel.add(functionBox);

        lblSpace = new JLabel("                                       ");
        lblSpace1 = new JLabel("                                                 ");
        lblSpace2 = new JLabel("                                                 ");
        lblSpace3 = new JLabel("                                                 ");
        addBookingbtn = new JButton("Add Booking");
        addBookingbtn.addActionListener(action);

        updateBookingbtn= new JButton("Update Booking");
        updateBookingbtn.addActionListener(action);

        removeBookingbtn = new JButton("Remove Booking");
        removeBookingbtn.addActionListener(action);

        searchBookingbtn = new JButton("Search Booking");
        searchBookingbtn.addActionListener(action);

        showBookingListbtn = new JButton("Show Booking List");
        showBookingListbtn.addActionListener(action);

        this.showListBookings();
        setLayout(null);
        setVisible(true);
    }
    public void showListBookings(){
        selectListBox.removeAll();
        selectListBox.setBorder(new TitledBorder(null,"List Of Bookings",
                TitledBorder.LEADING, TitledBorder.TOP,null,null));
        selectListBox.setBounds(5,50,1170,600);

        String[] columnNames = {"No", "Date", "Start", "Destination",
                "Distance", "ID of Customer", "ID of Driver", "Car", "Deposit", "Status"};
        table = new JTable();
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

        DefaultTableModel modelE = (DefaultTableModel) table.getModel();
        for(Booking i : manage.lBookings.getList()){
            modelE.addRow(new Object[]{i.getIDbooking(), i.getDate(), i.getStart(),
            i.getDestination(), i.getDistance(), i.getCustomer().getId(), i.getDriver().getId(),
            i.getCar().getNumberPlates(), i.getIsDeposit(), i.getStatus()});
        }

        functionBox.add(addBookingbtn);
        functionBox.add(lblSpace1);
        functionBox.add(updateBookingbtn);
        functionBox.add(lblSpace2);
        functionBox.add(removeBookingbtn);
        functionBox.add(lblSpace3);
        functionBox.add(searchBookingbtn);
        functionBox.add(lblSpace);
        functionBox.add(showBookingListbtn);
        selectListBox.add(sp);
        panel.revalidate();
    }
    public void showListDrivers(){
        selectListBox.removeAll();
        selectListBox.setBorder(new TitledBorder(null,"List Of Drivers",
                TitledBorder.LEADING, TitledBorder.TOP,null,null));
        String[] columnNames = {"No", "Name", "id", "DOB",
                "Accommodation", "Driving License", "Status", "Salary"};
        table = new JTable();
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

        selectListBox.add(sp);
        panel.revalidate();
    }
    public void showListCars(){
        selectListBox.removeAll();
        selectListBox.setBorder(new TitledBorder(null,"List Of Cars",
                TitledBorder.LEADING, TitledBorder.TOP,null,null));

        String[] columnNames = {"No", "Number Plate", "Type", "Maintenance Schedule",
                "Car Maker", "Year of Manufacture", "Status"};
        table = new JTable();
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



        selectListBox.add(sp);
        panel.revalidate();
    }

    public  void showListCustomers(){
        selectListBox.removeAll();
        selectListBox.setBorder(new TitledBorder(null,"List Of Customers",
                TitledBorder.LEADING, TitledBorder.TOP,null,null));

        String[] columnNames = {"No", "Name", "Phone Number"};
        table = new JTable();
        table.setModel(new DefaultTableModel(new Object[][]{}, columnNames));
        table.getColumnModel().getColumn(0).setPreferredWidth(10);
        table.getColumnModel().getColumn(1).setPreferredWidth(150);
        table.getColumnModel().getColumn(2).setPreferredWidth(150);
        table.setRowHeight(20);
        JScrollPane sp = new JScrollPane(table);


        selectListBox.add(sp);
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
