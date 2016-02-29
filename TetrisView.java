import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * MVC - VIEW  
 * @author Saverchenko
 * 
 */


public class TetrisView extends JFrame{

	public static final int SHAPE_SIZE=30;
	private JLabel linesClearedLabel;
	private JLabel tetrisesClearedLabel;
	private JLabel levelLabel;
	TetrisBoard tetrisBoard;
	TetrisNextPiece tetrisNextPiece;
	
	/**
	 * Constructor
	 **/
	public TetrisView(){
							
		// create a JFrame & add there the status bar
		super("Tetris");
		
		tetrisNextPiece=new TetrisNextPiece();
		tetrisNextPiece.setPreferredSize(new Dimension(50,50));
		add(createStatusBar(), BorderLayout.NORTH);        
		
        tetrisBoard=new TetrisBoard();
        add(tetrisBoard, BorderLayout.CENTER);

        
		// set size
        setSize((SHAPE_SIZE*10)+20, (SHAPE_SIZE*18)+90);

		// exit normally on closing the window
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

		// show frame
        setVisible( true );
	}
	
	
	public void updateStatusBar(int linesCleared, int tetrisesCleared, int level, String nextPiece){
		linesClearedLabel.setText(String.valueOf(linesCleared));
		tetrisesClearedLabel.setText(String.valueOf(tetrisesCleared));
		levelLabel.setText(String.valueOf(level));
		tetrisNextPiece.repaintNextPiece(nextPiece);
	}
	
	public void updateBoard(int numCols, int numRows, int[][] boardFromModel, int [][] currentShape){
			// make a copy current board (below add in it current shape)
			int [][] board= new int[numCols][numRows];
			for (int y=0; y<numRows;y++)
				for (int x=0; x<numCols; x++){
					board[x][y]=boardFromModel[x][y];
				}
			
			// add to the cope of current board the current shape
        	for (int i = 0; i < 4; ++i) {        		
        		board[currentShape[i][0]][currentShape[i][1]]=currentShape[i][2];
        	}	
	
		tetrisBoard.setInstances(numCols, numRows, SHAPE_SIZE, boardFromModel, currentShape);
		tetrisBoard.repaint();
	}
	
	private JPanel createStatusBar(){
		JPanel statusBar=new JPanel();

		
		// use BoxLayout to create 2 colons with 2 rows to show the results 
		// Links: 	https://docs.oracle.com/javase/tutorial/uiswing/layout/visual.html
		//			https://docs.oracle.com/javase/tutorial/uiswing/layout/box.html
		
		// create the first colon
		JPanel namePanel = new JPanel();
		namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.PAGE_AXIS  ));
		namePanel.add(new JLabel("Lines cleared:"));
		namePanel.add(new JLabel("Tetrises cleared:   "));
		namePanel.add(new JLabel("Level:   "));
		
		// create the second colon 
		JPanel scorePanel = new JPanel();
		scorePanel.setLayout(new BoxLayout(scorePanel, BoxLayout.PAGE_AXIS));
		linesClearedLabel=new JLabel("0");
		scorePanel.add(linesClearedLabel);
		tetrisesClearedLabel=new JLabel("0");
		scorePanel.add(tetrisesClearedLabel);
		levelLabel=new JLabel("1");
		scorePanel.add(levelLabel);
		
		// use BorderLayout to put there created colons with results 
		// Link:	https://docs.oracle.com/javase/tutorial/uiswing/layout/visual.html
		//			https://docs.oracle.com/javase/tutorial/uiswing/layout/border.html
		statusBar.setLayout(new BorderLayout());				
		statusBar.add(namePanel, BorderLayout.WEST);
		statusBar.add(scorePanel, BorderLayout.CENTER);	
		statusBar.add(tetrisNextPiece, BorderLayout.EAST);
		
	
		return statusBar;
	}
	

}
