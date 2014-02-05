/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hello;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import edu.stanford.nlp.ie.AbstractSequenceClassifier;
import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.io.IOUtils;
import edu.stanford.nlp.ling.CoreLabel;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
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
public class Similar_Entites {
    public static void main(String args[]) throws IOException{
//        File file = new File("C:\\Users\\Kanchan\\Documents\\NetBeansProjects\\Hello\\India.txt");
//        PrintStream is = new PrintStream(file);
        PrintStream orig=System.out;
//        String url = "http://en.wikipedia.org/wiki/Indian_Constitution";
//
//        Document doc = Jsoup.connect(url).get();
//        Elements paragraphs = doc.select("p");
//        Element firstParagraph = doc.select("p").first();
//
//        Element lastParagraph = doc.select("p").last();
//        Element p;
//        int i = 1;
//        p = firstParagraph;
//        System.out.println(p);
//        System.setOut(is);
//        while (p != lastParagraph) {
//            System.out.println(p.text());
//            p = paragraphs.get(i);
//            i++;
//        }
//        System.setOut(orig);
//         
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
        
        
     System.out.println(set);
     Iterator setit=set.iterator();
     File f=new File("C:\\Users\\Kanchan\\Desktop\\Category1.ttl");
     PrintStream ps=new PrintStream(f);
     System.setOut(ps);
     while(setit.hasNext()){
         String s1=setit.next().toString();
         s1.replaceAll(" ","_");
         String cmd = "FINDSTR " + "\"<"+s1+">\"" + " C:\\Users\\Kanchan\\Desktop\\yagoTypes.ttl";
                Process proc = Runtime.getRuntime().exec(cmd);
                BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
                BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
                String st;
                st = stdInput.readLine();
                    System.out.println(st);

                
                while ((st = stdError.readLine()) != null) {
                    
                }
     }
     System.setOut(orig);
     Model model=ModelFactory.createDefaultModel();
     model.read("C:\\Users\\Kanchan\\Desktop\\Category1.ttl");
     
     HashSet catset1=new HashSet();
     StmtIterator it=model.listStatements();
     while(it.hasNext()){
         Statement state=it.nextStatement();
         catset1.add(state.getObject().toString().substring(12));
     }
//     File file1 = new File("C:\\Users\\Kanchan\\Documents\\NetBeansProjects\\Hello\\USA.txt");
//        PrintStream is1 = new PrintStream(file1);
//        PrintStream orig1=System.out;
//        String url1 = "http://en.wikipedia.org/wiki/British_Constitution";
//
//        Document doc1 = Jsoup.connect(url1).get();
//        Elements paragraphs1 = doc1.select("p");
//        Element firstParagraph1 = doc1.select("p").first();
//
//        Element lastParagraph1 = doc1.select("p").last();
//        Element p1;
//        int j = 1;
//        p1 = firstParagraph1;
//        System.out.println(p1);
//        System.setOut(is1);
//        while (p1 != lastParagraph1) {
//            System.out.println(p1.text());
//            p1 = paragraphs1.get(j);
//            j++;
//        }
//        System.setOut(orig1);
//         
        HashSet set1 = new HashSet();

        String serializedClassifier1 = "C:\\Users\\Kanchan\\Downloads\\stanford-ner-2013-04-04\\stanford-ner-2013-04-04\\classifiers\\english.muc.7class.distsim.crf.ser.gz";

        AbstractSequenceClassifier<CoreLabel> classifier1 = CRFClassifier.getClassifierNoExceptions(serializedClassifier1);

        String fileContents1= IOUtils.slurpFile("C:\\Users\\Kanchan\\Documents\\NetBeansProjects\\Hello\\USA.txt");
        List<List<CoreLabel>> out1 = classifier.classify(fileContents1);
        String s1 = (classifier.classifyWithInlineXML(fileContents1));
          pat = Pattern.compile("<PERSON>(.*?)</PERSON>");
         m = pat.matcher(s1);
        while (m.find()) {
            set1.add(m.group(1)); // => "3"
        }
         pat1 = Pattern.compile("<LOCATION>(.*?)</LOCATION>");
         m1 = pat1.matcher(s1);
        while (m1.find()) {
            set1.add(m1.group(1)); // => "3"
        }
         pat2 = Pattern.compile("<ORGANIZATION>(.*?)</ORGANIZATION>");
         m2 = pat2.matcher(s1);
        while (m2.find()) {
            set1.add(m2.group(1)); // => "3"
        }
        
     System.out.println(set1);
     HashSet sets=new HashSet();
      Iterator itr=set.iterator();
      while(itr.hasNext()){
          String str=itr.next().toString();
          if(set1.contains(str)){
           sets.add(str);
          }
      }
      System.out.println(sets);
    
      double simscr=0;
      int size1=set.size();
      int size2=set1.size();
      int union=size1+size2;
      simscr=(double)sets.size()/union;
      System.out.println("Similarity Score ="+ simscr);
            }
}
