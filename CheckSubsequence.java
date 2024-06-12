/**
 * %W% %E% Om Deshmukh
 *
 * Copyright (c) 1993-1996 XYZ, Inc. All Rights Reserved.
 * 
 * PURPOSE OF THE FOLLOWING PROGRAM IS TO CHECK IF THE CHILD STRING CAN BE
 * FORMED OUT OF PARENT STRING CHARACTERS (CHARACTERS MAY NOT BE CONTINUOUS)
 *
 */
import java.util.Scanner;  

/**
 * CheckSubsequence class is the main class which will be used to check if the child
 * string can be formed out of parent string characters
 *
 * @version 1.0 13 Jun 2024
 * @author Om Deshmukh
 */
class CheckSubsequence {
    public static void main(String[] args) {
        /** Create a scanner object */
        Scanner sc = new Scanner(System.in);

        /** Input the parent string */
        System.out.println("Enter the parent string: ");
        String parentString = sc.nextLine();

        /** Input the child string */
        System.out.println("Enter the child string: ");
        String childString = sc.nextLine();
        
        /** Get the length of both strings */
        int n1 = parentString.length();
        int n2 = childString.length();
        
        /** Check if the child string can be formed out of parent string characters */
        if(n2 > n1){ 
            System.out.print("false");
            sc.close();
            return;
        }
        
        /** We use 2-pointer to approach to solve the problem */
        /** Pointer 1 - Use variable 'j' to iterate over the child string */
        int j = 0;
        
        /** Pointer 2 - Use variable 'i' to iterate over the parent string */
        for(int i = 0; i < n1; i++){
            /** If the child string is formed, break the loop */
            if(j == n2) break;
            
            /** If the characters match, increment 'j' */
            if(parentString.charAt(i) == childString.charAt(j)) j++;
        }
        
        /** If the child string is formed, print true else false */
        if(j == n2){
            System.out.print("true");
        }
        else{
            System.out.print("false");
        }


        sc.close();
    }
}