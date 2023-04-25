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

    private JLabel label=new JLabel("00:00:00");
    private JButton starButton=new JButton("Start");
    private JButton stopButton=new JButton("Reset");
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
                timIUserPlayable.Reset();
            }
        });
    }
    private void InitAdd(){
        label.setFont(new Font("Arial", Font.PLAIN, 40));
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

interface IPauseable{
    void Pause();
}
interface IResetable{
    void Reset();
}

interface IStartable extends ActionListener{
    void Start();
}
interface IUserPlayable extends IStartable,IPauseable,IResetable{

}

class Tuple<T> {
    public T first;
    public T second;
    public T third;

    public Tuple<T> returnTuple(T first,T second,T third) {
        this.first = first;
        this.second = second;
        this.third=third;
        return this;
    }

}

