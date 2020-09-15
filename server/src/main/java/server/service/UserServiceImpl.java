package server.service;

import lib.dto.UserDto;
import lib.service.IUserService;
import server.convert.UserConvertor;
import server.dao.IUserDao;
import server.dao.impl.UserDaoImpl;
import server.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Optional;

public class UserServiceImpl extends UnicastRemoteObject implements IUserService {

    private final IUserDao userDao;

    public UserServiceImpl() throws RemoteException {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("planner2PU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        userDao = new UserDaoImpl(entityManager);
    }

    @Override
    public int login(UserDto userDto) throws RemoteException {
        Optional<User> optionalUser = userDao.findByUsername(userDto.getUsername());
        if (!optionalUser.isEmpty()) {
            if (optionalUser.get().getPassword().equals(userDto.getPassword())) {
                return optionalUser.get().getId();
            }
        }
        throw new IllegalArgumentException();
    }

    @Override
    public void registration(UserDto userDto) throws RemoteException {
        Optional<User> optionalUser = userDao.findByUsername(userDto.getUsername());
        if (optionalUser.isEmpty()) {
            userDao.addUser(UserConvertor.convert(userDto)).getId();
        } else {
            throw new IllegalArgumentException();
        }
    }

}
