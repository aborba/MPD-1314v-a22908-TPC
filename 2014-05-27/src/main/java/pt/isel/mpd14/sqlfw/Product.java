package pt.isel.mpd14.sqlfw;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by António on 2014/05/30.
 */
public class Product {
  final int productID;
  final String productName;
  final double unitPrice;
  final int unitsInStock;

  public Product(int productID, String productName, double unitPrice, int unitsInStock) {
    this.productID = productID;
    this.productName = productName;
    this.unitPrice = unitPrice;
    this.unitsInStock = unitsInStock;
  }

  public Product(ResultSet resultSet) throws SQLException {
    this(resultSet.getInt(1), resultSet.getString(2), resultSet.getDouble(3), resultSet.getInt(4));
  }

}
