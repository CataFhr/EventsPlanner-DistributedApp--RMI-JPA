package server;

import server.service.EventServiceImpl;
import server.service.GuestServiceImpl;
import server.service.UserServiceImpl;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class MainServer {

    public static void main(String[] args) throws RemoteException {
        Registry registry = LocateRegistry.createRegistry(4545);
        registry.rebind("userService", new UserServiceImpl());
        registry.rebind("eventService", new EventServiceImpl());
        registry.rebind("guestService", new GuestServiceImpl());
    }
}
