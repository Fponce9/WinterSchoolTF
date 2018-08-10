/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.newpi.trabajofinal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Proyecto
 */
public class Conexion {
      Connection conn;
    Statement stmt;

    public Conexion() throws ClassNotFoundException, SQLException {
        String urlDatabase = "jdbc:mysql://localhost/hopital";
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection(urlDatabase, "root", "");
        stmt = conn.createStatement();
    }

    void exec(String string) throws SQLException {
        stmt.execute(string);
    }
    
    ArrayList<Enfermera> getAllNurses() throws SQLException {
        String query = "SELECT infirmier.numero,nom,prenom,adresse,tel,code_service,rotation,salaire from infirmier "
                + "inner join employe on employe.numero=infirmier.numero;";
        ResultSet rs = stmt.executeQuery(query);
        ArrayList<Enfermera> resultado = new ArrayList<>();
        while(rs.next()) {
            resultado.add(new Enfermera(
                    "" 
                    + rs.getInt("numero"),
                    rs.getString("nom") ,
                    rs.getString("prenom"),
                    rs.getString("adresse"),
                    rs.getString("tel"),
                    rs.getString("code_service"),
                    rs.getString("rotation"),
                    rs.getString("salaire")
            ));
        }
        return resultado;
    }
    
    ArrayList<Paciente> getAllPatients() throws SQLException {
        String query = "SELECT DISTINCT numero, nom, prenom, code_service, no_chambre, lit from malade INNER JOIN hospitalisation ON malade.numero=hospitalisation.no_malade";
        ResultSet rs = stmt.executeQuery(query);
        ArrayList<Paciente> resultado = new ArrayList<>();
        while(rs.next()) {
            resultado.add(new Paciente(
                    "" + rs.getInt("numero"),
                    rs.getString("nom"),
                    rs.getString("prenom"),
                    rs.getString("code_service"),
                    rs.getString("no_chambre"),
                    rs.getString("lit")
            ));
        }
        return resultado;
    }
    
    public void deletePatient(String numero) throws SQLException {
        String query1 = String.format("DELETE FROM malade WHERE numero='%s'", numero);
        String query2 = String.format("DELETE FROM hospitalisation WHERE no_malade='%s'", numero);
        exec(query1);
        exec(query2);
    }
    
    public void deleteNurse(String Cod)throws SQLException{
        String query1= String.format("DELETE FROM infirmier WHERE numero='%s'",Cod);
        String query2 = String.format("DELETE FROM employe WHERE numero='%s'",Cod);
        exec(query1);
        exec(query2);
    }
    
    
    public int patientCount() throws SQLException {
        String query = "SELECT COUNT(*) AS number FROM malade";
        ResultSet rs = stmt.executeQuery(query);
        int patientCount = 0;
        while(rs.next()) {
            patientCount = rs.getInt("number");
        }
        return patientCount;
    }
    
    public int doctorCount() throws SQLException {
        String query = "SELECT COUNT(*) AS number FROM docteur";
        ResultSet rs = stmt.executeQuery(query);
        int doctorCount = 0;
        while(rs.next()) {
            doctorCount = rs.getInt("number");
        }
        return doctorCount;
    }
    
    public double nurseSalaireSum() throws SQLException {
        String query = "SELECT SUM(salaire) AS number FROM infirmier";
        ResultSet rs = stmt.executeQuery(query);
        double salaireSum = 0;
        while(rs.next()) {
            salaireSum = rs.getDouble("number");
        }
        return salaireSum;
    }
    
    public List<Object[]> patientReport1() throws SQLException {
        List<Object[]> result = new ArrayList<>();
        String query = "SELECT COUNT(*) AS number, code_service, service.nom AS nameservice  FROM malade INNER JOIN hospitalisation ON malade.numero=hospitalisation.no_malade INNER JOIN service ON hospitalisation.code_service=service.code GROUP BY code_service";
        ResultSet rs = stmt.executeQuery(query);
        while(rs.next()) {
            Object[] row = new Object[2];
            row[0] = rs.getInt("number");
            row[1] = rs.getString("nameservice");
            result.add(row);
        }
        return result;
    }
    
    public List<Object[]> doctorReport1() throws SQLException {
        List<Object[]> result = new ArrayList<>();
        String query = "SELECT COUNT(*) AS number, specialite FROM docteur GROUP BY specialite ORDER BY number DESC";
        ResultSet rs = stmt.executeQuery(query);
        while(rs.next()) {
            Object[] row = new Object[2];
            row[0] = rs.getInt("number");
            row[1] = rs.getString("specialite");
            result.add(row);
        }
        return result;
    }
    
    public List<Object[]> nurseReport1() throws SQLException {
        List<Object[]> result = new ArrayList<>();
        String query = "SELECT COUNT(*) AS number, rotation FROM infirmier GROUP BY rotation";
        ResultSet rs = stmt.executeQuery(query);
        while(rs.next()) {
            Object[] row = new Object[2];
            row[0] = rs.getInt("number");
            row[1] = rs.getString("rotation");
            result.add(row);
        }
        return result;
    }
    
    public List<Object[]> nurseReport2() throws SQLException {
        List<Object[]> result = new ArrayList<>();
        String query = "SELECT COUNT(*) AS number, service.nom, infirmier.rotation FROM infirmier INNER JOIN service ON service.code=infirmier.code_service GROUP BY service.code, infirmier.rotation";
        ResultSet rs = stmt.executeQuery(query);
        while(rs.next()) {
            Object[] row = new Object[3];
            row[0] = rs.getInt("number");
            row[1] = rs.getString("service.nom");
            row[2] = rs.getString("infirmier.rotation");
            result.add(row);
        }
        return result;
    }
}
