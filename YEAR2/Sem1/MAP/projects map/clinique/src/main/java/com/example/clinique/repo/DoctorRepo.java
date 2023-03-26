package com.example.clinique.repo;

import com.example.clinique.domain.Doctor;
import com.example.clinique.domain.Section;
import com.example.clinique.repo.Repository;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class DoctorRepo implements Repository<Integer, Doctor> {

    private String url;
    private String userName;
    private String password;

    public DoctorRepo(String url, String userName, String password) {
        this.url = url;
        this.userName = userName;
        this.password = password;
    }


    @Override
    public Doctor findOne(Integer id) {
        try (Connection connection = DriverManager.getConnection(url, userName, password);
             PreparedStatement statement = connection.prepareStatement("select * from doctors where id=" + id);
             ResultSet resultSet = statement.executeQuery();
        ) {
            if (resultSet.next()) {
                int id1 = resultSet.getInt("id");
                int idSection = resultSet.getInt("idSection");
                String name = resultSet.getString("name");
                int seniority = resultSet.getInt("seniority");
                String residence = resultSet.getString("residence");
                Doctor doctor =new Doctor(id1, idSection, name, seniority, residence);
                return doctor;
            } else {
                return null;
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public Iterable<Doctor> findAll() {
        Set<Doctor> doctors = new HashSet<>();
        try (Connection connection = DriverManager.getConnection(url, userName, password);
             PreparedStatement statement = connection.prepareStatement("select * from doctors");
             ResultSet resultSet = statement.executeQuery();) {
            while (resultSet.next()) {
                int id1 = resultSet.getInt("id");
                int idSection = resultSet.getInt("idSection");
                String name = resultSet.getString("name");
                int seniority = resultSet.getInt("seniority");
                String residence = resultSet.getString("residence");
                Doctor doctor =new Doctor(id1, idSection, name, seniority, residence);
                doctors.add(doctor);

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doctors;
    }

    @Override
    public Doctor save(Doctor entity) {

        String sql = "insert into doctors(id, idSection, name, seniority, residence) values(?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(url, userName, password);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, entity.getId());
            ps.setInt(2, entity.getIdSection());
            ps.setString(3, entity.getName());
            ps.setInt(4, entity.getSeniority());
            ps.setString(5, entity.getResidence());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return entity;
    }
}
