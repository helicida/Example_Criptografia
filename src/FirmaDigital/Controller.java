package FirmaDigital;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;

/**
 * Created by 46465442z on 30/03/16.
 */
public class Controller {

    public static final String PRIVATE_KEY_FILE = "/home/46465442z/encriptar/private.key";      // private.key
    public static final String FITXER_PLA = "/home/46465442z/encriptar/Pt13_Mireia_Fer.java";   // Archivo normal
    public static final String FITXER_SIGNAT = "/home/46465442z/encriptar/FIRMADO.java";        // Archivo firmado

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, ClassNotFoundException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchPaddingException {

        // Llaves
        KeyPair keyPair = null;
        PrivateKey prik = null;

        // Archivo
        File f = new File(FITXER_PLA);

        // Comprueba si las llaves están generadas y si no las crea
        if(!Utils.areKeysPresent()){
            keyPair = Utils.generateKey();
            prik = keyPair.getPrivate();
        }else{
            ObjectInputStream inputStream = null;
            inputStream = new ObjectInputStream(new FileInputStream(PRIVATE_KEY_FILE));
            prik = (PrivateKey) inputStream.readObject();
        }

        // Arrays
        byte[] digestionat =  Utils.digestiona(f,"MD5");
        byte[] encryptDigestionat = Utils.signar(digestionat, prik);

        // Imprimimos la información por la pantalla y escribimos el archivo con firma
        System.out.println("Longitud del fitxer: " + f.length());
        System.out.println("Longitud de la firma: " + encryptDigestionat.length);
        Utils.write(FITXER_SIGNAT, Utils.concatenateByteArrays(Utils.read(f), encryptDigestionat));
    }
}