import java.util.EmptyStackException;
import java.util.Vector;

public class Stack<E> extends Vector<E> {
    private String type;

    @Override
    public String toString() {
        return type;
    }

    public Stack(String s) {
        type=s;
        Main.stacks.add(this);
    }
    public E push(E item) {
        addElement(item);
        return item;
    }

    public synchronized E pop() {
        E       obj;
        int     len = size();

        obj = peek();
        removeElementAt(len - 1);

        return obj;
    }

    public synchronized E peek() {
        int     len = size();

        if (len == 0)
            throw new EmptyStackException();
        return elementAt(len - 1);
    }

    public void buy(String type,int number){
        if (type.equals(this.type)){
            for (Object x : Main.queue){
                ((Token) x).spend(type,number);
                if (Main.kalansayi==0){
                    break;
                }

            }
            for (int i = 0; i<number; i++){
                this.pop();
            }
        }
        Main.kalansayi=-1;
    }


}
