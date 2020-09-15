package server.dao.impl;

import server.dao.IEventDao;
import server.model.Event;
import server.model.User;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

public class EventDaoImpl implements IEventDao {

    private EntityManager entityManager;

    public EventDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Collection<Event> findByUserId(int userId) {
        TypedQuery<Event> query = entityManager.createQuery("SELECT e FROM Event e JOIN e.user u WHERE u.id = :id", Event.class);
        query.setParameter("id", userId);
        return query.getResultList();
    }

    @Override
    public void addEvent(Event event) {
                entityManager.getTransaction().begin();
        entityManager.persist(event);
        entityManager.getTransaction().commit();
    }

    @Override
    public Optional<Event> findByDate(LocalDate date) {
        TypedQuery<Event> query = entityManager.createQuery("SELECT e FROM Event e WHERE e.date = :date", Event.class);
        query.setParameter("date", date);
        return query.getResultStream().findFirst();
    }

    @Override
    public void deleteEvent(Event event) {
        entityManager.getTransaction().begin();
        event = entityManager.merge(event);
        entityManager.remove(event);
        entityManager.getTransaction().commit();
    }

    @Override
    public Collection<Event> findAll() {
        TypedQuery<Event> query = entityManager.createQuery("SELECT e FROM Event e", Event.class);
        return query.getResultList();
    }

    @Override
    public Event findById(int eventId) {
        return entityManager.find(Event.class, eventId);
    }

}
