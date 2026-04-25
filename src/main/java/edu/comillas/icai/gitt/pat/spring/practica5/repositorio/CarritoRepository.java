package edu.comillas.icai.gitt.pat.spring.practica5.repositorio;

import edu.comillas.icai.gitt.pat.spring.practica5.entity.Carrito;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CarritoRepository extends CrudRepository<Carrito, Long> {
    Carrito findByIdCarrito(Long idCarrito);
}
