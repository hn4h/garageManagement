//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.


import view.Handle;

public class Main {
    public static void main(String[] args) {
        String n = "0123456781";
        if(Handle.handlePhoneNumber(n)){
            System.out.println(n);
        }
    }
}