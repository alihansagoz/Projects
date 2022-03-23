import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class Main {
    public static final int AUAV_CAPACITY = 10;
    public static void main(String[] args) throws IOException {

        System.out.println("##MISSION FIREWALL INITIATED##");

        Map<String, Malware> malwareDB = XMLParser.parse(args[0]);
        MalwareScanner scanner = new MalwareScanner(malwareDB);
        scanner.scanFile(args[1]);
        System.out.println("##MISSION FIREWALL COMPLETED##");

        System.out.println("##MISSION NUKE'M INITIATED##");

        ArrayList<Integer> numberOfEnemiesArrivingPerHour = Util.readTroopsDeploymentSchedule(args[2]);
        DefenseAgainstEnemyTroops defense =  new DefenseAgainstEnemyTroops(numberOfEnemiesArrivingPerHour);
        OptimalEnemyDefenseSolution solution = defense.getOptimalDefenseSolutionDP();
        solution.printEnemyDefenseSolution(numberOfEnemiesArrivingPerHour);
        System.out.println("##MISSION NUKE'M COMPLETED##");

        System.out.println("##MISSION EXTERMINATE INITIATED##");

        ArrayList<Integer> bombWeights = Util.readBombWeights(args[3]);
        int maxNumberOfAvailableAUAVs = Util.readNumberOfAvailableAUAVs(args[3]);
        OptimalFinalDefenseGP finalDefense = new OptimalFinalDefenseGP(bombWeights);
        finalDefense.printFinalDefenseOutcome(maxNumberOfAvailableAUAVs, AUAV_CAPACITY);
        System.out.println("##MISSION EXTERMINATE COMPLETED##");
    }
}
