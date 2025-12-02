import socket

class UDPEchoClient:
    def __init__(self, server_host="192.168.1.106", server_port=5000):
        self.server_host = server_host
        self.server_port = server_port
        self.sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)

    def send_and_receive(self, message):
        self.sock.sendto(message.encode("utf-8"), (self.server_host, self.server_port))
        data, _ = self.sock.recvfrom(1024)
        return data.decode("utf-8")

    def run_interactive(self):
        print(f"UDP Echo Client to {self.server_host}:{self.server_port}")
        try:
            while True:
                msg = input("입력(exit 종료): ")
                if msg.lower() == "exit":
                    break
                echo = self.send_and_receive(msg)
                print(f"서버 에코: {echo}")
        finally:
            self.sock.close()
            print("Socket closed")

if __name__ == "__main__":
    client = UDPEchoClient("192.168.1.106", 5000)
    client.run_interactive()
