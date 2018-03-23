package polynomial.calc;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import operation.calc.Operation;

public class PolyTest 
{
	@Test
	public void addTest()
	{
		Polinom p1 = new Polinom();
		p1.addMonom(new Monom(2,6));
		p1.addMonom(new Monom(-3,5));
		p1.addMonom(new Monom(2,1));
		p1.addMonom(new Monom(-1,0));
		
		Polinom p2 = new Polinom();
		p2.addMonom(new Monom(-4,3));
		p2.addMonom(new Monom(7,1));
		p2.addMonom(new Monom(-1,0));
		
		Polinom rez = Operation.add(p1, p2);
		Polinom truerez = new Polinom();
		truerez.addMonom(new Monom(2,6));
		truerez.addMonom(new Monom(-3,5));
		truerez.addMonom(new Monom(-4,3));
		truerez.addMonom(new Monom(9,1));
		truerez.addMonom(new Monom(-2,0));
		rez.sort();
		truerez.sort();
		assertEquals("Addition test", rez, truerez);
	}
	
	@Test
	public void subtractionTest()
	{
		Polinom p1 = new Polinom();
		p1.addMonom(new Monom(5,4));
		p1.addMonom(new Monom(-3,3));
		p1.addMonom(new Monom(2,1));
		p1.addMonom(new Monom(15,0));
		
		Polinom p2 = new Polinom();
		p2.addMonom(new Monom(6,5));
		p2.addMonom(new Monom(9,3));
		p2.addMonom(new Monom(2,2));
		p2.addMonom(new Monom(-12,0));
		
		Polinom rez = Operation.subtract(p1, p2);
		Polinom truerez = new Polinom();
		truerez.addMonom(new Monom(-6,5));
		truerez.addMonom(new Monom(5,4));
		truerez.addMonom(new Monom(-12,3));
		truerez.addMonom(new Monom(-2,2));
		truerez.addMonom(new Monom(2,1));
		truerez.addMonom(new Monom(27,0));
		rez.sort();
		truerez.sort();
		assertEquals("Subtraction test", rez, truerez);
	}
	
	@Test
	public void multiplicationTest()
	{
		Polinom p1 = new Polinom();
		p1.addMonom(new Monom(-2,3));
		p1.addMonom(new Monom(3,1));
		
		Polinom p2 = new Polinom();
		p2.addMonom(new Monom(2,2));
		p2.addMonom(new Monom(-1,0));
		
		Polinom rez = Operation.multiply(p1, p2);
		Polinom truerez = new Polinom();
		truerez.addMonom(new Monom(-4,5));
		truerez.addMonom(new Monom(8,3));
		truerez.addMonom(new Monom(-3,1));
		
		rez.sort();
		truerez.sort();
		assertEquals("Multiplication test", rez, truerez);
	}
	
	@Test
	public void divisionTest()
	{
		Polinom p1 = new Polinom();
		p1.addMonom(new Monom(2,5));
		p1.addMonom(new Monom(1,4));
		p1.addMonom(new Monom(-5,3));
		p1.addMonom(new Monom(-8,1));
		p1.addMonom(new Monom(1,0));
		
		Polinom p2 = new Polinom();
		p2.addMonom(new Monom(1,2));
		p2.addMonom(new Monom(-3,0));
		
		List<Polinom> list = Operation.divide(p1, p2);
		Polinom quotient = new Polinom();
		quotient.addMonom(new Monom(2,3));
		quotient.addMonom(new Monom(1,2));
		quotient.addMonom(new Monom(1,1));
		quotient.addMonom(new Monom(3,0));
		
		Polinom remainder = new Polinom();
		remainder.addMonom(new Monom(-5,1));
		remainder.addMonom(new Monom(10,0));
		
		remainder.sort();
		quotient.sort();
		assertEquals("Division test", list.get(0), quotient);
		assertEquals("Division test", list.get(1), remainder);
	}
	
	@Test
	public void differentationTest()
	{
		Polinom p1 = new Polinom();
		p1.addMonom(new Monom(8,8));
		p1.addMonom(new Monom(7,4));
		p1.addMonom(new Monom(-3,2));
		p1.addMonom(new Monom(-12,1));
		p1.addMonom(new Monom(1,0));
		
		
		Polinom rez = Operation.differentiate(p1);
		Polinom truerez = new Polinom();
		truerez.addMonom(new Monom(64,7));
		truerez.addMonom(new Monom(28,3));
		truerez.addMonom(new Monom(-6,1));
		truerez.addMonom(new Monom(-12,0));
		
		rez.cleanPol();
		rez.sort();
		truerez.sort();
		assertEquals("Differentiation test", rez, truerez);
	}
	
	public void integrationTest()
	{
		Polinom p1 = new Polinom();
		p1.addMonom(new Monom(7,3));
		p1.addMonom(new Monom(-3,2));
		p1.addMonom(new Monom(5,1));
		p1.addMonom(new Monom(-2,0));
		
		
		Polinom rez = Operation.integrate(p1);
		Polinom truerez = new Polinom();
		truerez.addMonom(new Monom((float)1.75,4));
		truerez.addMonom(new Monom(-1,3));
		truerez.addMonom(new Monom((float)2.5,2));
		truerez.addMonom(new Monom(-2,1));
		
		rez.cleanPol();
		rez.sort();
		truerez.sort();
		assertEquals("Integration test", rez, truerez);
	}
}
