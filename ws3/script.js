async function fetchEventSummary() {
	try {
    	const response = await fetch('http://localhost:3000/stage-events');
    	const events = await response.json();
    	const tbody = document.querySelector('#event-summary tbody');

    	for (const event of events) {
        	const responseShows = await fetch(`http://localhost:3000/stage-event-shows?stage_event_id=${event.id}`);
        	const shows = await responseShows.json();

        	for (const show of shows) {
            	const responseBookings = await fetch(`http://localhost:3000/ticket-bookings?stage_event_show_id=${show.id}`);
            	const bookings = await responseBookings.json();
            	const ticketsBooked = bookings.reduce((total, booking) => total + booking.no_of_seats, 0);

            	const row = document.createElement('tr');
            	row.innerHTML = `
                	<td>${event.name}</td>
                	<td>${event.detail}</td>
                	<td>${event.organizer}</td>
                	<td>${new Date(show.start_time).toLocaleString()}</td>
                	<td>${new Date(show.end_time).toLocaleString()}</td>
                	<td>${ticketsBooked}</td>
            	`;
            	tbody.appendChild(row);
        	}
    	}
	} catch (error) {
    	console.error('Error fetching event summary:', error);
    	const tbody = document.querySelector('#event-summary tbody');
    	tbody.innerHTML = '<tr><td colspan="6">Failed to load data. Please try again later.</td></tr>';
	}
}

fetchEventSummary();
