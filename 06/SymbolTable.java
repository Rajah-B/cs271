package assembler2;
import java.util.HashMap;


public class SymbolTable {

    private HashMap<String, Integer> symTable;

    private int index = 16;

    public SymbolTable(){
        symTable = new HashMap<String, Integer>();
        symTable.put("R0", 0);
		symTable.put("R1", 1);
		symTable.put("R2", 2);
		symTable.put("R3", 3);
		symTable.put("R4", 4);
		symTable.put("R5", 5);
		symTable.put("R6", 6);
		symTable.put("R7", 7);
		symTable.put("R8", 8);
		symTable.put("R9", 9);
		symTable.put("R10", 10);
		symTable.put("R11", 11);
		symTable.put("R12", 12);
		symTable.put("R13", 13);
		symTable.put("R14", 14);
		symTable.put("R15", 15);
		
		symTable.put("SP", 0);
		symTable.put("LCL", 1);
		symTable.put("ARG", 2);
		symTable.put("THIS", 3);
		symTable.put("THAT", 4);
		symTable.put("SCREEN", 16384);
		symTable.put("KBD", 24576);
    }

    
    public void addEntry(String sym,int location){
        if(contains(sym)){
            return;
        }
        symTable.put(sym, location);
    }

    public void addEntry(String sym){        
        if(contains(sym)){
            return;
        }
        symTable.put(sym, index++);
    }

    public boolean contains(String sym){
        return symTable.containsKey(sym);
    }

    public int getAddress(String sym){
        return symTable.get(sym);
    }
}