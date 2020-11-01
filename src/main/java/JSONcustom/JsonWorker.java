package JSONcustom;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import entitys.Project;
import entitys.Task;
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
import java.util.Map;

import static javax.print.attribute.standard.ReferenceUriSchemesSupported.HTTP;
import static sun.security.krb5.Confounder.intValue;

public class JsonWorker {


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



        try {

          jsonObject = org.json.HTTP.toJSONObject(jb.toString());
         //   jsonObject =  HTTP.toJSONObject(jb.toString());
        } catch (JSONException e) {
            // crash and burn
            throw new IOException("Error parsing JSONcustom request string");
        }



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

            String lhjk = JsonWorker.jsonPprepare(request);

            Map jsonMap = gson.fromJson(lhjk, Map.class);

            String dsfs = jsonMap.get("name").toString();
            int iu =9;

            if (jsonMap.containsKey("id")){project.setId(((Number)jsonMap.get("id")).intValue());}
            if (jsonMap.containsKey("name")){project.setName(jsonMap.get("name").toString());};
            if (jsonMap.containsKey("user_id")){project.setUser_id(((int)jsonMap.get("user_id")));}
            if (jsonMap.containsKey("description")){project.setDescription(jsonMap.get("description").toString());}

            //project = gson.fromJson(lhjk, Project.class);

            int i =9;
        } catch (Exception c){
            try {
                throw new IOException("Error parsing JSONcustom request string");
            } catch (IOException e) {

            }
        }

        return project;

    }
    public static Task getTask(HttpServletRequest request){
        Task task=null;
        try {
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();

            task = gson.fromJson(JsonWorker.jsonPprepare(request), Task.class);

        } catch (Exception c){
            try {
                throw new IOException("Error parsing JSONcustom request string");
            } catch (IOException e) {

            }
        }

        return task;

    }


}