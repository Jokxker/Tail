import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class TailTest {
    private void checkContentsFile (String res, String except) {
        try {
            BufferedReader result = new BufferedReader(new FileReader(res));
            BufferedReader exc = new BufferedReader(new FileReader(except));
            String line;
            while ((line = result.readLine()) != null) {
                String lineExc = exc.readLine();
                assertEquals(lineExc, line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void printTail() {
        String[] param = {"-n", "5", "src/main/resources/inputParam.txt", "-o", "src/main/resources/output.txt"};
        new Tail().parser(param);
        checkContentsFile("src/main/resources/output.txt", "src/test/resources/expected.txt");
    }

    @Test
    void printTail2() {
        String[] param = {"-n", "5", "src/main/resources/inputParam2.txt", "src/main/resources/inputParam2.txt", "src/main/resources/inputParam3.txt", "src/main/resources/inputParam4.txt", "-o", "src/main/resources/output2.txt"};
        new Tail().parser(param);
        checkContentsFile("src/main/resources/output2.txt", "src/test/resources/expected2.txt");
    }
}