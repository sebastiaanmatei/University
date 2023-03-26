package com.example.practice2.repo;

import com.example.practice2.domain.Hotel;
import com.example.practice2.domain.OfferDetails;
import com.example.practice2.domain.SpecialOffer;
import com.example.practice2.domain.Type;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class OfferRepo implements Repository<Double, SpecialOffer> {

    private String url;
    private String userName;
    private String password;

    public OfferRepo(String url, String userName, String password) {
        this.url = url;
        this.userName = userName;
        this.password = password;
    }


    @Override
    public Iterable<SpecialOffer> findAll() {
        Set<SpecialOffer> sections = new HashSet<>();
        try (Connection connection = DriverManager.getConnection(url, userName, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM offers");
             ResultSet resultSet = statement.executeQuery();) {
            while (resultSet.next()) {
                Double id1 = resultSet.getDouble("offerid");
                Double hotelid = resultSet.getDouble("hotelid");
                Date startdate=resultSet.getDate("startdate");
                Date enddate=resultSet.getDate("enddate");
                Integer percents=resultSet.getInt("percents");
                SpecialOffer section = new SpecialOffer(id1, hotelid, startdate, enddate, percents);
                section.setId(id1);
                sections.add(section);

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sections;
    }


    @Override
    public SpecialOffer save(SpecialOffer entity) {
        String sql = "insert into offers(offerid, hotelid, startdate, enddate, percents) values(?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(url, userName, password);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setDouble(1, entity.getIdOff());
            ps.setDouble(2, entity.getHotelId());
            ps.setDate(3, (Date) entity.getStartDate());
            ps.setDate(4, (Date) entity.getEndDate());
            ps.setInt(5, entity.getPercents());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return entity;

    }
}
