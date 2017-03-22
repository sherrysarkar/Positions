import java.util.Scanner;

public class CutOffs {
    public static void main(String[] args) {

        Scanner menu = new Scanner(System.in);
        System.out.println("hi pick one of the choices.");
        System.out.println("1. printPpositions()");
        System.out.println("2. determineSequences(lower, upper)");
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

        }

    }

    public static void printPpositions() {
        Scanner scan = new Scanner(System.in);
        System.out.println();
        double alpha = 1;
        while(scan.hasNextDouble()) {
            alpha = scan.nextDouble();
            CalculatingPPostions obj = new CalculatingPPostions(alpha);
            //System.out.println(obj.printRecursion());

             for (int i = 0; i < 20; i++) {
                  System.out.println(obj.getPPositions()[i]);
              }

            System.out.println();
            System.out.println(obj.printRecursion());
        }
    }

    public void determineCutoff (int k) {
        int seperation = k - 1;
        double ratio;

        for (int q = 1; q <= 20; q++) {

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

}
