public class CalculatingPPostions {

    // MAKING A CHANGE TO SEE IF I UNDERSTAND HOW GITHUBS WORKS LOL

    private boolean[] positions; // What is each number?
    private int[] pPositions; // List of all the pPositions
    private int lastKnownP; //  For figureOutLastP(int i) - Given a numer i, what is the last known final P position?
    private int distToP; // Distance to the last known final P position
    private double alpha; // alpha for this object

    private int indexOfLastP = 2; // everytime there's a P postion, this number is incremented.

    public CalculatingPPostions (double a) {
        positions = new boolean[10000000];
        pPositions = new int[500];
        lastKnownP = 0;
        distToP = 0;
        alpha = a;
        calculatePositions();
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
                    positions[i] = true; //the we know it's gotta be a P POSITION
                    indexOfLastP++;
                    pPositions[indexOfLastP] = i;
                }
                else {
                    positions[i] = false; //N POSITION
                }
            }
        }

        // //List out P POSITIONS and put them in a new array.
        // for (int x = 0; x < positions.length; x++) {
        //     if (positions[x]) {
        //         pPositions[actualSize++] = x;
        //     }
        // }

    }

    public String printRecursion() {
        final int firstP = indexOfLastP - 1; final int secondP = indexOfLastP - 2;
        int rec = figureOutRecursion(firstP, secondP);
        String ans = firstP + " " + secondP + " " + rec + " ->> n = P_n - 1 + P_ n - " + (firstP - rec);
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

        return recursion;
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
        int first = indexOfLastP - 1;
        int second = indexOfLastP - 2;
        int rec = figureOutRecursion(first, second);
        return rec;
    }

}
