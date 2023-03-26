package com.example.practice2.repo;

import com.example.practice2.domain.Hotel;
import com.example.practice2.domain.Type;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class HotelRepo implements Repository<Double, Hotel>{

    private String url;
    private String userName;
    private String password;

    public HotelRepo(String url, String userName, String password) {
        this.url = url;
        this.userName = userName;
        this.password = password;
    }


    @Override
    public Iterable<Hotel> findAll() {
        Set<Hotel> sections = new HashSet<>();
        try (Connection connection = DriverManager.getConnection(url, userName, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM hotels");
             ResultSet resultSet = statement.executeQuery();) {
            while (resultSet.next()) {
                Double id1 = resultSet.getDouble("hotelid");
                Double locationid = resultSet.getDouble("locationid");
                String name = resultSet.getString("hotelname");
                int norooms = resultSet.getInt("norooms");
                Double price = resultSet.getDouble("pricepernight");
                String type=resultSet.getString("type");
                Hotel section = new Hotel(id1, locationid, name, norooms, price, type);
                section.setId(id1);
                sections.add(section);

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sections;
    }


    @Override
    public Hotel save(Hotel entity) {
        String sql = "insert into hotels(hotelid, locationid, hotelname, norooms, price, type) values(?, ?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(url, userName, password);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setDouble(1, entity.getIdHotel());
            ps.setDouble(2, entity.getLocationId());
            ps.setString(3, entity.getHotelName());
            ps.setInt(4, entity.getNoRooms());
            ps.setDouble(5, entity.getPricePerNight());
            Type t=null;
            if(entity.getType().equals(t.family)) {
                ps.setString(6, "family");
            }
            if(entity.getType().equals(t.teenagers)) {
                ps.setString(6, "teenagers");
            }
            if(entity.getType().equals(t.oldpeople)) {
                ps.setString(6, "oldpeople");
            }

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return entity;

    }
}
