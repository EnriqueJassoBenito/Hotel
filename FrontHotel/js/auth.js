//const API_BASE_URL = 'http://localhost:8081';
const API_BASE_URL = 'http://44.216.242.226:8080';

// Si usamos ngrok, agregamos el header especial
const NGROK_EXTRA_HEADERS = API_BASE_URL.includes('ngrok')
  ? { 'ngrok-skip-browser-warning': 'true' }
  : {};

// allowedRoles es un array, ej: ['RECEPCION'] o ['LIMPIEZA']
function checkAuth(allowedRoles) {
  const token = localStorage.getItem('token');
  const rol = localStorage.getItem('rol');

  // Si no hay token → al login
  if (!token) {
    window.location.href = '/';
    return;
  }

  // Si el rol NO está en los permitidos para esta página
  if (!allowedRoles.includes(rol)) {
    // Redirigimos a SU página correcta
    if (rol === 'RECEPCION') {
      window.location.href = '/pages/habitaciones.html';
    } else if (rol === 'LIMPIEZA') {
      window.location.href = '/pages/camarera-dashboard.html';
    } else {
      window.location.href = '/';
    }
  }
}

// Fetch con token para llamar al backend
async function authFetch(url, options = {}) {
  const token = localStorage.getItem('token');

  const headers = {
    'Content-Type': 'application/json',
    ...(options.headers || {}),
    ...NGROK_EXTRA_HEADERS,
    'Authorization': `Bearer ${token}`
  };

  const response = await fetch(`${API_BASE_URL}${url}`, {
    ...options,
    headers
  });

  if (response.status === 401 || response.status === 403) {
    localStorage.clear();
    window.location.href = '/';
  }

  return response;
}

function logout() {
  localStorage.clear();   // elimina token, rol, username, nombre, userId, etc.
  window.location.href = '/';
}

