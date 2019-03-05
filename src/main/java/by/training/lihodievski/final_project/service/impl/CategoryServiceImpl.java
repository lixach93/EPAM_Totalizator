package by.training.lihodievski.final_project.service.impl;


import by.training.lihodievski.final_project.bean.Category;
import by.training.lihodievski.final_project.dao.impl.category.CategoryDaoAbstract;
import by.training.lihodievski.final_project.dao.factory.DaoFactory;
import by.training.lihodievski.final_project.dao.exception.DaoException;
import by.training.lihodievski.final_project.service.CategoryService;
import by.training.lihodievski.final_project.service.exception.ServiceException;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {

    private final static CategoryServiceImpl INSTANCE = new CategoryServiceImpl ();
    private final DaoFactory daoFactory = DaoFactory.getInstance ();
    private final CategoryDaoAbstract categoryDao = daoFactory.getCategoryDao ();
    private CategoryServiceImpl(){}
    public static CategoryServiceImpl getInstance(){
        return INSTANCE;
    }
    @Override
    public void addCategory(Category category) throws ServiceException {

        try {
            categoryDao.insert (category);
        } catch (DaoException e) {
            throw new ServiceException ();
        }
    }

    @Override
    public List<Category> getAll() throws ServiceException {
        try {
            return categoryDao.getAll ();
        } catch (DaoException e) {
            throw new ServiceException (e);
        }
    }
}
