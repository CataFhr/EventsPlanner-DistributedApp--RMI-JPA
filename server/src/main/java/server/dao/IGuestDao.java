package server.dao;

import server.model.Guest;

import java.util.Collection;
import java.util.Optional;

public interface IGuestDao {

    Collection<Guest> findByEventId(int eventId);

    void addGuest(Guest guest);

    Optional<Guest> findByEmail(String email);

}
