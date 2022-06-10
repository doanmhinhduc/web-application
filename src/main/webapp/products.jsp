<%@ page import="com.example.webapplication.entity.Product" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 6/10/2022
  Time: 9:06 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Product> list = (ArrayList<Product>) request.getAttribute("products");
    if (list==null)
        list = new ArrayList<>();
%>
<!DOCTYPE html>
<html>
<title>test</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<meta charset="UTF-8">
<head>
    <style>
        #customers {
            font-family: Arial, Helvetica, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }
        #customers td, #customers th {
            border: 1px solid #ddd;
            padding: 8px;
        }
        #customers tr:nth-child(even) {
            background-color: #f2f2f2;
        }
        #customers tr:hover {
            background-color: #ddd;
        }
        #customers th {
            padding-top: 12px;
            padding-bottom: 12px;
            text-align: left;
            background-color: #04AA6D;
            color: white;
        }
    </style>
</head>
<body>
<div class="w3-container">
    <h2>Tab as a Card</h2>
    <a href="/products/create" class="w3-btn w3-black">create at</a>
</div>
<table id="customers">
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Price</th>
    </tr>
    <%
        for (int i = 0; i < list.size(); i++) {
            Product obj = list.get(i);
    %>
    <tr>
        <td><%=obj.getId()%>
        </td>
        <td><%=obj.getName()%>
        </td>
        <td><%=obj.getWage()%> VND
        </td>
        <td><a href="/products/edit?id=<%=obj.getId()%>">Edit</a>&nbsp;&nbsp;|&nbsp;&nbsp;</td>
        <td><a href="/products/delete?id=<%=obj.getId()%>" class="btn-delete">Delete</a>&nbsp;&nbsp;|&nbsp;&nbsp;</td>
    </tr>
    <%
        }
    %>
    <script>
        document.addEventListener('DOMContentLoaded', function () {
            let listDeleteBtn = document.querySelectorAll('.btn-delete');
            for (let i = 0; i < listDeleteBtn.length; i++) {
                listDeleteBtn[i].addEventListener('click', function (event) {
                    event.preventDefault();
                    if (confirm("Are U sure?")) {
                        let xhr = new XMLHttpRequest();
                        xhr.onreadystatechange = function () {
                            if (xhr.readyState == 4 && xhr.status == 200) {
                                alert("Deleted!")
                                window.location.reload()
                            }
                        }
                        xhr.open('DELETE', this.href, false);
                        xhr.send();
                    }
                })
            }
        })
    </script>
</table>
</body>
</html>
