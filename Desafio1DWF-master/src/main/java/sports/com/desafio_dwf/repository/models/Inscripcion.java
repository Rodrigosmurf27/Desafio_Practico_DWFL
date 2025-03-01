package sports.com.desafio_dwf.repository.models;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Inscripcion {
    @Id
    private Long id;
    @Column(nullable = false)
    private String nombreDeportista;
    @Column(nullable = false)
    private String documentoIdentificacion;
    @Column(nullable = false)
    private String telefono;
    @Column(nullable = false)
    private Integer edadDeportista;
    @Column(nullable = false)
    private Boolean condicionMedica;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "evento_id")
    private Evento evento;

}
