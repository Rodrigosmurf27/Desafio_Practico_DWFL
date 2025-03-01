package sports.com.desafio_dwf.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sports.com.desafio_dwf.repository.EventoRepository;
import sports.com.desafio_dwf.repository.models.Evento;
import sports.com.desafio_dwf.services.EventoService;

import java.util.List;

@Service
public class EventoServicelmpl implements EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    @Override
    public List<Evento> findAll() {
        return eventoRepository.findAll();
    }

    @Override
    public Evento findById(Long id) {
        return eventoRepository.findById(id);
    }

    @Override
    public void save(Evento evento) throws Exception {
        if (!validateEventDates(evento)) {
            throw new Exception("Las fechas del evento son inválidas. La fecha de fin debe ser después de la fecha de inicio.");
        }

        if (evento.getCostoTotal() < 0) {
            throw new Exception("El costo del evento no puede ser negativo.");
        }

        eventoRepository.save(evento);
    }

    @Override
    public void update(Evento evento) throws Exception {
        if (!validateEventDates(evento)) {
            throw new Exception("Las fechas del evento son inválidas. La fecha de fin debe ser después de la fecha de inicio.");
        }

        if (evento.getCostoTotal() < 0) {
            throw new Exception("El costo del evento no puede ser negativo.");
        }

        eventoRepository.update(evento);
    }

    @Override
    public void delete(Evento evento) {
        eventoRepository.delete(evento);
    }

    @Override
    public boolean validateEventDates(Evento evento) {
        // Validar que la fecha de fin sea posterior a la fecha de inicio
        if (evento.getDiaFin().isBefore(evento.getDiaInicio())) {
            return false;
        }

        // Si las fechas son iguales, validar que la hora de fin sea posterior a la hora de inicio
        if (evento.getDiaFin().isEqual(evento.getDiaInicio()) &&
                evento.getHoraFin().isBefore(evento.getHoraInicio())) {
            return false;
        }

        return true;
    }
}
