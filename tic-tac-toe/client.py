import socket

# Constants
HOST = '127.0.0.2'  # Server's hostname or IP address
PORT = 65432        # Server's port

def main():
    client = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    client.connect((HOST, PORT))

    while True:
        message = client.recv(1024).decode('utf-8')
        print(message)
        if 'Your move' in message:
            move = input('Enter your move (row,col): ')
            client.sendall(move.encode('utf-8'))

if __name__ == "__main__":
    main()
