/**
 * Shape is an abstract class representing a Tetris figure
 *
 */
public abstract class Shape {
     
    //array of blocks that will make a Tetris shape
    private Block[] blockArray = new Block[4];
    
    private String pieceName;
     
    int rotationIndex;
     
     
    /**
     * Constructor
     * @param x and y coordinates of each block and color of blocks 
     */
    public Shape (int x1, int y1, int x2, int y2,int x3, int y3,int x4, int y4, int color, String pieceName){
           blockArray[0] = new Block(x1, y1, color);
           blockArray[1] = new Block(x2, y2, color);
           blockArray[2] = new Block(x3, y3, color);
           blockArray[3] = new Block(x4, y4, color);
           this.pieceName=pieceName;
            
           rotationIndex = 0;
    }
    
    public Block [] getBlockArray(){
    	return blockArray;
    }
    
    
    /**
     * Assign a rotation index to indicate which of the 4 rotation positions the shape is in
     */
    public void setRotationIndex(boolean isRotateClockwise){
    	if (isRotateClockwise==true){ // if rotate clockwise then assign next rotation index
    		if (rotationIndex<3){
    		rotationIndex++;
    		} else rotationIndex=0;
    	} else {					 // if rotate counter-clockwise then assign previous rotation index
    	 	if (rotationIndex==0){
    	 		rotationIndex=3;
        	} else rotationIndex--;
    	}
    		
    }
    
    /**
     * Get rotation index
     */
    public int getRotationIndex(){
    	return rotationIndex;	
    }
    
    /**
     * Get class name
     */
    public String getPieceName(){
    	return pieceName;
    }
    
     
    /**
     * Rotate shape
     */
    abstract int[][] rotate(int rotationIndex);
  
     
}
