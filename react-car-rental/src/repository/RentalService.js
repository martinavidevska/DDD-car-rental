
import { rentalService } from '../custom-axios/axios';
import vehicleService from "./VehicleService";

const RentalService = {
    fetchRentals: () => {
        return rentalService.get('');
    },
    fetchRentalById: (id) => {
        return rentalService.get(`/rental/${id}`);
    },
    rent: (rental) => {
        return rentalService.post('/rental/rent', rental);
    },
    fetchVehicles: () =>{
        return rentalService.get('/rental/list-vehicles');
    },
    fetchLocations: () =>{
        return rentalService.get('/rental/locations');
    },
    addLocation: (location) => {
        return rentalService.post('/rental/add-location', location);
    },
    addPayment :(paymentForm) =>{
        return rentalService.post('/rental/payment', paymentForm);
    },
    fetchRentalsByUser: (userId) =>{
        return rentalService.get(`/rental/by-user`, userId );
    }
};

export default RentalService;
