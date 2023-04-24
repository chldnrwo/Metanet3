<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%
            int n1 = Integer.parseInt(request.getParameter("n1"));
            int n2 = Integer.parseInt(request.getParameter("n2"));
            String op = request.getParameter("op");

            String message = "";
            int result = 0;

            switch (op) {
              case "+": result = n1 + n2; break;
              case "-": result = n1 - n2; break;
              case "*": result = n1 * n2; break;
              case "/":
                if(n2 == 0){
                  result = 0;
                  message = "0으로 나눌 수 없습니다.";
                }else{
                  result = n1 / n2;
                }
                break;
            }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Hello World!</title>
    </head>
    <body>
        <h2>계산결과 JSP</h2>
        <hr>
        결과 : <%= result %> <br>
        <%= message%>
    </body>
</html>