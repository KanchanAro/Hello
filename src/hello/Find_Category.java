/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hello;

import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import edu.stanford.nlp.ie.AbstractSequenceClassifier;
import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.io.IOUtils;
import edu.stanford.nlp.ling.CoreLabel;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Kanchan
 */
public class Find_Category {
    

    public static void main(String args[]) throws IOException{
        File file = new File("C:\\Users\\Kanchan\\Documents\\NetBeansProjects\\Hello\\India.txt");
        PrintStream is = new PrintStream(file);
        PrintStream orig=System.out;
        String url = "http://en.wikipedia.org/wiki/Indian_Constitution";

        Document doc = Jsoup.connect(url).get();
        Elements paragraphs = doc.select("p");
        Element firstParagraph = doc.select("p").first();

        Element lastParagraph = doc.select("p").last();
        Element p;
        int i = 1;
        p = firstParagraph;
        System.out.println(p);
        System.setOut(is);
        while (p != lastParagraph) {
            System.out.println(p.text());
            p = paragraphs.get(i);
            i++;
        }
        System.setOut(orig);
         
        HashSet set = new HashSet();

        String serializedClassifier = "C:\\Users\\Kanchan\\Downloads\\stanford-ner-2013-04-04\\stanford-ner-2013-04-04\\classifiers\\english.muc.7class.distsim.crf.ser.gz";

        AbstractSequenceClassifier<CoreLabel> classifier = CRFClassifier.getClassifierNoExceptions(serializedClassifier);

        String fileContents = IOUtils.slurpFile("C:\\Users\\Kanchan\\Documents\\NetBeansProjects\\Hello\\India.txt");
        List<List<CoreLabel>> out = classifier.classify(fileContents);
        String s = (classifier.classifyWithInlineXML(fileContents));
         Pattern pat = Pattern.compile("<PERSON>(.*?)</PERSON>");
        Matcher m = pat.matcher(s);
        while (m.find()) {
            set.add(m.group(1)); // => "3"
        }
        Pattern pat1 = Pattern.compile("<LOCATION>(.*?)</LOCATION>");
        Matcher m1 = pat1.matcher(s);
        while (m1.find()) {
            set.add(m1.group(1)); // => "3"
        }
        Pattern pat2 = Pattern.compile("<ORGANIZATION>(.*?)</ORGANIZATION>");
        Matcher m2 = pat2.matcher(s);
        while (m2.find()) {
            set.add(m2.group(1)); // => "3"
        }
        Pattern pat3 = Pattern.compile("<DATE>(.*?)</DATE>");
        Matcher m3 = pat3.matcher(s);
        while (m3.find()) {
          set.add(m3.group(1)); // => "3"
        }
        
     System.out.println(set);
     Iterator it=set.iterator();
     while(it.hasNext()){
        String str = it.next().toString();
        str=str.replaceAll(" ", "_");
           System.out.println(str);
            String sparqlQueryString1 = "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> "
                    + "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
                    + "PREFIX owl: <http://www.w3.org/2002/07/owl#> "
                    + "PREFIX dbp: <http://dbpedia.org/ontology/> "                   
                    + "PREFIX xsd: <http://www.w3.org/2001/XMLSchema>"
                    + "PREFIX skos: <http://www.w3.org/2004/02/skos/core#> "
                    + "PREFIX : <http://yago-knowledge.org/resource/> "
                    + "CONSTRUCT {"
                    + ":"+str+" <rdf:type> ?o.}"
                    + "where {"
                    +":"+ str+" rdf:type ?o ."
                    +"}";
            com.hp.hpl.jena.query.Query query = QueryFactory.create(sparqlQueryString1);
            QueryExecution qexec = QueryExecutionFactory.sparqlService("http://lod2.openlinksw.com/sparql", query);
            Model mod=qexec.execConstruct();
            StmtIterator stmt=mod.listStatements();
            while(stmt.hasNext()){
                Statement state=stmt.nextStatement();
                System.out.println("<"+state.getSubject()+"> "+state.getPredicate()+" <"+state.getObject()+">.");
            }
                qexec.close();
            }
            
     }
    }

