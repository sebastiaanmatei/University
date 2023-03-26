package com.example.examenmap.repo;

import com.example.examenmap.domain.Persoana;
import com.example.examenmap.domain.TipuriOrase;

import java.sql.*;
import java.util.*;

public class PersoaneRepository implements Repository<Integer, Persoana> {
    private String url;
    private String username;
    private String password;

    public PersoaneRepository(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    /*@Override
    public Persoana save(Persoana entity)
    {
        String sql = "INSERT INTO persoane(id, nume,prenume,username, parola,oras , strada, nr_strada, telefon) VALUES (?, ?, ?,?,?,?,?,?,?)";
        try(Connection connection = DriverManager.getConnection(this.url, this.username, this.password);
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, entity.getId());
            statement.setString(2, entity.getNume());
            statement.setString(3, entity.getPrenume());
            statement.setString(4, entity.getUsername());
            statement.setString(5, entity.getParola());
            statement.setString(6, String.valueOf(entity.getOras()));
            statement.setString(7, entity.getStrada());
            statement.setString(8, entity.getNr_strada());
            statement.setString(9, entity.getTelefon());
            //System.out.printf(statement.toString());

            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return entity;
    }*/

    @Override
    public Persoana update(Persoana entity) {
        return null;
    }

    @Override
    public Iterable<Persoana> findAll() {
        Set<Persoana> sections = new HashSet<>();
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM persoane");
             ResultSet resultSet = statement.executeQuery();) {
            while (resultSet.next()) {
                Integer id1 = resultSet.getInt("id");
                String nume= resultSet.getString("nume");
                String prenume= resultSet.getString("prenume");
                String user= resultSet.getString("nume");
                String parola= resultSet.getString("nume");
                String oras=resultSet.getString("oras");
                String strada= resultSet.getString("strada");
                String nr_strada= resultSet.getString("nr_strada");
                String telefon= resultSet.getString("telefon");
                Persoana section = new Persoana(id1, nume, prenume, user, parola, oras, strada, nr_strada, telefon);
                section.setId(id1);
                sections.add(section);

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sections;
    }


    @Override
    public Persoana save(Persoana entity) {
        String sql = "insert into persoane(id, nume, prenume, username, parola, oras, strada, nr_strada, telefon) values(?, ?, ?, ?, ?,?,?,?,?)";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, entity.getId());
            ps.setString(2, entity.getNume());
            ps.setString(3, entity.getPrenume());
            ps.setString(4, entity.getUsername());
            ps.setString(5, entity.getParola());
            //ps.setString(6, entity.getNume());
            ps.setString(7, entity.getStrada());
            ps.setString(8, entity.getNr_strada());
            ps.setString(9, entity.getTelefon());


            TipuriOrase t=null;
            if(entity.getOras().equals(t.Cluj)) {
                ps.setString(6, "Cluj");
            }
            if(entity.getOras().equals(t.Sibiu)) {
                ps.setString(6, "Sibiu");
            }
            if(entity.getOras().equals(t.Brasov)) {
                ps.setString(6, "Brasov");
            }

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return entity;

    }

    /*private Persoana getPersoanaFromResult(ResultSet resultSet) throws SQLException {
        Integer id = resultSet.getInt("id");
        String nume = resultSet.getString("nume");
        String prenume = resultSet.getString("prenume");
        String username = resultSet.getString("username");
        String parola = resultSet.getString("parola");
        String ors=resultSet.getString("oras");
        TipuriOrase oras=null;
        if(ors.equals("Cluj")){
            oras
        }
        TipuriOrase oras = TipuriOrase.valueOf(resultSet.getString("oras"));
        String strda = resultSet.getString("strada");
        String nr_strda = resultSet.getString("nr_strada");
        String telefon = resultSet.getString("telefon");
        return new Persoana(id,nume,prenume ,username,parola,oras,strda,nr_strda,telefon);
    }*/

}
