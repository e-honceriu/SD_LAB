import React, {useEffect, useState} from "react";
import axios from "axios";
import { Link, useNavigate, useParams } from "react-router-dom";

export default function EditReview() {

    let navigate = useNavigate();

    const {id}=useParams();
    const {movieId}=useParams();

    const [review, setReview] = useState({
        description:"",
        rating:""
    });

    const{description, rating} = review;

    const onInputChange=(e) => {

        setReview({...review,[e.target.name]: e.target.value})
    
    };

    useEffect( () => {
        loadReview();
    }, []);

    const onSubmit = async (e) => {
        e.preventDefault();
        try {
            await axios.put(`http://localhost:8080/api/reviews/${id}`, review)
            navigate(`/reviews/${movieId}`)
        } catch(error) {
            if (error.response.status === 400 || error.response.status === 401) {
                alert(error.response.data);
            } else {
                alert("An error occurred. Please try again later.");
            }
        }
    };

    const loadReview = async () => {
        const result=await axios.get(`http://localhost:8080/api/reviews/${id}`)
        setReview(result.data)
    };

    return (
        <div className="container">
            <div className="row">
                <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
                    <h2 className="text-center m-4">Edit Review</h2>
                    <form onSubmit={(e) => onSubmit(e)}>
                        <div className="mb-3">
                            <label htmlFor="description" className="form-label">
                                Description
                            </label>
                            <textarea 
                                className="form-control"
                                placeholder="Enter the movie description"
                                name="description"
                                value={description}
                                onChange={(e) => onInputChange(e)}>
                            </textarea>
                        </div>
                        <div className="mb-3">
                            <label htmlFor="budget" className="form-label">
                                Rating
                            </label>
                            <input 
                                type="number"
                                className="form-control"
                                placeholder="Enter the budget (dollars)"
                                name="rating"
                                value={rating}
                                onChange={(e) => onInputChange(e)}
                                required
                            />
                        </div>
                        <button type="submit" className="btn btn-outline-primary">Edit</button>
                        <Link className="btn btn-outline-danger mx-2" to="/">Cancel</Link>
                    </form>
                </div>
            </div>
        </div>
    )
}