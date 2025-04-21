from flask import Flask, jsonify
from flask_cors import CORS
import requests

app = Flask(__name__)
CORS(app)

USER_SERVICE_URL = 'http://localhost:5000'
TODO_SERVICE_URL = 'http://localhost:5001'

@app.route('/api/users', methods=['GET'])
def get_users():
    try:
        response = requests.get(f'{USER_SERVICE_URL}/api/users')
        return jsonify(response.json()), response.status_code
    except requests.RequestException as e:
        return jsonify({"error": "Failed to reach User Service"}), 500

@app.route('/api/users/<username>', methods=['GET'])
def get_user_by_username(username):
    try:
        response = requests.get(f'{USER_SERVICE_URL}/api/users/{username}')
        return jsonify(response.json()), response.status_code
    except requests.RequestException as e:
        return jsonify({"error": "Failed to reach User Service"}), 500

@app.route('/api/todos', methods=['GET'])
def get_todos():
    try:
        response = requests.get(f'{TODO_SERVICE_URL}/api/todos')
        return jsonify(response.json()), response.status_code
    except requests.RequestException as e:
        return jsonify({"error": "Failed to reach Todo Service"}), 500

@app.route('/api/todos/<username>', methods=['GET'])
def get_todos_by_username(username):
    try:
        response = requests.get(f'{TODO_SERVICE_URL}/api/todos/{username}')
        return jsonify(response.json()), response.status_code
    except requests.RequestException as e:
        return jsonify({"error": "Failed to reach Todo Service"}), 500

if __name__ == '__main__':
    app.run(port=5002, debug=True)
