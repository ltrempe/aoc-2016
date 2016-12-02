package agi.aoc.util;

import java.io.*;
import java.net.URL;

public class ResourceLoader {

    public String load(String fileName) {
        FileInputStream inputStream = null;
        String content = "";

        try {

            final ClassLoader classLoader = getClass().getClassLoader();
            final URL url = classLoader.getResource(fileName);

            if (url != null) {
                final File file = new File(url.getFile());
                if (file.exists()) {
                    final StringBuilder sb = new StringBuilder();
                    inputStream = new FileInputStream(file);
                    final BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        sb.append(line);
                    }
                    content = sb.toString();
                    reader.close();
                } else {
                    System.out.println(fileName + " does not exist.");
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return content;
    }
}
