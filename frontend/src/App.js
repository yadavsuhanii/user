import {
  BrowserRouter as Router,
  Routes,
  Route
} from "react-router-dom";
import './App.css';
import Signup from "./component/Signup";

function App() {
  return (
    <>
    <Router>
        <Routes>
        <Route path="/" element={<Signup />}></Route>
       
        </Routes>
      </Router>
    </>
  )
 
}

export default App;
