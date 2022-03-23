import java.util.ArrayList;

public class DefenseAgainstEnemyTroops {
    private ArrayList<Integer> numberOfEnemiesArrivingPerHour;

    public DefenseAgainstEnemyTroops(ArrayList<Integer> numberOfEnemiesArrivingPerHour){
        this.numberOfEnemiesArrivingPerHour = numberOfEnemiesArrivingPerHour;
    }

    public ArrayList<Integer> getNumberOfEnemiesArrivingPerHour() {
        return numberOfEnemiesArrivingPerHour;
    }

    private int getRechargedWeaponPower(int hoursCharging){
        return hoursCharging*hoursCharging;
    }

    public OptimalEnemyDefenseSolution getOptimalDefenseSolutionDP(){
        ArrayList<Integer> P = new ArrayList<>();
        numberOfEnemiesArrivingPerHour.add(0, 0);
        for (int i = 0;i<numberOfEnemiesArrivingPerHour.size()+1; i++){
            P.add(i*i);
        }
        ArrayList<Integer> SOL = new ArrayList<>();
        for (int i = 0;i<numberOfEnemiesArrivingPerHour.size(); i++){
            SOL.add(0);
        }
        ArrayList<ArrayList<Integer>> HOURS = new ArrayList<>();

        for (int i = 0;i<numberOfEnemiesArrivingPerHour.size(); i++){
            HOURS.add(new ArrayList<>());
        }

        for (int j =0 ;j<numberOfEnemiesArrivingPerHour.size(); j++){
            for (int i = 0; i<j;i++){
                int min = Math.min(numberOfEnemiesArrivingPerHour.get(j),P.get(j-i));
                if (SOL.get(i)+min>SOL.get(j)){
                    SOL.set(j,SOL.get(i)+min);
                    ArrayList<Integer> temp = new ArrayList<>(HOURS.get(i));
                    temp.add(j);
                    HOURS.set(j,temp);
                }
            }
        }
        int maxKilled = SOL.get(numberOfEnemiesArrivingPerHour.size()-1);
        ArrayList<Integer> hoursToFireWeaponForMaxDestruction = new ArrayList<>(HOURS.get(numberOfEnemiesArrivingPerHour.size()-1));

        return new OptimalEnemyDefenseSolution(maxKilled,hoursToFireWeaponForMaxDestruction);
    }
}
