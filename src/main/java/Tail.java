import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.util.ArrayList;

public class Tail {
    @Option(name = "-c", usage = "numSymbols", forbids = "-n")
    private Integer numSymbols;
    @Option(name = "-n", usage = "numLines", forbids = "-c")
    private Integer numLines;
    @Option(name = "-o", metaVar = "String", usage = "outName")
    private String outName;
    @Argument(usage = "inpName", metaVar = "ArrayList<String>")
    private ArrayList<String> inpName = new ArrayList<>();

    public static void main(String[] args) {
        new Tail().parser(args);
    }

    public void parser(String[] args) {
        CmdLineParser pars = new CmdLineParser(this);
        try {
            pars.parseArgument(args);
            if (numSymbols != null && numSymbols < 1 || numLines != null && numLines < 1) {
                throw new RuntimeException("Invalid parameters");
            }
        } catch (CmdLineException e) {
            e.printStackTrace();
        }
        SelectTail result = new SelectTail(numSymbols, numLines, outName, inpName);
        result.printTail();
    }
}