package board_games.gui;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;

import board_games.Board;
import board_games.Board.Square;
import board_games.Cell;
import board_games.Player;
import board_games.Player.Type;
import board_games.boardGames.BoardGames;
import board_games.logic.GameLogic;
import swing2swt.layout.BorderLayout;
 
/**
 * The actual game window
 * 
 * Draws the board and allows the players to play.
 */
public class Game extends Composite {
	private static Cell nextStep;
	private int rows;
	private int columns;
	private Composite menuComposite;
	private Canvas boardCanvas;
	private BoardGames game;
	private boolean isEndded = false;
	private Label lblTurn;
	private Button btnContinue;
	private GameLogic.Status gameStatus;
	
	/**
	 * Constructs the game window according to a given board game
	 * 
	 * @param parent
	 * 		Parent composite
	 * @param style
	 * 		Style, can be SWT.NONE
	 * @param boardGames
	 * 		The game board we want to play on
	 */
	public Game(Composite parent, int style, BoardGames boardGames) {
		super(parent, style);
		setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		
		game = boardGames;
		
		this.rows = game.getGameBoard().getRows();
        this.columns = game.getGameBoard().getColumns();
        setLayout(new BorderLayout(0, 0));
       
        menuComposite = new Composite(this, SWT.BORDER);
        menuComposite.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
        menuComposite.setLayoutData(BorderLayout.NORTH);
        menuComposite.setLayout(null);
        
        Button btnMainMenu = new Button(menuComposite, SWT.BORDER);
        btnMainMenu.setForeground(SWTResourceManager.getColor(0, 51, 153));
        btnMainMenu.addSelectionListener(new SelectionAdapter() {
        	@Override
        	public void widgetSelected(SelectionEvent e)
        	{
        		System.out.println("Click!!");
				
				Main.changeComposite(new ChangeCompositeRequest() {
					@Override
					public Composite change(Composite parent) {
						return new MainWindow(parent, SWT.NONE);
					}
				});
        	}
        });
        btnMainMenu.setBounds(10, 10, 80, 40);
        btnMainMenu.setFont(SWTResourceManager.getFont("Kristen ITC", 12, SWT.BOLD));
        btnMainMenu.setText("< Main");
        
        Label lblPerform = new Label(menuComposite, SWT.NONE);
        lblPerform.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
        lblPerform.setBounds(463, 27, 341, 26);
        lblPerform.setFont(SWTResourceManager.getFont("Kristen ITC", 11, SWT.BOLD));
        lblPerform.setText("Perform your turn and click Continue\r\n");
        
        
        lblTurn = new Label(menuComposite, SWT.NONE);
        lblTurn.setBounds(227, 16, 196, 33);
        lblTurn.setForeground(SWTResourceManager.getColor(255, 255, 153));
        lblTurn.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.BOLD));
        lblTurn.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
        lblTurn.setText("Turn :   Player 1");
        
        Label lblClickNext = new Label(menuComposite, SWT.NONE);
        lblClickNext.setBounds(657, 20, 132, 26);
        lblClickNext.setText("Click Next :\r\n");
        lblClickNext.setFont(SWTResourceManager.getFont("Kristen ITC", 11, SWT.BOLD));
        lblClickNext.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
        
        Button btnNext = new Button(menuComposite, SWT.NONE);
        btnNext.addSelectionListener(new SelectionAdapter() {
        	@Override
        	public void widgetSelected(SelectionEvent e) 
        	{
        		turn();
        	}
        });
        btnNext.setBounds(795, 7, 176, 46);
        btnNext.setText("Next");
        btnNext.setFont(SWTResourceManager.getFont("Kristen ITC", 12, SWT.BOLD));
        
        btnContinue = new Button(menuComposite, SWT.NONE);
        btnContinue.setBounds(810, 7, 176, 46);
        btnContinue.addSelectionListener(new SelectionAdapter() {
        	@Override
        	public void widgetSelected(SelectionEvent e)
        	{
				if (game.getCurrentPlayer().getType() == Type.Human)
				{
					return;
				}
				
        		turn();
        	}
        });
        btnContinue.setFont(SWTResourceManager.getFont("Kristen ITC", 12, SWT.BOLD));
        btnContinue.setText("Continue");
        
        Button btnSave = new Button(menuComposite, SWT.BORDER);
        btnSave.addSelectionListener(new SelectionAdapter() {
        	@Override
        	public void widgetSelected(SelectionEvent e) {
                /**
                 * Open file selection
                 */
                FileDialog fileDialog = new FileDialog(getShell(), SWT.SAVE);
               
                fileDialog.setText("Save current game");
                fileDialog.setFilterExtensions(new String[]{"*.game"});
               
                /**
                 * Opens dialog and waits for user selection.
                 * Returns path to file (including file name)
                 */
                String selectedFile = fileDialog.open();
                System.out.println("Selected file is " + selectedFile);
                if (selectedFile == null)
                	return;
               
                /**
                 * Save a "Serializable" class to a file from:
                 * https://www.mkyong.com/java/how-to-write-an-object-to-file-in-java/
                 */
                FileOutputStream fout = null;
                ObjectOutputStream oos = null;
 
                try {
 
                    fout = new FileOutputStream(selectedFile);
                    oos = new ObjectOutputStream(fout);
                    oos.writeObject(game); /* here we provide GameBoard class */
                } catch (Exception ex) {
 
                    ex.printStackTrace();
 
                } finally {
 
                    if (fout != null) {
                        try {
                            fout.close();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
 
                    if (oos != null) {
                        try {
                            oos.close();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
 
                }
        	}
        });
        btnSave.setText("Save");
        btnSave.setForeground(SWTResourceManager.getColor(0, 51, 153));
        btnSave.setFont(SWTResourceManager.getFont("Kristen ITC", 12, SWT.BOLD));
        btnSave.setBounds(96, 10, 80, 40);
        
        
        if(game.getPlayer(0).getType() == game.getPlayer(1).getType())
        {
        	lblPerform.setVisible(false);
        	btnContinue.setVisible(false);
        }
        
        
        if(game.getPlayer(0).getType() == Type.Human ||
        		game.getPlayer(1).getType() == Type.Human)
    	{
    		lblClickNext.setVisible(false);
    		btnNext.setVisible(false);
    	}
        
 		
        boardCanvas = new Canvas(this, SWT.NONE);
        boardCanvas.setFont(SWTResourceManager.getFont("Kristen ITC", 12, SWT.NORMAL));
        boardCanvas.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
        boardCanvas.setLayoutData(BorderLayout.CENTER);
       
		boardCanvas.addPaintListener(new PaintListener() {

			private boolean flickerState;

			public void paintControl(PaintEvent e) {
				
				if (boardCanvas.isDisposed())
					   return;
				
				Canvas canvas = (Canvas) e.widget;
				Point boardSize = canvas.getSize();
				int maxX = boardSize.x;
				int maxY = boardSize.y;
				int cellWidth = maxX / columns;
				int cellHeight = maxY / rows;

				e.gc.setForeground(e.display.getSystemColor(SWT.COLOR_BLUE));
				e.gc.setLineWidth(10);

				/* Draw rows */
				for (int i = 1; i < rows; i++)
					e.gc.drawLine(0, cellHeight * i, maxX, cellHeight * i);

				/* Draw columns */
				for (int i = 1; i < columns; i++)
					e.gc.drawLine(cellWidth * i, 0, cellWidth * i, maxY);

				///////////////////// Add images of cells /////////////
				Image imageOne = SWTResourceManager.getImage(game.getPlayerIcon(Square.OnePlayer));
				imageOne = new Image(getDisplay(), imageOne.getImageData().scaledTo(cellWidth - 20, cellHeight - 20));

				Image imageSecond = SWTResourceManager.getImage(game.getPlayerIcon(Square.SecondPlayer));
				imageSecond = new Image(getDisplay(),
						imageSecond.getImageData().scaledTo(cellWidth - 20, cellHeight - 20));

				for (Board.CellValue cell : game.getGameBoard()) {
					Image imageToDraw = null;

					if (cell.getCellData() == Square.OnePlayer) {
						imageToDraw = imageOne;
					} else if (cell.getCellData() == Square.SecondPlayer)
						imageToDraw = imageSecond;

					if (imageToDraw != null) {
						e.gc.drawImage(imageToDraw, cell.getCellColumn() * cellWidth + 10,
								cell.getCellRow() * cellHeight + 10);
					}
				}

				
				/**
				 * Winner flicker
				 */
				if (isEndded) {
					int boxColor;
					int textColor;
					String winnerText;

					if (flickerState) {
						boxColor = SWT.COLOR_YELLOW;
						textColor = SWT.COLOR_DARK_RED;
					} else {
						boxColor = SWT.COLOR_DARK_RED;
						textColor = SWT.COLOR_YELLOW;
					}
					flickerState = !flickerState;

					e.gc.setForeground(e.display.getSystemColor(boxColor));
					e.gc.setBackground(e.display.getSystemColor(boxColor));
					e.gc.fillRectangle(maxX / 4, maxY / 4, maxX / 2, maxY / 2);

					e.gc.setForeground(e.display.getSystemColor(textColor));
					e.gc.drawText("Game Ended!", maxX / 3, maxY / 3);

					if (gameStatus.getWinner() == Square.OnePlayer)
						winnerText = "The winner is Player 1!";
					else if (gameStatus.getWinner() == Square.SecondPlayer)
						winnerText = "The winner is Player 1!";
					else
						winnerText = "Tie! No winner!";
					
					e.gc.drawText(winnerText, maxX / 3, maxY / 3 + 50);

					/* Redraw with other colors in 500ms */
					e.display.timerExec(500, new Runnable() {
						@Override
						public void run() {
							if (!canvas.isDisposed())
								canvas.redraw();
						}
					});
				}
			}
		});
        
		
		boardCanvas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				Point boardSize = boardCanvas.getSize();
				int cellRow = e.y / (boardSize.y / rows);
				int cellCol = e.x / (boardSize.x / columns);

				if (game.getCurrentPlayer().getType() == Type.Computer)
				{
					return;
				}
				
				nextStep = new Cell(cellRow, cellCol);

				turn();
			}
		});

        updateMenu();
	}
	
	/**
	 * Play the turn (computer or human)
	 */
	private void turn()
	{
		if (isEndded)
			return;
		
		if (game.performsGameStep() == false)
			return;

		gameStatus = game.getStatus();
		
		if (gameStatus.isEnded())
			isEndded = true;
		
		boardCanvas.redraw();
		updateMenu();
	}
	
	/**
	 * Update the top menu with:
	 * - Who's turn right now
	 * - Enable/Disable the "Continue" button when
	 *   computer and human are playing.
	 */
	private void updateMenu()
	{
		Player player1 = game.getPlayer(0);
		Player player2 = game.getPlayer(1);
		Player currentPlayer = game.getCurrentPlayer();
		String playerText = "";
		
		if (player1.getType() == Type.Computer && player2.getType() == Type.Human)
		{
			if (currentPlayer == player1) {
				playerText = "Computer";
				btnContinue.setEnabled(true);
			} else {
				playerText = "Human";
				btnContinue.setEnabled(false);
			}
		} 
		else if (player1.getType() == Type.Human && player2.getType() == Type.Computer)
		{
			if (currentPlayer == player1) {
				playerText = "Human";
				btnContinue.setEnabled(false);
			} else {
				playerText = "Computer";
				btnContinue.setEnabled(true);
			}
		}
		else if (player1.getType() == Type.Human && player2.getType() == Type.Human)
		{
			if (currentPlayer == player1)
				playerText = "Player 1";
			else
				playerText = "Player 2";
		}
		else if (player1.getType() == Type.Computer && player2.getType() == Type.Computer)
		{
			if (currentPlayer == player1)
				playerText = "Computer 1";
			else
				playerText = "Computer 2";
		}
		
		lblTurn.setText("Turn :   " + playerText);
	}
	
	/**
	 * Get the next step as selected by human player in GUI.
	 * 
	 * Can be useful by any class that wants to know the last cell selection in GUI.
	 * 
	 * @return
	 * 		Cell selected by user.
	 */
	public static Cell getNextStep()
	{
		return nextStep;
	}

}
