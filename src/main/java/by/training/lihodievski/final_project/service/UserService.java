package by.training.lihodievski.final_project.service;

import by.training.lihodievski.final_project.bean.User;
import by.training.lihodievski.final_project.service.exception.ServiceException;
import by.training.lihodievski.final_project.service.exception.UserException;

public interface UserService {

   void registration(String login, String email,String password, String confirmPassword) throws ServiceException, UserException;
   User login(String login , String password) throws ServiceException, UserException;
}
