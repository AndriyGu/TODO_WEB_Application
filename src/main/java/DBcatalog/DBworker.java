package DBcatalog; /**
 * Created by AdanaC on 16.10.2020.
 */
import entitys.Project;
import entitys.Task;

import java.io.File;
import java.sql.*;

public class DBworker {
    static Connection co;
    static PreparedStatement preparedStatement = null;

    public static void conectDB(){

        try {
            File f = new File(".\\src\\main\\java\\DBcatalog\\todo.db");
            if(f.exists()) {
                System.out.println("yes");
            }


            Class.forName("org.sqlite.JDBC"); //".src\\resourses\\sqlite-jdbc-3.32.3.2.jar"
            co = DriverManager.getConnection(
                    "jdbc:sqlite:.\\src\\main\\java\\DBcatalog\\todo.db");
            //DB/todo.db
            System.out.println("conect");
        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    public static void closeDB(){
        try {
            if (preparedStatement!=null){preparedStatement.close();}
            co.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //Project

    public static void addProject(Project project){

        String query =
                "INSERT INTO projects (name, user_id, description) "+
                        "VALUES ('"+project.getName()+"','"+project.getUser_id()+"','"+project.getDescription()+"')";
        try {

            Statement statement = co.createStatement();
            statement.executeUpdate(query);
            System.out.println("addProject");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateProject (Project project){

int i = 4;

        try {
            preparedStatement = co.prepareStatement(
                    "UPDATE projects SET name = ?, description =? WHERE id = ?");

                preparedStatement.setString(1,project.getName());
                preparedStatement.setString(2,project.getDescription());
                preparedStatement.setInt(3,project.getId());

            int ui =preparedStatement.executeUpdate();
                int t =7;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void deleteProject (Project project){

        try {
            preparedStatement = co.prepareStatement(
                    "DELETE FROM  projects WHERE id = ?");

            preparedStatement.setInt(1,project.getId());

            int ui =preparedStatement.executeUpdate();


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        try {
            preparedStatement = co.prepareStatement(
                    "DELETE FROM  task WHERE project_id = ?");

            preparedStatement.setInt(1,project.getId());

            int ui =preparedStatement.executeUpdate();


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


    }


    //Task

    public static void addTask(Task task){

        String query =
                "INSERT INTO task (deadline, text, status, project_id, date_create, priority) "+
                        "VALUES ('"+task.getDedline()+"','"+task.getText()+"','"+task.getStatus()+
                        "','"+task.getProject_id()+"','"+task.getDate_crerate()+
                        "','"+task.getPrority()+"')";
        try {

            Statement statement = co.createStatement();
            statement.executeUpdate(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateTask (Task task){

        try {
            preparedStatement = co.prepareStatement(
                    "UPDATE task SET deadline = ?, text =?, status =?, project_id =?, date_create =?, priority = ? WHERE id = ?");

            preparedStatement.setInt(1, task.getDedline());
            preparedStatement.setString(2, task.getText());
            preparedStatement.setInt(3,task.getStatus());
            preparedStatement.setInt(4,task.getProject_id());
            preparedStatement.setInt(5,task.getDate_crerate());
            preparedStatement.setInt(6,task.getPrority());

            int ui =preparedStatement.executeUpdate();


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void deleteTask (Task task){

        try {
            preparedStatement = co.prepareStatement(
                    "DELETE FROM  task WHERE id = ?");

            preparedStatement.setInt(1,task.getId());

            int ui =preparedStatement.executeUpdate();


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }



}
