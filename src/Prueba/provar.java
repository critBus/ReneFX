/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Prueba;

//import Utiles.ClasesUtiles.BasesDeDatos.BDConexionController;
import Utiles.MetodosUtiles.Archivo;
import Utiles.MetodosUtiles.MetodosUtiles;
import java.util.Scanner;
import Utiles.MetodosUtiles.Operaciones;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rene
 */
public class provar {

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("hola");
//        try {
//            //        Scanner s = new Scanner(System.in);
////        s.next();
////        Object o=MetodosUtiles.<String>getClassDeE();
////        System.out.println("o="+o);
////        if(o!=null){
////            System.out.println(o.getClass().getSimpleName());
////        }
//
//// provar.<String>aa();
////        sacarTabla(Archivo.leerTXT("F:\\_Cosas\\ISCA\\estadistica\\Tablas\\Normal.txt"));
////System.out.println(Operaciones.getZnormal(0.05, 0, 0));
//
//BDConexionController bd=BDConexionController.getPOSTGRESConexion("postgres", "rene", "Prueba2", "5432");
//bd.executeINSERT("usuario", new String[]{"nombre","edad"}, new Object[]{"dos",11});
////bd.executeINSERT("usuario", new String[]{"edad"}, new Object[]{"10"});
////ejecucion=INSERT INTO  usuario ( id ,nombre , edad ) VALUES ( 1 , "uno" , "10" )
////bd.execute("INSERT INTO  usuario ( id ,nombre , edad ) VALUES ( 1 , 'uno' , '10' ) ");
//        } //gil peres
//        catch (ClassNotFoundException ex) {
//            Logger.getLogger(provar.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (Exception ex) {
//            Logger.getLogger(provar.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
   
    
   public static void sacarTabla(String A[]){
       System.out.println("A.length="+A.length);
  final String r="double tablaNormal[][]={";
       System.out.println(r);
       for (int i = 0; i < A.length; i++) {
           final int ii=i;
           System.out.print("{");
           MetodosUtiles.recorrerTokenizer(A[i], (v,j)->{
               //System.out.print((j!=0?",":"")+" "+(ii>0&&j>0?"0":"")+v.replace(",", "."));
               System.out.print((j!=0?",":"")+" "+v);
              // r.concat((j!=0?",":"")+MetodosUtiles.dou(v));
           });
           System.out.print("}"+(i!=A.length-1?",\n":"\n};"));
          // r.concat("}\n"+(i!=A.length-1?",{":"};"));
       }
       //System.out.println(r);
   }
}
