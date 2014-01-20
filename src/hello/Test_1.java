/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hello;

/**
 *
 * @author Kanchan
 */
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.NodeIterator;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.ResIterator;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Selector;
import com.hp.hpl.jena.rdf.model.SimpleSelector;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;
import com.hp.hpl.jena.vocabulary.RDF;
import java.util.Iterator;

public class Test_1 {

    public static void main(String args[]) {
        int n = 1;
        Model data = ModelFactory.createDefaultModel();
        data.read("K:\\Kanchan\\MTech Thesis\\Fact Files from yago\\Sarojini_Naidu.ttl");

        while (n <= 17) {
            OntModel m = ModelFactory.createOntologyModel();
            m.read("K:\\Kanchan\\MTech Thesis\\yago2s\\yagoTypes\\fileout" + n + ".ttl");



            ResIterator i = data.listSubjects();
            while (i.hasNext()) {
                Resource s = i.next();
                Selector sel = new SimpleSelector(s, RDF.type, (RDFNode) null);


                StmtIterator j = m.listStatements(sel);
                while (j.hasNext()) {
                    Statement st = j.next();
                    System.out.print("<" + st.getSubject() + "> ");
                    System.out.print("<" + st.getPredicate() + "> ");
                    System.out.println("<" + st.getObject() + ">.");

                }
                j.close();
            }
            i.close();



            NodeIterator k = data.listObjects();
            while (k.hasNext()) {
                RDFNode s = k.next();
                if (s.isResource()) {
                    Selector sel = new SimpleSelector(s.asResource(), RDF.type, (RDFNode) null);


                    StmtIterator l = m.listStatements(sel);
                    while (l.hasNext()) {
                        Statement st = l.next();
                        System.out.print("<" + st.getSubject() + "> ");
                        System.out.print("<" + st.getPredicate() + "> ");
                        System.out.println("<" + st.getObject() + ">.");
                    }

                }


            }
            k.close();

            n++;
            m.close();
        }

    }
}
