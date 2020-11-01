package main;

import accounts.AccountService;
import accounts.UserProfile;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.*;

/**
 * @author v.chibrikov
 *         <p>
 *         Пример кода для курса на https://stepic.org/
 *         <p>
 *         Описание курса и лицензия: https://github.com/vitaly-chibrikov/stepic_java_webserver
 */
public class Main {
    public static void main(String[] args) throws Exception {

        //DBworker.conectDB();
        //DBworker.insertDB();
        //DBworker.read("");
       //DBworker.update();


        AccountService accountService = new AccountService();

        accountService.addNewUser(new UserProfile("admin"));
        accountService.addNewUser(new UserProfile("test"));

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(new SessionsServlet(accountService)), "/api/v1/sessions");
        context.addServlet(new ServletHolder(new ProjectAddServlet(accountService)), "/api/addProj");
        context.addServlet(new ServletHolder(new TaskAddServlet(accountService)), "/api/addTask");
        context.addServlet(new ServletHolder(new ProjectDeleteServlet(accountService)), "/api/delProj");
        context.addServlet(new ServletHolder(new TaskDeleteServlet(accountService)), "/api/delTask");

        //Update
        context.addServlet(new ServletHolder(new ProjectUpdateServlet(accountService)), "/api/updProj");
        context.addServlet(new ServletHolder(new TaskUpdateServlet(accountService)), "/api/updTask");



        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setResourceBase("public_html");

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resource_handler, context});

        Server server = new Server(8080);
        server.setHandler(handlers);

        server.start();
        server.join();
    }
}
