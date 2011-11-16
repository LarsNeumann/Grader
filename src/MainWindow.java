import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import org.eclipse.wb.swt.SWTResourceManager;


public class MainWindow {

	protected Shell shlGrader;
	private Text textMaxScore;
	private Text textActualScore;
	private Label lblGradeDisplay;
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
		shlGrader.setSize(520, 173);
		shlGrader.setText("Notenrechner");

		Label lblMaxScore = new Label(shlGrader, SWT.NONE);
		lblMaxScore.setBounds(10, 10, 66, 16);
		lblMaxScore.setText("Max Punkte");

		Label lblActualScore = new Label(shlGrader, SWT.NONE);
		lblActualScore.setBounds(101, 10, 97, 16);
		lblActualScore.setText("Erreichte Punkte");

		textMaxScore = new Text(shlGrader, SWT.BORDER | SWT.CENTER);
		textMaxScore.setText("100");
		textMaxScore.setBounds(10, 32, 66, 22);
		textMaxScore.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				// TODO Auto-generated method stub
				updateTable();
			}
		});

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

		table = new Table(shlGrader, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(10, 82, 484, 46);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn tblclmn1p = new TableColumn(table, SWT.CENTER);
		tblclmn1p.setResizable(false);
		tblclmn1p.setWidth(30);
		tblclmn1p.setText("1+");

		TableColumn tblclmn1 = new TableColumn(table, SWT.CENTER);
		tblclmn1.setResizable(false);
		tblclmn1.setWidth(30);
		tblclmn1.setText("1");

		TableColumn tblclmn1m = new TableColumn(table, SWT.CENTER);
		tblclmn1m.setResizable(false);
		tblclmn1m.setWidth(30);
		tblclmn1m.setText("1-");

		TableColumn tblclmn2p = new TableColumn(table, SWT.CENTER);
		tblclmn2p.setResizable(false);
		tblclmn2p.setWidth(30);
		tblclmn2p.setText("2+");

		TableColumn tblclmn2 = new TableColumn(table, SWT.CENTER);
		tblclmn2.setResizable(false);
		tblclmn2.setWidth(30);
		tblclmn2.setText("2");

		TableColumn tblclmn2m = new TableColumn(table, SWT.CENTER);
		tblclmn2m.setResizable(false);
		tblclmn2m.setWidth(30);
		tblclmn2m.setText("2-");

		TableColumn tblclmn3p = new TableColumn(table, SWT.CENTER);
		tblclmn3p.setResizable(false);
		tblclmn3p.setWidth(30);
		tblclmn3p.setText("3+");

		TableColumn tblclmn3 = new TableColumn(table, SWT.CENTER);
		tblclmn3.setResizable(false);
		tblclmn3.setWidth(30);
		tblclmn3.setText("3");

		TableColumn tblclmn3m = new TableColumn(table, SWT.CENTER);
		tblclmn3m.setResizable(false);
		tblclmn3m.setWidth(30);
		tblclmn3m.setText("3-");

		TableColumn tblclmn4p = new TableColumn(table, SWT.CENTER);
		tblclmn4p.setResizable(false);
		tblclmn4p.setWidth(30);
		tblclmn4p.setText("4+");

		TableColumn tblclmn4 = new TableColumn(table, SWT.CENTER);
		tblclmn4.setResizable(false);
		tblclmn4.setWidth(30);
		tblclmn4.setText("4");

		TableColumn tblclmn4m = new TableColumn(table, SWT.CENTER);
		tblclmn4m.setResizable(false);
		tblclmn4m.setWidth(30);
		tblclmn4m.setText("4-");

		TableColumn tblclmn5p = new TableColumn(table, SWT.CENTER);
		tblclmn5p.setResizable(false);
		tblclmn5p.setWidth(30);
		tblclmn5p.setText("5+");

		TableColumn tblclmn5 = new TableColumn(table, SWT.CENTER);
		tblclmn5.setResizable(false);
		tblclmn5.setWidth(30);
		tblclmn5.setText("5");

		TableColumn tblclmn5m = new TableColumn(table, SWT.CENTER);
		tblclmn5m.setResizable(false);
		tblclmn5m.setWidth(30);
		tblclmn5m.setText("5-");

		TableColumn tblclmn6 = new TableColumn(table, SWT.CENTER);
		tblclmn6.setResizable(false);
		tblclmn6.setWidth(30);
		tblclmn6.setText("6");

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
	
	protected void updateTable() {
		double dMaxScore = Double.parseDouble(textMaxScore.getText());
//		Irgendwas in Tabelleschreiben
	}
}
