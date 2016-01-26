/**
 * Created by Joe on 1/16/16.
 */
public class GameState {
    private int damage = 0;
    private int turn = 0;
    private int mana = 0;
    private int totalPower = 0;
    private Hand gsHand;
    private Deck gsDeck;

    public GameState (Hand hand, Deck deck) {
        this.gsHand = hand;
        this.gsDeck = deck;
    }


    public void playCard(Card cardPlayed){
        mana-=cardPlayed.getCost();
        totalPower+=cardPlayed.getPower();
        damage+=cardPlayed.getBurst();
    }

    public int getMana() {
        return mana;
    }
    public void setMana(int passedMana)
        { this.mana = passedMana; }

    public int getDamage() {
        return damage;
    }

    public int getTotalPower() { return totalPower; }

    public void setTotalPower(int passedPower) {
        this.totalPower = passedPower;
    }

    public int getTurn() {
        return turn;
    }

    public String getName() {
        return this.gsDeck.getName();
    }


}
