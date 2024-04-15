package dbService;

import dbService.dao.UsersDAO;
import dbService.dataSets.UsersDataSet;
import org.h2.jdbcx.JdbcDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DBService {

    private final Connection connection;

    public DBService() {
        this.connection = getH2Connection();
        UsersDAO dao = new UsersDAO(connection);
    }

    public UsersDataSet getUser(long id) throws SQLException {
            return (new UsersDAO(connection).get(id));
    }

    public long addUser(String name, String password) throws DBException {
        try {
            connection.setAutoCommit(false);
            UsersDAO dao = new UsersDAO(connection);
            dao.createTable();
            dao.insertUser(name, password);
            connection.commit();
            System.out.println("!!saveUser - " + name + " : "+password);
            return dao.getUserId(name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public UsersDataSet getUserByName(String name) {
        try {
            UsersDAO dao = new UsersDAO(connection);
            UsersDataSet usersDataSet = dao.getUserByLogin(name);
            System.out.println("!!getUserByName - " + usersDataSet.getName()+ " : "+usersDataSet.getPassword());
            return usersDataSet;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void cleanUp() throws DBException {
        UsersDAO dao = new UsersDAO(connection);
        try {
            dao.dropTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getH2Connection() {
        try {
            String url = "jdbc:h2:./h2db";
            String name = "test";
            String password = "test";

            JdbcDataSource ds = new JdbcDataSource();
            ds.setURL(url);
            ds.setUser(name);
            ds.setPassword(password);

            Connection connection = DriverManager.getConnection(url, name, password);
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
