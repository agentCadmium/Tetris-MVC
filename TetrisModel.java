import java.awt.Color;
import java.awt.Point;
import java.util.Random;

/**
 * MVC - MODEL
 * @author Saverchenko
 *
 */

public class TetrisModel {

	/** default number of rows in a standard Tetris board **/
	public static final int NUM_ROWS=18;
	/** number of columns in a standard Tetris board	 **/
	public static final int NUM_COLS=10;
	/** number of shapes **/
	private static final int NUM_SHAPES=7;
	/** number of cleaned lines for next level */
	private static final int NUM_LINES_NEXT_LEVEL=10;
	/** number of cleaned lines for next level */
	private static final double SPEED_INCREASE= 0.8; // 0.8 increase speed by 20%
	/** current board **/
	private int [][] board;
	/** current shape **/
	private Shape currentShape;
	/** next shape **/
	private Shape nextShape;
	/** true when game over **/
	private boolean isFinished = false;
	/**	number of lines cleared */	
	private int linesCleared=0;
	/**	number of tetrises cleared */
	private int tetrisesCleared=0;
	/**	speed of pieces falling */
	private int speed=1000; // 1 sec.
	/** level of the game */
	private int level=1;
	/** variable of a thread allows to interrupt the running thread. It's necessary to change speed and start a new thread */     
	private	Thread t;
	
	
	/** saved board	(allows the user to restart a level) */
	private int savedBoard[][];
	/**	saved number of lines cleared (allows the user to restart a level) */
	private int savedLinesCleared;
	/**	saved number of tetrises cleared (allows the user to restart a level) */
	private int savedTetrisesCleared;
	
		

	/**
	 * Constructor.
	 */
	public TetrisModel() {
			board = new int[NUM_COLS][NUM_ROWS];
			// initialization a new game with a clear board
			clearBoard();			
			
			// get the first shape
			currentShape=generateRandomShape();	
			// get the next shape
			nextShape=generateRandomShape();
			
			
			// create additional array with a board (allows the user to restart a level)
			savedBoard= new int[NUM_COLS][NUM_ROWS];			
						
			
			startGame(); // start to move a shape down	
			
					
	}
	

	/**
	 * Starts a game
	 */
	public void startGame(){
		// every 1 sec. moves current shape down
		t=new Thread(new Runnable() {
			public void run() {
				while (true) {
					if(!Thread.interrupted()){
						try {
							Thread.sleep(speed);
							moveDown(); // moves current shape down
							} catch ( InterruptedException e ) {}
					}else return; // interrupt the running thread
				}  
			}
		});
		t.start();
	}
	

	
	/**
	 * Saves a game and allows the user to restart the last level 
	 */
	public void saveGame(){
		for (int y=0; y<NUM_ROWS;y++)
			for (int x=0; x<NUM_COLS; x++){
				savedBoard[x][y]=board[x][y];
			}
		savedLinesCleared=linesCleared;
		savedTetrisesCleared=tetrisesCleared;
	}
	
	/**
	 * Restores a game and allows the user to restart the last level 
	 */
	public void restartGame(){
		for (int y=0; y<NUM_ROWS;y++)
			for (int x=0; x<NUM_COLS; x++){
				board[x][y]=savedBoard[x][y];
			}
		linesCleared=savedLinesCleared;
		tetrisesCleared=savedTetrisesCleared;
		isFinished=false;
		currentShape=generateRandomShape(); // get the first shape	
		nextShape=generateRandomShape(); // get the next shape
		startGame(); // start to move a shape down	
	}
	
	
	
	
	/**
	 * 
	 * Return an array of the board. 
	 * @return an array with a board
	 */
	public int[][] getBoard(){
		return board;
	}
	
	/**
	 * Return the name of the the next shape 
	 * @return the name of the the next shape
	 */
	public String getNextPieceName(){
		return nextShape.getPieceName();
	}
	
	
	/**
	 * Return the current level of the game
	 * @return the current level
	 */
	public int getLevel(){
		return level; 
	}
	
	/**
	 * Return current shape
	 * @return current Shape
	 */
	public Shape getCurrentShape(){
		return currentShape;
	}
	
