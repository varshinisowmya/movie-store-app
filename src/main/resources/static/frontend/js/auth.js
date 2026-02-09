const BASE_AUTH_URL = "http://localhost:8080/api/auth";

// Function to show "Toast" messages
function showToast(message, isError = false) {
    const toast = document.getElementById('toast');
    toast.textContent = message;
    toast.style.display = 'block';
    toast.style.background = isError ? 'rgba(239, 68, 68, 0.8)' : 'rgba(34, 197, 94, 0.8)';

    setTimeout(() => {
        toast.style.display = 'none';
    }, 3000);
}

// Handle Login
const loginForm = document.getElementById('loginForm');
if (loginForm) {
    loginForm.addEventListener('submit', async (e) => {
        e.preventDefault();
        const u = document.getElementById('username').value;
        const p = document.getElementById('password').value;

        try {
            // We use template literals to build the URL with parameters
            const response = await fetch(`${BASE_AUTH_URL}/login?username=${u}&password=${p}`, {
                method: 'POST'
            });

            if (response.ok) {
                const userData = await response.json(); // The User Object from Java

                // SAVE TO BROWSER MEMORY
                localStorage.setItem("userId", userData.id);
                localStorage.setItem("username", userData.username);
                localStorage.setItem("role", userData.role);

                alert("Success! Welcome " + userData.username);
                window.location.href = (userData.role === 'ADMIN') ? "admin.html" : "index.html";
            } else {
                alert("Login Failed: Check username or password in Database.");
            }
        } catch (error) {
            console.error(error);
            alert("Backend Error: Is Spring Boot running?");
        }
    });
}
// Handle Registration
const registerForm = document.getElementById('registerForm');
if (registerForm) {
    registerForm.addEventListener('submit', async (e) => {
        e.preventDefault();

        const username = document.getElementById('reg-username').value;
        const password = document.getElementById('reg-password').value;
        const role = document.getElementById('reg-role').value;

        try {
            const response = await fetch(`api/auth/register`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    username: username,
                    password: password,
                    role: role
                })
            });

            if (response.ok) {
                showToast("Account created for " + username + "! Redirecting...");
                setTimeout(() => window.location.href = "login.html", 2000);
            } else {
                showToast("Username already exists!", true);
            }
        } catch (error) {
            showToast("Connection failed!", true);
        }
    });
}