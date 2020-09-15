package server.service;

import lib.dto.GuestDto;
import lib.service.IGuestService;
import server.convert.GuestConvertor;
import server.dao.IEventDao;
import server.dao.IGuestDao;
import server.dao.impl.EventDaoImpl;
import server.dao.impl.GuestDaoImpl;
import server.model.Event;
import server.model.Guest;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

public class GuestServiceImpl extends UnicastRemoteObject implements IGuestService {

    private final IGuestDao guestDao;
    private final IEventDao eventDao;

    public GuestServiceImpl() throws RemoteException {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("planner2PU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        guestDao = new GuestDaoImpl(entityManager);
        eventDao = new EventDaoImpl(entityManagerFactory.createEntityManager());
    }


    @Override
    public Collection<GuestDto> findByEventId(int eventId) throws RemoteException {
        return guestDao.findByEventId(eventId).stream()
                .map(GuestConvertor::convert)
                .collect(Collectors.toList());
    }

    @Override
    public void addGuest(GuestDto guestDto) throws RemoteException {
        Optional<Guest> optionalGuest = guestDao.findByEmail(guestDto.getEmail());
        if (optionalGuest.isEmpty()) {
            Guest guest = GuestConvertor.convert(guestDto);
            Event event = eventDao.findById(guestDto.getEventId());
            guest.setEvent(event);
            guestDao.addGuest(guest);
        } else {
            throw new IllegalArgumentException();
        }
    }

}
