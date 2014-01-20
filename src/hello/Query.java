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
import com.hp.hpl.jena.rdf.model.NodeIterator;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.ResIterator;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Iterator;

/**
 *
 * @author Kanchan
 */
public class Query {

    public static void main(String args[]) throws FileNotFoundException, IOException {
        Model data = ModelFactory.createDefaultModel();
        data.read("C:\\Users\\Kanchan\\Desktop\\Abhay_Kumar0.ttl");
        
        File file=new File("C:\\Users\\Kanchan\\Desktop\\Abhay_KumarFD.ttl");
        PrintStream p=new PrintStream(file);
        ResIterator i = data.listSubjects();
        System.setOut(p);
        StmtIterator sta=data.listStatements();
        while(sta.hasNext()){
            Statement stat=sta.nextStatement();
            if(!stat.getObject().isLiteral())
           System.out.println("<"+stat.getSubject()+">"+" <"+stat.getPredicate()+"> <"+stat.getObject()+">.");
           else
            System.out.println("<"+stat.getSubject()+">"+" <"+stat.getPredicate()+"> " +"\""+stat.getObject().asLiteral().getString()+"\""+".");
           
        }
        while (i.hasNext()) {
            String s = i.next().getLocalName().toString();
           // System.out.println(s);
            String sparqlQueryString1 = "PREFIX dbont: <http://dbpedia.org/ontology/> "
                    + "PREFIX dbp: <http://dbpedia.org/property/>"
                    + "PREFIX  rdfs: <http://www.w3.org/2000/01/rdf-schema#>"
                    + "PREFIX dbpedia: <http://dbpedia.org/resource/>"
                    + "PREFIX geo: <http://www.w3.org/2003/01/geo/wgs84_pos#>"
                    + "CONSTRUCT {"
                    + "dbpedia:" + s + "<rdf:type> ?c1 }"
                    + "FROM<http://dbpedia.org>"
                    + "WHERE {"
                    + "dbpedia:" + s + " a ?c1."
                    + "}";
            com.hp.hpl.jena.query.Query query = QueryFactory.create(sparqlQueryString1);
            QueryExecution qexec = QueryExecutionFactory.sparqlService("http://lod2.openlinksw.com/sparql", query);
//            Iterator ir = qexec.execConstructTriples();
//            while (ir.hasNext()) {
//                System.out.println(ir.next());
//            }
            Model m=qexec.execConstruct();
            StmtIterator st=m.listStatements();
            while(st.hasNext()){
                Statement state=st.nextStatement();
                System.out.println("<"+state.getSubject()+">"+state.getPredicate()+" <"+state.getObject()+">.");
            }
        }
        ResIterator is = data.listSubjects();
        while (is.hasNext()) {
            String ss = is.next().getLocalName().toString();
           // System.out.println(ss);
            String sparqlQueryString2 = "PREFIX dbont: <http://dbpedia.org/ontology/> "
                    + "PREFIX dbp: <http://dbpedia.org/property/>"
                    + "PREFIX  rdfs: <http://www.w3.org/2000/01/rdf-schema#>"
                    + "PREFIX dbpedia: <http://dbpedia.org/resource/>"
                    + "PREFIX geo: <http://www.w3.org/2003/01/geo/wgs84_pos#>"
                    + "CONSTRUCT {"
                    + " ?c1 rdfs:subClassOf ?c2 .  "
                    + "}"
                    + "FROM<http://dbpedia.org>"
                    + "WHERE {"
                    + "dbpedia:" + ss + " a ?c1 ; a ?c2."
                    + "?c1 rdfs:subClassOf ?c2 ."
                    + "}";
            com.hp.hpl.jena.query.Query query2 = QueryFactory.create(sparqlQueryString2);
            QueryExecution qexec2 = QueryExecutionFactory.sparqlService("http://lod2.openlinksw.com/sparql", query2);
//            Iterator irt = qexec2.execConstructTriples();
//            while (irt.hasNext()) {
//                System.out.println(irt.next());
//            }
            Model m=qexec2.execConstruct();
            StmtIterator st=m.listStatements();
            while(st.hasNext()){
                Statement state=st.nextStatement();
                System.out.println("<"+state.getSubject()+">"+" <"+state.getPredicate()+">"+" <"+state.getObject()+">.");
            }
            qexec2.close();
        }

        NodeIterator o = data.listObjects();
        while (o.hasNext()) {

            RDFNode s = o.next();
            if (s.isResource()) {
                String st = s.asResource().getLocalName().toString();
                //System.out.println(st);
                String sparqlQueryString1 = "PREFIX dbont: <http://dbpedia.org/ontology/> "
                        + "PREFIX dbp: <http://dbpedia.org/property/>"
                        + "PREFIX  rdfs: <http://www.w3.org/2000/01/rdf-schema#>"
                        + "PREFIX dbpedia: <http://dbpedia.org/resource/>"
                        + "PREFIX geo: <http://www.w3.org/2003/01/geo/wgs84_pos#>"
                        + "CONSTRUCT {"
                        + "dbpedia:" + st + " <rdf:type> ?c1 .  "
                        + "}"
                        + "FROM<http://dbpedia.org>"
                        + "WHERE {"
                        + "dbpedia:" + st + " a ?c1 ;"
                        + "}";
                com.hp.hpl.jena.query.Query query = QueryFactory.create(sparqlQueryString1);
                QueryExecution qexec = QueryExecutionFactory.sparqlService("http://lod2.openlinksw.com/sparql", query);
//                Iterator io = qexec.execConstructTriples();
//
//                while (io.hasNext()) {
//                    System.out.println(io.next());
//                }
                Model m=qexec.execConstruct();
            StmtIterator stmt=m.listStatements();
            while(stmt.hasNext()){
                Statement state=stmt.nextStatement();
                System.out.println("<"+state.getSubject()+">"+state.getPredicate()+" <"+state.getObject()+">.");
            }
                qexec.close();
            }
        }
        NodeIterator o1 = data.listObjects();
        while (o1.hasNext()) {

            RDFNode s1 = o1.next();
            if (s1.isResource()) {
                String st = s1.asResource().getLocalName().toString();
               // System.out.println(st);
                String sparqlQueryString1 = "PREFIX dbont: <http://dbpedia.org/ontology/> "
                        + "PREFIX dbp: <http://dbpedia.org/property/>"
                        + "PREFIX  rdfs: <http://www.w3.org/2000/01/rdf-schema#>"
                        + "PREFIX dbpedia: <http://dbpedia.org/resource/>"
                        + "PREFIX geo: <http://www.w3.org/2003/01/geo/wgs84_pos#>"
                        + "CONSTRUCT {"
                        + " ?c1 rdfs:subClassOf ?c2 .  "
                        + "}" + "FROM<http://dbpedia.org>"
                        + "WHERE {"
                        + "dbpedia:" + st + " a ?c1 ; a ?c2."
                        + "?c1 rdfs:subClassOf ?c2 ."
                        + "}";
                com.hp.hpl.jena.query.Query query = QueryFactory.create(sparqlQueryString1);
                QueryExecution qexec = QueryExecutionFactory.sparqlService("http://lod2.openlinksw.com/sparql", query);
//                Iterator io = qexec.execConstructTriples();
//
//                while (io.hasNext()) {
//                    System.out.println(io.next());
//                }
                Model m=qexec.execConstruct();
            StmtIterator stmt=m.listStatements();
            while(stmt.hasNext()){
                Statement state=stmt.nextStatement();
                System.out.println("<"+state.getSubject()+">"+" <"+state.getPredicate()+">"+" <"+state.getObject()+">.");
            }
                qexec.close();
            }
        }
        StmtIterator sti=data.listStatements();
        while(sti.hasNext()){
            Statement state=sti.next();
            String str=state.getPredicate().getURI().toString();
            String cmd = "FINDSTR " + "\"<"+str+">\"" + " C:\\Users\\Kanchan\\Downloads\\raw_infobox_property_definitions_en.ttl\\raw_infobox_property_definitions_en.ttl";
                //System.out.println(cmd);
                Process proc = Runtime.getRuntime().exec(cmd);
                BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
                BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
                String s = null;
                while ((s = stdInput.readLine()) != null) {
                    System.out.println(s);

                }
                while ((s = stdError.readLine()) != null) {
                      }
        }
        StmtIterator stit=data.listStatements();
       while(stit.hasNext()){
         Statement pr=stit.next();
        String str=pr.getPredicate().getLocalName().toString();

        String sparqlQueryString1 = "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> "
                    + "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
                    + "PREFIX owl: <http://www.w3.org/2002/07/owl#> "
                    + "PREFIX dbp: <http://dbpedia.org/ontology/> "                   
                    + "PREFIX xsd: <http://www.w3.org/2001/XMLSchema>"
                    + "PREFIX skos: <http://www.w3.org/2004/02/skos/core#> "
                    + "PREFIX : <http://yago-knowledge.org/resource/> "
                    + "CONSTRUCT {"
                    + "dbp:"+str+" <rdfs:domain> ?o1.}"
                    + "where {"
                    +"dbp:"+ str+" rdfs:domain ?o1. "
                    +"dbp:"+ str+" rdf:type owl:ObjectProperty"
                   
                    +"}";
            com.hp.hpl.jena.query.Query query = QueryFactory.create(sparqlQueryString1);
            QueryExecution qexec = QueryExecutionFactory.sparqlService("http://lod2.openlinksw.com/sparql", query);
            Model m=qexec.execConstruct();
            StmtIterator stmt=m.listStatements();
            while(stmt.hasNext()){
                Statement state=stmt.nextStatement();
                System.out.println("<"+state.getSubject()+"> "+state.getPredicate()+ " <"+state.getObject()+">.");
            }
                qexec.close();
            }
       stit=data.listStatements();
        while(stit.hasNext()){
         Statement pr=stit.next();
        String str=pr.getPredicate().getLocalName().toString();

        String sparqlQueryString1 = "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> "
                    + "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
                    + "PREFIX owl: <http://www.w3.org/2002/07/owl#> "
                    + "PREFIX dbp: <http://dbpedia.org/ontology/> "                   
                    + "PREFIX xsd: <http://www.w3.org/2001/XMLSchema>"
                    + "PREFIX skos: <http://www.w3.org/2004/02/skos/core#> "
                    + "PREFIX : <http://yago-knowledge.org/resource/> "
                    + "CONSTRUCT {"
                    + "dbp:"+str+" <rdfs:range> ?o1.}"
                    + "where {"
                    +"dbp:"+ str+" rdfs:range ?o1. "
                    +"dbp:"+ str+" rdf:type owl:ObjectProperty"
                   
                    +"}";
            com.hp.hpl.jena.query.Query query = QueryFactory.create(sparqlQueryString1);
            QueryExecution qexec = QueryExecutionFactory.sparqlService("http://lod2.openlinksw.com/sparql", query);
            Model m=qexec.execConstruct();
            StmtIterator stmt=m.listStatements();
            while(stmt.hasNext()){
                Statement state=stmt.nextStatement();
                System.out.println("<"+state.getSubject()+"> "+state.getPredicate()+ " <"+state.getObject()+">.");
            }
                qexec.close();
            }
    }
}
