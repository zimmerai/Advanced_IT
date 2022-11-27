public class WorkerThreads extends Thread{
    private BigArray bigArray;

    private int threadCount;

    public WorkerThreads(int threadCount, BigArray bigArray){
        int id = bigArray.getArrayLength() / threadCount;
        for (int i = 0; i<threadCount; i++) {
            WorkerThread currentThread = new WorkerThread(id*i, id*i+id, bigArray);
            currentThread.start();

            //With this join the individual time is significantly smaller
            try {
                currentThread.join();
            } catch (Exception e) {

            }

        }
        setBigArray(bigArray);
    }


    public BigArray getBigArray() {
        return bigArray;
    }

    public void setBigArray(BigArray bigArray) {
        this.bigArray = bigArray;
    }

    public int getThreadCount() {
        return this.threadCount;
    }

    public void setNumber(int number) {
        this.threadCount = number;
    }

}
