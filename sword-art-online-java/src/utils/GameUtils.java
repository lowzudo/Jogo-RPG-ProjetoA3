package src.utils;

import java.util.Scanner;

public class GameUtils {
    private static Scanner scanner = new Scanner(System.in);
    
    public static void dramaticPrint(String text) {
        try {
            for (char c : text.toCharArray()) {
                System.out.print(c);
                System.out.flush();
                Thread.sleep(30);
            }
            System.out.println();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    public static void waitForEnter() {
        System.out.println("\nPressione ENTER para continuar...");
        scanner.nextLine();
    }
    
    public static String inputTypewriter(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
    
    public static void clearScreen() {
        try {
            // Para Windows
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } 
            // Para Linux/Mac
            else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            for (int i = 0; i < 50; i++) {
                System.out.println();
            }
        }
    }
}