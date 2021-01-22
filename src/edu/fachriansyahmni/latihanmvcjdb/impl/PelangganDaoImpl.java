/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fachriansyahmni.latihanmvcjdb.impl;

import edu.fachriansyahmni.latihanmvcjdb.entity.Pelanggan;
import edu.fachriansyahmni.latihanmvcjdb.error.PelangganException;
import edu.fachriansyahmni.latihanmvcjdb.service.PelangganDao;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author Fachriansyah PC
 */
public class PelangganDaoImpl implements PelangganDao{

    private Connection connection;

    private final String insertPelanggan = "INSERT INTO pelanggan (nama,alamat,telepon,email) VALUES (?,?,?,?)";
    private final String updatePelanggan = "UPDATE pelanggan SET nama=?, alamat=?, telepon=?, email=? WHERE id=?";
    private final String deletePelanggan = "DELETE FROM pelanggan WHERE id=?";
    private final String getById = "SELECT * FROM pelanggan WHERE id=?";
    private final String getByEmail = "SELECT * FROM pelanggan WHERE email=?";
    private final String selectAll = "SELECT * FROM pelanggan";
    
    
    public PelangganDaoImpl(Connection connection) {
        this.connection = connection;
    }
    
    @Override
    public void insertPelanggan(Pelanggan pelanggan) throws PelangganException {
        PreparedStatement statement = null;
        try
        {
            connection.setAutoCommit(false);
            
            statement = connection.prepareStatement(insertPelanggan, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, pelanggan.getNama());
            statement.setString(2, pelanggan.getAlamat());
            statement.setString(3, pelanggan.getTelepon());
            statement.setString(4, pelanggan.getEmail());
            statement.executeUpdate();
            
            ResultSet result = statement.getGeneratedKeys();
            if(result.next()){
                pelanggan.setId(result.getInt(1));
            }
            
            connection.commit();
        }catch (SQLException e){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                
            }
            throw new PelangganException(e.getMessage());
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
            }
            if(statement != null){
                try{
                    statement.close();
                }catch(SQLException e){

                }
            }
            
        }
    }

    @Override
    public void updatePelanggan(Pelanggan pelanggan) throws PelangganException {
        PreparedStatement statement = null;
        try
        {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(updatePelanggan);
            statement.setString(1, pelanggan.getNama());
            statement.setString(2, pelanggan.getAlamat());
            statement.setString(3, pelanggan.getTelepon());
            statement.setString(4, pelanggan.getEmail());
            statement.setInt(5,pelanggan.getId());
            statement.executeUpdate();
            
            connection.commit();
        }catch (SQLException e){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                
            }
            throw new PelangganException(e.getMessage());
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
            }
            if(statement != null){
                try{
                    statement.close();
                }catch(SQLException e){

                }
            }
            
        }
    }

    @Override
    
    public void deletePelanggan(Integer id) throws PelangganException {
        PreparedStatement statement = null;
        try
        {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(deletePelanggan);
            statement.setInt(1, id);
            statement.executeUpdate();
            
            connection.commit();
        }catch (SQLException e){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                
            }
            throw new PelangganException(e.getMessage());
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
            }
            if(statement != null){
                try{
                    statement.close();
                }catch(SQLException e){

                }
            }
            
        }
    }

    @Override
    public Pelanggan getPelanggan(Integer id) throws PelangganException {
        PreparedStatement statement = null;
        try
        {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(getById);
            statement.setInt(1,id);
            
            ResultSet result = statement.executeQuery();
            Pelanggan pelanggan = null;
            
            if(result.next()){
                pelanggan = new Pelanggan();
                pelanggan.setId(result.getInt("id"));
                pelanggan.setNama(result.getString("nama"));
                pelanggan.setAlamat(result.getString("alamat"));
                pelanggan.setTelepon(result.getString("telepon"));
                pelanggan.setEmail(result.getString("email"));
            }else{
                throw new PelangganException("Pelanggan dengan id +"+id+" tidak ditemukan");
            }
            connection.commit();
            return pelanggan;
        }catch (SQLException e){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                
            }
            throw new PelangganException(e.getMessage());
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
            }
            if(statement != null){
                try{
                    statement.close();
                }catch(SQLException e){

                }
            }
            
        }
    }

    @Override
    public Pelanggan getPelanggan(String email) throws PelangganException {
        PreparedStatement statement = null;
        try
        {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(getByEmail);
            statement.setString(1,email);
            
            ResultSet result = statement.executeQuery();
            Pelanggan pelanggan = null;
            
            if(result.next()){
                pelanggan = new Pelanggan();
                pelanggan.setId(result.getInt("id"));
                pelanggan.setNama(result.getString("nama"));
                pelanggan.setAlamat(result.getString("alamat"));
                pelanggan.setTelepon(result.getString("telepon"));
                pelanggan.setEmail(result.getString("email"));
            }else{
                throw new PelangganException("Pelanggan dengan email "+email+" tidak ditemukan");
            }
            connection.commit();
            return pelanggan;
        }catch (SQLException e){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                
            }
            throw new PelangganException(e.getMessage());
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
            }
            if(statement != null){
                try{
                    statement.close();
                }catch(SQLException e){

                }
            }
            
        }
    }

    @Override
    public List<Pelanggan> SelectAllPelanggan() throws PelangganException {
        Statement statement = null;
        
        List<Pelanggan> list = new ArrayList<Pelanggan>();
        
        try
        {
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            
            ResultSet result = statement.executeQuery(selectAll);
            Pelanggan pelanggan = null;
            
            while(result.next()){
                pelanggan = new Pelanggan();
                pelanggan.setId(result.getInt("id"));
                pelanggan.setNama(result.getString("nama"));
                pelanggan.setAlamat(result.getString("alamat"));
                pelanggan.setTelepon(result.getString("telepon"));
                pelanggan.setEmail(result.getString("email"));
                list.add(pelanggan);
            }
            connection.commit();
            return list;
        }catch (SQLException e){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                
            }
            throw new PelangganException(e.getMessage());
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
            }
            if(statement != null){
                try{
                    statement.close();
                }catch(SQLException e){

                }
            }
            
        }
    }
    
}
