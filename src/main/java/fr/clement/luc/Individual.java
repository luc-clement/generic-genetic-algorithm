package fr.clement.luc;

public abstract class Individual<C extends Chromosome> implements Comparable<Individual<C>> {
	/**
	 * Higher the value, better the individual.
	 */
	private int value;
	private C chromosome;
	
	abstract void applyMutation();
	abstract void doOptimisation();
	abstract <T extends Individual<?>> T combination(T individual);
	
	
	public Individual() {
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	public C getChromosome() {
		return chromosome;
	}
	
	public void setChromosome(C chromosome) {
		this.chromosome = chromosome;
	}

}
