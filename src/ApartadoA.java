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

  public String txtEncrypt;
  String secret = "ssshhhhhhhhhhh";

  public static byte[] decryptedString;

  public void EncryptMethod() {
   
    //Obtain the text we want to decrypt
    Scanner input = new Scanner(System.in);
    System.out.println("Escriba el texto a cifrar: ");
    txtEncrypt = input.nextLine();
    System.out.println("El texto a cifrar es " + txtEncrypt);
        
    //PrintWriter for showing the decrypt text
    PrintWriter printWriter = null;

    String encryptedString = ApartadoA.encrypt(txtEncrypt, secret);

    {
      try {
        printWriter = new PrintWriter("writerFile2.txt");

      } catch (FileNotFoundException e) {
        System.out.println("Unable to locate the fileName: " + e.getMessage());
      }
      Objects.requireNonNull(printWriter).println("The text to decrypt is: " + txtEncrypt);
      Objects.requireNonNull(printWriter).println("The decpyher text is: " + encryptedString);
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
  public static String encrypt(final String txtEncrypt, final String secret) {
    try {
      setKey(secret);
      Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
      cipher.init(Cipher.ENCRYPT_MODE, secretKey);
      return Base64.getEncoder()
        .encodeToString(cipher.doFinal(txtEncrypt.getBytes("UTF-8")));
    } catch (Exception e) {
      System.out.println("Error while encrypting: " + e.toString());
    }
    return null;
  }

}



