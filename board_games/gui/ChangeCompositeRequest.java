package board_games.gui;

import org.eclipse.swt.widgets.Composite;

/**
 * A request to change the composite that's present in the shell
 */
public abstract class ChangeCompositeRequest {
	public abstract Composite change(Composite shell); 
}
