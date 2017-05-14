package com.tyskie.DAOs;

import com.tyskie.DBConnection.DatabaseConnection;
import com.tyskie.Domain.Person;

import javax.enterprise.context.Dependent;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Dependent
public class PersonDAOImpl implements PersonDAO {
    private DatabaseConnection connection = new DatabaseConnection();
    private Connection conn = connection.getConnection();

    private PreparedStatement preparedStatement;
    private Statement stmt;
    private String query;
    private ResultSet rs;

    @Override
    public List<Person> findAll() {
        List<Person> personList = new ArrayList<Person>();
        try{
            stmt = conn.createStatement();
            query = "SELECT * FROM Person";
            rs = stmt.executeQuery(query);
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                personList.add(new Person(id, name, age));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personList;
    }

    @Override
    public List<Person> findPersonById(int id) {
        List<Person> personList = new ArrayList<Person>();
        try{
            query = "SELECT * FROM Person WHERE id = ?";
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();
            while(rs.next()){
                String name = rs.getString("name");
                int age = rs.getInt("age");
                personList.add(new Person(id, name, age));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personList;
    }

    @Override
    public void createPerson(int id, String name, int age) {
        try{
            query = "INSERT INTO Person VALUES (?, ?, ?)";
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setInt(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean findPerson(int id, String name, int age) {
        boolean exists = false;
        try{
            query = "SELECT * FROM Person WHERE id = ? AND name = ? AND age = ?";
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setInt(3, age);
            exists = preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exists;
    }
}
