package Database;

import Entity.Kullanici;
import Entity.Atv;
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

public class AtvDAO {

    private Atv atv;
    private DBConnection db;
    private Connection c;
    private AtvDAO atvDAO;
    private KullaniciDAO kullanicidao;

    public List<Atv> getAtvListByKategori(Long kategori_id) {
        List<Atv> liste = new ArrayList<>();

        Atv tmp;

        try {
            PreparedStatement pst = this.getC().prepareStatement("select * from atv_kategori where kategori_id=?");
            pst.setLong(1, kategori_id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                tmp = new Atv();

                tmp = this.find(rs.getLong("atv_id"));

                liste.add(tmp);
            }

        } catch (SQLException ex) {
            Logger.getLogger(BisikletDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return liste;
    }

    public List<Atv> getAtvList(int page, int pageSize) {
        List<Atv> liste = new ArrayList<>();
        Atv tmp;

        int start = (page - 1) * pageSize;

        try {
            PreparedStatement pst = this.getC().prepareStatement("select * from atv order by atv_id asc limit " + start + "," + pageSize);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                tmp = new Atv();
                tmp.setAtv_id(rs.getLong("atv_id"));
                tmp.setMarka(rs.getString("marka"));
                tmp.setModel(rs.getString("model"));
                tmp.setUcret(rs.getInt("ucret"));
                tmp.setMotorhacmi(rs.getInt("motor_hacmi"));
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
            PreparedStatement pst = this.getC().prepareStatement("select count(atv_id) as atv_count from atv");
            ResultSet rs = pst.executeQuery();
            rs.next();
            count = rs.getInt("atv_count");

        } catch (SQLException ex) {
            Logger.getLogger(AtvDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return count;

    }

    public void update(Atv atv) {
        try {
            PreparedStatement pst = this.getC().prepareStatement("update atv set marka=?,model=?,motor_hacmi=?,ucret=?,kullanici_id=? where atv_id=?");
            pst.setString(1, atv.getMarka());
            pst.setString(2, atv.getModel());
            pst.setInt(3, atv.getMotorhacmi());
            pst.setInt(4, atv.getUcret());
            pst.setLong(5, atv.getKullanici().getKullanici_id());
            pst.setLong(6, atv.getAtv_id());
            pst.executeUpdate();

            pst = this.getC().prepareStatement("update kullanici_atv set kullanici_id=? where atv_id=?");
            pst.setLong(1, atv.getKullanici().getKullanici_id());
            pst.setLong(2, atv.getAtv_id());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(AtvDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void create(Atv atv) {
        try {
            // Önce atv tablosna sonra atv- kullanici tablosuna

            Statement st = this.getC().createStatement();
            st.executeUpdate("insert into atv (marka,model,ucret,motor_hacmi,kullanici_id)"
                    + " values ('" + atv.getMarka() + "','" + atv.getModel() + "','" + atv.getUcret() + "','" + atv.getMotorhacmi() + "','" + atv.getKullanici().getKullanici_id() + "')", Statement.RETURN_GENERATED_KEYS);

            Long atv_id = null;
            ResultSet gk = st.getGeneratedKeys(); // Ekledigimiz filmin id sini alıp film_Category ilişki tablosuna ekliyecegiz.
            if (gk.next()) {
                atv_id = gk.getLong(1); // 1. SÜTÜNDAKİ İD Yİ ALIYORUZ VERİ TABANINDAN
            }
            System.out.println("------" + atv_id);

            Statement st2 = this.getC().createStatement();
            st2.executeUpdate("insert into kullanici_atv (kullanici_id,atv_id)" + " values (" + atv.getKullanici().getKullanici_id() + "," + atv_id + ")");

            for (Kategori k : atv.getKategoriList()) {
                Statement st3 = this.getC().createStatement();
                st3.executeUpdate("insert into atv_kategori (atv_id,kategori_id)" + " values (" + atv_id + "," + k.getKategori_id() + ")");

            }

        } catch (SQLException ex) {
            Logger.getLogger(AtvDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public List<Atv> getList(Long kullanici_id) {

        List<Atv> liste = new ArrayList<>();
        try {
            PreparedStatement pst = this.getC().prepareStatement("select * from kullanici_atv where kullanici_id=?");
            pst.setLong(1, kullanici_id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                liste.add(this.find(rs.getLong("atv_id")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AtvDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return liste;
    }

    public Atv find(Long atv_id) {
        PreparedStatement pst;
        Atv tmp = null;
        try {
            pst = this.getC().prepareStatement("select * from atv where atv_id=?");
            pst.setLong(1, atv_id);
            ResultSet rs = pst.executeQuery();

            rs.next();

            tmp = new Atv();
            tmp.setAtv_id(rs.getLong("atv_id"));
            tmp.setMarka(rs.getString("marka"));
            tmp.setModel(rs.getString("model"));
            tmp.setMotorhacmi(rs.getInt("motor_hacmi"));
            tmp.setUcret(rs.getInt("ucret"));

        } catch (SQLException ex) {
            Logger.getLogger(AtvDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tmp;
    }

    public void kirala(Atv atv, Kullanici kiralayan) {
        try {
            PreparedStatement pst = this.getC().prepareStatement("update atv set kullanici_id=? where atv_id=?");
            pst.setLong(1, kiralayan.getKullanici_id());//Burada silip eklemeye gerek yok zaten tek değer direk update...
            pst.setLong(2, atv.getAtv_id());//Aşağıda eklersek bir atv farklı kişilere kiralanmış olur bu yüzden silip yeniden ekliyoruz.
            pst.executeUpdate();

            pst = this.getC().prepareStatement("delete from kullanici_atv where atv_id=?"); //Kullanici atv many to many ilişkisinden
            //var olan atvle ilgili kaydı silip yeni kiralayan kişiyi ekleyeceğiz
            pst.setLong(1, atv.getAtv_id());
            pst.executeUpdate();

            pst = this.getC().prepareStatement("insert kullanici_atv (atv_id,kullanici_id) values (?,?)");
            pst.setLong(1, atv.getAtv_id());
            pst.setLong(2, kiralayan.getKullanici_id());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(AtvDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void iadeEt(Atv atv, Kullanici kiralayan) {
        try {
            PreparedStatement pst = this.getC().prepareStatement("update atv set kullanici_id=? where atv_id=?");
            pst.setLong(1, 1);
            pst.setLong(2, atv.getAtv_id());
            pst.executeUpdate();

            pst = this.getC().prepareStatement("delete from kullanici_atv where kullanici_id=?");
            pst.setLong(1, kiralayan.getKullanici_id());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(AtvDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delete(Atv atv) {
        try {
            PreparedStatement pst = this.getC().prepareStatement("delete from atv where atv_id=?");
            pst.setLong(1, atv.getAtv_id());
            pst.executeUpdate();
            pst = this.getC().prepareStatement("delete from kullanici_atv where atv_id=?");

            pst.setLong(1, atv.getAtv_id());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(AtvDAO.class.getName()).log(Level.SEVERE, null, ex);
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

    public Atv getAtv() {
        if (this.atv == null) {
            this.atv = new Atv();
        }
        return atv;
    }

    public void setAtv(Atv atv) {
        this.atv = atv;
    }

    public AtvDAO getAtvDAO() {
        return atvDAO;
    }

    public void setAtvDAO(AtvDAO atvDAO) {
        this.atvDAO = atvDAO;
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
