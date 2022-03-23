public class Ork extends Zorde {
    private final int HealPoints;


    @Override
    public void checkandmove(String[] command) {
        if (command.length==getMaxMove()*2){
            heal();
            this.move(Integer.parseInt(command[1]),Integer.parseInt(command[0]));
            attack();
        }
        else {
            throw new ArithmeticException();
        }
    }

    public Ork(String name, String k1, String k2) {
        super(name,k1,k2);
        HealPoints= Constants.orkHealPoints;
        super.setAP(Constants.orkAP);
        super.setMaxMove(Constants.orkMaxMove);
        super.setHP(Constants.orkHP);
        super.setDefaultHP(Constants.orkHP);
    }

    public void heal(){
        for (int[] ints: findAround()){
            if (Board.Board[ints[0]][ints[1]] instanceof Zorde){
                Characters character = Board.Board[ints[0]][ints[1]];
                int afterheal=character.getHP()+HealPoints;
                if (afterheal<=character.getDefaultHP()){
                    character.setHP(character.getHP()+HealPoints);
                }
                else {
                    character.setHP(character.getDefaultHP());
                }
            }
        }
        if (getHP()<getDefaultHP()) {
            setHP(getHP() + HealPoints);
        }

    }

}
