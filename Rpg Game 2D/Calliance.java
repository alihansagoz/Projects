public abstract class Calliance extends Characters {

    public Calliance(String name, String k1, String k2) {
        super(name, k1, k2);
    }

    public void attack(){
        if (isaBoolean()){
            for (Characters characters: findAroundPeople(findAround())){
                if (characters instanceof Zorde){
                    int health = characters.getHP()-this.getAP();
                    if (health>0){
                        characters.setHP(characters.getHP()-this.getAP());
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
}
