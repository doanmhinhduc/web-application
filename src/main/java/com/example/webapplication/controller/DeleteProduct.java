package com.example.webapplication.controller;


import com.example.webapplication.entity.Product;
import com.example.webapplication.retrofit.RetrofitGenerator;
import com.example.webapplication.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteProduct", value = "/products/delete")
public class DeleteProduct extends HttpServlet {
    ProductService productService = RetrofitGenerator.createService(ProductService.class);

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id =0;
        try {
            id = Integer.parseInt(req.getParameter("id"));

        }catch (Exception e){
            resp.getWriter().println("Invalid Id");
        }
        Product obj = productService.getProductDetail(id).execute().body();
        if (obj == null) {
            resp.setStatus(404);
            resp.getWriter().println("Not found");
        } else {
            if (Boolean.TRUE.equals(productService.delete(id).execute().body()))
                resp.setStatus(200);
            else resp.setStatus(400);
        }
    }
}
