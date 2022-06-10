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

@WebServlet(name = "UpdateProduct", value = "/products/edit")
public class UpdateProduct extends HttpServlet {
    ProductService productService = RetrofitGenerator.createService(ProductService.class);

    public UpdateProduct() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = 0;
        try {
            id = Integer.parseInt(req.getParameter("id"));
        } catch (Exception e) {
            resp.getWriter().println(e);
        }
        if (id <= 0) {
            resp.getWriter().println("Is valid information");
            return;
        }
        Product obj = productService.getProductDetail(id).execute().body();
        if (obj == null) {
            resp.setStatus(404);
            resp.getWriter().println("Not found");
            return;
        }
        req.setAttribute("product", obj);
        req.getRequestDispatcher("/create.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("utf-8");
        int id = 0;
        try {
            id = Integer.parseInt(req.getParameter("id"));
        } catch (Exception e) {
            resp.getWriter().println(e);
        }
        if (id <= 0) {
            resp.getWriter().println("Is valid information");
            return;
        }
        Product obj = productService.getProductDetail(id).execute().body();
        if (obj == null) {
            resp.setStatus(404);
            resp.getWriter().println("Not found");
            return;
        }
        BigDecimal wage = new BigDecimal(0);
        String name = req.getParameter("name");
        try {
            wage = new BigDecimal(req.getParameter("wage"));
        } catch (Exception e) {
            System.err.println(e);
        }
        obj.setName(name);
        obj.setWage(wage);
        if (Boolean.TRUE.equals(productService.update(id, obj).execute().body())) {
            resp.sendRedirect("/products");
        } else {
            req.setAttribute("failure", "UpdatedFail");
            req.getRequestDispatcher("/create.jsp").forward(req, resp);
        }


    }
}
