import ftplib

HOSTNAME = "10.1.67.193"  # FTP server address (replace with your actual server IP)
USERNAME = "user"         # FTP username
PASSWORD = "pwd"          # FTP password

# Connect to the FTP server
ftp_server = ftplib.FTP(HOSTNAME)

# Login using the provided username and password
ftp_server.login(USERNAME, PASSWORD)

# Set encoding (optional, but can help with special characters in filenames)
ftp_server.encoding = "utf-8"

# The file you want to upload
filename = "login.txt"

# Open the file in binary mode and upload it
with open(filename, "rb") as file:
    ftp_server.storbinary(f"STOR {filename}", file)

# List the contents of the current directory on the FTP server
ftp_server.dir()

# Quit the FTP session
ftp_server.quit()

