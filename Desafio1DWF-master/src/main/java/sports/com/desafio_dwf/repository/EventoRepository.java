package sports.com.desafio_dwf.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sports.com.desafio_dwf.repository.models.Evento;
import java.util.List;

@Repository
public class EventoRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Evento> findAll() {
        final String QUERY = "SELECT e FROM Evento e";
        return entityManager.createQuery(QUERY, Evento.class).getResultList();
    }
    public Evento findById(final Long id) {
        return entityManager.find(Evento.class, id);
    }

    @Transactional
    public void save(final Evento evento) {
        entityManager.persist(evento);
    }

    @Transactional
    public void update(final Evento evento) {
        entityManager.merge(evento);
    }
    @Transactional
    public void delete(final Evento evento) {
        entityManager.remove(entityManager.contains(evento) ? evento : entityManager.merge(evento));
    }
}
