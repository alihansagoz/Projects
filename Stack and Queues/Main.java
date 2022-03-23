import java.io.*;
import java.util.*;

public class Main {
    public static int kalansayi = -1;
    public static ArrayList<Stack> stacks = new ArrayList<>();
    public static Queue queue = new Queue();

    public static void write(String var0, File var1) throws IOException {
        FileWriter var2 = new FileWriter(var1,true);
        var2.append(var0);
        var2.close();
    }

    public static Stack findstack(String s){
        for (Stack s1: stacks){
            if (s1.toString().equals(s)){
                return s1;
            }
        }
        return null;
    }
    public static String writex(){
        String s = "";
        for (Stack s1: stacks){
            Collections.reverse(s1);
            s+=(s1+":"+"\n");
            for (Object i1: s1){
                s+=(i1.toString()+"\n");
            }
            s+=("---------------\n");
            Collections.reverse(s1);
        }
        return s;
    }
    public static String writey(){
        String s = "";
        s+="Token Box:\n";
        Collections.reverse(queue);
        for (Object object: queue){
            s+=(((Token)object).getName() +" " + ((Token)object).getType() +" " + ((Token)object).getNumber()+"\n");
        }
        Collections.reverse(queue);
        return s;
    }
    public static void removezeros(){
        ArrayList<Object> arrayList = new ArrayList();
        for (int i=0 ; i<queue.size();i++){
            int z = ((Token) queue.get(i)).getNumber();
            if (z ==0){
                arrayList.add(queue.get(i));
            }
        }
        for (Object x: arrayList){
            queue.remove(x);
        }
    }

    public static void main(String[] args) throws IOException {
        File filewriter = new File(args[4]);
        FileWriter writer = new FileWriter(filewriter);
        writer.close();

        Scanner scanner = new Scanner(new FileReader(args[0]));
        while (scanner.hasNextLine()) {
            String part_info = scanner.nextLine();
            new Stack(part_info);
        }
        Scanner scanner2 = new Scanner(new FileReader(args[1]));
        while (scanner2.hasNextLine()) {
            String item_info = scanner2.nextLine();
            String[] iteminfo = item_info.split(" ");
            new Item(iteminfo[0],iteminfo[1]);
        }
        Scanner scanner4 = new Scanner(new FileReader(args[2]));
        while (scanner4.hasNextLine()) {
            String token_info = scanner4.nextLine();
            String[] tokeninfo = token_info.split(" ");
            new Token(tokeninfo[0],tokeninfo[1],Integer.parseInt(tokeninfo[2]));
        }
        Collections.sort(queue, new Comparator<Token>() {
            @Override
            public int compare(Token o1, Token o2) {
                return o2.getNumber() - o1.getNumber();
            }
        });
        Scanner scanner3 = new Scanner(new FileReader(args[3]));
        while (scanner3.hasNextLine()) {
            String task_info = scanner3.nextLine();
            String[] taskinfo = task_info.split("\t");
            if (taskinfo[0].equals("PUT")){
                for (String s: taskinfo){
                    String[] items = s.split(",");
                    for (int i = 1; i< items.length;i++){
                        new Item(items[i],items[0]);
                    }
                }
            }
            else if (taskinfo[0].equals("BUY")) {
                for (String s: taskinfo){
                    String[] itemandnumber = s.split(",");
                    if (!itemandnumber[0].equals("BUY")) {
                        for (Stack X : stacks) {
                            X.buy(itemandnumber[0], Integer.parseInt(itemandnumber[1]));
                        }
                        Collections.sort(queue, new Comparator<Token>() {
                            @Override
                            public int compare(Token o1, Token o2) {
                                return o2.getNumber() - o1.getNumber();
                            }
                        });
                    }

                }
            }
        }
        removezeros();
        write(writex(),filewriter);
        write(writey(),filewriter);

    }
}
