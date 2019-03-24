package de.mrrobworks.springbootangular.backend.helper;

import java.lang.management.ManagementFactory;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.hsqldb.util.DatabaseManagerSwing;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * A Test-Class which inherit this class get access to the Hsqldb-DatabaseManger in debug-Mode.
 */
public abstract class EnableDatabaseManager {

  @Autowired
  private DataSource dataSource;

  @BeforeEach
  public void runOnceBeforeMethod() throws SQLException {
    final boolean debugEnabled = ManagementFactory.getRuntimeMXBean().getInputArguments().stream()
        .anyMatch(s -> s.contains("jdwp"));
    if (debugEnabled) {
      DatabaseManagerSwing.main(
          new String[]{"--url", dataSource.getConnection().getMetaData().getURL(), "--noexit"});
    }
  }
}
