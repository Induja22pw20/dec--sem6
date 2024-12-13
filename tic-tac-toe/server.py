import socket
import threading

# Constants
HOST = '127.0.0.2'  # Localhost
PORT = 65432        # Arbitrary non-privileged port

# Store the current game sessions
sessions = []

def handle_session(player1, player2, player1_conn, player2_conn):
    board = [[' ' for _ in range(3)] for _ in range(3)]
    current_player = player1
    connections = {player1: player1_conn, player2: player2_conn}
    tokens = {player1: 'X', player2: 'O'}
    running = True

    def print_board():
        board_str = '\n'.join([' | '.join(row) for row in board])
        return board_str

    def check_winner():
        for row in board:
            if row[0] == row[1] == row[2] and row[0] != ' ':
                return row[0]
        for col in range(3):
            if board[0][col] == board[1][col] == board[2][col] and board[0][col] != ' ':
                return board[0][col]
        if board[0][0] == board[1][1] == board[2][2] and board[0][0] != ' ':
            return board[0][0]
        if board[0][2] == board[1][1] == board[2][0] and board[0][2] != ' ':
            return board[0][2]
        return None

    while running:
        for player in [player1, player2]:
            conn = connections[player]
            conn.sendall(print_board().encode('utf-8'))
            conn.sendall(f"Your move, {player} (format: row,col): ".encode('utf-8'))
            move = conn.recv(1024).decode('utf-8')
            row, col = map(int, move.split(','))
            if board[row][col] == ' ':
                board[row][col] = tokens[player]
                winner = check_winner()
                if winner:
                    conn.sendall(f"{winner} wins!\n".encode('utf-8'))
                    connections[player1].sendall(f"{winner} wins!\n".encode('utf-8'))
                    connections[player2].sendall(f"{winner} wins!\n".encode('utf-8'))
                    running = False
                    break
                if all(cell != ' ' for row in board for cell in row):
                    connections[player1].sendall("It's a draw!\n".encode('utf-8'))
                    connections[player2].sendall("It's a draw!\n".encode('utf-8'))
                    running = False
                    break

def receive():
    while True:
        if len(sessions) % 2 == 0:  # Waiting for a new pair
            conn, addr = server.accept()
            print(f"Player 1 connected from {addr}")
            player1 = addr
            conn.sendall("You are Player 1 with token 'X'".encode('utf-8'))
            sessions.append((player1, conn))
        else:  # Connecting to an existing pair
            conn, addr = server.accept()
            print(f"Player 2 connected from {addr}")
            player2 = addr
            conn.sendall("You are Player 2 with token 'O'".encode('utf-8'))
            player1, player1_conn = sessions.pop()
            sessions.append((player1, player2, player1_conn, conn))
            thread = threading.Thread(target=handle_session, args=(player1, player2, player1_conn, conn))
            thread.start()

# Setup server
server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
server.bind((HOST, PORT))
server.listen()
print('Server is listening...')

receive()
