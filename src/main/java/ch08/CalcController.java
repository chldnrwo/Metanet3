package ch08;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


@WebServlet("/calcControl")
public class CalcController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public CalcController(){
    super();
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    int n1 = Integer.parseInt(req.getParameter("n1"));
    int n2 = Integer.parseInt(req.getParameter("n2"));
    String op = req.getParameter("op");

    String message = "";
    long result = 0;

    switch (req.getParameter("op")) {
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

    req.setAttribute("result", result);
    req.setAttribute("message", message);
    RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/ch08/calcResult.jsp");
    requestDispatcher.forward(req, resp);

  }

  public static Connection makeConnection2() {

    String url = "jdbc:mysql://localhost:3306/" +
        "madang?serverTimezone=Asia/Seoul&" +
        "useSSL=false&useUnicode=true&characterEncoding=UTF-8&" +
        "allowPublicKeyRetrieval=true";

    Connection conn = null;

    try{
      Class.forName("com.mysql.cj.jdbc.Driver");

      System.out.println("데이터베이스 연결중...");
      conn = DriverManager.getConnection(url, "root", "qkznaks1!2!3!");
      System.out.println("데이터베이스 연결 성공!");
    }catch(Exception e){
      System.out.println("데이터베이스 연결 실패!");
      System.out.println(e.getMessage());
    }
    return conn;
  }
}
