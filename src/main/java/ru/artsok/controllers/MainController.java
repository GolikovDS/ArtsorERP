package ru.artsok.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.artsok.dao.ProductCrudDAO;
import ru.artsok.utils.WebUtils;

import java.security.Principal;
import java.util.Objects;

@Controller
public class MainController {

    private final ProductCrudDAO productDAO;

    @Autowired
    public MainController(ProductCrudDAO productDAO) {
        this.productDAO = productDAO;
    }

    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String welcomePage(Model model, Principal principal) {
        model.addAttribute("title", "АРТСОК");
        model.addAttribute("message", "Домашняя страница");
        // model.addAttribute("userInfo", WebUtils.toString((User)((Authentication) principal).getPrincipal()));
        return "welcomePage";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminPage(Model model, Principal principal) {
        //model.addAttribute("userInfo", WebUtils.toString((User)((Authentication) principal).getPrincipal()));
        return "adminPage";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        return "loginPage";
    }

    @RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
    public String logoutSuccessfulPage(Model model) {
        model.addAttribute("title", "Logout");
        return "logoutSuccessfulPage";
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public String userInfo(Model model, Principal principal) {
        // model.addAttribute("userInfo", WebUtils.toString((User)((Authentication) principal).getPrincipal()));
        return "products";
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied(Model model, Principal principal) {

        if (principal != null) {
            model.addAttribute("userInfo", WebUtils.toString((User) ((Authentication) principal).getPrincipal()));
            String message = "Hi " + principal.getName() //
                    + "<br> You do not have permission to access this page!";
            model.addAttribute("message", message);
        }
        return "403Page";
    }

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public String orders(Model model, Principal principal) {
        //model.addAttribute("userInfo", WebUtils.toString((User)((Authentication) principal).getPrincipal()));
        return "orders";
    }


    @RequestMapping(value = "/designer", method = RequestMethod.GET)
    public String designer(Model model, @RequestParam(value = "error", defaultValue = "") String error,
                           @RequestParam(value = "product", defaultValue = "") String product) {
        if (!Objects.equals(error, "")) {
            switch (error) {
                case "1":
                    model.addAttribute("error", "Ошибка: изделие под данным чертежом уже существует!");
                    break;
                case "2":
                    model.addAttribute("error", "Ошибка: неверно введены данные!");
                    break;
                case "3":
                    model.addAttribute("error", "Ошибка: файл слишком большой!");
                    break;
                default:
                    model.addAttribute("error", "Ошибка!");
                    break;
            }
        }
        model.addAttribute("products", productDAO.findAll());
        if (!product.equals(""))
            model.addAttribute("select_product", productDAO.findById(Long.parseLong(product)).get());

        model.addAttribute("str", "String");
        return "designer";
    }
}
