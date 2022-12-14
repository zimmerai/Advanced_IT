package Aufgabe_9;

public class WorkerThread extends Thread{
        private int idNum;
        private BigArray bigArray;

        private int endNum;


    public WorkerThread(int id, int endNum, BigArray bigArray){
        setIdNum(id);
        setendNum(endNum);
        setBigArray(bigArray);
    }

    @Override
    public void run() {
        double sum = 0;
        double start = System.nanoTime();
        double[] array = bigArray.getBigArray();
        for (int i = getIdNum(); i< this.getendNum(); i++) {
            sum += array[i];
        }
        double end = System.nanoTime();

        System.out.println("The sum is " + sum + " and it took " + (end-start) / 1000000 + " ms time" );
    }

    public int getIdNum() {
        return idNum;
    }

    public void setIdNum(int id) {
        this.idNum = id;
    }


    public BigArray getBigArray() {
        return bigArray;
    }

    public void setBigArray(BigArray bigArray) {
        this.bigArray = bigArray;
    }

    public int getendNum() {
        return endNum;
    }

    public void setendNum(int doubleIdNum) {
        this.endNum = doubleIdNum;
    }
}
