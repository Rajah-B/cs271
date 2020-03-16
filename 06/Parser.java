import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

public class Parser {

    public String[] arrCommand;

    public int arrsize;

    public int curIndex = 0;
    public String curCommand;

    public SymbolTable symT;


    public static enum CommandType {
        A_COMMAND, C_COMMAND, L_COMMAND
    }

    public Parser(String path, SymbolTable sTable) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"));
        String line;
        symT = sTable;
        List<String> list = new ArrayList<String>();
        while (null != (line = in.readLine())) {
            line = line.replaceAll(" ", "");
            if (line.equals("") || line.startsWith("//")) {
                continue;
            }
            if (line.indexOf("//") > 0) {
                line = line.substring(0, line.indexOf("//"));
            }
            if (line.startsWith("(") && line.endsWith(")")) {
                symT.addEntry(line.substring(1, line.length() - 1), curIndex);
                continue;
            }

            curIndex++; 
            list.add(line);
        }
        arrsize = list.size();
        arrCommand = (String[]) list.toArray(new String[arrsize]);
        in.close();

        curIndex = 0;
    }

    public boolean hasMoreCommands() {
        return arrsize - curIndex > 0;
    }
	
    public void advance() {
        curCommand = arrCommand[curIndex++];
    }

    public CommandType commandType() {
        if (curCommand.startsWith("@")) {
            return CommandType.A_COMMAND;
        }

        if (curCommand.indexOf("=") > 0 || curCommand.indexOf(";") > 0) {
            return CommandType.C_COMMAND;
        }

        if (curCommand.startsWith("(") && curCommand.endsWith(")")) {
            return CommandType.L_COMMAND;
        }
        throw new RuntimeException("unknow command '" + curCommand + "'");
    }


    public String symbol() {
        if (curCommand.startsWith("@")) {
            return curCommand.substring(1);
        }

\        return curCommand.substring(1, curCommand.length() - 1);
    }

    public String dest() {
        int index = curCommand.indexOf("=");
        if (index > 0) {
            return curCommand.substring(0, index);
        }
        return "null";
    }

    public String cmp() {
        int indexEqu = curCommand.indexOf("=");
        int indexSem = curCommand.indexOf(";");
        int start = indexEqu > 0 ? indexEqu + 1 : 0;
        int end = indexSem > 0 ? indexSem : curCommand.length();

        return curCommand.substring(start, end);
    }

    public String jmp() {
        int index = curCommand.indexOf(";");
        if (index > 0) {
            return curCommand.substring(index + 1, curCommand.length());
        }
        return "null";
    }

    public static boolean isNumeric(String str) {
        Pattern pa = Pattern.compile("[0-9]*");
        return pa.matcher(str).matches();
    }

}