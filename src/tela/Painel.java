package tela;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseWheelEvent;

public class Painel extends JComponent {
    private Image image;
    private Graphics2D graphics2D;
    private int actX, actY,oldX,oldY;

    public void setFerramenta(String ferramenta) {
        this.ferramenta = ferramenta;
    }

    private String ferramenta="";
    public Painel() {
        setDoubleBuffered(false);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                oldX = e.getX();
                oldY = e.getY();
            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                actX = e.getX();
                actY = e.getY();

                if (graphics2D != null && ferramenta.equals("")) {
                    graphics2D.drawLine(oldX, oldY, actX, actY);
                    repaint();
                    oldX = actX;
                    oldY = actY;
                }else if(ferramenta.equals("reta")){
                    reta(actX,actY,oldX,oldY);
                }
            }
        });
    }
    protected void paintComponent(Graphics g) {
        if (image == null) {
            // image to draw null ==> we create
            image = createImage(getSize().width, getSize().height);
            graphics2D = (Graphics2D) image.getGraphics();
            // enable antialiasing
            graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            // clear draw area
            clear();
        }

        g.drawImage(image, 0, 0, null);
    }

    public void clear(){
        graphics2D.setPaint(Color.white);
        // draw white on entire draw area to clear
        graphics2D.fillRect(0, 0, getSize().width, getSize().height);
        graphics2D.setPaint(Color.black);
        this.ferramenta="";
        repaint();
    }
    public void red(){
        graphics2D.setPaint(Color.red);
    }
    public void black(){
        graphics2D.setPaint(Color.black);

    }
    public void blue(){
        graphics2D.setPaint(Color.blue);

    }
    public void magenta(){
        graphics2D.setPaint(Color.magenta);

    }
    public void green(){
        graphics2D.setPaint(Color.green);
    }

    public void reta(int x1,int y1,int x2, int y2){
        int erro, deltaX, deltaY;
        erro = 0;
        deltaX = x2 - x1;
        deltaY = y2 - y1;
        Graphics2D gIni=graphics2D;
        if((Math.abs(deltaY)>=Math.abs(deltaX) && y1>y2)
                ||(Math.abs(deltaY)<Math.abs(deltaX) && deltaY<0)){

            x1 = x2;
            y1 = y2;
            deltaX = x1-x2;
            deltaY = y1-y2;
        }
        if(deltaX>=0){
            if(Math.abs(deltaX)>=Math.abs(deltaY)){
                for(int i=1;i<Math.abs(deltaX);i++){
                    if(erro<0){
                        x1++;
                        drawPoint(x1,y1,gIni);
                        erro += deltaY;
                    }else{
                        x1++;
                        y1++;
                        drawPoint(x1,y1,gIni);
                        erro += deltaY - deltaX;
                    }
                }
            }else{
                for(int i=1;i<Math.abs(deltaY);i++){
                    if(erro<0){
                        x1++;
                        y1++;
                        drawPoint(x1,y1,gIni);
                        erro += deltaY - deltaX;
                    }else{
                        y1++;
                        drawPoint(x1,y1,gIni);
                        erro -= deltaX;
                    }
                }
            }
        }else{
            if(deltaX>=Math.abs(deltaY)){
                for(int i=1;i<Math.abs(deltaX);i++){
                    if(erro<0){
                        x1--;
                        drawPoint(x1,y1,gIni);
                        erro += deltaY;
                    }else{
                        x1--;
                        y1++;
                        drawPoint(x1,y1,gIni );
                        erro += deltaY + deltaX;
                    }
                }
            }else{
                for(int i=1;i<Math.abs(deltaY);i++){
                    if(erro<0){
                        x1--;
                        y1++;
                        drawPoint(x1,y1,gIni);
                        erro += deltaY + deltaX;
                    }else{
                        y1++;
                        drawPoint(x1,y1,gIni);
                        erro += deltaX;
                    }
                }
            }
        }

    }

    public void drawPoint(int x,int y, Graphics2D g){
        g.drawLine(x,y,x,y);
        repaint();
    }

    }


