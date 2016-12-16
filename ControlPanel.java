package diamond_square;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

public class ControlPanel extends JPanel implements ActionListener{
	
	JLabel pLabel = new JLabel("pVal     ");
	JTextArea pVal = new JTextArea("0.1      ");
	
	JLabel wLabel = new JLabel("wait (ms)");
	JTextArea wVal = new JTextArea("500     ");
	
	
	JRadioButton s1 = new JRadioButton("Effect 1:");
	JRadioButton s2 = new JRadioButton("Effect 2:");
	JRadioButton s3 = new JRadioButton("Effect 3:");
	
	public void init(){
		//this.add(pLabel);
	//	this.add(pVal);
	//	this.add(wLabel);
	//	this.add(wVal);
		this.add(s1);
		this.add(s2);
		this.add(s3);
		s1.setSelected(true);
		s1.addActionListener(this);
		s2.addActionListener(this);
		s3.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		s1.setSelected(false);
		s2.setSelected(false);
		s3.setSelected(false);
		JRadioButton source = (JRadioButton)e.getSource();
		source.setSelected(true);
		// TODO Auto-generated method stub
		
	}
	
}
