package org.tugasbesar.demo;

/**
 *
 * @author Fauzan
 * @author Hafiz
 * @author Nadia
 * @version 16.0.1
 */
public class Musik {
    private int id;
    private String judul;
    private String penyanyi;
    private String produser;
    private String label;
    private int tahun;
    
    public Musik(int id, String judul, String penyanyi, String produser, String label, int tahun) {
        this.id = id;
        this.judul = judul;
        this.penyanyi = penyanyi;
        this.produser = produser;
        this.label = label;
        this.tahun = tahun;
    }
    
    public int getId() {
        return id;
    }
    
    public String getJudul() {
        return judul;
    }

    public String getPenyanyi() {
        return penyanyi;
    }

    public String getProduser() {
        return produser;
    }

    public String getLabel() {
        return label;
    }

    public int getTahun() {
        return tahun;
    }
}