	/**
	 * Return true if the game is finished
	 * @return boolean 
	 */
	public boolean getIsFinished(){
		return isFinished;
	}
	
	/**
	 * Return number of lines cleared
	 * @return
	 */
	public int getLinesCleared(){
		return linesCleared;
	}
	
	/**
	 * Return number of tetrisesCleared
	 * @return
	 */
	public int getTetrisesCleared(){
		return tetrisesCleared;
	}
	
	
	/** 
	 * Clear the board to start a new game.
	 * populated with 0. 0 means no figure present. 
	 */
	public void clearBoard(){
		for (int y=0; y<NUM_ROWS;y++)
			for (int x=0; x<NUM_COLS; x++){
				board[x][y]=0;
			}
	}
	
	
    /** 
     * Generates a random number to represent one of the Tetris shapes available
     * in Full version there is MUST BE 7 shapes 
     */
    public Shape generateRandomShape(){
		Random randomNumber = new Random ();
		int iNumber=randomNumber.nextInt(NUM_SHAPES); //generates a random number from 0 to NUMBER_SHAPES-1
		
    	switch (iNumber){
      	case 0: 
      		return new ShapeT();
      	case 1: 
      		return new ShapeZ();
      	case 2:
      		return new ShapeI();
      	case 3:
      		return new ShapeL();
      	case 4:
      		return new ShapeO();
      	case 5:      		
      		return new ShapeS();
      	case 6:	
      		return new ShapeJ();
      	default: 
      		return null;
      	}
    }

	/**
	 * Check free space for a piece moving down, left or right
	 */
	private boolean checkSpace(int plusX, int plusY, Shape currentShape) {
		for (int i = 0; i < 4; i++) { // for all blocks in the current shape...
				// calculates perspective coordinates
	            int x = currentShape.getBlockArray()[i].getXPos() + plusX;
	            int y = currentShape.getBlockArray()[i].getYPos() + plusY;	            
	            if (x < 0 | x >= NUM_COLS | y < 0 | y >= NUM_ROWS){
	                return false;	 // false if we try to move outside the playing area             
	            }
	            if (board[x][y]!=0){ // false if the other shape is already on this place
	            	return false;	            	
	            }
	    }
		return true; // all positions are vacant
	}

	
	/**
	 * Check free space for a piece rotation
	 */
	private boolean checkSpaceForRoration(int [][] newCoordinates, Shape currentShape) {
		for (int i = 0; i < 4; i++) {	// for all blocks in the current shape...		
				// calculates perspective coordinates
	            int x = currentShape.getBlockArray()[i].getXPos() + newCoordinates[i][0];
	            int y = currentShape.getBlockArray()[i].getYPos() + newCoordinates[i][1];
	            
	            if (x < 0 | x >= NUM_COLS | y < 0 | y >= NUM_ROWS){
	                return false;	 // false if we try to move outside the playing area               
	            }
	            if (board[x][y]!=0){ // false if the other shape is already on this place
	            	return false;	            	
	            }
	        }
		return true; // can be rotated
	}
	
	
	
	/**
	 * Move a shape one row down 
	 */
    public void moveDown(){   
    	
        if (checkSpace(0, +1, currentShape)==true){ // check if perspective coordinates for all blocks of the current shape are vacant
        	
        	for (int i = 0; i < 4; i++) {
        		currentShape.getBlockArray()[i].setYPos(currentShape.getBlockArray()[i].getYPos()+1); // add to the Y-coordinates all blocks of the current shape +1       		
        	}
        } else { // if we can't move down current shape       	
        	for (int i = 0; i < 4; i++) {
        		// the current shape becomes a part of the board
        		board[currentShape.getBlockArray()[i].getXPos()][currentShape.getBlockArray()[i].getYPos()]=currentShape.getBlockArray()[i].getColor(); // save color all blocks of the current shape
        		// check a status of the game. If a part of shape lying on the first row of the board then the game over  
        		if (currentShape.getBlockArray()[i].getYPos()<1) {
        			       			
        			gameLost();
        			return; // exit from "for" loop  				
        		}
        	}
        	if (isFinished==false) {
        		// clear completed rows
        		clearLines();
        		// generate a new shape        		
        		currentShape=nextShape;
        		nextShape=generateRandomShape();
        	} 
        }
          
        
    }

    
	/**
	 * Move a shape one colon left of right 
	 */
    public void moveLeftOrRight(int newX){    	
        if (checkSpace(newX, 0, currentShape)==true){ // check if perspective coordinates for all blocks of the current shape are vacant
        	for (int i = 0; i < 4; i++) {
        		currentShape.getBlockArray()[i].setXPos(currentShape.getBlockArray()[i].getXPos()+newX);  // add to the X-coordinates all blocks of the current shape +1 if move right and -1 when move left      		
        	}
        }
    }
	
