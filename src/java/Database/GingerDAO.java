package Database;

import Entity.Ginger;
import Entity.Kategori;
import Entity.Kullanici;
import Util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GingerDAO {

    private Ginger ginger;
    private DBConnection db;
    private Connection c;
    private GingerDAO gingerDAO;
    private KullaniciDAO kullanicidao;

    public List<Ginger> getGingerListByKategori(Long kategori_id) {
        List<Ginger> liste = new ArrayList<>();

        Ginger tmp;

        try {
            PreparedStatement pst = this.getC().prepareStatement("select * from ginger_kategori where kategori_id=?");
            pst.setLong(1, kategori_id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                tmp = new Ginger();

                tmp = this.find(rs.getLong("ginger_id"));

                liste.add(tmp);
            }

        } catch (SQLException ex) {
            Logger.getLogger(BisikletDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return liste;
    }

    public List<Ginger> getGingerList(int page, int pageSize) {
        List<Ginger> liste = new ArrayList<>();
        Ginger tmp;

        int start = (page - 1) * pageSize;

        try {
            PreparedStatement pst = this.getC().prepareStatement("select * from ginger order by ginger_id asc limit " + start + "," + pageSize);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                tmp = new Ginger();
                tmp.setGinger_id(rs.getLong("ginger_id"));
                tmp.setMarka(rs.getString("marka"));
                tmp.setModel(rs.getString("model"));
                tmp.setUcret(rs.getInt("ucret"));
                tmp.setRenk(rs.getString("renk"));
                tmp.setKullanici(this.getKullanicidao().find(rs.getLong("kullanici_id")));

                liste.add(tmp);
            }
            pst.close();
            rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(BisikletDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return liste;

    }

    public int count() {
        int count = 0;

        try {
            PreparedStatement pst = this.getC().prepareStatement("select count(ginger_id) as ginger_count from ginger");
            ResultSet rs = pst.executeQuery();
            rs.next();
            count = rs.getInt("ginger_count");

        } catch (SQLException ex) {
            Logger.getLogger(GingerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return count;

    }

    public void update(Ginger ginger) {
        try {
            PreparedStatement pst = this.getC().prepareStatement("update ginger set marka=?,model=?,renk=?,ucret=?,kullanici_id=? where ginger_id=?");
            pst.setString(1, ginger.getMarka());
            pst.setString(2, ginger.getModel());
            pst.setString(3, ginger.getRenk());
            pst.setInt(4, ginger.getUcret());
            pst.setLong(5, ginger.getKullanici().getKullanici_id());
            pst.setLong(6, ginger.getGinger_id());
            pst.executeUpdate();

            pst = this.getC().prepareStatement("update kullanici_ginger set kullanici_id=? where ginger_id=?");
            pst.setLong(1, ginger.getKullanici().getKullanici_id());
            pst.setLong(2, ginger.getGinger_id());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(GingerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void create(Ginger ginger) {
        try {

            Statement st = this.getC().createStatement();
            st.executeUpdate("insert into ginger (marka,model,ucret,renk,kullanici_id)"
                    + " values ('" + ginger.getMarka() + "','" + ginger.getModel() + "','" + ginger.getUcret() + "','" + ginger.getRenk() + "','" + ginger.getKullanici().getKullanici_id() + "')", Statement.RETURN_GENERATED_KEYS);

            Long ginger_id = null;
            ResultSet gk = st.getGeneratedKeys();
            if (gk.next()) {
                ginger_id = gk.getLong(1);
            }
            System.out.println("------" + ginger_id);

            Statement st2 = this.getC().createStatement();
            st2.executeUpdate("insert into kullanici_ginger (kullanici_id,ginger_id)" + " values (" + ginger.getKullanici().getKullanici_id() + "," + ginger_id + ")");

            for (Kategori k : ginger.getKategoriList()) {
                Statement st3 = this.getC().createStatement();
                st3.executeUpdate("insert into ginger_kategori (ginger_id,kategori_id)" + " values (" + ginger_id + "," + k.getKategori_id() + ")");

            }
        } catch (SQLException ex) {
            Logger.getLogger(GingerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public List<Ginger> getList(Long kullanici_id) {

        List<Ginger> liste = new ArrayList<>();
        try {
            PreparedStatement pst = this.getC().prepareStatement("select * from kullanici_ginger where kullanici_id=?");
            pst.setLong(1, kullanici_id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                liste.add(this.find(rs.getLong("ginger_id")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(GingerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return liste;
    }

    public Ginger find(Long ginger_id) {
        PreparedStatement pst;
        Ginger tmp = null;
        try {
            pst = this.getC().prepareStatement("select * from ginger where ginger_id=?");
            pst.setLong(1, ginger_id);
            ResultSet rs = pst.executeQuery();

            rs.next();

            tmp = new Ginger();
            tmp.setGinger_id(rs.getLong("ginger_id"));
            tmp.setMarka(rs.getString("marka"));
            tmp.setModel(rs.getString("model"));
            tmp.setRenk(rs.getString("renk"));
            tmp.setUcret(rs.getInt("ucret"));

        } catch (SQLException ex) {
            Logger.getLogger(GingerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tmp;
    }

    public void kirala(Ginger ginger, Kullanici kiralayan) {
        try {
            PreparedStatement pst = this.getC().prepareStatement("update ginger set kullanici_id=? where ginger_id=?");
            pst.setLong(1, kiralayan.getKullanici_id());
            pst.setLong(2, ginger.getGinger_id());
            pst.executeUpdate();

            pst = this.getC().prepareStatement("delete from kullanici_ginger where ginger_id=?");
            pst.setLong(1, ginger.getGinger_id());
            pst.executeUpdate();

            pst = this.getC().prepareStatement("insert kullanici_ginger (ginger_id,kullanici_id) values (?,?)");
            pst.setLong(1, ginger.getGinger_id());
            pst.setLong(2, kiralayan.getKullanici_id());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(GingerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void iadeEt(Ginger ginger, Kullanici kiralayan) {
        try {
            PreparedStatement pst = this.getC().prepareStatement("update ginger set kullanici_id=? where ginger_id=?");
            pst.setLong(1, 1);
            pst.setLong(2, ginger.getGinger_id());
            pst.executeUpdate();

            pst = this.getC().prepareStatement("delete from kullanici_ginger where kullanici_id=?");
            pst.setLong(1, kiralayan.getKullanici_id());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(GingerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delete(Ginger ginger) {
        try {
            PreparedStatement pst = this.getC().prepareStatement("delete from ginger where ginger_id=?");
            pst.setLong(1, ginger.getGinger_id());
            pst.executeUpdate();
            pst = this.getC().prepareStatement("delete from kullanici_ginger where ginger_id=?");

            pst.setLong(1, ginger.getGinger_id());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(GingerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public KullaniciDAO getKullanicidao() {
        if (this.kullanicidao == null) {
            this.kullanicidao = new KullaniciDAO();
        }
        return kullanicidao;
    }

    public void setKullanicidao(KullaniciDAO kullanicidao) {
        this.kullanicidao = kullanicidao;
    }

    public Ginger getGinger() {
        if (this.ginger == null) {
            this.ginger = new Ginger();
        }
        return ginger;
    }

    public void setGinger(Ginger ginger) {
        this.ginger = ginger;
    }

    public GingerDAO getGingerDAO() {
        return gingerDAO;
    }

    public void setGingerDAO(GingerDAO gingerDAO) {
        this.gingerDAO = gingerDAO;
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

}
