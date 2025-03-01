package sports.com.desafio_dwf.repository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sports.com.desafio_dwf.repository.models.Inscripcion;
import java.util.List;

@Repository
public class InscripcionRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Inscripcion> findAll() {
        final String QUERY = "SELECT i FROM Inscripcion i";
        return entityManager.createQuery(QUERY, Inscripcion.class).getResultList();
    }
    public Inscripcion findById(final Long id) {
        return entityManager.find(Inscripcion.class, id);
    }

    @Transactional
    public void save(final Inscripcion inscripcion) {
        entityManager.persist(inscripcion);
    }
    @Transactional
    public void update(final Inscripcion inscripcion) {
        entityManager.merge(inscripcion);
    }

    @Transactional
    public void delete(final Inscripcion inscripcion) {
        entityManager.remove(entityManager.contains(inscripcion) ? inscripcion : entityManager.merge(inscripcion));
    }
}
