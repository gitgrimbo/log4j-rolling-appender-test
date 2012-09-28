package grimbo.test.log4j.rollingappender;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class Verify {
    String[] filenames;
    Map<String, int[]> counters;
    String curFilename;
    int curLineNumber;

    Verify(String[] filenames) {
        this.filenames = filenames;
    }

    public void verify() throws FileNotFoundException, IOException {
        counters = new HashMap<String, int[]>();
        accumulate(filenames);
        curFilename = null;
        curLineNumber = -1;
    }

    void accumulate(String[] filenames) throws FileNotFoundException, IOException {
        for (int i = 0; i < filenames.length; i++) {
            accumulate(filenames[i]);
        }
    }

    void accumulate(String filename) throws FileNotFoundException, IOException {
        accumulate(new File(filename));
    }

    void accumulate(File file) throws FileNotFoundException, IOException {
        curFilename = file.getAbsolutePath();
        accumulate(new FileReader(file));
    }

    void accumulate(Reader r) throws IOException {
        accumulate((r instanceof BufferedReader) ? (BufferedReader) r : new BufferedReader(r));
    }

    void accumulate(BufferedReader r) throws IOException {
        curLineNumber = 0;
        String line = r.readLine();
        while (null != line) {
            processLine(line);
            line = r.readLine();
        }
    }

    void processLine(String line) {
        String[] IGNORE = { "started", "ended" };
        for (int i = 0; i < IGNORE.length; i++) {
            if (line.indexOf(IGNORE[i]) > -1) {
                return;
            }
        }
        // [2012-09-28 11:46:00,000][Thread:mialog4j[4][DEBUG]mialog4j[4] 17019
        final String FIND = "[DEBUG]";
        int idx = line.lastIndexOf(FIND);
        line = line.substring(idx + FIND.length());
        idx = line.lastIndexOf(" ");
        String name = line.substring(0, idx);
        int counter = Integer.parseInt(line.substring(idx + 1), 10);
        int[] current = counters.get(name);
        if (null == current) {
            current = new int[1];
            counters.put(name, current);
        }
        int expected = current[0];
        if (counter != expected) {
            String msg = "counters[" + name + "]. Expected=" + expected + ", found=" + counter;
            throw new IllegalStateException(msg);
        }
        current[0]++;
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        Verify v = new Verify(args);
        try {
            v.verify();
        } catch (IOException e) {
            System.out.println("Error in " + v.curFilename + " at line " + v.curLineNumber);
        } finally {
            for (Iterator<Entry<String, int[]>> it = v.counters.entrySet().iterator(); it.hasNext();) {
                Entry<String, int[]> e = it.next();
                System.out.println(e.getKey() + "=" + e.getValue()[0]);
            }
        }
    }
}
