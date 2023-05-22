import React, { useEffect, useState } from 'react';
import { Link, useParams } from 'react-router-dom';
import axios from 'axios';

export default function ViewReviews() {
  const [reviews, setReviews] = useState([]);
  const { movieId } = useParams();

  const loadReviews = async () => {
    const result = await axios.get(`http://localhost:8080/api/reviews/movies/${movieId}`);
    setReviews(result.data);
  };


  useEffect(() => {
    loadReviews();
  }, []);

  return (
    <div className='container'>
      <div className="my-4">
        <Link to='/' className='btn btn-outline-primary' style={{ padding: '10px 20px' }}>
          Back
        </Link>
      </div>
      <div className='py-4'>
        <table className='table'>
          <thead>
            <tr>
              <th scope='col'>User</th>
              <th scope='col'>Description</th>
              <th scope='col'>Rating</th>
              <th scope='col'>Action</th>
            </tr>
          </thead>
          <tbody>
            {reviews.map((review) => (
              <tr key={review.id}>
                <th scope='row'>{review.reviewer.username}</th>
                <td>{review.description}</td>
                <td>{review.rating}</td>
                <td>
                  <Link className='btn btn-outline-primary mx-2' to={`/editreview/${review.id}/${movieId}`}>
                    Edit
                  </Link>
                  <Link className='btn btn-danger mx-2' to={`/deletereview/${review.id}/${movieId}`}>
                    Delete
                  </Link>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}
