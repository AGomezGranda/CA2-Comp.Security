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

public class ApartadoA {

  private static SecretKeySpec secretKey;
  private static byte[] key;

  public String txtFile; //Text file to encrypt, writeFile.txt
  String secret = "ssshhhhhhhhhhh!!!!";  //secret key, now hardcoded in future random generate

  public static byte encryptedString;

  //BufferedReader reader;
  String line = "";

  public void EncryptMethod() {

    System.out.println(" \n Please write the file name: ");
    Scanner fileName = new Scanner(System.in);
    String userOptions = fileName.nextLine();

      //Read the text file we want to decrypt    
      try {
        File myObj = new File(userOptions);
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
          line = myReader.nextLine();
          System.out.println("Lines in file detected: " + line);
        }
        myReader.close();
      } catch (FileNotFoundException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
      }
    

    //Call the encryptString method, with parameters line and secret
   //call encrypt method
    String encryptedString = ApartadoA.encrypt(line, secret);

    // System.out.println("aaa - The secret key is " + secret);
    //PrintWriter for showing the decrypt text
    PrintWriter printWriter = null;
    {
      try {
        printWriter = new PrintWriter("ciphertext.txt");
        System.out.println("The encrypt file is ciphertext.txt");
        System.out.println("The secret key is " + secret);

      } catch (FileNotFoundException e) {
        System.out.println("Unable to locate the fileName: " + e.getMessage());
      }
      //Objects.requireNonNull(printWriter).println("The text to encrypt is: " + txtEncrypt);
      Objects.requireNonNull(printWriter).println(encryptedString);
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
  public static String encrypt(final String txtEncrypt, final String secret){
    try {
      setKey(secret);
      Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
      cipher.init(Cipher.ENCRYPT_MODE, secretKey);
      return Base64.getEncoder()
        .encodeToString(cipher.doFinal(txtEncrypt.getBytes("UTF-8")));
    } catch (Exception e) {
      System.out.println("Error while encrypting: " + e.toString());
    }

    //no muestra por pantalla System.out.println("ccc - The secret key is " + secret);
    return null;
  }

}





