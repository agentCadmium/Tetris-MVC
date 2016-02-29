import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JComponent;

public class TetrisNextPiece extends JComponent{
	private String nextPiece;
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		g.setColor(Color.BLACK);
		
		if (nextPiece!=null){
		switch (nextPiece){
		case "O": 
			g.fillRect(10, 10, 9, 9);
			g.fillRect(20, 10, 9, 9);
			g.fillRect(10, 20, 9, 9);
			g.fillRect(20, 20, 9, 9);
			break;
		case "L":
			g.fillRect(10, 0,  9, 9);
			g.fillRect(10, 10, 9, 9);
			g.fillRect(10, 20, 9, 9);
			g.fillRect(20, 20, 9, 9);
			break;
		case "J":
			g.fillRect(20, 0,  9, 9);
			g.fillRect(20, 10, 9, 9);
			g.fillRect(20, 20, 9, 9);
			g.fillRect(10, 20, 9, 9);
			break;
		case "I":
			g.fillRect(10, 0,  9, 9);
			g.fillRect(10, 10, 9, 9);
			g.fillRect(10, 20, 9, 9);
			g.fillRect(10, 30, 9, 9);
			break;
		case "T":
			g.fillRect(20,  0, 9, 9);
			g.fillRect(10, 10, 9, 9);
			g.fillRect(20, 10, 9, 9);
			g.fillRect(20, 20, 9, 9);
			break;
		case "S":
			g.fillRect(10, 0,  9, 9);
			g.fillRect(10, 10, 9, 9);
			g.fillRect(20, 10, 9, 9);
			g.fillRect(20, 20, 9, 9);
			break;
		case "Z":
			g.fillRect(20, 0,  9, 9);
			g.fillRect(20, 10, 9, 9);
			g.fillRect(10, 10, 9, 9);
			g.fillRect(10, 20, 9, 9);
			break;	
		}}
		
	}	
	
	public void repaintNextPiece(String nextPiece){
		this.nextPiece=nextPiece;
		super.repaint();
	}
}
