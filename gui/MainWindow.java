package board_games.gui;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Button;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;

import board_games.boardGames.BoardGames;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

/**
 * The main window of the game
 *
 * This is the starting screen of the application,
 * that allows starting a new game, loading a saved game or exiting the application.
 */
public class MainWindow extends Composite {

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * 		The parent composite
	 * @param style
	 * 		Style, can be SWT.NONE
	 */
	public MainWindow(Composite parent, int style) {
		super(parent, style);
		setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		
		Label lblBoardGames = new Label(this, SWT.NONE);
		lblBoardGames.setForeground(SWTResourceManager.getColor(255, 255, 255));
		lblBoardGames.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblBoardGames.setAlignment(SWT.CENTER);
		lblBoardGames.setFont(SWTResourceManager.getFont("Kristen ITC", 50, SWT.BOLD | SWT.ITALIC));
		lblBoardGames.setBounds(250, 30, 500, 90);
		lblBoardGames.setText("Board Games");
		
		Button btnNewGame = new Button(this, SWT.BORDER);
		btnNewGame.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		btnNewGame.setFont(SWTResourceManager.getFont("Kristen ITC", 18, SWT.BOLD));
		btnNewGame.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				System.out.println("Click!!");
				
				Main.changeComposite(new ChangeCompositeRequest() {
					@Override
					public Composite change(Composite parent) {
						return new GameSelection(parent, SWT.NONE);
					}
				});
			}
		});
		btnNewGame.setBounds(350, 225, 300, 70);
		btnNewGame.setText("New Game");
		
		Button btnLoadGame = new Button(this, SWT.BORDER);
		btnLoadGame.setFont(SWTResourceManager.getFont("Kristen ITC", 18, SWT.BOLD));
		btnLoadGame.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				System.out.println("Click!!");
				
				BoardGames boardGame;
				
                /**
                 * Open file selection
                 */
                FileDialog fileDialog = new FileDialog(getShell(), SWT.OPEN);
               
                fileDialog.setText("Load saved game");
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
                 * Load a "Serializable" class from a file.
                 */
                FileInputStream fin = null;
                ObjectInputStream ois = null;
 
                try {
                    fin = new FileInputStream(selectedFile);
                    ois = new ObjectInputStream(fin);
                    Object board = ois.readObject(); /* this is the GameBoard class */
                    boardGame = (BoardGames)board;
 
                } catch (Exception ex) {
 
                    ex.printStackTrace();
                    return;
                } finally {
 
                    if (fin != null) {
                        try {
                            fin.close();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
 
                    if (ois != null) {
                        try {
                            ois.close();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
 
                }
				
				Main.changeComposite(new ChangeCompositeRequest() {
					@Override
					public Composite change(Composite parent) {
						return new Game(parent, SWT.NONE, boardGame);
					}
				});
			}
		});
		btnLoadGame.setBounds(350, 351, 300, 70);
		btnLoadGame.setText("Load Game");
		
		Button btnQuit = new Button(this, SWT.BORDER);
		btnQuit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				System.out.println("Click!!");
				
				getShell().dispose();
			}
		});
		btnQuit.setFont(SWTResourceManager.getFont("Kristen ITC", 18, SWT.BOLD));
		btnQuit.setBounds(350, 474, 300, 65);
		btnQuit.setText("Quit");

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	
}
