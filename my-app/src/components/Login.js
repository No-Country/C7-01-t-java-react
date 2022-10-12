import axios from 'axios';
import React, { useState } from 'react'
import { Link } from 'react-router-dom'

const Login = () => {
    const [data,setdata] = useState({email: "", password:""});

  const handleChange = ({target})=>{
    setdata({
      ...data,
      [target.name]:target.value
    })
    
  }

  const URL = "http://localhost:8080/login"
  const handleSubmit = async (e)=>{
    e.preventDefault();
    const response = await axios.get(URL,data)
    
    console.log(response);
  }

    return (
        <div className="containerMain">

            <div className="row p-5 align-items-center justify-content-center">
                <div className="container col-4 bg-danger bg-opacity-50 rounded-4">
                    <form className="row g-3 py-5" onSubmit={handleSubmit}>
                    <h1 className="fw-bold text-center ">Iniciar Sesion</h1>
                        <div className="mb-4">
                            <label className="form-label">Usuario</label>
                            <input type="email" className="form-control" name="email" value={data.email} onChange={handleChange} placeholder="name@example.com" required/>
                        </div>

                        <div className="mb-4">
                            <label className="form-label">Contraseña</label>
                            <input type="password" className="form-control" name="password" value={data.password} onChange={handleChange} required  />
                        </div>

                        <div className="d-grid">
                            <button type="submit" className="button2">Acceder</button>
                        </div>
                        <div className="my-3">
                            <span>No tienes cuenta? <Link to={"/user-form"}>Registrarse</Link></span><br/>
                            <span>Recuperar Contraseña</span><br/>

                        </div>
                    </form>
                </div>

            </div>

        </div>
    )
}

export default Login
