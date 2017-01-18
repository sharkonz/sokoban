package view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class MazeDisplayer extends Canvas {
	int[][] mazeData;
	private StringProperty wallFileName;
	
	int cCol,cRow;
	
	

	public MazeDisplayer() {
		wallFileName=new SimpleStringProperty();
		cCol=2;
		cRow=0;
	}

	public int getcCol() {
		return cCol;
	}

	public void setcCol(int cCol) {
		this.cCol = cCol;
	}

	public int getcRow() {
		return cRow;
	}

	public void setcRow(int cRow) {
		this.cRow = cRow;
	}
	
	public void setCharecterPosition(int row, int col){
		cCol=col;
		cRow=row;
		redraw();
	}

	public String getWallFileName() {
		return wallFileName.get();
	}

	public void setWallFileName(String wallFileName) {
		this.wallFileName.set(wallFileName);
	}

	
	public int[][] getMazeData() {
		return mazeData;
	}

	public void setMazeData(int[][] mazeData) {
		this.mazeData = mazeData;
		redraw();
	}
	public void redraw() {
		if (mazeData!=null) {
			double W= getWidth();
			double H=getHeight();
			double w=W/mazeData[0].length;
			double h=H/mazeData.length;
			
			GraphicsContext gc=getGraphicsContext2D();
			
			Image wall=null;
			
			try {
				wall=new Image(new FileInputStream(wallFileName.get()));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			gc.clearRect(0, 0, W, H);
			
			for (int i=0;i<mazeData.length;i++)
				for (int j=0;j<mazeData[i].length;j++){
					if (mazeData[i][j]!=0) {
						if(wall==null)
							gc.fillRect(j*w, i*h, w, h);
						else
							gc.drawImage(wall,j*w, i*h, w, h);
					}		
				}
		
		gc.setFill(Color.GREEN);
		gc.fillOval(cCol*w, cRow*h, w, h);
		
		}
	}
		
	
}
