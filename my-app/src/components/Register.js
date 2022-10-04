import React from 'react'

export const Register = () => {
  return (
    <div className="containerMain">
      <div className="row py-4">
        <br></br>
      </div>
      <div className="row p-4">
        <div className="col-8 bg-danger bg-opacity-50 rounded-4">
          <form className="row g-3 py-5">
            <h1 className="fw-bold text-center ">Registrarse</h1>
            <div className="mb-2 col-4">
              <input type="email" className="form-control" id="exampleFormControlInput1" placeholder="Ingrese su Email" />
            </div>

            <div className="mb-2 col-4">
              <input type="password" className="form-control" required placeholder="Contraseña" />
            </div>

            <div className="mb-2 col-4">
              <input type="password" className="form-control" required placeholder="Repita la Contraseña" />
            </div>
            <div className="mb-2 col-4">
              <input type="text" className="form-control" required placeholder="Nombre" />
            </div>
            <div className="mb-2 col-4">
              <input type="text" className="form-control" required placeholder="Apellido" />
            </div>
            <div className="mb-2 col-4">
              <input type="text" className="form-control" required placeholder="Celular" />
            </div>
            <div className="mb-2 col-4">
              <input type="text" className="form-control" placeholder="Cuenta de Facebook" />
            </div>

            <div className="mb-2 col-12">
              <button type="submit" className="button2">Acceder</button>
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
