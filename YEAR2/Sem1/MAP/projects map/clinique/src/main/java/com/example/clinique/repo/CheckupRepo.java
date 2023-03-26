package com.example.clinique.repo;

import com.example.clinique.domain.Checkup;
import com.example.clinique.domain.Doctor;
import com.example.clinique.domain.Section;
import com.example.clinique.repo.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class CheckupRepo implements Repository<Integer, Checkup> {

    private String url;
    private String userName;
    private String password;

    public CheckupRepo(String url, String userName, String password) {
        this.url = url;
        this.userName = userName;
        this.password = password;
    }


    @Override
    public Checkup findOne(Integer id) {
        try (Connection connection = DriverManager.getConnection(url, userName, password);
             PreparedStatement statement = connection.prepareStatement("select * from checkups where id=" + id);
             ResultSet resultSet = statement.executeQuery();
        ) {
            if (resultSet.next()) {
                int id1 = resultSet.getInt("id");
                int idDoctor = resultSet.getInt("idDoctor");
                int idPacient = resultSet.getInt("idPacient");
                String namePacient = resultSet.getString("namePacient");
                LocalDate date = resultSet.getDate("dateCheck").toLocalDate();
                int hour=resultSet.getInt("hourCheck");
                Checkup check =new Checkup(id1, idDoctor, idPacient, namePacient, date, hour);
                return check;
            } else {
                return null;
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public Iterable<Checkup> findAll() {
        Set<Checkup> checkups = new HashSet<>();
        try (Connection connection = DriverManager.getConnection(url, userName, password);
             PreparedStatement statement = connection.prepareStatement("select * from checkups");
             ResultSet resultSet = statement.executeQuery();) {
            while (resultSet.next()) {
                int id1 = resultSet.getInt("id");
                int idDoctor = resultSet.getInt("idDoctor");
                int idPacient = resultSet.getInt("idPacient");
                String namePacient = resultSet.getString("namePacient");
                LocalDate date = resultSet.getDate("dateCheck").toLocalDate();
                int hour=resultSet.getInt("hourCheck");
                Checkup check =new Checkup(id1, idDoctor, idPacient, namePacient, date, hour);
                checkups.add(check);

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return checkups;
    }

    @Override
    public Checkup save(Checkup entity) {

        String sql = "insert into checkups(id, idDoctor, idPacient, namePacient, dateCheck, hourCheck) values(?, ?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(url, userName, password);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, entity.getId());
            ps.setInt(2, entity.getIdDoctor());
            ps.setInt(3, entity.getIdPacient());
            ps.setString(4, entity.getNamePacient());
            ps.setDate(5, Date.valueOf((LocalDate) entity.getDate()));
            ps.setInt(6, entity.getHour());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return entity;
    }
}

