import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class broadcast_client {
    public static void main(String[] args) {
        final int PORT = 5000;

        try (DatagramSocket socket = new DatagramSocket();
             Scanner scanner = new Scanner(System.in)) {

            socket.setBroadcast(true);
            InetAddress broadcastAddr = InetAddress.getByName("255.255.255.255");

            System.out.println("[Java Client] 브로드캐스트 메시지 전송 시작");

            while (true) {
                System.out.print("보낼 메시지 입력: ");
                String message = scanner.nextLine();

                byte[] data = message.getBytes("UTF-8");
                DatagramPacket packet =
                        new DatagramPacket(data, data.length, broadcastAddr, PORT);

                socket.send(packet);
                System.out.println("[송신] " + message);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
