const express = require('express');
const sqlite3 = require('sqlite3').verbose();
const bodyParser = require('body-parser');
const cors = require('cors');

const app = express();
const db = new sqlite3.Database('stage_event_booking.db');

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));
app.use(cors());

// Root route
app.get('/', (req, res) => {
	res.send('Welcome to the Stage Event Booking API!');
});

// Start the server
app.listen(3000, () => {
	console.log('Server is running on port 3000');
});

// Create a new stage event
app.post('/stage-events', (req, res) => {
	const { name, detail, organizer } = req.body;
	db.run(
    	`INSERT INTO stage_event (name, detail, organizer) VALUES (?, ?, ?)`,
    	[name, detail, organizer],
    	function (err) {
        	if (err) {
            	return res.status(500).json({ error: err.message });
        	}
        	res.status(201).json({ id: this.lastID });
    	}
	);
});

// Get all stage events
app.get('/stage-events', (req, res) => {
	db.all(`SELECT * FROM stage_event`, [], (err, rows) => {
    	if (err) {
        	return res.status(500).json({ error: err.message });
    	}
    	res.json(rows);
	});
});

// Update a stage event
app.put('/stage-events/:id', (req, res) => {
	const { id } = req.params;
	const { name, detail, organizer } = req.body;
	db.run(
    	`UPDATE stage_event SET name = ?, detail = ?, organizer = ? WHERE id = ?`,
    	[name, detail, organizer, id],
    	function (err) {
        	if (err) {
            	return res.status(500).json({ error: err.message });
        	}
        	res.json({ updatedID: id });
    	}
	);
});

// Delete a stage event
app.delete('/stage-events/:id', (req, res) => {
	const { id } = req.params;
	db.run(`DELETE FROM stage_event WHERE id = ?`, id, function (err) {
    	if (err) {
        	return res.status(500).json({ error: err.message });
    	}
    	res.json({ deletedID: id });
	});
});

// Create a new stage event show
app.post('/stage-event-shows', (req, res) => {
	const { start_time, end_time, stage_event_id } = req.body;
	db.run(
    	`INSERT INTO stage_event_show (start_time, end_time, stage_event_id) VALUES (?, ?, ?)`,
    	[start_time, end_time, stage_event_id],
    	function (err) {
        	if (err) {
            	return res.status(500).json({ error: err.message });
        	}
        	res.status(201).json({ id: this.lastID });
    	}
	);
});

// Get all stage event shows
app.get('/stage-event-shows', (req, res) => {
	db.all(`SELECT * FROM stage_event_show`, [], (err, rows) => {
    	if (err) {
        	return res.status(500).json({ error: err.message });
    	}
    	res.json(rows);
	});
});

// Create a new ticket booking
app.post('/ticket-bookings', (req, res) => {
	const { stage_event_show_id, no_of_seats } = req.body;
	db.run(
    	`INSERT INTO ticket_booking (stage_event_show_id, no_of_seats) VALUES (?, ?)`,
    	[stage_event_show_id, no_of_seats],
    	function (err) {
        	if (err) {
            	return res.status(500).json({ error: err.message });
        	}
        	res.status(201).json({ id: this.lastID });
    	}
	);
});

// Get all ticket bookings
app.get('/ticket-bookings', (req, res) => {
	db.all(`SELECT * FROM ticket_booking`, [], (err, rows) => {
    	if (err) {
        	return res.status(500).json({ error: err.message });
    	}
    	res.json(rows);
	});
});







