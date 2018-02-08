import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

/**
 * Created by golden on 2016/11/25 0025.
 * 以纯文本的形式返回数据
 */
@WebServlet(
        name="ajaxServlet", urlPatterns={"/ajaxServlet"}
)
public class AJAXServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8" );
        PrintWriter printWriter = resp.getWriter();

        //1.取参数信息
        String username = req.getParameter("username");
        //2.检查参数是否有问题
        if(username == null || username.length() == 0) {
            printWriter.println("用户名不能为空！");
        } else { //3.校验操作
            String name = URLDecoder.decode(username,"UTF-8");
            if(name.equals("ZJX")) {
                //4.与传统不同，不是将新的页面发给用户，而是将数据返回给页面端；
                printWriter.println("用户名[" + name + "]已存在，请选择其他用户名！");
            } else {
                printWriter.println("用户名[" + name + "]尚未存在，可以注册！");
            }
        }
    }
}
