package com.example.clinique.repo;

import com.example.clinique.domain.Section;
import com.example.clinique.repo.Repository;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class SectionRepo implements Repository<Integer, Section> {

    private String url;
    private String userName;
    private String password;

    public SectionRepo(String url, String userName, String password) {
        this.url = url;
        this.userName = userName;
        this.password = password;
    }


    @Override
    public Section findOne(Integer id) {
        try (Connection connection = DriverManager.getConnection(url, userName, password);
             PreparedStatement statement = connection.prepareStatement("select * from sections where id=" + id);
             ResultSet resultSet = statement.executeQuery();
        ) {
            if (resultSet.next()) {
                int id1 = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int idChief = resultSet.getInt("idChief");
                int price = resultSet.getInt("price");
                int maxDuration = resultSet.getInt("maxDuration");
                Section section=new Section(id1, name,idChief,price,maxDuration);
                return section;
            } else {
                return null;
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public Iterable<Section> findAll() {
        Set<Section> sections = new HashSet<>();
        try (Connection connection = DriverManager.getConnection(url, userName, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM sections");
             ResultSet resultSet = statement.executeQuery();) {
            while (resultSet.next()) {
                int id1 = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int idChief = resultSet.getInt("idChief");
                int price = resultSet.getInt("price");
                int maxDuration = resultSet.getInt("maxDuration");
                Section section = new Section(id1, name,idChief,price,maxDuration);
                section.setId(id1);
                sections.add(section);

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sections;
    }

    @Override
    public Section save(Section entity) {

        String sql = "insert into sections(id, name, idChief, price, maxDuration) values(?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(url, userName, password);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, entity.getId());
            ps.setString(2, entity.getName());
            ps.setInt(3, entity.getIdSectionChief());
            ps.setInt(4, entity.getPricePerCheckup());
            ps.setInt(5, entity.getMaxDurationCheckup());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return entity;
    }
}
