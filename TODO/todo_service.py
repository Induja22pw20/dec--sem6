from flask import Flask, jsonify, request
from flask_cors import CORS
import uuid

app = Flask(__name__)
CORS(app)

# In-memory todo storage
todos = [
    {"id": str(uuid.uuid4()), "username": "induja", "task": "Complete project", "completed": False},
    {"id": str(uuid.uuid4()), "username": "veda", "task": "Write documentation", "completed": True},
    {"id": str(uuid.uuid4()), "username": "harsh", "task": "Team meeting", "completed": False}
]

@app.route('/api/todos', methods=['GET'])
def get_todos():
    return jsonify(todos)

@app.route('/api/todos/<username>', methods=['GET'])
def get_todos_by_username(username):
    user_todos = [todo for todo in todos if todo['username'] == username]
    return jsonify(user_todos)

if __name__ == '__main__':
    app.run(port=5001, debug=True)
