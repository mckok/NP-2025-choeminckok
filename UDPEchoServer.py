import socket

class UDPEchoServer:
    def __init__(self, host="0.0.0.0", port=5000):
        self.host = host
        self.port = port
        self.sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)

    def start(self):
        self.sock.bind((self.host, self.port))
        print(f"UDP Echo Server started on {self.host}:{self.port}")

        while True:
            data, addr = self.sock.recvfrom(1024)
            message = data.decode("utf-8")
            print(f"[RECV] {addr} → {message}")
            self.sock.sendto(data, addr)
            print(f"[SEND] Echoed → {addr}")

if __name__ == "__main__":
    server = UDPEchoServer()
    server.start()
