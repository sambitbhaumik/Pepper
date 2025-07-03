import java.io.*;
import java.util.*;
class readCom
{
    public static void main(String[] args)
    {
        ArrayList<String> result = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("corpus.txt"))) {
            while (br.ready()) {
                result.add(br.readLine());
            }
        }
    }
}