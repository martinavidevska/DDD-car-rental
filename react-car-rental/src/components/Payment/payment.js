import React, { useState } from 'react';
import {useNavigate, useParams} from 'react-router-dom';

const Payment = (props) => {
    const rentalId = useParams(); // Assuming rentalId is passed as a prop
    const navigate = useNavigate(); // Use for navigation after form submission

    const [paymentData, setPaymentData] = useState({
        rentalId: rentalId.rentalId,
        paymentMethod: ""
    });

    const onFormSubmit = (e) => {
        e.preventDefault();
        props.onAddPayment(paymentData);
        console.log(paymentData)
        navigate('/');
    };
    const handleChange = (e) => {
        setPaymentData({
            ...paymentData,
            [e.target.name]: e.target.value
        });
    };
    return (
        <div className="payment-form">
            <h2>Payment Information</h2>
            <form onSubmit={onFormSubmit}>
                <div>
                    <label>Payment Method</label>
                    <input
                        type="text"
                        name="paymentMethod"
                        value={paymentData.paymentMethod}
                        onChange={handleChange}
                        required
                    />
                </div>
                <button type="submit">Submit Payment</button>
            </form>
        </div>
    );
};

export default Payment;
