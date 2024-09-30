import React, { useState } from "react";
import { Link } from "react-router-dom";
import emailImg from "../../assets/img/logo.jpg";
import profileImgDefault from "../../assets/img/logo.jpg";
import styles from "./index.module.css";

const Header = ({user, onLogout}) => {
    const [showImg, setShowImg] = useState(profileImgDefault);
        return (
        <header className="navbar navbar-expand-lg navbar-light bg-white">
            <nav className={`container ${styles["nav-pad"]}`}>
                <button
                    className="navbar-toggler"
                    type="button"
                    data-bs-toggle="collapse"
                    data-bs-target="#navbarNav"
                    aria-controls="navbarNav"
                    aria-expanded="false"
                    aria-label="Toggle navigation"
                >
                    <span className="navbar-toggler-icon"></span>
                </button>

                <section className={styles["donut-up"]}>
                    <span className={styles["circle-black"]}></span>
                    <span className={styles["circle-yellow"]}></span>
                </section>

                <section className="collapse navbar-collapse" id="navbarNav">
                    <ul className="navbar-nav ms-auto mb-2 mb-lg-0">
                        <li className={`nav-item ${styles["nav-item"]}`}>
                            <Link to="/" className={`nav-link ${styles["nav-link"]}`}>
                                <p>Home</p>
                            </Link>
                        </li>
                        <li className={`nav-item ${styles["nav-item"]}`}>
                            <Link to="/add-vehicle" className={`nav-link ${styles["nav-link"]}`}>
                                <p>Add Vehicle</p>
                            </Link>
                        </li>

                        <li className={`nav-item ${styles["nav-item"]}`}>
                            <Link to="/login" className={`nav-link ${styles["nav-link"]}`}>
                                <p>Login</p>
                            </Link>
                        </li>
                        <li className={`nav-item ${styles["nav-item"]}`}>
                            <Link to="/register" className={`nav-link ${styles["nav-link"]}`}>
                                <p>Register</p>
                            </Link>
                        </li>


                        {user && (
                            <li className={`nav-item ${styles["nav-item"]}`}>
                                <Link to="/user-profile" className={`nav-link ${styles["nav-link"]}`}>
                                    <p>Profile</p>
                                </Link>
                            </li>
                        )}
                        <li className={`nav-item ${styles["nav-item"]}`}>
                            <Link to="/register" className={`nav-link ${styles["nav-link"]}`}>
                                <button className={`nav-item ${styles["nav-item"]}`} onClick={onLogout}>Logout</button>
                            </Link>
                        </li>
                    </ul>
                </section>
            </nav>
        </header>
        );
};

export default Header;
