package sports.com.desafio_dwf;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import sports.com.desafio_dwf.repository.EventoRepository;
import sports.com.desafio_dwf.repository.InscripcionRepository;
import sports.com.desafio_dwf.repository.models.Evento;
import sports.com.desafio_dwf.repository.models.Inscripcion;
import sports.com.desafio_dwf.services.EventoService;
import sports.com.desafio_dwf.services.InscripcionService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class ServiceImplementationTests {

    @Autowired
    private EventoService eventoService;

    @Autowired
    private InscripcionService inscripcionService;

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private InscripcionRepository inscripcionRepository;

    @Test
    public void registrarEventoExitosamente() throws Exception {

        Evento evento = Evento.builder()
                .id(100L) // ID único que no colisiona con datos precargados
                .nombreEvento("Torneo de Fútbol Test")
                .diaInicio(LocalDate.of(2025, 4, 15))
                .diaFin(LocalDate.of(2025, 4, 16))
                .horaInicio(LocalTime.of(8, 0))
                .horaFin(LocalTime.of(17, 0))
                .tipoEvento("Deportivo")
                .rangoEdad("15-25")
                .requerimientoSalud(false)
                .costoTotal(50.0)
                .build();


        eventoService.save(evento);


        Evento eventoGuardado = eventoService.findById(100L);
        assertNotNull(eventoGuardado);
        assertEquals("Torneo de Fútbol Test", eventoGuardado.getNombreEvento());
    }

    @Test
    public void intentarRegistrarEventoConFechasInvalidas() {

        Evento evento = Evento.builder()
                .id(101L)
                .nombreEvento("Torneo de Fútbol Inválido")
                .diaInicio(LocalDate.of(2025, 4, 16)) // Fecha posterior a la de fin
                .diaFin(LocalDate.of(2025, 4, 15))
                .horaInicio(LocalTime.of(8, 0))
                .horaFin(LocalTime.of(17, 0))
                .tipoEvento("Deportivo")
                .rangoEdad("15-25")
                .requerimientoSalud(false)
                .costoTotal(50.0)
                .build();


        Exception exception = assertThrows(Exception.class, () -> {
            eventoService.save(evento);
        });

        assertEquals("Las fechas del evento son inválidas. La fecha de fin debe ser después de la fecha de inicio.", exception.getMessage());
        assertNull(eventoService.findById(101L));
    }

    @Test
    public void inscripcionExitosaDeportista() throws Exception {

        Evento evento = Evento.builder()
                .id(300L)
                .nombreEvento("Torneo para Inscripción")
                .diaInicio(LocalDate.of(2025, 4, 15))
                .diaFin(LocalDate.of(2025, 4, 16))
                .horaInicio(LocalTime.of(8, 0))
                .horaFin(LocalTime.of(17, 0))
                .tipoEvento("Deportivo")
                .rangoEdad("15-25")
                .requerimientoSalud(false)
                .costoTotal(50.0)
                .build();

        eventoService.save(evento);

        Inscripcion inscripcion = Inscripcion.builder()
                .id(300L)
                .nombreDeportista("Juan Pérez Test")
                .documentoIdentificacion("12345678-9")
                .telefono("+50312345678")
                .edadDeportista(20)
                .condicionMedica(false)
                .evento(evento)
                .build();


        inscripcionService.save(inscripcion);


        Inscripcion inscripcionGuardada = inscripcionService.findById(300L);
        assertNotNull(inscripcionGuardada);
        assertEquals("Juan Pérez Test", inscripcionGuardada.getNombreDeportista());
        assertEquals(300L, inscripcionGuardada.getEvento().getId());
    }

    @Test
    public void intentarInscribirseAEventoInexistente() {

        Inscripcion inscripcion = Inscripcion.builder()
                .id(301L)
                .nombreDeportista("Juan Pérez Test")
                .documentoIdentificacion("12345678-9")
                .telefono("+50312345678")
                .edadDeportista(20)
                .condicionMedica(false)
                .evento(null) // Evento inexistente
                .build();


        Exception exception = assertThrows(Exception.class, () -> {
            inscripcionService.save(inscripcion);
        });

        assertEquals("El evento no existe.", exception.getMessage());
        assertNull(inscripcionService.findById(301L));
    }

    @Test
    public void intentarInscribirseAEventoConCondicionNoCumplida() throws Exception {

        Evento evento = Evento.builder()
                .id(302L)
                .nombreEvento("Torneo para Inscripción")
                .diaInicio(LocalDate.of(2025, 4, 15))
                .diaFin(LocalDate.of(2025, 4, 16))
                .horaInicio(LocalTime.of(8, 0))
                .horaFin(LocalTime.of(17, 0))
                .tipoEvento("Deportivo")
                .rangoEdad("15-25")
                .requerimientoSalud(false)
                .costoTotal(50.0)
                .build();

        eventoService.save(evento);

        Inscripcion inscripcion = Inscripcion.builder()
                .id(302L)
                .nombreDeportista("Juan Fuera de Rango")
                .documentoIdentificacion("12345678-9")
                .telefono("+50312345678")
                .edadDeportista(30) // Fuera del rango 15-25
                .condicionMedica(false)
                .evento(evento)
                .build();


        Exception exception = assertThrows(Exception.class, () -> {
            inscripcionService.save(inscripcion);
        });

        assertEquals("El deportista no cumple con los requisitos para inscribirse en este evento.", exception.getMessage());
        assertNull(inscripcionService.findById(302L));
    }

    @Test
    public void persistenciaCorrectaDeNuevoEvento() throws Exception {

        Evento evento = Evento.builder()
                .id(400L)
                .nombreEvento("Nuevo Torneo")
                .diaInicio(LocalDate.of(2025, 5, 10))
                .diaFin(LocalDate.of(2025, 5, 11))
                .horaInicio(LocalTime.of(9, 0))
                .horaFin(LocalTime.of(18, 0))
                .tipoEvento("Deportivo")
                .rangoEdad("18-30")
                .requerimientoSalud(false)
                .costoTotal(100.0)
                .build();


        eventoService.save(evento);


        Evento eventoGuardado = eventoService.findById(400L);
        assertNotNull(eventoGuardado);
        assertEquals("Nuevo Torneo", eventoGuardado.getNombreEvento());
        assertEquals(LocalDate.of(2025, 5, 10), eventoGuardado.getDiaInicio());
        assertEquals(LocalDate.of(2025, 5, 11), eventoGuardado.getDiaFin());
        assertEquals(LocalTime.of(9, 0), eventoGuardado.getHoraInicio());
        assertEquals(LocalTime.of(18, 0), eventoGuardado.getHoraFin());
        assertEquals("Deportivo", eventoGuardado.getTipoEvento());
        assertEquals("18-30", eventoGuardado.getRangoEdad());
        assertFalse(eventoGuardado.getRequerimientoSalud());
        assertEquals(100.0, eventoGuardado.getCostoTotal());
    }

    @Test
    public void persistenciaCorrectaDeDeportistaAEvento() throws Exception {

        Evento evento = Evento.builder()
                .id(401L)
                .nombreEvento("Torneo para Inscripción")
                .diaInicio(LocalDate.of(2025, 4, 15))
                .diaFin(LocalDate.of(2025, 4, 16))
                .horaInicio(LocalTime.of(8, 0))
                .horaFin(LocalTime.of(17, 0))
                .tipoEvento("Deportivo")
                .rangoEdad("15-25")
                .requerimientoSalud(false)
                .costoTotal(50.0)
                .build();

        eventoService.save(evento);

        Inscripcion inscripcion = Inscripcion.builder()
                .id(401L)
                .nombreDeportista("Juan Pérez Test")
                .documentoIdentificacion("12345678-9")
                .telefono("+50312345678")
                .edadDeportista(20)
                .condicionMedica(false)
                .evento(evento)
                .build();


        inscripcionService.save(inscripcion);


        Inscripcion inscripcionGuardada = inscripcionService.findById(401L);
        assertNotNull(inscripcionGuardada);
        assertEquals("Juan Pérez Test", inscripcionGuardada.getNombreDeportista());
        assertEquals("12345678-9", inscripcionGuardada.getDocumentoIdentificacion());
        assertEquals("+50312345678", inscripcionGuardada.getTelefono());
        assertEquals(20, inscripcionGuardada.getEdadDeportista());
        assertFalse(inscripcionGuardada.getCondicionMedica());
        assertEquals(401L, inscripcionGuardada.getEvento().getId());
    }

    @Test
    public void listarEventosYDeportistasDisponibles() {

        List<Evento> eventos = eventoService.findAll();
        List<Inscripcion> inscripciones = inscripcionService.findAll();


        assertNotNull(eventos);
        assertFalse(eventos.isEmpty());
        assertNotNull(inscripciones);
        assertFalse(inscripciones.isEmpty());
    }
}