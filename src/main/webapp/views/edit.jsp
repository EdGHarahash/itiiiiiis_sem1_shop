<%@ page import="ru.itis.kpfu.eduard_harahashyan.models.Product" %>
<%@ page import="ru.itis.kpfu.eduard_harahashyan.controllers.db.ProductDAO" %>
<%@include file="nine.jsp" %>
<div class="content" align="center">
    <%
        if (role != 2) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    %>
    <div class="page-header">
        <h1>Edit product</h1>
    </div>
    <%
        String id = request.getParameter("id");
        Product p = new ProductDAO().getFirst("id", id);
        String action = "/product_operation?id=" + id;
    %>
    <form action=<%=action%> method="post" enctype="multipart/form-data">
        <table>
            <tr>
                <td>
                    <input type="file" name="file" size="50"/>
                </td>
            </tr>
            <tr>
                <td>
                    <label>Unit name</label>
                </td>
                <td>
                    <input type="text" name="name" value=<%="\"" + p.getName() + "\""%> required>
                </td>
            </tr>
            <tr>
                <td><label>Attack</label></td>
                <td><input type="number" name="attack" value= <%="\"" + p.getAttack() + "\""%>></td>
            </tr>
            <tr>
                <td><label>Defence</label></td>
                <td><input type="number" name="defense" value= <%="\"" + p.getDefense() + "\""%>></td>
            </tr>
            <tr>
                <td><label>Health</label></td>
                <td><input type="number" name="health" value= <%="\"" + p.getHealth() + "\""%>></td>
            </tr>
            <tr>
                <td><label>Damage</label></td>
                <td><input type="number" name="damage" value= <%="\"" + p.getDamage() + "\""%>></td>
            </tr>
            <tr>
                <td><label>Quantity</label></td>
                <td><input type="number" name="quantity" value= <%="\"" + p.getIntQuantity() + "\""%>></td>
            </tr>
            <tr>
                <td>
                    <label>Cost</label>
                </td>
                <td>
                    <input type="number" name="cost" value= <%="\"" + p.getCost() + "\""%>>
                </td>
            </tr>
            <tr>
                <td>
                    <label>Description</label>
                </td>
                <td>
                    <textarea name="description"><%=p.getDescription()%></textarea>
                </td>
            </tr>
            <input type="hidden" name="operation" value="edit">
        </table>
        <input type="submit" value="Edit"><br>
        <%
            request.setAttribute("operation", "edit");
            String msg = request.getParameter("msg");
            String success = request.getParameter("succ");
        %>
        <p class="error"><%=msg == null ? "" : msg%>
        <p class="success"><%=success == null ? "" : success%>
        </p>
    </form>
</div>
<%@include file="bottom.jsp" %>