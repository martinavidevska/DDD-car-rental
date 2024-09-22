import React from 'react';
import {BrowserRouter as Router, Navigate, Route, Routes} from 'react-router-dom';
import './App.css';
import Header from "../Header/Header";
import VehicleList from "../VehicleList/VehicleList";
import VehicleService from "../../repository/VehicleService";
import AddVehicle from "../AddVehicle/AddVehicle";
import RentalService from "../../repository/RentalService";
import RentalAdd from "../Rent/Rent";
import Login from "../Login/login";
import Payment from "../Payment/payment";
import Profile from "../Profile/profile";
import Register from "../Register/register";
import UserService from "../../repository/UserService";

class App extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            vehicles: [],
            rentals: [],
            user: null,  // Track the logged-in user
            vehicleTypes: [],
            locations: [],
            selectedVehicle: null,
            type: '',
            model: '',
            dailyPrice: ''
        };
    }

    componentDidMount() {
        this.fetchData();
        this.checkForLoggedInUser();
    }

    render() {
        return (
            <Router>
                <Header
                    user = {this.state.user}
                    onLogout = {this.logout}
                />
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
                                    this.state.user ? (
                                    <RentalAdd
                                        onAddRental={this.rentVehicle}
                                        locations={this.state.locations}
                                        findVehicleById={this.findVehicleById}
                                        vehicle={this.state.selectedVehicle}
                                        user = {this.state.user}
                                    />
                                    ) : (
                                        <Navigate to="/login" />
                                    )
                                }
                            />
                            <Route
                                path="/payment/:rentalId"
                                element={<Payment
                                    onAddPayment={this.addPayment}
                                />}
                            />
                            <Route
                                path="/login"
                                element={<Login onSubmit={this.login}/>}
                            />
                            <Route
                                path="/register"
                                element={<Register />} // Add the registration route here
                            />
                            <Route
                                path="/user-profile"
                                element={
                                    this.state.user ? (
                                        <Profile
                                            rentals={this.findRentalsByUser(this.state.user?.id)}
                                            user={this.state.user}
                                        />
                                    ) : (
                                        <Navigate to="/login" />
                                    )
                                }
                            />
                        </Routes>
                    </div>
                </main>
            </Router>
        );
    }

    checkForLoggedInUser = () => {
        const token = localStorage.getItem('JWT');
        console.log("Token found:", token);
        if (token) {
            UserService.getUser()
                .then((response) => {
                    this.setState({ user: response.data });
                    console.log("Logged in user details:", response.data);
                    console.log(" in user details:", this.state.user);
                })
                .catch((error) => {
                    console.error("Error fetching user details:", error);
                });
        }
    };

    // Fetch initial data when the app loads
    fetchData = () => {
        this.loadVehicles();
        this.loadLocations();
        this.loadVehicleTypes();
    }

    loadVehicles = () => {
        VehicleService.fetchVehicles()
            .then((data) => {
                this.setState({ vehicles: data.data });
            })
            .catch((error) => {
                console.error('Error fetching vehicles:', error);
            });
    }

    loadLocations = () => {
        RentalService.fetchLocations()
            .then((response) => {
                this.setState({ locations: response.data });
            })
            .catch((error) => {
                console.error("Error fetching locations:", error);
            });
    }

    loadVehicleTypes = () => {
        VehicleService.fetchVehicleTypes()
            .then((data) => {
                this.setState({ vehicleTypes: data.data });
            })
            .catch((error) => {
                console.error('Error fetching vehicle types:', error);
            });
    }

    fetchFilteredVehicles = (type, model, dailyPrice) => {
        return VehicleService.filterVehicles(type, model, dailyPrice)
            .then((response) => {
                this.setState({ vehicles: response.data });
                console.log("the new vehicle are", this.state.vehicles);
            })
            .catch((error) => {
                console.error('Error fetching filtered vehicles:', error);
            });
    }

    rentVehicle = (rentalForm) => {
        return RentalService.rent(rentalForm)
            .then((response) => {
                return response.data;
            })
            .catch((error) => {
                console.error("Error renting:", error);
                throw error;
            });
    }

    addPayment = (paymentForm) => {
        RentalService.addPayment(paymentForm)
            .then((response) => {
                console.log("Payment added successfully:", response.data);
            })
            .catch((error) => {
                console.error("Error adding payment:", error);
            });
    }

    addVehicle = (vehicleForm) => {
        VehicleService.addVehicle(vehicleForm)
            .then((data) => {
                this.loadVehicles();
            })
            .catch((error) => {
                console.error("Error adding vehicle:", error);
            });
    }

    findVehicleById = (vehicleId) => {
        VehicleService.fetchVehicleById(vehicleId)
            .then((response) => {
                this.setState({ selectedVehicle: response.data });
            })
            .catch((error) => {
                console.error("Error fetching vehicle by ID:", error);
            });
    }

    findRentalsByUser = (username) => {
        console.log("The username is", username)
        return RentalService.fetchRentalsByUser(username)
            .then((response)=>
            console.log("The vehicles are",response)
            )
            .catch((error) => {
                console.error("Error fetching rentals by user:", error);
            });
    }
    // logout () => {
    //     localStorage.removeItem('JWT'); // Remove the token
    //     this.setState({ user: null }); // Update state
    // };
     logout = () => {
        localStorage.removeItem("JWT"); // Clear the JWT token
        this.setState({ user: null }); // Update state
         window.location.href = "/login"; // Redirect to login
    };
}

export default App;
