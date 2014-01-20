/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hello;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Kanchan
 */
public class Merge {
    public static void main(String args[]) throws IOException {
        Model m=ModelFactory.createDefaultModel();
        m.read("C:\\Users\\Kanchan\\Desktop\\Abhay_KumarFF.ttl");
        
        StmtIterator i=m.listStatements();
        while(i.hasNext()){
            Statement s=i.next();
            String str=s.getSubject().getLocalName().toString();
            String cmd = "FINDSTR " + "\"<"+str+">\"" + " C:\\Users\\Kanchan\\Downloads\\yagoDBpediaInstances.ttl\\yagoDBpediaInstances.ttl yagoDBpediaClasses.ttl\\yagoDBpediaClasses.ttl";
                //System.out.println(cmd);
                Process proc = Runtime.getRuntime().exec(cmd);
                BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
                BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
                String s1 = null;
                while ((s1 = stdInput.readLine()) != null) {
                    System.out.println(s1);

                }
                while ((s1 = stdError.readLine()) != null) {
                      }
                if(s.getObject().isResource()){
                String str2=s.getObject().asResource().getLocalName().toString();
            String cmd2 = "FINDSTR " + "\"<"+str2+">\"" + " C:\\Users\\Kanchan\\Downloads\\yagoDBpediaInstances.ttl\\yagoDBpediaInstances.ttl ";
                //System.out.println(cmd);
                Process proc2 = Runtime.getRuntime().exec(cmd2);
                BufferedReader stdInput2 = new BufferedReader(new InputStreamReader(proc2.getInputStream()));
                BufferedReader stdError2 = new BufferedReader(new InputStreamReader(proc2.getErrorStream()));
                String s2 = null;
                while ((s2 = stdInput2.readLine()) != null) {
                    System.out.println(s2);

                }
                while ((s2 = stdError2.readLine()) != null) {
                      }
                }
                String str3=s.getPredicate().getLocalName().toString();
            String cmd3 = "FINDSTR " + "\"<"+str3+">\"" + " C:\\Users\\Kanchan\\Downloads\\yd_properties.ttl";
                //System.out.println(cmd);
                Process proc3 = Runtime.getRuntime().exec(cmd3);
                BufferedReader stdInput3 = new BufferedReader(new InputStreamReader(proc3.getInputStream()));
                BufferedReader stdError3 = new BufferedReader(new InputStreamReader(proc3.getErrorStream()));
                String s3 = null;
                while ((s3 = stdInput3.readLine()) != null) {
                    System.out.println(s3);

                }
                while ((s3 = stdError3.readLine()) != null) {
                      }
        
        }
        
    }
    
}
