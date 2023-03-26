package com.example.practice2.repo;

import com.example.practice2.domain.Client;
import com.example.practice2.domain.Hobby;
import com.example.practice2.domain.SpecialOffer;
import com.example.practice2.domain.Type;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class ClientRepo implements Repository<Long, Client>{

    private String url;
    private String userName;
    private String password;

    public ClientRepo(String url, String userName, String password) {
        this.url = url;
        this.userName = userName;
        this.password = password;
    }


    @Override
    public Iterable<Client> findAll() {
        Set<Client> sections = new HashSet<>();
        try (Connection connection = DriverManager.getConnection(url, userName, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM clients");
             ResultSet resultSet = statement.executeQuery();) {
            while (resultSet.next()) {
                Long id1 = resultSet.getLong("clientid");
                String name= resultSet.getString("name");
                Integer fid=resultSet.getInt("fidelity");
                Integer age=resultSet.getInt("age");
                String hobby=resultSet.getString("hobby");
                Client section = new Client(id1, name, fid, age, hobby);
                section.setId(id1);
                sections.add(section);

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sections;
    }


    @Override
    public Client save(Client entity) {
        String sql = "insert into offers(clientid, name, fidelity, age, hobby) values(?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(url, userName, password);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, entity.getIdClient());
            ps.setString(2, entity.getName());
            ps.setInt(3, entity.getFidelityGrade());
            ps.setInt(4, entity.getAge());

            Hobby t=null;
            if(entity.getHobby().equals(t.reading)) {
                ps.setString(5, "reading");
            }
            if(entity.getHobby().equals(t.music)) {
                ps.setString(5, "reading");
            }
            if(entity.getHobby().equals(t.hiking)) {
                ps.setString(5, "reading");
            }
            if(entity.getHobby().equals(t.walking)) {
                ps.setString(5, "reading");
            }
            if(entity.getHobby().equals(t.extreme_sports)) {
                ps.setString(5, "extreme sports");
            }
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return entity;

    }
}
