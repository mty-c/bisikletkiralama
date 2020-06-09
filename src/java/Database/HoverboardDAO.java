package Database;

import Entity.Kullanici;
import Entity.Hoverboard;
import Entity.Kategori;
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

public class HoverboardDAO {

    private Hoverboard hoverboard;
    private DBConnection db;
    private Connection c;
    private HoverboardDAO hoverboardDAO;
    private KullaniciDAO kullanicidao;

    public List<Hoverboard> getHoverboardListByKategori(Long kategori_id) {
        List<Hoverboard> liste = new ArrayList<>();

        Hoverboard tmp;

        try {
            PreparedStatement pst = this.getC().prepareStatement("select * from hoverboard_kategori where kategori_id=?");
            pst.setLong(1, kategori_id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                tmp = new Hoverboard();

                tmp = this.find(rs.getLong("hoverboard_id"));

                liste.add(tmp);
            }

        } catch (SQLException ex) {
            Logger.getLogger(HoverboardDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return liste;
    }

    public List<Hoverboard> getHoverboardList(int page, int pageSize) {
        List<Hoverboard> liste = new ArrayList<>();
        Hoverboard tmp;

        int start = (page - 1) * pageSize;

        try {
            PreparedStatement pst = this.getC().prepareStatement("select * from hoverboard order by hoverboard_id asc limit " + start + "," + pageSize);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                tmp = new Hoverboard();
                tmp.setHoverboard_id(rs.getLong("hoverboard_id"));
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
            Logger.getLogger(HoverboardDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return liste;

    }

    public int count() {
        int count = 0;

        try {
            PreparedStatement pst = this.getC().prepareStatement("select count(hoverboard_id) as hoverboard_count from hoverboard");
            ResultSet rs = pst.executeQuery();
            rs.next();
            count = rs.getInt("hoverboard_count");

        } catch (SQLException ex) {
            Logger.getLogger(HoverboardDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return count;

    }

    public void update(Hoverboard hoverboard) {
        try {
            PreparedStatement pst = this.getC().prepareStatement("update hoverboard set marka=?,model=?,renk=?,ucret=?,kullanici_id=? where hoverboard_id=?");
            pst.setString(1, hoverboard.getMarka());
            pst.setString(2, hoverboard.getModel());
            pst.setString(3, hoverboard.getRenk());
            pst.setInt(4, hoverboard.getUcret());
            pst.setLong(5, hoverboard.getKullanici().getKullanici_id());
            pst.setLong(6, hoverboard.getHoverboard_id());
            pst.executeUpdate();

            pst = this.getC().prepareStatement("update kullanici_hoverboard set kullanici_id=? where hoverboard_id=?");
            pst.setLong(1, hoverboard.getKullanici().getKullanici_id());
            pst.setLong(2, hoverboard.getHoverboard_id());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(HoverboardDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void create(Hoverboard hoverboard) {
        try {

            Statement st = this.getC().createStatement();
            st.executeUpdate("insert into hoverboard (marka,model,ucret,renk,kullanici_id)"
                    + " values ('" + hoverboard.getMarka() + "','" + hoverboard.getModel() + "','" + hoverboard.getUcret() + "','" + hoverboard.getRenk() + "','" + hoverboard.getKullanici().getKullanici_id() + "')", Statement.RETURN_GENERATED_KEYS);

            Long hoverboard_id = null;
            ResultSet gk = st.getGeneratedKeys();
            if (gk.next()) {
                hoverboard_id = gk.getLong(1);
            }
            System.out.println("------" + hoverboard_id);

            Statement st2 = this.getC().createStatement();
            st2.executeUpdate("insert into kullanici_hoverboard (kullanici_id,hoverboard_id)" + " values (" + hoverboard.getKullanici().getKullanici_id() + "," + hoverboard_id + ")");
             
                for (Kategori k : hoverboard.getKategoriList()) {
                Statement st3 = this.getC().createStatement();
                st3.executeUpdate("insert into hoverboard_kategori (hoverboard_id,kategori_id)" + " values (" + hoverboard_id + "," + k.getKategori_id() + ")");

            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(HoverboardDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public List<Hoverboard> getList(Long kullanici_id) {

        List<Hoverboard> liste = new ArrayList<>();
        try {
            PreparedStatement pst = this.getC().prepareStatement("select * from kullanici_hoverboard where kullanici_id=?");
            pst.setLong(1, kullanici_id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                liste.add(this.find(rs.getLong("hoverboard_id")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(HoverboardDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return liste;
    }

    public Hoverboard find(Long hoverboard_id) {
        PreparedStatement pst;
        Hoverboard tmp = null;
        try {
            pst = this.getC().prepareStatement("select * from hoverboard where hoverboard_id=?");
            pst.setLong(1, hoverboard_id);
            ResultSet rs = pst.executeQuery();

            rs.next();

            tmp = new Hoverboard();
            tmp.setHoverboard_id(rs.getLong("hoverboard_id"));
            tmp.setMarka(rs.getString("marka"));
            tmp.setModel(rs.getString("model"));
            tmp.setRenk(rs.getString("renk"));
            tmp.setUcret(rs.getInt("ucret"));

        } catch (SQLException ex) {
            Logger.getLogger(HoverboardDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tmp;
    }

    public void kirala(Hoverboard hoverboard, Kullanici kiralayan) {
        try {
            PreparedStatement pst = this.getC().prepareStatement("update hoverboard set kullanici_id=? where hoverboard_id=?");
            pst.setLong(1, kiralayan.getKullanici_id());
            pst.setLong(2, hoverboard.getHoverboard_id());
            pst.executeUpdate();

            pst = this.getC().prepareStatement("delete from kullanici_hoverboard where hoverboard_id=?");
            pst.setLong(1, hoverboard.getHoverboard_id());
            pst.executeUpdate();

            pst = this.getC().prepareStatement("insert kullanici_hoverboard (hoverboard_id,kullanici_id) values (?,?)");
            pst.setLong(1, hoverboard.getHoverboard_id());
            pst.setLong(2, kiralayan.getKullanici_id());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(HoverboardDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void iadeEt(Hoverboard hoverboard, Kullanici kiralayan) {
        try {
            PreparedStatement pst = this.getC().prepareStatement("update hoverboard set kullanici_id=? where hoverboard_id=?");
            pst.setLong(1, 1);
            pst.setLong(2, hoverboard.getHoverboard_id());
            pst.executeUpdate();

            pst = this.getC().prepareStatement("delete from kullanici_hoverboard where kullanici_id=?");
            pst.setLong(1, kiralayan.getKullanici_id());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(HoverboardDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delete(Hoverboard hoverboard) {
        try {
            PreparedStatement pst = this.getC().prepareStatement("delete from hoverboard where hoverboard_id=?");
            pst.setLong(1, hoverboard.getHoverboard_id());
            pst.executeUpdate();
            pst = this.getC().prepareStatement("delete from kullanici_hoverboard where hoverboard_id=?");

            pst.setLong(1, hoverboard.getHoverboard_id());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(HoverboardDAO.class.getName()).log(Level.SEVERE, null, ex);
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

    public Hoverboard getHoverboard() {
        if (this.hoverboard == null) {
            this.hoverboard = new Hoverboard();
        }
        return hoverboard;
    }

    public void setHoverboard(Hoverboard hoverboard) {
        this.hoverboard = hoverboard;
    }

    public HoverboardDAO getHoverboardDAO() {
        if (this.hoverboardDAO == null) {
            this.hoverboardDAO = new HoverboardDAO();
        }
        return hoverboardDAO;
    }

    public void setHoverboardDAO(HoverboardDAO hoverboardDAO) {
        this.hoverboardDAO = hoverboardDAO;
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
