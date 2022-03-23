import java.util.ArrayList;
import java.util.Arrays;

public abstract class Characters {
    private int defaultHP;
    private int HP;
    private int AP;
    private int maxMove;
    private final String Name;
    private boolean aBoolean;

    public boolean isaBoolean() {
        return aBoolean;
    }

    public abstract void attack();

    public abstract void checkandmove(String[] command);

    public void move(int x, int y) {
        if (this.findLocation()!=null) {

            int x1 = this.findLocation()[0];
            int y1 = this.findLocation()[1];
            Characters wherefromgo = Board.Board[x1][y1];
            boolean z = false;
            aBoolean = true;

            for (int[] ints : findAround()) {
                if (Arrays.equals(new int[]{x1 + x, y1 + y}, ints)) {
                    z = true;
                    break;
                }
            }
            if (z) {
                Characters wherewego = Board.Board[x1 + x][y1 + y];
                if (wherewego == null) {
                    Board.Board[x1][y1] = null;
                    Board.Board[x1 + x][y1 + y] = this;
                } else if (this instanceof Zorde && wherewego instanceof Calliance || this instanceof Calliance && wherewego instanceof Zorde) {
                    aBoolean = false;
                    if (wherewego.getHP() - this.getAP() > 0) {
                        wherewego.setHP(wherewego.getHP() - this.getAP());
                        if (wherefromgo.getHP() > wherewego.getHP()) {
                            wherefromgo.setHP(wherefromgo.getHP() - wherewego.getHP());
                            Board.Board[x1 + x][y1 + y] = wherefromgo;
                        } else {
                            wherewego.setHP(wherewego.getHP() - wherefromgo.getHP());
                        }
                    } else {
                        int x2 = wherewego.findLocation()[0];
                        int y2 = wherewego.findLocation()[1];
                        Board.Board[x2][y2] = this;
                    }
                    Board.Board[x1][y1] = null;
                }
            } else {
                throw new ArrayStoreException();
            }

        }
    }

    public int getDefaultHP() {
        return defaultHP;
    }

    public void setDefaultHP(int defaultHP) {
        this.defaultHP = defaultHP;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getAP() {
        return AP;
    }

    public void setAP(int AP) {
        this.AP = AP;
    }

    public int getMaxMove() {
        return maxMove;
    }

    public void setMaxMove(int maxMove) {
        this.maxMove = maxMove;
    }

    public String getName() {
        return Name;
    }


    public Characters(String name, String k1, String k2) {
        Name = name;
        Board.Board[Integer.parseInt(k1)][Integer.parseInt(k2)] = this;
    }

    @Override
    public String toString() {
        return Name;
    }

    public int[] findLocation() {
        for (int x = 0; x < Board.widght; x++) {
            for (int y = 0; y < Board.widght; y++) {
                if (Board.Board[x][y] == this) {
                    return new int[]{x, y};
                }
            }
        }
        return null;
    }

    public ArrayList<int[]> findAround() {

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

    public ArrayList<Characters> findAroundPeople(ArrayList<int[]> konumlar2) {
        ArrayList<Characters> aroundpeople = new ArrayList<>();
        for (int[] ints: konumlar2){
            aroundpeople.add(Board.Board[ints[0]][ints[1]]);
        }
        return aroundpeople;
    }

}
