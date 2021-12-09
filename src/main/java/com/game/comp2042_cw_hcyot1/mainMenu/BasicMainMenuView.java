package com.game.comp2042_cw_hcyot1.mainMenu;

import com.game.comp2042_cw_hcyot1.GameFrame;
import com.game.comp2042_cw_hcyot1.frame.GenericFrame;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

public class BasicMainMenuView extends MainMenuView {
    private static final String GREETINGS = "Welcome to:";
    private static final String GAME_TITLE = "Brick Destroy";
    private static final String CREDITS = "Version 0.1";
    private static final String START_TEXT = "Start";
    private static final String MENU_TEXT = "Exit";

    private static final Color BG_COLOR = Color.GREEN.darker();
    private static final Color BORDER_COLOR = new Color(200, 8, 21); //Venetian Red
    private static final Color DASH_BORDER_COLOR = new Color(255, 216, 0);//school bus yellow
    private static final Color TEXT_COLOR = new Color(16, 52, 166);//egyptian blue
    private static final Color CLICKED_BUTTON_COLOR = BG_COLOR.brighter();
    private static final Color CLICKED_TEXT = Color.WHITE;
    private static final int BORDER_SIZE = 5;
    private static final float[] DASHES = {12, 6};

    private Rectangle menuFace;
    private Rectangle startButton;
    private Rectangle menuButton;

    private BasicStroke borderStoke;
    private BasicStroke borderStoke_noDashes;

    private Font greetingsFont;
    private Font gameTitleFont;
    private Font creditsFont;
    private Font buttonFont;

    private boolean startClicked;
    private boolean menuClicked;

    public BasicMainMenuView(Dimension area) {
        super(area);
        menuFace = new Rectangle(new Point(0, 0), area);

        Dimension btnDim = new Dimension(area.width / 3, area.height / 12);
        startButton = new Rectangle(btnDim);
        menuButton = new Rectangle(btnDim);

        borderStoke = new BasicStroke(BORDER_SIZE, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 0, DASHES, 0);
        borderStoke_noDashes = new BasicStroke(BORDER_SIZE, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);

        greetingsFont = new Font("Noto Mono", Font.PLAIN, 25);
        gameTitleFont = new Font("Noto Mono", Font.BOLD, 40);
        creditsFont = new Font("Monospaced", Font.PLAIN, 10);
        buttonFont = new Font("Monospaced", Font.PLAIN, startButton.height - 2);
    }
    
    @Override
    public void drawMenu() {
        drawContainer();

        /*
        all the following method calls need a relative
        painting directly into the HomeMenu rectangle,
        so the translation is made here so the other methods do not do that.
         */
        Color prevColor = graphics2D.getColor();
        Font prevFont = graphics2D.getFont();

        double x = menuFace.getX();
        double y = menuFace.getY();

        graphics2D.translate(x, y);

        //methods calls
        drawText();
        drawButton();
        //end of methods calls

        graphics2D.translate(-x, -y);
        graphics2D.setFont(prevFont);
        graphics2D.setColor(prevColor);
    }

    protected void drawContainer() {
        Color prev = graphics2D.getColor();

        graphics2D.setColor(BG_COLOR);
        graphics2D.fill(menuFace);

        Stroke tmp = graphics2D.getStroke();

        graphics2D.setStroke(borderStoke_noDashes);
        graphics2D.setColor(DASH_BORDER_COLOR);
        graphics2D.draw(menuFace);

        graphics2D.setStroke(borderStoke);
        graphics2D.setColor(BORDER_COLOR);
        graphics2D.draw(menuFace);

        graphics2D.setStroke(tmp);

        graphics2D.setColor(prev);
    }

