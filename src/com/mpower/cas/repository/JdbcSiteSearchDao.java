package com.mpower.cas.repository;

import com.mpower.cas.domain.Site;
import java.util.List;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

/**
 * Implmentation of the SiteSearchDao. This implementation takes a list of
 * column names in the Sites table as a property and then constructs a SQL LIKE
 * query comparing the criteria against the specified columns.
 * @version 1.0
 */
public class JdbcSiteSearchDao extends SimpleJdbcDaoSupport implements SiteSearchDao {

    // base SQL to get all the columns for Users under a specific site
    private final static String BASE_SQL = "SELECT * FROM sites";

    // the customized SQL string based on the columns needed
    private String sql;

    // hold on to the list of column names
    private List<String> columnNames;


    /**
     * Finds the Sites based on the criteria. If the criteria is null
     * or zero-length, this will return a list of all the sites.
     * @param criteria the text criteria
     * @return the list of sites that match the criteria as List<Site>
     */
    @Override
    public List<Site> findSites(String criteria) {

        SimpleJdbcTemplate template = getSimpleJdbcTemplate();
        MapSqlParameterSource params = new MapSqlParameterSource();

        List<Site> sites = null;

        if (criteria == null || criteria.trim().length() == 0) {
            sites = template.query(BASE_SQL, RowMappers.SITE, params);
        } else {

            for (String s : columnNames) {
                params.addValue(s, "%" + criteria + "%");
            }

            sites = template.query(sql, RowMappers.SITE, params);
        }

        return sites;
    }

    /**
     * Injected property. Will contain a list of the column names to be used for searching.
     * The columns should all be some type of text type, as this implementation will use
     * a LIKE operation with wildcards to check the column values.
     * @param columnNames the List of column names to search
     */
    public void setColumnNames(List<String> columnNames) {

        if(columnNames.size() > 0) {
            this.columnNames = columnNames;
            StringBuilder builder = new StringBuilder(BASE_SQL);

            builder.append(" WHERE ");
            for(String s : columnNames) {

                builder.append(s).append(" LIKE :").append(s).append(" OR ");
            }

            sql = builder.substring(0, builder.lastIndexOf("OR "));

        } else {
            throw new IllegalArgumentException("At least one column name must be specified");
        }
    }


}
