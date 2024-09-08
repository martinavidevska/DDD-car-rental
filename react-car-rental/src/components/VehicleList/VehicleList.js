import React, { useState, useEffect } from 'react';
import VehicleService from '../../repository/VehicleService';
import { Link } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';


const VehicleList = ({ vehicles, fetchFilteredVehicles, vehicleTypes }) => {
    const [model, setModel] = useState('');
    const [dailyPrice, setDailyPrice] = useState('');
    const [type, setType] = useState('');

    const handleSubmit = (e) => {
        e.preventDefault();
        console.log('Submitting filters:', type, model, dailyPrice);
        fetchFilteredVehicles(type, model, dailyPrice);
    };

    return (
        <div className="container mt-5">
            <h1 className="text-center mb-4">Vehicles</h1>

            {/* Filter Form */}
            <form onSubmit={handleSubmit} className="filter-form mb-4">
                <div className="input-wrapper">
                    <label htmlFor="vehicleModel" className="input-label">Search by model</label>
                    <input
                        type="text"
                        id="vehicleModel"
                        className="input-field"
                        value={model}
                        onChange={(e) => setModel(e.target.value)}
                        placeholder="Model"
                    />
                </div>

                <div className="input-wrapper">
                    <label htmlFor="price" className="input-label">Maximal daily price</label>
                    <input
                        type="number"
                        id="price"
                        className="input-field"
                        value={dailyPrice}
                        onChange={(e) => setDailyPrice(e.target.value)}
                        placeholder="Amount"
                    />
                </div>

                <div className="input-wrapper">
                    <label htmlFor="vehicleTypeId" className="input-label">Choose a vehicle type</label>
                    <select
                        id="vehicleTypeId"
                        className="input-field"
                        value={type}
                        onChange={(e) => setType(e.target.value)}
                    >
                        <option value="">All</option>
                        {vehicleTypes.map((type, index) => (
                            <option key={index} value={type}>{type}</option>
                        ))}
                    </select>
                </div>

                <button type="submit" className="btn btn-primary">Search</button>
            </form>

            <div className="row">
                {vehicles.map(vehicle => (
                    <div key={vehicle.id.id} className="col-md-4 mb-4">
                        <div className="card h-100">
                            <img src={vehicle.pictureLink} className="card-img-top" alt={"the image"} />
                            <div className="card-body">
                                <h5 className="card-title">{vehicle.brand} {vehicle.model}</h5>
                                <p className="card-text">License Plate: {vehicle.licensePlate.licensePlate}</p>
                                <p className="card-text">Price per day: {vehicle.dailyPrice.amount} {vehicle.dailyPrice.currency}</p>
                                <p className="card-text">Seats: {vehicle.seats}</p>
                                <p className="card-text">Bags: {vehicle.bags}</p>
                                <p className="card-text">Type: {vehicle.vehicleType}</p>
                                <div className="text-center mt-3">
                                    <Link
                                        className="btn btn-dark"
                                        to={`/rent/${vehicle.id.id}`}
                                    >
                                        Rent
                                    </Link>
                                </div>
                            </div>
                        </div>
                    </div>
                ))}
            </div>
        </div>
    );
}

export default VehicleList;
