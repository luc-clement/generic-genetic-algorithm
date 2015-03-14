package fr.clement.luc;

import java.util.ArrayList;
import java.util.List;

public class Population<T extends Individual> {
	private List<T> individuals = new ArrayList<T>();
	
	public int getBestScore() {
		// TODO
		return 0;
	}
	
	public Couple<T> getCouple() {
		// TODO
		return null;
	}
	
	public boolean accept(T individual) {
		// TODO
		return false;
	}
	
	public void insert(T individual) {
		// TODO
	}
	
	public Population(List<T> individuals) {
		super();
		this.individuals = individuals;
	}
	
	public Population() {
		super();
	}

	public List<T> getIndividuals() {
		return individuals;
	}

	public void setIndividuals(List<T> individuals) {
		this.individuals = individuals;
	}
	
	public class Couple<T2 extends Individual> {
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
