package ru.itis.kpfu.eduard_harahashyan.controllers.servlets;

import ru.itis.kpfu.eduard_harahashyan.controllers.db.ProductDAO;
import ru.itis.kpfu.eduard_harahashyan.models.Bucket;
import ru.itis.kpfu.eduard_harahashyan.models.Product;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddToCart extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
        super.doGet(rq, rs);
    }

    @Override
    protected void doPost(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
        int quantity = Integer.parseInt(rq.getParameter("prodq"));
        String id = rq.getParameter("prodid");
        ProductDAO dao = new ProductDAO();
        Product temp = dao.getFirst("id", id);
        String result = "Success";
        Bucket bucket = (Bucket) rq.getSession().getAttribute("cart");
        try {
            bucket.addProduct(temp, quantity);
            rs.getWriter().write(result);
        } catch (Exception e) {
            rs.getWriter().write(e.getMessage());
        }
    }
}
