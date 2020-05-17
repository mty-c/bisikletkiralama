package Database;

import Entity.Bisiklet;
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

/**
 *
 * @author dazlakgandalf
 */
public class KullaniciDAO {

    private Kullanici kullanici;
    private DBConnection db;
    private Connection c;
    private BisikletDAO bisikletdao;

    public Kullanici login(Kullanici kullanici) {
        Kullanici tmp = new Kullanici();
        try {
            PreparedStatement pst = this.getC().prepareStatement("select * from kullanici where name=? and pass=?");
            pst.setString(1, kullanici.getName());
            pst.setInt(2, kullanici.getPass());
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                tmp.setKullanici_id(rs.getLong("kullanici_id")); // Burada bunu çekmezsem kiralama yapamam!!
                tmp.setName(rs.getString("name"));
                tmp.setPass(rs.getInt("pass"));
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
            kullanici.setPass(rs.getInt("pass"));
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
            pst.setInt(1, kullanici.getPass());
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
                tmp.setPass(rs.getInt("pass"));
                tmp.setSurname(rs.getString("surname"));
                tmp.setBisikletler(this.getBisikletdao().getList(rs.getLong("kullanici_id")));
                liste.add(tmp);
            }

        } catch (SQLException ex) {
            Logger.getLogger(KullaniciDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return liste;
    }

    public void create(Kullanici kullanici) { // 3TABLOYUDA UNUTMADAN AYNI ANDA İLERLEMELİ GÜNCELLEME YADA CREATE..
        try {
            Statement st = this.getC().createStatement();
            st.executeUpdate("insert into kullanici (pass,role,name,surname,gender)"
                    + " values (" + kullanici.getPass() + "," + kullanici.getRole() + ",'" + kullanici.getName() + "','" + kullanici.getSurname() + "'," + kullanici.isGender() + ")", Statement.RETURN_GENERATED_KEYS);

            Long kullanici_id = null;

            ResultSet gk = st.getGeneratedKeys();
            if (gk.next()) {
                kullanici_id = gk.getLong(1);
            }

            for (Bisiklet b : kullanici.getBisikletler()) {
                Statement st2 = this.getC().createStatement();
                st2.executeUpdate("insert into kullanici_bisiklet (kullanici_id,bisiklet_id)" + "values (" + kullanici_id + "," + b.getBisiklet_id() + ")");
                PreparedStatement pst = this.getC().prepareStatement("update bisiklet set kullanici_id=? where bisiklet_id=?");
                pst.setLong(1, kullanici_id); //Bisiklet Tablosundada güncelleme olmalı...
                pst.setLong(2, b.getBisiklet_id());
                pst.executeUpdate();
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

            pst = this.getC().prepareStatement("delete from kullanici_bisiklet where kullanici_id=?");
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
            pst.setInt(3, kullanici.getPass());
            pst.setBoolean(4, kullanici.isGender());
            pst.setInt(5, kullanici.getRole());
            pst.setLong(6, kullanici.getKullanici_id());
            pst.executeUpdate();

            pst = this.getC().prepareStatement("delete from kullanici_bisiklet where kullanici_id=?");
            pst.setLong(1, kullanici.getKullanici_id());
            pst.executeUpdate();

            for (Bisiklet b : kullanici.getBisikletler()) {
                pst = this.getC().prepareStatement("insert into kullanici_bisiklet (kullanici_id,bisiklet_id) values(?,?)");
                pst.setLong(1, kullanici.getKullanici_id());
                pst.setLong(2, b.getBisiklet_id());
                pst.executeUpdate();

            }
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

    public Connection getC() {
        if (this.c == null) {
            this.c = this.getDb().connect();
        }
        return c;
    }

    public void setC(Connection c) {
        this.c = c;
    }

    public BisikletDAO getBisikletdao() {
        if (this.bisikletdao == null) {
            this.bisikletdao = new BisikletDAO();
        }
        return bisikletdao;
    }

    public void setBisikletdao(BisikletDAO bisikletdao) {
        this.bisikletdao = bisikletdao;
    }

}
