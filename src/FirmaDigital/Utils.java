package FirmaDigital;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.*;

/**
 * Created by 46465442z on 30/03/16.
 */
public class Utils {

    // Genera el par de llaves
    static KeyPairGenerator kpg;

    // Comprueba si las llaves existen en el directorio y devuelve un booleano que lo indica
    public static boolean areKeysPresent(){

        if(kpg == null){
            return false;
        }

        return true;
    }

    // Se introduce un string con el tipo de encripatción y el fichero a encriptar y devuelve el hash del archivo
    public static byte[] digestiona(File file, String algoritme) throws IOException, NoSuchAlgorithmException {

        MessageDigest md = MessageDigest.getInstance(algoritme);
        md.update(read(file));
        byte[] digest = md.digest();
        return digest;
    }

    // Firmar un archivo
    public static byte[] signar(byte[] array, PrivateKey privateKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {

        byte[]encryptDigestionat = null;
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        encryptDigestionat =  cipher.doFinal(array);

        return encryptDigestionat;
    }

    // desencripta un archivo
    public static byte[] decrypt(byte[] encrypted, PublicKey publicKey) throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, IllegalBlockSizeException {

        byte[]decryptDigestionat = null;
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        decryptDigestionat = cipher.doFinal(encrypted);

        return decryptDigestionat;
    }

    // Escribimos un archivo
    public static void write(String ruta, byte[] arrayBytes) throws IOException {

        FileOutputStream fos = new FileOutputStream(ruta);
        fos.write(arrayBytes);
        fos.close();
    }

    // Generamos la key
    public static KeyPair generateKey() throws NoSuchAlgorithmException {

        kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(1024);
        KeyPair kp = kpg.genKeyPair();
        return kp;
    }

    // Concatenamos los dos byte
    public static byte[] concatenateByteArrays(byte[] array1, byte[] array2){

        // Concatenamos los arrays
        byte[]encryptDigestionat = new byte[array1.length + array2.length];
        System.arraycopy(array1, 0, encryptDigestionat, 0, array1.length);
        System.arraycopy(array2, 0, encryptDigestionat, array1.length, array2.length);

        return encryptDigestionat;
    }

    // Pasar file a bytes
    public static byte[]read(File file) throws IOException {

        byte[]fileToBytes = null;

        fileToBytes = Files.readAllBytes(Paths.get(file.getAbsolutePath()));

        return fileToBytes;
    }

    public static byte[] extraerHash(File file, String algoritme) throws NoSuchAlgorithmException, IOException {

        MessageDigest messageDigest = MessageDigest.getInstance(algoritme);
        messageDigest.update(Files.readAllBytes(Paths.get(file.getAbsolutePath())));
        return messageDigest.digest();
    }
}
