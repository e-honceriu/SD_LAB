import axios from 'axios';
import React, { useEffect, useState } from 'react'
import { Link } from 'react-router-dom'

export default function Home() {

    const [movies, setMovies]=useState([])

    useEffect((e) => {
       loadMovies();
    }, []);

    const loadMovies=async()=> {
        const result = await axios.get("http://localhost:8080/api/movies")
        setMovies(result.data)
    }


  return (
    <div className='container'>
        Movie Forum Page
        <div className='py-4'>
            <table className="table">
                <thead>
                    <tr>
                    <th scope="col">Title</th>
                    <th scope="col">Release Date</th>
                    <th scope="col">Runtime</th>
                    <th scope="col">Description</th>
                    <th scope="col">Budget</th>
                    <th scope="col">Action</th>
                    </tr>
                </thead>
                <tbody>

                    {
                        movies.map((movie)=>(
                            <tr>
                            <th scope="row">{movie.title}</th>
                            <td>{movie.releaseDate}</td>
                            <td>{movie.runtime}</td>
                            <td>{movie.description}</td>
                            <td>{movie.budget}</td>
                            <td>
                                <Link className="btn btn-outline-primary mx-2"
                                 to={`/editmovie/${movie.id}`}
                                 >Edit</Link>
                                <Link className="btn btn-outline-primary mx-2"
                                to={`/addreview/${movie.id}`}>Add Review</Link>
                                <Link className="btn btn-outline-primary mx-2"
                                to={`/reviews/${movie.id}`}>View Reviews</Link>
                                <Link className="btn btn-danger mx-2" 
                                to={`/deletemovie/${movie.id}`}
                                >Delete</Link>                            
                            </td>
                            </tr>
                        ))
                    }
                   
                </tbody>
            </table>
        </div>
    </div>
  )
}
