import java.util.ArrayList;

/**
 * Created by Joe on 1/15/16.
 */
public class HearthSim {
    static Card leper = new Card("Leper Gnome", 1, 2, 0);
    static Card millhouse = new Card("Millhouse Manastorm", 2, 4, 0);
    static Card wolfrider = new Card("Wolfrider", 3, 3, 3);
    static Card kill = new Card("Kill Command", 3, 0, 5);

    public static void main(String[] args) {
        System.out.println(wolfrider.getCost());
        System.out.println(leper.getBurst());
        System.out.println(millhouse.getPower());

        String name = "09-09-09-03";
        ArrayList<Card> deckTest=Deck.deckBuilder(name);
        Deck deckToTest = new Deck(name,deckTest);

        for(Card x:deckTest){
            System.out.print(x.getName()+" ");}
        deckToTest.shuffle();
        System.out.println();
        for(Card x:deckTest){
            System.out.print(x.getName()+" ");}
    }

}
