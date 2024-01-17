package com.task3a_b;

public class ComputerGame extends Product{
    private String gamePublisher;

    public ComputerGame(){

    }

    public ComputerGame(int id, String name, double price, String category, int volume, String pub){
        super(id, name, price, category, volume);
        gamePublisher = pub;
    }

    @Override
    public void display_product_info(){
        super.display_product_info();
        System.out.println("Game Publisher: "+getPublisher());
    }

    // Setter
    public void setPublisher(String pub){
        gamePublisher = pub;
    }

    // Getter
    public String getPublisher(){
        return gamePublisher;
    }
}
