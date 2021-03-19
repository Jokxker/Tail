import java.io.*;
import java.util.ArrayList;

public class SelectTail {
    private final Integer numSymbols;
    private Integer numLines;
    private final String outName;
    private final ArrayList<String> inputName;

    public SelectTail(Integer numSymbols, Integer numLines, String outName, ArrayList<String> inputName) {
        this.numSymbols = numSymbols;
        this.numLines = numLines;
        this.outName = outName;
        this.inputName = inputName;
    }

    public void printTail() {
        if (!inputName.isEmpty()) {
            for (String s : inputName) {
                File file = new File(s);
                if (!file.exists()) throw new RuntimeException("Not file");
            }
        }
        StringBuilder result = new StringBuilder();
        InputStream input = System.in;
        if (inputName.isEmpty()) {
            result.append(lineOrSymbol(input));
        } else {
            for (String s : inputName) {
                File inputFile = new File(s);
                try {
                    result.append(inputFile.getName()).append("\n").append(lineOrSymbol(new FileInputStream(inputFile)));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        if (outName == null) {
            System.out.println(result.toString());
        } else {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(outName))) {
                writer.write(result.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String lineOrSymbol(InputStream input) {
        if (numSymbols == null && numLines == null) numLines = 10;
        if (numSymbols != null) {
            return tailSymbol(input);
        } else {
            return tailLine(input);
        }
    }

    public String tailSymbol(InputStream input) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        StringBuilder result = new StringBuilder();
        String line;
        try {
            while ((line = reader.readLine()).length() != 0) {
                result.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (result.length() > numSymbols) {
            result.delete(0, (result.length() - 1) - numSymbols);
        }
        return result.toString();
    }

    public String tailLine(InputStream input) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        ArrayList<String> listResult = new ArrayList<>();
        StringBuilder result = new StringBuilder();
        String line;
        try {
            while ((line = reader.readLine()).length() != 0) {
                listResult.add(line);
                if (listResult.size() > numLines) {
                    listResult.remove(0);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (String s : listResult) {
            result.append(s).append("\n");
        }
        return result.toString();
    }
}