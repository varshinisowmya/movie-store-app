const NOTE_API = "https://cinevault-app.onrender.com/api/notifications";

document.addEventListener('DOMContentLoaded', fetchNotifications);

async function fetchNotifications() {
    const userId = 1; // Assuming User ID 1 for testing
    try {
        const response = await fetch(`${NOTE_API}/${userId}`);
        const notes = await response.json();

        const list = document.getElementById('notifications-list');

        if (notes.length === 0) {
            list.innerHTML = `<div class="glass-card" style="padding: 2rem; text-align:center;">Your inbox is empty.</div>`;
            return;
        }

        list.innerHTML = notes.reverse().map(n => `
            <div class="glass-card" style="padding: 1.5rem; display: flex; align-items: center; gap: 1.5rem; border-left: 4px solid var(--neon-purple);">
                <div style="background: rgba(168, 85, 247, 0.2); padding: 15px; border-radius: 12px;">
                    <i class="fas fa-info-circle" style="color: var(--neon-purple);"></i>
                </div>
                <div style="flex-grow: 1;">
                    <p style="font-weight: 500;">${n.message}</p>
                    <small style="color: var(--text-dim); font-size: 0.7rem;">${new Date(n.createdAt).toLocaleString()}</small>
                </div>
            </div>
        `).join('');
    } catch (error) {
        console.error("Error fetching notes", error);
    }
}