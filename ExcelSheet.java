/**
 * %W% %E% Om Deshmukh
 *
 * Copyright (c) 1993-1996 XYZ, Inc. All Rights Reserved.
 * 
 * PURPOSE OF THE FOLLOWING PROGRAM IS TO IMPLEMENT FEATURES OF EXCEL SHEET.
 * USER SHOULD BE ABLE TO CREATE HIS OWN SHEET AND PERFORM SEVERAL OPERATIONS WHICH ARE
 * OFFERED BY MICROSOFT EXCEL.
 *
 */

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

/**
 * ExcelSheet class is the main class which will be used to create the Excel Sheet
 * and perform operations on it.
 *
 * @version 1.0 9 Jun 2024
 * @author Om Deshmukh
 */

public class ExcelSheet{
    
    /** Java Collection used to implement Excel Sheet */
    private ArrayList<ArrayList<String>> sheet;
    
    /** Constructor to initialize the Excel Sheet */
    public ExcelSheet() {
        this.sheet = new ArrayList<>();
    }

    /** Add a value in a cell
     * @param row Row index of the cell
     * @param col Column index of the cell
     * @param value Value to be added in the cell
     */
    public void addCell(int row, int col, String value) {
        if(sheet.isEmpty() == true){
            int maxNumber = Math.max(row, col);                                                                     // Find the maximum number of rows and columns

            for(int i = 0; i < maxNumber; i++){
                ArrayList<String> newRow = new ArrayList<>(Collections.nCopies(maxNumber, ""));                   // Initialize the new row with empty strings
                sheet.add(newRow);
            }
        }
        else{
            if(row > sheet.size() && col > sheet.get(0).size()){
                int maxNumber = Math.max(row, col);                                                                 // Find the maximum number of rows and columns

                for(int i = 0; i < sheet.size(); i++){                                                              // Add empty strings to the existing rows
                    for(int j = 0; j < maxNumber; j++){
                        sheet.get(i).add("");
                    }
                }

                for(int i = sheet.size(); i < maxNumber; i++){                                                      // Add new rows with empty strings                   
                    ArrayList<String> newRow = new ArrayList<>(Collections.nCopies(maxNumber, ""));               
                    sheet.add(newRow);
                }
            } 
            else if(col > sheet.get(0).size()){                                                               // Add a new column                                         
                // empty columns to add
                int moreCol = col - sheet.get(0).size();

                for(int i = 0; i < sheet.size(); i++){
                    for(int j = 0; j < moreCol; j++){
                        sheet.get(i).add("");
                    }
                }
            }
            else if(row > sheet.size()){                                                                            // Add a new row                                
                // empty rows to add
                int moreRow = row - sheet.size();

                for(int i = 0; i < moreRow; i++){
                    // Add new row with empty strings
                    ArrayList<String> newRow = new ArrayList<>(Collections.nCopies(sheet.get(0).size(), ""));      
                    sheet.add(newRow);
                }
            }
        }
        // Set the value in the cell
        sheet.get(row-1).set(col-1, value);
    }

    /** Add a new row at a specific index
     * @param index Index at which the new row is to be added
     */
    public void addRow(int index) {
        if(sheet.isEmpty() == true) {                                                                               // If the sheet is empty initialize the sheet                               
            System.out.println("Enter the number of columns: ");                                                  // with the number of columns = 'cols'                 
            Scanner sc = new Scanner(System.in);
            int cols = sc.nextInt();

            for(int j = 0;j < index; j++){                                                                          // Add 'index' number of rows with 'cols' number of columns
                ArrayList<String> newRow = new ArrayList<>(Collections.nCopies(cols, ""));
                sheet.add(newRow);
            }
            sc.close();
        } else{
            if (index <= sheet.size()) {                                                                            // If the index is less than or equal to the number of rows in the sheet
                ArrayList<String> newRow = new ArrayList<>(Collections.nCopies(sheet.get(0).size(), ""));   // Add a new row with the same number of columns as the first row            
                sheet.add(index-1, newRow);
            } else {
                int moreRow = index - sheet.size();
                for(int i = 0; i < moreRow; i++){                                                       
                    ArrayList<String> newRow = new ArrayList<>(Collections.nCopies(sheet.get(0).size(), ""));               
                    sheet.add(newRow);
                }
            }
        }
    }

    /** Add a new column at a specific index 
     * @param index Index at which the new column is to be added
     */ 
    public void addColumn(int index) {
       if(sheet.isEmpty() == true){                                                            // If the sheet is empty initialize the sheet   
            System.out.println("Enter the number of rows: ");                                // with the number of rows = 'rows'
            Scanner sc = new Scanner(System.in);
            int rows = sc.nextInt();                                                           // Number of rows in the new column
            for (int i = 0; i < rows; i++) {
                ArrayList<String> newRow = new ArrayList<>();
                newRow.add("");
                sheet.add(newRow);
            }
            sc.close();
        }
        else{
            if (index <= sheet.get(0).size()) {                                          // If the index is less than or equal to 
                for (ArrayList<String> row : sheet) {                                          // the number of columns in the first row 
                    row.add(index-1, "");
                }
            } else {
                int moreCol = index - sheet.get(0).size();
                for (ArrayList<String> row : sheet) {
                    for(int i = 0; i < moreCol; i++){
                        row.add("");
                    }
                }
            }
       }
    }

