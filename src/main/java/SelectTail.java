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
            result.append(tail(input));
        } else {
            for (String s : inputName) {
                File inputFile = new File(s);
                try {
                    result.append(inputFile.getName()).append("\n").append(tail(new FileInputStream(inputFile)));
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

    public String tail(InputStream input) {
        if (numSymbols == null && numLines == null) numLines = 10;
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        ArrayList<String> listResult = new ArrayList<>();
        StringBuilder result = new StringBuilder();
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                if (line.equals("end")) break;
                if (numSymbols == null) {
                    listResult.add(line);
                    if (listResult.size() > numLines) {
                        listResult.remove(0);
                    }
                } else {
                    result.append(line).append("\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (numSymbols == null) {
            for (String s : listResult) {
                result.append(s).append("\n");
            }
        } else if (result.length() > numSymbols) {
            result.delete(0, (result.length() - 1) - numSymbols);
        }
        return result.toString();
    }
}