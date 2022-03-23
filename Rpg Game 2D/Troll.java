public class Troll extends Zorde{
    @Override
    public void checkandmove(String[] command) {
        if (command.length==getMaxMove()*2){
            this.move(Integer.parseInt(command[1]),Integer.parseInt(command[0]));
            attack();
        }
        else {
            throw new ArithmeticException();
        }
    }

    public Troll(String name, String k1, String k2) {
        super(name,k1,k2);
        super.setAP(Constants.trollAP);
        super.setMaxMove(Constants.trollMaxMove);
        super.setHP(Constants.trollHP);
        super.setDefaultHP(Constants.trollHP);
    }
}
