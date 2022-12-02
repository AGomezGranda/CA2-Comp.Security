import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Objects;
import java.util.Scanner;


public class ApartadoA {
    public static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    public String textoACifrar;
    public int shiftkey = (int)(Math.random()*10+1);
    String message = "";

    public void IngresarTexto(){
       Scanner input = new Scanner (System.in);
       System.out.println("Escriba el texto a cifrar: ");
       textoACifrar = input.nextLine(); 
       System.out.println("El texto a cifrar es " + textoACifrar);
    }

    public String encrypt(String textoACifrar, int shiftKey) {
        textoACifrar = textoACifrar.toLowerCase();
        char replaceVal;
        for (int ii = 0; ii < textoACifrar.length(); ii++) {
            int charPosition = ALPHABET.indexOf(textoACifrar.charAt(ii));
           if (charPosition == -1) {
                replaceVal = 32;
           } else {
                int keyVal = (charPosition - shiftKey) % 26;
                if (keyVal < 0) {
                    keyVal = ALPHABET.length() + keyVal;
                }
                replaceVal = ALPHABET.charAt(keyVal);
           }
            message += replaceVal;
        }
        return message;
    }

    public void writeFile(){
            PrintWriter printWriter = null;
            {
                try {
                    printWriter = new PrintWriter("writerFile.txt");
                } catch (FileNotFoundException e) {
                    System.out.println("Unable to locate the fileName: " + e.getMessage());
                }
                Objects.requireNonNull(printWriter).println("El texto a cifrar es " + textoACifrar);
                Objects.requireNonNull(printWriter).println("El texto cifrado es " + message);
                Objects.requireNonNull(printWriter).println("La clave es desplazar hacia la izquierda "  + shiftkey + "posiciones");
                printWriter.close();
            }
    }
 }



