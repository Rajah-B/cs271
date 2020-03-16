import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

public class Parser {

    public String[] arCom;

    public int arSize;

    public int curnIndex = 0;
    public String curnCom;

    public SymbolTable symT;


    public static enum CommandType 
	{
        A_COMMAND, C_COMMAND, L_COMMAND
    }

    public Parser(String path, SymbolTable sTable) throws IOException 
	{
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"));
        String line;
        symT = sTable;
        List<String> list = new ArrayList<String>();
        while (null != (line = in.readLine())) {
            line = line.replaceAll(" ", "");
            if (line.equals("") || line.startsWith("//")) 
			{
                continue;
            }
            if (line.indexOf("//") > 0) 
			{
                line = line.substring(0, line.indexOf("//"));
            }
            if (line.startsWith("(") && line.endsWith(")")) 
			{
                symT.addEntry(line.substring(1, line.length() - 1), curIndex);
                continue;
            }

            curnIndex++; 
            list.add(line);
        }
        aSize = list.size();
        arCom = (String[]) list.toArray(new String[arSize]);
        in.close();

        curnIndex = 0;
    }

    public boolean moreCom() {
        return arSize - curnIndex > 0;
    }
	
    public void advance() {
        curCom = arCom[curIndex++];
    }

    public CommandType comT() {
        if (curnCom.startsWith("@")) 
		{
            return CommandType.A_COMMAND;
        }

        if (curnCom.indexOf("=") > 0 || curnCom.indexOf(";") > 0) 
		{
            return CommandType.C_COMMAND;
        }

        if (curnCom.startsWith("(") && curnCom.endsWith(")")) 
		{
            return CommandType.L_COMMAND;
        }
        throw new RuntimeException("unknow command '" + curnCom + "'");
    }


    public String sym() {
        if (curnCom.startsWith("@")) 
		{
            return curnCom.substring(1);
        }
		return curnCom.substring(1, curnCom.length() - 1);
    }

    public String dest() {
        int index = curnCom.indexOf("=");
        if (index > 0) 
		{
            return curnCom.substring(0, index);
        }
        return "null";
    }

    public String cmp() 
	{
        int indexEqu = curnCom.indexOf("=");
        int indexSem = curnCom.indexOf(";");
        int start = indexEqu > 0 ? indexEqu + 1 : 0;
        int end = indexSem > 0 ? indexSem : curnCom.length();

        return curnCom.substring(start, end);
    }

    public String jmp() 
	{
        int index = curnCom.indexOf(";");
        if (index > 0)
		{
			return curnCom.substring(index + 1, curnCom.length());
		}
        return "null";
    }

    public static boolean isNum(String str)
	{
        Pattern pa = Pattern.compile("[0-9]*");
        return pa.matcher(str).matches();
    }

}