package edu.fachriansyahmni.latihanmvcjdbc.main;

import edu.fachriansyahmni.latihanmvcjdb.database.KingBarbershopDatabase;
import edu.fachriansyahmni.latihanmvcjdb.entity.Pelanggan;
import edu.fachriansyahmni.latihanmvcjdb.error.PelangganException;
import edu.fachriansyahmni.latihanmvcjdb.service.PelangganDao;
import edu.fachriansyahmni.latihanmvcjdb.view.MainViewPelanggan;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
/*
 * @author
 * NAMA                 : Fachriansyah Muhammad Nur Ihsan
 * KELAS                : PBOIF2
 * NIM                  : 10119065
 * Deskripsi Program    : Implementasi JDBC-DAO-MVC
 *
 */
public class PBOIF2PERT1310119065FACHRIANSYAHMUHNURI {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, PelangganException{
        // TODO code application logic here
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    MainViewPelanggan pelanggan = new MainViewPelanggan();
                    pelanggan.loadDatabase();
                    pelanggan.setVisible(true);
                } catch (SQLException e) {
                } catch (PelangganException ex) {
                    Logger.getLogger(PBOIF2PERT1310119065FACHRIANSYAHMUHNURI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    
}
