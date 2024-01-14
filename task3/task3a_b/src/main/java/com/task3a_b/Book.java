package com.task3a_b;

public class Book extends Product{
    private String bookIBAN;
    private String bookGenre;
    private String bookAuthor;

    public Book(){

    }

    public Book(int id, String name, double price, String category, int volume, String IBAN, String genre, String author){
        super(id, name, price, category, volume);
        bookIBAN = IBAN;
        bookGenre = genre;
        bookAuthor = author;
    }

    @Override
    public void display_product_info(){
        super.display_product_info();
        System.out.println("Book IBAN: "+getIBAN());
        System.out.println("Book Genre: "+getGenre());
        System.out.println("Book Author: "+getAuthor());
    }

    // Setters
    public void setIBAN(String IBAN){
        bookIBAN = IBAN;
    }

    public void setGenre(String genre){
        bookGenre = genre;
    }

    public void setAuthor(String author){
        bookAuthor = author;
    }

    // Getters
    public String getIBAN(){
        return bookIBAN;
    }

    public String getGenre(){
        return bookGenre;
    }
    
    public String getAuthor(){
        return bookAuthor;
    }
}
