package edu.comillas.icai.gitt.pat.spring.practica5.Controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import edu.comillas.icai.gitt.pat.spring.practica5.entity.Carrito;
import edu.comillas.icai.gitt.pat.spring.practica5.entity.LineaCarrito;
import edu.comillas.icai.gitt.pat.spring.practica5.service.CarritoService;
import edu.comillas.icai.gitt.pat.spring.practica5.service.LineaCarritoService;

import java.util.List;


@RestController
@RequestMapping("/api/carrito")
public class ControladorCarrito {
    
    @Autowired
    private CarritoService carritoService;

    @Autowired
    private LineaCarritoService lineaCarritoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Carrito crearCarrito(@RequestBody Carrito carritoNuevo) {
        return carritoService.crearCarrito(carritoNuevo);
    }

    @GetMapping("/{idCarrito}")
    public Carrito buscarCarrito(@PathVariable Long idCarrito) {
        return carritoService.buscarCarritoPorId(idCarrito);
    }

    @PutMapping("/{idCarrito}")
    public Carrito actualizarCarrito(@PathVariable Long idCarrito,
                                    @RequestBody Carrito carritoActualizado) {
        return carritoService.actualizarCarrito(idCarrito, carritoActualizado);
    }

    @DeleteMapping("/{idCarrito}")
    public void eliminarCarrito(@PathVariable Long idCarrito) {
        carritoService.eliminarCarrito(idCarrito);
    }

    @PostMapping("/{idCarrito}/lineas")
    @ResponseStatus(HttpStatus.CREATED)
        public LineaCarrito crearLineaCarrito(@PathVariable Long idCarrito, @RequestBody LineaCarrito lineaNueva) {
        return lineaCarritoService.crearLinea(idCarrito, lineaNueva);
    }

    @DeleteMapping("/{idCarrito}/lineas/{idLineaCarrito}")
    public void eliminarLineaCarrito(@PathVariable Long idCarrito, @PathVariable Long idLineaCarrito) {
        lineaCarritoService.eliminarLinea(idLineaCarrito);
    }

    @GetMapping("/{idCarrito}/lineas")
    public List<LineaCarrito> obtenerLineasCarrito(@PathVariable Long idCarrito) {
        return lineaCarritoService.obtenerLineasPorCarrito(idCarrito);
    }
}
