package iac.schobshop.Schobshop.service;

import com.google.common.collect.Sets;
import iac.schobshop.Schobshop.exceptions.ObjectNotFoundException;
import iac.schobshop.Schobshop.model.Category;
import iac.schobshop.Schobshop.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    @Override
    public Set<Category> findAllCategories() {
        Iterable<Category> categories = categoryRepository.findAll();
        return Sets.newHashSet(categories);
    }

    @Override
    public Category findCategory(Long id) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if(!categoryOptional.isPresent()){
            throw new ObjectNotFoundException("category", "id", id.toString());
        }
        return categoryOptional.get();
    }
}
