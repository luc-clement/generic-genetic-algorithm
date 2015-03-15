package fr.clement.luc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;

/**
 * 
 * @author Luc CLÉMENT
 *
 * @param <T>
 */
public class GeneticAlgorithm<T extends Individual> {

	private final static Logger LOGGER = Logger.getLogger(GeneticAlgorithm.class);

	private Class<T> classT;
	private int populationSize;
	private int mutationProbability;
	private boolean doOptimisation;
	private int nbOfGenerationsToCreate;

	public GeneticAlgorithm(Class<T> classT) {
		this.classT = classT;
	}

	public void execute() {
		Population<T> generation = generateFirstGeneration(populationSize);		
		Population<T> nextGeneration = new Population<T>();
		int nextGenerationSize = 0;
		Random random = new Random();
		random.setSeed(java.util.Calendar.getInstance().getTimeInMillis());
		
		LOGGER.info("Initial Population best score : " + generation.getBestScore());

		for (int i=1; i<=nbOfGenerationsToCreate; ++i) {
			while (nextGenerationSize < populationSize) {
				Population<T>.Couple<T> parents = generation.getCouple();
				T child = (T) parents.getParentA().combination(parents.getParentB());

				if (random.nextInt()%101 > mutationProbability) {
					child.applyMutation();
				}

				if (doOptimisation) {
					child.doOptimisation();
				}

				if (generation.accept(child)) {
					nextGeneration.insert(child);
					nextGenerationSize++;
				}
			}
			LOGGER.info("Generation n°" + i + " best score : " + nextGeneration.getBestScore());
			generation.setIndividuals(nextGeneration.getIndividuals());
			nextGeneration = new Population<T>();
			nextGenerationSize = 0;
		}
	}

	/**
	 * Generates a population of @Individuals based on the constructor of the individual which as to returns a real individual.
	 * 
	 * @param populationSize The size of the population to be generate
	 * @return a population of individuals
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	Population<T> generateFirstGeneration(int populationSize) {
		List<T> individuals = new ArrayList<T>();
		try {
			for (int i=0; i<populationSize; ++i) {
				T randomIndividual;
				randomIndividual = classT.newInstance();

				individuals.add(randomIndividual);
			}
		} catch (InstantiationException e) {
			LOGGER.error("Error while initializing individuals - InstantiationException : " + e.getMessage());
		} catch (IllegalAccessException e) {
			LOGGER.error("Error while initializing individuals - IllegalAccessException : " + e.getMessage());
		}
		Collections.sort(individuals);
		return new Population<T>(individuals);

	}

	public Class<T> getClassT() {
		return classT;
	}

	public void setClassT(Class<T> classT) {
		this.classT = classT;
	}

	public int getPopulationSize() {
		return populationSize;
	}

	public void setPopulationSize(int populationSize) {
		this.populationSize = populationSize;
	}

	public int getMutationProbability() {
		return mutationProbability;
	}

	public void setMutationProbability(int mutationProbability) {
		this.mutationProbability = mutationProbability;
	}

	public boolean isDoOptimisation() {
		return doOptimisation;
	}

	public void setDoOptimisation(boolean doOptimisation) {
		this.doOptimisation = doOptimisation;
	}

}
