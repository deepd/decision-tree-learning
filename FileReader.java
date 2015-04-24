import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class FileReader {
    public static final String PATH_TO_DATA_FILE = "adopt.txt";

    public static ArrayList<ArrayList<String>> buildAttributeSets() {
        BufferedReader reader = null;
        ArrayList<ArrayList<String>> AttributeSets = new ArrayList<ArrayList<String>>();

        try {
            File f = new File(PATH_TO_DATA_FILE);
            FileInputStream fis = new FileInputStream(f);
            reader = new BufferedReader(new InputStreamReader(fis));

            // read the first AttributeSet of the file
            String line;
            ArrayList<String> attributes;
            while ((line = reader.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(line, ",");
                attributes = new ArrayList<String>();

                String age = st.nextToken();
                String sex = st.nextToken();
                String breed = st.nextToken();
                String adopt = st.nextToken();

                attributes.add(age);
                attributes.add(sex);
                attributes.add(breed);
                attributes.add(adopt);

                AttributeSets.add(attributes);
            }

        }
        catch (IOException e) {
            System.out.println("Uh oh, got an IOException error: " + e.getMessage());
        }
        catch (Exception e) {
            System.out.println("Uh oh, got an Exception error: " + e.getMessage());
        }
        return AttributeSets;
    }

    public static void main(String[] args) {
        ArrayList<ArrayList<String>> x = buildAttributeSets();
        for(ArrayList<String> s : x) {
            for(String str : s) {
                System.out.print(str + "\t");
            }
            System.out.println();
        }
    }
}
