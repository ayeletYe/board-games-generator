package board_games.gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;

import board_games.boardGames.BoardGameFactory;

/**
 * Selection of the parameters of a previously selected game.
 * 
 * User needs to select for the 2 players of the game:
 * - Player type: Human or Computer
 * - For computer player - select Easy of Difficult difficulty
 * - Select which player start to play
 * 
 * All these selections are set to the board game factory singleton.
 */
public class NewGameMenu extends Composite {
	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * 		The parent composite
	 * @param style
	 * 		Style, can be SWT.NONE
	 */
	public NewGameMenu(Composite parent, int style) {
		super(parent, style);
		setTouchEnabled(true);
		setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		
		Label lblGameMenu = new Label(this, SWT.NONE);
		lblGameMenu.setForeground(SWTResourceManager.getColor(255, 255, 255));
		lblGameMenu.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblGameMenu.setAlignment(SWT.CENTER);
		lblGameMenu.setFont(SWTResourceManager.getFont("Kristen ITC", 50, SWT.BOLD | SWT.ITALIC));
		lblGameMenu.setBounds(250, 30, 500, 90);
		lblGameMenu.setText("Game Menu");
		
		Label lblPlayersProperties = new Label(this, SWT.NONE);
		lblPlayersProperties.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblPlayersProperties.setFont(SWTResourceManager.getFont("Kristen ITC", 16, SWT.BOLD | SWT.ITALIC));
		lblPlayersProperties.setAlignment(SWT.CENTER);
		lblPlayersProperties.setBounds(10, 157, 349, 50);
		lblPlayersProperties.setText("Choose Players Properties :");
		
		Label lblWhoStarts = new Label(this, SWT.NONE);
		lblWhoStarts.setForeground(SWTResourceManager.getColor(0, 0, 0));
		lblWhoStarts.setFont(SWTResourceManager.getFont("Kristen ITC", 16, SWT.BOLD | SWT.ITALIC));
		lblWhoStarts.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblWhoStarts.setAlignment(SWT.CENTER);
		lblWhoStarts.setText("Choose Starting Player :");
		lblWhoStarts.setBounds(20, 409, 339, 37);
		
		Label lblPlayer1 = new Label(this, SWT.NONE);
		lblPlayer1.setForeground(SWTResourceManager.getColor(255, 255, 255));
		lblPlayer1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblPlayer1.setAlignment(SWT.CENTER);
		lblPlayer1.setFont(SWTResourceManager.getFont("Kristen ITC", 14, SWT.NORMAL));
		lblPlayer1.setBounds(291, 213, 107, 37);
		lblPlayer1.setText("Player 1 :");
		
		Label lblPlayer2 = new Label(this, SWT.NONE);
		lblPlayer2.setForeground(SWTResourceManager.getColor(255, 255, 204));
		lblPlayer2.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblPlayer2.setFont(SWTResourceManager.getFont("Kristen ITC", 14, SWT.NORMAL));
		lblPlayer2.setAlignment(SWT.CENTER);
		lblPlayer2.setText("Player 2 :");
		lblPlayer2.setBounds(291, 304, 107, 37);
		
		Button btnStart = new Button(this, SWT.NONE);
		btnStart.setEnabled(false);
		btnStart.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				System.out.println("Click!!");
				
				Main.changeComposite(new ChangeCompositeRequest() {
					@Override
					public Composite change(Composite parent) {
						return new Game(parent, SWT.NONE, BoardGameFactory.getInstance().createBoardGame());
					}
				});
			}
		});
		btnStart.setForeground(SWTResourceManager.getColor(0, 0, 0));
		btnStart.setText("Start");
		btnStart.setFont(SWTResourceManager.getFont("Kristen ITC", 14, SWT.NORMAL));
		btnStart.setBounds(250, 540, 500, 50);
		
		
		Combo comboWhoStarts = new Combo(this, SWT.NONE);
		comboWhoStarts.setEnabled(false);
		comboWhoStarts.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				btnStart.setEnabled(true);
				Combo combo = (Combo)(e.getSource());
				String type = combo.getText();
				if(type.equals("Player 1"))
				{
					BoardGameFactory.getInstance().setWhoStart("Player 1");
				}
				if(type.equals("Player 2"))
				{
					BoardGameFactory.getInstance().setWhoStart("Player 2");
				}
			}
		});
		comboWhoStarts.setItems(new String[] {"Player 1", "Player 2"});
		comboWhoStarts.setFont(SWTResourceManager.getFont("Kristen ITC", 12, SWT.NORMAL));
		comboWhoStarts.setBackground(SWTResourceManager.getColor(255, 255, 255));
		comboWhoStarts.setBounds(373, 411, 210, 31);
		comboWhoStarts.setText("Start Player");
		
		
		Combo comboPlayer2Diff = new Combo(this, SWT.NONE);
		comboPlayer2Diff.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				comboWhoStarts.setEnabled(true);
				Combo combo = (Combo)(e.getSource());
				String type = combo.getText();
				if(type.equals("Easy"))
				{
					BoardGameFactory.getInstance().setPlayerTwoDifficulty("Easy");
				}
				if(type.equals("Difficult"))
				{
					BoardGameFactory.getInstance().setPlayerTwoDifficulty("Difficult");
				}
			}
		});
		comboPlayer2Diff.setEnabled(false);
		comboPlayer2Diff.setItems(new String[] {"Easy", "Difficult"});
		comboPlayer2Diff.setTouchEnabled(true);
		comboPlayer2Diff.setFont(SWTResourceManager.getFont("Kristen ITC", 12, SWT.NORMAL));
		comboPlayer2Diff.setBackground(SWTResourceManager.getColor(255, 255, 255));
		comboPlayer2Diff.setBounds(641, 310, 151, 31);
		comboPlayer2Diff.setText("difficulty");
		
		
		Combo comboPlayer2Type = new Combo(this, SWT.NONE);
		comboPlayer2Type.setEnabled(false);
		comboPlayer2Type.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				Combo combo = (Combo)(e.getSource());
				String type = combo.getText();
				if(type.equals("Computer"))
				{
					comboWhoStarts.setEnabled(false);
					comboPlayer2Diff.setEnabled(true);
					BoardGameFactory.getInstance().setPlayerTwoType("Computer");
				}
				if(type.equals("Human"))
				{
					comboWhoStarts.setEnabled(true);
					comboPlayer2Diff.setEnabled(false);
					BoardGameFactory.getInstance().setPlayerTwoType("Human");
				}
			}
		});
		comboPlayer2Type.setItems(new String[] {"Computer", "Human"});
		comboPlayer2Type.setTouchEnabled(true);
		comboPlayer2Type.setToolTipText("");
		comboPlayer2Type.setFont(SWTResourceManager.getFont("Kristen ITC", 12, SWT.NORMAL));
		comboPlayer2Type.setBackground(SWTResourceManager.getColor(255, 255, 255));
		comboPlayer2Type.setBounds(442, 310, 141, 31);
		comboPlayer2Type.setText(" Type");
		
		
		Combo comboPlayer1Diff = new Combo(this, SWT.NONE);
		comboPlayer1Diff.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				comboPlayer2Type.setEnabled(true);
				Combo combo = (Combo)(e.getSource());
				String type = combo.getText();
				if(type.equals("Easy"))
				{
					BoardGameFactory.getInstance().setPlayerOneDifficulty("Easy");
				}
				if(type.equals("Difficult"))
				{
					BoardGameFactory.getInstance().setPlayerOneDifficulty("Difficult");
				}
			}
		});
		comboPlayer1Diff.setTouchEnabled(true);
		comboPlayer1Diff.setEnabled(false);
		comboPlayer1Diff.setToolTipText("");
		comboPlayer1Diff.setItems(new String[] {"Easy", "Difficult"});
		comboPlayer1Diff.setFont(SWTResourceManager.getFont("Kristen ITC", 12, SWT.NORMAL));
		comboPlayer1Diff.setBackground(SWTResourceManager.getColor(255, 255, 255));
		comboPlayer1Diff.setBounds(641, 219, 151, 31);
		comboPlayer1Diff.setText("difficulty");
		//m_bindingContext = initDataBindings();
		
		
		Combo comboPlayer1Type = new Combo(this, SWT.NONE);
		comboPlayer1Type.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				Combo combo = (Combo)(e.getSource());
				String type = combo.getText();
				if(type.equals("Computer"))
				{
					comboPlayer2Type.setEnabled(false);
					comboPlayer1Diff.setEnabled(true);
					BoardGameFactory.getInstance().setPlayerOneType("Computer");
				}
				if(type.equals("Human"))
				{
					comboPlayer2Type.setEnabled(true);
					comboPlayer1Diff.setEnabled(false);
					BoardGameFactory.getInstance().setPlayerOneType("Human");
				}
			}
		});
		comboPlayer1Type.setItems(new String[] {"Computer", "Human"});
		comboPlayer1Type.setForeground(SWTResourceManager.getColor(0, 0, 0));
		comboPlayer1Type.setBackground(SWTResourceManager.getColor(255, 255, 255));
		comboPlayer1Type.setTouchEnabled(true);
		comboPlayer1Type.setFont(SWTResourceManager.getFont("Kristen ITC", 12, SWT.NORMAL));
		comboPlayer1Type.setToolTipText("");
		comboPlayer1Type.setBounds(442, 219, 141, 31);
		comboPlayer1Type.setText("Type");
		
		Button btnBack = new Button(this, SWT.NONE);
		btnBack.addSelectionListener(new SelectionAdapter() {
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
		btnBack.setForeground(SWTResourceManager.getColor(0, 51, 153));
		btnBack.setText("< Back");
		btnBack.setFont(SWTResourceManager.getFont("Kristen ITC", 12, SWT.BOLD));
		btnBack.setBounds(10, 10, 80, 40);
		
	}
}
