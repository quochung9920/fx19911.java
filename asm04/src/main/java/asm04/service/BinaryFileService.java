package asm04.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class BinaryFileService {

    /** 
     * Read file and return list of objects
     * @param fileName
     * @return
     */
    public static <T> List<T> readFile(String fileName) {
        List<T> objects = new ArrayList<>();
        try(ObjectInputStream file = new ObjectInputStream(new BufferedInputStream(new FileInputStream(fileName)))){
            boolean oef = false;
            while(!oef) {
                try {
                    T object = (T) file.readObject();
                    objects.add(object);
                } catch (EOFException e) {
                    oef = true;
                }
            }
        } catch (EOFException e) {
           return new ArrayList<>();
        } catch (IOException io) {
            System.out.println("IO Exception " + io.getMessage());
        } catch (ClassNotFoundException cnf) {
            System.out.println("Class not found Exception " + cnf.getMessage());
        }

        return objects;
    }

    /** 
     * Write list of objects to file
     * @param fileName
     * @param objects
     */
    public static <T> void writeFile(String fileName, List<T> objects) {
        try(ObjectOutputStream file = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fileName)))){

            for(T object : objects) {
                file.writeObject(object);
            }
        } catch (IOException io) {

            System.out.println("IO Exception " + io.getMessage());
        }
    }
}
