package iac.schobshop.Schobshop.service;

import iac.schobshop.Schobshop.exceptions.ObjectNotFoundException;
import iac.schobshop.Schobshop.model.Category;
import iac.schobshop.Schobshop.repository.CategoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CategoryServiceImplTest {

    private CategoryServiceImpl categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        categoryService = new CategoryServiceImpl(categoryRepository);
    }

    @Test
    public void findAllCategories() {
    }

    @Test
    public void findCategory() {
        Long id = 1L;
        Category category = new Category();
        category.setName("test");
        category.setId(id);

        when(categoryRepository.findById(id)).thenReturn(Optional.of(category));
        Category categoryResult = categoryService.findCategory(id);
        assertEquals(category, categoryResult);
        verify(categoryRepository,times(1)).findById(id);

    }
    @Test(expected = ObjectNotFoundException.class)
    public void findCategoryNull() {
        Long id = 1L;
        Category category = new Category();
        category.setName("test");
        category.setId(id);

        when(categoryRepository.findById(id)).thenReturn(Optional.empty());
        categoryService.findCategory(id);
        verify(categoryRepository,times(1)).findById(id);
    }


}