
import java.util.TimerTask;

import javax.swing.JLabel;

class MyTimerTask extends TimerTask {
    private  long startTime;
    private long diff;
    private long resumeTime=0;
    private JLabel label;
    private Tuple<Integer> tuple=new Tuple<Integer>();
    
    public MyTimerTask(JLabel label){
        this.label=label;
    }
    public  MyTimerTask(JLabel lebel,long resumeTime){
        this.label=lebel;
        this.resumeTime=resumeTime;
    }
    @Override
    public void run() {
        long now = System.currentTimeMillis();
        diff = now - startTime+resumeTime;
        Tuple<Integer> item=Calc(diff);
        label.setText(String.format("%02d:%02d:%02d", item.first, item.second, item.third));
    }
    public long getStartTime() {
        return startTime;
    }
    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getDiff(){
        return diff;
    }
    private Tuple<Integer> Calc(long diff){
        var seconds = (int) (diff / TimerDatas.ONE_SECOND) % TimerDatas.ONE_HOUR_TO_MINUTES;
        var minutes = (int) ((diff / (TimerDatas.ONE_SECOND * TimerDatas.ONE_HOUR_TO_MINUTES)) % TimerDatas.ONE_HOUR_TO_MINUTES);
        var hours = (int) ((diff / (TimerDatas.ONE_SECOND * TimerDatas.ONE_HOUR_TO_MINUTES * TimerDatas.ONE_HOUR_TO_MINUTES)) % TimerDatas.ONE_DAY_TO_HOURS);
        return tuple.returnTuple(hours,minutes,seconds);
    }
}
