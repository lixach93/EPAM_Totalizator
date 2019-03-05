package by.training.lihodievski.final_project.dao;

import by.training.lihodievski.final_project.bean.Entity;
import by.training.lihodievski.final_project.dao.exception.DaoException;

import java.util.List;

public interface GenericDao<T extends Entity> {

    List<T> getAll() throws DaoException;
    void delete(T object) throws DaoException;
    int update(T object) throws DaoException;
    void insert(T object ) throws DaoException;


}
