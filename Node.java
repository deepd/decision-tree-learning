import java.util.ArrayList;

/**
 * Created by deep on 3/30/15.
 */
public class Node {
    public String name;
    public boolean isLeaf;
    public Node[] children;
    public Node parent;
    public double entropy;
    public String testAttribute;
    public ArrayList<ArrayList<String>> learningSet;
    public String incomingAttr;
    public double gain;
    public Node() {
        name = "";
        isLeaf = false;
        testAttribute = "";
        entropy = 0;
        incomingAttr = "";
        gain = 0;
    }
    public void setEntropy(double entropy) {
        this.entropy = entropy;
    }

    public double getEntropy() {
        return entropy;
    }

    public void setChildren(Node[] children) {
        this.children = children;
    }

    public Node[] getChildren() {
        return children;
    }

    public void setLearningSet(ArrayList<ArrayList<String>> data) {
        this.learningSet = data;
    }

    public ArrayList<ArrayList<String>> getLearningSet() {
        return learningSet;
    }

    public void setTestAttribute(String testAttribute) {
        this.testAttribute = testAttribute;
    }

    public String getTestAttribute() {
        return testAttribute;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node getParent() {
        return parent;
    }
}
