package fr.clement.luc;

public abstract class Individual implements Comparable<Individual> {
	/**
	 * Higher the value, better the individual.
	 */
	private int value;
	
	abstract void applyMutation();
	abstract void doOptimisation();
	abstract <T extends Individual> T combination(Individual individual);
	
	
	public Individual() {
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

}
