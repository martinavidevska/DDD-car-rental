import React, { useState } from 'react';
import {useNavigate} from "react-router-dom";

const AddVehicle = ({ onAddVehicle,vehicleTypes }) => {
    const navigate = useNavigate();
    const [vehicleData, setVehicleData] = useState({
        licensePlate: '',
        currency: '',
        amount: '',
        model: '',
        brand: '',
        seats: '',
        bags: '',
        vehicleType: '',
        pictureLink: ''
    });

    const handleChange = (e) => {
        setVehicleData({ ...vehicleData, [e.target.name]: e.target.value });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        onAddVehicle(vehicleData);
        navigate('/');


    };

    return (
        <form onSubmit={handleSubmit}>
            <div className="form-group">
                <label>License Plate</label>
                <input
                    type="text"
                    name="licensePlate"
                    className="form-control"
                    value={vehicleData.licensePlate}
                    onChange={handleChange}
                    required
                />
            </div>

            <div className="form-group">
                <label>Amount</label>
                <input
                    type="number"
                    name="amount"
                    className="form-control"
                    value={vehicleData.amount}
                    onChange={handleChange}
                    required
                />
            </div>
            <div className="form-group">
                <label>Currency</label>
                <input
                    type="text"
                    name="currency"
                    className="form-control"
                    value={vehicleData.currency}
                    onChange={handleChange}
                    required
                />
            </div>

            <div className="form-group">
                <label>Model</label>
                <input
                    type="text"
                    name="model"
                    className="form-control"
                    value={vehicleData.model}
                    onChange={handleChange}
                    required
                />
            </div>

            <div className="form-group">
                <label>Brand</label>
                <input
                    type="text"
                    name="brand"
                    className="form-control"
                    value={vehicleData.brand}
                    onChange={handleChange}
                    required
                />
            </div>

            <div className="form-group">
                <label>Seats</label>
                <input
                    type="number"
                    name="seats"
                    className="form-control"
                    value={vehicleData.seats}
                    onChange={handleChange}
                    required
                    min="1"
                />
            </div>

            <div className="form-group">
                <label>Bags</label>
                <input
                    type="number"
                    name="bags"
                    className="form-control"
                    value={vehicleData.bags}
                    onChange={handleChange}
                    required
                    min="0"
                />
            </div>

            <div className="input-wrapper">
                <label htmlFor="vehicleTypeId" className="input-label">Choose a vehicle type</label>
                <select
                    name="vehicleType"
                    id="vehicleTypeId"
                    className="input-field"
                    value={vehicleData.vehicleTypeId}
                    onChange={handleChange}
                    required
                >
                    <option value="">All</option>
                    {vehicleTypes.map((type, index) => (
                        <option key={index} value={type}>{type}</option>
                    ))}
                </select>
            </div>

            <div className="form-group">
                <label>Picture Link</label>
                <input
                    type="url"
                    name="pictureLink"
                    className="form-control"
                    value={vehicleData.pictureLink}
                    onChange={handleChange}
                    required
                />
            </div>

            <button type="submit" className="btn btn-primary">Add Vehicle</button>
        </form>
    );
};

export default AddVehicle;
