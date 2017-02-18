<%@include file="nine.jsp" %>
<div class="content" align="center">
    <%
        if (role != 2) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    %>
    <div class="page-header">
        <h1>Add new product</h1>
    </div>
    <form action="/product_operation" method="post" enctype="multipart/form-data">
        <table>
            <tr>
                <td>
                    <input type="file" name="file" size="50"/>
                </td>
            </tr>
            <tr>
                <td>
                    <label>Name</label>
                </td>
                <td>
                    <input type="text" name="name">
                </td>
            </tr>
            <tr>
                <td><label>Quantity</label></td>
                <td><input type="number" name="quantity"></td>
            </tr>
            <tr>
                <td>
                    <label>Cost</label>
                </td>
                <td>
                    <input type="number" name="cost">
                </td>
            </tr>
            <tr>
                <td>
                    <label>Attack</label>
                </td>
                <td>
                    <input type="number" name="attack">
                </td>
            </tr>
            <tr>
                <td>
                    <label>Defense</label>
                </td>
                <td>
                    <input type="number" name="defense">
                </td>
            </tr>
            <tr>
                <td>
                    <label>Damage</label>
                </td>
                <td>
                    <input type="number" name="damage">
                </td>
            </tr>
            <tr>
                <td>
                    <label>Health</label>
                </td>
                <td>
                    <input type="number" name="health">
                </td>
            </tr>
            <tr>
                <td>
                    <label>Description</label>
                </td>
                <td>
                    <input type="text" name="description">
                </td>
            </tr>
            <input type="hidden" name="operation" value="add">
        </table>
        <input type="submit" value="Add"><br>
        <%
            request.setAttribute("operation", "add");
            String msg = request.getParameter("msg");
            String success = request.getParameter("succ");
        %>
        <p class="error"><%=msg == null ? "" : msg%>
        <p class="success"><%=success == null ? "" : success%>
        </p>
    </form>
</div>
<%@include file="bottom.jsp" %>
