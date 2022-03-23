public class Token implements Comparable<Token>{
    private String name;
    private String type;
    private int number;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Token(String name, String type,int number) {
        this.name = name;
        this.type = type;
        this.number = number;
        Main.queue.push(this);
    }

    @Override
    public int compareTo(Token o) {
        return 0;
    }

    public void spend(String type, int sayi){
        if (type.equals(this.type)) {
            if (this.getNumber() - sayi <= 0) {
                Main.kalansayi = -(this.getNumber() - sayi);
                this.number = 0;
            }
            else {
                Main.kalansayi=0;
                this.number -= sayi;
            }
        }
    }
}
