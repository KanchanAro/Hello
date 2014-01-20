/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hello;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
 
/**
 *
 * @author Kanchan
 */
public class Replace {
    public static void main(String args[]) throws FileNotFoundException, IOException{
        File file=new File("C:\\Users\\Kanchan\\Downloads\\Dom_Moraes1.ttl");
        File fw=new File("C:\\Users\\Kanchan\\Downloads\\Dom_Moraes2.ttl");
        PrintWriter writer=new PrintWriter(fw);
        writer.println("@prefix xsd: <http://www.w3.org/2001/XMLSchema>.\n" +
"@base <http://yago-knowledge.org/resource/> .\n" +
"@prefix dbp: <http://dbpedia.org/ontology/> .\n" +
"@prefix owl: <http://www.w3.org/2002/07/owl#> .\n" +
"@prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> .\n" +
"@prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#> .\n" +
"@prefix skos: <http://www.w3.org/2004/02/skos/core#> \n" +
"\n" +
"");
       
        HashMap<String, String> replacements = new HashMap<String, String>();
            replacements.put("http://yago-knowledge.org/resource/", "");
            replacements.put("http://dbpedia.org/ontology/", "");
            replacements.put("http://www.w3.org/2002/07/owl#", "");
            replacements.put("http://www.w3.org/1999/02/22-rdf-syntax-ns#","");
            replacements.put("http://www.w3.org/2000/01/rdf-schema#","");
            replacements.put("http://www.w3.org/2004/02/skos/core#","");
            replacements.put("http://www.w3.org/2001/XMLSchema#","xsd:");
            replacements.put("http://www.w3.org/2001/XMLSchema","xsd:");
            
            replacements.put("@","");
            //replacements.put(" ","> <");
            
       
        Scanner s=new Scanner(file);
        while(s.hasNextLine()){
        //System.out.println("Kanchan");
        StringBuffer str=new StringBuffer(s.nextLine());
        // create the pattern joining the keys with '|'
        String regexp = "http://yago-knowledge.org/resource/|http://dbpedia.org/ontology/|http://www.w3.org/2002/07/owl#|http://www.w3.org/1999/02/22-rdf-syntax-ns#|http://www.w3.org/2000/01/rdf-schema#|http://www.w3.org/2004/02/skos/core#|http://www.w3.org/2001/XMLSchema#|@|http://www.w3.org/2001/XMLSchema";

        StringBuffer sb = new StringBuffer();
        Pattern p = Pattern.compile(regexp);
        Matcher m = p.matcher(str);

        while (m.find())
        m.appendReplacement(sb, replacements.get(m.group()));
        m.appendTail(sb);
        writer.println(sb );
        
       }  
        writer.close();
    }
}
