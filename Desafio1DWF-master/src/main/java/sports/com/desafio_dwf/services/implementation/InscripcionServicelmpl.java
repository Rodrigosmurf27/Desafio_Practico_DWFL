package sports.com.desafio_dwf.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sports.com.desafio_dwf.repository.InscripcionRepository;
import sports.com.desafio_dwf.repository.models.Evento;
import sports.com.desafio_dwf.repository.models.Inscripcion;
import sports.com.desafio_dwf.services.EventoService;
import sports.com.desafio_dwf.services.InscripcionService;

import java.util.List;
import java.util.regex.Pattern;

@Service
public class InscripcionServicelmpl implements InscripcionService {

    @Autowired
    private InscripcionRepository inscripcionRepository;

    @Autowired
    private EventoService eventoService;

    @Override
    public List<Inscripcion> findAll() {
        return inscripcionRepository.findAll();
    }

    @Override
    public Inscripcion findById(Long id) {
        return inscripcionRepository.findById(id);
    }

    @Override
    public void save(Inscripcion inscripcion) throws Exception {
        validateDocumentFormat(inscripcion.getDocumentoIdentificacion());
        validatePhoneFormat(inscripcion.getTelefono());

        if (!validateRequirements(inscripcion)) {
            throw new Exception("El deportista no cumple con los requisitos para inscribirse en este evento.");
        }

        inscripcionRepository.save(inscripcion);
    }

    @Override
    public void update(Inscripcion inscripcion) throws Exception {
        validateDocumentFormat(inscripcion.getDocumentoIdentificacion());
        validatePhoneFormat(inscripcion.getTelefono());

        if (!validateRequirements(inscripcion)) {
            throw new Exception("El deportista no cumple con los requisitos para inscribirse en este evento.");
        }

        inscripcionRepository.update(inscripcion);
    }

    @Override
    public void delete(Inscripcion inscripcion) {
        inscripcionRepository.delete(inscripcion);
    }

    @Override
    public boolean validateRequirements(Inscripcion inscripcion) {
        Evento evento = inscripcion.getEvento();

        if (evento == null) {
            return false;
        }

        // Validar el rango de edad
        String[] rangoEdad = evento.getRangoEdad().split("-");
        if (rangoEdad.length == 2) {
            int edadMinima = Integer.parseInt(rangoEdad[0].trim());
            int edadMaxima = Integer.parseInt(rangoEdad[1].trim());

            if (inscripcion.getEdadDeportista() < edadMinima || inscripcion.getEdadDeportista() > edadMaxima) {
                return false;
            }
        }

        // Validar requerimiento de salud
        if (evento.getRequerimientoSalud() && inscripcion.getCondicionMedica()) {
            return false;
        }

        return true;
    }

    private void validateDocumentFormat(String documento) throws Exception {
        // Validar formato DUI (00000000-0)
        if (documento.matches("\\d{8}-\\d{1}")) {
            return;
        }

        // Si no es formato DUI, se asume que es pasaporte (formato más flexible)
        if (documento.length() >= 6 && documento.length() <= 20) {
            return;
        }

        throw new Exception("Formato de documento de identidad inválido. Debe ser DUI (00000000-0) o pasaporte válido.");
    }

    private void validatePhoneFormat(String telefono) throws Exception {
        // Validar formato internacional: +503XXXXXXXX
        Pattern pattern = Pattern.compile("\\+\\d{3}\\d{8}");
        if (!pattern.matcher(telefono).matches()) {
            throw new Exception("Formato de teléfono inválido. Debe ser formato internacional (ej: +503XXXXXXXX).");
        }
    }
}
