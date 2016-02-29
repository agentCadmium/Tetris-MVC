import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JComponent;

/**
 * Represents the game board
 */
public class TetrisBoard extends JComponent
{
	
	int [][] board;
	int [][] currentShape;
	int numCols;
	int numRows;
	int shapeSize;

	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		// create a background
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, shapeSize*numCols, shapeSize*numRows);
		
		
		// show the board
		if (board!=null){
			for (int x = 0; x < numCols; x++) {
				for (int y = 0; y < numRows; y++) {
					if (board[x][y]>0){
						g.setColor(getColor(board[x][y]));
						g.fillRect(shapeSize*x, shapeSize*y, shapeSize-1, shapeSize-1);
					}
				}
			}
		}
		
		// show the current shape
		if (currentShape!=null){
			for (int i = 0; i < 4; ++i){
				g.setColor(getColor(currentShape[i][2]));
				g.fillRect(shapeSize*currentShape[i][0], shapeSize*currentShape[i][1], shapeSize-1, shapeSize-1);
			}
		}			
		
	}
	
	public void setInstances(int numCols, int numRows, int shapeSize, int[][] boardFromModel, int [][] currentShape){
		this.numCols=numCols;
		this.numRows=numRows;
		this.shapeSize=shapeSize;
		this.board=boardFromModel;
		this.currentShape=currentShape;		
	}	
	
	private Color getColor(int colorCode){
		switch (colorCode){
			case 1: return Color.RED;
			case 2: return Color.PINK;
			case 3: return Color.GREEN;
			case 4: return Color.CYAN;
			case 5: return Color.BLUE;
			case 6: return Color.MAGENTA;
			case 7: return Color.ORANGE;
			default: return Color.WHITE;
		}
	}

}
