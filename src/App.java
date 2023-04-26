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
    }
}
final class MyFrame extends JFrame{

    private JLabel label=new JLabel(TimerDatas.FORMAT);
    private JButton starButton=new JButton("Start");
    private JButton resetButton=new JButton("Reset");
    private JButton pauseButton=new JButton("Puase");
    private IUserPlayable timIUserPlayable=new TimerCount(label);
    public JLabel getLabel() {
        return label;
    }
    public MyFrame() {
        super("TimerApp");
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
        pauseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                timIUserPlayable.Pause();
            }
        });
        resetButton.addActionListener(new ActionListener() {
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
        panel.add(resetButton);
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

interface IStartable{
    void Start();
}
interface IUserPlayable extends IStartable,IPauseable,IResetable{

}



