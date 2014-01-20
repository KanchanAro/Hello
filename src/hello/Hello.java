/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hello;

/**
 *
 * @author Kanchan
 */
public class Hello {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int x=4;
        System.out.println("Hello");
        System.out.println("Netbeans is working fine");
        Hi h=new Hi();
        h.show();
        Hi hi=new Namaste();
        hi.show();
        
    }
}
 class Hi {
//    int x;
//    int y;
//    float z;
//     Hi(){
// }
// 
//   Hi(int x){   
//       System.out.println(x);
//}
//   Hi(int x,float z){
//    System.out.println(x+ " "+z);
//   }
   
   void show(){
    System.out.println("Hii");
   }
   }
 
class Namaste extends Hi{
    void show(){
        System.out.println("Namaste");
    }
} 