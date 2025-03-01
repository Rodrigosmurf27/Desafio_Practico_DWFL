package sports.com.desafio_dwf.services;

import sports.com.desafio_dwf.repository.models.Inscripcion;
import java.util.List;

public interface InscripcionService {
    List<Inscripcion> findAll();
    Inscripcion findById(Long id);
    void save(Inscripcion inscripcion) throws Exception;
    void update(Inscripcion inscripcion) throws Exception;
    void delete(Inscripcion inscripcion);
    boolean validateRequirements(Inscripcion inscripcion);
}
