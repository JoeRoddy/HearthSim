import java.util.ArrayList;

public class Game {
    private int damage, turn, mana, totalPower, numBurn, numOne, numTwo, numThree, unspentMana,totalDamage;
    private double averageDamage;
    private ArrayList<Card> gHand;

    private boolean turnOver = false;
    private Deck gDeck;

    public Game(Deck deck,int numSimulations) {
        this.gDeck = deck;
        for (int x = 0;x<numSimulations;x++){
            totalDamage+=play();
        }
        averageDamage = (double)totalDamage/(double)numSimulations;
    }

    public int play() {
        //returns damage done this game.
        damage = turn = mana = totalPower = numBurn = numOne = numTwo = numThree = unspentMana = 0;
        gHand = new ArrayList<Card>();
        gDeck.setCurrentDeck(gDeck.deckBuilder(gDeck.getDeckCode()));
        //^This line is convoluted, but essentially, it rebuilds the deck so we aren't working from the incomplete
        // deck from last game.
        gDeck.shuffle();
        drawOpener(3);
        turn1Brain();
        turn2Brain();
        turn3Brain();
        turn4Brain();
        turn5Brain();
        return damage;
    }

    public void turn1Brain() {
        turnUpkeep();
        playXDrop(cardTypes.ONEDROP, 1);
        turnOver = true;
    }

    public void turn2Brain() {
        turnUpkeep();
        if (playXDrop(cardTypes.TWODROP, 1) > 0) {//plays a 2-drop, if one is in hand
            return;
        }
        //If no 2-drop in hand, plays up to two 1-drops
        playXDrop(cardTypes.ONEDROP, 2);
    }

    public void turn3Brain() {
        turnUpkeep();
        if (numTwo >= 1 && numOne >= 1) { //if hand has 1 1-drop & 1 2-drop, plays both
            playXDrop(cardTypes.ONEDROP, 1);
            playXDrop(cardTypes.TWODROP, 1);
            return;
        } else if (numOne >= 3) {
            playXDrop(cardTypes.ONEDROP, 3);//Plays 3 1-drops
            return;
        } else if (numThree>0) {
            playXDrop(cardTypes.THREEDROP, 1); //Plays a 3-drop if you have it
            return;
        }
        playXDrop(cardTypes.TWODROP, 1);
        if (numOne>0) {
            playXDrop(cardTypes.ONEDROP, 2);
            return;
        }
        playXDrop(cardTypes.BURN, 1);
    }

    public void turn4Brain() {
        turnUpkeep();
        if (numTwo > 1) {
            playXDrop(cardTypes.TWODROP, 2);
            return;
        }
        if (numTwo == 1 && numOne >= 1) { //if hand has at least 1 1-drop & 1 2-drop
            playXDrop(cardTypes.TWODROP, 1);
            if (playXDrop(cardTypes.ONEDROP, 2) > 1) {
                return;
            }
        }
        playXDrop(cardTypes.THREEDROP, 1);
        if (numBurn > 1) {
            playXDrop(cardTypes.BURN, 1);
        } else {
            playXDrop(cardTypes.TWODROP, 1);
        }
        playXDrop(cardTypes.ONEDROP, 4);
        playXDrop(cardTypes.BURN, 1);
    }

    public void turn5Brain() {
        turnUpkeep();
        playXDrop(cardTypes.BURN, 1);
        playXDrop(cardTypes.THREEDROP, 1);
        unspentMana += (turn - mana);
    }

    public void playCard(Card cardPlayed, int cardIndex) {
        mana -= cardPlayed.getCost();
        totalPower += cardPlayed.getPower();
        damage += cardPlayed.getBurst();
        gHand.remove(cardIndex);

        handContentUpdater(cardPlayed,false);
    }


    public int playXDrop(cardTypes typeArg, int limit) {
        int numPlayed = 0;
        int cost;
        if (typeArg == cardTypes.ONEDROP) {
            cost = 1;
        } else if (typeArg == cardTypes.TWODROP) {
            cost = 2;
        } else {
            cost = 3;
        }
        if (cost > mana) {
            return 0;
        }
        for (int x = 0; x < gHand.size(); x++) {
            Card thisCard = gHand.get(x);
            if (thisCard.getType() == typeArg && mana >= cost) {
                playCard(thisCard, x);
                numPlayed++;
                if (numPlayed == limit) {
                    if (mana == 0) {
                        turnOver = true;
                    }
                    return numPlayed;
                }
                x--;
            }
        }
        return numPlayed;
    }

    public void drawOpener(int handSize) {
        for (int x = 0; x < 3; x++) {
            Card cardDrawn = gDeck.draw();
            gHand.add(cardDrawn);
            handContentUpdater(cardDrawn,true);
        }
    }
  

    public void turnUpkeep() {
        unspentMana += (turn - mana);
        turn++;
        mana = turn;
        gHand.add(gDeck.draw());
        Card drawn = gHand.get(gHand.size() - 1);
        handContentUpdater(drawn,true);
        damage += totalPower;
        turnOver = false;
    }


    public void handContentUpdater(Card cardArg, boolean added) {
        if (cardArg.getType().equals(cardTypes.ONEDROP)) {
            if (added) {
                numOne++;
            } else {
                numOne--;
            }
            return;
        } else if (cardArg.getType().equals(cardTypes.TWODROP)) {
            if (added) {
                numTwo++;
            } else {
                numTwo--;
            }
            return;
        } else if (cardArg.getType().equals(cardTypes.THREEDROP)) {
            if (added) {
                numThree++;
            } else {
                numThree--;
            }
            return;
        } else if (cardArg.getType().equals(cardTypes.BURN)) {
            if (added) {
                numBurn++;
            } else {
                numBurn--;
            }
            return;
        }
        System.out.println("Error in handContentUpdater().  Card passed w/ incorrect cardType property");
    }

    public double getAverageDamage() {
        return averageDamage;
    }
}


