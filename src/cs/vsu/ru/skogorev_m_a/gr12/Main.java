package cs.vsu.ru.skogorev_m_a.gr12;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

class ReadFile {
    public MyStack<String> readFileToStack(String path) {
        MyStack<String> stack = new MyStack<>();
        if (path != null) {
            try (Scanner read = new Scanner(new File(path))) {
                String line;
                while (read.hasNextLine()) {
                    line = read.nextLine().trim();
                    if (line.length() > 0) {
                        line = line.replaceAll("\\p{Punct}", " ").trim();
                        for (String word : line.split("\\s+")) {
                            stack.push(word);
                        }
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("ERROR: File not found.");
            }
        }
        return stack;
    }

    public MyStack<String> getMaxLines(MyStack<String> stack) {
        MyStack<String> result = new MyStack<>();
        int max = 0;
        for (String word : stack) {
            if (max == word.length()) {
                result.push(word);
            } else if (max < word.length()) {
                result.clear();
                max = word.length();
                result.push(word);
            }
        }
        return result;
    }
}


class MyStackTest {
    private static final String PATH = "test.txt";

    public static void main(String[] args) {
        ReadFile read = new ReadFile();
        MyStack<String> words = read.readFileToStack(PATH);
        System.out.println("All words: " + words + ";");

        MyStack<String> stackTask = read.getMaxLines(words);
        System.out.println("Max length words: " + stackTask + ";");
    }
}