package pt.isel.mpd14.sqlfw;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import static java.lang.String.format;

/**
 * Hello world!
 */
public class App {
  public static void main(String[] args) throws SQLException {

    final String connectionUrl =
        "jdbc:sqlserver://localhost:1433;" +
            "databaseName=Northwind;" +
            "user=mpd14v;" +
            "password=leicisel";

    // Connection c = DriverManager.getConnection(connectionUrl);

    SQLServerDataSource dataSource = new SQLServerDataSource();
    dataSource.setUser("mpd14v");
    dataSource.setPassword("leicisel");
    dataSource.setServerName("localhost");
    dataSource.setPortNumber(1433);
    dataSource.setDatabaseName("Northwind");

    Connection connection = dataSource.getConnection();

    PreparedStatement preparedStatement = connection.prepareStatement(
        "SELECT ProductID, ProductName, UnitPrice, UnitsInStock "
            + "FROM Products WHERE UnitPrice > ? AND UnitsInStock > ?");
    preparedStatement.setDouble(1, 30.0);
    preparedStatement.setInt(2, 20);
    ResultSet resultSet = preparedStatement.executeQuery();

    Collection<Product> collection = new LinkedList<>();
    while (resultSet.next()) {
      collection.add(new Product(resultSet));
    }
    collection.forEach((p) -> System.out.println(format("%d %s %f %d",
          p.productID, p.productName, p.unitPrice, p.unitsInStock))
    );
    System.out.println("Count of products types found: " + collection.size());
  }
}
