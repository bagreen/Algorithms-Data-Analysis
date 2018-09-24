public class ArrayPrinting {

    public static void printArray(int[][] neighbors, int points) {

        String columnText = "";

        for (int i = 0; i <= points + 1; i++) {
            if (i >= 10) columnText += "   " + i;
            else columnText += "   " + i +" ";
        }

        System.out.print(columnText);

        System.out.println();

        System.out.print("--|");

        for (int i = 2; i <= columnText.length(); i++) System.out.print("-");

        System.out.println();

        for (int rows = 0; rows <= points + 1; rows++) {

            if (rows >= 10) System.out.print(rows + "");
            else System.out.print(rows + " ");
            System.out.print("|");

            for (int columns = 0; columns <= points; columns++) {

                System.out.print(neighbors[rows][columns]);

                if (columns < points && columns < 11) System.out.print(",   ");

                else if (columns < points && columns >= 11) System.out.print(",    ");

                else System.out.print("|");
            }
            System.out.println();
        }

        System.out.println();
    }

    public static void main(String[] args) {
        int[][] neighbors = new int[5][5];
        int pointNum = 0;


        System.out.println("neighbors:");
        System.out.println("  1  2  3  4  5  *");
        System.out.println("-|-----------------");
        for (int rows = 0; rows <= pointNum - 1; rows++) {
            System.out.print((rows + 1));
            System.out.print("|");
            for (int columns = 0; columns <= pointNum; columns++) {
                System.out.print((neighbors[rows][columns]));
                if (columns < pointNum) System.out.print(", ");
                else System.out.print("|");
            }
            System.out.println();
        }

        System.out.println();
    }
}
