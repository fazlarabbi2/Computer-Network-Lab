package fozle.github.io;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(2222);
        System.out.println("Server started.");

        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("Client Connected..");

            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

            Object cMsg = null;
            try {
                cMsg = ois.readObject();

                String serverMsg = (String) cMsg;
                serverMsg = serverMsg.toUpperCase();

                oos.writeObject(serverMsg);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            System.out.println("From Client: " + (String) cMsg);
        }
    }
}
