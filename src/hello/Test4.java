
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hello;

import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author Kanchan
 */
public class Test4 {

    public static void main(String args[]) throws IOException {

        int n = 1;
        boolean t = true;
        Entiites e = new Entiites();
        Set set = e.entity();
        Jaccard_Cofficient jc = new Jaccard_Cofficient();
        File fn = new File("C:\\Users\\Kanchan\\Desktop\\Abhay_Kumar0.ttl");
        PrintStream p=new PrintStream(fn);
        System.setOut(p);
        String sparqlQueryString1 = "PREFIX dbont: <http://dbpedia.org/ontology/> "
                    + "PREFIX dbp: <http://dbpedia.org/property/>"
                    + "PREFIX  rdfs: <http://www.w3.org/2000/01/rdf-schema#>"
                    + "PREFIX dbpedia: <http://dbpedia.org/resource/>"
                    + "PREFIX geo: <http://www.w3.org/2003/01/geo/wgs84_pos#>"
                    + "CONSTRUCT {"
                    + "dbpedia:Abhay_Kumar" + " ?c2 ?c1.}"
                    + "FROM<http://dbpedia.org>"
                    + "WHERE {"
                    + "dbpedia:Abhay_Kumar "+" ?c2 ?c1."                   
                    + "FILTER (langMatches(lang(?c1),\"en\"))"
                     + "}";
            com.hp.hpl.jena.query.Query query = QueryFactory.create(sparqlQueryString1);
            QueryExecution qexec = QueryExecutionFactory.sparqlService("http://lod2.openlinksw.com/sparql", query);
            Model m=qexec.execConstruct();
          
            StmtIterator st=m.listStatements();
            while(st.hasNext()){
                Statement state=st.nextStatement();
                if(!state.getObject().isLiteral())
                System.out.println("<"+state.getSubject()+">"+" <"+state.getPredicate()+">"+" <"+state.getObject()+">.");
                
                else
                    System.out.println("<"+state.getSubject()+">"+" <"+state.getPredicate()+">"+"\""+state.getObject().asLiteral().getString()+"\""+".");
                 
            }
            while(t){
            File f2 = new File("C:\\Users\\Kanchan\\Desktop\\Abhay_Kumar"+n+".ttl");
            PrintStream ps = new PrintStream(f2);
            File f1 = new File("C:\\Users\\Kanchan\\Desktop\\Abhay_Kumar"+(n-1)+".ttl");
            Model data = ModelFactory.createDefaultModel();
            data.read("C:\\Users\\Kanchan\\Desktop\\Abhay_Kumar0.ttl");
            StmtIterator sta = data.listStatements();
            while (sta.hasNext()) {
                Statement state = sta.nextStatement();
                String s1 = null;
                if (state.getPredicate().asResource().toString().matches("hasGender")) {
                    continue;
                }
                if (state.getObject().isResource()) {
                    s1 = state.getObject().asResource().getLocalName().toString();
                }
                if (state.getObject().isLiteral()) {
                   continue;
                }
                System.setOut(ps);
                String sparqlQueryString2 = "PREFIX dbont: <http://dbpedia.org/ontology/> "
                    + "PREFIX dbp: <http://dbpedia.org/property/>"
                    + "PREFIX  rdfs: <http://www.w3.org/2000/01/rdf-schema#>"
                    + "PREFIX dbpedia: <http://dbpedia.org/resource/>"
                    + "PREFIX geo: <http://www.w3.org/2003/01/geo/wgs84_pos#>"
                    + "CONSTRUCT {"
                    +"dbpedia:"+s1+ " ?c2 ?c1.}"
                    + "FROM<http://dbpedia.org>"
                    + "WHERE {"
                    +"dbpedia:"+s1+ " ?c2 ?c1."
                    + "FILTER (langMatches(lang(?c1),\"en\"))"
                    +"}";
            com.hp.hpl.jena.query.Query query2 = QueryFactory.create(sparqlQueryString2);
            QueryExecution qexec2 = QueryExecutionFactory.sparqlService("http://lod2.openlinksw.com/sparql", query2);
            Model m2=qexec2.execConstruct();
            StmtIterator st2=m2.listStatements();
            while(st2.hasNext()){
                Statement state2=st2.nextStatement();
                if(!state2.getObject().isLiteral())
                System.out.println("<"+state2.getSubject()+">"+" <"+state2.getPredicate()+">"+" <"+state2.getObject()+">.");
                
                else
                    System.out.println("<"+state2.getSubject()+">"+" <"+state2.getPredicate()+">"+"\""+state2.getObject().asLiteral().getString()+"\""+".");
                  }
            }
             st = data.listStatements();
            while (st.hasNext()) {
                Statement state=st.nextStatement();
                String s2 = state.getSubject().getLocalName().toString();
               String sparqlQueryString2 = "PREFIX dbont: <http://dbpedia.org/ontology/> "
                    + "PREFIX dbp: <http://dbpedia.org/property/>"
                    + "PREFIX  rdfs: <http://www.w3.org/2000/01/rdf-schema#>"
                    + "PREFIX dbpedia: <http://dbpedia.org/resource/>"
                    + "PREFIX geo: <http://www.w3.org/2003/01/geo/wgs84_pos#>"
                    + "CONSTRUCT {"
                    + "dbpedia:"+s2+ " ?c2 ?c1.}"
                    + "FROM<http://dbpedia.org>"
                    + "WHERE {"
                    + "dbpedia:"+s2+ " ?c2 ?c1}";
            com.hp.hpl.jena.query.Query query2 = QueryFactory.create(sparqlQueryString2);
            QueryExecution qexec2 = QueryExecutionFactory.sparqlService("http://lod2.openlinksw.com/sparql", query2);
            Model m2=qexec2.execConstruct();
            StmtIterator st2=m2.listStatements();
            while(st2.hasNext()){
                Statement state2=st2.nextStatement();
               if(!state2.getObject().isLiteral())
                System.out.println("<"+state2.getSubject()+">"+" <"+state2.getPredicate()+">"+" <"+state2.getObject()+">.");
                
                else
                    System.out.println("<"+state2.getSubject()+">"+" <"+state2.getPredicate()+">"+"\""+state2.getObject().asLiteral().getString()+"\""+".");
                  }   
            }
            File f3 = new File("C:\\Users\\Kanchan\\Desktop\\Abhay_Kumar" + (n + 1) + ".ttl");
            PrintStream ps1 = new PrintStream(f3);
            Model data2 = ModelFactory.createDefaultModel();

            data2.read("C:\\Users\\Kanchan\\Desktop\\Abhay_Kumar" + n + ".ttl");
            StmtIterator i = data2.listStatements();
            while (i.hasNext()) {
                Statement s = i.next();
                String s1 = s.getSubject().toString().substring(2);
                String s2 = s.getObject().toString().substring(2);
                s1 = s1.replaceAll("_", " ");
                s2 = s2.replaceAll("_", " ");

                Iterator it = set.iterator();
                while (it.hasNext()) {
                    String se = it.next().toString();
                    double jc1 = jc.jaccard(se, s1);
                    if (jc1 < 0.6) {
                        continue;
                    } else {
                        Iterator it2 = set.iterator();
                        while (it2.hasNext()) {
                            String se2 = it2.next().toString();
                            double jc2 = jc.jaccard(se2, s2);
                            if (jc2 > 0.6) {
                               System.setOut(ps1);
                               
                System.out.println("<"+s.getSubject()+">"+" <"+s.getPredicate()+">"+" <"+s.getObject()+">.");
                            }
                else {
                                continue;
                            }
                        }
                    }
                }
            }
            if (f1.length() == f3.length()) {
                t = false;
            }
            n = n + 2;
            }
            
            
   }
}
