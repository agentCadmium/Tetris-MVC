/**
 * Subclass of Shape
 */
public class ShapeI extends Shape {
     
    //set a color index for this Tetris shape
    public static final int COLOR = 3; 
     
    /**
     * Constructor
     * Set the coordinates of the blocks in a way that would form this figure
     */
    public ShapeI (){
        //set coordinates of the blocks
    	super(5, 0, 5, 1, 5, 2, 5, 3, COLOR, "I");
    }
 
     
    /**
     * @Override
     * Rotate shape
     */
    public int[][] rotate(int rotationIndex) {
     switch (rotationIndex){  // coordinate adjustment for rotation all blocks of this shape
         case 0: return new int[][] {{1,0},{0,-1},{-1,-2},{-2,-3}};         
         case 1: return new int[][] {{-1,3},{0,2},{1,1},{2,0}};
         case 2: return new int[][] {{-2,-3},{-1,-2},{0,-1},{1,0}};
         case 3: return new int[][] {{2,0},{1,1},{0,2},{-1,3}};
         default: return new int[][] {{0,0},{0,0},{0,0},{0,0}};
         }
    }
 
}