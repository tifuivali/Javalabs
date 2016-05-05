/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Elements;

import java.awt.geom.Point2D;

/**
 *
 * @author tifui
 */
public class Plot {

    private Point2D origine;
    private double unitateX;
    private double unitateY;
    private double width;
    private double height;

    public Plot(double width, double height, int nrUnitatiX, int nrUnitatiY) {
        this.width = width;
        this.height = height;
        width -= 20;
        height -= 20;
        origine = new Point2D.Double();
        origine.setLocation(width / 2, height / 2);
        unitateX = width / nrUnitatiX;
        unitateY = height / nrUnitatiY;
    }

    public Point2D getPointAt(double coordonateX, double coordonateY) {
        Point2D point = new Point2D.Double();
        double x = getOrigine().getX() + (coordonateX * getUnitateX() + 10);
        double y = getOrigine().getY() - (coordonateY * getUnitateY()) + 10;
        point.setLocation(x, y);
        return point;
    }

    /**
     * @return the origine
     */
    public Point2D getOrigine() {
        return origine;
    }

    /**
     * @param origine the origine to set
     */
    public void setOrigine(Point2D origine) {
        this.origine = origine;
    }

    /**
     * @return the unitateX
     */
    public double getUnitateX() {
        return unitateX;
    }

    /**
     * @param unitateX the unitateX to set
     */
    public void setUnitateX(double unitateX) {
        this.unitateX = unitateX;
    }

    /**
     * @return the unitateY
     */
    public double getUnitateY() {
        return unitateY;
    }

    /**
     * @param unitateY the unitateY to set
     */
    public void setUnitateY(double unitateY) {
        this.unitateY = unitateY;
    }

    /**
     * @return the width
     */
    public double getWidth() {
        return width;
    }

    /**
     * @param width the width to set
     */
    public void setWidth(double width) {
        this.width = width;
    }

    /**
     * @return the height
     */
    public double getHeight() {
        return height;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(double height) {
        this.height = height;
    }

}
