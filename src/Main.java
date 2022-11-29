import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        try {

            while (true) {

                ArrayList<String> optionsMenu = new ArrayList<>();
                optionsMenu.add("Encrypt a file with AES");
                optionsMenu.add("Decrypta a file");
                optionsMenu.add("Quit");

                System.out.println("\n Welcome to the AES Encrypt/Decrypt Application \n");

                for (int i = 0; i < optionsMenu.size(); i++) {
                    System.out.println("\t" + (i + 1) + "." + optionsMenu.get(i));

                }

                System.out.println(" \n Please choose an option: ");

                Scanner inputUser = new Scanner(System.in);
                int userOptions = inputUser.nextInt() - 1;
                System.out.println("Your option was: " + optionsMenu.get(userOptions));

                switch (userOptions) {
                    case 0:
                    //     ApartadoA apartadoAs = new ApartadoA();
                    //     apartadoAs.Leer();
                        break;

                    case 1:
                        // ApartadoB apartadoBs = new ApartadoB();
                        // apartadoBs.Leer();
                        break;

                    case 2:
                        System.exit(0);
                }

                continue;
            }

        } catch (InputMismatchException e) {
            System.out.println("Input was not a number");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Menu option was not found");
        }

    }
}
