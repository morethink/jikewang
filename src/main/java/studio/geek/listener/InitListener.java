package studio.geek.listener;

import studio.geek.entity.Manager;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Created by think on 2017/2/1.
 */


@WebListener
public class InitListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        Manager manager = new Manager();
        manager.setUsername("geek");
        manager.setPassword("geek");
        servletContextEvent.getServletContext().setAttribute("manager",manager);
    }

    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("The Server destroyed.");
    }
}
