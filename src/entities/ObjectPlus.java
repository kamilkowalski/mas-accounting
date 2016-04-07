package entities;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Hashtable;
import java.util.Vector;

public class ObjectPlus implements Serializable {
    private static Hashtable<Class, Vector<ObjectPlus>> classInstances = new Hashtable<>();

    public ObjectPlus() {
        Vector<ObjectPlus> classInstancesVector = null;
        Class klass = this.getClass();

        if(classInstances.containsKey(klass)) {
            classInstancesVector = classInstances.get(klass);
        } else {
            classInstancesVector = new Vector<>();
            classInstances.put(klass, classInstancesVector);
        }
        classInstancesVector.add(this);
    }

    public static void saveClassInstances(ObjectOutputStream stream) throws IOException {
        stream.writeObject(classInstances);
    }

    public static void readClassInstances(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        classInstances = (Hashtable<Class, Vector<ObjectPlus>>) stream.readObject();
    }

    public static Vector<ObjectPlus> getClassInstancesVector(Class klass) {
        return classInstances.get(klass);
    }

    public static void printClassInstances(Class klass) throws Exception {
        Vector<ObjectPlus> classInstancesVector = null;
        if(classInstances.containsKey(klass)) {
            classInstancesVector = classInstances.get(klass);
        } else {
            throw new Exception("Nieznana klasa: " + klass);
        }

        System.out.println("Ekstensja klasy " + klass + ":");

        for(ObjectPlus o : classInstancesVector) {
            System.out.println(o);
        }
    }
}
