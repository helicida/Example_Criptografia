package FirmaDigital;

import java.io.File;
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

    public static byte[] signar(byte[] array, PrivateKey privateKey){

        byte[]encryptDigestionat = null;

        return encryptDigestionat;
    }

    public static void write(File file, byte[] arrayBytes){



    }

    public static KeyPair generateKey() throws NoSuchAlgorithmException {

        kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(2048);
        KeyPair kp = kpg.genKeyPair();
        return kp;
    }

    public static byte[] concatenateByteArrays(byte[] encryptDigestionar){

        byte[]encryptDigestionat = null;

        return encryptDigestionat;
    }

    // Pasar file a bytes
    public static byte[]read(File file) throws IOException {

        byte[]fileToBytes = null;

        fileToBytes = Files.readAllBytes(Paths.get(file.getAbsolutePath()));

        return fileToBytes;
    }
}
