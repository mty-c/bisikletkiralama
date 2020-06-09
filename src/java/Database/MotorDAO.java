package Database;

import Entity.Kategori;
import Entity.Kullanici;
import Entity.Motor;
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

public class MotorDAO {

    private Motor motor;
    private DBConnection db;
    private Connection c;
    private MotorDAO motorDAO;
    private KullaniciDAO kullanicidao;

    public List<Motor> getMotorListByKategori(Long kategori_id) {
        List<Motor> liste = new ArrayList<>();

        Motor tmp=null;

        try {
            PreparedStatement pst = this.getC().prepareStatement("select * from motor_kategori where kategori_id=?");
            pst.setLong(1, kategori_id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                tmp = new Motor();

                tmp = this.find(rs.getLong("motor_id"));

                liste.add(tmp);
            }

        } catch (SQLException ex) {
            Logger.getLogger(BisikletDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return liste;
    }

    public List<Motor> getMotorList(int page, int pageSize) {
        List<Motor> liste = new ArrayList<>();
        Motor tmp;

        int start = (page - 1) * pageSize;

        try {
            PreparedStatement pst = this.getC().prepareStatement("select * from motor order by motor_id asc limit " + start + "," + pageSize);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                tmp = new Motor();
                tmp.setMotor_id(rs.getLong("motor_id"));
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
            PreparedStatement pst = this.getC().prepareStatement("select count(motor_id) as motor_count from motor");
            ResultSet rs = pst.executeQuery();
            rs.next();
            count = rs.getInt("motor_count");

        } catch (SQLException ex) {
            Logger.getLogger(MotorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return count;

    }

    public void update(Motor motor) {
        try {
            PreparedStatement pst = this.getC().prepareStatement("update motor set marka=?,model=?,motor_hacmi=?,ucret=?,kullanici_id=? where motor_id=?");
            pst.setString(1, motor.getMarka());
            pst.setString(2, motor.getModel());
            pst.setInt(3, motor.getMotorhacmi());
            pst.setInt(4, motor.getUcret());
            pst.setLong(5, motor.getKullanici().getKullanici_id());
            pst.setLong(6, motor.getMotor_id());
            pst.executeUpdate();

            pst = this.getC().prepareStatement("update kullanici_motor set kullanici_id=? where motor_id=?");
            pst.setLong(1, motor.getKullanici().getKullanici_id());
            pst.setLong(2, motor.getMotor_id());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(MotorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void create(Motor motor) {
        try {

            Statement st = this.getC().createStatement();
            st.executeUpdate("insert into motor (marka,model,ucret,motor_hacmi,kullanici_id)"
                    + " values ('" + motor.getMarka() + "','" + motor.getModel() + "','" + motor.getUcret() + "','" + motor.getMotorhacmi() + "','" + motor.getKullanici().getKullanici_id() + "')", Statement.RETURN_GENERATED_KEYS);

            Long motor_id = null;
            ResultSet gk = st.getGeneratedKeys();
            if (gk.next()) {
                motor_id = gk.getLong(1);
            }
            System.out.println("------" + motor_id);

            Statement st2 = this.getC().createStatement();
            st2.executeUpdate("insert into kullanici_motor (kullanici_id,motor_id)" + " values (" + motor.getKullanici().getKullanici_id() + "," + motor_id + ")");

            for (Kategori k : motor.getKategoriList()) {
                Statement st3 = this.getC().createStatement();
                st3.executeUpdate("insert into motor_kategori (motor_id,kategori_id)" + " values (" + motor_id + "," + k.getKategori_id() + ")");

            }
        } catch (SQLException ex) {
            Logger.getLogger(MotorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public List<Motor> getList(Long kullanici_id) {

        List<Motor> liste = new ArrayList<>();
        try {
            PreparedStatement pst = this.getC().prepareStatement("select * from kullanici_motor where kullanici_id=?");
            pst.setLong(1, kullanici_id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                liste.add(this.find(rs.getLong("motor_id")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MotorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return liste;
    }

    public Motor find(Long motor_id) {
        PreparedStatement pst;
        Motor tmp = null;
        try {
            pst = this.getC().prepareStatement("select * from motor where motor_id=?");
            pst.setLong(1, motor_id);
            ResultSet rs = pst.executeQuery();

            rs.next();

            tmp = new Motor();
            tmp.setMotor_id(rs.getLong("motor_id"));
            tmp.setMarka(rs.getString("marka"));
            tmp.setModel(rs.getString("model"));
            tmp.setMotorhacmi(rs.getInt("motor_hacmi"));
            tmp.setUcret(rs.getInt("ucret"));

        } catch (SQLException ex) {
            Logger.getLogger(MotorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tmp;
    }

    public void kirala(Motor motor, Kullanici kiralayan) {
        try {
            PreparedStatement pst = this.getC().prepareStatement("update motor set kullanici_id=? where motor_id=?");
            pst.setLong(1, kiralayan.getKullanici_id());
            pst.setLong(2, motor.getMotor_id());
            pst.executeUpdate();

            pst = this.getC().prepareStatement("delete from kullanici_motor where motor_id=?");

            pst.setLong(1, motor.getMotor_id());
            pst.executeUpdate();

            pst = this.getC().prepareStatement("insert kullanici_motor (motor_id,kullanici_id) values (?,?)");
            pst.setLong(1, motor.getMotor_id());
            pst.setLong(2, kiralayan.getKullanici_id());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(MotorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void iadeEt(Motor motor, Kullanici kiralayan) {
        try {
            PreparedStatement pst = this.getC().prepareStatement("update motor set kullanici_id=? where motor_id=?");
            pst.setLong(1, 1);
            pst.setLong(2, motor.getMotor_id());
            pst.executeUpdate();

            pst = this.getC().prepareStatement("delete from kullanici_motor where kullanici_id=?");
            pst.setLong(1, kiralayan.getKullanici_id());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(MotorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delete(Motor motor) {
        try {
            PreparedStatement pst = this.getC().prepareStatement("delete from motor where motor_id=?");
            pst.setLong(1, motor.getMotor_id());
            pst.executeUpdate();
            pst = this.getC().prepareStatement("delete from kullanici_motor where motor_id=?");

            pst.setLong(1, motor.getMotor_id());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(MotorDAO.class.getName()).log(Level.SEVERE, null, ex);
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

    public Motor getMotor() {
        if (this.motor == null) {
            this.motor = new Motor();
        }
        return motor;
    }

    public void setMotor(Motor motor) {
        this.motor = motor;
    }

    public MotorDAO getMotorDAO() {
        return motorDAO;
    }

    public void setMotorDAO(MotorDAO motorDAO) {
        this.motorDAO = motorDAO;
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
