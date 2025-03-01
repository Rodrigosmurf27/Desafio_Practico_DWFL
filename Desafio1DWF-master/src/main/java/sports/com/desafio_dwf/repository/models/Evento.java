package sports.com.desafio_dwf.repository.models;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Evento {
    @Id
    private Long id;
    @Column(nullable = false)
    private String nombreEvento;
    @Column(nullable = false)
    private LocalDate diaInicio;
    @Column(nullable = false)
    private LocalDate diaFin;
    @Column(nullable = false)
    private LocalTime horaInicio;
    @Column(nullable = false)
    private LocalTime horaFin;
    @Column(nullable = false)
    private String tipoEvento;
    @Column(nullable = false)
    private String rangoEdad;
    @Column(nullable = false)
    private Boolean requerimientoSalud;
    @Column(nullable = false)
    private Double costoTotal;
}
