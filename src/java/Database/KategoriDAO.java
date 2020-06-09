package Database;

import Entity.Bisiklet;
import Entity.Kategori;
import Entity.Kullanici;
import Util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class KategoriDAO {

    private DBConnection db;
    private Connection c;
    private BisikletDAO bisikletDAO = new BisikletDAO();
    private MotorDAO motorDAO = new MotorDAO();
    private AtvDAO atvDAO = new AtvDAO();
    private ScooterDAO scooterDAO = new ScooterDAO();
    private GingerDAO gingerDAO = new GingerDAO();
    private HoverboardDAO hoverboardDAO = new HoverboardDAO();

    public Kategori find(Long kategori_id) {
        Kategori kategori = null;
        try {
            PreparedStatement pst = this.getC().prepareStatement("select * from kategori where kategori_id=?");
            pst.setLong(1, kategori_id);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                kategori = new Kategori();
                kategori.setKategori_id(rs.getLong("kategori_id"));
                kategori.setKategori_ad(rs.getString("kategori_ad"));
                //  kategori.setBisikletList(this.bisikletDAO.getBisikletListByKategori(rs.getLong("kategori_id")));
            }

            return kategori;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }

    }

    public List<Kategori> getBisikletListByKategori(Long bisiklet_id) {
        List<Kategori> liste = new ArrayList<>();

        Kategori tmp;

        try {
            PreparedStatement pst = this.getC().prepareStatement("select * from bisiklet_kategori where bisiklet_id=?");
            pst.setLong(1, bisiklet_id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                tmp = new Kategori();

                tmp = this.find(rs.getLong("kategori_id"));

                liste.add(tmp);
            }

        } catch (SQLException ex) {
            Logger.getLogger(BisikletDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return liste;
    }

    public List<Kategori> getMotorListByKategori(Long motor_id) {
        List<Kategori> liste = new ArrayList<>();

        Kategori tmp;

        try {
            PreparedStatement pst = this.getC().prepareStatement("select * from motor_kategori where motor_id=?");
            pst.setLong(1, motor_id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                tmp = new Kategori();

                tmp = this.find(rs.getLong("kategori_id"));

                liste.add(tmp);
            }

        } catch (SQLException ex) {
            Logger.getLogger(MotorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return liste;
    }

    public List<Kategori> getAtvListByKategori(Long atv_id) {
        List<Kategori> liste = new ArrayList<>();

        Kategori tmp;

        try {
            PreparedStatement pst = this.getC().prepareStatement("select * from atv_kategori where atv_id=?");
            pst.setLong(1, atv_id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                tmp = new Kategori();

                tmp = this.find(rs.getLong("kategori_id"));

                liste.add(tmp);
            }

        } catch (SQLException ex) {
            Logger.getLogger(AtvDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return liste;
    }
    
    public List<Kategori> getScooterListByKategori(Long scooter_id) {
        List<Kategori> liste = new ArrayList<>();

        Kategori tmp;

        try {
            PreparedStatement pst = this.getC().prepareStatement("select * from scooter_kategori where scooter_id=?");
            pst.setLong(1, scooter_id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                tmp = new Kategori();

                tmp = this.find(rs.getLong("kategori_id"));

                liste.add(tmp);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ScooterDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return liste;
    }
    
    public List<Kategori> getGingerListByKategori(Long ginger_id) {
        List<Kategori> liste = new ArrayList<>();

        Kategori tmp;

        try {
            PreparedStatement pst = this.getC().prepareStatement("select * from ginger_kategori where ginger_id=?");
            pst.setLong(1, ginger_id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                tmp = new Kategori();

                tmp = this.find(rs.getLong("kategori_id"));

                liste.add(tmp);
            }

        } catch (SQLException ex) {
            Logger.getLogger(GingerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return liste;
    }
    
    public List<Kategori> getHoverboardListByKategori(Long hoverboard_id) {
        List<Kategori> liste = new ArrayList<>();

        Kategori tmp;

        try {
            PreparedStatement pst = this.getC().prepareStatement("select * from hoverboard_kategori where hoverboard_id=?");
            pst.setLong(1, hoverboard_id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                tmp = new Kategori();

                tmp = this.find(rs.getLong("kategori_id"));

                liste.add(tmp);
            }

        } catch (SQLException ex) {
            Logger.getLogger(HoverboardDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return liste;
    }

    public List<Kategori> tumKategoriler() {
        List<Kategori> myList = new ArrayList<>();
        Kategori cate = null;
        try {

            PreparedStatement pst = this.getC().prepareStatement("select * from kategori");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                cate = new Kategori();
                cate.setKategori_id(rs.getLong("kategori_id"));
                cate.setKategori_ad(rs.getString("kategori_ad"));
                cate.setBisikletList(this.getBisikletDAO().getBisikletListByKategori(cate.getKategori_id()));
                cate.setMotorList(this.getMotorDAO().getMotorListByKategori(cate.getKategori_id()));
                cate.setAtvList(this.getAtvDAO().getAtvListByKategori(cate.getKategori_id()));
                cate.setScooterList(this.getScooterDAO().getScooterListByKategori(cate.getKategori_id()));
                cate.setHoverboardList(this.getHoverboardDAO().getHoverboardListByKategori(cate.getKategori_id()));
                myList.add(cate);
            }
            pst.close();
            rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(KategoriDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return myList;
    }

    public DBConnection getDb() {
        if (this.db == null) {
            this.db = new DBConnection();
        }
        return db;
    }

    public void setDb(DBConnection db) {
        this.db = db;
    }

    public BisikletDAO getBisikletDAO() {
        return bisikletDAO;
    }

    public void setBisikletDAO(BisikletDAO bisikletDAO) {
        this.bisikletDAO = bisikletDAO;
    }

    public MotorDAO getMotorDAO() {
        return motorDAO;
    }

    public void setMotorDAO(MotorDAO motorDAO) {
        this.motorDAO = motorDAO;
    }

    public AtvDAO getAtvDAO() {
        return atvDAO;
    }

    public void setAtvDAO(AtvDAO atvDAO) {
        this.atvDAO = atvDAO;
    }

    public ScooterDAO getScooterDAO() {
        return scooterDAO;
    }

    public void setScooterDAO(ScooterDAO scooterDAO) {
        this.scooterDAO = scooterDAO;
    }

    public GingerDAO getGingerDAO() {
        return gingerDAO;
    }

    public void setGingerDAO(GingerDAO gingerDAO) {
        this.gingerDAO = gingerDAO;
    }

    public HoverboardDAO getHoverboardDAO() {
        return hoverboardDAO;
    }

    public void setHoverboardDAO(HoverboardDAO hoverboardDAO) {
        this.hoverboardDAO = hoverboardDAO;
    }
    

    public Connection getC() throws SQLException {
        if (this.c == null) {
            this.c = this.getDb().connect();
        }
        return c;
    }

    public void setC(Connection c) {
        this.c = c;
    }

}
