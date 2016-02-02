package cliente;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cliente extends Thread {

    static Cliente cli;
    static int cliente = 0;

    public Cliente(String name) {
        super(name);
    }

    Cliente() {
    }

    @Override
    public void run() {
        try {
            System.out.println("Creando socket cliente");
            Socket clienteSocket = new Socket();
            System.out.println("Estableciendo la conexion");
            InetSocketAddress addr = new InetSocketAddress("localhost", 5556);
            Conectarse con = new Conectarse(clienteSocket, addr);
            con.setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) throws IOException {
        ServidorPSP server = new ServidorPSP();
        server.conexion();
        cli = new Cliente("cliente " + 1);
        cli.start();
        while (cliente <= 6) {

            if (cliente == 1) {
                Cliente cli2 = new Cliente("cliente " + 2);
                
                cli2.start();
            } else if (cliente == 2) {
                cli = new Cliente("cliente " + 3);
                cli.start();

            } else if (cliente == 3) {
                cli = new Cliente("cliente " + 4);
                cli.start();

            } else if (cliente == 4) {
                cli = new Cliente("cliente " + 5);
                cli.start();

            } else if (cliente == 5) {
                cli = new Cliente("cliente " + 6);
                cli.start();
            } else {
                System.out.println("Servidor Saturado");
            }
            server.escucha();
        }
        server.cerrar();
    }

}
