import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;


public class MainWindow {

	protected Shell shlGrader;
	private Text textMaxScore;
	private Text textActualScore;
	private Table table;
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MainWindow window = new MainWindow();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlGrader.open();
		shlGrader.layout();
		while (!shlGrader.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlGrader = new Shell();
		shlGrader.setSize(520, 316);
		shlGrader.setText("Notenrechner");
		
		Label lblMaxScore = new Label(shlGrader, SWT.NONE);
		lblMaxScore.setBounds(10, 10, 66, 16);
		lblMaxScore.setText("Max Punkte");
		
		Label lblActualScore = new Label(shlGrader, SWT.NONE);
		lblActualScore.setBounds(134, 10, 97, 16);
		lblActualScore.setText("Erreichte Punkte");
		
		textMaxScore = new Text(shlGrader, SWT.BORDER);
		textMaxScore.setText("100");
		textMaxScore.setBounds(10, 32, 74, 22);
		
		textActualScore = new Text(shlGrader, SWT.BORDER);
		textActualScore.setText("0");
		textActualScore.setBounds(133, 32, 74, 22);
		
		Label lblGrade = new Label(shlGrader, SWT.NONE);
		lblGrade.setBounds(270, 10, 29, 16);
		lblGrade.setText("Note");
		
		Label lblGradeDisplay = new Label(shlGrader, SWT.NONE);
		lblGradeDisplay.setBounds(270, 32, 57, 16);
		lblGradeDisplay.setText("6");
		
		table = new Table(shlGrader, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(10, 132, 480, 45);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmn1p = new TableColumn(table, SWT.CENTER);
		tblclmn1p.setWidth(30);
		tblclmn1p.setText("1+");
		
		TableColumn tblclmn1 = new TableColumn(table, SWT.CENTER);
		tblclmn1.setWidth(30);
		tblclmn1.setText("1");
		
		TableColumn tblclmn1m = new TableColumn(table, SWT.CENTER);
		tblclmn1m.setWidth(30);
		tblclmn1m.setText("1-");
		
		TableColumn tblclmn2p = new TableColumn(table, SWT.CENTER);
		tblclmn2p.setWidth(30);
		tblclmn2p.setText("2+");
		
		TableColumn tblclmn2 = new TableColumn(table, SWT.CENTER);
		tblclmn2.setWidth(30);
		tblclmn2.setText("2");
		
		TableColumn tblclmn2m = new TableColumn(table, SWT.CENTER);
		tblclmn2m.setWidth(30);
		tblclmn2m.setText("2-");
		
		TableColumn tblclmn3p = new TableColumn(table, SWT.CENTER);
		tblclmn3p.setWidth(30);
		tblclmn3p.setText("3+");
		
		TableColumn tblclmn3 = new TableColumn(table, SWT.CENTER);
		tblclmn3.setWidth(30);
		tblclmn3.setText("3");
		
		TableColumn tblclmn3m = new TableColumn(table, SWT.CENTER);
		tblclmn3m.setWidth(30);
		tblclmn3m.setText("3-");
		
		TableColumn tblclmn4p = new TableColumn(table, SWT.CENTER);
		tblclmn4p.setWidth(30);
		tblclmn4p.setText("4+");
		
		TableColumn tblclmn4 = new TableColumn(table, SWT.CENTER);
		tblclmn4.setWidth(30);
		tblclmn4.setText("4");
		
		TableColumn tblclmn4m = new TableColumn(table, SWT.CENTER);
		tblclmn4m.setWidth(30);
		tblclmn4m.setText("4-");
		
		TableColumn tblclmn5p = new TableColumn(table, SWT.CENTER);
		tblclmn5p.setWidth(30);
		tblclmn5p.setText("5+");
		
		TableColumn tblclmn5 = new TableColumn(table, SWT.CENTER);
		tblclmn5.setWidth(30);
		tblclmn5.setText("5");
		
		TableColumn tblclmn5m = new TableColumn(table, SWT.CENTER);
		tblclmn5m.setWidth(30);
		tblclmn5m.setText("5-");
		
		TableColumn tblclmn6 = new TableColumn(table, SWT.CENTER);
		tblclmn6.setWidth(30);
		tblclmn6.setText("6");

	}
}
