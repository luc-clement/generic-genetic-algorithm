package fr.clement.luc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Population<T extends Individual<?>> {
	private List<T> individuals = new ArrayList<T>();
	private Random random = new Random();
	
	public int getBestScore() {
		int result = 0;
		for (T individual : individuals) {
			int currentValue = individual.getValue();
			if (Math.abs(currentValue) > result) {
				result = currentValue;
			}
		}
		return result;
	}
	
	public int getBaddestScore() {
		int result = Integer.MAX_VALUE;
		for (T individual : individuals) {
			int currentValue = individual.getValue();
			if (Math.abs(currentValue) < result) {
				result = currentValue;
			}
		}
		return result;
	}
	
	public Couple<T> getCouple() {
		T parentA = getIndividualBetweenPositions(0, 4);
		T parentB = getIndividualBetweenPositions(5, individuals.size()-1);
		return new Couple<T>(parentA, parentB);
	}
	
	public T getIndividualBetweenPositions(int min, int max) {
		if (individuals.isEmpty()) {
			return null;
		} else if (min < 0 || max > individuals.size()-1) {
			return individuals.get(0);
		}
		
		int position = (random.nextInt() % (max - min)) + min;
		return individuals.get(position);
	}
	
	public boolean accept(T individual) {
		if (individual.getValue() > getBaddestScore()) {
			return true;
		}
		return false;
	}
	
	public void insert(T individual) {
		individuals.add(individual);
		Collections.sort(individuals);
	}
	
	public Population(List<T> individuals) {
		super();
		this.individuals = individuals;
		random.setSeed(java.util.Calendar.getInstance().getTimeInMillis());
	}
	
	public Population() {
		super();
		random.setSeed(java.util.Calendar.getInstance().getTimeInMillis());
	}

	public List<T> getIndividuals() {
		return individuals;
	}

	public void setIndividuals(List<T> individuals) {
		this.individuals = individuals;
	}
	
	public class Couple<T2 extends Individual<?>> {
		private T2 parentA;
		private T2 parentB;
		
		public T2 getParentA() {
			return parentA;
		}
		
		public T2 getParentB() {
			return parentB;
		}
		
		public Couple(T2 parentA, T2 parentB) {
			this.parentA = parentA;
			this.parentB = parentB;
		}
	}
}
