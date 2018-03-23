package polynomial.calc;

public class Monom implements Comparable<Monom>
{
	private int degree;
	private float coefficient;
	
	public Monom(float coefficient, int degree)
	{
		this.coefficient = coefficient;
		this.degree = degree;
	}
	public float getCoefficient()
	{
		return coefficient;
	}
	
	public int getDegree()
	{
		return degree;
	}
	
	public void setCoefficient(float coefficient)
	{
		this.coefficient = coefficient;
	}
	
	public void setDegree(int degree)
	{
		this.degree = degree;
	}
	
	public void add(Monom m)
	{
		if(this.degree == m.getDegree()) this.coefficient += m.getCoefficient();	
	}
	
	public void multiply(Monom m)
	{
		this.coefficient *= m.coefficient;
		this.degree += m.degree;
	}

	public void differentiante()
	{
		this.coefficient *= this.degree;
		this.degree--;
	}
	
	public void integrate()
	{
		this.degree++;
		this.coefficient /= this.degree;
	}
	@Override
	public boolean equals(Object obj)
	{
		if(obj == null) return false;
		if(!(obj instanceof Monom)) return false;
		Monom m2 = (Monom) obj;
		if(this.coefficient != m2.getCoefficient()) return false;
		if(this.degree != m2.getDegree()) return false;
		return true;
	}
	
	public int compareTo(Monom m)
	{
		return m.degree - this.degree;
	}
	
	@Override
	public String toString()
	{
		return String.format("%.2fx^%d + ", this.getCoefficient(), this.getDegree());
	}
}
