public class CalculatingPPostions {

    // MAKING A CHANGE TO SEE IF I UNDERSTAND HOW GITHUBS WORKS LOL

    private boolean[] positions; // What is each number?
    private int[] pPositions; // List of all the pPositions
    private int lastKnownP; //  For figureOutLastP(int i) - Given a numer i, what is the last known final P position?
    private int distToP; // Distance to the last known final P position
    private double alpha; // alpha for this object CHANGE
    private int rec;

    private int indexOfLastP = 2; // everytime there's a P postion, this number is incremented.

    public CalculatingPPostions (double a) {
        positions = new boolean[10000000];
        pPositions = new int[500];
        lastKnownP = 0;
        distToP = 0;
        alpha = a;
        calculatePositions();
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

    public boolean isSame (CalculatingPPostions obj) {
        if (this.pPositions[25] == obj.getPPositions()[25]) {
            return true;
        }
        return false;
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

    /**
    \begin{algorithm}
    \caption{Determining P Positions}\label{euclid}
    \begin{algorithmic}[1]
    \State $\textit{stringlen} \gets \text{length of }\textit{string}$
    \Procedure{calculatepositions()}{}
    \State $\textit{stringlen} \gets \text{length of }\textit{string}$
    \State $i \gets \textit{patlen}$
    \BState \emph{top}:
    \If {$i > \textit{stringlen}$} \Return false
    \EndIf
    \State $j \gets \textit{patlen}$
    \BState \emph{loop}:
    \If {$\textit{string}(i) = \textit{path}(j)$}
    \State $j \gets j-1$.
    \State $i \gets i-1$.
    \State \textbf{goto} \emph{loop}.
    \State \textbf{close};
    \EndIf
    \State $i \gets i+\max(\textit{delta}_1(\textit{string}(i)),\textit{delta}_2(j))$.
    \State \textbf{goto} \emph{top}.
    \EndProcedure
    \end{algorithmic}
    \end{algorithm}

    **/

}
