import java.util.ArrayList;

public class Entropy {
    public static double calculateEntropy(ArrayList<ArrayList<String>> data) {
        double entropy = 0;

        int countY = 0, countN = 0;
        //System.out.println(data.size());
        for(int j = 0; j < data.size(); j++) {
            ArrayList<String> record = data.get(j);
            //System.out.println(record.get(3));
            if(record.get(3).equals("Y")) {

                countY++;
            }
            else if(record.get(3).equals("N")) {
                countN++;
            }
        }
        //System.out.println("No. of Y: " + countY);
        //System.out.println("No. of N: " + countN);

        double probabilityY = countY / (double)data.size();
        if(countY > 0) {
            entropy += -probabilityY * (Math.log(probabilityY) / Math.log(2));
        }
        double probabilityN = countN / (double)data.size();
        if(countN > 0) {
            entropy += -probabilityN * (Math.log(probabilityN) / Math.log(2));
        }

        return entropy;
    }

    public static double calculateGain(double rootEntropy, ArrayList<Double> subEntropies, ArrayList<Integer> setSizes, int data) {
        double gain = rootEntropy;

        for(int i = 0; i < subEntropies.size(); i++) {
            gain += -((setSizes.get(i) / (double)data) * subEntropies.get(i));
        }

        return gain;
    }
}
