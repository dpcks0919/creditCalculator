import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class creditCalculator {

    public static void main(String[] args) {

        Menu m = new Menu();
        System.out.println("-----시작-----");
        boolean check = true;

        // Using Scanner
//        Scanner scan = new Scanner(System.in);
//
//        while (check) {
//            m.printMenu();
//            String input =  scan.next();
//            check = m.menuChoose(input);
//        }

        while (check) {
            try {
                m.printMenu();
                BufferedReader sbr = new BufferedReader(new InputStreamReader(System.in)); // Using BufferedReader
                String input = sbr.readLine();
                check = m.menuChoose(input);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}


