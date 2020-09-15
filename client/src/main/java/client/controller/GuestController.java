package client.controller;

import lib.dto.GuestDto;
import lib.service.IGuestService;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Collection;

public class GuestController {

    private final IGuestService guestService;

    private GuestController() {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 4545);
            guestService = (IGuestService) registry.lookup("guestService");
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private static final class SingletonHolder {
        private static final GuestController INSTANCE = new GuestController();
    }

    public static GuestController getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public Collection<GuestDto> findByEventId(int eventId) {
        try {
            return guestService.findByEventId(eventId);
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void addGuest(GuestDto guestDto) {
        try {
            guestService.addGuest(guestDto);
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
