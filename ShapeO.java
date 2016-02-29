/**
 * Subclass of Shape
 */
public class ShapeO extends Shape {
     
    //set a color index for this Tetris shape
    public static final int COLOR = 5; 
     
    /**
     * Constructor
     * Set the coordinates of the blocks in a way that would form this figure
     */
    public ShapeO (){
        //set coordinates of the blocks
    	super(5, 0, 6, 0, 5, 1, 6, 1, COLOR, "O");
    }
 
     
    /**
     * @Override
     * Rotate shape
     */
    public int[][] rotate(int rotationIndex) {
     switch (rotationIndex){  // coordinate adjustment for rotation all blocks of this shape
         default: return new int[][] {{0,0},{0,0},{0,0},{0,0}};
         }
    }
 
}