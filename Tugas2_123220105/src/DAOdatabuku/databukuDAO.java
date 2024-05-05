/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOdatabuku;
import java.sql.*;
import java.util.*;
import koneksi.connector;
import model.*;
import DAOImplement.databukuimplement;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author tadeusvitoo
 */
public class databukuDAO implements databukuimplement{
    Connection connection;
    
    final String select = "select * from dataperpus;";
    final String insert = "INSERT INTO dataperpus (judul, genre, penulis, penerbit, lokasi, stock) VALUES (?, ?, ?, ?, ?, ?);";
    final String update = "update dataperpus set judul=?, genre=?, penulis=?, penerbit=?, lokasi=?, stock=? where id=?";
    final String delete = "delete from dataperpus where id=?";

    public databukuDAO(){
        connection = connector.connection();
    }
    
    @Override
    public void insert(databuku b) {
       PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, b.getJudul());
            statement.setString(2, b.getGenre());
            statement.setString(3, b.getPenulis());
            statement.setString(4, b.getPenerbit());
            statement.setString(5, b.getLokasi());
            statement.setString(6, b.getStock());
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            while(rs.next()){
                b.setId(rs.getInt(1));
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            try{
                statement.close();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void update(databuku b) {
        PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement(update);
            statement.setString(1, b.getJudul());
            statement.setString(2, b.getGenre());
            statement.setString(3, b.getPenulis());
            statement.setString(4, b.getPenerbit());
            statement.setString(5, b.getLokasi());
            statement.setString(6, b.getStock());
            statement.setInt(7, b.getId());
            statement.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            try{
                statement.close();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void delete(int id) {
        PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement(delete);
            
            statement.setInt(1, id);
            statement.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            try{
                statement.close();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
    }

    @Override
    public List<databuku> getAll() {
        List<databuku> db = null;
        try{
            db = new ArrayList<databuku>();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(select);
            while(rs.next()){
                databuku bu = new databuku();
                bu.setId(rs.getInt("id"));
                bu.setJudul(rs.getString("judul"));
                bu.setGenre(rs.getString("genre"));
                bu.setPenulis(rs.getString("penulis"));
                bu.setPenerbit(rs.getString("penerbit"));
                bu.setLokasi(rs.getString("lokasi"));
                bu.setStock(rs.getString("stock"));
                db.add(bu);
                
            }
        }catch(SQLException ex){
            Logger.getLogger(databukuDAO.class.getName()).log(Level.SEVERE, null,ex);
        }
        
        return db;
    }
}
