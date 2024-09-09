import React, { useEffect, useState } from 'react';
import { useParams } from "react-router-dom";
import './rent.css';
import { useNavigate } from 'react-router-dom';


const RentalAdd = (props) => {
    const navigate = useNavigate();
    const { vehicleId } = useParams(); // Extract vehicleId from route params
    const [rentData, setRentData] = useState({
        startRent: "",
        endRent: "",
        vehicleId: vehicleId,
        userId: "",
        pickedFrom: "",
        returnedTo: ""
    });

    useEffect(() => {
        props.findVehicleById(vehicleId);  // Fetch vehicle details if not already provided
    }, [vehicleId, props]);


    const handleChange = (e) => {
        setRentData({
            ...rentData,
            [e.target.name]: e.target.value
        });
    };


    const onFormSubmit = (e) => {
        e.preventDefault();
        props.onAddRental(rentData);
        navigate('/');  // Replace '/some-page' with the desired route
    };

    const vehicle = props.vehicle;
    console.log("the vehicle is", vehicle)

    return (
        <div className="rent-container">
            <div className="content-wrapper">
                <div className="vehicle-info">
                    {vehicle ? (
                        <div className="vehicle-details">
                            <div className="vehicle-details-text">
                                <h2>Vehicle Details:</h2>
                                <p>Brand: {vehicle.brand}</p>
                                <p>Model: {vehicle.model}</p>
                                <p>Seats: {vehicle.seats}</p>
                                <p>Price per Day: ${vehicle.dailyPrice.amount} {vehicle.dailyPrice.currency}</p>
                            </div>
                            <div className="vehicle-details-image">
                                <img src={vehicle.pictureLink} alt={`${vehicle.brand} ${vehicle.model}`} />
                            </div>
                        </div>
                    ) : (
                        <p>Vehicle not found</p>
                    )}
                </div>
                <div className="rental-form">
                    <form onSubmit={onFormSubmit}>
                        <div className="form-group">
                            <label htmlFor="startRent">Start Date </label>
                            <input
                                type="date"
                                id="startRent"
                                name="startRent"
                                value={rentData.startRent}
                                onChange={handleChange}
                                required
                            />
                        </div>
                        <div className="form-group">
                            <label htmlFor="endRent">End Date </label>
                            <input
                                type="date"
                                id="endRent"
                                name="endRent"
                                value={rentData.endRent}
                                onChange={handleChange}
                                required
                            />
                        </div>
                        {/*<div className="form-group">*/}
                        {/*    <label htmlFor="vehicleId">Vehicle ID </label>*/}
                        {/*    <input*/}
                        {/*        type="text"*/}
                        {/*        id="vehicleId"*/}
                        {/*        name="vehicleId"*/}
                        {/*        value={rentData.vehicleId} // Display vehicleId from route*/}
                        {/*        readOnly*/}
                        {/*    />*/}
                        {/*</div>*/}
                        <div className="form-group">
                            <label htmlFor="userId">User ID</label>
                            <input
                                type="text"
                                id="userId"
                                name="userId"
                                required
                                onChange={handleChange}
                            />
                        </div>
                        <div className="form-group">
                            <label htmlFor="pickedFrom">Picked From</label>
                            <select
                                name="pickedFrom"
                                id="pickedFrom"
                                className="form-control"
                                onChange={handleChange}
                                required
                            >
                                <option value="">Select Location</option>
                                {props.locations.map((location) => (
                                    <option key={location.id} value={location.id}>
                                        {location.name} - {location.state}
                                    </option>
                                ))}
                            </select>
                        </div>
                        <div className="form-group">
                            <label htmlFor="returnedTo">Returned To</label>
                            <select
                                name="returnedTo"
                                id="returnedTo"
                                className="form-control"
                                onChange={handleChange}
                                required
                            >
                                <option value="">Select Location</option>
                                {props.locations.map((location) => (
                                    <option key={location.id} value={location.id}>
                                        {location.name} - {location.state}
                                    </option>
                                ))}
                            </select>
                        </div>
                        <button type="submit" className="btn-submit">
                            Submit
                        </button>
                    </form>
                </div>
            </div>
        </div>
    );
};
export default RentalAdd;
