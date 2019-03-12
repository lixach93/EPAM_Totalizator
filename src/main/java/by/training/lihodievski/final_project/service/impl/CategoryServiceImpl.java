package by.training.lihodievski.final_project.service.impl;


import by.training.lihodievski.final_project.bean.Category;
import by.training.lihodievski.final_project.dao.impl.category.CategoryDaoAbstract;
import by.training.lihodievski.final_project.dao.factory.DaoFactory;
import by.training.lihodievski.final_project.dao.exception.DaoException;
import by.training.lihodievski.final_project.service.CategoryService;
import by.training.lihodievski.final_project.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {

    private static final Logger LOGGER = LogManager.getLogger (CategoryServiceImpl.class);
    private static final CategoryServiceImpl INSTANCE = new CategoryServiceImpl ();
    private DaoFactory daoFactory = DaoFactory.getInstance ();
    private CategoryDaoAbstract categoryDao = daoFactory.getCategoryDao ();

    private CategoryServiceImpl(){}

    public static CategoryServiceImpl getInstance(){
        return INSTANCE;
    }

    @Override
    public List<Category> getCategories() throws ServiceException {
        try {
            return categoryDao.getAll ();
        } catch (DaoException e) {
            LOGGER.error ("Exception in getCategories in CategoryServiceImpl.class ", e);
            throw new ServiceException (e);
        }
    }
}
