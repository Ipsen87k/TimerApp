
import java.util.Timer;

import javax.swing.JLabel;

class TimerCount implements IUserPlayable {
    private Timer timer;
    private MyTimerTask timerTask;
    private JLabel label;
    private TimerState state = TimerState.first;
    private long diff = 0;

    public TimerCount(JLabel jLabel) {
        this.label = jLabel;
    }

    @Override
    public void Start() {
        if (state == TimerState.first) {
            Init(label);
        } else if (state == TimerState.pause) {
            Init(label);
        }
    }

    @Override
    public void Pause() {
        diff = timerTask.getDiff();
        Cancel();
        state = TimerState.pause;
    }

    @Override
    public void Reset() {
        label.setText(TimerDatas.FORMAT);
        Cancel();
        state = TimerState.first;
    }

    private void Cancel() {
        if (state == TimerState.start) {
            timerTask.cancel();
            timer.cancel();
        }
    }

    private void Init(JLabel label) {
        state = TimerState.start;
        timer = new Timer();
        timerTask = new MyTimerTask(label, diff);
        timerTask.setStartTime(System.currentTimeMillis());
        timer.scheduleAtFixedRate(timerTask, 0, 1000);
    }
}

class TimerDatas {
    public static final String FORMAT = "00:00:00";
    public static final int ONE_SECOND = 1000;
    public static final int ONE_HOUR_TO_MINUTES = 60;
    public static final int ONE_DAY_TO_HOURS = 24;
}

enum TimerState {
    first, start, pause,
}