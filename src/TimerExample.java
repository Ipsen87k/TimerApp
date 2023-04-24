
import java.awt.event.ActionEvent;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;

class TimerExample implements IUserPlayable{
    private  Timer timer;
    private MyTimerTask timerTask;

    public TimerExample(JLabel jLabel) {
        timer = new Timer();      
        timerTask=new MyTimerTask(jLabel);
    }


    
    @Override
    public void Start() {
        timerTask.setStartTime(System.currentTimeMillis());
        timer.scheduleAtFixedRate(timerTask,0,1000) ;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }


    @Override
    public void Stop() {
        //timerTask.cancel();
        timer.cancel();
    }


    @Override
    public void Reset() {
        
        throw new UnsupportedOperationException("Unimplemented method 'Reset'");
    }
}

class MyTimerTask extends TimerTask {
    private  long startTime;
    private JLabel label;

    public MyTimerTask(JLabel label){
        this.label=label;
    }
    private Tuple<Integer> Calc(long diff){
        var seconds = (int) (diff / TimerDatas.ONE_SECOND) % TimerDatas.ONE_HOUR_TO_MINUTES;
        var minutes = (int) ((diff / (TimerDatas.ONE_SECOND * TimerDatas.ONE_HOUR_TO_MINUTES)) % TimerDatas.ONE_HOUR_TO_MINUTES);
        var hours = (int) ((diff / (TimerDatas.ONE_SECOND * TimerDatas.ONE_HOUR_TO_MINUTES * TimerDatas.ONE_HOUR_TO_MINUTES)) % TimerDatas.ONE_DAY_TO_HOURS);
        return new Tuple<Integer>(hours, minutes, seconds);
    }
    @Override
    public void run() {
        long now = System.currentTimeMillis();
        var diff = now - startTime;
        Tuple<Integer> item=Calc(diff);
        label.setText(String.format("%02d:%02d:%02d", item.first, item.second, item.third));
    }
    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }
}

class TimerDatas{
    public static final int ONE_SECOND=1000;
    public static final int ONE_HOUR_TO_MINUTES=60;
    public static final int ONE_DAY_TO_HOURS=24;
}