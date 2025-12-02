public class UDPEchoClient {
    
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPEchoClient {

    private final String serverHost;
    private final int serverPort;

    public UDPEchoClient(String serverHost, int serverPort) {
        this.serverHost = serverHost;
        this.serverPort = serverPort;
    }

    public String sendAndReceive(String message) throws IOException {
        try (DatagramSocket socket = new DatagramSocket()) {
            byte[] sendData = message.getBytes("UTF-8");
            InetAddress serverAddr = InetAddress.getByName(serverHost);
            DatagramPacket sendPacket =
                    new DatagramPacket(sendData, sendData.length, serverAddr, serverPort);
            socket.send(sendPacket);

            byte[] recvBuf = new byte[1024];
            DatagramPacket recvPacket = new DatagramPacket(recvBuf, recvBuf.length);
            socket.receive(recvPacket);

            return new String(recvPacket.getData(), 0, recvPacket.getLength(), "UTF-8");
        }
    }

    public void runInteractive() {
        System.out.println("UDP Echo Client to " + serverHost + ":" + serverPort);

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                System.out.print("입력(exit 종료): ");
                String msg = br.readLine();
                if (msg == null || msg.equalsIgnoreCase("exit")) break;

                String echo = sendAndReceive(msg);
                System.out.println("서버 에코: " + echo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        UDPEchoClient client = new UDPEchoClient("192.168.1.106", 5000);
        client.runInteractive();
    }
}
