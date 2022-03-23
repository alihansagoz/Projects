import java.util.ArrayList;

public class Elf extends Calliance {
    private final int rangedAP;

    public int getRangedAP() {
        return rangedAP;
    }

    @Override
    public void checkandmove(String[] command) {


        if (command.length==getMaxMove()*2){
            int y=0;
            for (int x=1; x<getMaxMove()*2;x+=2){
                this.move(Integer.parseInt(command[x]),Integer.parseInt(command[y]));
                y+=2;
                if (x!=(getMaxMove()*2-1)){
                    attack();
                }
                else {
                    rangedAttack();
                }
            }
        }
        else {
            throw new ArithmeticException();
        }




    }

    public Elf(String name, String k1, String k2) {
        super(name,k1,k2);
        super.setAP(Constants.elfAP);
        super.setMaxMove(Constants.elfMaxMove);
        rangedAP=Constants.elfRangedAP;
        super.setHP(Constants.elfHP);
        super.setDefaultHP(Constants.elfHP);
    }

    public void rangedAttack(){
        if (isaBoolean()){
            for (Characters characters: findAroundPeople(findAround2())){
                if (characters instanceof Zorde){
                    int health = characters.getHP()-this.getRangedAP();
                    if (health>0){
                        characters.setHP(characters.getHP()-this.getRangedAP());
                    }
                    else {
                        int x1= characters.findLocation()[0];
                        int y1= characters.findLocation()[1];
                        Board.Board[x1][y1]=null;
                    }
                }
            }
        }
    }

    public ArrayList<int[]> findAround2() {

        int[] konum = this.findLocation();
        int x1 = konum[0];
        int y1 = konum[1];
        ArrayList<int[]> konumlar = new ArrayList<>();
        ArrayList<int[]> konumlar2 = new ArrayList<>();

        konumlar.add(new int[]{x1 - 1, y1});
        konumlar.add(new int[]{x1 - 1, y1 - 1});
        konumlar.add(new int[]{x1, y1 - 1});
        konumlar.add(new int[]{x1 + 1, y1});
        konumlar.add(new int[]{x1 + 1, y1 - 1});
        konumlar.add(new int[]{x1 + 1, y1 + 1});
        konumlar.add(new int[]{x1 - 1, y1 + 1});
        konumlar.add(new int[]{x1, y1 + 1});

        konumlar.add(new int[]{x1-2, y1});
        konumlar.add(new int[]{x1, y1-2});

        konumlar.add(new int[]{x1+2, y1});
        konumlar.add(new int[]{x1, y1+2});

        konumlar.add(new int[]{x1-2, y1-1});
        konumlar.add(new int[]{x1-1, y1-2});

        konumlar.add(new int[]{x1-2, y1-2});
        konumlar.add(new int[]{x1+2, y1+2});

        konumlar.add(new int[]{x1-2, y1+1});
        konumlar.add(new int[]{x1+1, y1-2});

        konumlar.add(new int[]{x1-2, y1+2});
        konumlar.add(new int[]{x1+2, y1-2});

        konumlar.add(new int[]{x1+2, y1+1});
        konumlar.add(new int[]{x1+1, y1+2});

        konumlar.add(new int[]{x1+2, y1-1});
        konumlar.add(new int[]{x1-1, y1+2});

        for (int[] ints: konumlar){
            int y=0;
            if ((ints[0]>=0 && ints[0]<Board.widght) && (ints[1]>=0 && ints[1]<Board.widght)){
                y=1;
            }
            if (y==1){
                konumlar2.add(new int[]{ints[0],ints[1]});
            }
        }
        return konumlar2;
    }

}
