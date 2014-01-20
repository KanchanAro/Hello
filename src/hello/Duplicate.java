/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hello;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Kanchan
 */
public class Duplicate {
    public static void main(String args[]) throws FileNotFoundException, IOException {
        String filename="C:\\Users\\Kanchan\\Downloads\\Dom_Moraes2.ttl";
    BufferedReader reader = new BufferedReader(new FileReader(filename));
    Set<String> lines = new HashSet<String>(10000); // maybe should be bigger
    String line;
    while ((line = reader.readLine()) != null) {
        lines.add(line);
    }
    reader.close();
    BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\Kanchan\\Downloads\\Dom_Moraes3.ttl"));
    for (String unique : lines) {
        writer.write(unique);
        writer.newLine();
    }
    writer.close();
}
}
