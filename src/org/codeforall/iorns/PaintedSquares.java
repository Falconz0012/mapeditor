package org.codeforall.iorns;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;

import javax.management.modelmbean.InvalidTargetObjectTypeException;
import java.util.Objects;

public class PaintedSquares {

    PaintedSquares(int x, int y,int width, int height){
        this.paintedSquare = new Rectangle(x,y,width,height);
    }

    Rectangle paintedSquare;
    public  boolean isFilled;

    int chosenColor;

    public void setFilled() {
        isFilled =true;
    }

    public void setUnFilled() {
        isFilled = false;
    }

    public boolean isFilled(){
        return isFilled;
    }

    @Override
    public boolean equals(Object o) {
        int result;
        PaintedSquares square = (PaintedSquares) o;
        if(!(this.paintedSquare.getWidth() == square.paintedSquare.getWidth())){
            result = this.paintedSquare.getWidth() - square.paintedSquare.getWidth();
        }
        if(!(this.paintedSquare.getHeight() == square.paintedSquare.getHeight())){
          result =  this.paintedSquare.getWidth() - square.paintedSquare.getWidth();
        }
        if(!(this.paintedSquare.getX() == square.paintedSquare.getX())){
            result =this.paintedSquare.getX() -square.paintedSquare.getX();
        }
            result = this.paintedSquare.getY() - square.paintedSquare.getY();
        if(result == 0){
            return true;
        }
        return false;

    }


    @Override
    public int hashCode() {

        return Objects.hash(paintedSquare.getY(), paintedSquare.getX());
    }

    @Override
    public String toString() {
        return this.paintedSquare.getX() + "/"+ this.paintedSquare.getY() + "/"+this.getChosenColor();

    }

    public void setChosenColor(int chosenColor) {
        this.chosenColor = chosenColor;
    }
    public int getChosenColor() {
        return chosenColor;
    }


}
