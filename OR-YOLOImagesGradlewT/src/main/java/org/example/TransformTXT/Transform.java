package org.example.TransformTXT;

import java.io.*;
import java.util.*;
public class Transform{
    public ArrayList<String> getTheVectorClass(){
        File archivo = null;
        FileReader fr = null;
        ArrayList<String> vector = new ArrayList<String>();
        int c=80;
        try {
            archivo = new File ("src\\main\\java\\yolov3.txt");
            String m;
            fr = new FileReader (archivo);
            BufferedReader br = new BufferedReader(fr);
            for (int i = 0; i < c; i++) {
                m = br.readLine();
                vector.add(m);
            }
        }
        catch(IOException e){
            System.out.println(e);
        }
        return vector;
    }
    }