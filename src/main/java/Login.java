import java.sql.*;
import java.util.ArrayList;

public class Login {
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    public Login() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "1234");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Driver not found");
            return;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("PostgreSQL JDBC Driver successfully connected");


    }
    public void delete(String backMess){
        String sql = "DELETE FROM books.books2 WHERE mess=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, backMess);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    public void  add(String text) {
        String sql = "INSERT INTO books.books2(mess) VALUES(?) ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, text);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    public ArrayList<String> get() {
        String sql = "select * from books.books2";
        ArrayList<String> list = new ArrayList();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String mess = resultSet.getString("mess");
                list.add(mess);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;



    }
}
