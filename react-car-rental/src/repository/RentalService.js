
import { rentalService } from '../custom-axios/axios';

const RentalService = {
    fetchRentals: () => {
        return rentalService.get('');
    },
    fetchRentalById: (id) => {
        return rentalService.get(`/${id}`);
    },
    rent: (rental) => {
        return rentalService.post('/rent', rental);
    },
    fetchVehicles: () =>{
        return rentalService.get('/list-vehicles');
    },
    fetchLocations: () =>{
        return rentalService.get('/locations');
    },
    addLocation: (location) => {
        return rentalService.post('/add-location', location);
    }
};

export default RentalService;
