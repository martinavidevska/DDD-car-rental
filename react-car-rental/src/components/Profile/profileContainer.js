import React, { useEffect, useState } from 'react';
import RentalService from  '../../repository/RentalService'
import VehicleService from '../../repository/VehicleService';
import Profile from './profile';  // Adjust the path if necessary

const ProfileContainer = ({ user}) => {
    const [rentals, setRentals] = useState([]);
    const [enrichedRentals, setEnrichedRentals] = useState([]);

    useEffect(() => {
        const fetchRentals = async () => {
            try {
                const rentalResponse = await RentalService.fetchRentalsByUser(user.id.id);
                const userRentals = rentalResponse.data;
      console.log("The rentals ",userRentals);
                const enrichedData = await Promise.all(
                    userRentals.map(async (rent) => {
                        const [vehicleResponse, pickUpLocationResponse, returnLocationResponse] = await Promise.all([
                            VehicleService.fetchVehicleById(rent.vehicleId),
                            RentalService.fetchLocationById(rent.pickedFrom),
                            RentalService.fetchLocationById(rent.returnedTo)
                        ]);
console.log("The location", returnLocationResponse);
                        return {
                            ...rent,
                            vehicle: vehicleResponse.data,
                            pickUpLocation: pickUpLocationResponse.data,
                            returnLocation: returnLocationResponse.data,
                        };
                    })
                );

                // Set the enriched rentals data to state
                setEnrichedRentals(enrichedData);
            } catch (error) {
                console.error('Error fetching rentals or associated data:', error);
            }
        };

        fetchRentals();
    }, [user.id]);

    return <Profile user={user} rentals={enrichedRentals} />;
};

export default ProfileContainer;
