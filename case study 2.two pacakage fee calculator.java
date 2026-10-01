package com.scsvmv.fee.model;

public class Student {
    public String name;
    public String course;

    public Student(String n, String c) {
        name = n;
        course = c;
    }
}
package com.scsvmv.fee.service;

public class FeeRule {
    public static double fee(String course) {
        if (course.equals("BE"))
            return 75000;

        if (course.equals("ME"))
            return 60000;

        return 40000;
    }
}
package com.scsvmv.fee.report;

import com.scsvmv.fee.model.Student;
import com.scsvmv.fee.service.FeeRule;

public class FeeReport {
    public static void print(Student[] s) {
        double total = 0;

        for (Student x : s) {
            double f = FeeRule.fee(x.course);

            System.out.printf("%-8s %-5s %10.2f%n",
                    x.name, x.course, f);

            total = total + f;
        }

        System.out.printf("Total fee = %.2f%n", total);
    }
}
import com.scsvmv.fee.model.Student;
import com.scsvmv.fee.report.FeeReport;

public class FeeApp {
    public static void main(String[] args) {

        Student[] s = {
            new Student("Meena", "BE"),
            new Student("Ravi", "ME"),
            new Student("Anu", "BSc")
        };

        FeeReport.print(s);
    }
}
