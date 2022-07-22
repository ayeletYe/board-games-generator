package board_games.gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

/**
 * The main class of the board game application
 */
public class Main {
	private static Shell shell;
	private static Composite currentComposite;
	
	/**
	 * Launch the application.
	 * @param args
	 * 		No arguments are needed by this application
	 */
	public static void main(String[] args) {
		Display display = Display.getDefault();
		shell = new Shell(SWT.TITLE | SWT.CLOSE);
		shell.setSize(1000, 650);
		shell.setText("SWT Application");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		shell.setBackgroundImage(SWTResourceManager.getImage("images/bg3.jpg"));

		currentComposite = new MainWindow(shell, SWT.NONE);
		
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Allows to change the displayed content (composite) of the shell
	 * that we opened.
	 * 
	 * @param req
	 * 		The change composite request
	 */
	public static void changeComposite(ChangeCompositeRequest req) {
		currentComposite.dispose(); //close the composite
		
		currentComposite = req.change(shell);
		shell.layout();
	}

}
