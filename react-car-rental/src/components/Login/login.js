import React from 'react';
import UserService from "../../repository/UserService";
import {Link, useNavigate} from "react-router-dom";
import './login.css'

const Login = (props) => {

    const navigate = useNavigate(); // Use for navigation after form submission

    const [formData, updateFormData] = React.useState({
        username: "",
        password: ""
    })

    const handleChange = (e) => {
        updateFormData({
            ...formData,
            [e.target.name]: e.target.value.trim()
        })
    }

    const onFormSubmit = (e) => {
        e.preventDefault();
        const loginRequest = {
            username: formData.username,
            password: formData.password
        }
        UserService.login(loginRequest).then(response => {
            console.log("The response of login", response)
            localStorage.setItem("JWT", response.data.token);
            window.location.href = "/"; // Refresh the page after login
        })

    }

    return (
        <div className="row mt-5">
            <div className="col-md-5">
                <form onSubmit={onFormSubmit}>
                    <div className="form-group">
                        <label htmlFor="name">Username</label>
                        <input type="text"
                               className="form-control"
                               name="username"
                               required
                               placeholder="Enter username"
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="price">Password</label>
                        <input type="password"
                               className="form-control"
                               name="password"
                               placeholder="Enter password"
                               required
                               onChange={handleChange}
                        />
                    </div>
                    <button id="submit" type="submit" className="btn btn-primary">Submit</button>
                    <div>
                        <p>Don't have an account yet?<Link to="/register"><p>Register Here</p> </Link></p>
                    </div>
                </form>
            </div>
        </div>
    )
}

export default Login;