package com.cooksys.ftd.assignments.collections;


import com.cooksys.ftd.assignments.collections.hierarchy.Hierarchy;
import com.cooksys.ftd.assignments.collections.model.Capitalist;
import com.cooksys.ftd.assignments.collections.model.FatCat;
import com.cooksys.ftd.assignments.collections.model.WageSlave;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;
import java.util.Map.Entry;

public class MegaCorp implements Hierarchy<Capitalist, FatCat> {
	
	
	HashMap<FatCat, Set<Capitalist>> map = new HashMap<>();

    /**
     * Adds a given element to the hierarchy.
     * <p>
     * If the given element is already present in the hierarchy,
     * do not add it and return false
     * <p>
     * If the given element has a parent and the parent is not part of the hierarchy,
     * add the parent and then add the given element
     * <p>
     * If the given element has no parent but is a Parent itself,
     * add it to the hierarchy
     * <p>
     * If the given element has no parent and is not a Parent itself,
     * do not add it and return false
     *
     * @param capitalist the element to add to the hierarchy
     * @return true if the element was added successfully, false otherwise
     */
    @Override
    public boolean add(Capitalist capitalist) {
    	
    	
    	if(capitalist == null)
    		return false;
    	
    	if(has(capitalist))
    		return false;
    	if(capitalist.hasParent() && !has(capitalist.getParent())){
    		
    			Set<Capitalist> set = new HashSet<>();
    			set.add(capitalist);
    			map.put(capitalist.getParent(), set);
    			return true;
    		    		
    		
    	}
    	if(capitalist.hasParent() && has(capitalist.getParent())){
    		Set<Capitalist> set = map.get(capitalist.getParent());
    		set.add(capitalist);
    		map.put(capitalist.getParent(), set);
    		return true;
    		
    	}
    	
    	
    	 if(!capitalist.hasParent() && capitalist instanceof FatCat)
    	{
    		
    		map.put((FatCat)capitalist, null);
    		return true;
    	}
    	if(!capitalist.hasParent() && !(capitalist instanceof FatCat))
    	{
    		
    		
    		return false;
    	}
    	
    		return false;
    }

    /**
     * @param capitalist the element to search for
     * @return true if the element has been added to the hierarchy, false otherwise
     */
    @Override
    public boolean has(Capitalist capitalist) {
    	Set<Capitalist> set = getElements();
    	
    	 Iterator<Capitalist> iterator = set.iterator();
    	 while(iterator.hasNext())
    	 {
    		 if(capitalist.equals(iterator.next()))
    		 {
    			 return true;
    		 }
    	 }
    	 
    	return false;        
    }

    /**
     * @return all elements in the hierarchy,
     * or an empty set if no elements have been added to the hierarchy
     */
    @Override
    public Set<Capitalist> getElements() {
        Set<Capitalist> set = new HashSet<>();
       
        for(Entry<FatCat, Set<Capitalist>> m:map.entrySet())
    	{
        	if(m.getValue() != null){
        	 Iterator<Capitalist> iterator = m.getValue().iterator();
        	 while(iterator.hasNext())
        	 {
        		 set.add(iterator.next());
        	 }
        	 set.add(m.getKey());
        	}
        	else{
        		set.add(m.getKey());
        	}
    	}
        return set;
    }

    /**
     * @return all parent elements in the hierarchy,
     * or an empty set if no parents have been added to the hierarchy
     */
    @Override
    public Set<FatCat> getParents() {
       Set<FatCat> set = new HashSet<>();
       for(Entry<FatCat, Set<Capitalist>> m:map.entrySet())
       {
    	   set.add(m.getKey());
       }
       return set;
    }
    /**
     * @param fatCat the parent whose children need to be returned
     * @return all elements in the hierarchy that have the given parent as a direct parent,
     * or an empty set if the parent is not present in the hierarchy or if there are no children
     * for the given parent
     */
    @Override
    public Set<Capitalist> getChildren(FatCat fatCat) {
    	
    	 Set<Capitalist> set = new HashSet<>();
    	 
    	 if(map.get(fatCat) != null){
	    	 for(Capitalist c: map.get(fatCat))
	         {
	    		  set.add(c);
	         }
    	 }
    	 
    	return set;
    }

    /**
     * @return a map in which the keys represent the parent elements in the hierarchy,
     * and the each value is a set of the direct children of the associate parent, or an
     * empty map if the hierarchy is empty.
     */
    @Override
    public Map<FatCat, Set<Capitalist>> getHierarchy() {
    	HashMap<FatCat, Set<Capitalist>> mappy = new HashMap<>();
    	
    	getParents().forEach(current -> {
    		mappy.put(current, getChildren(current));
    	});
    	
    	
    return mappy;
    		
    
    }

    /**
     * @param capitalist
     * @return the parent chain of the given element, starting with its direct parent,
     * then its parent's parent, etc, or an empty list if the given element has no parent
     * or if its parent is not in the hierarchy
     */
    @Override
    public List<FatCat> getParentChain(Capitalist capitalist) {
    	List<FatCat> list = new LinkedList<>();
    	int index = 0;
    	 for(Entry<FatCat, Set<Capitalist>> m:map.entrySet())
     	{
         	 Iterator<Capitalist> iterator = m.getValue().iterator();
         	 while(iterator.hasNext())
         	 {
         		 if(iterator.next().equals(capitalist))
         		 {
         			 list.add(index++, m.getKey());
         			 getParentChain(m.getKey());
         		 }
         	 }
         	 
     	}
    	 return list;
    	
    	
}}
