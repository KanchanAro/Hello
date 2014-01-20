/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hello;

import edu.stanford.nlp.ie.AbstractSequenceClassifier;
import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.io.IOUtils;
import edu.stanford.nlp.ling.CoreLabel;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Kanchan
 */
public class Entiites {

    public  HashSet set = new HashSet();

    public HashSet entity() throws IOException {
        //File f = new File("C:\\Users\\Kanchan\\Desktop\\Dilip_Chitre.txt");
        //PrintStream p = new PrintStream(f);
        String serializedClassifier = "C:\\Users\\Kanchan\\Downloads\\stanford-ner-2013-04-04\\stanford-ner-2013-04-04\\classifiers\\english.muc.7class.distsim.crf.ser.gz";

        AbstractSequenceClassifier<CoreLabel> classifier = CRFClassifier.getClassifierNoExceptions(serializedClassifier);

        String fileContents = IOUtils.slurpFile("C:\\Users\\Kanchan\\Documents\\NetBeansProjects\\Reverb\\Dilip_Chitre.txt");
        List<List<CoreLabel>> out = classifier.classify(fileContents);
        String s = (classifier.classifyWithInlineXML(fileContents));
        //System.setOut(p);
        //System.out.println(s);
        //String s = "<a>3</a>";
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
        Pattern pat4 = Pattern.compile("<TIME>(.*?)</TIME>");
        Matcher m4 = pat4.matcher(s);
        while (m4.find()) {
            set.add(m4.group(1)); // => "3"
        }
        Pattern pat5 = Pattern.compile("<PERCENT>(.*?)</PERCENT>");
        Matcher m5 = pat5.matcher(s);
        while (m5.find()) {
            set.add(m5.group(1)); // => "3"
        }
        Pattern pat6 = Pattern.compile("<MONEY>(.*?)</MONEY>");
        Matcher m6 = pat6.matcher(s);
        while (m6.find()) {
            set.add(m6.group(1)); // => "3"
        }
//        for (List<CoreLabel> sentence : out) {
//          
//          for (CoreLabel word : sentence) {
//          if(!word.get(CoreAnnotations.AnswerAnnotation.class).toString().contentEquals("O"))
//           s.add(word.word().toString());
//            }
//          
//        }
//        Iterator i=s.iterator();
       System.out.println(set);
        return set;  
    }
}
