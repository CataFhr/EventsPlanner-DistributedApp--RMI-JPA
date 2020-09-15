package client.controller;

import lib.dto.UserDto;
import lib.service.IUserService;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class UserController {

    private final IUserService userService;

    private UserController() {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 4545);
            userService = (IUserService) registry.lookup("userService");
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private static final class SingletonHolder {
        private static final UserController INSTANCE = new UserController();
    }

    public static UserController getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public int login(UserDto userDto) {
        try {
            return userService.login(userDto);
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void registration(UserDto userDto) {
        try {
            userService.registration(userDto);
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
