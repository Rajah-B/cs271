import java.io.*;

public class Assembler {

    public static void main(String[] args) throws IOException {
        String iP = "project/06/Pong/Pong.asm";
        iP = args[0];
        String oP = iP.substring(0,iP.lastIndexOf(".")) + ".hack";
        delFile(oP);
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(oP), "UTF-8"));

        SymbolTable symT = new SymbolTable();
        Parser p = new Parser(iP,symT);
        while (p.hasMoreCommands()) {
            p.advance();
            switch (p.commandType()) {
            case A_COMMAND:
                System.out.println("A: " + p.curCommand);                
                System.out.println(symbolbinary(p.symbol(),symT));
                out.write(symbin(p.symbol(),symT) + "\n");
                break;
            case C_COMMAND:
                System.out.println("C: " + p.curCommand);
                String dest = Code.dest(p.dst());
                String comp = Code.comp(p.cmp());
                String jump = Code.jump(p.jmp());
                System.out.println("111" + cmp + dst + jmp);
                out.write("111" + cmp + dst + jmp + "\n");
                break;
            case L_COMMAND:
                System.out.println("L: " + p.curCommand);
                System.out.println(symbin(p.symbol(),symT));
                out.write(symbin(p.symbol(),symT) + "\n");
                break;

            default:
                break;
            }
        }
        out.close();
    }

    public static void delFile(String path) 
	{
        File file = new File(path);
        if (file.exists() && file.isFile())
            file.delete();
    }

    public static String symbin(String symbol,SymbolTable symT)
	{
        if(Parser.isNumeric(symbol))
		{
           return Code.getbin(symbol);
        }
        symT.addEntry(symbol);
        String address =  Integer.toString(symT.getAddress(symbol));        
        return Code.getbin(address);
    }
}