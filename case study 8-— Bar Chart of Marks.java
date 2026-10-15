public class Main {
    public static void main(String[] args) {
        int[] marks = { 78, 91, 45, 66, 88 };
        String[] subjects = { "Sub 1", "Sub 2", "Sub 3", "Sub 4", "Sub 5" };

        System.out.println("=========================================");
        System.out.println("       BAR CHART - STUDENT MARKS         ");
        System.out.println("=========================================\n");

        // Find max mark to scale the chart height
        int maxMark = 100;

        // Render bars row by row (top to bottom)
        for (int level = maxMark; level >= 10; level -= 10) {
            System.out.printf("%3d | ", level);
            for (int mark : marks) {
                if (mark >= level) {
                    System.out.print("  ███   ");
                } else {
                    System.out.print("        ");
                }
            }
            System.out.println();
        }

        // Render axis line
        System.out.println("    +------------------------------------------");

        // 1. Mark on top / value line
        System.out.print("      ");
        for (int mark : marks) {
            System.out.printf("  %-3d   ", mark);
        }
        System.out.println();

        // 2. Subject names below bars
        System.out.print("      ");
        for (String sub : subjects) {
            System.out.printf(" %-5s  ", sub);
        }
        System.out.println("\n");

        // Status report (Pass/Fail)
        System.out.println("--- STATUS ---");
        for (int i = 0; i < marks.length; i++) {
            String status = marks[i] >= 50 ? "PASS (Green)" : "FAIL (Red)";
            System.out.println(subjects[i] + ": " + marks[i] + " -> " + status);
        }
    }
}
