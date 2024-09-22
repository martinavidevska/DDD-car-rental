import axios from "axios";

const VEHICLE_SERVICE_URL = 'http://localhost:9091/api/vehicle';
const USER_SERVICE_URL = 'http://localhost:9090/api/auth';
const RENTAL_SERVICE_URL = 'http://localhost:9093/api';

// Create an axios instance with default configuration and interceptors
const instance = axios.create({
    headers: {
        'Content-Type': 'application/json',
    },
});

// Add a request interceptor to handle JWT token for all services
instance.interceptors.request.use(
    config => {
        const token = localStorage.getItem("JWT");
        if (token) {
            config.headers.Authorization = `Bearer ${token}`;
        }
        return config;
    },
    error => {
        return Promise.reject(error);
    }
);

instance.interceptors.response.use(
    response => response,
    error => {
        if (error.response && error.response.status === 403) {
            localStorage.removeItem("JWT");
            window.location.href = '/login';  // Redirect to login on 403 Forbidden
        }
        return Promise.reject(error);
    }
);

// Use the same instance for all services but with different base URLs
const vehicleService = instance.create({
    baseURL: VEHICLE_SERVICE_URL
});

const rentalService = instance.create({
    baseURL: RENTAL_SERVICE_URL
});

const userService = instance.create({
    baseURL: USER_SERVICE_URL
});

// Export services for use in other parts of the application
export { vehicleService, rentalService, userService };
