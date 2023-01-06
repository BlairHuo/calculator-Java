import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.event.ActionListener;
import java.util.ArrayList;



import java.awt.*;
import java.awt.Color;
import java.awt.event.*;
import java.util.*;


public class calculatorApp extends Frame{
	/*
	 * class field that make up the GuI and functionality
	 */
	private Label myLabel;
	private TextField outputField;
	private ArrayList<Button> digits;
	private Button plusSign;
	private Button minusSign;
	private Button divSign;
	private Button multSign;
	private Button eqSign;
	private String currentOperation;
	private Double firstOperand;
	
	/*
	 * Constructor
	 */
	public calculatorApp() {
		
		this.currentOperation =" ";
		this.firstOperand = 0.0;
		
		Panel displayPanel = new Panel(new FlowLayout());
		
		outputField = new TextField("0", 20);
		displayPanel.add(outputField);

		Panel buttonPanel = new Panel(new GridLayout(1,2)); // first layer
		
		
		Panel digitButtonPanel = new Panel(new GridLayout(4,3)); // second layer
		
		/*
		 * customize buttons, create an arraylist of buttons and store the button objects in the "digits" array list.
		 */
		
		digits = new ArrayList<Button>();
		digits.add(new Button("0"));
		digits.add(new Button("1"));
		digits.add(new Button("2"));
		digits.add(new Button("3"));
		digits.add(new Button("4"));
		digits.add(new Button("5"));
		digits.add(new Button("6"));
		digits.add(new Button("7"));
		digits.add(new Button("8"));
		digits.add(new Button("9"));
		digits.add(new Button("."));
		digits.add(new Button("AC"));
		
		
		digits.get(10).setForeground(Color.MAGENTA);
		digits.get(11).setForeground(Color.MAGENTA);
		/*
		 * now we can customize the digitButtonPanel(The Panel of Buttons)
		 * Therefore, we need to add those "digits buttons" into the "Panel(button panel)"
		 */
		digitButtonPanel.add(digits.get(0));
		digitButtonPanel.add(digits.get(1));
		digitButtonPanel.add(digits.get(2));
		digitButtonPanel.add(digits.get(3));
		digitButtonPanel.add(digits.get(4));
		digitButtonPanel.add(digits.get(5));
		digitButtonPanel.add(digits.get(6));
		digitButtonPanel.add(digits.get(7));
		digitButtonPanel.add(digits.get(8));
		digitButtonPanel.add(digits.get(9));
		digitButtonPanel.add(digits.get(10));// "."
		digitButtonPanel.add(digits.get(11));// "AC"
		
		/*
		 * add "digitButtonPanel" into "buttonPanel"
		 */
		buttonPanel.add(digitButtonPanel);
		
		/*
		 * customize the operatorButtonPanel 
		 * this panel is for operator only
		 */
		Panel operatorButtonPanel = new Panel(new GridLayout(5,1)); // third layer
		
		/*
		 * we create the operator buttons first
		 */
		plusSign = new Button("+");
		minusSign = new Button("-");
		divSign= new Button("/");
		multSign = new Button("*");
		eqSign = new Button("=");
		
		/*
		 * and then, we could set those buttons to be the color we want
		 */
		
		plusSign.setForeground(Color.BLUE);
		minusSign.setForeground(Color.BLUE);
		divSign.setForeground(Color.BLUE);
		multSign.setForeground(Color.BLUE);
		eqSign.setForeground(Color.GREEN);
		/*
		 * After finishing creating and designing the buttons' looks
		 * and then we can add them to "Operator Button Panel"
		 */
		operatorButtonPanel.add(plusSign);
		operatorButtonPanel.add(minusSign);
		operatorButtonPanel.add(divSign);
		operatorButtonPanel.add(multSign);
		operatorButtonPanel.add(eqSign);
		
		/*
		 * finally, we can add the "operatorButtonPanel" to "buttonPanel"
		 */
		buttonPanel.add(operatorButtonPanel);
		
		/*
		 * next step, we could add the "buttonPanel" to "displayPanel"
		 * and then, the button is finished. 
		 */
		displayPanel.add(buttonPanel);
		/*
		 * we add the display panel into constructor
		 */
		add(displayPanel);
		
		/*
		 * now we need customize the digit 11"AC" and digit 10 "."
		 * by adding addActionListener() method
		 */
		
		digits.get(11).addActionListener(new ActionListener() {
			@Override 
			public void actionPerformed(ActionEvent event) {
				resetValues();
			}
		});
		
		digits.get(10).addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent event) {
				
				String currentText = outputField.getText();
				if(currentText.indexOf(".")< 0) {
					outputField.setText(currentText + ".");
				}
			}
				

		});
		
		
		/*
		 * create "opListener" opratorListener object
		 * and then, we add opListener to the buttons by using addActionListener() method
		 */
		OperatorListener opListener = new OperatorListener();
		plusSign.addActionListener(opListener);
		minusSign.addActionListener(opListener);
		multSign.addActionListener(opListener);
		divSign.addActionListener(opListener);
		
		for(int i = 0; i <= 9; i++) {
			digits.get(i).addActionListener( new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent event) {
					
					String currentText = outputField.getText();
					Button source = (Button)event.getSource();
					String newDigit = "";
					
					if (source == digits.get(0)) {
						newDigit = "0";
					
					}else if (source == digits.get(1)) {
						newDigit = "1";
					}else if (source == digits.get(2)) {
						newDigit = "2";
					}else if(source == digits.get(3)) {
						newDigit ="3";
					}else if(source == digits.get(4)) {
						newDigit = "4";
					}else if(source == digits.get(5)) {
						newDigit = "5";
					}else if(source == digits.get(6)) {
						newDigit = "6";
					}else if(source == digits.get(7)) {
						newDigit = "7";
					}else if(source == digits.get(8)) {
						newDigit ="8";
					}else if(source == digits.get(9)) {
						newDigit ="9";
					}
					
					
					currentText = currentText + newDigit;
					currentText = currentText.replaceFirst("^0+(?!$)", "");
					outputField.setText(currentText);
					
				}
			});
			
		}
		
		/*
		 * add the action listener to eqSign
		 * override actionPerformed(ActionEvent event)
		 */
		eqSign.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				
				Double result = 0.0;
				String currentText = outputField.getText();
				
				
				
				try {
					Double secondOperand = new Double(currentText);
					
					if(currentOperation == "+") {
						result = firstOperand + secondOperand;
					
					}else if(currentOperation == "-") {
						result = firstOperand - secondOperand;
					
					}else if(currentOperation == "*") {
						result = firstOperand * secondOperand;
					
					}else if(currentOperation == "/") {
						
						if(secondOperand != 0.0) {
							result = firstOperand / secondOperand;
						
						} else {
							resetValues();
							outputField.setBackground(Color.PINK);
						}
					}
					
					outputField.setText(result.toString());
					firstOperand = result;
					
				}catch(NumberFormatException e) {
					resetValues();
				}
			}
			
		});
		
		/*
		 * add a window listener
		 * when we want to close the calculator
		 */
		addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent event) {
				System.exit(0);
			}
		});
		
		/*
		 * setting the App name, App size and App visibility
		 */
		setTitle("Calculator");
		setSize(500, 200);
		setVisible(true);
		
	}	
		
	/**
	 * reset values of the input	
	 */
	private void resetValues() {
		
		currentOperation = "";
		firstOperand = 0.0;
		outputField.setText("0");
		outputField.setBackground(Color.WHITE);
	
	}
	
	
	
	/*
	 * design how the button appear in the window
	 */
	private class OperatorListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent event) {
			
			Button source = (Button)event.getSource();
			
			if(source == plusSign) {
				currentOperation = "+";
			
			}else if(source == minusSign) {
				currentOperation = "-";
			
			}else if(source == multSign) {
				currentOperation = "*";
			
			}else if(source == divSign) {
				currentOperation = "/";
			}
			
			String currentText = outputField.getText();
			
			try {
				
				Double currentTextDouble = new Double(currentText);
				firstOperand = currentTextDouble;
				outputField.setText("0");
			
			}catch(NumberFormatException e) {
				resetValues();
			}
			
			
			
		}
		
	}
	
	
	
	
	
	
	public static void main(String[]args) {
		new calculatorApp();
	}
	

}
