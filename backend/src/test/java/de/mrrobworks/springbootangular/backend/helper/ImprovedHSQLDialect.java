package de.mrrobworks.springbootangular.backend.helper;

import org.hibernate.dialect.HSQLDialect;

/**
 * Workaround.
 *
 * @see <a href="https://stackoverflow.com/questions/12054422/unsuccessful-alter-table-xxx-drop-constraint-yyy-in-hibernate-jpa-hsqldb-standa">Description
 * found here</a>
 */
public class ImprovedHSQLDialect extends HSQLDialect {

  @Override
  public String getDropSequenceString(String sequenceName) {
    // Adding the "if exists" clause to avoid warnings
    return "drop sequence if exists " + sequenceName;
  }

  @Override
  public boolean dropConstraints() {
    // We don't need to drop constraints before dropping tables, that just
    // leads to error messages about missing tables when we don't have a
    // schema in the database
    return false;
  }
}
