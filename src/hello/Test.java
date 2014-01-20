/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hello;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 *
 * @author Kanchan
 */
public class Test {

    public static void main(String args[]) throws FileNotFoundException, UnsupportedEncodingException, IOException {
        File file1 = new File("K:\\Kanchan\\MTech Thesis\\yago2s\\yagoTaxonomy.ttl");
        FileInputStream inputStream = new FileInputStream(file1);
        InputStreamReader streamReader = new InputStreamReader(inputStream, "UTF-8");
        BufferedReader reader = new BufferedReader(streamReader);
        int n = 1;
        
        System.out.println(file1.length());
        while (n<=(file1.length()/1048576)+1) {
            File file = new File("K:\\Kanchan\\MTech Thesis\\yago2s\\yagoTaxonomy\\fileout" + n + ".ttl");
            PrintWriter out = new PrintWriter(file);
            if(n!=1){
            out.println("@base <http://yago-knowledge.org/resource/> .\n"
                        + "@prefix dbp: <http://dbpedia.org/ontology/> .\n"
                        + "@prefix owl: <http://www.w3.org/2002/07/owl#> .\n"
                        + "@prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> .\n"
                        + "@prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#> .\n"
                        + "@prefix skos: <http://www.w3.org/2004/02/skos/core#> .\n"
                        + "@prefix xsd: <http://www.w3.org/2001/XMLSchema#> .");
            
            }
            
            while (file.length() <=1048576 ) {
                String line = reader.readLine();
                
                out.println(line);
                System.out.println(file.length());
            }
            
            n++;
            out.close();
        }

        reader.close();
        streamReader.close();
        inputStream.close();
    }
}
