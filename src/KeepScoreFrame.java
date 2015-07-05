import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;


public class KeepScoreFrame extends JFrame implements ActionListener, WindowListener {
	
	private JPanel ScoreScreen = new JPanel();
	
	private int numTeams = 0;
	
	private TeamPanel[] panels;
	
	private JTextField numberTeams;
	
	private JButton clickContinue;
	
	private JPanel askTeams;
	
	private JButton undoButton;
	
	private TeamPanel prevPanel;
	
	private String prevAction;
	
	
	private JPanel timerPanel;
	
	private JButton startTimer;
	
	private JLabel theTimer;
	
	private Timer bonusTimer;
	
	
	private int timerSecond = 0;
	
	
	public KeepScoreFrame(String title) {
		
		this.addWindowListener(this);
	
		askTeams = new JPanel();
		
		numberTeams = new JTextField(2);
		
		clickContinue = new JButton("Create Teams");
		clickContinue.addActionListener(this);
		
		askTeams.add(new JLabel("Enter the Number of Teams: "));
		askTeams.add(numberTeams);
		askTeams.add(clickContinue);

		
		 this.add(askTeams,BorderLayout.NORTH);

		
		
	}
	
	
	
	public void findInterrupt(TeamPanel team, String type) {
				
		for (int i=0;i < numTeams;i++) {
				
			if (panels[i] != team) {
						
					panels[i].addScore(type);
											
			}
				
		}
		
	}

	
	public void lastAction(TeamPanel team, String action) {
		
		prevPanel = team;
		
		prevAction = action;
		
	}
	
	
	public void undo(){
		
		if (prevAction == "Correct") {
			
			prevPanel.addScore("undo");
			
		} else if (prevAction == "Interrupt") {
			
			findInterrupt(prevPanel, "undo");
			
		} else if (prevAction == "Bonus") {
			
			prevPanel.subtractBonus();
			
		}
		
	}

	
	
	public void createTeamPanels() {
		
		numTeams = Integer.parseInt(numberTeams.getText());
	    
	    GridLayout AlltheTeams = new GridLayout(numTeams+2,1);


	    
	    GridLayout timerRow = new GridLayout(1,2);
	    timerPanel = new JPanel();
	    timerPanel.setLayout(timerRow);
	    
	    startTimer = new JButton("Start Bonus Timer");
	    startTimer.addActionListener(this);
	    
	    theTimer = new JLabel("Here is the timer");
	    theTimer.setOpaque(true);
	   
	    timerPanel.add(startTimer);
	    timerPanel.add(theTimer);
	    
		ScoreScreen.setLayout(AlltheTeams);
		
		
		panels = new TeamPanel[numTeams];
		    
	    for (int i=0;i < numTeams;i++) {
	    	
	    	panels[i] = new TeamPanel("Team " + (i+1), this);
	    	
	    	ScoreScreen.add(panels[i]);
	    	
	    }
	    
	    undoButton = new JButton("Undo Last Score Change");
	    ScoreScreen.add(undoButton);
	    undoButton.addActionListener(this);
	    

	    ScoreScreen.add(timerPanel);
	  
	    
	    clickContinue.setEnabled(false);
	    
	    this.add(ScoreScreen);
	   
	    this.remove(askTeams);

	    this.validate();
	    
	    this.pack();
		
	}
	

	@Override
	public void actionPerformed(ActionEvent event) {
		
		if (event.getSource() == clickContinue) {
			
			createTeamPanels();
			
		} else if (event.getSource() == undoButton) {
			
			undo();
			
		} else if (event.getSource() == startTimer) {
			
			timerSecond = 0;
			
			bonusTimer = new Timer(1000, this);
			
			theTimer.setBackground(new Color(0,255,0));
			
			theTimer.setText("Timer Started");
			
			bonusTimer.start();
			
		} else if (event.getSource() == bonusTimer) {
			
			timerSecond = timerSecond + 1;
			
			theTimer.setText(Integer.toString(timerSecond));
			
			if (timerSecond == 20) {
				
				bonusTimer.stop();
					
				getToolkit().beep();

				theTimer.setBackground(new Color(255,0,0));
				
			}
			
		}
		

	}



	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub	
    	setVisible (false);
    	dispose();
    	System.exit(0);
		
	}



	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
		
	

	
}
