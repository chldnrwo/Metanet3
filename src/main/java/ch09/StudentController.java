package ch09;

import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/studentControl")
public class StudentController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  StudentDAO dao;

  @Override
  public void init() throws ServletException {
    super.init();
    dao = new StudentDAO();
  }

  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String action = req.getParameter("action");
    String view = "";

    if (action == null) {
      action = "list";
    }
    if (action.equals("list")) {
      view = list(req, resp);
    } else if (action.equals("insert")) {
      view = insert(req, resp);
    }

    getServletContext()
        .getRequestDispatcher("/ch09/" + view)
        .forward(req, resp);
  }
  public String insert(HttpServletRequest req, HttpServletResponse resp){

    Student s = new Student();
    try {
      Map<String, String[]> paramMap = req.getParameterMap();
      Map<String, String[]> convertedParamMap = new HashMap<>();
      for (String key : paramMap.keySet()) {
        String[] values = paramMap.get(key);
        String[] convertedValues = new String[values.length];
        for (int i = 0; i < values.length; i++) {
          convertedValues[i] = new String(values[i].getBytes("ISO-8859-1"), "UTF-8");
        }
        convertedParamMap.put(key, convertedValues);
      }
      BeanUtils.populate(s, convertedParamMap);
    } catch (Exception e) {
      e.printStackTrace();
    }
    dao.insert(s);
    return list(req, resp);
  }
  public String list(HttpServletRequest req, HttpServletResponse resp){
    req.setAttribute("students", dao.getAll());
    return "studentInfo.jsp";
  }
}
