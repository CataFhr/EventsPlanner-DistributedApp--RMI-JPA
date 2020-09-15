package lib.service;

import lib.dto.EventDto;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;

public interface IEventService extends Remote {

    Collection<EventDto> findByUserId(int userId) throws RemoteException;

    void addEvent(EventDto eventDto) throws RemoteException;

    void deleteEvent(EventDto eventDto) throws RemoteException;

    Collection<EventDto> findAll() throws RemoteException;;



}
