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
  String secret = "ssshhhhhhhhhhh!!!!";

  public static byte[] decryptedString;

  public void DecryptMethod() {
   
    //Obtain the text we want to decrypt
    Scanner input = new Scanner(System.in);
    System.out.println("Escriba el texto a descifrar: ");
    txtDecrypt = input.nextLine();
    System.out.println("El texto a descifrar es " + txtDecrypt);
        
    //PrintWriter for showing the decrypt text
    PrintWriter printWriter = null;

    String decryptedString = ApartadoB.decrypt(txtDecrypt, secret);

    {
      try {
        printWriter = new PrintWriter("writerFile3.txt");

      } catch (FileNotFoundException e) {
        System.out.println("Unable to locate the fileName: " + e.getMessage());
      }
      Objects.requireNonNull(printWriter).println("The text to decrypt is: " + txtDecrypt);
      Objects.requireNonNull(printWriter).println("The decpyher text is: " + decryptedString);
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
  public static String decrypt(final String txtDecrypt, final String secret) {
   
    try {
        setKey(secret);
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        return new String(cipher.doFinal(Base64.getDecoder()
          .decode(txtDecrypt)));
      } catch (Exception e) {
        System.out.println("Error while decrypting: " + e.toString());
      }
      return null;
    }

}


  // public String encrypt(String textoACifrar, int shiftKey) {
  // textoACifrar = textoACifrar.toLowerCase();
  // char replaceVal;
  // for (int ii = 0; ii < textoACifrar.length(); ii++) {
  // int charPosition = ALPHABET.indexOf(textoACifrar.charAt(ii));
  // if (charPosition == -1) {
  // replaceVal = 32;
  // } else {
  // int keyVal = (charPosition - shiftKey) % 26;
  // if (keyVal < 0) {
  // keyVal = ALPHABET.length() + keyVal;
  // }
  // replaceVal = ALPHABET.charAt(keyVal);
  // }
  // message += replaceVal;
  // }
  // return message;
  // }

