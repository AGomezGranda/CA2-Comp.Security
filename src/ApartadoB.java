import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Objects;
import java.util.Scanner;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class ApartadoB {

  private static SecretKeySpec secretKey;
  private static byte[] key;

  public String txtDecrypt;
  String secret = "";

  public static byte[] decryptedString;
  
  BufferedReader reader;
  String line = null;

  public void DecryptMethod() {
   
    System.out.println(" \n Please write the secret key: ");
    Scanner askSecretKey = new Scanner(System.in);
    secret = askSecretKey.nextLine();

    //Obtain the text we want to decrypt
    try {
        File myObj = new File("ciphertext.txt");
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
          line = myReader.nextLine();
          //System.out.println("Lines detected in file: " + line);
        }
        myReader.close();
      } catch (FileNotFoundException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
      }

    // Scanner input = new Scanner(System.in);
    // System.out.println("Escriba el texto a descifrar: ");
    // txtDecrypt = input.nextLine();
    // System.out.println("El texto a descifrar es " + txtDecrypt);
        
    //PrintWriter for showing the decrypt text
    PrintWriter printWriter = null;

    String decryptedString = ApartadoB.decrypt(line, secret);

    {
      try {
        printWriter = new PrintWriter("plaintext.txt");
        System.out.println("\n The decrypted file is: plaintext.txt");

      } catch (FileNotFoundException e) {
        System.out.println("Unable to locate the fileName: " + e.getMessage());
      }
      //Objects.requireNonNull(printWriter).println("The text to decrypt is: " + txtDecrypt);
      Objects.requireNonNull(printWriter).println(decryptedString);
      printWriter.close();

    }

  }

  //setKey method
  public static void setKey(final String myKey) {
    MessageDigest sha = null;
    try {
      key = myKey.getBytes("UTF-8");
      sha = MessageDigest.getInstance("SHA-1");
      key = sha.digest(key);
      key = Arrays.copyOf(key, 16);
      secretKey = new SecretKeySpec(key, "AES");
    } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
      e.printStackTrace();
    }
  }

  //decrypt method
  public static String decrypt(final String line, final String secret) {
   
    try {
        setKey(secret);
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        return new String(cipher.doFinal(Base64.getDecoder()
          .decode(line)));
      } catch (Exception e) {
        System.out.println("Error while decrypting: " + e.toString());
      }
      return null;
    }

}


