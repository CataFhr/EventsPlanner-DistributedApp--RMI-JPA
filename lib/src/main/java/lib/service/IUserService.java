package lib.service;

import lib.dto.UserDto;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IUserService extends Remote {

    int login(UserDto userDto) throws RemoteException;

    void registration(UserDto userDto) throws RemoteException;
}
