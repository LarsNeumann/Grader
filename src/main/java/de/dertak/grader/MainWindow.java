package de.dertak.grader;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.wb.swt.SWTResourceManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainWindow {
	private final Logger logger = LoggerFactory.getLogger(MainWindow.class);
	protected Shell shlGrader;
	private Spinner spnMaxScore;
	private Spinner spnActualScore;
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
			Logger logger = LoggerFactory.getLogger(MainWindow.class);
			logger.error("Problem at main method '{}'.", e.getMessage(), e.getLocalizedMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		logger.debug("Open() calls '{}'.", this);
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
		shlGrader.setSize(250, 450);
		shlGrader.setText("Notenrechner");

		Label lblPointsForGrades = new Label(shlGrader, SWT.NONE);
		lblPointsForGrades.setBounds(10, 10, 80, 16);
		lblPointsForGrades.setText("Notenspiegel");

		final Table table = new Table(shlGrader, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(10, 32, 110, 360);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn[] columns = new TableColumn[2];
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.CENTER);
		tableColumn_1.setWidth(40);
		columns[0] = tableColumn_1;
		columns[0].setResizable(false);
		columns[0].setWidth(45);
		columns[0].setText("Note");
		
		TableColumn tableColumn = new TableColumn(table, SWT.CENTER);
		tableColumn.setWidth(60);
		columns[1] = tableColumn;
		columns[1].setResizable(false);
		columns[1].setWidth(60);
		columns[1].setText("Punkte");

		Label lblMaxScore = new Label(shlGrader, SWT.CENTER);
		lblMaxScore.setBounds(140, 32, 80, 16);
		lblMaxScore.setText("maximal");

		spnMaxScore = new Spinner(shlGrader, SWT.BORDER);
		spnMaxScore.setBounds(140, 54, 80, 24);
		spnMaxScore.setMaximum(10000);
		spnMaxScore.setSelection(100);
		spnMaxScore.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				updateTable(table);
			}
		});

		Label lblActualScore = new Label(shlGrader, SWT.CENTER);
		lblActualScore.setBounds(140, 84, 80, 16);
		lblActualScore.setText("erreicht");

		spnActualScore = new Spinner(shlGrader, SWT.BORDER);
		spnActualScore.setBounds(140, 106, 80, 24);
		spnActualScore.setMaximum(100000);
		spnActualScore.setDigits(1);
		spnActualScore.setIncrement(5);
		spnActualScore.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				updateGrade(table);
			}
		});
		
		Label lblGrade = new Label(shlGrader, SWT.CENTER);
		lblGrade.setBounds(140, 136, 80, 16);
		lblGrade.setText("Note");

		lblGradeDisplay = new Label(shlGrader, SWT.CENTER);
		lblGradeDisplay.setBounds(140, 158, 80, 16);
		lblGradeDisplay.setFont(SWTResourceManager.getFont("Ubuntu", 9, SWT.BOLD));
		lblGradeDisplay.setText("6");

		updateTable(table);
		updateGrade(table);
		
		Label lblPoints = new Label(shlGrader, SWT.CENTER);
		lblPoints.setBounds(140, 10, 80, 16);
		lblPoints.setText("Punkte");
		
		
	}


	protected void updateGrade(Table table) {
		double dActualScore;
		try{
			dActualScore = spnActualScore.getSelection()/Math.pow(10, spnActualScore.getDigits());
		} catch (SWTException e) {
			logger.error("Problem at updateGrade '{}' solved by return.", e.getMessage(), spnActualScore);
			return;
		} 
		int nIx = 0;
		double dMinScore;
		do{
			try {
				String itemText = table.getItem(nIx).getText(1);
				itemText = itemText.replace(',', '.');
				dMinScore = Double.parseDouble(itemText);
			} catch (NumberFormatException e) {
				
				logger.error("Problem at updateGrade '{}' solved by return.", e.getMessage(), table);
				return;
			}
			nIx++;
		} while(nIx < table.getItemCount() &&  dMinScore > dActualScore);
		
		if(dMinScore > dActualScore) {
			lblGradeDisplay.setText("6");	
		} else {
			lblGradeDisplay.setText(table.getItem(nIx - 1).getText(0));
		}
	}
	
	private void updateTable(Table table) {
		double dMaxScore;
		try{
			dMaxScore = spnMaxScore.getSelection()/Math.pow(10, spnMaxScore.getDigits());
		} catch (SWTException e) {
			logger.error("Problem at updateTable '{}' solved by return.", e.getMessage(), spnMaxScore);
			return;
		}
		String[] gradeNames = {"1+", "1", "1-", "2+", "2", "2-", "3+", "3", "3-", "4+", "4", "4-", "5"};
		double[] minPercent = {0.99, 0.97, 0.95, 0.9, 0.85, 0.8, 0.75, 0.7, 0.65, 0.59, 0.51, 0.45, 0.25};

		table.setRedraw(false);
		TableItem items[] = table.getItems();
		if(items.length <= 0) {
			for(int nIx = 0; nIx < gradeNames.length; nIx++) {
				new TableItem(table, SWT.NONE);
			}
			items = table.getItems();
		}
		
		for(int nIx = 0; nIx < gradeNames.length; nIx++) {
			double dMinPoints = dMaxScore * minPercent[nIx];
			items[nIx].setText(0, gradeNames[nIx]);
			items[nIx].setText(1, String.format("%.1f", dMinPoints));
		}
		
		for (TableColumn tc : table.getColumns())
	        tc.pack();

		table.setRedraw(true);
	}
}
