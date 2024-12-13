from pyftpdlib.authorizers import DummyAuthorizer
from pyftpdlib.handlers import FTPHandler
from pyftpdlib.servers import FTPServer

FTP_PORT = 8087  # Use a port greater than 1024 to avoid needing root privileges
FTP_USER = "user"
FTP_PASSWORD = "pwd"
FTP_DIRECTORY = "/run/user/1001/gvfs/smb-share:server=10.1.67.156,share=22pw20/dec"  # Change this to a valid directory on your system

def main():
    # Set up the authorizer to handle FTP permissions
    authorizer = DummyAuthorizer()

    # Add user with access to FTP_DIRECTORY
    authorizer.add_user(FTP_USER, FTP_PASSWORD, FTP_DIRECTORY, perm="elradfmw")

    # Set up the FTP handler
    handler = FTPHandler
    handler.authorizer = authorizer
    handler.banner = "pyftpdlib-based FTP server ready."

    # Set the server address (bind to all interfaces)
    address = ("0.0.0.0", FTP_PORT)  # Listen on all interfaces on port 8087

    # Set up the server
    server = FTPServer(address, handler)

    # Limit the number of connections
    server.max_cons = 256
    server.max_cons_per_ip = 5

    # Start the FTP server
    print(f"FTP server running on {address[0]}:{FTP_PORT}")
    server.serve_forever()

if __name__ == "__main__":
    main()

