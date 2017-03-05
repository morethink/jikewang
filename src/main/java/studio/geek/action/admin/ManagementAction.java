package studio.geek.action.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import studio.geek.entity.Manager;
import studio.geek.exception.ErrorException;
import studio.geek.util.Result;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.regex.Pattern;


@Controller
@RequestMapping(value = "/management")
public class ManagementAction {


    @Autowired
    ServletContext servletContext;

    /**
     * 管理员登录页面
     *
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() throws IOException {

        return "redirect:/back-end/login.html";
    }

    /**
     * 管理员登录
     *
     * @param manager
     * @param session
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Result login(Manager manager, HttpSession session) {
        System.out.println("/login start.");
        if (manager.getUsername().equals("") || manager.getUsername() == null) {
            throw new ErrorException("用户名未填写");
        }
        if (manager.getPassword().equals("") || manager.getPassword() == null) {
            throw new ErrorException("密码未填写");
        }

        if (manager.getUsername() == null) {
            throw new ErrorException("用户名未填写");
        }
        if (manager.getPassword() == null) {
            throw new ErrorException("密码未填写");
        }
        Result result = new Result();
        result.setSuccess(true);
        System.out.println(servletContext.getAttribute("manager"));
        System.out.println(manager);
        if (manager.equals(servletContext.getAttribute("manager"))) {
            result.setData("success");
            session.setAttribute("manager", manager);
        } else result.setData("fail");

        return result;
    }


    /**
     * 管理员退出
     *
     * @param session
     * @return
     */

    @RequestMapping(value = "/logout", consumes = "application/json")
    @ResponseBody
    public Result logout(HttpSession session) {
        System.out.println("logout");
        Result result = new Result();
        result.setSuccess(true);

        session.removeAttribute("manager");

        if (session.getAttribute("manager") != null) {
            throw new ErrorException("退出失败");
        }
        result.setData("退出成功");

        return result;
    }

// TODO 可以开启PUT

    @RequestMapping(value = "/setPageSize", method = RequestMethod.POST)
    @ResponseBody
    public Result setPageSize(String pageSize) {
        Result result = new Result();
        Pattern pattern = Pattern.compile("[0-9]{1,9}");
        int num;
        try {
            if (!pattern.matcher(pageSize).matches()) {
                num = 10;
            } else {
                num = Integer.valueOf(pageSize);
            }
        } catch (Exception e) {
            throw new ErrorException("I dont't konw.");
        }
//        Page.setPageSize(num);
        result.setSuccess(true);
        result.setData("修改成功");
        return result;
    }
}