    /** Update a cell value 
     * @param row Row index of the cell to be updated
     * @param col Column index of the cell to be updated
     */ 
    public void updateCell(int row, int col, String value) {
        if (row > 0 && row <= sheet.size() && col > 0 && col <= sheet.get(row - 1).size()) {           // Check if the cell location is valid
            sheet.get(row - 1).set(col - 1, value);
        } else {
            System.out.println("Invalid cell location");
        }
    }
    
    /** Delete a cell 
     * @param row Row index of the cell to be deleted
     * @param col Column index of the cell to be deleted
    */
    public void deleteCell(int row, int col) {
        if (row >= 0 && row <= sheet.size() && col >= 0 && col <= sheet.get(row-1).size()) {           // Check if the cell location is valid
            sheet.get(row-1).set(col-1, "");
        } else {
            System.out.println("Invalid cell location");
        }
    }
    
    /** Delete a row 
     * @param row Row index to be deleted
    */
    public void deleteRow(int row) {
        if (row <= sheet.size()) {                                                                    // Check if the row index is valid
            sheet.remove(row-1);
        } else {
            System.out.println("Invalid row index");
        }
    }

    /** Delete a column 
     * @param col Column index to be deleted
    */
    public void deleteColumn(int col) {
        if (!sheet.isEmpty() && col <= sheet.get(0).size()) {          // Check if the column index is valid
            for (ArrayList<String> row : sheet) {                            // Remove the column from each row
                row.remove(col-1);
            }
        } else {
            System.out.println("Invalid column index");
        }
    }

    /** Print Sheet */
    public void printSheet() {
        // Print column numbers
        System.out.print("\t");
        for (int col = 1; col <= sheet.get(0).size(); col++) {
            System.out.print(col + "\t");
        }
        System.out.println();
    
        // Print rows with row numbers
        int rowNum = 1;
        for (ArrayList<String> row : sheet) {
            System.out.print(rowNum++ + "\t"); // Print row number
            for (String cell : row) {
                if (cell.equals("")) {
                    System.out.print("-\t"); // Print empty cell
                } else {
                    System.out.print(cell + "\t"); // Print each cell
                }
            }
            System.out.println();
        }
    }
    
    public static void main(String args[]){
        ExcelSheet obj = new ExcelSheet();
        Scanner sc = new Scanner(System.in);
        int ch; 

        do{
            System.out.println("\nExcel Sheet Operations:");
            // System.out.println("\n");
            System.out.println("\n***************************");

            System.out.println("\n1. Add a cell");
            System.out.println("2. Add a row");
            System.out.println("3. Add a column");
            System.out.println("4. Update a cell");
            System.out.println("5. Delete a cell");
            System.out.println("6. Delete a row");
            System.out.println("7. Delete a column");
            System.out.println("8. Print the sheet");
            System.out.println("9. Exit\n");
            System.out.println("Enter your choice: \n");

            ch = sc.nextInt();
            switch(ch){
                case 1:
                    System.out.println("Enter the row number: ");
                    int row = sc.nextInt();
                    System.out.println("Enter the column number: ");
                    int col = sc.nextInt();
                    System.out.println("Enter the value: ");
                    String value = sc.next();
                    obj.addCell(row, col, value);
                    break;
                case 2:
                    System.out.println("Enter the row index: ");
                    int index = sc.nextInt();
                    obj.addRow(index);
                    break;
                case 3:
                    System.out.println("Enter the column index: ");
                    int colIndex = sc.nextInt();
                    obj.addColumn(colIndex);
                    break;
                case 4:
                    System.out.println("Enter the row number: ");
                    int row1 = sc.nextInt();
                    System.out.println("Enter the column number: ");
                    int col1 = sc.nextInt();
                    System.out.println("Enter the value: ");
                    String value1 = sc.next();
                    obj.updateCell(row1, col1, value1);
                    break;
                case 5:
                    System.out.println("Enter the row number: ");
                    int row2 = sc.nextInt();
                    System.out.println("Enter the column number: ");
                    int col2 = sc.nextInt();
                    obj.deleteCell(row2, col2);
                    break;
                case 6:
                    System.out.println("Enter the row number: ");
                    int row3 = sc.nextInt();
                    obj.deleteRow(row3);
                    break;
                case 7:
                    System.out.println("Enter the column number: ");
                    int col3 = sc.nextInt();
                    obj.deleteColumn(col3);
                    break;
                case 8:
                    obj.printSheet();
                    break;
                case 9:
                    System.exit(0);
                    break;
            }
        } while(ch != 9);
        sc.close();
    }
};