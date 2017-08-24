package com.cooksys.ftd.assignments.collections.model;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class WageSlave implements Capitalist {
	
	private FatCat owner;
	private int salary;
	private String name;

    public WageSlave(String name, int salary) {
        this.name = name;
        this.salary = salary;
    }

    public WageSlave(String name, int salary, FatCat owner) {
    	this.name = name;
        this.salary = salary;
        this.owner = owner;
    }
    
    public WageSlave(WageSlave slave)
    {
    	owner = slave.owner;
    	name = slave.name;
    	salary = slave.salary;
    }

    /**
     * @return the name of the capitalist
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * @return the salary of the capitalist, in dollars
     */
    @Override
    public int getSalary() {
        return this.salary;
    }

    /**
     * @return true if this element has a parent, or false otherwise
     */
    @Override
    public boolean hasParent() {
        if(this.owner != null)
        {
        	return true;
        }
        return false;
    }

    /**
     * @return the parent of this element, or null if this represents the top of a hierarchy
     */
    @Override
    public FatCat getParent() {
    	if(this.owner != null)
    		return owner;
    	else
    		return null;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((owner == null) ? 0 : owner.hashCode());
		result = prime * result + salary;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WageSlave other = (WageSlave) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (owner == null) {
			if (other.owner != null)
				return false;
		} else if (!owner.equals(other.owner))
			return false;
		if (salary != other.salary)
			return false;
		return true;
	}
	
	public WageSlave copy(WageSlave slave)
	{
		return new WageSlave(slave.getName(), slave.getSalary(), slave.getParent());
	}
	
	
	@Override
	public WageSlave clone()
	{
		return new WageSlave(name, salary, owner);
	}
}
