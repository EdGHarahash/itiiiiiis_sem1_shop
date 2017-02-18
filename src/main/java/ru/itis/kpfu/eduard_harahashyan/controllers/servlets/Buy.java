package ru.itis.kpfu.eduard_harahashyan.controllers.servlets;

import ru.itis.kpfu.eduard_harahashyan.models.Bucket;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Buy extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
        super.doGet(rq, rs);
    }

    @Override
    protected void doPost(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
        Bucket bucket = (Bucket) rq.getSession().getAttribute("cart");
        if(bucket.getProductCount()!=0) {
            bucket.clear();
            rs.getWriter().write("GLHF");
        }else {
            rs.getWriter().write("Your bucket is empty!");
        }
    }
}
