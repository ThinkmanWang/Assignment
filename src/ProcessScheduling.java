import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintStream;
import java.util.*;

class ProcessInfo {
    private int mPID;
    private int mPriority;
    private int mDuration;
    private int mAarrivalTime;
    private int mRunTime = 0;

    public ProcessInfo(int nID, int nPriority, int nDuration, int nArrivalTime) {
        this.mPID = nID;
        this.mPriority = nPriority;
        this.mDuration = nDuration;
        this.mAarrivalTime = nArrivalTime;
    }

    public String toString() {
        String szRet = String.format("Id = %d, priority = %d, duration = %d, arrival time = %d"
                , this.getmPID()
                , this.getmPriority()
                , this.getmDuration()
                , this.getmAarrivalTime());

        return szRet;
    }

    public String toStringFinish() {
        String szRet = String.format("Process id = %d\n\tPriority = %d\n\tArrival = %d\n\tDuration = %d\n"
                , this.getmPID()
                , this.getmPriority()
                , this.getmAarrivalTime()
                , this.getmDuration());

        return szRet;
    }

    public int getmRunTime() {
        return mRunTime;
    }

    public void setmRunTime(int mRunTime) {
        this.mRunTime = mRunTime;
    }

    public int getmPriority() {
        return mPriority;
    }

    public int getmPID() {
        return mPID;
    }

    public int getmAarrivalTime() {
        return mAarrivalTime;
    }

    public int getmDuration() {
        return mDuration;
    }

    public void setmPriority(int mPriority) {
        this.mPriority = mPriority;
    }

    public void setmPID(int mPID) {
        this.mPID = mPID;
    }

    public void setmAarrivalTime(int mAarrivalTime) {
        this.mAarrivalTime = mAarrivalTime;
    }

    public void setmDuration(int mDuration) {
        this.mDuration = mDuration;
    }
}

class ProcessScheduler {
    private ProcessInfo mCurProcess = null;
    private int mCurrentTime = 0;
    private boolean mAllFinished = false;
    public static final int MAX_WAIT_TIME = 30;
    public static int mTotalWaitTime = 0;
    public static int mTotalProcess = 0;
    private String mOutFile = null;
    private PrintStream mOutStream = null;

    private List<ProcessInfo> mProcessNotReady = new ArrayList<>();
    private PriorityQueue<ProcessInfo> mProcess = new PriorityQueue<>(new Comparator<ProcessInfo>() {
        @Override
        public int compare(ProcessInfo o1, ProcessInfo o2) {
            return o1.getmPriority() - o2.getmPriority();
        }
    });

    public void printAllProcess() {
        for (ProcessInfo item : mProcessNotReady) {
            System.out.println(item.toString());
            mOutStream.println(item.toString());
        }
    }

    public void printMaxWait() {
        System.out.printf("\nMaximum wait time = %d\n\n", ProcessScheduler.MAX_WAIT_TIME);
        mOutStream.printf("\nMaximum wait time = %d\n\n", ProcessScheduler.MAX_WAIT_TIME);
    }

    private void updateQueue() {
        //Get (don’t remove) a process p from D that has the earliest arrival time
        if (mProcessNotReady.isEmpty()) {
            return;
        }

        Iterator<ProcessInfo> iterator = mProcessNotReady.iterator();
        while(iterator.hasNext()) {
            ProcessInfo item = iterator.next();
            if (item.getmAarrivalTime() <= mCurrentTime) {
                iterator.remove();

                mProcess.add(item);
            }
        }

        if (mProcessNotReady.isEmpty()) {
            System.out.printf("D becomes empty at time %d\n", mCurrentTime);
            mOutStream.printf("D becomes empty at time %d\n", mCurrentTime);
        }
    }

    private void updatePriority() {
        System.out.println("Update priority: ");
        mOutStream.println("Update priority: ");

        for (ProcessInfo item : mProcess) {
            if (mCurProcess == item) {
                continue;
            }

            if (mCurrentTime - item.getmAarrivalTime() >= MAX_WAIT_TIME
                    && item.getmPriority() > 0) {
                System.out.printf("" +
                                "PID = %d, wait time = %d, current priority = %d \n" +
                                "PID = %d, new priority = %d\n"
                        , item.getmPID()
                        , mCurrentTime - item.getmAarrivalTime()
                        , item.getmPriority()
                        , item.getmPID()
                        , item.getmPriority() - 1);

                mOutStream.printf("" +
                                "PID = %d, wait time = %d, current priority = %d \n" +
                                "PID = %d, new priority = %d\n"
                        , item.getmPID()
                        , mCurrentTime - item.getmAarrivalTime()
                        , item.getmPriority()
                        , item.getmPID()
                        , item.getmPriority() - 1);

                item.setmPriority(item.getmPriority() - 1);
            }
        }

        mProcess = new PriorityQueue<>(mProcess); //resort
        System.out.println();
        mOutStream.println();
    }

