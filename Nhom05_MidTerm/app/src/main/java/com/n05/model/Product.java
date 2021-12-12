package com.n05.model;

public class Product {
    private int imvThumb;
    private int txtMaSP;
    private String txtTenSP;
    private String txtHSX;
    private double txtGia;

    public Product(int imvThumb, int txtMaSP, String txtTenSP, String txtHSX, double txtGia) {
        this.imvThumb = imvThumb;
        this.txtMaSP = txtMaSP;
        this.txtTenSP = txtTenSP;
        this.txtHSX = txtHSX;
        this.txtGia = txtGia;
    }

    public int getImvThumb() {
        return imvThumb;
    }

    public void setImvThumb(int imvThumb) {
        this.imvThumb = imvThumb;
    }

    public int getTxtMaSP() {
        return txtMaSP;
    }

    public void setTxtMaSP(int txtMaSP) {
        this.txtMaSP = txtMaSP;
    }

    public String getTxtTenSP() {
        return txtTenSP;
    }

    public void setTxtTenSP(String txtTenSP) {
        this.txtTenSP = txtTenSP;
    }

    public String getTxtHSX() {
        return txtHSX;
    }

    public void setTxtHSX(String txtHSX) {
        this.txtHSX = txtHSX;
    }

    public double getTxtGia() {
        return txtGia;
    }

    public void setTxtGia(double txtGia) {
        this.txtGia = txtGia;
    }
}
