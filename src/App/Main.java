package App;

import controllers.TondeuseCtrl;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        TondeuseCtrl tondeuseCtrl = new TondeuseCtrl();

        try {
            // My approach supposes 2 different types of inputs, a text or an automated test
            System.out.println("How do you want to input data? Write 1 to read from file and 2 to run an automated test with sample data");
            Scanner input = new Scanner(System.in);
            int election = Integer.parseInt(input.nextLine());
            List<String> name = tondeuseCtrl.inputReader(election);
            tondeuseCtrl.performOperations(name);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
