import React, {useState} from "react";
import axios from "axios";
import { Link, useNavigate } from "react-router-dom";

export default function Login() {

    let navigate = useNavigate()

    const [user, setUser] = useState({
        username:"",
        password:""
    })

    const{username, password} = user

    const onInputChange=(e) => {

        setUser({...user,[e.target.name]: e.target.value})
    
    }

    const onSubmit = async (e) => {
        e.preventDefault();
        try {
            await axios.post("http://localhost:8080/api/users/authenticate", user)
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
                    <h2 className="text-center m-4">Login</h2>
                    <form onSubmit={(e) => onSubmit(e)}>
                        <div className="mb-3">
                            <label htmlFor="Username" className="form-label">
                                Username
                            </label>
                            <input 
                                type={"text"}
                                className="form-control"
                                placeholder="Enter your username"
                                name="username"
                                value={username}
                                onChange={(e) => onInputChange(e)}>
                                </input>
                        </div>
                        <div className="mb-3">
                            <label htmlFor="password" className="form-label">
                                Password
                            </label>
                            <input 
                                type="password"
                                className="form-control"
                                placeholder="Enter your password"
                                name="password"
                                value={password}
                                onChange={(e) => onInputChange(e)}>
                            </input>
                        </div>
                        <button type="submit" className="btn btn-outline-primary">Login</button>
                        <Link className="btn btn-outline-danger mx-2" to="/">Cancel</Link>
                    </form>
                </div>
            </div>
        </div>
    )
}