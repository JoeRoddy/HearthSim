public class HearthSim {
    static Card leper = new Card(cardTypes.ONEDROP, 1, 2, 0);
    static Card wrathguard = new Card(cardTypes.TWODROP, 2, 4, 0);
    static Card wolfrider = new Card(cardTypes.THREEDROP, 3, 3, 3);
    static Card kill = new Card(cardTypes.BURN, 3, 0, 5);

    public static void main(String[] args) {
        Deck toTest = new Deck("15-09-04-02");
        Game testGames = new Game(toTest,10000);
        System.out.println("Average dmg for our tests:"+testGames.getAverageDamage());
    }

}
