package ch08;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/pcontrol")
public class ProductController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  //ProductService service;
  ProductDAO service;
  // 인터페이스 service

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.setCharacterEncoding("UTF-8");
    String id = req.getParameter("id");
    String name = req.getParameter("name");
    String maker = req.getParameter("maker");
    int price = Integer.parseInt(req.getParameter("price"));
    String date = req.getParameter("date");

    Product p = new Product();
    p.setId(id);
    p.setName(name);
    p.setMaker(maker);
    p.setPrice(price);
    p.setDate(date);

    ProductDAO pd = new ProductDAO();
    pd.update(p);

    // 상품 추가가 완료된 후 상품 목록 페이지로 리다이렉트합니다.
    resp.sendRedirect("/pcontrol?action=list");
  }
  @Override
  public void init(ServletConfig config) throws ServletException {
    super.init(config);
    //service = new ProductService();
    service = new ProductDAO();
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String action = req.getParameter("action");
    String view = "";

    if (action == null) {
      action = "list";
    }
    if (action.equals("list")) {
      view = list(req, resp);
    } else if (action.equals("info")) {
      view = info(req, resp);
    }

    getServletContext()
        .getRequestDispatcher("/ch08/" + view)
        .forward(req, resp);
  }

  private String info(HttpServletRequest req, HttpServletResponse resp){
    req.setAttribute("p",service.find(req.getParameter("id")));
    return "productInfo.jsp";
  }
  private String list(HttpServletRequest req, HttpServletResponse resp){
    req.setAttribute("products",service.findAll());
    return "productList.jsp";
  }

}
