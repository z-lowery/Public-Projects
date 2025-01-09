import java.util.*;
import java.io.*;

public class StreamDemo {
    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<String> lines = new ArrayList<>();
        Scanner fin = new Scanner(new File("groceryList.txt"));
        while(fin.hasNextLine()){
            lines.add(fin.nextLine());
        }

        int sum = lines.stream()
            .filter(line -> line.contains("fruit") )
            .map(line -> line.split(",") [2] )
            .map(string -> Integer.parseInt(string.trim()))
            .reduce(0,(runningTotal,nextValue) -> runningTotal + nextValue); // extra 0 fixed type mismatch

            fin.close();
            //.forEach( data -> System.out.println(data));
            System.out.println("sum: " + sum);
    }
}
