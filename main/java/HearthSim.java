import java.io.BufferedWriter;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;

public class HearthSim {
    static Card leper = new Card(cardTypes.ONEDROP, 1, 2, 0);
    static Card wrathguard = new Card(cardTypes.TWODROP, 2, 4, 0);
    static Card wolfrider = new Card(cardTypes.THREEDROP, 3, 3, 3);
    static Card kill = new Card(cardTypes.BURN, 3, 0, 5);

    public static void main(String[] args) {
        ArrayList<int[]> allDeckCombinations = generateDeckCombinations(30);
        ArrayList<Deck> results = new ArrayList<Deck>();
        for(int[] deckCombo : allDeckCombinations){
            Deck currentDeck = new Deck(deckCombo);
            addByDamage(currentDeck,results);
        }
        appendToSimulationResults("Deck Code   -  Average T5 Damage");
        for(Deck deck : results){
            appendToSimulationResults(deck.getDeckCode()+" - Avg. Dmg. : "+deck.getAvgDmg());
        }

    }

    public static ArrayList<int[]> generateDeckCombinations(int n){
        //for every card we add, we'd need an additional for-loop
        ArrayList<int[]> answer = new ArrayList<int[]>();
        for(int i=0;i<=n;i++){
            for(int j=0;j<=(n-i);j++){
                for(int k=0;k<=(n-i-j);k++){
                    int[] deck = {i,j,k,(n-i-j-k)};
                    answer.add(deck);
                }
            }
        }
        return answer;
    }
    public static void appendToSimulationResults(String stringToAdd){
        BufferedWriter bw = null;

        try {
            // APPEND MODE SET HERE
            bw = new BufferedWriter(new FileWriter("SimulationResults.txt", true));
            bw.write(stringToAdd);
            bw.newLine();
            bw.flush();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {                       // always close the file
            if (bw != null) try {
                bw.close();
            } catch (IOException ioe2) {
                // just ignore it
            }
        } // end try/catch/finally
    }
    public static ArrayList<Deck> addByDamage(Deck deckToAdd, ArrayList<Deck> deckList){
        if(deckList==null){deckList.add(deckToAdd); return deckList;}
        for(int x=0;x<deckList.size();x++){
            if(deckToAdd.getAvgDmg()>deckList.get(x).getAvgDmg()){
                deckList.add(x,deckToAdd); return deckList;
            }
        }
        //If lowest avg. damage, appends the deck to the end of the list and returns it.
        deckList.add(deckToAdd);
        return deckList;
    }
}
