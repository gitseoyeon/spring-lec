package org.example.prac.repository;

import lombok.RequiredArgsConstructor;
import org.example.prac.model.User_jdbc;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class User_jdbcRepository {
    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<User_jdbc> userRowMapper = (resultSet, rowNum) -> {
        User_jdbc userJdbc = User_jdbc.builder()
                .id(resultSet.getInt("id"))
                .username(resultSet.getString("username"))
                .password(resultSet.getString("password"))
                .build();

        return userJdbc;
    };


    public User_jdbc findByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";

        try {
            return jdbcTemplate.queryForObject(sql, userRowMapper, username);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public int save(User_jdbc userJdbc) {
        String sql = "INSERT INTO users (username, password) VALUES (?, ?)";

        return jdbcTemplate.update(sql, userJdbc.getUsername(), userJdbc.getPassword());

    }
}
