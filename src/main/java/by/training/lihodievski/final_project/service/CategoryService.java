package by.training.lihodievski.final_project.service;

import by.training.lihodievski.final_project.bean.Category;
import by.training.lihodievski.final_project.service.exception.ServiceException;

import java.util.List;

public interface CategoryService {

    void addCategory(Category category) throws ServiceException;
    List<Category> getAll() throws ServiceException;
}
