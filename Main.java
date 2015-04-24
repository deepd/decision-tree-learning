import java.util.ArrayList;

/**
 * Created by deep on 3/29/15.
 */
public class Main {
    public static int NUM_ATTRS = 4;
    public static ArrayList<String> attrMap;
    public static ArrayList<Integer> usedAttributes = new ArrayList<Integer>();

    public static String getLeafNames(int attributeNum, int valueNum) {
        if(attributeNum == 0) {
            if(valueNum == 0) {
                return "1";
            }
            else if(valueNum == 1) {
                return "2";
            }
            else if(valueNum == 2) {
                return "4";
            }
        }
        else if(attributeNum == 1) {
            if(valueNum == 0) {
                return "Male";
            }
            else if(valueNum == 1) {
                return "Female";
            }
        }
        else if(attributeNum == 2) {
            if(valueNum == 0) {
                return "Pomeranian";
            }
            else if(valueNum == 1) {
                return "Chihuahua";
            }
            else if(valueNum == 2) {
                return "AustralianShepherd";
            }
            else if(valueNum == 3) {
                return "PitBull";
            }
        }
        else if(attributeNum == 3) {
            if(valueNum == 0) {
                return "No";
            }
            else if(valueNum == 1) {
                return "Yes";
            }
        }

        return null;
    }

    public void populateAttrMap() {
        attrMap = new ArrayList<String>();
        attrMap.add("Age");
        attrMap.add("Sex");
        attrMap.add("Breed");
        attrMap.add("Adopt");
    }

    public static int getSetSize(String set) {
        if(set.equalsIgnoreCase("Adopt")) {
            return 2;
        }
        else if(set.equalsIgnoreCase("Breed")) {
            return 4;
        }
        else if(set.equalsIgnoreCase("Age")) {
            return 3;
        }
        else if(set.equalsIgnoreCase("Sex")) {
            return 2;
        }
        return 0;
    }

    public static boolean isAttributeUsed(int attribute) {
        if (usedAttributes.contains(attribute)) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        Main m = new Main();
        m.populateAttrMap();
        FileReader f = new FileReader();
        ArrayList<ArrayList<String>> learningset = f.buildAttributeSets();
        Tree t = new Tree();
        Node root = new Node();
        ArrayList<ArrayList<String>> temp = new ArrayList<ArrayList<String>>();
        for(ArrayList<String> record : learningset) {
            //root.getLearningSet().add(record);
            temp.add(record);
        }
        root.setLearningSet(temp);
        ArrayList<Integer> allowed = new ArrayList<Integer>();
        allowed.add(0);
        allowed.add(1);
        allowed.add(2);
        t.buildTree(root, learningset, 0, allowed);
        //System.out.println(root.children[0].getLearningSet());
        t.displayTree(root);
        return;
    }

    public static ArrayList<String> getSetValues(int i) {
        ArrayList<String> ret = new ArrayList<String>();
        if(i == 0) {
            ret.add("One");
            ret.add("Two");
            ret.add("Four");
        }
        else if(i == 1) {
            ret.add("Male");
            ret.add("Female");
        }
        else if(i == 2) {
            ret.add("Pomeranian");
            ret.add("Chihuahua");
            ret.add("AustralianShepherd");
            ret.add("PitBull");
        }
        return ret;
    }
}
