package de.mrrobworks.springbootangular.backend.helper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;

/**
 * Helper Class to create Test-XML-Files for DbUnit-Tests. Hint: Not in Spring-Boot-Context, just a
 * Helper.
 */
class DatabaseExportForDbUnit {

  private Connection con;

  private DatabaseExportForDbUnit() throws SQLException {
    con = DriverManager
        .getConnection("jdbc:postgresql://localhost:54321/spring_boot_angular_db_dev",
            "postgres_dev", "changeit_dev");
  }

  private void writeToXmlFile(final String tableName, final String sql, final String fileName)
      throws DatabaseUnitException, IOException {

    final IDatabaseConnection dbUnitCon = new DatabaseConnection(con);
    final DatabaseConfig config = dbUnitCon.getConfig();
    config.setProperty(DatabaseConfig.FEATURE_QUALIFIED_TABLE_NAMES, true);
    final QueryDataSet dataSet = new QueryDataSet(dbUnitCon);
    dataSet.addTable(tableName, sql);
    FlatXmlDataSet.write(dataSet, new FileOutputStream(fileName));
  }

  public static void main(String[] args) throws SQLException, DatabaseUnitException, IOException {
    final DatabaseExportForDbUnit export = new DatabaseExportForDbUnit();
    export.writeToXmlFile("person", "select * from person", "dbunit_example.xml");
  }
}
