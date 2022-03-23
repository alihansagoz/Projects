import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class OptimalFinalDefenseGP
{
    private ArrayList<Integer> bombWeights;

    public OptimalFinalDefenseGP(ArrayList<Integer> bombWeights) {
        this.bombWeights = bombWeights;
    }

    public ArrayList<Integer> getBombWeights() {
        return bombWeights;
    }

    public int getMinNumberOfAUAVsToDeploy(int maxNumberOfAvailableAUAVs, int maxAUAVCapacity)
    {
        int numberofAUVS = 0;
        bombWeights.sort(Collections.reverseOrder());
        int[] remainingAUAVs =  new int[maxNumberOfAvailableAUAVs];
        Arrays.fill(remainingAUAVs, 0);
        boolean filled;
        if (bombWeights.get(0)<=maxAUAVCapacity){
            remainingAUAVs[0] = bombWeights.get(0);
        }
        else {
            return -1;
        }
        for (int i = 1; i<bombWeights.size();i++){
            filled = false;
            for (int j = 0 ; j<=i; j++){
                if (j>remainingAUAVs.length-1){
                    return -1;
                }
                if (remainingAUAVs[j]+bombWeights.get(i)<=maxAUAVCapacity){
                    remainingAUAVs[j] += bombWeights.get(i);
                    j = i+1;
                    filled = true;
                }
            }
            if (!filled){
                return -1;
            }
        }
        for (int remainingAUAV : remainingAUAVs) {
            if (remainingAUAV != 0) {
                numberofAUVS++;
            } else {
                break;
            }
        }
        return numberofAUVS;
    }
    public void printFinalDefenseOutcome(int maxNumberOfAvailableAUAVs, int AUAV_CAPACITY){
        int minNumberOfAUAVsToDeploy = this.getMinNumberOfAUAVsToDeploy(maxNumberOfAvailableAUAVs, AUAV_CAPACITY);
        if(minNumberOfAUAVsToDeploy!=-1) {
            System.out.println("The minimum number of AUAVs to deploy for complete extermination of the enemy army: " + minNumberOfAUAVsToDeploy);
        }
        else{
            System.out.println("We cannot load all the bombs. We are doomed.");
        }
    }
}
