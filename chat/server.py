import socket
import threading

# Server details
host = '127.0.0.1'  # Localhost
port = 12345        # Port number

server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
server.bind((host, port))
server.listen()

clients = []
aliases = []

def broadcast(message):
    for client in clients:
        client.send(message)

def handle_client(client):
    while True:
        try:
            message = client.recv(1024)
            broadcast(message)
        except:
            index = clients.index(client)
            clients.remove(client)
            client.close()
            alias = aliases[index]
            broadcast(f'{alias} has left the chat!'.encode('utf-8'))
            aliases.remove(alias)
            break

def receive():
    while True:
        client, address = server.accept()
        print(f'Connection established with {str(address)}')

        client.send('ALIAS'.encode('utf-8'))
        alias = client.recv(1024).decode('utf-8')
        aliases.append(alias)
        clients.append(client)

        print(f'The alias of this client is {alias}'.encode('utf-8'))
        broadcast(f'{alias} has joined the chat!'.encode('utf-8'))
        client.send('You are now connected!'.encode('utf-8'))

        thread = threading.Thread(target=handle_client, args=(client,))
        thread.start()

print('Server is listening...')
receive()
