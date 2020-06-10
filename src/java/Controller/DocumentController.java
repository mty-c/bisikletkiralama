package Controller;

import Database.DocumentDAO;
import Entity.Document;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;

import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.servlet.http.Part;

@Named
@SessionScoped
public class DocumentController implements Serializable {

    private Document document;
    private List<Document> documentList;
    private DocumentDAO documentDao;
    private String bul = "";
    private Part doc;
    private final String uploadTo = "D:\\Program Files (x86)\\Java\\JavaProjects\\bisiklet\\bisiklet\\WebDesign\\web\\document\\upload\\";
    
    private int page = 1;
    private int pageSize = 5;
    private int pageCount;

    public void next() {
        if (this.page == this.getPageCount()) {
            this.page = 1;
        } else {
            this.page++;
        }
    }

    public void previous() {
        if (this.page == 1) {
            this.page = this.getPageCount();
        } else {
            this.page--;
        }
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageCount() {
        this.pageCount = (int) Math.ceil(this.getDocumentDao().count() / (double) pageSize);
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public Document find(int id) {
        return this.getDocumentDao().find(id);
    }

    public void upload() {

        try {
            InputStream input = doc.getInputStream();
            File f = new File(uploadTo + doc.getSubmittedFileName());
            Files.copy(input, f.toPath());
            this.document = this.getDocument();
            this.document.setFilePath(f.getParent());
            this.document.setFileName(f.getName());
            this.document.setFileType(doc.getContentType());

            this.getDocumentDao().insert(this.document);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public String getUploadTo() {
        return uploadTo;
    }

    public Document getDocument() {
        if (this.document == null) {
            this.document = new Document();
        }

        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public List<Document> getDocumentList() {
        this.documentList = this.getDocumentDao().findAll(this.bul,page,pageSize);
        return documentList;
    }

    public void setDocumentList(List<Document> documentList) {
        this.documentList = documentList;
    }

    public DocumentDAO getDocumentDao() {
        if (this.documentDao == null) {
            this.documentDao = new DocumentDAO();
        }
        return documentDao;
    }

    public void delete() {
        this.getDocumentDao().delete(this.document);
        this.document = new Document();

    }

    public void info(Document document) {
        this.document = document;
    }

    public void setDocumentDao(DocumentDAO documentDao) {
        this.documentDao = documentDao;
    }

    public Part getDoc() {
        return doc;
    }

    public void setDoc(Part doc) {
        this.doc = doc;
    }

    public String getBul() {
        return bul;
    }

    public void setBul(String bul) {
        this.bul = bul;
    }

}
