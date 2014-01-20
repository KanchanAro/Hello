/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hello;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author Kanchan
 */
public class Entity_Matching {

    static Jaccard_Cofficient jc = new Jaccard_Cofficient();

    public static void main(String args[]) throws IOException {
        File f = new File("C:\\Users\\Kanchan\\Desktop\\Dilip_Chitre3.ttl");
        Model data = ModelFactory.createOntologyModel();
        data.read("C:\\Users\\Kanchan\\Desktop\\Dilip_Chitre1.ttl");
        PrintStream p = new PrintStream(f);
        StmtIterator i = data.listStatements();
        Entiites e = new Entiites();
        Set set = e.entity();
        ArrayList<String> a1 = new ArrayList();
        ArrayList<String> a2 = new ArrayList();
       while (i.hasNext()) {
            Statement s = i.next();

           // System.out.println(s);
            String s1 = s.getSubject().getLocalName();
            String s2 = s.getObject().asResource().getLocalName();
            s1 = s1.replaceAll("_", " ");
            s2 = s2.replaceAll("_", " ");
            Iterator it = set.iterator();
            while (it.hasNext()) {
                String se = it.next().toString();
                double jc1=jc.jaccard(se,s1);
                if(jc1<0.5){
                    continue;
                }
                else{
                    Iterator it2=set.iterator();
                    while(it2.hasNext()){
                        String se2=it2.next().toString();
                        double jc2=jc.jaccard(se2,s2);
                        if(jc2>0.5){
                    System.out.println("<" + s.getSubject().getLocalName() + ">" + " <" + s.getPredicate().getLocalName() + ">" + " <" + s.getObject().asResource().getLocalName() + ">" + ".");
                       }
                        else{
                            continue;
                        }
            }
                }   
        }
    }
}
}
