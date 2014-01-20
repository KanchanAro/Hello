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
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author Kanchan
 */
public class Test3 {

    public static void main(String args[]) throws IOException {

        int n = 1;
        boolean t = true;
        Entiites e = new Entiites();
        Set set = e.entity();
        Jaccard_Cofficient jc = new Jaccard_Cofficient();
        File fn = new File("C:\\Users\\Kanchan\\Desktop\\Abhay_Kumar0.ttl");
        while (t) {
            File f2 = new File("C:\\Users\\Kanchan\\Desktop\\Abhay_Kumar" + n + ".ttl");
            PrintStream ps = new PrintStream(f2);
            File f1 = new File("C:\\Users\\Kanchan\\Desktop\\Abhay_Kumar" + (n - 1) + ".ttl");
            Model data = ModelFactory.createDefaultModel();
            data.read("C:\\Users\\Kanchan\\Desktop\\Abhay_Kumar" + (n - 1) + ".ttl");
            StmtIterator st = data.listStatements();
            while (st.hasNext()) {
                Statement state = st.nextStatement();
                String s1 = null;
                if (state.getPredicate().asResource().getLocalName().toString().matches("hasGender")) {
                    continue;
                }
                if (state.getObject().isResource()) {
                    s1 = "<" + state.getObject().asResource().getLocalName().toString() + ">";
                }
                if (state.getObject().isLiteral()) {
                    s1 = "<" + state.getObject().asLiteral().getString() + ">";
                }
                System.setOut(ps);
                String cmd = "FINDSTR " + "\""+s1+"\"" + " C:\\Users\\Kanchan\\Desktop\\yagoFacts.ttl";
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
            st = data.listStatements();
            while (st.hasNext()) {
                Statement state=st.nextStatement();
                String s2 = "<"+state.getSubject().getLocalName().toString()+">";
                String cmd = "FINDSTR " + "\""+s2+"\"" + " C:\\Users\\Kanchan\\Desktop\\yagoFacts.ttl";
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
                                System.out.println("<" + s.getSubject().getLocalName() + ">" + " <" + s.getPredicate().getLocalName() + ">" + " <" + s.getObject().asResource().getLocalName() + ">" + ".");
                            } else {
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

        File f = new File("C:\\Users\\Kanchan\\Desktop\\Abhay_Kumar" + (n - 1) + ".ttl");
        //  File fn=new File("C:\\Users\\Kanchan\\Desktop\\A._K._Ramanujan0.ttl");
        BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\Kanchan\\Desktop\\Abhay_Kumar0.ttl", true));

        try {

            FileInputStream fstream = new FileInputStream(f);
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            String strLine;
            while ((strLine = br.readLine()) != null) {

                writer.append(strLine);
                writer.newLine();

            }
            in.close();
        } catch (Exception ee) {//Catch exception if any
            System.err.println("Error: " + ee.getMessage());
        }
        writer.write("\n");
        writer.close();
   }
}
