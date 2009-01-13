package com.mpower.cas.repository;

import com.mpower.cas.domain.Site;
import com.mpower.cas.domain.User;

import java.util.List;
import java.util.Date;

import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

/**
 * JDBC implementation of the SiteDao. This one knows how
 * to talk to the MySQL database engine.
 * @version 1.0
 */
public class JdbcSiteDao extends SimpleJdbcDaoSupport implements SiteDao {

    private SimpleJdbcInsert insertSite;


    @Override
    protected void initTemplateConfig() {
        super.initTemplateConfig();
        insertSite = new SimpleJdbcInsert(getDataSource()).withTableName("sites").usingGeneratedKeyColumns("id");
    }

    /**
     * Retrieves a Site based on the login domain
     *
     * @param loginDomain the login domain of the site
     * @return the corresponding Site object
     */
    @Override
    public Site getSite(String loginDomain) {

        SimpleJdbcTemplate template = getSimpleJdbcTemplate();
        SqlParameterSource params = new MapSqlParameterSource("domain", loginDomain);
        String sql = "SELECT * FROM sites where domain = :domain";

        Site site = template.queryForObject(sql, RowMappers.SITE, params);

        return site;
    }

    /**
     * Retrieves a Site by its unique numeric ID
     *
     * @param siteId the numeric ID of the site
     * @return the corresponding Site object
     */
    @Override
    public Site getSite(int siteId) {

        SimpleJdbcTemplate template = getSimpleJdbcTemplate();
        SqlParameterSource params = new MapSqlParameterSource("id", siteId);
        String sql = "SELECT * FROM sites where id = :id";

        Site site = template.queryForObject(sql, RowMappers.SITE, params);

        return site;
    }

    /**
     * Returns a List of Users associated with a specific Site ID. The
     * list will be empty if there are no users associated with the site (not null).
     *
     * @param siteId the numeric ID of the Site
     * @return a List<User> of the Users
     */
    @Override
    public List<User> getUsers(int siteId) {

        SimpleJdbcTemplate template = getSimpleJdbcTemplate();
        SqlParameterSource params = new MapSqlParameterSource("id", siteId);
        String sql = "SELECT * FROM users WHERE siteid = :id ORDER BY id";

        List<User> users = template.query(sql, RowMappers.USER, params);

        return users;
    }

    /**
     * Returns the count of the number of users for a Site
     *
     * @param siteId the numeric Site ID
     * @return an int user count, always >= 0
     */
    @Override
    public int getUserCount(int siteId) {

        SimpleJdbcTemplate template = getSimpleJdbcTemplate();
        String sql = "SELECT COUNT(*) FROM users WHERE siteid = ?";

        int count = template.queryForInt(sql, siteId);

        return count;
    }

    /**
     * Returns a List of Users for the specific Site who are currently locked out.
     *
     * @param siteId the numeric ID of the Site
     * @return a List<User> of the locked-out Users
     */
    @Override
    public List<User> getLockedOutUsers(int siteId) {

        SimpleJdbcTemplate template = getSimpleJdbcTemplate();
        SqlParameterSource params = new MapSqlParameterSource("id", siteId);
        String sql = "SELECT * FROM users WHERE siteid = :id AND locked = TRUE ORDER BY id";

        List<User> users = template.query(sql, RowMappers.USER, params);

        return users;

    }

    /**
     * Returns a count of the number of users for a Site that
     * are currently locked out
     *
     * @param siteId the numeric Site ID
     * @return an int count of locked out users, always >= 0
     */
    @Override
    public int getLockedOutUserCount(int siteId) {

        SimpleJdbcTemplate template = getSimpleJdbcTemplate();
        String sql = "SELECT COUNT(*) FROM users WHERE siteid = ? AND locked = TRUE";

        int count = template.queryForInt(sql, siteId);

        return count;
    }

    /**
     * Update the data for the specified Site. Only certain fields are allowed
     * to be updated. Changes to Login Domain are ignored. This value cannot be
     * changed, as it must remain fixed to main referential integrity across the
     * systems and access logs.
     * Note that the ID property of the Site must be present. If ID is not set,
     * this method will assume you are trying to create instead of update, and will
     * throw an IllegalArgumentException
     *
     * @param site the Site to update
     */
    @Override
    public void updateSite(Site site) {

        SimpleJdbcTemplate template = getSimpleJdbcTemplate();
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id", site.getId());
        map.addValue("active",true); //TODO This can't be edited, so set to TRUE for now
        map.addValue("contactemail", site.getContactEmail());
        map.addValue("contactphone", site.getContactPhone());
        map.addValue("locked", site.isLocked());
        map.addValue("name", site.getCompanyName());
        map.addValue("primarycontact", site.getPrimaryContact());

        String sql = "UPDATE sites SET active=:active, contactemail=:contactemail, " +
                "contactphone=:contactphone, locked=:locked, name=:name, primarycontact=:primarycontact " +
                "WHERE id=:id";

        template.update(sql, map);
    }

    /**
     * Creates a new Site in the system, and sets the generated ID
     *
     * @param site a Site object containing the initial data for record
     */
    @Override
    public void createSite(Site site) {

        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("active",site.isActive());
        map.addValue("contactemail", site.getContactEmail());
        map.addValue("contactphone", site.getContactPhone());
        map.addValue("domain", site.getLoginDomain());
        map.addValue("locked", site.isLocked());
        map.addValue("name", site.getCompanyName());
        map.addValue("primarycontact", site.getPrimaryContact());

        Number key = insertSite.executeAndReturnKey(map);
        site.setId(key.intValue());
    }
}
