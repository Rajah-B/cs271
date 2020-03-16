import java.util.HashMap;

public class Code {

    public static final String C_PRIFIX = "111";

    public static HashMap<String, String> dstT;

    public static HashMap<String, String> cmpT;

    public static HashMap<String, String> jmpT;

    static {
        dstT = new HashMap<String, String>();
        cmpT = new HashMap<String, String>();
        jmpT = new HashMap<String, String>();

        dstT.put("null", "000");
        dstT.put("M", "001");
        dstT.put("D", "010");
        dstT.put("MD", "011");
        dstT.put("A", "100");
        dstT.put("AM", "101");
        dstT.put("AD", "110");
        dstT.put("AMD", "111");

        cmpT.put("0", "0101010");
        cmpT.put("1", "0111111");
        cmpT.put("-1", "0111010");
        cmpT.put("D", "0001100");
        cmpT.put("A", "0110000");
        cmpT.put("!D", "0001101");
        cmpT.put("!A", "0110001");
        cmpT.put("-D", "0001111");
        cmpT.put("-A", "0110011");
        cmpT.put("D+1", "0011111");
        cmpT.put("A+1", "0110111");
        cmpT.put("D-1", "0001110");
        cmpT.put("A-1", "0110010");
        cmpT.put("D+A", "0000010");
        cmpT.put("D-A", "0010011");
        cmpT.put("A-D", "0000111");
        cmpT.put("D&A", "0000000");
        cmpT.put("D|A", "0010101");
        cmpT.put("M", "1110000");
        cmpT.put("!M", "1110001");
        cmpT.put("-M", "1110011");
        cmpT.put("M+1", "1110111");
        cmpT.put("M-1", "1110010");
        cmpT.put("D+M", "1000010");
        cmpT.put("D-M", "1010011");
        cmpT.put("M-D", "1000111");
        cmpT.put("D&M", "1000000");
        cmpT.put("D|M", "1010101");

        jmpT.put("null", "000");
        jmpT.put("JGT", "001");
        jmpT.put("JEQ", "010");
        jmpT.put("JGE", "011");
        jmpT.put("JLT", "100");
        jmpT.put("JNE", "101");
        jmpT.put("JLE", "110");
        jmpT.put("JMP", "111");
    }

    public static String dst(String mnemonic) {
        String ret = dstT.get(mnemonic);
        if (ret == null) {
            throw new RuntimeException("Can not find destination  '" + mnemonic + "'");
        }
        return ret;
    }

    public static String comp(String mnemonic) {
        String ret = cmpT.get(mnemonic);
        if (ret == null) {
            throw new RuntimeException("Can not find comp  '" + mnemonic + "'");
        }
        return ret;
    }

    public static String jmp(String mnemonic) {
        String ret = jmpT.get(mnemonic);
        if (ret == null) {
            throw new RuntimeException("Can not find jump '" + mnemonic + "'");
        }
        return ret;
    }

    public static String getbin(String address) {

        String binStr = Integer.toBinaryString(Integer.parseInt(address));
        StringBuffer strB = new StringBuffer();
        for (int i = 0; i < 16 - binStr.length(); i++) {
            strB.append('0');
        }
        return strB.append(binStr).toString();
    }
}