package polynomial.calc;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Polinom 
{
	private List<Monom> list;
	
	public Polinom()
	{
		list = new ArrayList<Monom>();
	}
	
	public void addMonom(Monom m) 
	{
		boolean found = false;
		for(Monom i: list) 
		{
			if(m.getDegree() == i.getDegree())
			{
				i.add(m);
				found = true;
			}
		}
		
		if(!found) list.add(m); 
	}
	
	public void multiplyMonom(Monom m)
	{
		for(Monom i: list)
		{
			i.multiply(m);
		}
	}
	
	public void differentiate()
	{
		for(Monom i: list)
		{
			i.differentiante();
		}
	}
	
	public void integrate()
	{
		for(Monom i: list)
		{
			i.integrate();
		}
	}
	
	public List<Monom> getTerms()
	{
		return list;
	}
	
	public Polinom getDeepCopy() //returns a deep copy for the current polynomial
	{
		Polinom copy = new Polinom();
		Monom mcopy;
		for(Monom m : this.getTerms())
		{
			mcopy = new Monom(m.getCoefficient(), m.getDegree());
			copy.addMonom(mcopy);
		}
		return copy;
	}
	
	public void cleanPol() //erase the elements with coefficient 0
	{
		Polinom aux = this.getDeepCopy();
		for(Monom m : aux.list)
		{
			if(m.getCoefficient() == 0.0 || m.getDegree() < 0) this.list.remove(m);
		}
	}
	
	public void clearPol() //erase the polynomial
	{
		list.clear();
	}
	
	public void sort()
	{
		Collections.sort(this.list);
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if(obj == null) return false;
		if(!(obj instanceof Polinom)) return false;
		Polinom pol = (Polinom) obj;
		if(!(this.list.equals(pol.getTerms()))) return false;
		return true;
	}
	
	@Override
	public String toString() //returns a formatted string containing the polynomial expression based on some cases
	{
		String s = "";
		int size = list.size();
		if(list.size() > 0) //check if the list contains at least one element
		{
			if(list.size() == 1 && list.get(0).getDegree() == 0) //polynomial has just the constant
			{
				if(list.get(list.size()-1).getCoefficient() > 0) s =  String.format("%s%.2f", s, (float)list.get(list.size()-1).getCoefficient());
				else s =  String.format("%s%.2f", s, (float)list.get(list.size()-1).getCoefficient());
			}
			else if(list.size() == 1 && list.get(0).getDegree() > 0) //has just one variable
			{
				if(list.get(list.size()-1).getCoefficient() > 0) s =  String.format("%s%.2fx^%d", s, (float)list.get(list.size()-1).getCoefficient(),list.get(0).getDegree());
				else s =  String.format("%s%.2fx^%d", s, (float)list.get(list.size()-1).getCoefficient(),list.get(0).getDegree());
			}
			else
			{
				s = String.format("%.2fx^%d",(float)list.get(0).getCoefficient(),list.get(0).getDegree()); //puts the first monomial without sign in case of +
				if(list.get(list.size()-1).getDegree() == 0) //checks if the last element has degree 0 (for printing just the coefficient, without the variable)
				{
					size--;
				}
				for(int i = 1; i < size; i++) //obtain the representation of the rest of the expression
				{
					if(list.get(i).getCoefficient() > 0)
					{
						s = String.format("%s+%.2fx^%d", s, (float)list.get(i).getCoefficient(),list.get(i).getDegree());
					}
					else 
					{
						s = String.format("%s%.2fx^%d", s, (float)list.get(i).getCoefficient(),list.get(i).getDegree());
					}
				}
				if(size != list.size()) //size will be different if the last element has degree 0
				{
					if(list.get(list.size()-1).getCoefficient() > 0) s =  String.format("%s+%.2f", s, (float)list.get(list.size()-1).getCoefficient());
					else s =  String.format("%s%.2f", s, (float)list.get(list.size()-1).getCoefficient());
				}
			}
		}
		else s = "0"; //0 if the list is empty
		return s;
	}
}
