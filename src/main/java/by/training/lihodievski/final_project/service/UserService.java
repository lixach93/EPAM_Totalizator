package by.training.lihodievski.final_project.service;

import by.training.lihodievski.final_project.bean.User;
import by.training.lihodievski.final_project.service.exception.ServiceException;
import by.training.lihodievski.final_project.service.exception.UserException;

import java.util.List;

public interface UserService {

   void registration(String login, String email,String password, String confirmPassword) throws ServiceException, UserException;
   User login(String login , String password) throws ServiceException, UserException;
   User getUserById(long id) throws ServiceException;
   boolean updateBalance(long id, String cardNumberStr, String moneyStr) throws ServiceException;
   List<User> getUsers() throws ServiceException;

    boolean changeRole(String role, String idStr) throws ServiceException;
}
