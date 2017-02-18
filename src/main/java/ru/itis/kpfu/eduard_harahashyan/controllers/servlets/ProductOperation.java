package ru.itis.kpfu.eduard_harahashyan.controllers.servlets;

import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import ru.itis.kpfu.eduard_harahashyan.controllers.db.ProductDAO;
import ru.itis.kpfu.eduard_harahashyan.models.Product;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class ProductOperation extends HttpServlet {

    private String filePath;
    private int maxFileSize = 1024 * 1024 * 3;

    @Override
    public void init() throws ServletException {
        filePath = getServletContext().getInitParameter("file_upload");
        System.out.println(filePath);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
        boolean isMultipart = ServletFileUpload.isMultipartContent(rq);
        if (!isMultipart) {
            rs.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        ServletFileUpload upload = new ServletFileUpload(new DiskFileItemFactory());
        upload.setSizeMax(maxFileSize);
        try {
            List<FileItem> fileItems = upload.parseRequest(rq);
            FileItem image = null;
            Map<String, String> textParams = new HashMap<String, String>();
            for (FileItem item : fileItems) {
                if (item.isFormField()) {
                    textParams.put(item.getFieldName(), item.getString());
                } else {
                    image = item;
                }
            }
            String redirect = saveDataToDB(textParams, image, rq);
            rs.sendRedirect(redirect);
        } catch (FileUploadException ex) {
            ex.printStackTrace();
        }
    }

    private String saveDataToDB(Map<String, String> textParams, FileItem item, HttpServletRequest rq) {
        String operation = textParams.get("operation");
        String name = textParams.get("name");
        int attack = Integer.parseInt(textParams.get("attack"));
        int defense = Integer.parseInt(textParams.get("defense"));
        int damage = Integer.parseInt(textParams.get("damage"));
        int health = Integer.parseInt(textParams.get("health"));
        int quantity = Integer.parseInt(textParams.get("quantity"));
        int cost = Integer.parseInt(textParams.get("cost"));
        String description = textParams.get("description");
        Product product = new Product(name, attack, defense, damage, health, description, cost, quantity);
        ProductDAO dao = new ProductDAO();
        String result;
        int productId = 0;
        if (operation.equals("edit")) {
            productId = Integer.parseInt(rq.getParameter("id"));
            product.setId(productId);
            if (dao.edit(product)) {
                result = "/edit_product?id=" + productId + "&succ=Success";
            } else {
                result = "/edit_product?id=" + productId + "&error=Error";
            }
        } else {
            if (dao.addNew(product)) {
                productId = dao.getLastId();
                result = "/add_product?succ=Success";
            } else {
                result = "/add_product?error=Error";
            }
        }
        if (item != null) {
            saveFile(item, productId);
        }
        return result;
    }

    private void saveFile(FileItem item, int fileId) {
        File file = new File(filePath + fileId + ".jpg");
        File file2 = new File("C:\\Users\\Эдик\\Downloads\\ITIS_SEMESTER1-master\\src\\main\\webapp\\img\\" + fileId + ".jpg");
        try {
            item.write(file);
            item.write(file2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
