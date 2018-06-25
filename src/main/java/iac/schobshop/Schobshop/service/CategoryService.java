package iac.schobshop.Schobshop.service;

import iac.schobshop.Schobshop.model.Category;
import java.util.Set;

public interface CategoryService {
    Set<Category> findAllCategories();
    Category findCategory(Long id);
}
