import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser 
{

    private int index = 0;
    private ArrayList<String> commands;
    private String crntCmd;

    public Parser(String asmF) 
	{
		commands = new ArrayList<String>();
		reset();
		iP(asmF);
    }

    // Reset the parser.  Used prior to the second pass.    
    public void reset() 
	{
		index = -1;
    }

    private void iP(String src) 
	{
		Scanner scanner;
		String line;
		int index;
		try 
		{
			scanner = new Scanner(new File(src));
			while (scanner.hasNextLine())
			{
				line = scanner.nextLine();
				line = line.replaceAll("\\s", "");
				index = line.indexOf("//");
				if (index != -1)
				{
					line = line.substring(0, index);
				}
				if (line.length() > 0) 
				{
					commands.add(line);
					System.out.println("Input: " + line);
				}
			}
		} 
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
			System.exit(1);
		}
	}
    
    public String getCommand()
    {
    	return crntCmd;
    }
    
    public void advance()
    {
    	if (moreCommands())
    	{
    		index++;
    		crntCmd = commands.get(index);
    	}
    	else
		{
			System.out.println("~END~");
		}
    }
    
    public boolean moreCommands()
    {
    	if (index < commands.size()-1) return true;
    	else return false;
    } 
	
	public enum CommandType 
	{
		A_COMMAND, C_COMMAND, L_COMMAND
	}
    
    public CommandType commandType()
    {
    	if (crntCmd.startsWith("@"))
		{
    		return CommandType.A_COMMAND;
    	}
		else if(crntCmd.startsWith("(") && curInstruct.endsWith(")"))
		{
    		return CommandType.L_COMMAND;
    	}
		else
		{
			return CommandType.C_COMMAND;
		}
    }
    
    public String symbol()
    {
    	int endIndex = crntCmd.length()-1;
    	if(commandType() == CommandType.A_COMMAND)
    	{
    		return crntCmd.substring(1);
    	}
    	else if (commandType() == CommandType.L_COMMAND)
    	{
    		return crntCmd.substring(1, endIndex);
    	}
    	else
    	{
    		System.out.println("Command type =! A or L");
    		return "";
    	}
    }
    
    public String dst()
    {
    	if (commandType() == CommandType.C_COMMAND)
    	{
    		if (crntCmd.contains("="))
    		{
    			int i = crntCmd.indexOf("=");
				return crntCmd.substring(0, i);
    		}
    		else
        	{
        		System.out.println("Command isn't dst");
        		return "";
        	}
    	}
    	else 
    	{
    		System.out.println("Command Type isn't C");
    		return "";
    	}
    }
    
    public String comp()
    {
    	if (commandType() == CommandType.C_COMMAND)
    	{
    		if (crntCmd.contains("=") && crntCmd.contains(";"))
    		{
    			int colon = crntCmd.indexOf(";");
    			int sum = crntCmd.indexOf("=");
    			return crntCmd.substring(sum, colon);
    		}
    		else if (crntCmd.contains("="))
        	{
				int i = crntCmd.indexOf("=");
				return crntCmd.substring(i + 1);
        	}
    		else if (crntCmd.contains(";"))
    		{
				int colon = crntCmd.indexOf(";");
    			return crntCmd.substring(0, colon);
    		}
    		else
    		{
    			return crntCmd;
    		}
    	}
    	else 
    	{
    		System.out.println("Instruction isn't C");
    		return "";
    	}
    }
    
    public String jump()
    {
    	if (commandType() == CommandType.C_COMMAND)
    	{
    		if (crntCmd.contains(";"))
    		{
    			int i = crntCmd.indexOf(";");
    			return crntCmd.substring(i + 1);
    		}
    		else
        	{
        		System.out.println("Command doesn't contain a JMP");
        		return "";
        	}
    	}
    	else
    	{
    		System.out.println("Command is not C");
    		return "";
    	}
    }
}