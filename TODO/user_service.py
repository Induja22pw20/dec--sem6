from flask import Flask, jsonify, request
from flask_cors import CORS
import uuid

app = Flask(__name__)
CORS(app)

# In-memory user storage
users = [
    {"id": str(uuid.uuid4()), "username": "john_doe", "email": "john@example.com"},
    {"id": str(uuid.uuid4()), "username": "jane_smith", "email": "jane@example.com"}
]

@app.route('/api/users', methods=['GET'])
def get_users():
    return jsonify(users)

@app.route('/api/users/<username>', methods=['GET'])
def get_user_by_username(username):
    user = next((u for u in users if u['username'] == username), None)
    if user:
        return jsonify(user)
    return jsonify({"error": "User not found"}), 404

if __name__ == '__main__':
    app.run(port=5000, debug=True)
