package board_games.gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;

import board_games.boardGames.BoardGameFactory;

/**
 * Game selection window
 * 
 * Allows to select between the board games provided by this program.
 * Selected game is written to the board game factory.
 */
public class GameSelection extends Composite {
	private Label lblNewLabel;
	private Button btnConnectFour;
	private Button btnTicTacToe;
	private Button btnReversi;
	private Button btnBack;
	private BoardGameFactory bg = BoardGameFactory.getInstance();

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * 		The parent composite
	 * @param style
	 * 		Style, can be SWT.NONE
	 */
	public GameSelection(Composite parent, int style) {
		super(parent, style);
		setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		
		lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setForeground(SWTResourceManager.getColor(255, 255, 255));
		lblNewLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblNewLabel.setFont(SWTResourceManager.getFont("Kristen ITC", 50, SWT.BOLD | SWT.ITALIC));
		lblNewLabel.setAlignment(SWT.CENTER);
		lblNewLabel.setBounds(150, 30, 700, 90);
		lblNewLabel.setText("Choose your Game");
		
		btnConnectFour = new Button(this, SWT.BORDER);
		btnConnectFour.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				System.out.println("Click!!");
				
				bg.setGame("Connect Four");
				
				Main.changeComposite(new ChangeCompositeRequest()
				{
					@Override
					public Composite change(Composite parent) {
						return new NewGameMenu(parent, SWT.NONE);
					}
				});
			}
		});
		btnConnectFour.setFont(SWTResourceManager.getFont("Kristen ITC", 18, SWT.BOLD));
		btnConnectFour.setBounds(350, 220, 320, 70);
		btnConnectFour.setText("Connect Four");
		
		btnTicTacToe = new Button(this, SWT.BORDER);
		btnTicTacToe.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				System.out.println("Click!!");
				
				bg.setGame("Tic Tac Toe");
				
				Main.changeComposite(new ChangeCompositeRequest() {
					@Override
					public Composite change(Composite parent) {
						return new NewGameMenu(parent, SWT.NONE);
					}
				});
			}
		});
		btnTicTacToe.setFont(SWTResourceManager.getFont("Kristen ITC", 18, SWT.BOLD));
		btnTicTacToe.setBounds(350, 329, 320, 70);
		btnTicTacToe.setText("Tic Tac Toe");
		
		btnReversi = new Button(this, SWT.BORDER);
		btnReversi.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				System.out.println("Click!!");
				
				bg.setGame("Reversi");
				
				Main.changeComposite(new ChangeCompositeRequest() {
					@Override
					public Composite change(Composite parent) {
						return new NewGameMenu(parent, SWT.NONE);
					}
				});
			}
		});
		btnReversi.setFont(SWTResourceManager.getFont("Kristen ITC", 18, SWT.BOLD));
		btnReversi.setBounds(350, 436, 320, 70);
		btnReversi.setText("Reversi");
		
		btnBack = new Button(this, SWT.NONE);
		btnBack.addSelectionListener(new SelectionAdapter() {
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
		btnBack.setText("< Back");
		btnBack.setForeground(SWTResourceManager.getColor(0, 51, 153));
		btnBack.setFont(SWTResourceManager.getFont("Kristen ITC", 12, SWT.BOLD));
		btnBack.setBounds(10, 10, 80, 40);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
