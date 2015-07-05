import javax.swing.*;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;




public class TeamPanel extends JPanel implements ActionListener {

	
	private JLabel whatteam;

	private JLabel points;
	
	private JButton correct;
	private JButton interrupt;
	private JButton bonus;

	
	private ScoreCalculator teamcalc;
	
	private KeepScoreFrame parent;
	
	

	
	public TeamPanel(String teamname, KeepScoreFrame keepScoreFrame) {

		parent = keepScoreFrame;
		
		whatteam = new JLabel(teamname);
		
		points = new JLabel("Score: 0");
		
		points.setForeground(new Color(0,90,0));
		
		correct = new JButton("Correct");
		correct.addActionListener(this);
		
		interrupt = new JButton("Interruption/Blurt");
		interrupt.addActionListener(this);
		
		bonus = new JButton("Bonus");
		bonus.addActionListener(this);		

		teamcalc = new ScoreCalculator();
		
		GridLayout fgthis = new GridLayout(1,5);
		
		this.setLayout(fgthis);
		
		this.add(whatteam);
		this.add(correct);
		this.add(interrupt);
		this.add(bonus);
		this.add(points);

	}
	
	public void setScore() {
		
		points.setText("Score:" + " " + Integer.toString(teamcalc.getScore()));
		
	}

	
	public void addScore(String type) {

		teamcalc.right(type);
		
		setScore();
		
	}
	
	 public void subtractBonus() {
		 
		 teamcalc.bonus("undo");
		 
		 setScore();
		 
	 }




	@Override
	public void actionPerformed(ActionEvent event) {
		
		if (event.getSource() == correct) {
			
			teamcalc.right("normal");
			
			parent.lastAction(this, "Correct");
			
			
		} else if (event.getSource() == interrupt) {
			
			
	       parent.findInterrupt(this, "normal");

	       parent.lastAction(this, "Interrupt");
	       
			
		} else if (event.getSource() == bonus) {
			
			
			teamcalc.bonus("normal");
			
			parent.lastAction(this, "Bonus");

		}
		
		setScore();
		
	}

}
