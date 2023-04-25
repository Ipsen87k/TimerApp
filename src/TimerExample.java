
import java.awt.event.ActionEvent;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;

class TimerExample implements IUserPlayable{
    private  Timer timer;
    private MyTimerTask timerTask;
    private JLabel label;

    public TimerExample(JLabel jLabel) {
        this.label=jLabel;
    }


    
    @Override
    public void Start() {
        timer = new Timer();      
        timerTask=new MyTimerTask(label);
        timerTask.setStartTime(System.currentTimeMillis());
        timer.scheduleAtFixedRate(timerTask,0,1000) ;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }


    @Override
    public void Pause() {

    }


    @Override
    public void Reset() {
        label.setText("00:00:00");
        timerTask.cancel();
        timer.cancel();
    }
}

class MyTimerTask extends TimerTask {
    private  long startTime;
    private JLabel label;
    private Tuple<Integer> tuple=new Tuple<Integer>();
    public MyTimerTask(JLabel label){
        this.label=label;
    }
    private Tuple<Integer> Calc(long diff){
        var seconds = (int) (diff / TimerDatas.ONE_SECOND) % TimerDatas.ONE_HOUR_TO_MINUTES;
        var minutes = (int) ((diff / (TimerDatas.ONE_SECOND * TimerDatas.ONE_HOUR_TO_MINUTES)) % TimerDatas.ONE_HOUR_TO_MINUTES);
        var hours = (int) ((diff / (TimerDatas.ONE_SECOND * TimerDatas.ONE_HOUR_TO_MINUTES * TimerDatas.ONE_HOUR_TO_MINUTES)) % TimerDatas.ONE_DAY_TO_HOURS);
        return tuple.returnTuple(hours,minutes,seconds);
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