package com.example.practice2.repo;

import com.example.practice2.domain.Hotel;
import com.example.practice2.domain.Location;
import com.example.practice2.domain.Type;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class LocationRepo implements Repository<Double, Location>{
    private String url;
    private String userName;
    private String password;

    public LocationRepo(String url, String userName, String password) {
        this.url = url;
        this.userName = userName;
        this.password = password;
    }


    @Override
    public Iterable<Location> findAll() {
        Set<Location> sections = new HashSet<>();
        try (Connection connection = DriverManager.getConnection(url, userName, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM locations");
             ResultSet resultSet = statement.executeQuery();) {
            while (resultSet.next()) {
                Double id1 = resultSet.getDouble("locationid");
                String name = resultSet.getString("locationname");
                Location section = new Location(id1, name);
                section.setId(id1);
                sections.add(section);

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sections;
    }


    @Override
    public Location save(Location entity) {
        String sql = "insert into locations(locationid, locationname) values(?, ?)";

        try (Connection connection = DriverManager.getConnection(url, userName, password);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setDouble(1, entity.getIdLoc());
            ps.setString(2, entity.getLocationName());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return entity;

    }
}
