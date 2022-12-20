/*
Created by Group 9.
Magnus, Jesper and Johnni.
 */

package easv.mrs.DAL.db;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
import java.sql.SQLException;

//Creates the databaseconnector class

public class MyDatabaseConnector {
    private SQLServerDataSource dataSource;


//Database logon information

    public MyDatabaseConnector()
    {
        dataSource = new SQLServerDataSource();
        dataSource.setServerName("10.176.111.31");
        dataSource.setDatabaseName("Songs");
        dataSource.setUser("CSe22A_14");
        dataSource.setPassword("CSe22A_14");
        dataSource.setTrustServerCertificate(true);
    }
//Sends the logon information.

    public Connection getConnection() throws SQLServerException
    {
        return dataSource.getConnection();
    }

//Test if there is an open connection.

    public static void main(String[] args) throws SQLException
    {
        MyDatabaseConnector databaseConnector = new MyDatabaseConnector();

        try (Connection connection = databaseConnector.getConnection())
        {
            System.out.println("Is it open? " + !connection.isClosed());
        }
    }
}
