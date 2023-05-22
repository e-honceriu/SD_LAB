import React, {useState} from "react";
import axios from "axios";
import { Link, useNavigate } from "react-router-dom";

export default function AddMovie() {

    let navigate = useNavigate()

    const [movie, setMovie] = useState({
        title:"",
        releaseDate:"",
        runtime:"",
        description:"",
        budget:""
    })

    const{title, releaseDate, runtime, description, budget} = movie

    const onInputChange=(e) => {

        setMovie({...movie,[e.target.name]: e.target.value})
    
    }

    const onSubmit = async (e) => {
        e.preventDefault();
        try {
            await axios.post("http://localhost:8080/api/movies", movie)
            navigate("/")
        } catch(error) {
            if (error.response.status === 400 || error.response.status === 401) {
                alert(error.response.data);
            } else {
                alert("An error occurred. Please try again later.");
            }
        }
    }

    return (
        <div className="container">
            <div className="row">
                <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
                    <h2 className="text-center m-4">Create Movie</h2>
                    <form onSubmit={(e) => onSubmit(e)}>
                        <div className="mb-3">
                            <label htmlFor="title" className="form-label">
                                Title
                            </label>
                            <input 
                                type={"text"}
                                className="form-control"
                                placeholder="Enter the movie title"
                                name="title"
                                value={title}
                                onChange={(e) => onInputChange(e)}>
                                </input>
                        </div>
                        <div className="mb-3">
                            <label htmlFor="releaseDate" className="form-label">
                                Release Date
                            </label>
                            <input 
                                type="date"
                                className="form-control"
                                placeholder="Enter the release date"
                                name="releaseDate"
                                value={releaseDate}
                                onChange={(e) => onInputChange(e)}>
                            </input>
                        </div>
                        <div className="mb-3">
                            <label htmlFor="runtime" className="form-label">
                                Runtime
                            </label>
                            <input 
                                type="number"
                                className="form-control"
                                placeholder="Enter the runtime (minutes)"
                                name="runtime"
                                value={runtime}
                                onChange={(e) => onInputChange(e)}
                                required
                            />
                        </div>
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
                                Budget
                            </label>
                            <input 
                                type="number"
                                className="form-control"
                                placeholder="Enter the budget (dollars)"
                                name="budget"
                                value={budget}
                                onChange={(e) => onInputChange(e)}
                                required
                            />
                        </div>
                        <button type="submit" className="btn btn-outline-primary">Create</button>
                        <Link className="btn btn-outline-danger mx-2" to="/">Cancel</Link>
                    </form>
                </div>
            </div>
        </div>
    )
}