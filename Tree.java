
import java.util.*;

/**
 * Created by deep on 3/30/15.
 */
public class Tree {
    public Node buildTree(Node root, ArrayList<ArrayList<String>> data, int val, ArrayList<Integer> allowedAttributes) {
        if(data.size() == 0)
            return root;
        int bestAttribute = -1;
        double bestGain = 0;
        root.setEntropy(Entropy.calculateEntropy(root.getLearningSet()));
        //System.out.println(root.getEntropy());
        if(root.getEntropy() == 0) {
            //root.children = new Node[1];
            //root.children[0] = new Node();
            //root.children[0].setTestAttribute(data.get(0).get(3));
            //root.children[0].isLeaf = true;
            root.incomingAttr = Main.getLeafNames(Main.attrMap.indexOf(root.getParent().getTestAttribute()), val);
            root.setTestAttribute(data.get(0).get(3));
            return root;
        }
        while(true) {
            for (int i = 0; i < Main.NUM_ATTRS - 1; i++) {
                if (allowedAttributes.contains(i)) {
                    double entropy = 0;
                    ArrayList<Double> entropies = new ArrayList<Double>();
                    ArrayList<Integer> setSizes = new ArrayList<Integer>();

                    for (String j : Main.getSetValues(i)) {
                        ArrayList<ArrayList<String>> subset = subset(data, i, j);
                        setSizes.add(subset.size());

                        if (subset.size() != 0) {
                            entropy = Entropy.calculateEntropy(subset);
                            entropies.add(entropy);
                        }
                    }

                    double gain = Entropy.calculateGain(root.getEntropy(), entropies, setSizes, root.getLearningSet().size());

                    if (gain > bestGain) {
                        bestAttribute = i;
                        bestGain = gain;
                    }
                }
            }
            if (bestAttribute != -1) {
                //System.out.println(bestAttribute + " corresponds to : " + Main.attrMap.get(bestAttribute));
                int setSize = Main.getSetSize(Main.attrMap.get(bestAttribute));
                //System.out.println("setsize: " + setSize);
                root.setTestAttribute(Main.attrMap.get(bestAttribute));
                root.gain = bestGain;
                root.children = new Node[setSize];
                //Main.usedAttributes.add(bestAttribute);
                int index = 0;
                //System.out.println(Main.getSetValues(bestAttribute));
                for (String j : Main.getSetValues(bestAttribute)) {
                    root.children[index] = new Node();
                    root.children[index].setParent(root);
                    root.children[index].setLearningSet(subset(data, bestAttribute, j));
                    root.children[index].incomingAttr = j;
                    //root.children[index].setTestAttribute(Main.attrMap.get(i));
                    index++;
                }
                //int ind = allowedAttributes.indexOf(bestAttribute);
                ArrayList<Integer> allowedAttributesChild = new ArrayList<Integer>(allowedAttributes);
                allowedAttributesChild.remove(new Integer(bestAttribute));
                for (int j = 0; j < setSize; j++) {
                    buildTree(root.children[j], root.children[j].getLearningSet(), j, allowedAttributesChild);
                }

            }
            break;

        }
        return root;
    }

    private ArrayList<ArrayList<String>> subset(ArrayList<ArrayList<String>> data, int attr, String j) {
        //System.out.println(Main.attrMap.get(attr)+" , "+j);
        ArrayList<ArrayList<String>> subset = new ArrayList<ArrayList<String>>();

        for(int i = 0; i < data.size(); i++) {
            ArrayList<String> record = data.get(i);

            if(record.get(attr).equals(j)) {
                subset.add(record);
            }
        }
        //System.out.println(subset);
        return subset;
    }

    public void displayTree(Node root) {
        ArrayList<ArrayList<Node>> result = new ArrayList<ArrayList<Node>>();
        levelTraverse(root, result, 0);
        for(ArrayList<Node> arr : result) {
            for(Node node : arr) {
                if(node != null)
                    System.out.print("("+node.incomingAttr+")"+node.getTestAttribute()+"\t");
                else
                    System.out.print(node+"\t");
            }
            System.out.println();
        }
    }

    private void levelTraverse(Node root, ArrayList<ArrayList<Node>> result, int level) {
        if(result.size() == level) {
            result.add(new ArrayList<Node>());
        }
        result.get(level).add(root);
        if(root == null) {
            return;
        }
        if(root.children != null) {
            for (Node child : root.children) {
                levelTraverse(child, result, level + 1);
            }
        }
        else {
            levelTraverse(null, result, level+1);
        }
    }
}
