package com.example.springdatajpa.console;

import java.util.Scanner;

public class Console {
    private Scanner scanner;

    public Console() {
        scanner = new Scanner(System.in);
    }

    public static void print(String s) {System.out.print(s);}
    public static void println() {System.out.println();}
    public static void println(String s) {System.out.println(s);}

    public String getString(String prompt) {
        String response = "";
        boolean isValid = false;
        while (!isValid) {
            print(prompt);
            response = scanner.nextLine();
            if (response.equals("")) {
                println("Error! This is a required entry. Try again.\n");
            } else {
                isValid = true;
            }
        }
        return response;
    }

    public String getString(String prompt, String s1, String s2) {
        String response = "";
        boolean isValid = false;
        while (!isValid) {
            response = getString(prompt);
            if (!response.equalsIgnoreCase(s1) && !response.equalsIgnoreCase(s2)) {
                println("Error! Entry must be '" + s1 + "' or '" + s2 +"'. Try again.\n");
            } else {
                isValid = true;
            }
        }
        return response;
    }

    public double getDouble(String prompt) {
        boolean isValid = false;
        double decimal = 0;
        while (!isValid){
            print(prompt);
            if (scanner.hasNextDouble()) {
                decimal = scanner.nextDouble();
                isValid = true;
            } else {
                println("Error! Invalid decimal value. Try again.");
                println();
            }
            scanner.nextLine();
        }
        return decimal;
    }

    public double getDouble(String prompt, double min, double max) {
        double decimal = 0;
        boolean isValid = false;
        while(!isValid) {
            decimal = getDouble(prompt);
            if (decimal <= min) {
                println("Error! Number must be greater than " + min);
                println();
            } else if (decimal >= max) {
                println("Error! Number must be less than " + max);
                println();
            } else {
                isValid = true;
            }
        }
        return decimal;
    }

    public int getInt(String prompt) {
        boolean isValid = false;
        int value = 0;
        while (!isValid) {
            print(prompt);

            if (scanner.equals("")) {
                println("Error! This is a required entry. Try again.");
                println();
            }else if (scanner.hasNextInt()) {
                value = scanner.nextInt();
                isValid = true;
            } else {
                println("Error! Invalid integer. Try Again.");
                println();
            }
            scanner.nextLine();
        }
        return value;
    }

    public int getInt(String prompt, int min, int max) {
        int number = 0;
        boolean isValid = false;
        while (!isValid) {
            number = getInt(prompt);
            if (number < min) {
                println("Error! Number must be larger than " + min);
                println();
            } else if (number > max) {
                println("Error! Number must be less than " + max);
                println();
            } else {
                isValid = true;
            }
        }
        return number;
    }
}
