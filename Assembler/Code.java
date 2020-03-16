import java.util.Hashtable;


public class Code 
{
	
	Hashtable<String, String> compT = new Hashtable<String, String>();
	Hashtable<String, String> jmpT = new Hashtable<String, String>();
	Hashtable<String, String> dstT = new Hashtable<String, String>();
	
	public Code()
	{
		jmpT.put("NULL", "000");
		jmpT.put("JGT", "001");
		jmpT.put("JEQ", "010");
		jmpT.put("JGE", "011");
		jmpT.put("JLT","100");
		jmpT.put("JNE","101");
		jmpT.put("JLE","110");
		jmpT.put("JMP", "111");
		
		dstT.put("NULL","000");
		dstT.put("M","001");
		dstT.put("D","010");
		dstT.put("MD","011");
		dstT.put("A","100");
		dstT.put("AM","101");
		dstT.put("AD","110");
		dstT.put("AMD","111");	
		
		compT.put("0", "0101010");
        compT.put("1", "0111111");
        compT.put("-1", "0111010");
        compT.put("D", "0001100");
        compT.put("A", "0110000");
        compT.put("!D", "0001101");
        compT.put("!A", "0110001");
        compT.put("-D", "0001111");
        compT.put("-A", "0110011");
        compT.put("D+1", "0011111");
        compT.put("A+1", "0110111");
        compT.put("D-1", "0001110");
        compT.put("A-1", "0110010");
        compT.put("D+A", "0000010");
        compT.put("D-A", "0010011");
        compT.put("A-D", "0000111");
        compT.put("D&A", "0000000");
        compT.put("D|A", "0010101");
        compT.put("M", "1110000");
        compT.put("!M", "1110001");
        compT.put("-M", "1110011");
        compT.put("M+1", "1110111");
        compT.put("M-1", "1110010");
        compT.put("D+M", "1000010");
        compT.put("D-M", "1010011");
        compT.put("M-D", "1000111");
        compT.put("D&M", "1000000");
        compT.put("D|M", "1010101");
	}
	public String comp(String input)
	{
		return jmpT.get(input);
	}
	public String comp(String input)
	{
		return compT.get(input);
	}
	
	public String comp(String input)
	{
		return dstT.get(input);
	}
}