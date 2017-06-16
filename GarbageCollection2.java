package garbagecollection2;

import java.util.Scanner;

public class GarbageCollection2 {
    
    public static int DSsearch(String[] DSnames,String key){
        for(int i=0;i<DSnames.length;i++){
            if(DSnames[i].equals(key))
                return i;
        }
        return -1;
    }
    
    public static void addVariable(String[] DSnames, int[]DS){
        Scanner reader = new Scanner (System.in);
        System.out.print("Input your variable name: ");
        String userInputVarName = reader.nextLine();
        System.out.print("Input value: ");
        int userInputValue = reader.nextInt();
        
        if(DSsearch(DSnames,userInputVarName)>-1){
            System.out.println("ERROR! Duplicate Variable Name\n\n\n");
            return;
        }
        int i= DSsearch(DSnames,"__invalid__");
        if(i>-1){
            DSnames[i]=userInputVarName;
            DS[i]=userInputValue;
            return;
        }           
        System.out.println("Array is full");
    }
    
    public static void removeVariable(String[] DSnames, int[]DS){
        Scanner reader = new Scanner (System.in);
        System.out.print("Input your variable name: ");
        String userInputVarName = reader.nextLine();
        int i = DSsearch(DSnames,userInputVarName);
        if (i>-1){
            DSnames[i]="__invalid__";
            DS[i]=0;
            return;
        }
        System.out.println("Invalid Variable Name");
    }
    
    public static void garbageCollection(String[] DSnames, int[]DS){
        /*
        for (int j = 0; j<DSnames.length; j++){
            for(int i=0;i<DSnames.length-1;i++){
                if(DSnames[i].equals("__invalid__")){
                    DSnames[i]=DSnames[i+1];
                    DS[i]=DS[i+1];
                    DSnames[i+1]="__invalid__";
                    DS[i+1]=0;
                }
            }
        }
                */
        int pointer = DSsearch(DSnames,"__invalid__");
        if(pointer==-1){
            System.out.println("Garbage Collection Not Needed");
            return;
        }
        for (int i = pointer+1; i<DSnames.length;i++){
            if(DSnames[i]!="__invalid__"){
                DSnames[pointer]=DSnames[i];
                DSnames[i]="__invalid__";
                //DS[i]=0;
                pointer++;
            }
        }
        System.out.println("Garbage collection done.");
    }
    
    public static void usageStatistics(String[] DSnames){
        int invalidCounter = 0;
        int filledSpaceCounter = 0;
        for(int i = 0; i<DSnames.length;i++){
            if(DSnames[i].equals("__invalid__"))
                invalidCounter++;
            else
                filledSpaceCounter++;
        }
        
        int holeCounter = 0;
        if(DSnames[0].equals("__invalid__"))
            holeCounter = 1;
        else
            holeCounter = 0;
            
        for (int i = 1; i<DSnames.length;i++){
            if(DSnames[i].equals("__invalid__"))
                if(DSnames[i-1]!=("__invalid__"))
                    holeCounter++;
                //if the current is invalid and the previous is not invalid
        }
        
        System.out.println("Number of used spaces: "+ filledSpaceCounter);
        System.out.println("Number of empty spaces: "+ invalidCounter);
        System.out.println("Number of holes: "+ holeCounter);
    }
 
    public static void printArray(String[] DSnames, int[] DS){
        for (int i = 0; i<DS.length; i++){
            System.out.println(i+ ": "+ DSnames[i] + " = " + DS[i]);
        }
    }
    
    public static void main(String[] args) {
        Scanner reader = new Scanner (System.in);
        int [] DS= new int [8];
        String [] DSnames = new String [8];
        for (int i = 0; i<DSnames.length;i++){
            DSnames[i]=("__invalid__");
        }

        while (true){
        System.out.println("Memory Manager:\n(a) Add new variable \n"+ 
                             "(b) Delete variable \n"+
                             "(c) Garbage Collection \n"+
                             "(d) Print usage statistics \n"+
                             "(e) Print entire DS \n"+
                             "(f) Exit program \n");
        System.out.print("Input choice: ");
        String userInput = reader.nextLine();
        
        
        if (userInput.equals("a")){
            addVariable(DSnames, DS);
        }
        else if (userInput.equals("b")){         
            removeVariable(DSnames, DS);
        }
        else if (userInput.equals("c")){     
            garbageCollection(DSnames, DS);     
        }
        else if (userInput.equals("d")){
            usageStatistics(DSnames);
        }
        else if (userInput.equals("e")){
            printArray(DSnames, DS);
        }
        else if (userInput.equals("f"))
            break;
        else
            System.out.println("Input not recognized. Please try again.");
        }
    }  
}