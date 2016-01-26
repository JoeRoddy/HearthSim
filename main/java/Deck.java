/**
 * Created by Joe on 1/16/16.
 */

import java.util.ArrayList;
import java.util.Random;

public class Deck {
    private ArrayList<Card> currentDeck;
    private String name = "";

    public Deck(String name, ArrayList<Card> currentDeck) {
        this.name = name;
        this.currentDeck = currentDeck;
    }

    public void shuffle() {
        int index;
        Card temp;
        Random random = new Random();
        for (int i = currentDeck.size() - 1; i > 0; i--) {
            index = random.nextInt(i + 1);
            temp = currentDeck.get(index);
            currentDeck.set(index,currentDeck.get(i));
            currentDeck.set(i,temp);
        }
        System.out.println("Deck shuffled");
    }

    public static ArrayList<Card> deckBuilder(String deckCode){
        int numLep=Integer.parseInt(deckCode.substring(0,2));
        int numMH=Integer.parseInt(deckCode.substring(3,5));
        int numWolf=Integer.parseInt(deckCode.substring(6,8));
        int numKill=Integer.parseInt(deckCode.substring(9));
        ArrayList<Card> builtDeck = new ArrayList<Card>();
        for(int x=0;x<numLep;x++){ builtDeck.add(HearthSim.leper);}
        for(int x=0;x<numMH;x++){ builtDeck.add(HearthSim.millhouse);};
        for(int x=0;x<numWolf;x++){ builtDeck.add(HearthSim.wolfrider);}
        for(int x=0;x<numKill;x++){ builtDeck.add(HearthSim.kill);}
        return builtDeck;
    }

    public ArrayList<Card> getCurrentDeck() {
        return this.currentDeck;
    }

    public String getName() {
        return this.name;
    }

    public void setCurrentDeck(ArrayList<Card> currentDeck) {
        this.currentDeck = currentDeck;
    }
}
