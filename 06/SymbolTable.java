import java.util.HashMap;

public class SymbolTable 
{
	private HashMap<String,Integer> sT = new HashMap<String,Integer>();
	
	public SymbolTable()
	{
		sT.put("R0", 0); sT.put("R1", 1); sT.put("R2", 2); sT.put("R3", 3); sT.put("R4", 4);
		
        sT.put("R5", 5); sT.put("R6", 6); sT.put("R7", 7); sT.put("R8", 8); sT.put("R9", 9);
		
        sT.put("R10", 10); sT.put("R11", 11); sT.put("R12", 12); sT.put("R13", 13); sT.put("R14", 14); sT.put("R15", 15);	
		
		sT.put("SP", 0); sT.put("LCL", 1); sT.put("ARG", 2); sT.put("THIS", 3);
		
        sT.put("THAT", 4); sT.put("SCREEN", 16384); sT.put("KBD", 24576);
		
	}

	public void addEntry(String symbol, int address) 
	{	
		symbolTable.put(symbol, address);
	}
	
	public boolean contains(String symbol)
	{
		return symbolTable.containsKey(symbol);
	}
	
	public int getAddress(String symbol) 
	{
		return symbolTable.get(symbol);
	}
}