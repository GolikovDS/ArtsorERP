package ru.artsok.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.artsok.dao.ProductCrudDAO;
import ru.artsok.entity.ProductEntity;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Objects;

@Controller
public class DesignerController {
    private ProductCrudDAO productCrudDAO;


    @Autowired
    public DesignerController(ProductCrudDAO productCrudDAO) {
        this.productCrudDAO = productCrudDAO;
    }

    @RequestMapping(value = {"/add_product"}, method = RequestMethod.POST)
    public String welcomePage(@ModelAttribute("newProduct") ProductEntity products) {
        if (Objects.equals(products.getName(), "") || Objects.equals(products.getDrawing(), "")) {
            return "redirect:/designer?error=2";
        }
        try {
            productCrudDAO.save(products);
        } catch (Exception e) {
            return "redirect:/designer?error=1";
        }
        return "redirect:/designer";
    }

    @RequestMapping(value = "/file_upload", method = RequestMethod.POST)
    public String fileUpdate(@RequestParam(value = "product", defaultValue = "") String product,
                             @RequestParam("file") MultipartFile file) {
        String errorUploadFile = "";
        if (!file.isEmpty()) {
            if (file.getSize() < 2500000) {
                try {
                    ProductEntity productEntity = productCrudDAO.findById(Long.parseLong(product)).get();
                    productEntity.setPhoto(file.getBytes());
                    productCrudDAO.save(productEntity);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                    errorUploadFile = "error=2&";
                }
            }else {
                errorUploadFile = "error=3&";
            }
        }

        return "redirect:/designer?" + errorUploadFile + "product=" + product;
    }


    @RequestMapping(value = "/dinamic_image", method = RequestMethod.GET)
    public @ResponseBody
    void getImage(HttpServletResponse response, @RequestParam(value = "product", defaultValue = "") String product) {
        if (!product.equals("")) {
            try (OutputStream out = response.getOutputStream()) {
                ProductEntity productEntity = productCrudDAO.findById(Long.parseLong(product)).get();
                BufferedImage image = ImageIO.read(new ByteArrayInputStream(productEntity.getPhoto()));
                ImageIO.write(image, "png", out);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
