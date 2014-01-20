/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author kanchan
 */
package hello;

import java.util.List;
import java.io.File;
import java.util.ArrayList;

import edu.stanford.nlp.process.DocumentPreprocessor;
import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.ling.Sentence;
import edu.stanford.nlp.trees.*;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
import edu.stanford.nlp.trees.tregex.TregexMatcher;
import edu.stanford.nlp.trees.tregex.TregexPattern;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URL;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
//import sun.reflect.generics.tree.Tree;

class ParserDemo {

    /**
     * The main method demonstrates the easiest way to load a parser. Simply
     * call loadModel and specify the path, which can either be a file or any
     * resource in the classpath. For example, this demonstrates loading from
     * the models jar file, which you need to include in the classpath for
     * ParserDemo to work.
     */
    public static void main(String[] args) throws MalformedURLException, IOException {
        //URL yahoo = new URL("http://en.wikipedia.org/wiki/Ashok_Sawhny");
        File file = new File("Ashok_Sawhny.txt");
        FileWriter writer = new FileWriter(file,true);
 
	
Document doc = Jsoup.connect("http://en.wikipedia.org/wiki/Ashok_Sawhny").get();
//Document doc = Jsoup.connect(url).get();
    Elements paragraphs = doc.select(".mw-content-ltr p");

    Element firstParagraph = paragraphs.first();
    Element lastParagraph = paragraphs.last();
    Element p;
    int i=1;
    p=firstParagraph;
    System.out.println(p.text());
    while (p!=lastParagraph){
        p=paragraphs.get(i);
        
               		writer.write(p.text());
        System.out.println(p.text());
        i++;
    }     
    writer.close();
    LexicalizedParser lp = LexicalizedParser.loadModel("edu/stanford/nlp/models/lexparser/englishPCFG.ser.gz");
     demoDP(lp, "Ashok_Sawhny.txt");
    }

    /**
     * demoDP demonstrates turning a file into tokens and then parse trees. Note
     * that the trees are printed by calling pennPrint on the Tree object. It is
     * also possible to pass a PrintWriter to pennPrint if you want to capture
     * the output.
     */
    public static void demoDP(LexicalizedParser lp, String filename) {
        // This option shows loading and sentence-segmenting and tokenizing
        // a file using DocumentPreprocessor.
        TreebankLanguagePack tlp = new PennTreebankLanguagePack();
        GrammaticalStructureFactory gsf = tlp.grammaticalStructureFactory();
        StringBuffer output = new StringBuffer();

        for (List<HasWord> sentence : new DocumentPreprocessor(filename)) {
            Tree parse = (Tree) lp.apply(sentence);// generates parse tree for each sentence.
            parse.pennPrint();
            System.out.println();
         //   output.append(GetNounPhrases2(parse));
            
        }
        System.out.println(output);
    }

    
//    public static String GetNounPhrases2(Tree parse) {
//
//        List<Tree> phraseList = new ArrayList<Tree>();
//        List<Tree> childrenList = new ArrayList<Tree>();
//        List<Tree> abt = new ArrayList<Tree>();
//        Trees T;
//        Tree[] childt;
//        TregexPattern NPpattern = TregexPattern.compile("NP < NN | < NNP");// pattern of NP node containing NN OR NNP as child
//        TregexMatcher matcher = NPpattern.matcher(parse);
//        while (matcher.findNextMatchingNode()) {
//            Tree match = matcher.getMatch(); //matching pattern  
//            childt = match.children(); //getting children of NP node 
//            for (Tree subtree2 : childt) {
//                String v = subtree2.label().value();
//                if ((v.equals("NN") || v.equals("NNP"))) {
//                    childrenList.add(subtree2);// adding the children of containg label as NN or NNP 
//                }
//            }
//            match.setChildren(childrenList); //  setting as the new children 
//            childrenList.clear();
//        }
//        parse.pennPrint();
//        List out = new ArrayList();
//        System.out.println("Output Text:");
//        return Sentence.listToString(parse.yield());

//    }


    private ParserDemo() {
    } // static methods only
}