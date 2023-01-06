
import java.awt.event.ActionListener;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
/**
 * This program is to construct a simple calculator
 * there are some simple digit buttons from 0 to 9 
 * there are also  operator icons such as division, multiplication,
 * addition, subtraction, and the equal sign.
 * Beside there is operator panel, this program also has a operator menu
 * with same functionality as operator panel does. 
 * 
 * @author shengshenghuo
 *
 */
public class Calculator extends JFrame {
	
	private static Object NORTH;
	private static Object SOUTH;
	private static Object WEST;
	private static Object EAST;
	private static Object CENTER;

	
	private JTextField outText;
	private String currentOperation;
	private Double firstOperand;
	
	private JMenuBar menuBar;
	private JMenu operatorMenu;
	private JMenuItem PlusItem;
	private JMenuItem MinusItem;
	private JMenuItem DivItem;
	private JMenuItem MultiItem;
	private JMenuItem ClearItem;
		

	
	/*
	 * constructor
	 */

	public Calculator() {
		/*
		 * adding program closing behavior
		 */
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent event) {
				System.exit(0);
			}
		});	
		
		/*
		 * set the app's size, title and location
		 */
		setTitle("My Calculator");
		setSize(600,300);
		setLocation(10,200);
		setBackground(Color.WHITE);
		setVisible(true);
		
		
		/*
		 * create main JPanel, and  
		 * setting the border lay out for the main panel 
		 */
		
		JPanel content = new JPanel(new FlowLayout());
		
		
		
		
		/*
		 * setting the field for the output text, 
		 * setting the font you like
		 * and then add outText into content panel
		 * for displaying
		 */
		outText = new JTextField("0", 20);
		outText.setFont(new Font("Calibri",Font.BOLD,30));
		
		
		content.add(outText);
		
		/*
		 * create Button panel, digit panel and operator panel
		 * Button panel contains digit panel and operator panel
		 * 
		 */
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(1,2));
		
		
		JPanel digitPanel = new JPanel();
		digitPanel.setLayout(new GridLayout(4,3));
		
		JPanel operatorPanel = new JPanel();
		//operatorPanel.setLayout(new GridLayout(5,1));
		//operatorPanel.setLayout(new BoxLayout(operatorPanel, BoxLayout.X_AXIS));
		operatorPanel.setLayout(new BorderLayout());
		
		
		
		
		
		/*
		 * create numeric digits, set the font you like
		 * and add them into digits panel
		 */
		
		JButton[] digits = new JButton[12];
		for(int i = 0; i <10;i++) {
			
			digits[i] = new JButton(String.valueOf(i));
			digits[i].setFont(new Font("Calibri",Font.BOLD,26));
			digitPanel.add(digits[i]);
		}
		/*
		 * keep creating non numeric digits and add them into digits panel
		 */
		digits[10] = new JButton(".");
		digits[11] = new JButton("AC");
		
		digits[10].setFont(new Font("Calibri",Font.BOLD,26));
		digits[11].setFont(new Font("Calibri",Font.BOLD,26));
		
		digitPanel.add(digits[10]);
		digitPanel.add(digits[11]);
		/*
		 * add the digitPanel into buttonPanel
		 */
		buttonPanel.add(digitPanel);
		
		/*
		 * create operator buttons, set the font you like,
		 * add the image you like,
		 * add them into buttons
		 * and then add those buttons into operatorPanel
		 */
		
		/*
		 * copy and paste the icon images you like in the bin folder, 
		 * remember to paste the images right next to the .class,
		 * there may be so many classes in the bin folder
		 */
		ImageIcon plusIcon = new ImageIcon(getClass().getClassLoader().getResource("Plus.png")); 
		Image img = plusIcon.getImage();
		Image newimg = img.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		plusIcon = new ImageIcon(newimg);
		
		
		//JButton plus = new JButton("+",plusIcon);
		JButton plus = new JButton(plusIcon);
		
		
		
		
		ImageIcon minusIcon = new ImageIcon(getClass().getClassLoader().getResource("Minus.png")); 
		img = minusIcon.getImage();
		newimg = img.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		minusIcon = new ImageIcon(newimg);
		
		
		//JButton minus = new JButton("-",minusIcon);
		JButton minus = new JButton(minusIcon);
		
		
		ImageIcon divIcon = new ImageIcon(getClass().getClassLoader().getResource("Divide.png")); 
		img = divIcon.getImage();
		newimg = img.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		divIcon = new ImageIcon(newimg);
		
		//JButton div = new JButton("/",divIcon);
		JButton div = new JButton(divIcon);
		
		
		ImageIcon mulIcon = new ImageIcon(getClass().getClassLoader().getResource("Multiply.png")); 
		img = mulIcon.getImage();
		newimg = img.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		mulIcon = new ImageIcon(newimg);
		
		//JButton mul = new JButton("*",mulIcon);
		JButton mul = new JButton(mulIcon);
		
		
		ImageIcon eqIcon = new ImageIcon(getClass().getClassLoader().getResource("Equal.png")); 
		img = eqIcon.getImage();
		newimg = img.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		eqIcon = new ImageIcon(newimg);
		
		//JButton equal = new JButton("=",eqIcon);
		JButton equal = new JButton(eqIcon);
		
		
	
		
		operatorPanel.add(plus,BorderLayout.NORTH);
		operatorPanel.add(minus, BorderLayout.SOUTH);
		operatorPanel.add(div,BorderLayout.WEST);
		operatorPanel.add(mul,BorderLayout.EAST);
		operatorPanel.add(equal,BorderLayout.CENTER);
		
		/*
		 * 1.add the operatorPanel into buttonPanel, 
		 * 2.add the buttonPanel into content,
		 * 3.finally add the content into constructor 
		 */
		
		buttonPanel.add(operatorPanel);
		
		content.add(buttonPanel);
		
		/*
		 * create the menu bar object
		 * and create operator menu object
		 */
		menuBar = new JMenuBar();
		operatorMenu = new JMenu("Operator Menu");
		/*
		 * add the operator Menu into menu bar
		 */
		menuBar.add(operatorMenu);
		
		/*
		 * create menu items
		 */
		
		PlusItem = new JMenuItem("PLUS");
		MinusItem = new JMenuItem("MINUS"); 
		DivItem = new JMenuItem("DIVIDE");
		MultiItem = new JMenuItem("MULTIPLY");
		
		ImageIcon clearImage = new ImageIcon (getClass().getClassLoader().getResource("ClearAC.png"));
		Image currentClearImage = clearImage.getImage();
		Image newClearImage = currentClearImage.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		ImageIcon ClearAC = new ImageIcon(newClearImage);
		ClearItem = new JMenuItem(ClearAC);
		
		
	/*
	 * add the items into operator menu
	 */
		operatorMenu.add(PlusItem);
		operatorMenu.add(MinusItem);
		operatorMenu.add(DivItem);
		operatorMenu.add(MultiItem);
		operatorMenu.add(ClearItem);
	/*
	 * add the menu Action listener into each items
	 * which enable items to have functionality of its own
	 */
		MenuListener MenuL = new MenuListener();
		PlusItem.addActionListener(MenuL);
		MinusItem.addActionListener(MenuL);
		DivItem.addActionListener(MenuL);
		MultiItem.addActionListener(MenuL);
		ClearItem.addActionListener(MenuL);
		
		
		content.add(menuBar);
		add(content);
		
		/*
		 * customize functions of digit 11 "AC" and digit 10 "."
		 */
		
		digits[11].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				resetValues();
				
			}
			
		});
		
		

		digits[10].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String currentText = outText.getText();
				if(currentText.indexOf(".")< 0) {
					outText.setText(currentText + ".");
				}
				
			}
			
		});
		
		
		/*
		 * customize the operator listener for the functions of operators
		 */
		
		ActionListener OperatorListener =  new ActionListener() {
			@Override 
			public void actionPerformed(ActionEvent event) {
				JButton source = (JButton) event.getSource();
				
				if(source == plus) {
					currentOperation = "+";
				}else if (source == minus) {
					currentOperation = "-";
				}else if (source == mul) {
					currentOperation = "*";
				}else if (source == div) {
					currentOperation ="/";
				}
				
				String currentText = outText.getText();
				
				try {
					
					Double currentTextDouble = new Double(currentText);
					firstOperand = currentTextDouble;
					outText.setText("0");
					
				}catch(NumberFormatException e) {
					resetValues();
				}
			}
			
		};
		/*
		 * Add the operationListener to operator buttons
		 * by using addActionListener() method
		 */
		
		
		plus.addActionListener(OperatorListener);
		minus.addActionListener(OperatorListener);
		div.addActionListener(OperatorListener);
		mul.addActionListener(OperatorListener);
		
		/*
		 * customize the numberListener for the functions of digits button 
		 */
		ActionListener numberListener = new ActionListener() {
			@Override 
			public void actionPerformed(ActionEvent event) {
				
				String currentInput = outText.getText();
				JButton inputButton = (JButton)event.getSource();
				String newNumber = "";
				
				if (inputButton == digits[0]) {
					newNumber = "0";
				
				}else if (inputButton == digits[1]) {
					newNumber = "1";
				}else if (inputButton ==digits[2]) {
					newNumber = "2";
				}else if(inputButton == digits[3]) {
					newNumber ="3";
				}else if(inputButton == digits[4]) {
					newNumber = "4";
				}else if(inputButton == digits[5]) {
					newNumber = "5";
				}else if(inputButton == digits[6]) {
					newNumber = "6";
				}else if(inputButton == digits[7]) {
					newNumber = "7";
				}else if(inputButton == digits[8]) {
					newNumber ="8";
				}else if(inputButton == digits[9]) {
					newNumber ="9";
				}
				
				currentInput  = currentInput  + newNumber;
				currentInput  = currentInput.replaceFirst("^0+(?!$)", "");
				outText.setText(currentInput);
				
			}
		};
		
		/*
		 * add digits button with numberListener
		 */
		
		for(int i = 0; i <10; i ++) {
			
			digits[i].addActionListener(numberListener);
		}
		
		/*
		 * add the ActionListener to the function of the equal sign button
		 * by override the actionPerformed() method
		 */
		
		equal.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				Double outputResult = 0.0;
				String currentInput = outText.getText();
				
				try {
					Double secondOperand = new Double(currentInput);
					
					if(currentOperation == "+") {
						 outputResult = firstOperand + secondOperand;
					}else if(currentOperation == "-") {
						outputResult = firstOperand - secondOperand;
					
					}else if(currentOperation == "/") {
						
						if(secondOperand != 0.0) {
							outputResult = firstOperand / secondOperand;
						
						} else {
							resetValues();
							outText.setBackground(Color.RED);
						}
						
						
						
					}else if(currentOperation == "*") {
						outputResult = firstOperand * secondOperand;
					}
					outText.setText(outputResult.toString());
					firstOperand = outputResult;
					
					
				}catch(NumberFormatException e) {
					resetValues();
				}
				
			} 
			
		});
		
		
	}

	/**
	 * reset values of the input	
	 */
	private void resetValues() {
		
		currentOperation = "";
		firstOperand = 0.0;
		outText.setText("0");
		outText.setBackground(Color.WHITE);
	
	}
	
	/**
	 * Menu Action Listener
	 * class server
	 * 
	 */
	private class MenuListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent event) {
			JMenuItem source = (JMenuItem)event.getSource();
			
			if(source == PlusItem ) {
				currentOperation = "+";
			}else if (source == MinusItem) {
				currentOperation = "-";
			}else if (source == MultiItem) {
				currentOperation = "*";
			}else if (source == DivItem) {
				currentOperation = "/";
			}else if (source == ClearItem) {
				resetValues();
			}
			
			String currentText = outText.getText();
			
			try {
				
				Double currentTextDouble = new Double(currentText);
				firstOperand = currentTextDouble;
				outText.setText("0");
				
			}catch(NumberFormatException e) {
				resetValues();
			}
			
		}
		
	}
	
	
	
	
	
	
	
	/*
	 * testing right now 
	 */
	
	
	public static void main(String[] args) {
		
		JFrame myApp = new Calculator();
		myApp.show();

	}

	

}
