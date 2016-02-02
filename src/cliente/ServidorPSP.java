package cliente;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorPSP {

    ServerSocket serverSocket;
    static Socket newSocket;
    InetSocketAddress addr;
    InputStream is;
    OutputStream os;

    public void conexion() throws IOException {
        System.out.println("Creando socket servidor");
        serverSocket = new ServerSocket();
        System.out.println("Realizando el bind");
        addr = new InetSocketAddress("localhost", 5556);
        serverSocket.bind(addr);
    }

    public void escucha() throws IOException {

        System.out.println("Aceptando conexiones");
        newSocket = serverSocket.accept();
        System.out.println("conexion recibida");

        is = newSocket.getInputStream();
        os = newSocket.getOutputStream();

        String[] mensajes;

        byte[] mensaje = new byte[25];
        is.read(mensaje);
        mensajes = new String(mensaje).split(" ");

        String resultado = "";
        Calculadora calc = new Calculadora(resultado);
        int operacion = Integer.valueOf(mensajes[2]);

        switch (operacion) {
            case 1:
                int suma = Integer.valueOf(mensajes[0]) + Integer.valueOf(mensajes[1]);
                System.out.println(String.valueOf(suma));
                resultado = String.valueOf(suma);
                break;
            case 2:
                int resta = Integer.valueOf(mensajes[0]) - Integer.valueOf(mensajes[1]);
                resultado = String.valueOf(resta);
                break;
            case 3:
                float multi = Integer.valueOf(mensajes[0]) * Integer.valueOf(mensajes[1]);
                resultado = String.valueOf(multi);
                break;
            case 4:
                float divi = Integer.valueOf(mensajes[0]) / Integer.valueOf(mensajes[1]);
                resultado = String.valueOf(divi);
                break;
            default:
                break;
        }

    }

    public void cerrar() throws IOException {
        System.out.println("Cerrando el nuevo socket");
        newSocket.close();
        System.out.println("Cerrandote la vida");
        serverSocket.close();
        System.out.println("Terminado");
    }

}
