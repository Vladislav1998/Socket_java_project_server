package sample;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        String myLine="";
        int port = 8788;
        try {
            ServerSocket ss = new ServerSocket(port,3);
            System.out.println("Waiting for a client...\n");
            while (true) {
                Socket socket = ss.accept(); // заставляем сервер ждать подключений и выводим сообщение когда кто-то связался с сервером
                String address=socket.getLocalAddress().toString();
                if(address.contains(".2")){
                    // Берем входной и выходной потоки сокета, теперь можем получать и отсылать данные клиенту.
                    OutputStream sout = socket.getOutputStream();
                    // Конвертируем потоки в другой тип, чтоб легче обрабатывать текстовые сообщения.
                    DataOutputStream out = new DataOutputStream(sout);
                    out.writeUTF(myLine); // отсылаем клиенту обратно ту самую строку текста.
                    out.flush(); // заставляем поток закончить передачу данных.
                }
                else {
                    System.out.println("Client \"adminDB\" made a change!");
                    // Берем входной и выходной потоки сокета, теперь можем получать и отсылать данные клиенту.
                    InputStream sin = socket.getInputStream();
                    // Конвертируем потоки в другой тип, чтоб легче обрабатывать текстовые сообщения.
                    DataInputStream in = new DataInputStream(sin);
                    myLine = in.readUTF();
                    System.out.println(myLine + "\n-------------------------------");
                    System.out.println();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
