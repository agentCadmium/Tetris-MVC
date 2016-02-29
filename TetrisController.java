import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * MVC - CONTROLLER
 * @author Saverchenko
 *
 */

public class TetrisController implements KeyListener  { // Use KeyListener
														
	 private TetrisModel tetrisModel= new TetrisModel(); // create a game board
	 private TetrisView tetrisView;		// create a View Model of our board


	/**
	 * Constructor
	 */
	public TetrisController() {
		tetrisView=new TetrisView();
		tetrisView.addKeyListener(this); // add KeyListener in the JFrame of the ViewModel
		
		// Call View Model every 0.2 sec. to update the screen
		new Thread() {
			@Override public void run() {
				while (true) {
					try {
						if (tetrisModel.getIsFinished()==true){
							restartLevelDialog();
						}
						Thread.sleep(200); // repeat very 0.2 sec.						
						updateView(); // call Model to update information on the screen 
					} catch ( InterruptedException e ) {}
					}
				}
			}.start();	
	}
	

	@Override
	public void keyPressed(KeyEvent e) { 	

  		switch (e.getKeyCode()) {
			case KeyEvent.VK_LEFT : // move left
				// call a method for move left
				tetrisModel.moveLeftOrRight(-1);
				break;
			case KeyEvent.VK_RIGHT: // move right
				// call a method for move right
				tetrisModel.moveLeftOrRight(+1);
				break;
			case KeyEvent.VK_DOWN : // down
				// call a method for down
				tetrisModel.moveDown();
				break;
			case KeyEvent.VK_Z: // rotate counter-clockwise
				// call a method for rotate counter-clockwise
				tetrisModel.rotate(false);
				break;
			case KeyEvent.VK_X: // rotate clockwise
				// call a method for rotate clockwise
				tetrisModel.rotate(true);
				break;			
			}
			updateView(); // call Model to update information on the screen
				
	}
	
	/**
	 * Calls View Model to update information on the screen
	 */
	private void updateView(){
		int [][] currentShape = new int [4][3];
		for (int i = 0; i < 4; ++i) {        		
    		currentShape [i][0]=tetrisModel.getCurrentShape().getBlockArray()[i].getXPos();
    		currentShape [i][1]=tetrisModel.getCurrentShape().getBlockArray()[i].getYPos();
    		currentShape [i][2]=tetrisModel.getCurrentShape().getBlockArray()[i].getColor();
    	}
		tetrisView.updateBoard(tetrisModel.NUM_COLS, tetrisModel.NUM_ROWS, tetrisModel.getBoard(), currentShape);
		tetrisView.updateStatusBar(tetrisModel.getLinesCleared(),
		tetrisModel.getTetrisesCleared(),
		tetrisModel.getLevel(), 
		tetrisModel.getNextPieceName());  // call Model to show score awards, next shape & current level
	}
	
	/**
	 * Shows a dialog at the end of the game and restarts the last level
	 */
	private void restartLevelDialog(){
		//Useful link: https://docs.oracle.com/javase/tutorial/uiswing/components/dialog.html
		int reply = JOptionPane.showConfirmDialog(null, "Game Over! Would you like to restart the last level?",
		    "Restart the last level",JOptionPane.YES_NO_OPTION);
		if (reply == JOptionPane.YES_OPTION) {
			tetrisModel.restartGame();
		} else System.exit(0);	
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
	}

	@Override
	public void keyTyped(KeyEvent arg0) {		
	}
}
