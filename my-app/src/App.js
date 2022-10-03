
import './App.css';
import Footer from './components/Footer';
import Header from './components/Header';
import { Main } from './components/Main';
import { Routes, Route } from "react-router-dom";
import { Register } from './components/Register';
import Login from './components/Login';

function App() {
  return (
    <div className="App">
      <Header></Header>
      <Routes>
        <Route exact path="/" element={<Main />} />
        <Route exact path={"/user-form"} element={<Register/>} />
        <Route exact path={"/login"} element={<Login />} />
      </Routes>
      <Footer></Footer>
    </div>
  );
}

export default App;
