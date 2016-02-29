/**
 * Subclass of Shape
 */
public class ShapeS extends Shape {
     
    //set a color index for this Tetris shape
    public static final int COLOR = 6; 
     
    /**
     * Constructor
     * Set the coordinates of the blocks in a way that would form this figure
     */
    public ShapeS (){
        //set coordinates of the blocks
        super(5, 0, 5, 1, 6, 1, 6, 2, COLOR, "S");
    }
 
     
    /**
     * @Override
     * Rotate shape
     */
    public int[][] rotate(int rotationIndex) {
     switch (rotationIndex){  // coordinate adjustment for rotation all blocks of this shape
         case 0: return new int[][] {{1,0},{0,-1},{-1,0},{-2,-1}};         
         case 1: return new int[][] {{0,2},{1,1},{0,0},{1,-1}};
         case 2: return new int[][] {{-2,-1},{-1,0},{0,-1},{1,0}};
         case 3: return new int[][] {{1,-1},{0,0},{1,1},{0,2}};
         default: return new int[][] {{0,0},{0,0},{0,0},{0,0}};
         }
    }
 
}
 