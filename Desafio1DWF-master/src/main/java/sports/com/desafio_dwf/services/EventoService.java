// EventoService correcto
package sports.com.desafio_dwf.services;

import sports.com.desafio_dwf.repository.models.Evento;
import java.util.List;

public interface EventoService {
    List<Evento> findAll();
    Evento findById(Long id);
    void save(Evento evento) throws Exception;
    void update(Evento evento) throws Exception;
    void delete(Evento evento);
    boolean validateEventDates(Evento evento);
}