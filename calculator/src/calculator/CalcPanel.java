package calculator;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class CalcPanel extends JPanel {
	private JPanel panel;
	private boolean start;
	private double result;
	private JButton display;
	private String lastCommand;

	public CalcPanel() {
		super();

		start = true;
		lastCommand = "=";
		
		setLayout(new BorderLayout());
		display = new JButton("0");
		display.setEnabled(false);
		add(display, BorderLayout.NORTH);

		panel = new JPanel();
		panel.setLayout(new GridLayout(4, 4));

		InsertAction insert = new InsertAction();
		CommandAction command = new CommandAction();

		addButton("7", insert);
		addButton("8", insert);
		addButton("9", insert);
		addButton("/", command);

		addButton("4", insert);
		addButton("5", insert);
		addButton("6", insert);
		addButton("*", command);

		addButton("1", insert);
		addButton("2", insert);
		addButton("3", insert);
		addButton("-", command);

		addButton("0", insert);
		addButton(".", insert);
		addButton("=", command);
		addButton("+", command);

		add(panel, BorderLayout.CENTER);
	}

	private void addButton(String label, ActionListener listener) {
		JButton button = new JButton(label);
		button.addActionListener(listener);
		panel.add(button);
	}

	private class InsertAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent ae) {
			String input = ae.getActionCommand();
			if(start) {
				display.setText("");
				start = false;
			} 
			if (!input.contentEquals(".") || display.getText().indexOf(".") == -1) {
			display.setText(display.getText() + input);
		}
		}
	}

	private class CommandAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent ae) {
			String command = ae.getActionCommand();
			if (start) {
				lastCommand = command;
			} else {
				calc(Double.parseDouble(display.getText()));
				lastCommand = command;
				start = true;
			}
		}
		
		public void calc (Double x) {
			if(lastCommand == "+") result += x;
			else if (lastCommand == "-") result -= x;
			else if (lastCommand == "*") result *= x;
			else if (lastCommand == "/") result /= x;
			else if (lastCommand == "=") result = x;
			display.setText("" +result);
		}
	}
}
