package edu.comillas.icai.gitt.pat.spring.practica5.repositorio;

import edu.comillas.icai.gitt.pat.spring.practica5.entity.LineaCarrito;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface LineaCarritoRepository extends CrudRepository<LineaCarrito, Long> {
    LineaCarrito findByIdLineaCarrito(Long idLineaCarrito);
    List<LineaCarrito> findByCarritoIdCarrito(Long idCarrito);
}
