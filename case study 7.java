import java.io.*;

public class Main {
    public static void main(String[] args) {
        // Step 1: Automatically create student.txt with sample data
        createSampleFile();

        // Step 2: Variables for processing and tracking topper
        int pass = 0, fail = 0, total = 0, n = 0;
        int maxMarks = -1;
        String topperName = "";

        File file = new File("student.txt");

        // Feature 1: Check if file exists before opening
        if (!file.exists()) {
            System.out.println("Error: student.txt does not exist.");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file));
             PrintWriter pw = new PrintWriter("result.txt")) {
            
            String line;
            while ((line = br.readLine()) != null) {
                String[] f = line.split(",");
                if (f.length < 3) continue;

                try {
                    // Feature 2: Catch NumberFormatException for invalid numbers (e.g., letters)
                    int m = Integer.parseInt(f[2].trim());
                    String name = f[1].trim();

                    total += m;
                    n++;

                    // Feature 3: Track the topper
                    if (m > maxMarks) {
                        maxMarks = m;
                        topperName = name;
                    }

                    if (m >= 50) {
                        pass++;
                        pw.println(name + " PASS");
                    } else {
                        fail++;
                        pw.println(name + " FAIL");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Skipped invalid record (non-numeric mark): " + line);
                }
            }

            if (n > 0) {
                pw.println("Passed : " + pass);
                pw.println("Failed : " + fail);
                pw.printf("Average : %.2f%n", (double) total / n);
                pw.println("Topper : " + topperName + " (" + maxMarks + ")");
            }

            System.out.println("\nresult.txt created. Passed " + pass + ", failed " + fail);

        } catch (IOException e) {
            System.out.println("File error : " + e.getMessage());
        }

        // Step 3: Display the generated result file contents in the console
        printResultFile();
    }

    private static void createSampleFile() {
        try (FileWriter fw = new FileWriter("student.txt")) {
            fw.write("101,Aravind,78\n");
            fw.write("102,Divya,91\n");
            fw.write("103,Karthik,45\n");
            fw.write("104,InvalidUser,ABC\n"); // Bad data line (letters)
        } catch (IOException e) {
            System.out.println("Error generating sample file: " + e.getMessage());
        }
    }

    private static void printResultFile() {
        System.out.println("\n--- Contents of result.txt ---");
        try (BufferedReader br = new BufferedReader(new FileReader("result.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading result.txt: " + e.getMessage());
        }
    }
}
