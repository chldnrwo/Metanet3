package ch09;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
  Connection conn=null;
  PreparedStatement ps;

  final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
  final String JDBC_URL = "jdbc:mysql://localhost:3306/" +
      "madang?serverTimezone=Asia/Seoul&" +
      "useSSL=false&useUnicode=true&characterEncoding=UTF-8&" +
      "allowPublicKeyRetrieval=true";

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
  public void insert(Student s){
    open();
    String sql = "insert into student (username, univ, birth, email) " +
        "values (?, ?, ?, ?)";

    try {
      ps = conn.prepareStatement(sql);
      ps.setString(1, s.getUsername());
      ps.setString(2, s.getUniv());
      ps.setDate(3, s.getBirth());
      ps.setString(4, s.getEmail());

      ps.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    } finally{
      close();
    }
  }
  public List<Student> getAll(){
    open();
    List<Student> students = new ArrayList<>();

    try {
      ps = conn.prepareStatement("select * from student");
      ResultSet rs = ps.executeQuery();

      while(rs.next()){
        Student s = new Student();
        s.setId(rs.getInt("id"));
        s.setUsername(rs.getString("username"));
        s.setUniv(rs.getString("univ"));
        s.setBirth(rs.getDate("birth"));
        s.setEmail(rs.getString("email"));

        students.add(s);
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      close();
    }
    return students;
  }
}