    private void onFinish() {
        mOutStream.close();
    }

    public void init(String szInputFile, String szOutFile) {
        mOutFile = szOutFile;
        try {
            mOutStream = new PrintStream(mOutFile);
        } catch (Exception ex) {
        }

        FileReader fr = null;
        BufferedReader bf = null;

        try {
            fr = new FileReader(szInputFile);
            bf = new BufferedReader(fr);

            String str;
            while ((str = bf.readLine()) != null) {
                str = str.trim();
                String[] lstData = str.split(" ");

                mProcessNotReady.add(new ProcessInfo(
                        Integer.parseInt(lstData[0])
                        , Integer.parseInt(lstData[1])
                        , Integer.parseInt(lstData[2])
                        , Integer.parseInt(lstData[3])
                ));

                mTotalProcess += 1;
            }
        } catch (Exception ex) {

        } finally {
            try {
                bf.close();
                fr.close();
            } catch (Exception ex) {

            }

        }
    }

    private void createProcess() {
        if (mProcess.isEmpty()) {
            return;
        }

        mCurProcess = mProcess.remove();
        mTotalWaitTime += mCurrentTime - mCurProcess.getmAarrivalTime();
        System.out.printf("Process removed from queue is: id = %d, at time %d, wait time = %d Total wait time = %.1f\n"
                , mCurProcess.getmPID()
                , mCurrentTime
                , mCurrentTime - mCurProcess.getmAarrivalTime()
                , (float)mTotalWaitTime);
        mOutStream.printf("Process removed from queue is: id = %d, at time %d, wait time = %d Total wait time = %.1f\n"
                , mCurProcess.getmPID()
                , mCurrentTime
                , mCurrentTime - mCurProcess.getmAarrivalTime()
                , (float)mTotalWaitTime);
    }

    public void start() {

        while (false == mAllFinished) {
            try {

                if (mAllFinished) {
                    break;
                }

                //Get (don’t remove) a process p from D that has the earliest arrival time
                updateQueue();

                if (mCurProcess != null) {
                    mCurProcess.setmRunTime(mCurProcess.getmRunTime() + 1);

                    if (mCurProcess.getmRunTime() >= mCurProcess.getmDuration()) {
                        //process finished
                        System.out.println(mCurProcess.toStringFinish());
                        mOutStream.println(mCurProcess.toStringFinish());

                        System.out.printf("Process %d finished at time %d\n\n", mCurProcess.getmPID(), mCurrentTime);
                        mOutStream.printf("Process %d finished at time %d\n\n", mCurProcess.getmPID(), mCurrentTime);

                        mCurProcess = null;

                        updatePriority();
                        createProcess();

                    } else {
                        continue;
                    }

                } else {
                    if (mProcess.size() > 0) {
                        createProcess();
                    }
                }

                if (null == mCurProcess && mProcess.size() <= 0 && mProcessNotReady.size() <= 0) {
                    mAllFinished = true;
                }
            } catch (Exception ex) {
                int a = 0;
            } finally {
                try {
                    Thread.sleep(1000);
                    mCurrentTime += 1;
                } catch (Exception ex) {
                }
            }
        }

        /*
        Total wait time = 391.0 Average wait time = 39.1
         */
        System.out.printf("\nTotal wait time = %.1f \nAverage wait time = %.1f\n", (float)mTotalWaitTime, ((float)mTotalWaitTime/(float)mTotalProcess));
        mOutStream.printf("\nTotal wait time = %.1f \nAverage wait time = %.1f\n", (float)mTotalWaitTime, ((float)mTotalWaitTime/(float)mTotalProcess));

        this.onFinish();
    }
}

public class ProcessScheduling {
    public static void main(String[] args) {
        ProcessScheduler processScheduler = new ProcessScheduler();
        processScheduler.init("/Users/wangxiaofeng/Github-Thinkman/Assignment/src/process_scheduling_input.txt"
                , "/Users/wangxiaofeng/Github-Thinkman/Assignment/src/process_scheduling_output.txt");
        processScheduler.printAllProcess();
        processScheduler.printMaxWait();

        processScheduler.start();
    }
}
