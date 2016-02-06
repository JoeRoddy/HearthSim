import java.util.ArrayList;

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
}
