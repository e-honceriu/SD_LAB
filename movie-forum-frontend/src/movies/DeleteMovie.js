import React, {useEffect, useState} from "react";
import axios from "axios";
import { Link, useNavigate, useParams } from "react-router-dom";

export default function DeleteMovie() {

    let navigate = useNavigate();

    const {id}=useParams();

    const onSubmit = async (e) => {
        e.preventDefault();
        try {
            await axios.delete(`http://localhost:8080/api/movies/${id}`)
            navigate("/")
        } catch(error) {
            if (error.response.status === 404 || error.response.status === 204 || error.response.status === 400) {
                alert(error.response.data);
            } else {
                alert("An error occurred. Please try again later.");
            }
        }
    };

    return (
        <div className="container">
            <div className="row">
                <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
                    <h2 className="text-center m-4">Are you sure you want to delete this movie?</h2>
                    <form onSubmit={(e) => onSubmit(e)}>
                        <button type="submit" className="btn btn-outline-danger">Delete</button>
                        <Link className="btn btn-outline-primary mx-2" to="/">Cancel</Link>
                    </form>
                </div>
            </div>
        </div>
    )
}