    @Override
    protected void drawText() {
        graphics2D.setColor(TEXT_COLOR);

        FontRenderContext frc = graphics2D.getFontRenderContext();

        Rectangle2D greetingsRect = greetingsFont.getStringBounds(GREETINGS, frc);
        Rectangle2D gameTitleRect = gameTitleFont.getStringBounds(GAME_TITLE, frc);
        Rectangle2D creditsRect = creditsFont.getStringBounds(CREDITS, frc);

        int sX, sY;

        sX = (int) (menuFace.getWidth() - greetingsRect.getWidth()) / 2;
        sY = (int) (menuFace.getHeight() / 4);

        graphics2D.setFont(greetingsFont);
        graphics2D.drawString(GREETINGS, sX, sY);

        sX = (int) (menuFace.getWidth() - gameTitleRect.getWidth()) / 2;
        sY += (int) gameTitleRect.getHeight() * 1.1;//add 10% of String height between the two strings

        graphics2D.setFont(gameTitleFont);
        graphics2D.drawString(GAME_TITLE, sX, sY);

        sX = (int) (menuFace.getWidth() - creditsRect.getWidth()) / 2;
        sY += (int) creditsRect.getHeight() * 1.1;

        graphics2D.setFont(creditsFont);
        graphics2D.drawString(CREDITS, sX, sY);
    }

    @Override
    protected void drawButton() {
        FontRenderContext frc = graphics2D.getFontRenderContext();

        Rectangle2D txtRect = buttonFont.getStringBounds(START_TEXT, frc);
        Rectangle2D mTxtRect = buttonFont.getStringBounds(MENU_TEXT, frc);

        graphics2D.setFont(buttonFont);

        int x = (menuFace.width - startButton.width) / 2;
        int y = (int) ((menuFace.height - startButton.height) * 0.8);

        startButton.setLocation(x, y);

        x = (int) (startButton.getWidth() - txtRect.getWidth()) / 2;
        y = (int) (startButton.getHeight() - txtRect.getHeight()) / 2;

        x += startButton.x;
        y += startButton.y + (startButton.height * 0.9);


        if (startClicked) {
            Color tmp = graphics2D.getColor();
            graphics2D.setColor(CLICKED_BUTTON_COLOR);
            graphics2D.draw(startButton);
            graphics2D.setColor(CLICKED_TEXT);
            graphics2D.drawString(START_TEXT, x, y);
            graphics2D.setColor(tmp);
        } else {
            graphics2D.draw(startButton);
            graphics2D.drawString(START_TEXT, x, y);
        }

        x = startButton.x;
        y = startButton.y;

        y *= 1.2;

        menuButton.setLocation(x, y);

        x = (int) (menuButton.getWidth() - mTxtRect.getWidth()) / 2;
        y = (int) (menuButton.getHeight() - mTxtRect.getHeight()) / 2;

        x += menuButton.x;
        y += menuButton.y + (startButton.height * 0.9);

        if (menuClicked) {
            Color tmp = graphics2D.getColor();

            graphics2D.setColor(CLICKED_BUTTON_COLOR);
            graphics2D.draw(menuButton);
            graphics2D.setColor(CLICKED_TEXT);
            graphics2D.drawString(MENU_TEXT, x, y);
            graphics2D.setColor(tmp);
        } else {
            graphics2D.draw(menuButton);
            graphics2D.drawString(MENU_TEXT, x, y);
        }
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if (startButton.contains(p)) {
            owner.enableGameBoard();

        } else if (menuButton.contains(p)) {
            System.out.println("Goodbye " + System.getProperty("user.name"));
            System.exit(0);
        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if (startButton.contains(p)) {
            startClicked = true;
            repaint(startButton.x, startButton.y, startButton.width + 1, startButton.height + 1);

        } else if (menuButton.contains(p)) {
            menuClicked = true;
            repaint(menuButton.x, menuButton.y, menuButton.width + 1, menuButton.height + 1);
        }
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        if (startClicked) {
            startClicked = false;
            repaint(startButton.x, startButton.y, startButton.width + 1, startButton.height + 1);
        } else if (menuClicked) {
            menuClicked = false;
            repaint(menuButton.x, menuButton.y, menuButton.width + 1, menuButton.height + 1);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if (startButton.contains(p) || menuButton.contains(p))
            this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        else
            this.setCursor(Cursor.getDefaultCursor());
    }
}
