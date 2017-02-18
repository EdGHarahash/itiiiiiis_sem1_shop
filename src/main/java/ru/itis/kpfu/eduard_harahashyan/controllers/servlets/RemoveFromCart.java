package ru.itis.kpfu.eduard_harahashyan.controllers.servlets;

import ru.itis.kpfu.eduard_harahashyan.models.Bucket;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RemoveFromCart extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
        super.doGet(rq, rs);
    }

    @Override
    protected void doPost(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
        int id = Integer.parseInt(rq.getParameter("prodid"));
        Bucket bucket = (Bucket) rq.getSession().getAttribute("cart");
        if (bucket.removeProduct(id)){
            rs.getWriter().print("Succes");
        }else {
            rs.getWriter().print("Can't remove");
        }
    }
}
