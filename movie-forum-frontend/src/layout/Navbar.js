import React from 'react'
import { Link } from 'react-router-dom'

export default function Navbar() {
    return (
        <div>
            <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
                <div className="container-fluid">
                    <a className="navbar-brand">Movie Forum</a>
                    <div className="ms-auto">
                        <Link className="btn btn-outline-light mx-2" to="/addmovie">Add movie</Link>
                        <Link className="btn btn-outline-light mx-2" to="/register">Register</Link>
                        <Link className="btn btn-outline-light mx-2" to="/login">Login</Link>
                        <Link className="btn btn-outline-light mx-2" to="/logout">Logout</Link>
                    </div>
                </div>
            </nav>
        </div>
    )
}