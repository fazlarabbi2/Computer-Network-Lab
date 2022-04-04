package fozle.github.io;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class client {
    public static void main(String[] args) throws IOException {
        System.out.println("Client Started..");
        Socket socket = new Socket("localhost", 2222);
        System.out.println("Client Connected..");

        while (true) {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            Scanner sc = new Scanner(System.in);
            String message = sc.nextLine();

            oos.writeObject(message);

            try {
                Object fromServer = ois.readObject();
                System.out.println("Frome server: " + (String) fromServer);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
