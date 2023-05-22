import './App.css';
import "../node_modules/bootstrap/dist/css/bootstrap.min.css"
import Navbar from './layout/Navbar';
import Home from './pages/Home';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Login from './User/Login';
import Register from './User/Register';
import Logout from './User/Logout';
import AddMovie from './movies/AddMovie';
import EditMovie from './movies/EditMovie';
import DeleteMovie from './movies/DeleteMovie';
import AddReview from './reviews/AddReview';
import ViewReviews from './reviews/ViewReviews';
import DeleteReview from './reviews/DeleteReview';
import EditReview from './reviews/EditReview';

function App() {
  return (
    <div className="App">
      <Router>
        <Navbar/>
        <Routes>
          <Route exact path="/" element={<Home/>}></Route>
          <Route exact path="/login" element={<Login/>}></Route>
          <Route exact path="/logout" element={<Logout/>}></Route>
          <Route exact path="/register" element={<Register/>}></Route>
          <Route exact path="/addmovie" element={<AddMovie/>}></Route>
          <Route exact path="/editmovie/:id" element={<EditMovie/>}></Route>
          <Route exact path="/deletemovie/:id" element={<DeleteMovie/>}></Route>
          <Route exact path="/addreview/:movieId" element={<AddReview/>}></Route>
          <Route exact path="/reviews/:movieId" element={<ViewReviews/>}></Route>
          <Route exact path="/deletereview/:id/:movieId" element={<DeleteReview/>}></Route>
          <Route exact path="/editreview/:id/:movieId" element={<EditReview/>}></Route>
        </Routes>
      </Router>
    </div>
  );
}

export default App;