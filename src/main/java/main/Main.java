package main;

import DBcatalog.DBworker;
import JSONcustom.JsonWorker;
import accounts.AccountService;
import accounts.UserProfile;
import com.google.gson.JsonObject;
import entitys.Project;
import entitys.Task;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.*;

import java.util.ArrayList;

/**
 * @author v.chibrikov
 *         <p>
 *         Пример кода для курса на https://stepic.org/
 *         <p>
 *         Описание курса и лицензия: https://github.com/vitaly-chibrikov/stepic_java_webserver
 */
public class Main {
    public static void main(String[] args) throws Exception {

        int [] mass = {1,2,3};

        int [] mass2 = mass;

        mass[2]=4;


        System.out.println(mass2[2]);


        //String json = gson.toJson(entity);

        DBworker.conectDB();

      //  Task task = DBworker.getTaskID(5);

       // ArrayList<Task> arrayList = DBworker.getTasksProj_id(8);

       // Project project = DBworker.getProjectID(13);

       // ArrayList<Project> arrProj = DBworker.getProjectUser_id(88);

       // JsonObject jsonObject = JsonWorker.createJson(3);



        //DBworker.conectDB();
        //DBworker.insertDB();
        //DBworker.read("");
        //DBworker.update();

        System.out.println();
        AccountService accountService = new AccountService();



        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(new SessionsServlet(accountService)), "/api/sessions");
        context.addServlet(new ServletHolder(new ProjectAddServlet(accountService)), "/api/addProject");
        context.addServlet(new ServletHolder(new TaskAddServlet(accountService)), "/api/addTask");
        context.addServlet(new ServletHolder(new ProjectDeleteServlet(accountService)), "/api/delProject");
        context.addServlet(new ServletHolder(new TaskDeleteServlet(accountService)), "/api/deleteTask");

        //get
        context.addServlet(new ServletHolder(new UserProjectServlet(accountService)), "/api/getUserData");



        //Update
        context.addServlet(new ServletHolder(new ProjectUpdateServlet(accountService)), "/api/updateProject");
        context.addServlet(new ServletHolder(new TaskUpdateServlet(accountService)), "/api/updateTask");

        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setResourceBase("public_html");

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resource_handler, context});

        Server server = new Server(8080);
        server.setHandler(handlers);


        server.start();
        server.join();
    }

    public void sd(String s){
        s="ewrwer";
    }
}
