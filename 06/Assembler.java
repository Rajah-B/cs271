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
        while (p.moreCom()) {
            p.advance();
            switch (p.comT()) {
            case A_COMMAND:
                System.out.println("A: " + p.curnCom);                
                System.out.println(symbin2(p.sym(),symT));
                out.write(symbin(p.sym(),symT) + "\n");
                break;
            case C_COMMAND:
                System.out.println("C: " + p.curnCom);
                String dst = Code.dst(p.dst());
                String cmp = Code.cmp(p.cmp());
                String jmp = Code.jmp(p.jmp());
                System.out.println("111" + cmp + dst + jmp);
                out.write("111" + cmp + dst + jmp + "\n");
                break;
            case L_COMMAND:
                System.out.println("L: " + p.curnCom);
                System.out.println(symbin(p.sym(),symT));
                out.write(symbin(p.sym(),symT) + "\n");
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

    public static String symbin(String sym,SymbolTable symT)
	{
        if(Parser.isNumeric(sym))
		{
           return Code.getbin(sym);
        }
        symT.addEntry(sym);
        String location =  Integer.toString(symT.getAddress(sym));        
        return Code.getbin(location);
    }
}