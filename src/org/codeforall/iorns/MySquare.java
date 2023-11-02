package org.codeforall.iorns;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MySquare {
    Grid grid;
    Rectangle myRectangle;

    Color paintColor;
    int colorIndex;

    MySquare(Grid grid) {
        this.grid = grid;
        this.colorIndex = 0;
        myRectangle = new Rectangle(grid.getPaddingX(), grid.getPaddingY(), grid.getColSize(), grid.getRowSize());
        myRectangle.setColor(Color.GREEN);
        myRectangle.draw();
        myRectangle.fill();
        MyHandler squareHandler = new MyHandler();
        squareHandler.init();
        this.paintColor = Color.BLACK;
        this.colorIndex = 7;
    }

    public PaintedSquares paintSquare() {
        PaintedSquares paintedSquare = new PaintedSquares(myRectangle.getX(), myRectangle.getY(), myRectangle.getWidth(), myRectangle.getHeight());
        paintedSquare.paintedSquare.setColor(paintColor);
        paintedSquare.setChosenColor(colorIndex);
        return paintedSquare;
    }

    public void changeColor() {
        if(colorIndex == 7){
            colorIndex = 0;
        }
        switch (colorIndex) {
            case 0:
                myRectangle.setColor(Color.GREEN);
                this.paintColor = Color.GREEN;
                break;
            case 1:
                myRectangle.setColor(Color.YELLOW);
                this.paintColor = Color.YELLOW;
                break;
            case 2:
                myRectangle.setColor(Color.RED);
                this.paintColor = Color.RED;
                break;
            case 3:
                myRectangle.setColor(Color.PINK);
                this.paintColor = Color.PINK;
                break;
            case 4:
                myRectangle.setColor(Color.ORANGE);
                this.paintColor = Color.ORANGE;
                break;
            case 5:
                myRectangle.setColor(Color.CYAN);
                this.paintColor = Color.CYAN;
                break;
            case 6:
                myRectangle.setColor(Color.BLACK);
                this.paintColor = Color.BLACK;
                break;
            default:
                System.out.println("Please choose a color from the available ones");


        }
        colorIndex++;

    }

    public class MyHandler implements KeyboardHandler {

        public void init() {

            Keyboard kb = new Keyboard(this);
            KeyboardEvent right = new KeyboardEvent();
            right.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
            right.setKey(KeyboardEvent.KEY_D);

            kb.addEventListener(right);

            KeyboardEvent left = new KeyboardEvent();
            left.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
            left.setKey(KeyboardEvent.KEY_A);

            kb.addEventListener(left);

            KeyboardEvent up = new KeyboardEvent();
            up.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
            up.setKey(KeyboardEvent.KEY_W);
            kb.addEventListener(up);

            KeyboardEvent down = new KeyboardEvent();
            down.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
            down.setKey(KeyboardEvent.KEY_S);
            kb.addEventListener(down);


            KeyboardEvent space = new KeyboardEvent();
            space.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
            space.setKey(KeyboardEvent.KEY_SPACE);
            kb.addEventListener(space);


            KeyboardEvent clear = new KeyboardEvent();
            clear.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
            clear.setKey(KeyboardEvent.KEY_C);
            kb.addEventListener(clear);

            KeyboardEvent rePaint = new KeyboardEvent();
            rePaint.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
            rePaint.setKey(KeyboardEvent.KEY_R);
            kb.addEventListener(rePaint);


            KeyboardEvent saveSquares = new KeyboardEvent();
            saveSquares.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
            saveSquares.setKey(KeyboardEvent.KEY_P);
            kb.addEventListener(saveSquares);

            KeyboardEvent loadSquares = new KeyboardEvent();
            loadSquares.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
            loadSquares.setKey(KeyboardEvent.KEY_L);
            kb.addEventListener(loadSquares);


            KeyboardEvent chooseColor = new KeyboardEvent();
            chooseColor.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
            chooseColor.setKey(KeyboardEvent.KEY_T);

            kb.addEventListener(chooseColor);

        }

        @Override
        public void keyPressed(KeyboardEvent keyboardEvent) {
            switch (keyboardEvent.getKey()) {
                case KeyboardEvent.KEY_D:
                    if (myRectangle.getX() + myRectangle.getWidth() >= grid.background.getWidth()) {
                        return;
                    }
                    myRectangle.translate(grid.getColSize(), 0);


                    break;
                case KeyboardEvent.KEY_A:
                    if (myRectangle.getX() <= grid.background.getX()) {
                        return;
                    }
                    myRectangle.translate(-grid.getColSize(), 0);
                    break;
                case KeyboardEvent.KEY_W:
                    if (myRectangle.getY() <= grid.background.getY()) {
                        return;
                    }
                    myRectangle.translate(0, -grid.getRowSize());
                    break;
                case KeyboardEvent.KEY_S:
                    if (myRectangle.getHeight() + myRectangle.getY() >= grid.background.getHeight()) {
                        return;
                    }
                    myRectangle.translate(0, grid.getColSize());
                    break;
                case KeyboardEvent.KEY_SPACE:
                    PaintedSquares newSquare = paintSquare();
                    for (PaintedSquares paintedSquares : grid.paintedSquares) {
                        if (newSquare.hashCode() == paintedSquares.hashCode()) {

                            if (paintedSquares.isFilled()) {
                                paintedSquares.setUnFilled();
                                paintedSquares.paintedSquare.delete();
                                grid.paintedSquares.remove(paintedSquares);
                                return;
                            }
                            paintedSquares.paintedSquare.fill();
                            paintedSquares.setFilled();
                            return;

                        }

                    }
                    newSquare.paintedSquare.fill();
                    newSquare.setFilled();
                    grid.paintedSquares.add(newSquare);

                    break;

                case KeyboardEvent.KEY_C:
                    for (PaintedSquares paintedSquare : grid.paintedSquares) {
                        if (paintedSquare.isFilled()) {

                            paintedSquare.paintedSquare.delete();
                            paintedSquare.setUnFilled();
                        }
                    }
                    break;
                case KeyboardEvent.KEY_R:
                    for (PaintedSquares paintedSquare : grid.paintedSquares) {
                        paintedSquare.paintedSquare.fill();
                        paintedSquare.setFilled();

                    }
                    break;
                case KeyboardEvent.KEY_P:
                    grid.saveSquares();
                    System.out.println("Squares saved");
                    break;

                case KeyboardEvent.KEY_L:
                    grid.readSquares();
                    break;

                case KeyboardEvent.KEY_T:
                    changeColor();
                    break;

            }
        }

        @Override
        public void keyReleased(KeyboardEvent keyboardEvent) {

        }


    }


}


