import React, {useState} from 'react';
import './filter.css'

const VehicleFilter = ({onFilterSubmit, vehicleTypes}) => {
    const [model, setModel] = useState('');
    const [dailyPrice, setDailyPrice] = useState('');
    const [type, setType] = useState('');

    const handleSubmit = (e) => {
        e.preventDefault();
        onFilterSubmit(type, model, dailyPrice);
    };

    return (
        <section className="section-hero" id="home">
            <div className="container">
                <div className="hero-content">
                    <h2 className="h1 hero-title">Just choose a vehicle and come pick it up</h2>
                    <p className="hero-text">With locations in Skopje, Ohrid and Strumica</p>
                </div>
                <div className="hero-banner"></div>
                <form onSubmit={handleSubmit} className="hero-form">
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

                    <button type="submit" className="btn">Search</button>
                </form>
            </div>
        </section>
    );
};

export default VehicleFilter;
