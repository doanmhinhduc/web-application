<%@ page import="com.example.webapplication.entity.Product" %>
<%@ page import="java.util.HashMap" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String success = (String) request.getAttribute("success");
    String failure = (String) request.getAttribute("failure");
    Product product = (Product) request.getAttribute("product");
    HashMap<String,String> errros = (HashMap<String,String>) request.getAttribute("errors");
    if (product == null) {
        product = new Product();
        product.setId(0);
    }
%>
<!DOCTYPE html>
<html>
<title>W3.CSS</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="UTF-8">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<body>

<form class="w3-container w3-card-4" action="/products/create" method="post">
    <h2 class="w3-text-blue"><%if (product.getId() > 0) {
    %>Update Products<%} else {%>Create new Products
        <%}
        %></h2>
    <p>làm ơn.</p>

    <%
        if (errros!= null&&errros.size()>0){
    %>
    <div class="w3-panel w3-pale-red w3-border">
        <h3>Lỗi</h3>
        <ul>
            <%
                for (String message:
                        errros.values()) {
            %>
            <li><%=message%></li>
            <%}%>
        </ul>
    </div>
    <%
        }
    %>
    <p>
        <label class="w3-text-blue"><b>name</b></label>
        <input class="w3-input w3-border" name="name" type="text">
    </p>
    <p>
        <label class="w3-text-blue"><b>Price</b></label>
        <input class="w3-input w3-border" name="price" value="0" type="number">
    </p>
    <h4>xemdanh sách trang chủ ==><a href="/products">here</a> </h4>
    <p>
        <button href="/products" class="w3-btn w3-blue">submit</button>
    </p>
</form>
<script src="https://upload-widget.cloudinary.com/global/all.js" type="text/javascript"></script>

<script type="text/javascript">
    (function () {
        'use strict';
        window.addEventListener('load', function () {
            var forms = document.getElementsByClassName('needs-validation');
            var validation = Array.prototype.filter.call(forms, function (form) {
                form.addEventListener('submit', function () {
                    form.classList.add('was-validated');
                }, false);
            });
        }, false);
    })();
</script>
</body>
</html>
