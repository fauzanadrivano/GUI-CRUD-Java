package org.tugasbesar.demo;

/**
 *
 * @author Fauzan
 * @author Hafiz
 * @author Nadia
 * @version 16.0.1
 */
public class Film {
    private int id;
    private String judul;
    private String sutradara;
    private String aktor;
    private String label;
    private int tahun;
    
    public Film(int id, String judul, String sutradara, String aktor, String label, int tahun) {
        this.id = id;
        this.judul = judul;
        this.sutradara = sutradara;
        this.aktor = aktor;
        this.label = label;
        this.tahun = tahun;
    }
    
    
    public int getId() {
        return id;
    }
    
    public String getJudul() {
        return judul;
    }

    public String getSutradara() {
        return sutradara;
    }

    public String getAktor() {
        return aktor;
    }

    public String getLabel() {
        return label;
    }

    public int getTahun() {
        return tahun;
    }

}