    /**
     * Invoked when a Tetris shape reaches the top of the game board
     */
    public void gameLost()
    {
    	isFinished=true;
    	t.interrupt(); // interrupt the running thread
    }
     
    
    
    /**
     * Rotate current shape clockwise or counter-clockwise
     * @param isRotateClockwise
     */    
    public void rotate (boolean isRotateClockwise){    	
    	int rotationIndex=currentShape.getRotationIndex();
    	
    	if (isRotateClockwise==false){ // if rotate counter-clockwise than take (not current) the previous rotationIndex
    	 	if (rotationIndex==0){
    	 		rotationIndex=3;
        	} else rotationIndex--;
    	}       	
    	
    	int [][] newCoordinates=currentShape.rotate(rotationIndex);
    	
    	if (isRotateClockwise==false){ // if rotate counter-clockwise than convert rotate values to opposite  
    		for (int i = 0; i < 4; i++) {
    			newCoordinates[i][0]*=-1;
    			newCoordinates[i][1]*=-1;
    		}
    	}    	
    	
    	if (checkSpaceForRoration(newCoordinates, currentShape)){ // check if perspective coordinates for all blocks of the current shape are vacant
    		currentShape.setRotationIndex(isRotateClockwise); // save new value of rotationIndex of the current shape
    		for (int i = 0; i < 4; i++) {	// change coordinates for all blocks of the current shape   			
    			currentShape.getBlockArray()[i].setXPos (currentShape.getBlockArray()[i].getXPos() + newCoordinates[i][0]);
    			currentShape.getBlockArray()[i].setYPos (currentShape.getBlockArray()[i].getYPos() + newCoordinates[i][1]); 
    		}
    	}
    }
    
  
    
    /**
     * Invoked when a line of Tetris blocks is formed
     */
    public void clearLines() {
    	boolean hasRowSpace;
    	int numberLinesCleared = 0;
    	for (int y=0; y<NUM_ROWS;y++) {
    		hasRowSpace = false;
    		for (int x=0; x<NUM_COLS; x++){
    			if (board[x][y]==0) {
    				hasRowSpace=true;
    				break;
    			}
    		}
    		if (hasRowSpace==false) {
    			numberLinesCleared++;
    			shiftBlocksDown(y); 	// clear completed row
    			y--;
    		}
    	}  

    	// compute award score
    	switch (numberLinesCleared) {
    	case 1:
    		linesCleared += 1;
    		break;
    	case 2:
    		linesCleared += 2;
    		break;
    	case 3:
    		linesCleared += 3;;
    		break;
    	case 4:
    		linesCleared += 4;
    		tetrisesCleared +=1; 
    		break;
    	}
    	

		if (((int)(linesCleared/NUM_LINES_NEXT_LEVEL))>level) {	// every 10 cleared lines increase the level and the speed of pieces falling
			level++;
			speed=(int) (speed*SPEED_INCREASE); // increase speed by 20%
			t.interrupt(); // interrupt the running thread			
			saveGame(); // save the game (allow the user to restart a level)			
			startGame(); // restart a thread with increased speed
		}
    	
    	
    }         
     
    /**
     * Shifts the static blocks down when line/tetris is removed
     */
    public void shiftBlocksDown(int rowNumber)
    {
		for (int y = rowNumber; y > 0; y--) {
			for (int x = 0; x < NUM_COLS; x++) {				
				board[x][y] = board[x][y-1];
			}
		}
    }


	

}
