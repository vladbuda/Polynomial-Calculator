package operation.calc;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import polynomial.calc.Monom;
import polynomial.calc.Polinom;

public class Operation 
{
	
	public static Polinom add(Polinom p1, Polinom p2)
	{
		Polinom copyP1 = p1.getDeepCopy();
		
		for(Monom m : p2.getTerms())
		{
			copyP1.addMonom(m);
		}
		return copyP1;
	}
	
	public static Polinom subtract(Polinom p1, Polinom p2)
	{
		Polinom copyP1 = p1.getDeepCopy();
		Polinom copyP2 = p2.getDeepCopy();
		
		for(Monom m : copyP2.getTerms())
		{
			m.setCoefficient(-1 * m.getCoefficient());
		}
		
		for(Monom m : copyP2.getTerms())
		{
			copyP1.addMonom(m);
		}
		return copyP1;
	}
	
	public static Polinom multiply(Polinom p1, Polinom p2)
	{
		Polinom copyP1;
		Polinom rez = new Polinom();
		for(Monom m: p2.getTerms())
		{
			copyP1 = p1.getDeepCopy();
			copyP1.multiplyMonom(m);
			rez = Operation.add(rez, copyP1);
		}
		return rez;
	}
	
	public static Polinom differentiate(Polinom p)
	{
		Polinom copyP = p.getDeepCopy();
		copyP.differentiate();
		return copyP;
	}
	
	public static Polinom integrate(Polinom p)
	{
		Polinom copyP = p.getDeepCopy();
		copyP.integrate();
		return copyP;
	}
	
	public static Polinom multiply(Polinom p, Monom m)
	{
		Polinom rez = new Polinom();
		for(Monom m1 : p.getTerms())
		{
				rez.addMonom(new Monom(m1.getCoefficient()*m.getCoefficient(),m1.getDegree()+m.getDegree()));
		}
		return rez;
	}
	
	public static List<Polinom> divide(Polinom p1, Polinom p2)
	{
		List<Polinom> rez = new ArrayList<Polinom>();
		Polinom copyP1 = p1.getDeepCopy(); //remainder
		Polinom copyP2 = p2.getDeepCopy();
		Polinom quotient = new Polinom();
		copyP1.sort();
		copyP2.sort();
		Monom firstP1 = copyP1.getTerms().get(0);
		Monom firstP2 = copyP2.getTerms().get(0);
		while(firstP1.getDegree() >= firstP2.getDegree())
		{
			Monom auxm = new Monom((float)(firstP1.getCoefficient()/firstP2.getCoefficient()),firstP1.getDegree()-firstP2.getDegree());
			quotient.addMonom(auxm);
			Polinom auxp = Operation.multiply(copyP2, auxm);
			copyP1 = Operation.subtract(copyP1, auxp);
			copyP1.cleanPol();
			copyP1.sort();
			if(copyP1.getTerms().size() > 0) firstP1 = copyP1.getTerms().get(0);
			else break;
		}
		rez.add(quotient);
		rez.add(copyP1);
		return rez;
	}
	
	public static boolean readPolinom(String exp, Polinom p) //stores a string of a polynomial expression in a object of type Polinom; returns true if the string was valid or false otherwise
	{
		Pattern pattern = Pattern.compile("([+-]?[^-+]+)");
		Matcher matcher = pattern.matcher(exp);
		String[] s;
		String s2;
		for(int i = 0; i < exp.length(); i++) //first check if the expression contains illegal characters
		{
			char current = exp.charAt(i);
			if(!Character.isDigit(current) && current != 'x' && current != '-' && current != '+' && current != '^' && current != '.')
			{
				return false;
			}
			if(!Character.isDigit(current) && i < exp.length() - 1) //check for duplicates other than digits
			{
				if(current == 'x' && exp.charAt(i+1) == 'x' || exp.charAt(i+1) == '.') return false;
				else if(current == '+' && (exp.charAt(i+1) == '+' || exp.charAt(i+1) == '-' || exp.charAt(i+1) == '^')) return false;
				else if(current == '-' && (exp.charAt(i+1) == '+' || exp.charAt(i+1) == '-' || exp.charAt(i+1) == '^')) return false;
				else if(current == '^' && (exp.charAt(i+1) == '+' || exp.charAt(i+1) == '-' || exp.charAt(i+1) == '^')) return false;
				else if(current == '.' && !Character.isDigit(exp.charAt(i+1))) return false;
			}
			if(Character.isDigit(current) && i < exp.length() - 1) //check for digit^
			{
				if(exp.charAt(i+1) == '^') return false;
			}
		}
		while (matcher.find()) 
		{
			if(matcher.group().contains("x^"))
			{
				s = matcher.group().split("x\\^"); //get the degree and coefficient for x^
				if(s[0].length() == 1 && (s[0].contains("-") || s[0].contains("+"))) //case +-x^n
				{
					if(s[0].charAt(0) == '-')	s[0] = "-1";
					else s[0] = "1";
				}
				else 
				{
					if(s[0].length() < 1) s[0] = "1"; //case x^n
				}
				p.addMonom(new Monom(Float.parseFloat(s[0]), Integer.parseInt(s[1])));
			}
			else if (matcher.group().contains("x"))
			{
				s = matcher.group().split("x");
				if(s.length == 0) //case x
				{
					p.addMonom(new Monom(1,1));
				}
				else
				{
				if(s[0].length() == 1 && (s[0].contains("-") || s[0].contains("+"))) //case +-x
				{
					if(s[0].charAt(0) == '-')	s[0] = "-1";
					else s[0] = "1";
				}
				p.addMonom(new Monom(Float.parseFloat(s[0]), 1));
				}
			}
			else
			{
				s2 = matcher.group();
				p.addMonom(new Monom(Float.parseFloat(s2), 0));
			}
		}
		return true;
	}
}
