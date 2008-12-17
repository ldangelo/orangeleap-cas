package com.mpower.cas.repository;

import com.mpower.cas.domain.User;

import java.util.List;

import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

/**
 * This DAO will search a specified list of column names using a LIKE wildcard
 * query with the search criteria
 * @version 1.0
 */
public class JdbcUserSearchDao extends SimpleJdbcDaoSupport implements UserSearchDao {

    // base SQL to get all the columns for Users under a specific site
    private final static String BASE_SQL = "SELECT * FROM users WHERE siteid = :siteid";

    // the customized SQL string based on the columns needed
    private String sql;

    // hold on to the list of column names
    private List<String> columnNames;

    /**
     * Finds the users based on the criteria. NOTE: this uses a shortcut
     * method. If the criteria is null or zero-length, we'll return a list of all
     * the users for the site.
     * @param criteria the text criteria
     * @param siteId the site ID parent
     * @return the list of matching users as at List<User>
     */
    @Override
    public List<User> findUsers(String criteria, int siteId) {

        SimpleJdbcTemplate template = getSimpleJdbcTemplate();
        MapSqlParameterSource params = new MapSqlParameterSource();

        params.addValue("siteid", siteId);
        List<User> users = null;

        if(criteria == null || criteria.trim().length() == 0) {

            users = template.query(BASE_SQL, RowMappers.USER, params);

        } else {
            for (String s : columnNames) {
                params.addValue(s, "%" + criteria + "%");
            }

            users = template.query(sql, RowMappers.USER, params);
        }
      
        return users;
    }

    /**
     * Injected property. Will contain a list of the column names to be used for searching.
     * The columns should all be some type of text type, as this implementation will use
     * a LIKE operation with wildcards to check the column values.
     * @param columnNames  the List of column names to search
     */
    public void setColumnNames(List<String> columnNames) {

        if(columnNames.size() > 0) {
            this.columnNames = columnNames;
            StringBuilder builder = new StringBuilder(BASE_SQL);

            builder.append(" AND (");
            for(String s : columnNames) {

                builder.append(s).append(" LIKE :").append(s).append(" OR ");
            }

            sql = builder.substring(0, builder.lastIndexOf("OR "));

            sql += ")";
            
        } else {
            throw new IllegalArgumentException("At least one column name must be specified"); 
        }
    }

}
