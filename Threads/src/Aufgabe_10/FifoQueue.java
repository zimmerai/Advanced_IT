package Aufgabe_10;
import java.util.*;
import java.util.concurrent.Semaphore;

public class FifoQueue {
    private Element currentElement;

    private Element firstElement;

    public Semaphore putSemaphore;

    public Semaphore getSemaphore;

    public FifoQueue(Element firstElement){
        putSemaphore = new Semaphore(1, true);
        getSemaphore = new Semaphore(1, true);
        setCurrentElement(firstElement);
        setFirstElement(firstElement);
    }

    public void put(String s) {
//        try {
//            putSemaphore.acquire();
//        } catch (Exception e) {e.printStackTrace();}
        Element e = new Element(s);
        if (getFirstElement() == null) {
            setCurrentElement(e);
            setFirstElement(e);
        } else {
            getCurrentElement().setNextElement(e);
            setCurrentElement(e);
        }
//        try {
//            putSemaphore.release();
//        } catch (Exception e2) {e2.printStackTrace();}
    }

    public String get(){
//        try {
//            getSemaphore.acquire();
//        } catch (Exception e) {e.printStackTrace();}
        if (getFirstElement() == null) {
            System.out.println("Queue ist leer");
            return null;
        }
        String value = getFirstElement().getValue();
        setFirstElement(getFirstElement().getNextElement());
//        try {
//            getSemaphore.release();
//        } catch (Exception e2) {e2.printStackTrace();}
          return value;
    }

    private class Element{
         private String value;
         private Element nextElement;
        private Element(String value){
            setValue(value);
            setNextElement(null);
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public Element getNextElement() {
            return nextElement;
        }

        public void setNextElement(Element nextElement) {
            this.nextElement = nextElement;
        }
    }

    public Element getFirstElement() {
        return firstElement;
    }

    public void setFirstElement(Element firstElement) {
        this.firstElement = firstElement;
    }

    public Element getCurrentElement() {
        return currentElement;
    }

    public void setCurrentElement(Element currentElement) {
        this.currentElement = currentElement;
    }
}
