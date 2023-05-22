import React, { useState } from "react";
import axios from "axios";
import { Link, useNavigate, useParams } from "react-router-dom";

export default function AddReview() {
  const navigate = useNavigate();
  const { movieId } = useParams();

  const [review, setReview] = useState({
    description: "",
    rating: "",
  });

  const { description, rating } = review;

  const onInputChange = (e) => {
    setReview({ ...review, [e.target.name]: e.target.value });
  };

  const onSubmit = async (e) => {
    e.preventDefault();
    try {
      await axios.post(`http://localhost:8080/api/reviews/${movieId}`, review);
      navigate("/");
    } catch (error) {
      if (error.response.status === 400 || error.response.status === 401) {
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
          <h2 className="text-center m-4">Create Review</h2>
          <form onSubmit={(e) => onSubmit(e)}>
            <div className="mb-3">
              <label htmlFor="description" className="form-label">
                Description
              </label>
              <textarea
                className="form-control"
                placeholder="Enter the review description"
                name="description"
                value={description}
                onChange={onInputChange}
              ></textarea>
            </div>
            <div className="mb-3">
              <label htmlFor="rating" className="form-label">
                Rating
              </label>
              <input
                type="number"
                className="form-control"
                placeholder="Rating"
                name="rating"
                value={rating}
                onChange={onInputChange}
                required
              />
            </div>
            <button type="submit" className="btn btn-outline-primary">
              Create
            </button>
            <Link className="btn btn-outline-danger mx-2" to="/">
              Cancel
            </Link>
          </form>
        </div>
      </div>
    </div>
  );
}
