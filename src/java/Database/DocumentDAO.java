package Database;

import Entity.Document;
import Util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DocumentDAO {

    private DBConnection db;
    private Connection c;

    public List<Document> findAll() {
        List<Document> dList = new ArrayList<>();
        try {
            PreparedStatement pst = this.getC().prepareStatement("select * from document");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Document d = new Document();
                d.setDocumentId(rs.getInt("document_id"));
                d.setFilePath(rs.getString("filepath"));
                d.setFileName(rs.getString("filename"));
                d.setFileType(rs.getString("filetype"));
                dList.add(d);
            }
            pst.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dList;
    }

    public void insert(Document d) {
        try {
            PreparedStatement pst = this.getC().prepareStatement("insert into document(filepath,filename,filetype) values(?,?,?)");
            pst.setString(1, d.getFilePath());
            pst.setString(2, d.getFileName());
            pst.setString(3, d.getFileType());
            pst.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void delete(Document document) {
        String query = "delete from document where document_id=?";
        try {
            PreparedStatement pst = getC().prepareStatement(query);
            pst.setLong(1, document.getDocumentId());
            pst.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Document find(int id) {
        Document d = null;
        try {
            String query = ("select * from document where document_id=" + id);
            PreparedStatement pst = getC().prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            rs.next();

            d = new Document();
            d.setDocumentId(rs.getInt("document_id"));
            d.setFilePath(rs.getString("filepath"));
            d.setFileName(rs.getString("filename"));
            d.setFileType(rs.getString("filetype"));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return d;

    }
    public List<Document> findAll(String bulunacakDeger, int page, int pageSize) { 
        List<Document> dList = new ArrayList<>();
        int start = (page - 1) * pageSize;
        try {
            PreparedStatement pst = getC().prepareStatement("select*from document where filename like ? or filetype like ? order by document_id asc limit " + start + " , " + pageSize);
            pst.setString(1, "%" + bulunacakDeger + "%");  
            pst.setString(2, "%" + bulunacakDeger + "%");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Document d = new Document();
                d.setDocumentId(rs.getInt("document_id"));
                d.setFilePath(rs.getString("filepath"));
                d.setFileName(rs.getString("filename"));
                d.setFileType(rs.getString("filetype"));
                dList.add(d);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dList;
    }
    public int count() {
        int count = 0;

        try {
            PreparedStatement pst = this.getC().prepareStatement("select count(document_id) as document_count from document");
            ResultSet rs = pst.executeQuery();
            rs.next();
            count = rs.getInt("document_count");

        } catch (SQLException ex) {
            Logger.getLogger(DocumentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return count;

    }

    public DBConnection getDb() {
        if (this.db == null) {
            this.db = new DBConnection();
        }
        return db;
    }

    public Connection getC() throws SQLException {
        if (this.c == null) {
            this.c = this.getDb().connect();
        }
        return c;
    }
}
