const RENTAL_API = "https://cinevault-app.onrender.com/api/rentals";

document.addEventListener('DOMContentLoaded', fetchMyRentals);

async function fetchMyRentals() {
    // For now, we use User ID 1. In a real app, you'd get this from login.
    const userId = 1;
    try {
        const response = await fetch(`${RENTAL_API}/user/${userId}`);
        const rentals = await response.json();

        const tableBody = document.getElementById('rental-table-body');
        tableBody.innerHTML = rentals.map(r => `
            <tr>
                <td>${r.movie.title}</td>
                <td>${r.rentalDate}</td>
                <td><span style="color: ${r.processed ? '#4ade80' : '#fbbf24'}">
                    ${r.processed ? 'Returned' : 'Active'}
                </span></td>
                <td>
                    ${!r.processed ? `<button class="btn btn-primary" onclick="returnMovie(${r.id})" style="padding:5px 15px; font-size:12px;">Return</button>` : '---'}
                </td>
            </tr>
        `).join('');
    } catch (error) {
        console.log("Error loading rentals", error);
    }
}

async function returnMovie(rentalId) {
    await fetch(`${RENTAL_API}/return/${rentalId}`, { method: 'PUT' });
    location.reload(); // Refresh to see changes
}