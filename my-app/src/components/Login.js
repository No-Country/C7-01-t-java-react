import React from 'react'
import { Link } from 'react-router-dom'

const Login = () => {
    return (
        <div className="containerMain">
            <div className="row justify-content-between">
                <br></br>
            </div>
            <div className="row ">
                <div className="container col-4 bg-danger bg-opacity-50 rounded-4">
                    <form className="row g-3 py-5">
                    <h1 className="fw-bold text-center ">Iniciar Sesion</h1>
                        <div className="mb-4">
                            <label className="form-label">Usuario</label>
                            <input type="email" className="form-control" id="exampleFormControlInput1" placeholder="name@example.com" />
                        </div>

                        <div className="mb-4">
                            <label className="form-label">Contraseña</label>
                            <input type="password" className="form-control" id="exampleFormControlInput1"  />
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
