package Aufgabe_9;

public class BigArray {
    private int arrayLength;
    private double[] bigArray;

    public BigArray(int arrayLength){
        setArrayLength(arrayLength);
        double[] arr = new double [arrayLength];

        for (int i=0; i<arrayLength; i++) {
            arr[i] = Math.random() * 1000;
        }

        setBigArray(arr);
    }

    public void getSum(){
        double sum = 0;
        double start = System.nanoTime();
        double[] array = this.getBigArray();
        for (int i = 0; i<this.getArrayLength(); i++) {
            sum += array[i];
        }
        double end = System.nanoTime();

        System.out.println("The sum is " + sum + " and it took " + (end-start) / 1000000 + " ms time" );
    }

    public int getArrayLength() {
        return arrayLength;
    }

    public void setArrayLength(int arrayLength) {
        this.arrayLength = arrayLength;
    }

    public double[] getBigArray() {
        return bigArray;
    }

    public void setBigArray(double[] bigArray) {
        this.bigArray = bigArray;
    }
}