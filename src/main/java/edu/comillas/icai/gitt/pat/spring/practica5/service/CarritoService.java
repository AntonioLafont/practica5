package edu.comillas.icai.gitt.pat.spring.practica5.service;

import edu.comillas.icai.gitt.pat.spring.practica5.entity.Carrito;
import edu.comillas.icai.gitt.pat.spring.practica5.repositorio.CarritoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class CarritoService {
    
    @Autowired
    private CarritoRepository carritoRepository;
    
    public Carrito crearCarrito(Carrito carrito) {
        if (carrito.getIdUsuario() == null || carrito.getCorreo() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Los campos idUsuario y correo son obligatorios");
        }
        return carritoRepository.save(carrito);
    }
    
  
    public Carrito buscarCarritoPorId(Long idCarrito) {
        Carrito carrito = carritoRepository.findByIdCarrito(idCarrito);
        if (carrito == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Carrito con ID " + idCarrito + " no encontrado");
        }
        return carrito;
    }
    

    public Carrito actualizarCarrito(Long idCarrito, Carrito carritoActualizado) {
        Carrito carritoExistente = buscarCarritoPorId(idCarrito);
        
        if (carritoActualizado.getIdUsuario() != null) {
            carritoExistente.setIdUsuario(carritoActualizado.getIdUsuario());
        }
        if (carritoActualizado.getCorreo() != null) {
            carritoExistente.setCorreo(carritoActualizado.getCorreo());
        }
        
        return carritoRepository.save(carritoExistente);
    }
    

    public void eliminarCarrito(Long idCarrito) {
        if (!carritoRepository.existsById(idCarrito)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Carrito con ID " + idCarrito + " no encontrado");
        }
        carritoRepository.deleteById(idCarrito);
    }
    
}
