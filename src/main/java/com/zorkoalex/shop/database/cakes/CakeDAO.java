package com.zorkoalex.shop.database.cakes;

import com.zorkoalex.shop.dto.cake.Cake;
import com.zorkoalex.shop.exception.CakeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class CakeDAO {
    public final JdbcTemplate jdbcTemplate;
    public int id = 100;

    @Autowired
    public CakeDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public Cake getCake(Long id) throws CakeNotFoundException {
        return jdbcTemplate.query("SELECT * FROM cake WHERE id=?", new BeanPropertyRowMapper<>(Cake.class), id).stream()
                .findAny()
                .orElseThrow(() -> new CakeNotFoundException("No such cake with id "+id));
    }

    public void addCake(Cake cake) {
        jdbcTemplate.update("INSERT INTO cake VALUES (?, ?, ?, ?, ?, ?)", id, cake.getCalories(),
                cake.getImage(), cake.getName(), cake.getWeight(), cake.getPrice());
        id++;
    }

    public List<Cake> getCakes() {
      return jdbcTemplate.query("SELECT * FROM cake", new BeanPropertyRowMapper<>(Cake.class));
    }
}