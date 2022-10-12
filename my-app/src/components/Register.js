import axios from 'axios';
import React, { useState } from 'react'
import Swal from 'sweetalert2';

export const Register = () => {

  const [data,setdata] = useState({email: "", password:"",password2:"aaaaa" ,name: "",lastName: "",phone: "",facebookAccount:""});

  const handleChange = ({target})=>{
    setdata({
      ...data,
      [target.name]:target.value
    })
    
  }

  const URL = "http://localhost:8080/user/register/"
  const handleSubmit = async (e)=>{
    e.preventDefault();
    try {
      const response = await axios.post(URL,data)
    console.log(response);
        /*Swal.fire(
          'Registrado!',
          'El usuario $(response.data.email) ha sido guardado exitosamente',
          'success'
        )*/
    } catch (error) {
      console.log(error)
    }
    
  }

  return (
    <div className="containerMain">
      
      <div className="row p-5 align-items-center justify-content-center">
        <div className="col-6 bg-danger bg-opacity-50 rounded-4">
          <form className="row g-3 py-5" onSubmit={handleSubmit}>
            <h1 className="fw-bold text-center ">Ingrese sus datos</h1>
            <div className="mb-2 col-4">
              <input type="email" className="form-control"  placeholder="Ingrese su Email" name="email" value={data.email} onChange={handleChange} />
            </div>

            <div className="mb-2 col-4">
              <input type="password" className="form-control" required placeholder="Contraseña" name="password" value={data.password}  onChange={handleChange}/>
            </div>
            <div className="mb-2 col-4">
              <input type="text" className="form-control" required placeholder="Nombre" name="name" value={data.name} onChange={handleChange}/>
            </div>
            <div className="mb-2 col-4">
              <input type="text" className="form-control" required placeholder="Apellido" name="lastName" value={data.lastName} onChange={handleChange}/>
            </div>
            <div className="mb-2 col-4">
              <input type="text" className="form-control" required placeholder="Celular" name="phone" value={data.phone}  onChange={handleChange}/>
            </div>
            <div className="mb-2 col-4">
              <input type="text" className="form-control" placeholder="Cuenta de Facebook" name="facebookAccount" value={data.facebookAccount} onChange={handleChange} />
            </div>

            <div className="mb-2 col-12">
              <button type="submit" className="button2">Registrarse</button>
            </div>

          </form>
        </div>
        <div className="col-4">
          <h1>Lo acercamos a su mascota</h1>
          <p>lorem ipsum dolor sit amet, consectetur adip lorem ipsum dolor lorem ipsum dolor sit amet, consectetur adip nonum ipsum d -flex   et nonum ipsum dolor lorem ipsum dolor sit amet, consectetur adip   nonum ipsum dolor lorem ipsum dolor     sit amet, consectetur adip nonum ipsum dolor      lorem ipsum dolor     sit amet, consectetur adip   nonum ipsum dolor        lorem ipsum dolor     sit amet, consectetur adip   nonum ipsum dolor      lorem ipsum dolor     sit amet, consectetur adip   nonum ipsum dolor        lorem ipsum dolor     sit amet, consectetur adip   nonum ipsum dolor      lorem ipsum dolor     sit amet, consectetur adip   nonum ipsum dolor      lorem ipsum dolor     sit amet, consectetur adip   nonum ipsum dolor        lorem ipsum dolor sit amet        , consectetur adip
          </p>
        </div>

      </div>
    </div>
  )
}
