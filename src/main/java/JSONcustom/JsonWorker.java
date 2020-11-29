package JSONcustom;

import DBcatalog.DBworker;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import entitys.Project;
import entitys.Task;
import entitys.User;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.HTTP;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Map;

import static javax.print.attribute.standard.ReferenceUriSchemesSupported.HTTP;
import static sun.security.krb5.Confounder.intValue;

public class JsonWorker {
        private static int EMPTY_OBJ = -1;

    public static String jsonPprepare(HttpServletRequest request)throws ServletException, IOException{


        request.setCharacterEncoding("utf-8");
        Charset.defaultCharset();


        JSONObject jsonObject;

        StringBuffer jb = new StringBuffer();
        String line = null;


        try {
            BufferedReader reader = (request.getReader());
            while ((line = reader.readLine()) != null){
                jb.append(line);}

            String line2 ="" +jb;

                int i = 6;
        } catch (Exception e) { /*report an error*/ }

        System.out.println("jb length "+jb.length());
        String resultOfPOST="";
        try {
            resultOfPOST = java.net.URLDecoder.decode(jb.toString(), StandardCharsets.UTF_8.name());

        } catch (UnsupportedEncodingException e) {
            // not going to happen - value came from JDK's own StandardCharsets
        }

        return resultOfPOST;

    }

    public static Project getProject(HttpServletRequest request){
        Project project= new Project();
        try {

            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();

            String tempString = JsonWorker.jsonPprepare(request);

            Map jsonMapProj = gson.fromJson(tempString, Map.class);



            if (jsonMapProj.containsKey("id")){project.setId(((Number)jsonMapProj.get("id")).intValue());}
            if (jsonMapProj.containsKey("name")){project.setName(jsonMapProj.get("name").toString());}

            if (jsonMapProj.containsKey("user_id")){project.setUser_id(((Number)jsonMapProj.get("user_id")).intValue());}
                else{project.setUser_id(EMPTY_OBJ);}
            if (jsonMapProj.containsKey("description")){project.setDescription(jsonMapProj.get("description").toString());}
            if (jsonMapProj.containsKey("created")){project.setCreated(((Number)jsonMapProj.get("created")).intValue());}
            else{project.setCreated(EMPTY_OBJ);}

        } catch (Exception c){
            try {
                throw new IOException("Error parsing JSONcustom request string");
            } catch (IOException e) {

            }
        }

        return project;

    }
    public static Task getTask(HttpServletRequest request){
        Task task=new Task();
        try {
            String tempString = JsonWorker.jsonPprepare(request);
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();

            Map jsonMapTasks = gson.fromJson(tempString, Map.class);

            if(jsonMapTasks.containsKey("id")){task.setId(((Number)jsonMapTasks.get("id")).intValue());}
            if(jsonMapTasks.containsKey("deadline")){task.setDedline(((Number)jsonMapTasks.get("deadline")).intValue());}
            else{task.setDedline(EMPTY_OBJ);}
            if(jsonMapTasks.containsKey("text")){task.setText(jsonMapTasks.get("text").toString());}
            if(jsonMapTasks.containsKey("status")){task.setStatus(((Number)jsonMapTasks.get("status")).intValue());}
            else{task.setStatus(EMPTY_OBJ);}
            if(jsonMapTasks.containsKey("project_id")){task.setProject_id(((Number)jsonMapTasks.get("project_id")).intValue());}
            else{task.setProject_id(EMPTY_OBJ);}
            if(jsonMapTasks.containsKey("date_crerate")){task.setDate_crerate(((Number)jsonMapTasks.get("date_crerate")).intValue());}
            else{task.setDate_crerate(EMPTY_OBJ);}
            if(jsonMapTasks.containsKey("prority")){task.setPrority(jsonMapTasks.get("prority").toString());}
            if(jsonMapTasks.containsKey("name")){task.setName(jsonMapTasks.get("name").toString());}

        } catch (Exception c){
            try {
                throw new IOException("Error parsing JSONcustom request string");
            } catch (IOException e) {

            }
        }

        return task;

    }
    public static User getUser(HttpServletRequest request){
        User user=null;
        try {
            String tempString = JsonWorker.jsonPprepare(request);
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();

            user = gson.fromJson(tempString, User.class);
        } catch (Exception c){
            try {
                throw new IOException("Error parsing JSONcustom request string");
            } catch (IOException e) {

            }
        }

        return user;

    }



    public static String createJsonByUserID(int userID) {
        JsonObject jsonObject = new JsonObject();
        StringBuffer sb=null;

        //ArrayList<Project> projects = DBworker.getProjectUser_id(user.getId());
        ArrayList<Project> projects = DBworker.getProjectUser_id(userID);

        int projID=EMPTY_OBJ;

        if(projects!=null & projects.size()>0){projID = projects.get(0).getId();}

        if (projects.size() > 0) {
            sb = new StringBuffer();
           // sb.append("{");
           // sb.append("\"UserId\":");
          // sb.append(userID + ", \"projects\":");

           sb.append("[");
            //внутри массива проектов

            for (int p =0; p<projects.size(); p++) {
                Project pr = projects.get(p);
                sb.append("{\"id\":" + pr.getId() + ",");
                sb.append("\"name\":\"" + pr.getName() + "\",");
                sb.append("\"created\":" + pr.getCreated() + ",");
                sb.append("\"description\":\"" + pr.getDescription() + "\",");

                ArrayList<Task> tasks = DBworker.getTasksProj_id(pr.getId());
                if (tasks.size()==0){sb.append("\"tasks\":[]");};
                if (tasks.size() > 0) {
                    sb.append("\"tasks\":[");

                    for (int i = 0; i < tasks.size(); i++) {
                        Task task = tasks.get(i);
                        sb.append("{\"id\":" + task.getId() + ",");
                        sb.append("\"name\":\"" + task.getName() + "\",");
                        sb.append("\"description\":\"" + task.getText() + "\",");
                        sb.append("\"created\":" + task.getDate_crerate() + ",");
                        sb.append("\"priority\":\"" + task.getPrority() + "\"}");

                        if (i!=tasks.size()-1) {
                            sb.append(",");

                        }
                        else {
                            System.out.println("rew");
                        }
                    }

                    sb.append("]");

                }

                sb.append("}");

                if (p!=projects.size()-1) {
                    sb.append(",");

                }
                else {
                    System.out.println("rew");
                }

            }

            sb.append("]");
           // sb.append("}");

        } //else {d} // проектов нет

        return sb.toString();


    }

}