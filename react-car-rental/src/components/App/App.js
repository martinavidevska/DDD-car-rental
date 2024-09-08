import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import './App.css';
import Header from "../Header/Header";
import VehicleList from "../VehicleList/VehicleList";
import VehicleService from "../../repository/VehicleService";
import AddVehicle from "../AddVehicle/AddVehicle";
import RentalService from "../../repository/RentalService";
import RentalAdd from "../Rent/Rent";
import Login from "../Login/login";

class App extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            vehicles: [],
            users: [],
            vehicleTypes: [],
            locations: [],
            selectedVehicle: null,
            type: '',
            model: '',
            dailyPrice: ''
        };
    }

    render() {
        return (
            <Router>
                <Header />
                <main>
                    <div className="container">
                        <Routes>
                            <Route
                                path="/"
                                element={
                                    <VehicleList
                                        vehicles={this.state.vehicles}
                                        fetchFilteredVehicles={this.fetchFilteredVehicles}
                                        vehicleTypes={this.state.vehicleTypes}
                                    />
                                }
                            />
                            <Route
                                path="/add-vehicle"
                                element={<AddVehicle
                                    onAddVehicle={this.addVehicle}
                                    vehicleTypes={this.state.vehicleTypes}/>}
                            />
                            <Route
                                path="/rent/:vehicleId"
                                element={
                                    <RentalAdd
                                        onAddRental={this.rentVehicle}
                                        locations={this.state.locations}
                                        vehicle={this.state.selectedVehicle}
                                        findVehicleById={this.findVehicleById}
                                    />
                                }
                            />
                            <Route
                                path="/login"
                                element={<Login onLogin={this.fetchData} />}
                            />
                        </Routes>
                    </div>
                </main>
            </Router>
        );
    }

    componentDidMount() {
        this.fetchData();
            }

    fetchData = () => {
        this.loadVehicles();
        this.loadLocations();
        this.loadVehicleTypes();
    }

    loadVehicles = () => {
        VehicleService.fetchVehicles()
            .then((data) => {
                this.setState({ vehicles: data.data });
                console.log('Vehicle state after setting:', this.state.vehicles);

            })
            .catch((error) => {
                console.error('Error fetching vehicles:', error);
            });
    }

    loadLocations = () => {
        RentalService.fetchLocations()
            .then((response) => {
                const locations = response.data;
                if (Array.isArray(locations)) {
                    this.setState({ locations });
                } else {
                    console.error("Fetched locations is not an array:", locations);
                }
            })
            .catch((error) => {
                console.error("Error fetching locations:", error);
            });
    }

    loadVehicleTypes = () => {
       VehicleService.fetchVehicleTypes()
           .then((data) => {
               this.setState({ vehicleTypes: data.data });
               console.log('Vehicle types state after setting:', this.state.vehicleTypes);

           })
           .catch((error) => {
               console.error('Error fetching vehicle types:', error);
           });
    }

    fetchFilteredVehicles = (type, model, dailyPrice) => {
        VehicleService.filterVehicles(type, model, dailyPrice)
            .then((response) => {
                this.setState({ vehicles: response.data });
                console.log("The type is: ", this.state.type);
            })
            .catch((error) => {
                console.error('Error fetching filtered vehicles:', error);
            });
    }

    rentVehicle = (rentalForm) => {
        RentalService.rent(rentalForm)
            .then((response) => {
                console.log("Rented successfully:", response.data);
                this.loadVehicles();  // To refresh the list after renting a vehicle
            })
            .catch((error) => {
                console.error("Error renting:", error);
            });
    }

    addVehicle = (vehicleForm) => {
        VehicleService.addVehicle(vehicleForm)
            .then((data) => {
                console.log("Vehicle added successfully:", data.data);
                this.loadVehicles();  // To refresh the list after adding a new vehicle
            })
            .catch((error) => {
                console.error("Error adding vehicle:", error);
            });
    }

    findVehicleById = (vehicleId) => {
        VehicleService.fetchVehicleById(vehicleId)
            .then((data) => {
                this.setState({ selectedVehicle: data.data });
            });
    }
}

export default App;
