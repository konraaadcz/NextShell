package main.java.net.nextshell.visual;





import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import javax.swing.*;
import main.java.net.nextshell.assets.Version;




public class LS extends JWindow {



    private final LSPanel content;
    private final int width = 700;
    private final int height = 360;




    public LS() {


        content = new LSPanel(width, height);
        setContentPane(content);

        setSize(width, height);
        setLocationRelativeTo(null);
    }


    public void showSplash() {

        setVisible(true);

        content.startAnimation(() -> dispose());


    }


    public void end() {


        dispose();
    }
}



class LSPanel extends JPanel {

    private final int w, h;

    private float progress = 0f;

    private float velocity = 0.0025f;

    private int stripeOffset = 0;

    private Timer timer;

    private Runnable onComplete;

    LSPanel(int width, int height) {

        this.w = width;
        this.h = height;


        setPreferredSize(new Dimension(w, h));
        setOpaque(false);

    }



    void startAnimation(Runnable onComplete) {


        this.onComplete = onComplete;
        int delay = 16;


        timer = new Timer(delay, e -> {

            stripeOffset = (stripeOffset + 2) % 60;

            progress += velocity;

            if (progress > 1f) progress = 1f;

            if (progress > 0.95f) velocity = 0.0008f;
            repaint();

            if (progress >= 1f) {

                timer.stop();

                new Timer(600, ev -> {

                    ((Timer)ev.getSource()).stop();

                    if (onComplete != null) onComplete.run();


                }).start();
            }
        });


        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g0) {

        super.paintComponent(g0);

        Graphics2D g = (Graphics2D) g0.create();

        try {

            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

            g.setColor(Color.BLACK);
            g.fillRect(0, 0, w, h);


            paintStripes(g);
            paintTitle(g);
            paintProgressBar(g);
            paintPercent(g);
            paintFooterText(g);



        } finally {


            g.dispose();
        }
    }

    private void paintStripes(Graphics2D g) {


        int stripeW = 120;


        for (int i = -2; i < w / stripeW + 4; i++) {


            int x = i * stripeW - stripeOffset;


            Polygon p = new Polygon();


            p.addPoint(x - stripeW/2, -40);

            p.addPoint(x + stripeW/2, -40);

            p.addPoint(x + stripeW/2 + 200, h + 40);

            p.addPoint(x - stripeW/2 + 200, h + 40);


            float alpha = 0.06f + (i % 3 == 0 ? 0.03f : 0f);


            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, clamp(alpha)));

            g.setColor(Color.WHITE);


            g.fill(p);

        }


        g.setComposite(AlphaComposite.SrcOver);
    }

    private void paintTitle(Graphics2D g) {

        String title = "NextShell";

        Font titleFont = new Font("SansSerif", Font.BOLD, 72);

        g.setFont(titleFont);

        FontMetrics fm = g.getFontMetrics();

        int textW = fm.stringWidth(title);



        int x = (w - textW) / 2;

        int y = h / 2;

        g.setColor(new Color(255,255,255,60));

        for (int i = 1; i <= 6; i++) {

            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.04f * (7 - i)));

            g.drawString(title, x - i, y - i);
        }



        g.setComposite(AlphaComposite.SrcOver);
        g.setColor(Color.WHITE);
        g.drawString(title, x, y);


        int underlineY = y + 10;
        int pad = 10;


        g.setStroke(new BasicStroke(3f));
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.25f));
        g.drawLine(x - pad, underlineY, x + textW + pad, underlineY);
        g.setComposite(AlphaComposite.SrcOver);



    }

    private void paintProgressBar(Graphics2D g) {


        int barW = (int)(w * 0.6);
        int barH = 18;
        int x = (w - barW) / 2;
        int y = h - 80;




        RoundRectangle2D bgRect = new RoundRectangle2D.Float(x, y, barW, barH, barH, barH);


        g.setColor(new Color(255,255,255,20));
        g.fill(bgRect);
        g.setColor(new Color(255,255,255,50));
        g.setStroke(new BasicStroke(1.5f));
        g.draw(bgRect);




        int innerW = Math.max(6, (int)(barW * progress));

        RoundRectangle2D fillRect = new RoundRectangle2D.Float(x+2, y+2, innerW-4, barH-4, barH-4, barH-4);

        GradientPaint gp = new GradientPaint(x, y, new Color(0,180,0), x+barW, y, new Color(30,220,30));

        g.setPaint(gp);
        g.fill(fillRect);
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.18f));
        g.fill(new RoundRectangle2D.Float(x+2, y+2, innerW-4, (barH-4)/2f, barH-4, barH-4));



        int sparkleW = 40;
        int sparkleX = x + (int)((barW - sparkleW) * (Math.abs(Math.sin(progress * Math.PI * 2))));



        g.setColor(Color.WHITE);
        g.fillRoundRect(sparkleX, y+1, sparkleW, barH-2, barH-2, barH-2);
        g.setComposite(AlphaComposite.SrcOver);



    }

    private void paintPercent(Graphics2D g) {


        String pct = String.format("%d%%", Math.round(progress * 100));
        Font pctFont = new Font("SansSerif", Font.BOLD, 18);

        g.setFont(pctFont);

        FontMetrics fm = g.getFontMetrics();



        int textW = fm.stringWidth(pct);
        int x = (w - textW) / 2;
        int y = h - 110;



        g.setColor(new Color(255,255,255,200));
        g.drawString(pct, x, y);



    }

    private void paintFooterText(Graphics2D g) {



        String info = "Initializing components…";


        Font f = new Font("SansSerif", Font.PLAIN, 12);


        g.setFont(f);

        FontMetrics fm = g.getFontMetrics();


        int x = (w - fm.stringWidth(info)) / 2;

        int y = h - 40;

        g.setColor(new Color(255,255,255,120));

        g.drawString(info, x, y);

        String ver = Version.getVersion();

        g.setColor(new Color(255,255,255,70));

        g.drawString(ver, 10, h - 10);

    }

    private float clamp(float v) {

        return Math.max(0f, Math.min(1f, v));


    }

    
}

