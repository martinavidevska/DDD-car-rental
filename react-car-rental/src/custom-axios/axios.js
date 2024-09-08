import axios from "axios";

const VEHICLE_SERVICE_URL = 'http://localhost:9091/api/vehicle';
const USER_SERVICE_URL = 'http://localhost:9090/api/users';
const RENTAL_SERVICE_URL = 'http://localhost:9093/api/rental';

// Create an axios instance with default configuration
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

// Add a response interceptor to handle authentication errors
instance.interceptors.response.use(
    response => response,
    error => {
        if (error.response && error.response.status === 403) {
            localStorage.removeItem("JWT");
            window.location.href = '/login';
        }
        return Promise.reject(error);
    }
);

// Create service instances with their respective base URLs
const vehicleService = axios.create({
    baseURL: VEHICLE_SERVICE_URL,
});

const rentalService = axios.create({
    baseURL: RENTAL_SERVICE_URL,
    headers: {
        'Content-Type': 'application/json',
    },
    withCredentials: true,
});

const userService = axios.create({
    baseURL: USER_SERVICE_URL,
});

// Export services for use in other parts of the application
export { rentalService, userService, vehicleService, };
