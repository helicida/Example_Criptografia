package Protocolos_Seguros;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;

/**
 * Created by 46465442z on 13/04/16.
 */
public class Server {

    public static void main(String[] args) throws IOException {

        System.out.println("Obtenint factoría del servidor");

        SSLServerSocketFactory ssf = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
        System.out.println("Creant socket servidor");

        SSLServerSocket sss = (SSLServerSocket) ssf.createServerSocket();
        System.out.println("Binding!");

        InetSocketAddress address = new InetSocketAddress("0.0.0.0", 5556);
        sss.bind(address);

        SSLSocket ss = (SSLSocket) sss.accept();

        System.out.println("Rebut");

        InputStream is = ss.getInputStream();
        byte[] men = new byte[25];
        is.read(men);

        System.out.println("Missatge rebut!: "+new String(men));

        System.out.println("Tancant");

        sss.close();

        System.out.println("Acabat");
    }
}
