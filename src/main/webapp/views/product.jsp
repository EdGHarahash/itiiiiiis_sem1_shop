<%@include file="nine.jsp" %>
<%@ page import="ru.itis.kpfu.eduard_harahashyan.models.Product" %>
<%@ page import="ru.itis.kpfu.eduard_harahashyan.controllers.db.ProductDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ProductDAO dao = new ProductDAO();
    Product p = dao.getFirst("id", request.getParameter("id"));
    dao.close();
%>
<div class="content" align="center">
    <table width="70%">
        <tr>
            <td align="right">
                <%String source = "../img/" + p.getId() + ".jpg";%>
                <p><img width="80" height="156" src=<%=source%>></p>
            </td>
            <td>
                <h3><%=p.getName()%>
                </h3>
                <p>Quantity: <%=p.getQuantity()%>
                </p>
                <p>Price: <%=p.getCost()%></p><br>
            </td>
        </tr>
        </table>
        <table width="70%">
        <tr>
            <td align="center"><p>Description: <%=p.getDescription()%>
            </p></td>
        </tr>
        </table>
    <table width="70%" align="center">
        <tr>
            <td align="center"><p>Attack - <%=p.getAttack()%>
            </p></td>
        </tr>
        <tr>
            <td align="center"><p>Defense - <%=p.getDefense()%>
            </p></td>
        </tr>
        <tr>
            <td align="center"><p>Damage - <%=p.getDamage()%>
            </p></td>
        </tr>
        <tr>
            <td align="center"><p>Health - <%=p.getHealth()%>
            </p></td>
        </tr>
    </table>
    <br>
    <%if (role == 1) { %>
    <form class="addform" method="post">
        <%
                String quantity = String.valueOf(p.getIntQuantity());
        %>
        <label>Quantity:<input type="number" min="1" max="<%=quantity%>" value="1" size="2" class="prodq" name="prodq"></label>
        <input class="prodid" type="hidden" name="prodid" value=<%=p.getId()%>>
        <input type="submit" value="Add to cart">
    </form>
    <%}%>
    <a href="products?page=1">Back</a>
    <div>
        <h2>Comments</h2>
        <%if (role != 0) { %>
        <form class="commentform" method="post">
            <textarea class="msg" name="msg" cols="40" rows="5"></textarea><br>
            <input class="productid" type="hidden" name="productid" value=<%=request.getParameter("id")%>>
            <input type="submit" value="Post a comment">
        </form>
        <%}%>
    </div>
    <div id="comments">
        <%@include file="commentlist.jsp" %>
    </div>
</div>
<%@include file="bottom.jsp" %>