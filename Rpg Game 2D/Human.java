public class Human extends Calliance{
    @Override
    public void checkandmove(String[] command) {
        if (command.length==getMaxMove()*2){
            int y=0;
            for (int x=1; x<getMaxMove()*2;x+=2){
                this.move(Integer.parseInt(command[x]),Integer.parseInt(command[y]));
                y+=2;
            }
            attack();
        }
        else {
            throw new ArithmeticException();
        }
    }

    public Human(String name, String k1, String k2) {
        super(name,k1,k2);
        super.setAP(Constants.humanAP);
        super.setMaxMove(Constants.humanMaxMove);
        super.setHP(Constants.humanHP);
        super.setDefaultHP(Constants.humanHP);

    }
}
