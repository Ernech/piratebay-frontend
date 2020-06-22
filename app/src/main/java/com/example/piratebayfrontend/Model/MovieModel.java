package com.example.piratebayfrontend.Model;


public class MovieModel {
    int idMovie;
    String title;
    String format;
    String creationDate;
    String supplier;
    int quantity;

    public MovieModel(int idMovie, String title, String format, String creationDate, String supplier, int quantity) {
        this.idMovie = idMovie;
        this.title = title;
        this.format = format;
        this.creationDate = creationDate;
        this.supplier = supplier;
        this.quantity = quantity;
    }

    public MovieModel() {
    }

    public int getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(int idMovie) {
        this.idMovie = idMovie;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
