package de.dertak.grader;

import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.eclipse.wb.swt.SWTResourceManager;

public class MainWindow {
	private final Logger logger = LoggerFactory.getLogger(MainWindow.class);
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
		shlGrader.setSize(249, 365);
		shlGrader.setText("Notenrechner");

		Label lblPointsForGrades = new Label(shlGrader, SWT.NONE);
		lblPointsForGrades.setBounds(10, 10, 76, 16);
		lblPointsForGrades.setText("Notenspiegel");

		final Table table = new Table(shlGrader, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(10, 32, 109, 289);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn[] columns = new TableColumn[2];
		
		columns[0] = new TableColumn(table, SWT.CENTER);
		columns[0].setResizable(false);
		columns[0].setWidth(45);
		columns[0].setText("Note");
		
		columns[1] = new TableColumn(table, SWT.CENTER);
		columns[1].setResizable(false);
		columns[1].setWidth(60);
		columns[1].setText("Punkte");

		Label lblMaxScore = new Label(shlGrader, SWT.NONE);
		lblMaxScore.setBounds(125, 10, 66, 16);
		lblMaxScore.setText("Max Punkte");
		
		textMaxScore = new Text(shlGrader, SWT.BORDER | SWT.CENTER);
		textMaxScore.setText("100");
		textMaxScore.setBounds(125, 32, 66, 22);
		textMaxScore.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				// TODO Auto-generated method stub
				updateTable(table);
			}
		});

		Label lblActualScore = new Label(shlGrader, SWT.NONE);
		lblActualScore.setBounds(126, 67, 97, 16);
		lblActualScore.setText("Erreichte Punkte");

		textActualScore = new Text(shlGrader, SWT.BORDER | SWT.CENTER);
		textActualScore.setText("0");
		textActualScore.setBounds(125, 89, 98, 22);
		textActualScore.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				// TODO Auto-generated method stub
				updateGrade(table);
			}
		});
		
		Label lblGrade = new Label(shlGrader, SWT.NONE);
		lblGrade.setBounds(126, 121, 29, 16);
		lblGrade.setText("Note");

		lblGradeDisplay = new Label(shlGrader, SWT.CENTER);
		lblGradeDisplay.setFont(SWTResourceManager.getFont("Ubuntu", 9, SWT.BOLD));
		lblGradeDisplay.setBounds(126, 145, 29, 16);
		lblGradeDisplay.setText("6");

		updateTable(table);
		updateGrade(table);
	}


	protected void updateGrade(Table table) {
		double dActualScore;
		try{
			dActualScore = Double.parseDouble(textActualScore.getText());
		} catch (NumberFormatException e) {
			logger.error("Problem at updateGrade '{}' solved by return.", e.getMessage(), textActualScore);
			return;
		} 
		int nIx = 0;
		double dMinScore;
		do{
			try {
				dMinScore = Double.parseDouble(table.getItem(nIx).getText(1));
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
			dMaxScore = Double.parseDouble(textMaxScore.getText());
		} catch (NumberFormatException e) {
			logger.error("Problem at updateTable '{}' solved by return.", e.getMessage(), textMaxScore);
			return;
		}
//TODO make scale changeable.
		//qian
//		String[] gradeNames = {"1+", "1", "1-", "2+", "2", "2-", "3+", "3", "3-", "4+", "4", "4-", "5"};
//		double[] minPercent = {0.99, 0.97, 0.95, 0.9, 0.85, 0.8, 0.75, 0.7, 0.65, 0.59, 0.51, 0.45, 0.25};
		//sandra
		String[] gradeNames = {"1",  "1-", "1-2", "2+", "2",  "2-", "2-3", "3+", "3", "3-", "3-4", "+4", "4", "4-", "4-5", "5+", "5", "5-", "5-6", "6+", "6"};
		double[] minPercent = {0.98, 0.94, 0.90,  0.86, 0.82, 0.78, 0.74, 0.70,  0.66, 0.62, 0.58, 0.54, 0.50, 0.48, 0.44, 0.40, 0.36, 0.32, 0.28, 0.24, 0.00};

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
			//TODO add something Rounding / Formating
			items[nIx].setText(1, String.valueOf(dMinPoints));
			logger.debug("Calculated points as String:{} \t as double:{}", String.valueOf(dMinPoints), dMinPoints);
		}
		
		table.setRedraw(true);
	}
	
}
