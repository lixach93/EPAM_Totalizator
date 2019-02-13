package by.training.lihodievski.final_project.dao;

import by.training.lihodievski.final_project.dao.impl.CategoryDaoImpl;

public class DaoFactory {

    private final static DaoFactory INSTANCE = new DaoFactory ();

    private DaoFactory() {
    }
    private static DaoFactory getInstance(){
        return INSTANCE;
    }

    private static CategoryDaoAbstract getfoo(){
        return CategoryDaoImpl.getInstance ();
    }
}
