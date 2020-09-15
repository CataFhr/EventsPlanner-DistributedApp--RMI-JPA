package server.dao.impl;

import server.dao.IGuestDao;
import server.model.Guest;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Collection;
import java.util.Optional;

public class GuestDaoImpl implements IGuestDao {

    private EntityManager entityManager;

    public GuestDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Collection<Guest> findByEventId(int eventId) {
        TypedQuery<Guest> query = entityManager.createQuery("SELECT g FROM Guest g JOIN g.event e WHERE e.id = :id", Guest.class);
        query.setParameter("id", eventId);
        return query.getResultList();
    }

    @Override
    public void addGuest(Guest guest) {
        entityManager.getTransaction().begin();
        entityManager.persist(guest);
        entityManager.getTransaction().commit();
    }

    @Override
    public Optional<Guest> findByEmail(String email) {
        TypedQuery<Guest> query = entityManager.createQuery("SELECT g FROM Guest g WHERE g.email = :email", Guest.class);
        query.setParameter("email", email);
        return query.getResultStream().findFirst();
    }

}
