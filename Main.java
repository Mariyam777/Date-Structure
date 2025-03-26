import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class Date implements Comparable<Date> {
    private int day;
    private int month;
    private int year;

    // Constructor
    public Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    // Validate date
    public boolean isValidDate() {
        if (day < 1 || day > 31 || month > 12 || month < 1) {
            return false;
        } else if ((month == 4 || month == 6 || month == 9 || month == 11) && day > 30) {
            return false;
        } else if (month == 2) {
            boolean isLeap = (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
            return !(day > 29 || (day > 28 && !isLeap));
        }
        return true;
    }

    // Update date with validation
    public boolean updateDate(int d, int m, int y) {
        Date temp = new Date(d, m, y);
        if (temp.isValidDate()) {
            this.day = d;
            this.month = m;
            this.year = y;
            return true;
        }
        return false;
    }

    // Get day of week
    public String getDayOfWeek() {
        int m = month;
        int y = year;
        if (m < 3) {
            m += 12;
            y--;
        }
        int k = day;
        int D = y % 100;
        int C = y / 100;
        int f = k + ((13 * (m + 1)) / 5) + D + (D / 4) + (C / 4) - (2 * C);
        int dayOfWeek = ((f % 7) + 7) % 7;

        String[] days = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        return days[dayOfWeek];
    }

    // Calculate difference between two dates
    public long calculateDifference(Date otherDate) {
        long days1 = this.toDays();
        long days2 = otherDate.toDays();
        return Math.abs(days1 - days2);
    }

    private long toDays() {
        long days = year * 365L + day;
        days += (year / 4 - year / 100 + year / 400); // Leap years
        int[] monthDays = {0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334};
        days += monthDays[month - 1];
        if (month > 2 && ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0))) {
            days++; // Add leap day
        }
        return days;
    }

    // Print date in readable format - FIXED VERSION
    public void printDate() {
        String[] months = {"January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"};
        System.out.println(months[month - 1] + " " + day + ", " + year);  // Fixed array index
    }

    // Implement Comparable interface for sorting
    public int compareTo(Date other) {
        if (this.year != other.year) return this.year - other.year;
        if (this.month != other.month) return this.month - other.month;
        return this.day - other.day;
    }

    public int getDay() { return day; }
    public int getMonth() { return month; }
    public int getYear() { return year; }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Date> dates = new ArrayList<>();
        int choice;

        do {
            System.out.println("\nDate Structure Program");
            System.out.println("1 => Add new date");
            System.out.println("2 => Get day of the week for a date");
            System.out.println("3 => Calculate difference between two dates");
            System.out.println("4 => Print all dates");
            System.out.println("0 => Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter day : ");
                    int day = scanner.nextInt();
                    System.out.print("Enter month : ");
                    int month = scanner.nextInt();
                    System.out.print("Enter year : ");
                    int year = scanner.nextInt();

                    Date newDate = new Date(day, month, year);
                    if (newDate.isValidDate()) {
                        dates.add(newDate);
                        System.out.println("Date added successfully!");
                    } else {
                        System.out.println("Invalid date! Please check your input.");
                    }
                    break;

                case 2:
                    if (dates.isEmpty()) {
                        System.out.println("No dates available. Please add dates first.");
                        break;
                    }
                    System.out.print("Enter date index (0-" + (dates.size() - 1) + "): ");
                    int index = scanner.nextInt();
                    if (index >= 0 && index < dates.size()) {
                        Date date = dates.get(index);
                        System.out.printf("%d/%d/%d is a %s\n",
                                date.getDay(), date.getMonth(), date.getYear(),
                                date.getDayOfWeek());
                    } else {
                        System.out.println("Invalid index! Please try again.");
                    }
                    break;

                case 3:
                    if (dates.size() < 2) {
                        System.out.println("You need at least 2 dates to compare.");
                        break;
                    }
                    System.out.print("Enter first date index (0-" + (dates.size() - 1) + "): ");
                    int idx1 = scanner.nextInt();
                    System.out.print("Enter second date index (0-" + (dates.size() - 1) + "): ");
                    int idx2 = scanner.nextInt();

                    if (idx1 >= 0 && idx1 < dates.size() && idx2 >= 0 && idx2 < dates.size()) {
                        long diff = dates.get(idx1).calculateDifference(dates.get(idx2));
                        System.out.printf("There are %,d days between these dates\n", diff);
                    } else {
                        System.out.println("Invalid indexes! Please try again.");
                    }
                    break;

                case 4:
                    if (dates.isEmpty()) {
                        System.out.println("No dates to display.");
                        break;
                    }
                    Collections.sort(dates);
                    System.out.println("\nAll Dates (sorted):");
                    for (Date d : dates) {
                        d.printDate();
                    }
                    break;

                case 0:
                    System.out.println("Exiting program...");
                    break;

                default:
                    System.out.println("Invalid choice! Please enter 0-4.");
            }
        } while (choice != 0);

        scanner.close();
    }
}
