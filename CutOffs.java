import java.util.Scanner;

public class CutOffs {
    public static void main(String[] args) {

        Scanner menu = new Scanner(System.in);
        System.out.println("hi pick one of the choices.");
        System.out.println("1. printPpositions()");
        System.out.println("2. determineSequences(lower, upper)");
        System.out.println("3. testHypothesis");
        System.out.println("4. generateCutoffs");
        System.out.println();

        int choice = menu.nextInt();

        if (choice == 1) {
            printPpositions();
        } else if (choice == 2) {

            System.out.println("Lower Bound: ");
            double lower = menu.nextDouble();
            System.out.println("Upper Bound: ");
            double upper = menu.nextDouble();
            System.out.println();

            double[] cutoffs = determineSequences(lower, upper);

            for (int i = 0; i < cutoffs.length; i++) {
                System.out.println(cutoffs[i]);
            }

        } else if (choice == 3) {
            CalculatingPPostions obj;
            double hypothesis;
            double prevHypothesis = 0;

            for (double x = 6.0; x < 9.0; x = x + 0.1) {
                obj = new CalculatingPPostions(x);
                hypothesis = testHypothesis(obj);
                if (hypothesis != prevHypothesis) {
                    if (isCutoff(hypothesis)) {
                        System.out.println("SUCCESS" + hypothesis);
                    } else {
                        System.out.println("ANAMOLY DETECTED: " + hypothesis + "   " + x);
                    }
                }
                prevHypothesis = hypothesis;
            }

        } else if (choice == 4) {
            double cutoff_hyp = generateCutoffs(5);
            boolean bool_cutoff;
            for (int i = 0; i < 100; i++) {
                bool_cutoff = isCutoff(cutoff_hyp);
                System.out.println(cutoff_hyp + " " + bool_cutoff);
                cutoff_hyp = generateCutoffs(cutoff_hyp);
            }
        }

    }

    public static void printPpositions() {
        Scanner scan = new Scanner(System.in);
        double alpha = 1;
        while(scan.hasNextDouble()) {
            alpha = scan.nextDouble();
            CalculatingPPostions obj = new CalculatingPPostions(alpha);
            //System.out.println(obj.printRecursion());

             for (int i = 1; i < 30; i++) {
                  System.out.print(obj.getPPositions()[i] + ",  ");
              }

            System.out.println();
            System.out.println(obj.printRecursion());
        }
    }

    /**
    * Given an interval of a recursive sequence, (for example, the interval for
    * the sequence P_n = P_n-1 + P_n-7 is 4.333 < alpha <= 4.666), this method
    * will find the secondary CutOffs within this interval.
    **/
    public static double[] determineSequences (double lower, double upper) {

        double[] cutoffs = new double[5];
        CalculatingPPostions tester = new CalculatingPPostions(lower);
        int previous = tester.getPPositions()[20];
        int counter = 0;

        for (double i = lower + 0.001; i <= upper; i = i + 0.001 ) {
            tester = new CalculatingPPostions(i);

            if (tester.getPPositions()[20] != previous) {
                cutoffs[counter] = i;
                counter++;
            }

            //System.out.println(previous + " , " + tester.getPPositions()[20] );
            previous = tester.getPPositions()[20];
        }

        return cutoffs;

    }

    /*
    * The hypothesis was that the cutoffs were determined by the first time
    * a sequence of P positions followed a steady recurrence. As it turns out,
    * this hypothesis was FALSE.
    */
    public static double testHypothesis(CalculatingPPostions obj) {
        int rec = obj.getRecursion() - 1;

        for (int i = 0; i < obj.getPPositions().length - rec - 1; i++) {
            if (obj.getPPositions()[i] + obj.getPPositions()[i + rec] == obj.getPPositions()[i + rec + 1]) {
                return (double) (obj.getPPositions()[i + rec]) / obj.getPPositions()[i];  //obj.getPPositions()[i]
            }
        }
        return 0;
    }

    public static boolean isCutoff(double alpha) {
        CalculatingPPostions obj = new CalculatingPPostions(alpha);

        double lower = alpha - 0.01;
        CalculatingPPostions low = new CalculatingPPostions(lower);

        if (low.getPPositions()[499] == obj.getPPositions()[499]) {
            return false; // the lower and obj equal each other, so its not a upper bound
        }

        return true; // CHECKING!
    }

    public static double generateCutoffs(double current_alpha) {
        CalculatingPPostions current = new CalculatingPPostions(current_alpha);

        double minAlpha = current_alpha + 10;

        int[] current_sequence = current.getPPositions();

        int i = 1;
        //int window = 10; // just a non 1 value to start with. EDIT: I actually don't understand how windows work. So for now...
        int current_postion;

        while (i < current_alpha*2) {
            current_postion = current_sequence[i];

            double upperBound = current_sequence[i] * current_alpha;
            double lowerBound = current_sequence[i - 1] * current_alpha;

            //window = 0;
            int x = i;
            while (current_sequence[x] <= upperBound) {
                /***
                if (current_sequence[x] > lowerBound) {
                    window++; // JUST BECAUSE WINDOW BECOMES ONE AT THIIISS TERM DOESNT MEAN WE'RE DONE.
                }
                **/
                x++;
            } // current_sequence[x] is now the first one out of the window.

            double possible_alpha = ((double) (current_sequence[x])) / current_postion;
            //System.out.println(current_sequence[x]);
            //System.out.println(current_postion);
            //System.out.println(possible_alpha);

            if (possible_alpha < minAlpha) {
                minAlpha = possible_alpha;
            }
            i++;
            //System.out.println();
        }

        return minAlpha;

    }

}
