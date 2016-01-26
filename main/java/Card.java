/**
 * Created by Joe on 1/15/16.
 */
public class Card {
    private int cost, power, burst;
    private String name;

    public Card(String name, int cost, int power, int burst){
        this.name=name;
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
    public String getName(){ return this.name; }

}
