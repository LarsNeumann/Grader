import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import org.eclipse.wb.swt.SWTResourceManager;


public class MainWindow {

	protected Shell shlGrader;
	private Text textMaxScore;
	private Text textActualScore;
	private Label lblGradeDisplay;
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
		shlGrader.setSize(688, 180);
		shlGrader.setText("Notenrechner");

		Label lblActualScore = new Label(shlGrader, SWT.NONE);
		lblActualScore.setBounds(101, 10, 97, 16);
		lblActualScore.setText("Erreichte Punkte");

		textActualScore = new Text(shlGrader, SWT.BORDER | SWT.CENTER);
		textActualScore.setText("0");
		textActualScore.setBounds(100, 32, 98, 22);
		textActualScore.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				// TODO Auto-generated method stub
				updateGrade();
			}
		});
		
		Label lblGrade = new Label(shlGrader, SWT.NONE);
		lblGrade.setBounds(221, 10, 29, 16);
		lblGrade.setText("Note");

		lblGradeDisplay = new Label(shlGrader, SWT.CENTER);
		lblGradeDisplay.setFont(SWTResourceManager.getFont("Ubuntu", 10, SWT.BOLD));
		lblGradeDisplay.setBounds(221, 34, 29, 16);
		lblGradeDisplay.setText("6");

		final Table table = new Table(shlGrader, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(10, 82, 654, 50);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn[] columns = new TableColumn[13];

		columns[0] = new TableColumn(table, SWT.CENTER);
		columns[0].setResizable(false);
		columns[0].setWidth(50);
		columns[0].setText("1+");

		columns[1] = new TableColumn(table, SWT.CENTER);
		columns[1].setResizable(false);
		columns[1].setWidth(50);
		columns[1].setText("1");

		columns[2] = new TableColumn(table, SWT.CENTER);
		columns[2].setResizable(false);
		columns[2].setWidth(50);
		columns[2].setText("1-");

		columns[3] = new TableColumn(table, SWT.CENTER);
		columns[3].setResizable(false);
		columns[3].setWidth(50);
		columns[3].setText("2+");

		columns[4] = new TableColumn(table, SWT.CENTER);
		columns[4].setResizable(false);
		columns[4].setWidth(50);
		columns[4].setText("2");

		columns[5] = new TableColumn(table, SWT.CENTER);
		columns[5].setResizable(false);
		columns[5].setWidth(50);
		columns[5].setText("2-");

		columns[6] = new TableColumn(table, SWT.CENTER);
		columns[6].setResizable(false);
		columns[6].setWidth(50);
		columns[6].setText("3+");

		columns[7] = new TableColumn(table, SWT.CENTER);
		columns[7].setResizable(false);
		columns[7].setWidth(50);
		columns[7].setText("3");

		columns[8] = new TableColumn(table, SWT.CENTER);
		columns[8].setResizable(false);
		columns[8].setWidth(50);
		columns[8].setText("3-");

		columns[9] = new TableColumn(table, SWT.CENTER);
		columns[9].setResizable(false);
		columns[9].setWidth(50);
		columns[9].setText("4+");

		columns[10] = new TableColumn(table, SWT.CENTER);
		columns[10].setResizable(false);
		columns[10].setWidth(50);
		columns[10].setText("4");

		columns[11] = new TableColumn(table, SWT.CENTER);
		columns[11].setResizable(false);
		columns[11].setWidth(50);
		columns[11].setText("4-");

		columns[12] = new TableColumn(table, SWT.CENTER);
		columns[12].setResizable(false);
		columns[12].setWidth(50);
		columns[12].setText("5");

		Label lblMaxScore = new Label(shlGrader, SWT.NONE);
		lblMaxScore.setBounds(10, 10, 66, 16);
		lblMaxScore.setText("Max Punkte");
		
		textMaxScore = new Text(shlGrader, SWT.BORDER | SWT.CENTER);
		textMaxScore.setText("100");
		updateTable(table);
		textMaxScore.setBounds(10, 32, 66, 22);
		textMaxScore.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				// TODO Auto-generated method stub
				updateTable(table);
			}
		});

		Label lblPointsForGrades = new Label(shlGrader, SWT.NONE);
		lblPointsForGrades.setBounds(10, 60, 76, 16);
		lblPointsForGrades.setText("Notenspiegel");

	}


	protected void updateGrade() {
		if(!textActualScore.getText().isEmpty() && !textActualScore.getText().isEmpty()) {
			double dActualScore = Double.parseDouble(textActualScore.getText());
			double dMaxScore = Double.parseDouble(textMaxScore.getText());
			if(dMaxScore != 0) {
				double dGrade = dActualScore / dMaxScore;
				lblGradeDisplay.setText(String.valueOf(dGrade));
			}
		}
	}
	
	private void updateTable(Table table) {
		table.setRedraw(false);
		double dMaxScore = Double.parseDouble(textMaxScore.getText());
		// Min Percent       1+    1      1-   2+   2     2-   3+    3    3-    4+    4     4-    5
		double[] minPerc = {0.99, 0.97, 0.95, 0.9, 0.85, 0.8, 0.75, 0.7, 0.65, 0.59, 0.51, 0.45, 0.25};
//		Irgendwas in Tabelle schreiben
		TableItem item = new TableItem(table, SWT.CENTER);
		for(int nCounter = 0; nCounter < 13; nCounter ++) {
			double dMinPoints = dMaxScore * minPerc[nCounter];
			item.setText(nCounter, String.valueOf(dMinPoints));
		}
		
		table.setRedraw(true);
	}
	
}
