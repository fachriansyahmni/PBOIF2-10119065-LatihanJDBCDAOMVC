package edu.fachriansyahmni.latihanmvcjdb.database;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import edu.fachriansyahmni.latihanmvcjdb.impl.PelangganDaoImpl;
import edu.fachriansyahmni.latihanmvcjdb.service.PelangganDao;
import java.sql.*;

/*
 * @author
 * NAMA                 : Fachriansyah Muhammad Nur Ihsan
 * KELAS                : PBOIF2
 * NIM                  : 10119065
 * Deskripsi Program    : Implementasi JDBC-DAO-MVC
 *
 */
public class KingBarbershopDatabase {
   private static Connection connection;
   private static PelangganDao pelangganDao;
   
   public static Connection getConnection() throws SQLException{
       if(connection == null){
           MysqlDataSource dataSource = new MysqlDataSource();
           dataSource.setURL("jdbc:mysql://localhost:3306/kingsbarbershop");
           dataSource.setUser("root");
           dataSource.setPassword("");
           connection = dataSource.getConnection();
       }
       return connection;
   }
   
   public static PelangganDao getPelangganDao() throws SQLException{
       
       if(pelangganDao == null){
           pelangganDao = new PelangganDaoImpl(getConnection());
       }
       return pelangganDao;
   }
}
