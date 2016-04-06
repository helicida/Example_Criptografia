package FirmaDigital;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;

/**
 * Created by 46465442z on 30/03/16.
 */
public class Controller {

    public static final String PRIVATE_KEY_FILE = "private.key";    // private.key
    public static final String FITXER_PLA = "Sparring.pdf";         // PDF normal
    public static final String FITXER_SIGNAT = "firmat.pdf";        // PDF firmado

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, ClassNotFoundException {

        KeyPair keyPair = null;
        PrivateKey prik = null;

        File f = new File(FITXER_PLA);

        if(!Utils.areKeysPresent()){
            keyPair = Utils.generateKey();
            prik = keyPair.getPrivate();
        }else{
            ObjectInputStream inputStream = null;
            inputStream = new ObjectInputStream(new FileInputStream(PRIVATE_KEY_FILE));
            prik = (PrivateKey) inputStream.readObject();
        }

        byte[] digestionat =  Utils.digestiona(f,"MD5");
        byte[] encryptDigestionat = Utils.signar(digestionat, prik);
        System.out.println("Longitud del fitxer: " + f.length());
        System.out.println("Longitud de la firma: " + encryptDigestionat.length);
        Utils.write(FITXER_SIGNAT, Utils.concatenateByteArrays(Utils.read(f), encryptDigestionat));
    }
}