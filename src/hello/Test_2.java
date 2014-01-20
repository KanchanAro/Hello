/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hello;

import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.NodeIterator;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author Kanchan
 */
public class Test_2 {
    public static  OntModel data=ModelFactory.createOntologyModel();
    public static  OntModel m=ModelFactory.createOntologyModel();
    
                
    public static void main(String args[]){
         data.read("K:\\Kanchan\\MTech Thesis\\After_adding_yago_knowledge\\Abhay_Kumar_Types.ttl");
         m.read("K:\\Kanchan\\MTech Thesis\\yago2s\\yagoTaxonomy.ttl");
         System.out.println("Kanchan");
              NodeIterator i=data.listObjects();
              
      System.out.println();
      data.close();
     getsuperclasses(i.toSet());
      i.close();
      
     }           
    static void getsuperclasses(Set i){
        System.out.println(i);
        Iterator l=i.iterator();
         while(l.hasNext()){
           Resource r=(Resource)l.next();
             
            //System.out.println(r);
             
            Set k=m.listStatements(r,(Property)null,(RDFNode)null).toSet();
            Iterator n=k.iterator();
            while(n.hasNext()){
                Statement st=(Statement)n.next();
              System.out.println(st);
                //i.add(st.getObject());
                 }
           // getsuperclasses(i);
            System.gc();
            
    }
     
    m.close();
    }
   
    
}
