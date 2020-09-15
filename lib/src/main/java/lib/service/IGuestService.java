package lib.service;

import lib.dto.GuestDto;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;

public interface IGuestService extends Remote {

    Collection<GuestDto> findByEventId(int eventId) throws RemoteException;

    void addGuest(GuestDto guestDto) throws RemoteException;

}
