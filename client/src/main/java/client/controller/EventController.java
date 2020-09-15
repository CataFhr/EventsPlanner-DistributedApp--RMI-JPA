package client.controller;

import lib.dto.EventDto;
import lib.service.IEventService;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Collection;

public class EventController {

    private final IEventService eventService;

    private EventController() {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 4545);
            eventService = (IEventService) registry.lookup("eventService");
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private static final class SingletonHolder {
        private static final EventController INSTANCE = new EventController();
    }

    public static EventController getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public Collection<EventDto> findByUserId(int userId) {
        try {
            return eventService.findByUserId(userId);
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void addEvent(EventDto eventDto) {
        try {
            eventService.addEvent(eventDto);
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void deleteEvent(EventDto eventDto) {
        try {
            eventService.deleteEvent(eventDto);
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public Collection<EventDto> findAll() {
        try {
            return eventService.findAll();
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


}
