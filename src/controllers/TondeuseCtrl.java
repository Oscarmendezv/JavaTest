package controllers;

import model.Surface;
import model.Tondeuse;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import service.SurfaceSrv;
import service.TondeuseSrv;
import test.MainTest;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TondeuseCtrl {

    private Surface surface = new Surface();
    private TondeuseSrv tondeuseSrv = new TondeuseSrv();
    private SurfaceSrv surfaceSrv = new SurfaceSrv();

    /**
     * Method used to read the input
     * @param election
     * @return
     */
    public List<String> inputReader(int election) {
        Scanner name;
        // We create both case scenario, with txt and test
        if (election == 1) {
            System.out.print("Enter the file name with extension : ");
            name = new Scanner(System.in);

            // We read the file with the given name
            File file = new File(name.nextLine());
            try {
                name = new Scanner(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else if (election == 2) {
            // We run the test created, that will automatically input the data
            JUnitCore junit = new JUnitCore();
            Result result = junit.run(MainTest.class);
            name = new Scanner(System.in);
        } else {
            System.out.print("The option you selected is not considered. Please, choose a valid option (1 or 2): ");
            name = new Scanner(System.in);
        }
        List<String> inputs = scannerToList(name);
        return inputs;
    }

    /**
     * Performs all the operations needed.
     * @param name
     * @throws Exception
     */
    public void performOperations(List<String> name) throws Exception {
        int num_tondeuse = 0;
        List<String> listOfOrders = new ArrayList<String>();
        // We instantiate the surface dimensions, that we'll receive from the input
        List<Tondeuse> tondeuses = new ArrayList<Tondeuse>();

        // We first check if the file had some missing lines (file should be odd number)
        if(name.size() % 2 == 0){
            throw new Exception("There's some information missing in the file");
        }
        // The first line includes the dimensions of the surface
        String[] dimensions = name.get(0).split(" ");
        surface = surfaceSrv.createPositionMatrix(Integer.parseInt(dimensions[0]), Integer.parseInt(dimensions[1]));

        for (int count = 1; count < name.size(); count++) {
            // Every 2 lines, we create a new object Tondeuse if there are still lines left to read
            if (count % 2 == 1) {
                tondeuses.add(new Tondeuse());
                String[] position = name.get(count).split(" ");

                // We check if position is out of bounds
                if (Integer.parseInt(position[0]) > surface.getSurface_x() || Integer.parseInt(position[1]) > surface.getSurface_y()){
                    throw new Exception("Tondeuse " + num_tondeuse +" position is out of bounds. Stopping the program...");
                }

                // We check the availability of the position
                boolean availability = surfaceSrv.checkAvailability(Integer.parseInt(position[0]), Integer.parseInt(position[1]));
                if (!availability) {
                    tondeuseSrv.setPosition(tondeuses.get(num_tondeuse), Integer.parseInt(position[0]), Integer.parseInt(position[1]), position[2]);
                    surfaceSrv.changeAvailability(Integer.parseInt(position[0]), Integer.parseInt(position[1]));
                } else {
                    throw new Exception("The position for Tondeuse " + num_tondeuse + " is occupied. Stopping the program...");
                }

            } else if (count % 2 == 0) {
                if (checkOperations(name.get(count))) {
                    listOfOrders.add(name.get(count));
                } else {
                    throw new Exception("There is an order given to Tondeuse " + num_tondeuse + " that is not valid. Stopping the program...");
                }
                num_tondeuse++;
            }
        }
        for (Tondeuse tondeuse: tondeuses) {
            startMovement(tondeuse, listOfOrders.get(tondeuse.getId()));
            printFinalPosition(tondeuse);
        }
    }

    /**
     * Transforms all the input from Scanner into a String
     * @param name
     * @return
     */
    private List<String> scannerToList(Scanner name) {
        List<String> inputs = new ArrayList<String>();
        while (name.hasNextLine()) {
            inputs.add(name.nextLine());
        }
        name.close();
        return inputs;
    }

    /**
     * Checks if the String of operations to perform contains any errors
     * @param commands
     * @return
     */
    private boolean checkOperations(String commands) {
        boolean correct = true;
        for (Character letter : commands.toCharArray()) {
            if (!letter.equals(new Character('A')) && !letter.equals(new Character('G')) && !letter.equals(new Character('D'))) {
                correct=false;
            }
        }
        return correct;
    }

    /**
     * Method that performs the movements needed on the Tondeuse
     * @param tondeuse
     * @param commands
     */
    private void startMovement(Tondeuse tondeuse, String commands) {
        for (Character letter : commands.toCharArray()) {
            if (letter.equals(new Character('A'))){
                tondeuseSrv.advance(tondeuse, surfaceSrv);
            } else {
                tondeuseSrv.rotate(letter.toString(), tondeuse);
            }
        }
    }

    /**
     * Prints the final position of every Tondeuse once it has completed it's movement
     * @param tondeuse
     */
    private void printFinalPosition(Tondeuse tondeuse){
        System.out.println(tondeuse.getPosition_x() + " " + tondeuse.getPosition_y() +" " + tondeuse.getNotation_cardinale());
    }
}
