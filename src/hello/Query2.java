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

/**
 *
 * @author Kanchan
 */
public class Query2 {

    public static void main(String args[]) throws FileNotFoundException, IOException {
        Model data = ModelFactory.createDefaultModel();
        data.read("C:\\Users\\Kanchan\\Desktop\\Abhay_Kumar0Y.ttl");
        File file = new File("C:\\Users\\Kanchan\\Desktop\\Abhay_KumarFY.ttl");
        PrintStream p = new PrintStream(file);
         System.setOut(p);
       
        StmtIterator sta=data.listStatements();
        while(sta.hasNext()){
            Statement stat=sta.nextStatement();
            if(!stat.getObject().isLiteral())
           System.out.println("<"+stat.getSubject()+">"+" <"+stat.getPredicate()+"> <"+stat.getObject()+">.");
           else
           System.out.println("<"+stat.getSubject()+">"+" <"+stat.getPredicate()+"> " +"\""+stat.getObject().asLiteral().getString()+"\""+".");
        
        }
//       
        ResIterator i = data.listSubjects();
         
        while (i.hasNext()) {
            String s = i.next().getLocalName().toString();
            //System.out.println(s);
            String sparqlQueryString1 = "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> "
                    + "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
                    + "PREFIX owl: <http://www.w3.org/2002/07/owl#> "
                    + "PREFIX dbp: <http://dbpedia.org/ontology/> "                   
                    + "PREFIX xsd: <http://www.w3.org/2001/XMLSchema>"
                    + "PREFIX skos: <http://www.w3.org/2004/02/skos/core#> "
                    + "PREFIX : <http://yago-knowledge.org/resource/> "
                    + "CONSTRUCT {"
                    + ":"+s+" <rdf:type> ?o.}"
                    + "where {"
                    +":"+ s+" rdf:type ?o ."
                    +"}";
            com.hp.hpl.jena.query.Query query = QueryFactory.create(sparqlQueryString1);
            QueryExecution qexec = QueryExecutionFactory.sparqlService("http://lod2.openlinksw.com/sparql", query);
            Model m=qexec.execConstruct();
            StmtIterator stmt=m.listStatements();
            while(stmt.hasNext()){
                Statement state=stmt.nextStatement();
                System.out.println("<"+state.getSubject()+"> "+state.getPredicate()+" <"+state.getObject()+">.");
            }
                qexec.close();
            }
        ResIterator is = data.listSubjects();
      //  System.setOut(p);
        while (is.hasNext()) {
            String s = is.next().getLocalName().toString();
            //System.out.println(s);
            String sparqlQueryString1 = "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> "
                    + "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
                    + "PREFIX owl: <http://www.w3.org/2002/07/owl#> "
                    + "PREFIX dbp: <http://dbpedia.org/ontology/> "                   
                    + "PREFIX xsd: <http://www.w3.org/2001/XMLSchema>"
                    + "PREFIX skos: <http://www.w3.org/2004/02/skos/core#> "
                    + "PREFIX : <http://yago-knowledge.org/resource/> "
                    + "CONSTRUCT {"
                    + "?c1 rdfs:subClassOf ?c2.}"
                    + "where {"
                    +":"+ s+" rdf:type ?c1 ."
                      + "?c1 rdfs:subClassOf ?c2 ."
                  
                    +"}";
            com.hp.hpl.jena.query.Query query = QueryFactory.create(sparqlQueryString1);
            QueryExecution qexec = QueryExecutionFactory.sparqlService("http://lod2.openlinksw.com/sparql", query);
            Model m=qexec.execConstruct();
            StmtIterator stmt=m.listStatements();
            while(stmt.hasNext()){
                Statement state=stmt.nextStatement();
                 if(!state.getSubject().getLocalName().contentEquals("Thing"))
                System.out.println("<"+state.getSubject()+"> <"+state.getPredicate()+"> <"+state.getObject()+">.");
            }
                qexec.close();
            }
        NodeIterator o = data.listObjects();
      //  System.setOut(p);
        while (o.hasNext()) {
            RDFNode n=o.next();
            if(n.isResource()){
            String s = n.asResource().getLocalName().toString();
           // System.out.println(s);
            String sparqlQueryString1 = "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> "
                    + "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
                    + "PREFIX owl: <http://www.w3.org/2002/07/owl#> "
                    + "PREFIX dbp: <http://dbpedia.org/ontology/> "                   
                    + "PREFIX xsd: <http://www.w3.org/2001/XMLSchema>"
                    + "PREFIX skos: <http://www.w3.org/2004/02/skos/core#> "
                    + "PREFIX : <http://yago-knowledge.org/resource/> "
                    + "CONSTRUCT {"
                    + ":"+s+" <rdf:type> ?o.}"
                    + "where {"
                    +":"+ s+" rdf:type ?o ."
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
            NodeIterator oo = data.listObjects();
      //  System.setOut(p);
        while (oo.hasNext()) {
            RDFNode n=oo.next();
            if(n.isResource()){
            String s = n.asResource().getLocalName().toString();
           // System.out.println(s);
            String sparqlQueryString1 = "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> "
                    + "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
                    + "PREFIX owl: <http://www.w3.org/2002/07/owl#> "
                    + "PREFIX dbp: <http://dbpedia.org/ontology/> "                   
                    + "PREFIX xsd: <http://www.w3.org/2001/XMLSchema>"
                    + "PREFIX skos: <http://www.w3.org/2004/02/skos/core#> "
                    + "PREFIX : <http://yago-knowledge.org/resource/> "
                    + "CONSTRUCT {"
                    + "?c1 rdfs:subClassOf ?c1.}"
                    + "where {"
                    +":"+ s+" rdf:type ?c1 ."
                    +"?c1 rdfs:subClassOf ?c2."
                    +"}";
            com.hp.hpl.jena.query.Query query = QueryFactory.create(sparqlQueryString1);
            QueryExecution qexec = QueryExecutionFactory.sparqlService("http://lod2.openlinksw.com/sparql", query);
            Model m=qexec.execConstruct();
            StmtIterator stmt=m.listStatements();
            while(stmt.hasNext()){
                Statement state=stmt.nextStatement();
                 if(!state.getSubject().getLocalName().contentEquals("Thing"))
                System.out.println("<"+state.getSubject()+"> <"+state.getPredicate()+">"+" <"+state.getObject()+">.");
            }
                qexec.close();
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
                    + ":"+str+" <rdfs:domain> ?o1.}"
                    + "where {"
                    +":"+ str+" rdfs:domain ?o1 "
                   
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
                    + ":"+str+" <rdfs:range> ?o1.}"
                    + "where {"
                    +":"+ str+" rdfs:range ?o1 "
                   
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
