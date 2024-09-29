import React, {useState, useEffect} from 'react';
import {Link} from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import './vehicleList.css';
import VehicleFilter from "../Filter/filter";
import '@fortawesome/fontawesome-free/css/all.min.css';

const VehicleList = ({vehicles, fetchFilteredVehicles, vehicleTypes}) => {
    const [filteredVehicles, setFilteredVehicles] = useState([]);

    useEffect(() => {
        setFilteredVehicles(vehicles);
    }, [vehicles]);

    const handleFilterSubmit = (type, model, dailyPrice) => {
        fetchFilteredVehicles(type, model, dailyPrice)
            .then(data => {
                console.log('Filtered vehicles:', data);
                setFilteredVehicles(data);
            })
            .catch(error => console.error('Error fetching filtered vehicles:', error));
    };


    return (
<div>
        <VehicleFilter
            onFilterSubmit={handleFilterSubmit}
            vehicleTypes={vehicleTypes}
        />

    <section className="section featured-car" id="featured-car">
        <div className="title-wrapper text-center">
            <h2 className="section-title">Available cars</h2>
        </div>
        <div className="featured-car-list">
            {filteredVehicles.map(vehicle => (
                <div key={vehicle.id.id} className="featured-car-card">
                    <figure className="card-banner">
                        <img src={vehicle.pictureLink} alt={`${vehicle.model} ${vehicle.brand}`}/>
                    </figure>
                    <div className="card-content">
                        <h3 className="card-title">{vehicle.brand} {vehicle.model}</h3>

                        <ul className="card-list">
                            <li className="card-list-item">
                                <i className="fas fa-users"></i> {/* Seats Icon */}
                                 {vehicle.seats}  Seats
                            </li>
                            <li className="card-list-item">
                                <i className="fas fa-suitcase"></i> {/* Bags Icon */}
                                 {vehicle.bags}  Bags
                            </li>
                            <li className="card-list-item">
                                <i className="fas fa-car"></i> {/* Car Icon */}
                                  {vehicle.vehicleType}
                            </li>
                        </ul>


                        <div className="card-price-wrapper">
                            <p className="card-price">
                                {vehicle.dailyPrice.amount} {vehicle.dailyPrice.currency} / day
                            </p>
                            <button className="fav-btn">
                                <ion-icon name="heart-outline"></ion-icon>
                            </button>
                        </div>

                        <div className="text-center mt-3">
                            <Link className="btn btn-dark" to={`/rent/${vehicle.id.id}`}>Rent</Link>
                        </div>
                    </div>
                </div>
            ))}
        </div>
    </section>
</div>
    )
        ;
};

export default VehicleList;
