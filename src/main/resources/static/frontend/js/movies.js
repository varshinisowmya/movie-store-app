const MOVIES_API = "https://cinevault-app.onrender.com/api/movies";

document.addEventListener('DOMContentLoaded', fetchMovies);

async function fetchMovies() {
    const grid = document.getElementById('movie-grid');
    // Show a smooth loader while fetching
    grid.innerHTML = '<div class="glass-card" style="padding:2rem; grid-column: 1/-1; text-align:center;">Loading blockbusters...</div>';

    try {
        const response = await fetch(MOVIES_API);
        const movies = await response.json();
        renderMovies(movies);
    } catch (error) {
        grid.innerHTML = '<div class="glass-card" style="padding:2rem; grid-column: 1/-1; text-align:center; color: #ff4444;">Failed to load movies. Is the Backend running?</div>';
    }
}

function renderMovies(movies) {
    const grid = document.getElementById('movie-grid');
    if (movies.length === 0) {
        grid.innerHTML = '<p style="grid-column: 1/-1; text-align:center;">No movies in the vault yet!</p>';
        return;
    }

    grid.innerHTML = movies.map(movie => {
        // Professional fallback image logic
        const posterImg = `https://images.unsplash.com/photo-1485846234645-a62644f84728?auto=format&fit=crop&w=400&q=80`;

        return `
        <div class="movie-card glass-card">
            <div style="height: 380px; overflow:hidden; border-radius:15px; position: relative;">
                <img src="${posterImg}" alt="${movie.title}" style="width:100%; height:100%; object-fit:cover;">
                <div style="position: absolute; top: 10px; right: 10px; background: rgba(0,0,0,0.6); padding: 5px 10px; border-radius: 8px; font-size: 0.8rem;">
                    <i class="fas fa-star" style="color: #fbbf24;"></i> 4.5
                </div>
            </div>
            <div style="padding: 1.5rem;">
                <h3 style="margin-bottom: 0.5rem; letter-spacing: 0.5px;">${movie.title}</h3>
                <span style="color: var(--neon-cyan); font-size: 0.75rem; text-transform: uppercase; letter-spacing: 1px; font-weight: 600;">
                    ${movie.genre}
                </span>
                <div style="display: flex; justify-content: space-between; align-items: center; margin-top: 1.5rem;">
                    <span style="font-size: 1.2rem; font-weight: 700; color: #4ade80;">$${movie.pricePerDay}<small style="font-size: 0.6rem; color: var(--text-dim);">/day</small></span>
                    <button onclick="rentNow(${movie.id}, '${movie.title}')" class="btn btn-primary" style="padding: 0.6rem 1.2rem; font-size: 0.9rem;">Rent</button>
                </div>
            </div>
        </div>
    `}).join('');
}

async function rentNow(id, title) {
    const userId = 1;// Assuming demo user
    try {
        const response = await fetch(`api/rentals/${userId}/${id}`, {
            method: 'POST'
        });

        if(response.ok) {
            alert(`Success! "${title}" has been added to your rentals.`);
            window.location.href = "dashboard.html"; // Go see your new rental!
        } else {
            alert("This movie is already rented or unavailable.");
        }
    } catch (error) {
        alert("Connection error. Is the backend awake?");
    }
}