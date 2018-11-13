import java.util.*;

public class Physics {
    private static void equationChecker(Double[] variables, ArrayList<String> unknowns) {
        // DistanceX
        if (!unknowns.contains("Xf") && !unknowns.contains("Xi") && !unknowns.contains("Vix") && !unknowns.contains("t") && !unknowns.contains("a")) variables[11] = distance(variables[11], variables[10], variables[6], variables[3], variables[0], "Xf");
        else if (!unknowns.contains("Xi") && !unknowns.contains("Xf") && !unknowns.contains("Vix") && !unknowns.contains("t") && !unknowns.contains("a")) variables[11] = distance(variables[11], variables[10], variables[6], variables[3], variables[0], "Xi");
        else if (!unknowns.contains("Vix") && !unknowns.contains("Xf") && !unknowns.contains("Xi") && !unknowns.contains("t") && !unknowns.contains("a")) variables[11] = distance(variables[11], variables[10], variables[6], variables[3], variables[0], "Vix");
        else if (!unknowns.contains("t") && !unknowns.contains("Xf") && !unknowns.contains("Xi") && !unknowns.contains("Vix") && !unknowns.contains("a")) variables[11] = distance(variables[11], variables[10], variables[6], variables[3], variables[0], "t");
        else if (!unknowns.contains("a") && !unknowns.contains("Xf") && !unknowns.contains("Xi") && !unknowns.contains("Vix") && !unknowns.contains("t")) variables[11] = distance(variables[11], variables[10], variables[6], variables[3], variables[0], "a");

        // DistanceY
        // DistanceX
        if (!unknowns.contains("Yf") && !unknowns.contains("Yi") && !unknowns.contains("Viy") && !unknowns.contains("t") && !unknowns.contains("a")) variables[11] = distance(variables[11], variables[10], variables[6], variables[3], variables[0], "Xf");
        else if (!unknowns.contains("Yi") && !unknowns.contains("Xf") && !unknowns.contains("Viy") && !unknowns.contains("t") && !unknowns.contains("a")) variables[11] = distance(variables[11], variables[10], variables[6], variables[3], variables[0], "Xi");
        else if (!unknowns.contains("Viy") && !unknowns.contains("Xf") && !unknowns.contains("Xi") && !unknowns.contains("t") && !unknowns.contains("a")) variables[11] = distance(variables[11], variables[10], variables[6], variables[3], variables[0], "Vix");
        else if (!unknowns.contains("t") && !unknowns.contains("Yf") && !unknowns.contains("Yi") && !unknowns.contains("Viy") && !unknowns.contains("a")) variables[11] = distance(variables[11], variables[10], variables[6], variables[3], variables[0], "t");
        else if (!unknowns.contains("a") && !unknowns.contains("Yf") && !unknowns.contains("Yi") && !unknowns.contains("Viy") && !unknowns.contains("t")) variables[11] = distance(variables[11], variables[10], variables[6], variables[3], variables[0], "a");

        // Pythagorean
        if (unknowns.contains("A") && !unknowns.contains("B") && !unknowns.contains("C")) variables[14] = pythagorean(variables[16], variables[15], "A");
        else if (unknowns.contains("B") && !unknowns.contains("A") && !unknowns.contains("C")) variables[15] = pythagorean(variables[16], variables[14], "B");
        else if (unknowns.contains("C") && !unknowns.contains("A") && !unknowns.contains("B")) variables[16] = pythagorean(variables[14], variables[15], "C");
    }
    private static Double[] xAndY(double Vi, double angle) {
        Double[] xAndY = new Double[2];

        xAndY[0] = Vi * Math.cos(angle);
        xAndY[1] = Vi * Math.sin(angle);
        return xAndY;
    }
    private static double pythagorean(double side1, double side2, String unknown) {
        // A^2 + B^2 = C^2
        if (unknown.equals("side")) return Math.pow(side1, 2) - Math.pow(side2, 2);
        else return Math.pow(side1, 2) + Math.pow(side2, 2);
    }
    private static double distance(double Xf, double Xi, double Vix, double t, double a, String unknown) {
        // Xf = Xi + Vi*t + 0.5*a*t^2
        double answer = 0.0;

        if (unknown.equals("a")) answer = (Xf - Xi - Vix * t) / (0.5 * t * t);
        else if (unknown.equals("Vix") || unknown.equals("Viy")) answer = (Xf - 0.5 * a * t * t) / t;
        else if (unknown.equals("Xf") || unknown.equals("Yf")) answer = Xi + Vix * t + 0.5 * a * t * t;
        else if (unknown.equals("Xi") || unknown.equals("Yi")) answer = Xf - Vix * t - 0.5 * a * t * t;
        else if (unknown.equals("t")) { // unknown = "t"
            if (Xi == 0 && Xf != 0) {
                Double[] quadraticNums = quadratic(0.5 * a * t * t, Vix * t, Xf);
                double firstTime = quadraticNums[0], secondTime = quadraticNums[1];

                if (firstTime > secondTime) answer = firstTime;
                else answer = secondTime;
            }
            else if (Xf == 0 && Xi != 0) {
                Double[] quadraticNums = quadratic(0.5 * a * t * t, Vix * t, Xi);
                double firstTime = quadraticNums[0], secondTime = quadraticNums[1];

                if (firstTime > secondTime) answer = firstTime;
                else answer = secondTime;
            }
        }
        return answer;
    }
    private static Double[] quadratic(Double a, Double b, Double c) {
        // (-b +/- SquareRoot(b^2 - 4 * a * c)) / (2 * a)
        Double[] quadraticList = new Double[2];
        quadraticList[0] = -b + Math.sqrt((b * b) - (4 * a * c)) / (2 * a);
        quadraticList[1] = -b - Math.sqrt((b * b) - (4 * a * c)) / (2 * a);
        return quadraticList;
    }
    private static String inArraylist(ArrayList<String> variables, String formula) {
        ArrayList<String> values = new ArrayList<>(Arrays.asList(formula.split(" ")));

        String solveFor = "";
        int notFound = 0;
        int increment = 0;
        while (notFound < 2) {
            if (increment == values.size() - 1) {
                break;
            }
            if (!variables.contains(values.get(increment))) {
                notFound++;
                solveFor = values.get(increment);
            }
            increment++;
        }
        if (notFound == 1) {
            return solveFor;
        }
        else {
            return "!";
        }
    }
    public static void main(String[] args) {
        java.util.Scanner in = new java.util.Scanner(System.in);
        Double[] variables = new Double[20];

        System.out.println(Arrays.toString(variables));

        System.out.println("Enter all variables name then their value, then type exit");
        String input = "";
        while (!input.equals("exit")) {

            switch (in.next()) {
                case "a": variables[0] = in.nextDouble();
                break;
                case "angle": variables[1] = in.nextDouble();
                break;
                case "r": variables[2] = in.nextDouble();
                break;
                case "t": variables[3] = in.nextDouble();
                break;
                case "Vi": variables[4] = in.nextDouble();
                break;
                case "Vf": variables[5] = in.nextDouble();
                break;
                case "Vix": variables[6] = in.nextDouble();
                break;
                case "Viy": variables[7] = in.nextDouble();
                break;
                case "Vfx": variables[8] = in.nextDouble();
                break;
                case "Vfy": variables[9] = in.nextDouble();
                break;
                case "Xi": variables[10] = in.nextDouble();
                break;
                case "Xf": variables[11] = in.nextDouble();
                break;
                case "Yi": variables[12] = in.nextDouble();
                break;
                case "Yf": variables[13] = in.nextDouble();
                break;
                case "A": variables[14] = in.nextDouble();
                break;
                case "B": variables[15] = in.nextDouble();
                break;
                case "C": variables[16] = in.nextDouble();
                break;
                case "exit": input = "exit";
                break;
            }
        }

        ArrayList<String> unknowns = new ArrayList<>();

        for (int i = 0; i < unknowns.size(); i++) {
            if (variables[i] == 0) {
                switch (i) {
                    case 0: unknowns.add("a");
                    break;
                    case 1: unknowns.add("angle");
                    break;
                    case 2: unknowns.add("r");
                    break;
                    case 3: unknowns.add("t");
                    break;
                    case 4: unknowns.add("Vi");
                    break;
                    case 5: unknowns.add("Vf");
                    break;
                    case 6: unknowns.add("Vix");
                    break;
                    case 7: unknowns.add("Viy");
                    break;
                    case 8: unknowns.add("Vfx");
                    break;
                    case 9: unknowns.add("Vfy");
                    break;
                    case 10: unknowns.add("Xi");
                    break;
                    case 11: unknowns.add("Xf");
                    break;
                    case 12: unknowns.add("Yi");
                    break;
                    case 13: unknowns.add("Yf");
                    break;
                    case 14: unknowns.add("A");
                    break;
                    case 15: unknowns.add("B");
                    break;
                    case 16: unknowns.add("C");
                    break;
                }
            }
        }
        //equationChecker(variables, unknowns);

        if (!unknowns.contains("Vi")) {
            if (unknowns.contains("Vix") || unknowns.contains("Viy")) {
                if (!unknowns.contains("angle")) {
                    Double[] xAndY = xAndY(variables[4], variables[1]);
                    variables[6] = xAndY[0];
                    variables[7] = xAndY[1];
                }
                else if (!unknowns.contains("Vix")) {
                    variables[7] = pythagorean(variables[4], variables[6], "side");
                }
                else if (!unknowns.contains("Viy")) {
                    variables[6] = pythagorean(variables[4], variables[6], "side");
                }
            }
        }
        if (!unknowns.contains("Vf")) {
            if (unknowns.contains("Vfx") || unknowns.contains("Vfy")) {
                if (!unknowns.contains("angle")) {
                    Double[] xAndY = xAndY(variables[5], variables[1]);
                    variables[8] = xAndY[0];
                    variables[9] = xAndY[1];
                }
                else if (!unknowns.contains("Yix")) {
                    variables[9] = pythagorean(variables[5], variables[8], "side");
                }
                else if (!unknowns.contains("Yiy")) {
                    variables[8] = pythagorean(variables[5], variables[9], "side");
                }
            }
        }



        /*
        // DistanceX
        if (!unknowns.contains("Xf") && !unknowns.contains("Xi") && !unknowns.contains("Vix") && !unknowns.contains("t") && !unknowns.contains("a")) variables[11] = distance(variables[11], variables[10], variables[6], variables[3], variables[0], "Xf");
        else if (!unknowns.contains("Xi") && !unknowns.contains("Xf") && !unknowns.contains("Vix") && !unknowns.contains("t") && !unknowns.contains("a")) variables[11] = distance(variables[11], variables[10], variables[6], variables[3], variables[0], "Xi");
        else if (!unknowns.contains("Vix") && !unknowns.contains("Xf") && !unknowns.contains("Xi") && !unknowns.contains("t") && !unknowns.contains("a")) variables[11] = distance(variables[11], variables[10], variables[6], variables[3], variables[0], "Vix");
        else if (!unknowns.contains("t") && !unknowns.contains("Xf") && !unknowns.contains("Xi") && !unknowns.contains("Vix") && !unknowns.contains("a")) variables[11] = distance(variables[11], variables[10], variables[6], variables[3], variables[0], "t");
        else if (!unknowns.contains("a") && !unknowns.contains("Xf") && !unknowns.contains("Xi") && !unknowns.contains("Vix") && !unknowns.contains("t")) variables[11] = distance(variables[11], variables[10], variables[6], variables[3], variables[0], "a");

        // DistanceY
        // DistanceX
        if (!unknowns.contains("Yf") && !unknowns.contains("Yi") && !unknowns.contains("Viy") && !unknowns.contains("t") && !unknowns.contains("a")) variables[11] = distance(variables[11], variables[10], variables[6], variables[3], variables[0], "Xf");
        else if (!unknowns.contains("Yi") && !unknowns.contains("Xf") && !unknowns.contains("Viy") && !unknowns.contains("t") && !unknowns.contains("a")) variables[11] = distance(variables[11], variables[10], variables[6], variables[3], variables[0], "Xi");
        else if (!unknowns.contains("Viy") && !unknowns.contains("Xf") && !unknowns.contains("Xi") && !unknowns.contains("t") && !unknowns.contains("a")) variables[11] = distance(variables[11], variables[10], variables[6], variables[3], variables[0], "Vix");
        else if (!unknowns.contains("t") && !unknowns.contains("Yf") && !unknowns.contains("Yi") && !unknowns.contains("Viy") && !unknowns.contains("a")) variables[11] = distance(variables[11], variables[10], variables[6], variables[3], variables[0], "t");
        else if (!unknowns.contains("a") && !unknowns.contains("Yf") && !unknowns.contains("Yi") && !unknowns.contains("Viy") && !unknowns.contains("t")) variables[11] = distance(variables[11], variables[10], variables[6], variables[3], variables[0], "a");

        // Pythagorean
        if (unknowns.contains("A") && !unknowns.contains("B") && !unknowns.contains("C")) variables[14] = pythagorean(variables[16], variables[15], "A");
        else if (unknowns.contains("B") && !unknowns.contains("A") && !unknowns.contains("C")) variables[15] = pythagorean(variables[16], variables[14], "B");
        else if (unknowns.contains("C") && !unknowns.contains("A") && !unknowns.contains("B")) variables[16] = pythagorean(variables[14], variables[15], "C");
        */
    }
}
