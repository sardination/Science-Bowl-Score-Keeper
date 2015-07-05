/*Correct responses to toss-up questions are worth 4 points each. 
 If a student buzzes in on a toss-up question before the moderator 
 has completely read the question (i.e., interrupts the moderator) 
 and responds incorrectly, 4 points are awarded to the opposing team, 
 and the question is re-read in its entirety so that the opposing team 
 has an opportunity to buzz in. A correct response on a bonus question 
 earns 10 points, making the total possible score on a single question 
 18 points (4 for a correct answer, 4 for an incorrect interruption by 
 the other team, and 10 for the bonus), and a perfect score 450 points.
  Against an opponent who never buzzes in (often the better 
  approximation), the maximum (perfect) score is 350 points. 
  Any score above 200 is exceptional, and any score above 300 
  is extremely rare.*/



public class ScoreCalculator {
	
	private int score = 0;

	
	
	public ScoreCalculator() {

		
	}
	
	
	public void right(String type) {
		
		if (type == "normal") {
			
			score = score + 4;			
			
		} else if (type == "undo") {
			
			score = score - 4;
			
		}
		

		
	}

	
	public void bonus(String type) {
		
		if (type == "normal") {

			score = score + 10;
			
		} else if (type == "undo") {
			
			score = score - 10;
			
		}
		

		
	}
	
	public int getScore() {
		
		return score;
		
	}

}
