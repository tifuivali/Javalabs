/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Elements;

import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.StrokeLineCap;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

/**
 *
 * @author tifui
 */
public class Desen {

    private Canvas canvas;
    private GraphicsContext gc;
    private double unitateX;
    private double unitateY;
    private double minXCoordonate;
    private double maxXCoordonate;
    private double minYCoordonate;
    private double maxYCoordonate;
    private double minXCoordonateDesenare;
    private double maxXCoordonateDesenare;
    String function;
    Plot plot;

    public Desen(Canvas canvas, double minXCoordonate, double maxXCoordonate, String function) {
        this.minXCoordonate = minXCoordonate;
        this.maxXCoordonate = maxXCoordonate;
        if (function != null && function.length() > 0) {
            double m1 = Math.ceil(evaluateExpresion(getExpresion(function), minXCoordonate));
            double m2 = Math.ceil(evaluateExpresion(getExpresion(function), maxXCoordonate));
            if (Math.abs(m1) >= Math.abs(m2)) {
                this.maxYCoordonate = m1;
            } else {
                this.maxYCoordonate = m1;
            }
        } else {
            this.maxYCoordonate = 20;
        }

        this.minYCoordonate = -this.maxYCoordonate;
        double nrX = Math.max(Math.abs(minXCoordonate), Math.abs(maxXCoordonate));
        minXCoordonateDesenare = -nrX;
        maxXCoordonateDesenare = nrX;
        this.canvas = canvas;
        plot = new Plot(canvas.getWidth(), canvas.getHeight(), (int) nrX * 2, (int) maxYCoordonate * 2);

        gc = canvas.getGraphicsContext2D();
        desenareAxe();

    }

    public void setGcColor(Color color) {
        gc.setStroke(color);
    }

    public void setGcDimension(double dim) {
        gc.setLineWidth(dim);
    }

    public Desen(Canvas canvas, double minXCoordonate, double maxXCoordonate) {
        this.minXCoordonate = minXCoordonate;
        this.maxXCoordonate = maxXCoordonate;
        this.maxYCoordonate = 20;
        this.minYCoordonate = -this.maxYCoordonate;
        double nrX = Math.abs(minXCoordonate) + Math.abs(maxXCoordonate);
        this.canvas = canvas;
        plot = new Plot(canvas.getWidth(), canvas.getHeight(), (int) nrX, (int) maxYCoordonate * 2);
        desenareAxe();
        gc = canvas.getGraphicsContext2D();

    }

    private Expression getExpresion(String func) {
        Expression e = new ExpressionBuilder(func)
                .variables("x")
                .build();
        return e;
    }

    private double evaluateExpresion(Expression e, double x) {
        e.setVariable("x", x);
        return e.evaluate();
    }

    public void load_from_image(File file) throws IOException {
        
        Image img = new Image(file.toURI().toString());
        gc.drawImage(img, 0, 0, canvas.getWidth(), canvas.getHeight());
    }

    public void setGcDash(double x1, double x2) {
        gc.setLineDashes(x1, x2);
    }
    
        
    public void desenare(String function) {
        
        gc.setLineCap(StrokeLineCap.ROUND);
        gc.beginPath();
        StringBuilder pathBuilder = new StringBuilder();
        System.out.println("minx=" + minXCoordonate);
        Point2D start=null;
        
        try{
           start = plot.getPointAt(getMinXCoordonate(), evaluateExpresion(getExpresion(function), getMinXCoordonate()));
          pathBuilder.append("M").append(start.getX()).append(" ").append(start.getY());
        }catch(IllegalArgumentException ex)
        {
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Not Valid Expresion");
            alert.setHeaderText("Expresia introdusa nu este valida!");
            alert.showAndWait();
            
        }
        if(start==null)
            return;
        double line[]=gc.getLineDashes();
        double dim=gc.getLineWidth();
        gc.setLineDashes(0,0);
        gc.setLineWidth(1);
        gc.strokeText(function, start.getX(), start.getY());
        gc.setLineWidth(dim);
        gc.setLineDashes(line);
        for (double x = getMinXCoordonate() + 0.2; x <= maxXCoordonate; x += 0.2) {
            Point2D point = plot.getPointAt(x, evaluateExpresion(getExpresion(function), x));
            pathBuilder.append(" L").append(point.getX()).append(" ").append(point.getY());
        }
        //pathBuilder.append(" Z");
        gc.appendSVGPath(pathBuilder.toString());
        gc.stroke();
        gc.closePath();
    }

    public void desenareAxe() {
        double w = canvas.getWidth();
        double h = canvas.getHeight();
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.strokeLine(0, h / 2, w, h / 2);
        gc.strokeLine(w / 2, 0, w / 2, h);
        gc.fillText("X", w - 10, h / 2 + 15);
        gc.fillText("Y", w / 2 + 10, 15);
        gc.fillText("O", w / 2 + 10, h / 2 - 10);
        unitateX = w / 4;
        unitateY = h / 20;
        //desenez punctele
        Color color = Color.LIGHTSKYBLUE;
        Paint defColor = gc.getStroke();
        gc.setStroke(color);
        //desenez punce axa X
        for (double i = minXCoordonateDesenare; i <= maxXCoordonateDesenare; i +=1) {
            Point2D point = plot.getPointAt(i, 0);
            if (i == 0) {
                continue;
            }
            gc.strokeLine(point.getX(), point.getY() - 5, point.getX(), point.getY() + 5);

            gc.strokeText("" + (int) i, point.getX(), point.getY() - 10);
        }
        //desenez puncte axa Y
         int scale=1;
        if(maxYCoordonate>40)
          scale=(int)maxYCoordonate/20;

        for (double i = minYCoordonate; i <= maxYCoordonate; i += scale) {
            Point2D point = plot.getPointAt(0, i);
            if (i == 0) {
                continue;
            }

            gc.strokeLine(point.getX() - 5, point.getY(), point.getX() + 5, point.getY());
            if (i % 5 == 0) {
                gc.strokeText("" + (int) i, point.getX() - 35, point.getY());
            }
        }
        gc.setStroke(defColor);
    }

    public void stergereDesen() {
        double w = canvas.getWidth();
        double h = canvas.getHeight();
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, w, h);
        desenareAxe();
    }

    public void stergereDesenCuAxe() {
        double w = canvas.getWidth();
        double h = canvas.getHeight();
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, w, h);
    }

    /**
     * @return the minXCoordonate
     */
    public double getMinXCoordonate() {
        return minXCoordonate;
    }

    /**
     * @param minXCoordonate the minXCoordonate to set
     */
    public void setMinXCoordonate(double minXCoordonate) {
        this.minXCoordonate = minXCoordonate;
    }

    /**
     * @param maxXCoordonate the maxXCoordonate to set
     */
    public void setMaxXCoordonate(double maxXCoordonate) {
        this.maxXCoordonate = maxXCoordonate;
    }
}
