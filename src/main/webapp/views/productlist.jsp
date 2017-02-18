<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="ru.itis.kpfu.eduard_harahashyan.models.Bucket" %>
<%@ page import="ru.itis.kpfu.eduard_harahashyan.models.Product" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.Map" %>
<div>
    <%
        Bucket bucket;
        bucket = (Bucket) session.getAttribute("cart");
        Map<Integer, Product> products = (Map) bucket.getProducts();
    %>
    <h4>Products in bucket:<%=bucket.getProductCount()%>
    </h4>
    <h5>Total price:<%=bucket.getTotalPrice()%>
    </h5>
    <table>
        <%
            Iterator it = products.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<Integer, Product> pair = (Map.Entry) it.next();
                Product p = pair.getValue();
        %>
        <div id=<%=pair.getKey()%>>
            <tr>
                <td>Product name:</td>
                <td><%=p.getName()%>
                </td>

            </tr>
            <tr>
                <td>Quantity:</td>
                <td><%=p.getIntQuantity()%>
                </td>
            </tr>
            <tr>
                <td>
                    <form class="removefromcart" method="post">
                        <input class="prodid" type="hidden" name="prodid" value=<%=pair.getKey()%>>
                        <input type="submit" value="Remove">
                    </form>
                </td>
            </tr>
        </div>
        <%}%>
    </table>
</div>
