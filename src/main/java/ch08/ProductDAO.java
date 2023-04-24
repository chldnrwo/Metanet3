package ch08;

import ch09.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
  // conn
  // prepareStatement
  Connection conn = null;
  PreparedStatement ps;
  final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
  final String JDBC_URL = "jdbc:mysql://localhost:3306/" +
      "madang?serverTimezone=Asia/Seoul&" +
      "useSSL=false&useUnicode=true&characterEncoding=UTF-8&" +
      "allowPublicKeyRetrieval=true";

  // findAll
  public List<Product> findAll(){
    open();
    List<Product> products = new ArrayList<Product>();

    try {
      ps = conn.prepareStatement("select * from product");
      ResultSet rs = ps.executeQuery();

      while(rs.next()){
        Product p = new Product();
        p.setId(rs.getString("id"));
        p.setName(rs.getString("name"));
        p.setMaker(rs.getString("maker"));
        p.setPrice(rs.getInt("price"));
        p.setDate(rs.getString("date"));

        products.add(p);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      close();
    }
    return products;
  }
  // find
  public Product find(String id){
    open();
    Product p = new Product();

    try {
      ps = conn.prepareStatement(
          "select * " +
              "from product where id=?");
      ps.setString(1, id);
      ResultSet rs = ps.executeQuery();

      while(rs.next()){
        p.setId(rs.getString("id"));
        p.setName(rs.getString("name"));
        p.setMaker(rs.getString("maker"));
        p.setPrice(rs.getInt("price"));
        p.setDate(rs.getString("date"));
        }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      close();
    }
    return p;
  }
  // update

  public void update(Product p){
    open();
    String sql = "insert into product (id , name, maker, price, date) " +
        "values (?, ?, ?, ?, ?)";

    try {
      ps = conn.prepareStatement(sql);
      ps.setString(1, p.getId());
      ps.setString(2, p.getName());
      ps.setString(3, p.getMaker());
      ps.setInt(4, p.getPrice());
      ps.setString(5, p.getDate());

      ps.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    } finally{
      close();
    }
  }

  public void open() {
    try{
      Class.forName(JDBC_DRIVER);
      System.out.println("데이터베이스 연결중...");
      conn = DriverManager.getConnection(JDBC_URL, "root", "qkznaks1!2!3!");
      System.out.println("데이터베이스 연결 성공!");
    }catch(Exception e){
      System.out.println("데이터베이스 연결 실패!");
      System.out.println(e.getMessage());
    }
  }
  public void close() {
    try{
      ps.close();
      conn.close();
    }catch(SQLException e){
      e.printStackTrace();
    }
  }
}
