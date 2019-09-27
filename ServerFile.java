package serverfile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerFile {

    public static void main(String[] args) {
        Socket s = null;
        //—лучайное число дл¤ имени файла
        int random_number = 1 + (int) (Math.random() * 5);
        File file = new File(random_number + ".txt");
        //≈сли файла нет то сообщение 
        if (!file.exists()) {
            System.out.println("‘айл не найден");
            //»наче
        } else {
            try {
                ServerSocket server = new ServerSocket(8030);
                //ќжидани¤ присоединени¤ клиента
                s = server.accept();
                PrintStream ps = new PrintStream(s.getOutputStream());
                FileReader fread = new FileReader(file);
                BufferedReader br = new BufferedReader(fread);
                //„итать пока есть данные в файле
                while (br.ready()) {
                    //ќтправить сообщение из файла построчно 
                    String sText = br.readLine();
                    ps.println(sText);
                }
                ps.flush();
                s.close();
                System.out.println("—ообщение успешно доставлено");
            } catch (IOException e) {
                System.out.println("ќшибка: " + e);
            } finally {
                if (s != null) {
                    try {
                        s.close(); // разрыв соединени¤
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}
