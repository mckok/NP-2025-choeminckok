import socket

PORT = 5000
BUFF = 1024

# UDP 소켓 생성
sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)

# 모든 네트워크 인터페이스에서 수신하도록 설정
sock.bind(("", PORT))

print("[Python Receiver] 브로드캐스트 수신 대기 (Ubuntu)")

while True:
    data, addr = sock.recvfrom(BUFF)
    print(f"[수신] {addr} → {data.decode('utf-8', errors='ignore')}")
