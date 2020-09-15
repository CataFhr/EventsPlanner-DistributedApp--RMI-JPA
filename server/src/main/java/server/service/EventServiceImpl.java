package server.service;

import lib.dto.EventDto;
import lib.service.IEventService;
import server.convert.EventConvertor;
import server.dao.IEventDao;
import server.dao.IUserDao;
import server.dao.impl.EventDaoImpl;
import server.dao.impl.UserDaoImpl;
import server.model.Event;
import server.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

public class EventServiceImpl extends UnicastRemoteObject implements IEventService {

    private final IEventDao eventDao;
    private final IUserDao userDao;

    public EventServiceImpl() throws RemoteException {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("planner2PU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        eventDao = new EventDaoImpl(entityManager);
        userDao = new UserDaoImpl(entityManagerFactory.createEntityManager());
    }

    @Override
    public Collection<EventDto> findByUserId(int userId) throws RemoteException {
        return eventDao.findByUserId(userId).stream()
                .map(EventConvertor::convert)
                .collect(Collectors.toList());
    }

    @Override
    public void addEvent(EventDto eventDto) throws RemoteException {
        Optional<Event> optionalEvent = eventDao.findByDate(eventDto.getDate());
        if (optionalEvent.isEmpty()) {
            Event event = EventConvertor.convert(eventDto);
            User user = userDao.getById(eventDto.getUserId());
            event.setUser(user);
            eventDao.addEvent(event);
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void deleteEvent(EventDto eventDto) throws RemoteException {
        eventDao.deleteEvent(EventConvertor.convert(eventDto));
    }

    @Override
    public Collection<EventDto> findAll() throws RemoteException {
        return eventDao.findAll().stream().map(EventConvertor::convert).collect(Collectors.toList());
    }

}
