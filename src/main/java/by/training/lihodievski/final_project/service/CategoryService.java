package by.training.lihodievski.final_project.service;

import by.training.lihodievski.final_project.bean.Category;
import by.training.lihodievski.final_project.service.exception.ServiceException;

import java.util.List;

public interface CategoryService {

    List<Category> getCategories() throws ServiceException;
}
