package server.dao;

import server.model.Event;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

public interface IEventDao {

    Collection<Event> findByUserId(int userId);

    void addEvent(Event event);

     Optional<Event> findByDate(LocalDate date);

     void deleteEvent(Event event);

     Collection<Event> findAll();

    Event findById(int eventId);
}
