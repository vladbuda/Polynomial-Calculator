package gui.calc;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import operation.calc.Operation;
import polynomial.calc.Polinom;

public class GUI extends JFrame
{
	private JButton buttonAdd, buttonSubtract, buttonMultiply, buttonDivide, buttonDifferentiate, buttonIntegrate, buttonClear, buttonEqual;
	private JTextArea text, text2;
	private JScrollPane pane, pane2;
	private Polinom p1, p2; //stores the polynomial expressions
	private int flag; //used to point the current operation being performed (addition = 1, subtraction = 2, multiplication = 3, division = 4)
	
	public GUI()
	{
		super("Polynomial Calculator");
		
		p1 = new Polinom();
		p2 = new Polinom();
		
		buttonAdd = new JButton("+");
		buttonSubtract = new JButton("-");
		buttonMultiply = new JButton("*");
		buttonDivide = new JButton("/");
		buttonDifferentiate = new JButton("( )'");
		buttonIntegrate = new JButton("Sdx");
		buttonClear = new JButton("C");
		buttonEqual = new JButton("=");
		
		text = new JTextArea();
		text2 = new JTextArea();
		
		pane = new JScrollPane(text,JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pane.setBounds(50, 100, 236, 32);
		pane2 = new JScrollPane(text2,JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pane2.setBounds(50, 65, 236, 32);
		pane2.setBorder(BorderFactory.createEmptyBorder());
		text2.setEditable(false);
		text2.setBackground(this.getBackground());
		text.setFont(new Font("Calibri",0,16));
		
		buttonAdd.setBounds(50, 150, 56,56);
		buttonSubtract.setBounds(140,150,56,56);
		buttonMultiply.setBounds(230,150,56,56);
		buttonDivide.setBounds(50,220,56,56);
		buttonDifferentiate.setBounds(140,220,56,56);
		buttonIntegrate.setBounds(230,220,56,56);
		buttonClear.setBounds(50, 290, 100, 50);
		buttonEqual.setBounds(186, 290, 100, 50);
		
		buttonAdd.setFocusable(false);
		buttonSubtract.setFocusable(false);
		buttonMultiply.setFocusable(false);
		buttonDivide.setFocusable(false);
		buttonDifferentiate.setFocusable(false);
		buttonIntegrate.setFocusable(false);
		buttonClear.setFocusable(false);
		buttonEqual.setFocusable(false);
		
		buttonAdd.addActionListener(new AdditionHandler());
		buttonSubtract.addActionListener(new SubtractionHandler());
		buttonMultiply.addActionListener(new MultiplicationHandler());
		buttonDivide.addActionListener(new DivisionHandler());
		buttonDifferentiate.addActionListener(new DifferentiationHandler());
		buttonIntegrate.addActionListener(new IntegrationHandler());
		buttonEqual.addActionListener(new EqualHandler());
		buttonClear.addActionListener(new ClearHandler());
		
		this.add(pane);
		this.add(pane2);
		this.add(buttonAdd);
		this.add(buttonSubtract);
		this.add(buttonMultiply);
		this.add(buttonDivide);
		this.add(buttonDifferentiate);
		this.add(buttonIntegrate);
		this.add(buttonClear);
		this.add(buttonEqual);
		
		this.setLayout(null);
		this.setSize(340, 480);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	
	private class AdditionHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(text.getText().length() > 0)
			{
				if(flag == 1) p1.clearPol();
				String s = text.getText();
				s = s.replace('X','x'); //replace capital 'X' with 'x'
				if(!Operation.readPolinom(s, p1))
				{
					JOptionPane.showMessageDialog(null, String.format("%s", "Input should look like: ax^m+bx^n+c (no spaces)" ), "Invalid format", JOptionPane.ERROR_MESSAGE, null);
					text.setText(null);
				}
				else
				{
				text.setText(null);
				text2.setText("(" + s + ")" + " +"); //put the text in the above text area
				flag = 1; //set the flag to the corresponding operation
				}
			}
		}
	}
	
	private class SubtractionHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(text.getText().length() > 0)
			{
			if(flag == 2) p1.clearPol();
			String s = text.getText();
			s = s.replace('X','x');
			if(!Operation.readPolinom(s, p1))
			{
				JOptionPane.showMessageDialog(null, String.format("%s", "Input should look like: ax^m+bx^n+c (no spaces)" ), "Invalid format", JOptionPane.ERROR_MESSAGE, null);
				text.setText(null);
			}
			else
			{
			text.setText(null);
			text2.setText("(" + s + ")" + " -");
			flag = 2;
			}
		}
		}
	}
	
	private class MultiplicationHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(text.getText().length() > 0)
			{
			if(flag == 3) p1.clearPol();
			String s = text.getText();
			s = s.replace('X','x');
			if(!Operation.readPolinom(s, p1))
			{
				JOptionPane.showMessageDialog(null, String.format("%s", "Input should look like: ax^m+bx^n+c (no spaces)" ), "Invalid format", JOptionPane.ERROR_MESSAGE, null);
				text.setText(null);
			}
			else
			{
			text.setText(null);
			text2.setText("(" + s + ")" + " *");
			flag = 3;
			}
			}
		}
	}
	
	private class DivisionHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(text.getText().length() > 0)
			{
			if(flag == 4) p1.clearPol();
			String s = text.getText();
			s = s.replace('X','x');
			if(!Operation.readPolinom(s, p1))
			{
				JOptionPane.showMessageDialog(null, String.format("%s", "Input should look like: ax^m+bx^n+c (no spaces)" ), "Invalid format", JOptionPane.ERROR_MESSAGE, null);
				text.setText(null);
			}
			else
			{
			text.setText(null);
			text2.setText("(" + s + ")" + " /");
			flag = 4;
			}
			}
		}
	}
	
	private class DifferentiationHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(text.getText().length() > 0)
			{
			String s = text.getText();
			s = s.replace('X','x');
			if(!Operation.readPolinom(s, p1))
			{
				JOptionPane.showMessageDialog(null, String.format("%s", "Input should look like: ax^m+bx^n+c (no spaces)" ), "Invalid format", JOptionPane.ERROR_MESSAGE, null);
				text.setText(null);
			}
			else
			{
			Polinom rez = Operation.differentiate(p1);
			rez.cleanPol();
			text.setText(rez.toString());
			}
			}
		}
	}
	
	private class IntegrationHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(text.getText().length() > 0)
			{
			String s = text.getText();
			s = s.replace('X','x');
			if(!Operation.readPolinom(s, p1))
			{
				JOptionPane.showMessageDialog(null, String.format("%s", "Input should look like: ax^m+bx^n+c (no spaces)" ), "Invalid format", JOptionPane.ERROR_MESSAGE, null);
				text.setText(null);
			}
			else
			{
			if(p1.getTerms().size() == 1 && p1.getTerms().get(0).getCoefficient() == 0) text.setText("C"); //check for integral of 0
			else
			{
			Polinom rez = Operation.integrate(p1);
			text.setText(rez.toString() + "+C");
			}
			}
			}
		}
	}
	
	private class EqualHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(text.getText().length() > 0)
			{
			if (flag == 1)
			{
				String s = text.getText(); //read the second expression
				s = s.replace('X','x');
				if(!Operation.readPolinom(s, p2))
				{
					JOptionPane.showMessageDialog(null, String.format("%s", "Input should look like: ax^m+bx^n+c (no spaces)" ), "Invalid format", JOptionPane.ERROR_MESSAGE, null);
					text.setText(null);
				}
				else
				{
				text2.setText(null);
				Polinom rez = Operation.add(p1, p2); //obtain the result
				rez.cleanPol(); //erase monomials with coefficient 0
				rez.sort(); //sort the expression in descending order based on their degree
				text.setText(rez.toString()); //print the result
				flag = 0; //set the flag back to 0
				}
			}
			else if (flag == 2)
			{
				String s = text.getText();
				s = s.replace('X','x');
				if(!Operation.readPolinom(s, p2))
				{
					JOptionPane.showMessageDialog(null, String.format("%s", "Input should look like: ax^m+bx^n+c (no spaces)" ), "Invalid format", JOptionPane.ERROR_MESSAGE, null);
					text.setText(null);
				}
				else
				{
				text2.setText(null);
				Polinom rez = Operation.subtract(p1, p2);
				rez.cleanPol();
				rez.sort();
				text.setText(rez.toString());
				flag = 0;
				}
			}
			else if (flag == 3)
			{
				String s = text.getText();
				s = s.replace('X','x');
				if(!Operation.readPolinom(s, p2))
				{
					JOptionPane.showMessageDialog(null, String.format("%s", "Input should look like: ax^m+bx^n+c (no spaces)" ), "Invalid format", JOptionPane.ERROR_MESSAGE, null);
					text.setText(null);
				}
				else
				{
				text2.setText(null);
				Polinom rez = Operation.multiply(p1, p2);
				rez.sort();
				rez.cleanPol();
				text.setText(rez.toString());
				flag = 0;
				}
			}
			else if(flag == 4)
			{
				String s = text.getText();
				s = s.replace('X','x');
				if(!Operation.readPolinom(s, p2))
				{
					JOptionPane.showMessageDialog(null, String.format("%s", "Input should look like: ax^m+bx^n+c (no spaces)" ), "Invalid format", JOptionPane.ERROR_MESSAGE, null);
					text.setText(null);
				}
				else
				{
				p2.cleanPol(); //clean monomials with coefficient 0
				if(p2.getTerms().size() > 0)
				{
					List<Polinom> list =  Operation.divide(p1, p2);
					list.get(0).sort();
					text.setText("Q: "+list.get(0).toString());
					text2.setText("R: "+list.get(1).toString());
					flag = 0;
				}
				else
				{
					text.setText("Cannot divide by 0");
					text2.setText(null);
					flag = 0;
				}
			}
			}
			else flag = 0;
			}
	}
	}
	
	private class ClearHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			text.setText(null);
			text2.setText(null);
			p1.clearPol(); //erase the current polynomials
			p2.clearPol();
			flag = 0;
		}
	}
}
