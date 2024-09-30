import React from 'react';
import './profile.css';

const Profile = ({ user, rentals = [] }) => {
    console.log("the rentals are", rentals)
    return (
        <div className="profile">
            <div className="container-profile">
                <h1>Your Profile</h1>
               <button> <i className="fa fa-pencil"></i></button>

                <div className="profile-info">
                    <label htmlFor="username">Username:</label>
                    <p id="username">{user.username}</p>

                    <label htmlFor="name">Name:</label>
                    <p id="name">{user.name}</p>

                    <label htmlFor="email">Email:</label>
                    <p id="email">{user.email}</p>

                    <label htmlFor="phoneNumber">Phone Number:</label>
                    <p id="phoneNumber">{user.phoneNumber}</p>

                    <label htmlFor="address">Address:</label>
                    <p id="address">{user.address}</p>
                </div>

                <div className="rentals">
                    <h2>Your Rentals</h2>
                    {rentals.length > 0 ? (
                        rentals.map((rent, index) => (
                            <div key={index} className="rental-item">
                                <img src={rent.vehicle.pictureLink} alt="Car" className="w-100"/>
                                <p><strong>Car:</strong> {rent.vehicle.brand} {rent.vehicle.model}</p>
                                <p><strong>Start Date:</strong> {rent.startRent}</p>
                                <p><strong>End Date:</strong> {rent.endRent}</p>
                                {/*<p><strong>Total Amount:</strong> {rent.totalAmount}</p>*/}
                                <p><strong>Pick up
                                    location:</strong> {rent.pickUpLocation.name}, {rent.pickUpLocation.city}</p>
                                <p><strong>Return
                                    location:</strong> {rent.returnLocation.name}, {rent.returnLocation.city}</p>
                            </div>
                        ))
                    ) : (
                        <p>No rentals found.</p>
                    )}
                </div>
            </div>
        </div>
    );
};

export default Profile;
