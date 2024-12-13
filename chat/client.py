import socket
import threading

# Client details
alias = input('Choose an alias: ')
client = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
client.connect(('127.0.0.1', 12345))

def receive():
    while True:
        try:
            message = client.recv(1024).decode('utf-8')
            if message == 'ALIAS':
                client.send(alias.encode('utf-8'))
            else:
                print(message)
        except:
            print('Error!')
            client.close()
            break

def write():
    while True:
        message = f'{alias}: {input("")}'
        client.send(message.encode('utf-8'))

receive_thread = threading.Thread(target=receive)
receive_thread.start()

write_thread = threading.Thread(target=write)
write_thread.start()
