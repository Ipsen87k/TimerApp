import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App {
    public static void main(String[] args) throws Exception {
        new MyFrame();
        //new TimerExample(myFrame.getLabel());
    }
}
final class MyFrame extends JFrame{

    private JLabel label=new JLabel("test");
    private JButton starButton=new JButton("Start");
    private JButton stopButton=new JButton("Stop");
    private JButton pauseButton=new JButton("Puase");
    private IUserPlayable timIUserPlayable=new TimerExample(label);
    public JLabel getLabel() {
        return label;
    }
    public MyFrame() {
        super("test");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        InitAdd();
        EventSubscribe();
        setSize(300, 300);
        setVisible(true);
    }
    private void EventSubscribe(){
        starButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                timIUserPlayable.Start();
            }
        });
        stopButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                timIUserPlayable.Stop();
            }
        });
    }
    private void InitAdd(){
        label.setHorizontalAlignment(JLabel.CENTER);
        getContentPane().setLayout(new GridLayout(2, 1));
        getContentPane().add(label);
        var panel=new JPanel();
        panel.setLayout(new GridLayout(1, 3));
        panel.add(starButton);
        panel.add(stopButton);
        panel.add(pauseButton);
        getContentPane().add(panel);
    }
}

interface IStopable{
    void Stop();
    final String text="Stop";
}
interface IResetable{
    void Reset();
    final String text="Reset";
}

interface IStartable extends ActionListener{
    void Start();
    final String text="Start";
}
interface IUserPlayable extends IStartable,IStopable,IResetable{

}

class Tuple<T> {
    public T first;
    public T second;
    public T third;

    public Tuple(T first, T second, T third) {
        this.first = first;
        this.second = second;
        this.third=third;
    }
}

