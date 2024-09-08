import React, { useState } from "react";
import { Link } from "react-router-dom";
import emailImg from "../../assets/img/logo.jpg";
import profileImgDefault from "../../assets/img/logo.jpg";
import styles from "./index.module.css";

const Header = () => {
    const [showImg, setShowImg] = useState(profileImgDefault);

    const logoutHandler = () => {
        // Handle logout functionality if needed
    };

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
                            <Link to="/history?search=&by=id&order=desc" className={`nav-link ${styles["nav-link"]}`}>
                                <p>History</p>
                            </Link>
                        </li>

                        <li className={`nav-item ${styles["nav-item"]}`}>
                            <Link to="/about" className={`nav-link ${styles["nav-link"]}`}>
                                <p>About</p>
                            </Link>
                        </li>

                        {/* New "Add Vehicle" Button */}
                        <li className={`nav-item ${styles["nav-item"]}`}>
                            <Link to="/add-vehicle" className={`nav-link ${styles["nav-link"]}`}>
                                <p>Add Vehicle</p>
                            </Link>
                        </li>

                        <React.Fragment>
                            <li>
                                <Link to="/">
                                    <img
                                        src={emailImg}
                                        alt="email logo"
                                        className={styles["email-logo"]}
                                    />
                                </Link>
                            </li>

                            <li className={`dropdown ${styles["nav-item"]}`}>
                                <div
                                    id="navbarDropdown"
                                    role="button"
                                    data-bs-toggle="dropdown"
                                    aria-expanded="false"
                                >
                                    <img
                                        src={showImg}
                                        alt="profile"
                                        className={styles["user-profile-img"]}
                                        onError={(e) => {
                                            e.onError = null;
                                            setShowImg(profileImgDefault);
                                        }}
                                    />
                                </div>
                                <ul
                                    className="dropdown-menu"
                                    aria-labelledby="navbarDropdown"
                                >
                                    <li>
                                        <Link to="/edit/profile" className="dropdown-item">
                                            Edit Profile
                                        </Link>
                                    </li>
                                    <li>
                                        <Link to="/help" className="dropdown-item">
                                            Help
                                        </Link>
                                    </li>
                                    <li>
                                        <Link to="#" className="dropdown-item" onClick={logoutHandler}>
                                            Log out
                                        </Link>
                                    </li>
                                </ul>
                            </li>
                        </React.Fragment>
                    </ul>
                </section>
            </nav>
        </header>
    );
};

export default Header;
