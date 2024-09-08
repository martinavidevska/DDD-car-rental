import { vehicleService } from '../custom-axios/axios'

const VehicleService = {
    fetchVehicles: () => {
        return vehicleService.get('');
    },
    fetchVehicleById: (id) => {
        return vehicleService.get(`/${id}`);
    },
    addVehicle: (vehicle) => {
        return vehicleService.post('/add', vehicle);
    },
    filterVehicles: (type, model, dailyPrice) => {
        const params = {};
        if (type) params.type = type;
        if (model) params.model = model;
        if (dailyPrice) params.dailyPrice = dailyPrice;

        return vehicleService.get('/filter', { params });
    },
    fetchVehicleTypes: () => {
        return vehicleService.get('/types', {})
    }
}

export default VehicleService;
