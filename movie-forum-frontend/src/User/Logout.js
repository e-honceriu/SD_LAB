import React, {useState} from "react";
import axios from "axios";
import { Link, useNavigate } from "react-router-dom";

export default function Logout() {
    let navigate = useNavigate()

    const onSubmit = async (e) => {
    
        e.preventDefault();
        try {
            await axios.post("http://localhost:8080/api/users/logout")
            navigate("/")
        } catch(error) {
            alert(error.response.data);
        }
    }

    return (
        <div className="container">
            <div className="row">
                <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
                    <h2 className="text-center m-4">Do you want to logout?</h2>
                    <form onSubmit={(e) => onSubmit(e)}>
                        <button type="submit" className="btn btn-outline-primary">Logout</button>
                        <Link className="btn btn-outline-danger mx-2" to="/">Cancel</Link>
                    </form>
                </div>
            </div>
        </div>
    )
}
