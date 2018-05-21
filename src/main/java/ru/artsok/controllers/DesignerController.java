package ru.artsok.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.artsok.dao.ProductDAO;
import ru.artsok.entity.ProductEntity;

import java.security.Principal;
import java.util.Objects;

@Controller
public class DesignerController {
    private ProductDAO productDAO;


    @Autowired
    public DesignerController(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @RequestMapping(value = {"/add_product"}, method = RequestMethod.POST)
    public String welcomePage(Model model, @ModelAttribute("newProduct")ProductEntity products) {
        System.out.println((productDAO.findAll().toString()));
        if(Objects.equals(products.getName(), "") || Objects.equals(products.getDrawing(), "")){
            return "redirect:/designer?error=2";
        }
        try {
            productDAO.save(products);
        }catch (Exception e){
            return "redirect:/designer?error=1";
        }
        return "redirect:/designer";
    }


}
