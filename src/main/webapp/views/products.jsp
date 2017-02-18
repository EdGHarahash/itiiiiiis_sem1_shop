<%@ page import="ru.itis.kpfu.eduard_harahashyan.models.Product" %>
<%@ page import="java.util.LinkedList" %>
<%@include file="nine.jsp" %>
<%
    LinkedList<Product> products = (LinkedList<Product>) request.getAttribute("products");
%>
<div class="content" align="center">
    <div class="page-header">
        <h1>Units</h1>
    </div>
    <%if (role == 2) { %>
    <a class="redd" href="/add_product">Add new product</a>
    <% }%>
    <div class="table-responsive" style="margin-top: 2%">
        <table class="product_table">
            <%while (!products.isEmpty()) {%>
            <tr>
                <% for (int i = 0; i < 2; i++) {
                    if (!products.isEmpty()) {
                        Product p = products.poll();
                        String source = "../img/" + p.getId() + ".jpg";
                        String prodHref = "/product_detail?id=" + p.getId();
                %>
                <td>
                    <p><img src=<%=source%> width="80" height="156"></p>
                </td>
                <td>
                    <h3><a class="redd" href=<%=prodHref%>><%=p.getName()%></a></h3>
                    <p>Quantity: <%=p.getQuantity()%>
                    </p>
                    <p>Price: <%=p.getCost()%></p>
                    <p>
                        <%=p.getDescription().length() > 19 ? p.getDescription().substring(0, 20)+"..."  : p.getDescription()%>
                        </p>
                    <%if (role == 1) { %>
                    <div id="result"></div>
                    <%}
                        if (role == 2) {
                            String editHref = "/edit_product?id=" + p.getId();
                            String removeHref = "/product_remove?id=" + p.getId();
                    %>
                    <a class="redd" href=<%=editHref%>>Edit</a>
                    <a class="redd" onclick="alert('You`ve been deleted product');" href=<%=removeHref%>>Remove</a>
                    <%}%>
                </td>
                <%
                        }
                    }%>
            </tr>
            <%}%>
        </table>
    </div>
    <nav aria-label="Page navigation">
        <ul class="pagination">
            <%
                int pages = (int) request.getAttribute("pages");
                for (int i = 1; i <= pages; i++) {
                    String pageHref = "/products?page=" + i;
            %>
            <li><a href=<%=pageHref%>><%=i%>
            </a></li>
            <% }%>
        </ul>
    </nav>
    <br>
</div>
<%@include file="bottom.jsp" %>
