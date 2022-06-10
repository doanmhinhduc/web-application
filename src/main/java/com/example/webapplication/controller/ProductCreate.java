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
import java.math.BigDecimal;
import java.util.HashMap;

@WebServlet(name = "productCreate", value = "/products/create")
public class ProductCreate extends HttpServlet {
    ProductService productService = RetrofitGenerator.createService(ProductService.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/create.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("utf-8");
        BigDecimal wage = new BigDecimal(0);
        String name = req.getParameter("name");
        try {
            wage = new BigDecimal( req.getParameter("wage"));
        } catch (Exception e) {
            System.err.println(e);
        }
        Product csv = new Product();
        csv.setName(name);

        csv.setWage(wage);
        HashMap<String, String> errors;


        if (productService.save(csv).execute().isSuccessful()) {
            req.setAttribute("success", "CreateSuccess");
        }else {
            req.setAttribute("Fail", "CreateFail");
        }

        req.getRequestDispatcher("/create.jsp").forward(req, resp);    }
}
