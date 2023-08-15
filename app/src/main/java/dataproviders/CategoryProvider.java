package dataproviders;

import java.util.List;

import entities.Category;


public interface CategoryProvider {
    CategoryRepository getInstance();
    List<Category> getAllCategories();
    Category getCategoryByID(String categoryID);
}

