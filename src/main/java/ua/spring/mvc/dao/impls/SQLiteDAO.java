package ua.spring.mvc.dao.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;
@Component
public class SQLiteDAO {

    private NamedParameterJdbcTemplate jdbcTemplate;

    private static final String mp3Table = "mp3";
    private static final String mp3View = "mp3_view";

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }


    public int insertMP3(MP3 mp3) {
        int author_id = insertAuthor(mp3.getAuthor());
        String sql2 = "INSERT INTO " + mp3Table + " (name, author_id) VALUES (:mp3_name, :author_id)";

        MapSqlParameterSource params2 = new MapSqlParameterSource();

        params2.addValue("mp3_name", mp3.getName());
        params2.addValue("author_id", author_id);


        return jdbcTemplate.update(sql2, params2);
    }


    public int insertAuthor(Author author) {
        String sql1 = "INSERT INTO author (name) VALUES (:name)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource params1 = new MapSqlParameterSource();
        params1.addValue("name", author.getName());
        jdbcTemplate.update(sql1, params1, keyHolder);
        return keyHolder.getKey().intValue();
    }



    public void insert(List<MP3> mp3List) {
        for(MP3 mp3: mp3List) {
            insertMP3(mp3);
        }
    }

    public void delete(MP3 mp3) {
        delete(mp3.getId());
    }


    public void delete(int id) {
        String sql = "DELETE FROM " + mp3Table + " WHERE id=:id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        jdbcTemplate.update(sql, params);
    }


    public MP3 getMP3ByID(int id) {
        String sql = "SELECT * FROM " + mp3View + " WHERE mp3_id=:mp3_id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("mp3_id", id);
        return jdbcTemplate.queryForObject(sql, params, new MP3Mapper());
    }

    public MP3 getMP3ByAuthor(String author) {
        String sql = "SELECT * FROM " + mp3View + " WHERE upper(author_name) LIKE :author_name";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("author_name", "%" + author.toUpperCase() + "%");
        return jdbcTemplate.queryForObject(sql, params, new MP3Mapper());
    }

    public MP3 getMP3ByName(String name) {
        String sql = "SELECT * FROM " + mp3View + " WHERE upper(mp3_name) LIKE :mp3_name";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("mp3_name", "%" + name + "%");
        return jdbcTemplate.queryForObject(sql, params, new MP3Mapper());
    }

    public List<MP3> getMP3ListByName(String name) {
        String sql = "SELECT * FROM " + mp3View + " WHERE upper(mp3_name) like :mp3_name";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("mp3_name", "%" + name.toUpperCase() + "%");
        return jdbcTemplate.query(sql, params, new MP3Mapper());
    }

    public List<MP3> getMP3ListByAuthor(String author) {
        String sql = "SELECT * FROM " + mp3View + " WHERE upper(author_name) like :author_name";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("author", "%" + author.toUpperCase() + "%");
        return jdbcTemplate.query(sql, params, new MP3Mapper());
    }

    public Map<String, Integer> getStat() {
        String sql = "SELECT author_name, count(*) AS count FROM " + mp3View + " GROUP BY author_name";
        return jdbcTemplate.query(sql, new ResultSetExtractor<Map<String, Integer>>() {
            public Map<String, Integer> extractData(ResultSet rs) throws SQLException, DataAccessException {
                Map<String, Integer> map = new TreeMap<String, Integer>();
                while (rs.next()) {
                    String author = rs.getString("author_name");
                    int count = rs.getInt("count");
                    map.put(author, count);
                }
                return map;
            }
        });
    }

    public int getMP3Count() {
        String sql = "SELECT COUNT(*) FROM " + mp3Table;
        return jdbcTemplate.getJdbcOperations().queryForObject(sql, Integer.class);
    }

    private static final class MP3Mapper implements RowMapper<MP3> {

        public MP3 mapRow(ResultSet rs, int rowNum) throws SQLException {
            Author author = new Author();
            author.setId(rs.getInt("author_id"));
            author.setName(rs.getString("author_name"));

            MP3 mp3 = new MP3();
            mp3.setName(rs.getString("mp3_name"));
            mp3.setId(rs.getInt("mp3_id"));
            mp3.setAuthor(author);
            return  mp3;
        }
    }
}
