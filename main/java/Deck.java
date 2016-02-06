import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class Deck {
    private ArrayList<Card> currentDeck;
    private String deckCode = "";
    private double avgDmg;

    public Deck(int[] deckArg){
        this.deckCode = generateDeckCode(deckArg);
        this.currentDeck = deckBuilder(deckArg);
        Game simulations = new Game(this,10000);
        this.avgDmg=simulations.getAverageDamage();
    }

    public Deck(String deckCode) {
        this.deckCode = deckCode;
        this.currentDeck = deckBuilder(deckCode);
        Game simulations = new Game(this,10000);
        this.avgDmg=simulations.getAverageDamage();
    }

    public ArrayList<Card> deckBuilder(int[] deckContents){
        int numLep = deckContents[0];
        int numWG = deckContents[1];
        int numWolf = deckContents[2];
        int numKill = deckContents[3];
        ArrayList<Card> builtDeck = new ArrayList<Card>();
        for (int x = 0; x < numLep; x++) {
            builtDeck.add(HearthSim.leper);
        }
        for (int x = 0; x < numWG; x++) {
            builtDeck.add(HearthSim.wrathguard);
        }
        for (int x = 0; x < numWolf; x++) {
            builtDeck.add(HearthSim.wolfrider);
        }
        for (int x = 0; x < numKill; x++) {
            builtDeck.add(HearthSim.kill);
        }
        return builtDeck;
    }

    public ArrayList<Card> deckBuilder(String deckCode) {
        //ie:  String name = "09-09-09-03" : 9 1-drops, 9 2-drops, 9 3-drops, 3 burn

        int numLep = Integer.parseInt(deckCode.substring(0, 2));
        int numWG = Integer.parseInt(deckCode.substring(3, 5));
        int numWolf = Integer.parseInt(deckCode.substring(6, 8));
        int numKill = Integer.parseInt(deckCode.substring(9));
        ArrayList<Card> builtDeck = new ArrayList<Card>();
        for (int x = 0; x < numLep; x++) {
            builtDeck.add(HearthSim.leper);
        }
        for (int x = 0; x < numWG; x++) {
            builtDeck.add(HearthSim.wrathguard);
        }
        for (int x = 0; x < numWolf; x++) {
            builtDeck.add(HearthSim.wolfrider);
        }
        for (int x = 0; x < numKill; x++) {
            builtDeck.add(HearthSim.kill);
        }
        return builtDeck;
    }

    public void shuffle() {
        int index;
        Card temp;
        Random random = new Random();
        for (int i = currentDeck.size() - 1; i > 0; i--) {
            index = random.nextInt(i + 1);
            temp = currentDeck.get(index);
            currentDeck.set(index, currentDeck.get(i));
            currentDeck.set(i, temp);
        }
    }

    public Card draw() {
        //gets the top Card in the deck, removes it from the deck, and returns that Card object.
        int lastPosition = currentDeck.size() - 1;
        Card cardToDraw = currentDeck.get(lastPosition);
        currentDeck.remove(lastPosition);
        return cardToDraw;
    }

    public static String generateDeckCode(int[] deckArg){
        String codeToReturn="";
        for(int x=0;x<deckArg.length;x++){
            if(deckArg[x]<10){
                codeToReturn+="0"+deckArg[x];
            }else {
                codeToReturn+=deckArg[x];
            }
            if(x<deckArg.length-1){
                codeToReturn+="-";
            }
        }

        return codeToReturn;
    }

    public String getDeckCode() {
        return deckCode;
    }

    public double getAvgDmg() {
        return avgDmg;
    }

    public void setCurrentDeck(ArrayList<Card> currentDeck) {
        this.currentDeck = currentDeck;
    }
}
