/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import java.util.List;
import java.util.ArrayList;
import DAOdatabuku.databukuDAO;
import DAOImplement.databukuimplement;
import model.*;
import view.MainView;
/**
 *
 * @author tadeusvitoo
 */
public class databukucontroller {
    MainView frame;
    databukuimplement impldatabuku;
    List<databuku> db;
    
    public databukucontroller(MainView frame){
        this.frame = frame;
        impldatabuku = new databukuDAO();
        db = impldatabuku.getAll();
    }
    public void isitabel(){
        db = impldatabuku.getAll();
        modeltabeldatabuku mp = new modeltabeldatabuku(db);
        frame.getTabelDatabuku().setModel(mp);
    }
    
    public void insert(){
        databuku db = new databuku();
        db.setJudul(frame.getJTxtjudul().getText());
        db.setGenre(frame.getJtxtgenre().getText());
        db.setPenulis(frame.getJtxtpenulis().getText());
        db.setPenerbit(frame.getJtxtpenerbit().getText());
        db.setLokasi(frame.getJtxtlokasi().getText());
        db.setStock(frame.getJtxtstock().getText());
        impldatabuku.insert(db);
        
    }
    
    public void update(){
        databuku db = new databuku();
        db.setJudul(frame.getJTxtjudul().getText());
        db.setGenre(frame.getJtxtgenre().getText());
        db.setPenulis(frame.getJtxtpenulis().getText());
        db.setPenerbit(frame.getJtxtpenerbit().getText());
        db.setLokasi(frame.getJtxtlokasi().getText());
        db.setStock(frame.getJtxtstock().getText());
        db.setId(Integer.parseInt(frame.getJTxtid().getText()));
        impldatabuku.update(db);
    }
    
    public void delete(){
        int id = Integer.parseInt(frame.getJTxtid().getText());
        impldatabuku.delete(id);
    }
    
    public void cari(String keyword, String category) {
    List<databuku> datasearch = new ArrayList<>();
    List<databuku> allData = impldatabuku.getAll();

    for (databuku buku : allData) {
        if (category.equalsIgnoreCase("Judul")) {
            if (buku.getJudul().toLowerCase().contains(keyword.toLowerCase())) {
                datasearch.add(buku);
            }
        } else if (category.equalsIgnoreCase("Genre")) {
            if (buku.getGenre().toLowerCase().contains(keyword.toLowerCase())) {
                datasearch.add(buku);
            }
        } else if (category.equalsIgnoreCase("Penulis")) {
            if (buku.getPenulis().toLowerCase().contains(keyword.toLowerCase())) {
                datasearch.add(buku);
            }
        } else if (category.equalsIgnoreCase("Penerbit")) {
            if (buku.getPenerbit().toLowerCase().contains(keyword.toLowerCase())) {
                datasearch.add(buku);
            }
        }
    }
    modeltabeldatabuku model = new modeltabeldatabuku(datasearch);
    frame.getTabelDatabuku().setModel(model);
}

}
