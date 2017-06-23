import java.util.Scanner;

public class CutOffs {

    static double[] ratios = new double[50];
    static Scanner menu = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("hi pick one of the choices.");
        System.out.println("1. printPpositions()");
        System.out.println("2. determineSequences(lower, upper)");
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

        } else if (choice == 4) {
            int starting_point = menu.nextInt();
            double[] cutoff_hyp = generateCutoffs(starting_point);
            boolean bool_cutoff;
            for (int i = 0; i < 50; i++) {
                System.out.println(cutoff_hyp[0] + "/" + cutoff_hyp[1] + " = " + cutoff_hyp[2]);
                cutoff_hyp = generateCutoffs(cutoff_hyp[2]);
            }
        }
    }

    /*
    *   Prints the P positions of an alpha-game with the alpha the user
    *   inputs.
    */
    public static void printPpositions() {
        double alpha;
        while(menu.hasNextDouble()) {
            alpha = menu.nextDouble();
            CalculatingPPostions obj = new CalculatingPPostions(alpha);
            //System.out.println(obj.printRecursion());

             for (int i = 1; i < 50; i++) {
                  System.out.print(obj.getPPositions()[i] + ",  ");
              }

            System.out.println();
            System.out.println(obj.printRecursion());
        }
    }

    /*
    *   Given an interval of a recursive sequence, (for example, the interval for
    *   the sequence P_n = P_n-1 + P_n-7 is 4.333 < alpha <= 4.666), this method
    *   will find the secondary CutOffs within this interval.
    *   @param lower : the lower bound specified
    *   @param upper : the upper bound specified
    */
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
            previous = tester.getPPositions()[20];
        }

        return cutoffs;

    }

    /*
    *   Determines whether the specified alpha is a cutoff
    *   @param alpha : the specified alpha
    */
    public static boolean isCutoff(double alpha) {

        double lower = alpha - 0.01;
        double higher = alpha + 0.01;
        CalculatingPPostions low = new CalculatingPPostions(lower);
        CalculatingPPostions high = new CalculatingPPostions(higher);


        if (low.getPPositions()[CalculatingPPostions.NUM_RECORDED - 1] == high.getPPositions()[CalculatingPPostions.NUM_RECORDED - 1]) {
            return false;
        }

        return true;
    }

    /*
    *   Generates the upper bound cutoff of a sequence determined by the
    *   specified alpha.
    *   @param alpha : The specified alpha
    */
    public static double[] generateCutoffs(double current_alpha) {
        CalculatingPPostions current = new CalculatingPPostions(current_alpha);
        double[] values = new double[3];

        double minAlpha = current_alpha + 10;

        int[] current_sequence = current.getPPositions();

        int i = 1;

        int current_postion;

        while (i < current_alpha * 10) {
            current_postion = current_sequence[i];

            double upperBound = current_sequence[i] * current_alpha;
            double lowerBound = current_sequence[i - 1] * current_alpha;

            int x = i;
            while (current_sequence[x] <= upperBound && x < CalculatingPPostions.NUM_RECORDED - 2) {
                x++;
            }

            double possible_alpha = ((double) (current_sequence[x])) / current_postion;

            if (i < ratios.length) {
                ratios[i] = possible_alpha;
            }

            if (possible_alpha < minAlpha) {

                if (possible_alpha <= current_alpha) {
                    return values;
                }

                minAlpha = possible_alpha;
                values[0] = current_sequence[x];
                values[1] = current_postion;
                values[2] = minAlpha;
            }
            i++;
        }

        return values;

    }

}
