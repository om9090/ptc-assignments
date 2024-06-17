/**
 * %W% %E% Om Deshmukh
 *
 * Copyright (c) 1993-1996 XYZ, Inc. All Rights Reserved.
 * 
 * PURPOSE OF THE FOLLOWING PROGRAM IS TO CHECK IF THE CHILD STRING CAN BE
 * FORMED OUT OF PARENT STRING CHARACTERS (CHARACTERS MAY NOT BE CONTINUOUS)
 *
 */
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;  

/**
 * CheckSubsequence class is the main class which will be used to check if the child
 * string can be formed out of parent string characters
 *
 * @version 1.0 13 Jun 2024
 * @author Om Deshmukh
 */


 class CheckSubsequence2 {

    // Method to check if the child string is a subsequence of the parent string
    public static boolean CheckSequence(String parent, String child) {
        int j = 0;
        // Iterate through the parent string
        for (int i = 0; i < parent.length(); i++) {
            // Check if current character of parent matches with the current character of child
            if (j < child.length() && parent.charAt(i) == child.charAt(j)) {
                j++;
            }
        }
        // Return true if all characters in child are found in the parent in the correct order
        return j == child.length();
    }

    // Method to count the frequency of each character found in the child string within the parent string
    public static void CountFrequency(String parent, String child) { 
        Map<Character, Integer> foundChars = new HashMap<>();
        int j = 0;
        // Iterate through the parent string
        for (int i = 0; i < parent.length(); i++) {
            // If current character of parent matches with the current character of child
            if (j < child.length() && parent.charAt(i) == child.charAt(j)) {
                foundChars.put(child.charAt(j), foundChars.getOrDefault(child.charAt(j), 0) + 1);
                j++;
            }
        }
        
        // If all characters of child are found in parent
        if (j == child.length()) {
            System.out.println("Characters Found: ");
            for (Map.Entry<Character, Integer> entry : foundChars.entrySet()) {
                System.out.println(entry.getKey() + " : Freq = " + entry.getValue());
            }
            System.out.println("Characters Missed: None");
        } else {
            System.out.println("Characters Found: ");
            for (Map.Entry<Character, Integer> entry : foundChars.entrySet()) {
                System.out.println(entry.getKey() + " : Freq = " + entry.getValue());
            }
            // If not all characters of child are found in parent
            foundChars.clear();

            // Store the characters that were missed
            for (int k = j; k < child.length(); k++) {
                char c = child.charAt(k);
                foundChars.put(c, foundChars.getOrDefault(c, 0) + 1);
            }

            System.out.println("Characters Missed: ");
            for (Map.Entry<Character, Integer> entry : foundChars.entrySet()) {
                System.out.println(entry.getKey() + " : Freq = " + entry.getValue());
            }
        }
    }

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);

            System.out.println("Enter the parent string: ");
            String parentString = sc.nextLine();
    
            System.out.println("Enter the child string: ");
            String childString = sc.nextLine();

            // Check for empty strings
            if (parentString.isEmpty() || childString.isEmpty()) {
                throw new IllegalArgumentException("Strings cannot be empty");
            }

            // Reverse the child string
            String reverseChildString = new StringBuilder(childString).reverse().toString();       
            
            // Check if the child string or its reverse is a subsequence of the parent string
            if (CheckSequence(parentString, childString)) {
                System.out.println("True");
                CountFrequency(parentString, childString);
            } else if (CheckSequence(parentString, reverseChildString)) {
                System.out.println("True");
                CountFrequency(parentString, reverseChildString);
            } else {
                System.out.println("False");
                CountFrequency(parentString, childString);
            }

        } catch (IllegalArgumentException e) {
            // Handle empty string exception
            System.out.println("Strings cannot be empty");
        } catch (Exception e) {
            // Handle any other exceptions
            System.out.println("An error occurred");
        }    
    }
}