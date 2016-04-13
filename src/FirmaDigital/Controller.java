package FirmaDigital;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.File;
import java.io.IOException;
import java.security.*;

/**
 * Created by 46465442z on 30/03/16.
 */
public class Controller {

    public static final String FITXER_PLA = "/home/46465442z/encriptar/Pt13_Mireia_Fer.java";   // Archivo normal
    public static final String FITXER_SIGNAT = "/home/46465442z/encriptar/FIRMADO.java";        // Archivo firmado
    public static final String PRIVATE_KEY_FILE = "/home/46465442z/encriptar/private.txt";      // private.key
    public static final String PUBLIC_KEY_FILE = "/home/46465442z/encriptar/public.txt";        // public.key


    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, ClassNotFoundException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchPaddingException {

        // Llaves
        KeyPair keyPair = null;
        PrivateKey privateKey = null;
        PublicKey publicKey = null;

        // Archivo
        File f = new File(FITXER_PLA);

        // Comprueba si las llaves están generadas y si no las crea
        if(!Utils.areKeysPresent()) {
            keyPair = Utils.generateKeys();
            publicKey = keyPair.getPublic();
            privateKey = keyPair.getPrivate();
            Utils.guardarClaves(PUBLIC_KEY_FILE, PRIVATE_KEY_FILE, privateKey, publicKey);
        }

        // Arrays
        byte[] digestionat =  Utils.digestiona(f,"MD5");
        byte[] encryptDigestionat = Utils.signar(digestionat, privateKey);

        // Imprimimos la información por la pantalla y escribimos el archivo con firma
        System.out.println("Longitud del fitxer: " + f.length());
        System.out.println("Longitud de la firma: " + encryptDigestionat.length);
        Utils.write(FITXER_SIGNAT, Utils.concatenateByteArrays(Utils.read(f), encryptDigestionat));

        // Ahora comprobamos los dos archivos
        byte[] desencriptado = Utils.decrypt(encryptDigestionat, publicKey);    // Sacamos la key del archivo firmado

        // Hash del archivo original y del firmado
        String hash1 = new String(digestionat,"UTF-8");
        String hash2 = new String (desencriptado, "UTF-8");

        // Imprimimos por pantalla los hash
        System.out.println("\n· Hash del file original: " + hash1
                + "\n· Hash del file firmado: " + hash2);

        // Comparamos
        if (hash1.equals(hash2)) {
            System.out.println("\n El archivo es genuino");
        }
        else
            System.out.println("Te la han metido doblada. El archivo se ha comprometido.");
    }
}