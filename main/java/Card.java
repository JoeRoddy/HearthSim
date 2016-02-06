public class Card {
    private int cost, power, burst;
    private cardTypes type;

    public Card(cardTypes type, int cost, int power, int burst){
        this.type=type;
        this.cost=cost;
        this.power=power;
        this.burst=burst;
    }

    public int getCost(){
        return this.cost;
    }
    public int getPower(){
        return this.power;
    }
    public int getBurst(){
        return this.burst;
    }
    public cardTypes getType() {return type;}
}
enum cardTypes{
    ONEDROP,TWODROP,THREEDROP,BURN
}
