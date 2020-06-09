package Database;

import Entity.Bisiklet;
import Entity.Motor;
import Entity.Scooter;
import Entity.Atv;
import Entity.Ginger;
import Entity.Hoverboard;
import Entity.Kullanici;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import Util.DBConnection;

public class KullaniciDAO {

    private Kullanici kullanici;
    private DBConnection db;
    private Connection c;
    private BisikletDAO bisikletDAO;
    private MotorDAO motorDAO;
    private AtvDAO atvDAO;
    private ScooterDAO scooterDAO;
    private GingerDAO gingerDAO;
    private HoverboardDAO hoverboardDAO;

    public Kullanici login(Kullanici kullanici) {
        Kullanici tmp = new Kullanici();
        try {
            PreparedStatement pst = this.getC().prepareStatement("select * from kullanici where name=? and pass=?");
            pst.setString(1, kullanici.getName());
            pst.setString(2, kullanici.getPass());
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                tmp.setKullanici_id(rs.getLong("kullanici_id")); // Burada bunu çekmezsem kiralama yapamam!!
                tmp.setName(rs.getString("name"));
                tmp.setPass(rs.getString("pass"));
                tmp.setRole(rs.getInt("role"));
                return tmp;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(KullaniciDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    public Kullanici find(Long kullanici_id) {
        Kullanici kullanici;
        try {
            PreparedStatement pst = this.getC().prepareStatement("select * from kullanici where kullanici_id=?");
            pst.setLong(1, kullanici_id);
            ResultSet rs = pst.executeQuery();
            kullanici = new Kullanici();
            rs.next();
            kullanici.setKullanici_id(rs.getLong("kullanici_id"));
            kullanici.setName(rs.getString("name"));
            kullanici.setPass(rs.getString("pass"));
            kullanici.setRole(rs.getInt("role"));
            kullanici.setGender(rs.getBoolean("gender"));
            kullanici.setSurname(rs.getString("surname"));

            return kullanici;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }

    }

    public void register(Kullanici kullanici) {

        try {
            PreparedStatement pst = this.getC().prepareStatement("insert into kullanici (pass,name,surname,gender,role) values(?,?,?,?,?)");
            pst.setString(1, kullanici.getPass());
            pst.setString(2, kullanici.getName());
            pst.setString(3, kullanici.getSurname());
            pst.setBoolean(4, kullanici.isGender());
            pst.setInt(5, 0);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(KullaniciDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public List<Kullanici> getKullaniciList() {
        List<Kullanici> liste = new ArrayList<>();
        Kullanici tmp;

        try {
            PreparedStatement pst = this.getC().prepareStatement("select * from kullanici");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                tmp = new Kullanici();
                tmp.setKullanici_id(rs.getLong("kullanici_id"));
                tmp.setName(rs.getString("name"));
                tmp.setGender(rs.getBoolean("gender"));
                tmp.setRole(rs.getInt("role"));
                tmp.setPass(rs.getString("pass"));
                tmp.setSurname(rs.getString("surname"));
                tmp.setBisikletler(this.getBisikletDAO().getList(rs.getLong("kullanici_id")));
                tmp.setMotorlar(this.getMotorDAO().getList(rs.getLong("kullanici_id")));
                tmp.setAtvler(this.getAtvDAO().getList(rs.getLong("kullanici_id")));
                tmp.setGingerlar(this.getGingerDAO().getList(rs.getLong("kullanici_id")));
                tmp.setHoverboardlar(this.getHoverboardDAO().getList(rs.getLong("kullanici_id")));
                tmp.setScooterlar(this.getScooterDAO().getList(rs.getLong("kullanici_id")));
                liste.add(tmp);
            }
            pst.close();
            rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(KullaniciDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return liste;
    }

    public List<Kullanici> search(String aranan) {
        List<Kullanici> flist = new ArrayList();
       
        Kullanici tmp = null;
        try {

            PreparedStatement pst = this.getC().prepareStatement("select * from kullanici where name like ? order by kullanici_id " );
            pst.setString(1, "%" + aranan + "%"); 

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                tmp = new Kullanici();
                tmp.setKullanici_id(rs.getLong("kullanici_id"));
                tmp.setName(rs.getString("name"));
                tmp.setPass(rs.getString("pass"));
                tmp.setRole(rs.getInt("role"));
                tmp.setGender(rs.getBoolean("gender"));
                tmp.setSurname(rs.getString("surname"));

                flist.add(tmp);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return flist;
    }

    public void create(Kullanici kullanici) { // 3TABLOYUDA UNUTMADAN AYNI ANDA İLERLEMELİ GÜNCELLEME YADA CREATE..
        try {
            Statement st = this.getC().createStatement();
            st.executeUpdate("insert into kullanici (pass,role,name,surname,gender)"
                    + " values ('" + kullanici.getPass() + "','" + kullanici.getRole() + "','" + kullanici.getName() + "','" + kullanici.getSurname() + "'," + kullanici.isGender() + ")", Statement.RETURN_GENERATED_KEYS);

            Long kullanici_id = null;

            ResultSet gk = st.getGeneratedKeys();
            if (gk.next()) {
                kullanici_id = gk.getLong(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(KullaniciDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delete(Kullanici kullanici) {
        try {
            PreparedStatement pst = this.getC().prepareStatement("delete from kullanici where kullanici_id=?");
            pst.setLong(1, kullanici.getKullanici_id());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(KullaniciDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void update(Kullanici kullanici) {

        try {
            PreparedStatement pst = this.getC().prepareStatement("update kullanici set name=?,surname=?,pass=?,gender=?,role=? where kullanici_id=?");
            pst.setString(1, kullanici.getName());
            pst.setString(2, kullanici.getSurname());
            pst.setString(3, kullanici.getPass());
            pst.setBoolean(4, kullanici.isGender());
            pst.setInt(5, kullanici.getRole());
            pst.setLong(6, kullanici.getKullanici_id());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(KullaniciDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public Kullanici getKullanici() {
        return kullanici;
    }

    public void setKullanici(Kullanici kullanici) {
        this.kullanici = kullanici;
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

    public Connection getC() throws SQLException {
        if (this.c == null) {
            this.c = this.getDb().connect();
        }
        return c;
    }

    public void setC(Connection c) {
        this.c = c;
    }

    public BisikletDAO getBisikletDAO() {
        if (this.bisikletDAO == null) {
            this.bisikletDAO = new BisikletDAO();
        }
        return bisikletDAO;
    }

    public void setBisikletDAO(BisikletDAO bisikletDAO) {
        this.bisikletDAO = bisikletDAO;
    }

    public AtvDAO getAtvDAO() {
        if (this.atvDAO == null) {
            this.atvDAO = new AtvDAO();
        }
        return atvDAO;
    }

    public void setAtvDAO(AtvDAO atvDAO) {
        this.atvDAO = atvDAO;
    }

    public MotorDAO getMotorDAO() {
        if (this.motorDAO == null) {
            this.motorDAO = new MotorDAO();
        }
        return motorDAO;
    }

    public void setMotorDAO(MotorDAO motorDAO) {
        this.motorDAO = motorDAO;
    }

    public ScooterDAO getScooterDAO() {
        if (this.scooterDAO == null) {
            this.scooterDAO = new ScooterDAO();
        }
        return scooterDAO;
    }

    public void setScooterDAO(ScooterDAO scooterDAO) {
        this.scooterDAO = scooterDAO;
    }

    public GingerDAO getGingerDAO() {
        if (this.gingerDAO == null) {
            this.gingerDAO = new GingerDAO();
        }
        return gingerDAO;
    }

    public void setGingerDAO(GingerDAO gingerDAO) {
        this.gingerDAO = gingerDAO;
    }

    public HoverboardDAO getHoverboardDAO() {
        if (this.hoverboardDAO == null) {
            this.hoverboardDAO = new HoverboardDAO();
        }
        return hoverboardDAO;
    }

    public void setScooterDAO(HoverboardDAO hoverboardDAO) {
        this.hoverboardDAO = hoverboardDAO;
    }

}
