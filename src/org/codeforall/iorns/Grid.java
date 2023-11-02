package org.codeforall.iorns;

import java.awt.*;
import java.io.*;
import java.util.HashMap;
import java.util.HashSet;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.codeforall.iorns.PaintedSquares;

import static java.lang.Integer.valueOf;

public class  Grid {


    Rectangle background;
    MySquare me;

    private int paddingX;
    private int paddingY;

    private int colNumber;
    private int colSize;
    private int rowNumber;
    private int rowSize;
    private int colorCode;

    public void setColorCode(int colorCode) {
        this.colorCode = colorCode;
    }

    public int getColorCode() {
        return colorCode;
    }

    public HashSet<PaintedSquares> paintedSquares;
    public HashMap<Integer,Color> squareColorMap;

    Grid(int paddingX, int paddingY, int colNumber, int rowNumber) {
        this.background = new Rectangle(paddingX, paddingY, 400, 400);
        this.paddingX = paddingX;
        this.paddingY = paddingY;
        this.colNumber = colNumber;
        this.colSize = background.getWidth() / colNumber;
        this.rowNumber = rowNumber;
        this.rowSize = background.getHeight() / rowNumber;
        paintedSquares = new HashSet<>();
        MySquare me = new MySquare(this);
    }
    public void setMap(){
        squareColorMap.put(0,Color.GREEN);
        squareColorMap.put(1,Color.YELLOW);
        squareColorMap.put(2,Color.RED);
        squareColorMap.put(3,Color.PINK);
        squareColorMap.put(4,Color.ORANGE);
        squareColorMap.put(5,Color.CYAN);
        squareColorMap.put(6,Color.BLACK);


    }

    public void drawBackground() {
        background.draw();
    }

    public void drawRows() {
        int rowStartingPointY = paddingY;
        for (int i = 0; i < rowNumber; i++) {
            if (i < 1) {
                Rectangle row = new Rectangle(paddingX, rowStartingPointY, background.getWidth(), rowSize);
                row.draw();
                rowStartingPointY += rowSize;
                continue;
            }
            Rectangle row = new Rectangle(paddingX, rowStartingPointY, background.getWidth(), rowSize);
            rowStartingPointY += rowSize;
            row.draw();
        }
    }

    public void drawCols() {
        int colStartingPoint = paddingX;
        for (int i = 0; i < colNumber; i++) {
            if (i < 1) {
                Rectangle col = new Rectangle(colStartingPoint, paddingY, rowSize, background.getHeight());
                col.draw();
                colStartingPoint += colSize;
                continue;
            }
            Rectangle col = new Rectangle(colStartingPoint, paddingY, rowSize, background.getHeight());
            colStartingPoint += colSize;
            col.draw();
        }
    }

    public void init() {
        drawBackground();
        drawCols();
        drawRows();
        setMap();

    }

    public int getColSize() {
        return colSize;
    }

    public int getPaddingX() {
        return paddingX;
    }

    public int getPaddingY() {
        return paddingY;
    }

    public int getRowSize() {
        return rowSize;
    }

    public void saveSquares() {
        String result = "";
        for (PaintedSquares paintedSquares1 : this.paintedSquares) {
            if (!paintedSquares1.isFilled()) {
                continue;
            }
            result += paintedSquares1.toString() + "\n";

        }
        try {
            FileWriter fileWriter = new FileWriter("resources/positions.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(result);
            bufferedWriter.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void readSquares() {
        String result = "";
        String line = "";
        try {
            FileReader fileReader = new FileReader("resources/positions.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((line = bufferedReader.readLine()) != null) {
                String[] positions = line.split("/");
                int valueX = Integer.parseInt(positions[0]);
                int valueY = Integer.parseInt(positions[1]);
                int colorCode = Integer.parseInt(positions[2]);
                setColorCode(colorCode);


                PaintedSquares newSquare = new PaintedSquares(valueX, valueY, getColSize(), getRowSize());
                int colorCode1 = colorCode;
                newSquare.setChosenColor(colorCode);
                this.paintedSquares.add(newSquare);
            }
            for (PaintedSquares square : paintedSquares) {
                square.paintedSquare.fill();
                square.setFilled();
                Color color = this.squareColorMap.get(getColorCode());
                color.brighter();
                square.paintedSquare.setColor();
            }

        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }


    }
}

