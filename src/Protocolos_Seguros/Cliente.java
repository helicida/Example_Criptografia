package Protocolos_Seguros;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

/**
 * Created by 46465442z on 13/04/16.
 */
public class Cliente {

    public static void main(String[] args) throws IOException {

        SSLSocketFactory ssf = (SSLSocketFactory) SSLSocketFactory.getDefault();

        SSLSocket cliente = (SSLSocket) ssf.createSocket();
        InetSocketAddress address = new InetSocketAddress("localhost", 5556);
        cliente.connect(address);

        OutputStream os = cliente.getOutputStream();
        String mensage = "HOLA";
        os.write(mensage.getBytes());

        cliente.close();

    }
}


/*
    Instrucciones:
    ----------------------------------------

    Paso 1 - Abrimos un terminal y nos vamos a la carpeta del proyecto con el comando cd

    Pas 2 - Ejecutamos el siguiente comando
     · keytool -genkey -keystore mySrvKeyStore -keyalg RSA

     · java -Djavax.net.ssl.keyStore=mySrvKeyStore -Djavax.net.ssl.trustStrore=mySrvKeyStore -Djavax.net.ssl.keyStorePassword=123456 SecureServer
*/