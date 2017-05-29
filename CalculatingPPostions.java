public class CalculatingPPostions {


private boolean[] positions;

private int[] pPositions;     // List of all the pPositions
private int lastKnownP;     //  For figureOutLastP(int i) - Given a numer i, what is the last known final P position?
private int distToP;     // Distance to the last known final P position
private double alpha;     // alpha for this object CHANGE
private int rec;

public int indexOfLastP = 2;     // everytime there's a P postion, this number is incremented.

public CalculatingPPostions (double a) {
        positions = new boolean[10000000];
        pPositions = new int[500];
        lastKnownP = 0;
        distToP = 0;
        alpha = a;
        calculate_by_window();
        final int firstP = indexOfLastP - 1; final int secondP = indexOfLastP - 2;
        rec = figureOutRecursion(firstP, secondP);
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

public String printRecursion() {
        final int firstP = indexOfLastP - 1; final int secondP = indexOfLastP - 2;
        int rec = figureOutRecursion(firstP, secondP);
        String ans = firstP + " " + secondP + " " + rec + " ->> P_n = P_n - 1 + P_ n - " + rec;
        return ans;
}

public int figureOutRecursion(int first, int second) {

        int findValue = pPositions[first] - pPositions[second];
        int recursion = 0;

        for (int a = 0; a < pPositions.length; a++) {
                if (pPositions[a]==findValue) {
                        recursion = a;
                }
        }

        return (first - recursion);
}

public void calculate_by_window() {
    pPositions[0] = 0;
    pPositions[1] = 1;

    int x = 1;
    int candidate = pPositions[1];
    int nextP = 2;

    for (int i = 1; i < 500; i++) { // how many terms will we find the windows for
        int current = pPositions[i];

        double lower = pPositions[i - 1] * alpha;
        double upper = pPositions[i] * alpha;

        candidate = pPositions[nextP - 1];

        while (candidate <= upper && nextP < 500) {
            pPositions[nextP] = candidate + current;
            candidate = pPositions[nextP];
            nextP++;
        }
    }
}

public int figureOutLastP(int i) {
        int answer = 0;
        for (int j = 0; j < i; j++) {
                if (positions[j]) { //if it's a P position, then mark it
                        answer = j;
                }
        }
        return answer;
}

public int[] getPPositions() {
        return pPositions;
}

public int getActualSize() {
        return indexOfLastP;
}

public int getRecursion() {
        return rec;
}

public void setAlpha(double a) {
        alpha = a;
}

}
