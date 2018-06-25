package iac.schobshop.Schobshop.controller.advises;



import iac.schobshop.Schobshop.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.ArrayList;

@AllArgsConstructor
@ControllerAdvice
public class HeaderAdvice {

    private CategoryService categoryService;

    @ModelAttribute
    public void setHeaderData(Model model){
        model.addAttribute("categories", this.categoryService.findAllCategories());
    }}