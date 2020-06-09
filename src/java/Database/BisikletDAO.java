package Database;

import Entity.Bisiklet;
import Entity.Kategori;
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

public class BisikletDAO {

    private Bisiklet bisiklet;
    private DBConnection db;
    private Connection c;
    private KullaniciDAO kullanicidao;
    private BisikletDAO bisikletDAO;

    public List<Bisiklet> getBisikletListByKategori(Long kategori_id) {
        List<Bisiklet> liste = new ArrayList<>();

        Bisiklet tmp;

        try {
            PreparedStatement pst = this.getC().prepareStatement("select * from bisiklet_kategori where kategori_id=?");
            pst.setLong(1, kategori_id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                tmp = new Bisiklet();

                tmp = this.getBisikletDAO().find(rs.getLong("bisiklet_id"));

                liste.add(tmp);
            }

        } catch (SQLException ex) {
            Logger.getLogger(BisikletDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return liste;
    }

    public List<Bisiklet> getBisikletList(int page, int pageSize) {
        List<Bisiklet> liste = new ArrayList<>();
        Bisiklet tmp;

        int start = (page - 1) * pageSize;

        try {
            PreparedStatement pst = this.getC().prepareStatement("select * from bisiklet order by bisiklet_id asc limit " + start + "," + pageSize);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                tmp = new Bisiklet();
                tmp.setBisiklet_id(rs.getLong("bisiklet_id"));
                tmp.setMarka(rs.getString("marka"));
                tmp.setModel(rs.getString("model"));
                tmp.setUcret(rs.getInt("ucret"));
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
            PreparedStatement pst = this.getC().prepareStatement("select count(bisiklet_id) as bisiklet_count from bisiklet");
            ResultSet rs = pst.executeQuery();
            rs.next();
            count = rs.getInt("bisiklet_count");

        } catch (SQLException ex) {
            Logger.getLogger(BisikletDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return count;

    }

    public void update(Bisiklet bisiklet) {
        try {
            PreparedStatement pst = this.getC().prepareStatement("update bisiklet set marka=?,model=?,ucret=?,kullanici_id=? where bisiklet_id=?");
            pst.setString(1, bisiklet.getMarka());
            pst.setString(2, bisiklet.getModel());
            pst.setInt(3, bisiklet.getUcret());
            pst.setLong(4, bisiklet.getKullanici().getKullanici_id());
            pst.setLong(5, bisiklet.getBisiklet_id());
            pst.executeUpdate();

            pst = this.getC().prepareStatement("update kullanici_bisiklet set kullanici_id=? where bisiklet_id=?");
            pst.setLong(1, bisiklet.getKullanici().getKullanici_id());
            pst.setLong(2, bisiklet.getBisiklet_id());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(BisikletDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void create(Bisiklet bisiklet) {
        try {
            // Önce bisiklet tablosna sonra bisiklet- kullanici tablosuna

            Statement st = this.getC().createStatement();
            st.executeUpdate("insert into bisiklet (marka,model,ucret,kullanici_id)"
                    + " values ('" + bisiklet.getMarka() + "','" + bisiklet.getModel() + "','" + bisiklet.getUcret() + "','" + bisiklet.getKullanici().getKullanici_id() + "')", Statement.RETURN_GENERATED_KEYS);

            Long bisiklet_id = null;
            ResultSet gk = st.getGeneratedKeys(); // Ekledigimiz bsikletin id sini alıp bisiklet_kullanıcı ilişki tablosuna ekliyecegiz.
            if (gk.next()) {
                bisiklet_id = gk.getLong(1); // 1. SÜTÜNDAKİ İD Yİ ALIYORUZ VERİ TABANINDAN
            }
            System.out.println("------" + bisiklet_id);

            Statement st2 = this.getC().createStatement();
            st2.executeUpdate("insert into kullanici_bisiklet (kullanici_id,bisiklet_id)" + " values (" + bisiklet.getKullanici().getKullanici_id() + "," + bisiklet_id + ")");

            for (Kategori k : bisiklet.getKategoriList()) {
                Statement st3 = this.getC().createStatement();
                st3.executeUpdate("insert into bisiklet_kategori (bisiklet_id,kategori_id)" + " values (" + bisiklet_id + "," + k.getKategori_id() + ")");

            }

        } catch (SQLException ex) {
            Logger.getLogger(BisikletDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public List<Bisiklet> getList(Long kullanici_id) {

        List<Bisiklet> liste = new ArrayList<>();
        try {
            PreparedStatement pst = this.getC().prepareStatement("select * from kullanici_bisiklet where kullanici_id=?");
            pst.setLong(1, kullanici_id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                liste.add(this.find(rs.getLong("bisiklet_id")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BisikletDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return liste;
    }

    public Bisiklet find(Long bisiklet_id) {
        PreparedStatement pst;
        Bisiklet tmp = null;
        try {
            pst = this.getC().prepareStatement("select * from bisiklet where bisiklet_id=?");
            pst.setLong(1, bisiklet_id);
            ResultSet rs = pst.executeQuery();

            rs.next();

            tmp = new Bisiklet();
            tmp.setBisiklet_id(rs.getLong("bisiklet_id"));
            tmp.setMarka(rs.getString("marka"));
            tmp.setModel(rs.getString("model"));
            tmp.setUcret(rs.getInt("ucret"));

        } catch (SQLException ex) {
            Logger.getLogger(BisikletDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tmp;
    }

    public void kirala(Bisiklet bisiklet, Kullanici kiralayan) {
        try {
            PreparedStatement pst = this.getC().prepareStatement("update bisiklet set kullanici_id=? where bisiklet_id=?");
            pst.setLong(1, kiralayan.getKullanici_id());//Burada silip eklemeye gerek yok zaten tek değer direk update...
            pst.setLong(2, bisiklet.getBisiklet_id());//Aşağıda eklersek bir bisiklet farklı kişilere kiralanmış olur bu yüzden silip yeniden ekliyoruz.
            pst.executeUpdate();

            pst = this.getC().prepareStatement("delete from kullanici_bisiklet where bisiklet_id=?"); //Kullanici bisiklet many to many ilişkisinden
            //var olan bisikletle ilgili kaydı silip yeni kiralayan kişiyi ekleyeceğiz
            pst.setLong(1, bisiklet.getBisiklet_id());
            pst.executeUpdate();

            pst = this.getC().prepareStatement("insert kullanici_bisiklet (bisiklet_id,kullanici_id) values (?,?)");
            pst.setLong(1, bisiklet.getBisiklet_id());
            pst.setLong(2, kiralayan.getKullanici_id());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(BisikletDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void iadeEt(Bisiklet bisiklet, Kullanici kiralayan) {
        try {
            PreparedStatement pst = this.getC().prepareStatement("update bisiklet set kullanici_id=? where bisiklet_id=?");
            pst.setLong(1, 1);
            pst.setLong(2, bisiklet.getBisiklet_id());
            pst.executeUpdate();

            pst = this.getC().prepareStatement("delete from kullanici_bisiklet where kullanici_id=?");
            pst.setLong(1, kiralayan.getKullanici_id());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(BisikletDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delete(Bisiklet bisiklet) {
        try {
            PreparedStatement pst = this.getC().prepareStatement("delete from bisiklet where bisiklet_id=?");
            pst.setLong(1, bisiklet.getBisiklet_id());
            pst.executeUpdate();
            pst = this.getC().prepareStatement("delete from kullanici_bisiklet where bisiklet_id=?");

            pst.setLong(1, bisiklet.getBisiklet_id());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(BisikletDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public Bisiklet getBisiklet() {
        return bisiklet;
    }

    public void setBisiklet(Bisiklet bisiklet) {
        this.bisiklet = bisiklet;
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

    public KullaniciDAO getKullanicidao() {
        if (this.kullanicidao == null) {
            this.kullanicidao = new KullaniciDAO();
        }
        return kullanicidao;
    }

    public void setKullanicidao(KullaniciDAO kullanicidao) {
        this.kullanicidao = kullanicidao;
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

}
