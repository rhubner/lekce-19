package cz.robotdreams.java.lekce18.databaze;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SeznamStudentu {


    private final String dbUrl;
    private final String dbUser;
    private final String dbPass;

    public SeznamStudentu(String dbUrl, String dbUser, String dbPass) {
        this.dbUrl = dbUrl;
        this.dbUser = dbUser;
        this.dbPass = dbPass;
    }

    public List<Student> getStudents() {
        try(Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
            PreparedStatement ps = con.prepareStatement("SELECT first_name, last_name, email FROM seznam_studentu");
            ResultSet rs = ps.executeQuery();
        ) {
            List<Student> students = new ArrayList<>();
            while (rs.next()) {
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String email = rs.getString("email");
                students.add(new Student(firstName, lastName, email));
            }
            return students;
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }





}
