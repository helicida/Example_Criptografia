package FirmaDigital;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;

/**
 * Created by 46465442z on 30/03/16.
 */
public class Utils {

    public static byte[] digestiona(File file, String algoritme) throws IOException, NoSuchAlgorithmException {

        MessageDigest md = MessageDigest.getInstance(algoritme);
        md.update(Files.readAllBytes(Paths.get(file.getAbsolutePath())));
        byte[] digest = md.digest();

        return digest;
    }

    public static byte[] signar(byte[] array, PrivateKey privateKey){

        byte[]encryptDigestionat = null;

        return encryptDigestionat;
    }

    public static void write(File file, byte[] arrayBytes){



    }

    public static byte[] concatenateByteArrays(byte[] encryptDigestionar){

        byte[]encryptDigestionat = null;

        return encryptDigestionat;
    }

    public static byte[]read(File file){

        byte[]fileToBytes = null;

        return fileToBytes;
    }
}
