import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {
    public static int characternumber;
    public static String winner;

    public static void write(String var0, File var1) throws IOException {
        FileWriter var2 = new FileWriter(var1,true);
        var2.append(var0);
        var2.close();
    }

    public static String printBoard() {
        characternumber=0;
        ArrayList<Characters> characterscopy = new ArrayList<>();
        String z = "";
        for (Characters[] characters: Board.Board){
            for (Characters characters1: characters){
                if (characters1!=null){
                    characterscopy.add(characters1);
                }
            }
        }
        characterscopy.sort(Comparator.comparing(Characters::getName));
        String star = "*";

        for (int i =0; i<Board.widght*2+2;i++){
            z+=star;
        }
        z+="\n";
        for (Characters[] characters: Board.Board){
            z+=star;
            for (Characters characters1: characters){
                if (characters1!=null){
                    z+=characters1;
                }
                else {
                    z+="  ";
                }
            }
            z+=star;
            z+="\n";
        }
        for (int i =0; i<Board.widght*2+2;i++){
            z+=star;
        }
        z+="\n\n";

        for (Characters characters: characterscopy){
            if (characters != null) {
                z+=characters+"\t"+characters.getHP()+"\t"+"("+characters.getDefaultHP()+")"+"\n";
                characternumber+=1;
            }
        }
        z+=("\n");
        for (Characters characters: characterscopy){
            if (characters instanceof Zorde){
                winner="Zorde";
            }
            else {
                winner="Calliance";
            }
        }

        return z;
    }

    public static Characters findCharacter(String name) {
        for (Characters[] characters : Board.Board) {
            for (Characters characters1 : characters) {
                if (characters1!=null){
                    if (characters1.getName().equals(name)) {
                        return characters1;
                    }
                }
            }
        }
        return null;
    }
    public static void main(String[] args) throws IOException {

        File filewriter = new File(args[2]);
        FileWriter writer = new FileWriter(filewriter);
        writer.close();

        Scanner scanner = new Scanner(new FileReader(args[0]));
        scanner.nextLine();
        String board_info = scanner.nextLine();
        String[] boardinfo = board_info.split("x");
        Board.widght = Integer.parseInt(boardinfo[0]);
        Board.Board = new Characters[Board.widght][Board.widght];
        while (scanner.hasNextLine()) {
            String initial_info = scanner.nextLine();
            String[] initialinfo = initial_info.split(" ");
            switch (initialinfo[0]) {
                case "ELF" :
                    new Elf(initialinfo[1],initialinfo[3],initialinfo[2]);
                    break;
                case "DWARF":
                    new Dwarf(initialinfo[1],initialinfo[3],initialinfo[2]);
                    break;
                case "HUMAN" :
                    new Human(initialinfo[1],initialinfo[3],initialinfo[2]);
                    break;
                case "GOBLIN" :
                    new Goblin(initialinfo[1],initialinfo[3],initialinfo[2]);
                    break;
                case "TROLL" :
                    new Troll(initialinfo[1],initialinfo[3],initialinfo[2]);
                    break;
                case "ORK" :
                    new Ork(initialinfo[1],initialinfo[3],initialinfo[2]);
                    break;
            }
        }
        Scanner scanner3 = new Scanner(new FileReader(args[1]));
        write(printBoard(),filewriter);
        while (scanner3.hasNextLine()) {
            String command_information = scanner3.nextLine();
            String[] Commandinfos = command_information.split(" ");
            String[] commands = Commandinfos[1].split(";");
            try {
                Objects.requireNonNull(findCharacter(Commandinfos[0])).checkandmove(commands);
                write(printBoard(),filewriter);
                if (characternumber == 1){
                    write("\nGame Finished\n",filewriter);
                    write(winner + " Wins",filewriter);
                    break;
                }
            }
            catch(ArithmeticException e){
                write("Error : Move sequence contains wrong number of move steps. Input line ignored.\n\n",filewriter);
            }
            catch (ArrayStoreException x){
                write("Error : Game board boundaries are exceeded. Input line ignored.\n\n",filewriter);
            }
        }
    }

}
