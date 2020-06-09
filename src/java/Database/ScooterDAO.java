package Database;

import Entity.Kategori;
import Entity.Scooter;
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

public class ScooterDAO {

    private Scooter scooter;
    private DBConnection db;
    private Connection c;
    private ScooterDAO scooterDAO;
    private KullaniciDAO kullanicidao;

    public List<Scooter> getScooterListByKategori(Long kategori_id) {
        List<Scooter> liste = new ArrayList<>();

        Scooter tmp=null;

        try {
            PreparedStatement pst = this.getC().prepareStatement("select * from scooter_kategori where kategori_id=?");
            pst.setLong(1, kategori_id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                tmp = new Scooter();

                tmp = this.find(rs.getLong("scooter_id"));

                liste.add(tmp);
            }

        } catch (SQLException ex) {
            Logger.getLogger(BisikletDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return liste;
    }

    public List<Scooter> getScooterList(int page, int pageSize) {
        List<Scooter> liste = new ArrayList<>();
        Scooter tmp;

        int start = (page - 1) * pageSize;

        try {
            PreparedStatement pst = this.getC().prepareStatement("select * from scooter order by scooter_id asc limit " + start + "," + pageSize);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                tmp = new Scooter();
                tmp.setScooter_id(rs.getLong("scooter_id"));
                tmp.setMarka(rs.getString("marka"));
                tmp.setModel(rs.getString("model"));
                tmp.setUcret(rs.getInt("ucret"));
                tmp.setBoyut(rs.getString("boyut"));
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
            PreparedStatement pst = this.getC().prepareStatement("select count(scooter_id) as scooter_count from scooter");
            ResultSet rs = pst.executeQuery();
            rs.next();
            count = rs.getInt("scooter_count");

        } catch (SQLException ex) {
            Logger.getLogger(ScooterDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return count;

    }

    public void update(Scooter scooter) {
        try {
            PreparedStatement pst = this.getC().prepareStatement("update scooter set marka=?,model=?,boyut=?,ucret=?,kullanici_id=? where scooter_id=?");
            pst.setString(1, scooter.getMarka());
            pst.setString(2, scooter.getModel());
            pst.setString(3, scooter.getBoyut());
            pst.setInt(4, scooter.getUcret());
            pst.setLong(5, scooter.getKullanici().getKullanici_id());
            pst.setLong(6, scooter.getScooter_id());
            pst.executeUpdate();

            pst = this.getC().prepareStatement("update kullanici_scooter set kullanici_id=? where scooter_id=?");
            pst.setLong(1, scooter.getKullanici().getKullanici_id());
            pst.setLong(2, scooter.getScooter_id());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ScooterDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void create(Scooter scooter) {
        try {

            Statement st = this.getC().createStatement();
            st.executeUpdate("insert into scooter (marka,model,ucret,boyut,kullanici_id)"
                    + " values ('" + scooter.getMarka() + "','" + scooter.getModel() + "','" + scooter.getUcret() + "','" + scooter.getBoyut() + "','" + scooter.getKullanici().getKullanici_id() + "')", Statement.RETURN_GENERATED_KEYS);

            Long scooter_id = null;
            ResultSet gk = st.getGeneratedKeys();
            if (gk.next()) {
                scooter_id = gk.getLong(1);
            }
            System.out.println("------" + scooter_id);

            Statement st2 = this.getC().createStatement();
            st2.executeUpdate("insert into kullanici_scooter (kullanici_id,scooter_id)" + " values (" + scooter.getKullanici().getKullanici_id() + "," + scooter_id + ")");
             
            
            for (Kategori k : scooter.getKategoriList()) {
                Statement st3 = this.getC().createStatement();
                st3.executeUpdate("insert into scooter_kategori (scooter_id,kategori_id)" + " values (" + scooter_id + "," + k.getKategori_id() + ")");

            }
       
        
        } catch (SQLException ex) {
            Logger.getLogger(ScooterDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public List<Scooter> getList(Long kullanici_id) {

        List<Scooter> liste = new ArrayList<>();
        try {
            PreparedStatement pst = this.getC().prepareStatement("select * from kullanici_scooter where kullanici_id=?");
            pst.setLong(1, kullanici_id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                liste.add(this.find(rs.getLong("scooter_id")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ScooterDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return liste;
    }

    public Scooter find(Long scooter_id) {
        PreparedStatement pst;
        Scooter tmp = null;
        try {
            pst = this.getC().prepareStatement("select * from scooter where scooter_id=?");
            pst.setLong(1, scooter_id);
            ResultSet rs = pst.executeQuery();

            rs.next();

            tmp = new Scooter();
            tmp.setScooter_id(rs.getLong("scooter_id"));
            tmp.setMarka(rs.getString("marka"));
            tmp.setModel(rs.getString("model"));
            tmp.setBoyut(rs.getString("boyut"));
            tmp.setUcret(rs.getInt("ucret"));

        } catch (SQLException ex) {
            Logger.getLogger(ScooterDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tmp;
    }

    public void kirala(Scooter scooter, Kullanici kiralayan) {
        try {
            PreparedStatement pst = this.getC().prepareStatement("update scooter set kullanici_id=? where scooter_id=?");
            pst.setLong(1, kiralayan.getKullanici_id());
            pst.setLong(2, scooter.getScooter_id());
            pst.executeUpdate();

            pst = this.getC().prepareStatement("delete from kullanici_scooter where scooter_id=?");

            pst.setLong(1, scooter.getScooter_id());
            pst.executeUpdate();

            pst = this.getC().prepareStatement("insert kullanici_scooter (scooter_id,kullanici_id) values (?,?)");
            pst.setLong(1, scooter.getScooter_id());
            pst.setLong(2, kiralayan.getKullanici_id());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ScooterDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void iadeEt(Scooter scooter, Kullanici kiralayan) {
        try {
            PreparedStatement pst = this.getC().prepareStatement("update scooter set kullanici_id=? where scooter_id=?");
            pst.setLong(1, 1);
            pst.setLong(2, scooter.getScooter_id());
            pst.executeUpdate();

            pst = this.getC().prepareStatement("delete from kullanici_scooter where kullanici_id=?");
            pst.setLong(1, kiralayan.getKullanici_id());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ScooterDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delete(Scooter scooter) {
        try {
            PreparedStatement pst = this.getC().prepareStatement("delete from scooter where scooter_id=?");
            pst.setLong(1, scooter.getScooter_id());
            pst.executeUpdate();
            pst = this.getC().prepareStatement("delete from kullanici_scooter where scooter_id=?");

            pst.setLong(1, scooter.getScooter_id());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ScooterDAO.class.getName()).log(Level.SEVERE, null, ex);
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

    public Scooter getScooter() {
        if (this.scooter == null) {
            this.scooter = new Scooter();
        }
        return scooter;
    }

    public void setScooter(Scooter scooter) {
        this.scooter = scooter;
    }

    public ScooterDAO getScooterDAO() {
        return scooterDAO;
    }

    public void setScooterDAO(ScooterDAO scooterDAO) {
        this.scooterDAO = scooterDAO;
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
