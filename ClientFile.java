package clientfile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientFile {

    public static void main(String[] args) {
        Socket socket = null;
        try {
            socket = new Socket("localhost", 8030);
            BufferedReader br
                    = new BufferedReader(
                            new InputStreamReader(socket.getInputStream()));
            // Получить сообщение
            System.out.println("Полученное сообщение: ");
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            socket.close();
        } catch (IOException e) {
            System.err.println("Ошибка: " + e);

        } finally {
            if (socket != null) {
                try { // разрыв соединения                    
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
