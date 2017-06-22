public class CalculatingPPostions {


private boolean[] positions;

private int[] pPositions;     // List of all the pPositions
private int lastKnownP;     //  For figureOutLastP(int i) - Given a numer i, what is the last known final P position?
private int distToP;     // Distance to the last known final P position
private double alpha;     // alpha for this object CHANGE
public int rec;

public int indexOfLastP = 2;     // everytime there's a P postion, this number is incremented.
final static public int NUM_RECORDED = 1000000;

public CalculatingPPostions (double a) {
        positions = new boolean[10];
        pPositions = new int[NUM_RECORDED];
        lastKnownP = 0;
        distToP = 0;
        alpha = a;
        calculate_by_window();
        int rec_alpha = (int) alpha;
        rec = figureOutRecursion(rec_alpha * 5,rec_alpha * 5 - 1);
}

public void calculatePositions() {

        positions[0] = true; // true = P POSITION
        positions[1] = true; // false = N POSITION
        positions[2] = true;

        pPositions[0] = 0;
        pPositions[1] = 1;
        pPositions[2] = 2;

        for (int i = 3; i < positions.length; i++) {

                lastKnownP = pPositions[indexOfLastP];
                distToP = i - lastKnownP; // distance to the last known P AKA what you plan to remove

                if (  alpha*distToP < (i - distToP) ) {
                        positions[i] = false; // it's a N POSITION
                }
                else {
                        if(positions[distToP]) { //if the distance to P is a P itself
                                positions[i] = true; //then we know it's gotta be a P POSITION
                                indexOfLastP++;
                                pPositions[indexOfLastP] = i;
                        }
                        else {
                                positions[i] = false; //N POSITION
                        }
                }
        }
}

public void calculate_by_window() {
    pPositions[0] = 0;
    pPositions[1] = 1;

    int candidate = pPositions[1];
    int nextP = 2;

    for (int i = 1; i < NUM_RECORDED; i++) { // how many terms will we find the windows for
        int current = pPositions[i];

        double lower = pPositions[i - 1] * alpha;
        double upper = pPositions[i] * alpha;

        candidate = pPositions[nextP - 1];

        while (candidate <= upper && nextP < NUM_RECORDED) {
            pPositions[nextP] = candidate + current;
            candidate = pPositions[nextP];
            nextP++;
        }
    }
}

// RECURSION DERIVING RELATED //

public double[] ratios() {
    double[] ratio_list = new double[50];

    return ratio_list;
}

////

public int figureOutRecursion(int first, int second) {
        
        int findValue = pPositions[first] - pPositions[second];
        int recursion = 0;

        for (int a = first; a > 0; a--) {
                if (pPositions[a]==findValue) {
                        recursion = a;
                }
        }

        return (first - recursion);
}

public String printRecursion() {
        int rec_alpha = (int) alpha;
        int rec = figureOutRecursion(rec_alpha * 5,rec_alpha * 5 - 1);
        String ans = (rec_alpha * 5) + " " + (rec_alpha * 5 - 1) + " " + (rec_alpha * 5 - rec) + " ->> P_n = P_n - 1 + P_ n - " + rec;
        return ans;
}

// RATIO RELATED



//

// SOME GETTER METHODS //

public int[] getPPositions() {
        return pPositions;
}

public int getRecursion() {
        return rec;
}

}
