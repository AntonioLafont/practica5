const CarritoAPI = {
  API_BASE: '/api/carrito',
  CARRITO_ID_KEY: 'carritoId',
  CARRITO_EMAIL_KEY: 'carritoEmail',


  async obtenerOCrearCarrito(email = 'usuario@default.com') {
    let carritoId = localStorage.getItem(this.CARRITO_ID_KEY);
    
    if (carritoId) {
      try {
        const response = await fetch(`${this.API_BASE}/${carritoId}`);
        if (response.ok) {
          return await response.json();
        }
      } catch (error) {
        console.error('Error al obtener carrito:', error);
      }
    }


    try {
      const response = await fetch(this.API_BASE, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({
          idUsuario: 1, 
          correo: email,
          totalPrecio: 0.0
        })
      });

      if (response.ok) {
        const nuevoCarrito = await response.json();
        localStorage.setItem(this.CARRITO_ID_KEY, nuevoCarrito.idCarrito);
        localStorage.setItem(this.CARRITO_EMAIL_KEY, email);
        return nuevoCarrito;
      }
    } catch (error) {
      console.error('Error al crear carrito:', error);
    }
    return null;
  },


  async agregarProducto(idArticulo, nombreArticulo, precio, cantidad = 1) {
      try {
        const carrito = await this.obtenerOCrearCarrito();
        if (!carrito) {
          alert('Error al crear/obtener carrito');
          return null;
        }

        const carritoId = carrito.idCarrito;

        const response = await fetch(`${this.API_BASE}/${carritoId}/lineas`, {
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify({
            idArticulo: idArticulo,
            numeroUnidades: cantidad,
            precioUnitario: precio
          })
        });

        if (response.ok) {
          const linea = await response.json();
          const nombres = JSON.parse(localStorage.getItem('articuloNombres') || '{}');
          nombres[idArticulo] = nombreArticulo;
          localStorage.setItem('articuloNombres', JSON.stringify(nombres));
          await this.actualizarTotalCarrito(carritoId);
          return linea;
        } else {
          alert('Error al agregar producto al carrito');
        }
      } catch (error) {
        console.error('Error al agregar producto:', error);
        alert('Error: ' + error.message);
      }
      return null;
    },


  async obtenerLineasCarrito(carritoId = null) {
    try {
      if (!carritoId) {
        carritoId = localStorage.getItem(this.CARRITO_ID_KEY);
      }

      if (!carritoId) {
        console.log('No hay carrito activo');
        return [];
      }

      const response = await fetch(`${this.API_BASE}/${carritoId}/lineas`);
      if (response.ok) {
        return await response.json();
      }
    } catch (error) {
      console.error('Error al obtener líneas:', error);
    }
    return [];
  },


  async eliminarLineaCarrito(idLineaCarrito) {
    try {
      const carritoId = localStorage.getItem(this.CARRITO_ID_KEY);
      if (!carritoId) {
        alert('No hay carrito activo');
        return false;
      }

      const response = await fetch(`${this.API_BASE}/${carritoId}/lineas/${idLineaCarrito}`, {
        method: 'DELETE'
      });

      if (response.ok) {
        await this.actualizarTotalCarrito(carritoId);
        return true;
      } else {
        alert('Error al eliminar producto');
        return false;
      }
    } catch (error) {
      console.error('Error al eliminar línea:', error);
      alert('Error: ' + error.message);
    }
    return false;
  },


  async actualizarTotalCarrito(carritoId) {
    try {
      const response = await fetch(`${this.API_BASE}/${carritoId}`);
      if (response.ok) {
        const carrito = await response.json();
        return carrito.totalPrecio;
      }
    } catch (error) {
      console.error('Error al actualizar total:', error);
    }
    return 0;
  },


  getCarritoIdActual() {
    return localStorage.getItem(this.CARRITO_ID_KEY);
  },


  limpiarCarritoLocal() {
    localStorage.removeItem(this.CARRITO_ID_KEY);
    localStorage.removeItem(this.CARRITO_EMAIL_KEY);
    localStorage.removeItem('articuloNombres');
  }
};
