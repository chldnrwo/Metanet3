<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>상품 목록</title>
    </head>
    <body>
        <h2>상품 목록</h2>
        <hr>
        <table border="1">
        <tr>
                    <th>번호</th>
                    <th>상품명</th>
           <th>가격</th>
        </tr>
                <c:forEach var="p" varStatus="i" items="${products}">
                    <tr>
                        <td><a href="/pcontrol?action=info&id=${p.id}">${p.id}</td>
                        <td><a href="/pcontrol?action=info&id=${p.id}">${p.name}</a></td>
                        <td>${p.price}</td>
                    </tr>
                </c:forEach>
        </table>

        <h3>추가하실 핸드폰 정보를 입력해주세요.</h3>
        <div>
            <form method="post" action="/pcontrol">
                <label>ID&emsp;&emsp; : </label>
                <input type="text" name="id"> <br>
                <label>이름&emsp; : </label>
                <input type="text" name="name"> <br>
                <label>메이커 : </label>
                <input type="text" name="maker"> <br>
                <label>가격&emsp; : </label>
                <input type="text" name="price"> <br>
                <label>날짜&emsp; : </label>
                <input type="text" name="date"> <br>
                <input type="submit" value="입력">
            </form>
        </div>

    </body>
</html>