package configuration;

import individuals.BoundRepresentation;
import individuals.GeneTypes;
import individuals.GenoRepresentation;
import initialization.GenoInitializer;
import initialization.RandomGenoInitializer;
import mutation.EvovleMutation;
import mutation.MultiSigma;
import mutation.Mutation;
import recombination.RandomRecombination;
import recombination.Recombination;
import selection.*;

import java.util.ArrayList;

/**
 * Created by phlippe on 08.09.18.
 */
public class ExampleConfig extends Configuration
{

	private int population_size = 100;
	private int number_recombinations = 10;
	private int parent_arity = 2;
	private double[] variances = {0.05};
	private double variance = 0.05;
	private double sigvaraince = 0.05;


	public ExampleConfig(){
		super();
		init();
	}

	public ExampleConfig(int population_size, int number_recombinations, int parent_arity) {
		this(population_size, number_recombinations, parent_arity, "");
	}

	public ExampleConfig(int population_size, int number_recombinations, int parent_arity, String name){
		this.population_size = population_size;
		this.number_recombinations = number_recombinations;
		this.parent_arity = parent_arity;
		setName(name);
		init();
	}

	public ExampleConfig(int population_size, int number_recombinations, int parent_arity, double[] variances){
		this(population_size, number_recombinations, parent_arity, variances, "");
	}

	public ExampleConfig(int population_size, int number_recombinations, int parent_arity, double[] variances, String name){
		this.population_size = population_size;
		this.number_recombinations = number_recombinations;
		this.parent_arity = parent_arity;
		this.variances = variances;
		setName(name);
		init();
	}


	public ExampleConfig(int population_size, int number_recombinations, int parent_arity, double v){
		this(population_size, number_recombinations, parent_arity, v, "");
	}

	public ExampleConfig(int population_size, int number_recombinations, int parent_arity, double v, String name) {
		this.population_size = population_size;
		this.number_recombinations = number_recombinations;
		this.parent_arity = parent_arity;
		variances = new double[1];
		variances[0] = v;
		setName(name);
		init();
	}


	public ExampleConfig(int population_size, int number_recombinations, int parent_arity, double v, double sigv, String name) {
		this.population_size = population_size;
		this.number_recombinations = number_recombinations;
		this.parent_arity = parent_arity;
		this.variance = v;
		this.sigvaraince = sigv;
		setName(name);
		init();
	}

	@Override
	protected GenoRepresentation createRepresentation()
	{
		int num[] = {10};
		GeneTypes [] gene = {GeneTypes.MULTI_SIGMA};
		GenoRepresentation genoRepresentation = new BoundRepresentation(10, num, gene, -5, 5);
		return genoRepresentation;
	}

	@Override
	protected ArrayList<Mutation> createMutationOperators()
	{
		GeneTypes[] multi = {GeneTypes.MULTI_SIGMA};
		GeneTypes[] genes = {GeneTypes.OPT_GENES};
		ArrayList<Mutation> mutations = new ArrayList<>();
		mutations.add(new EvovleMutation(multi));
		mutations.add(new MultiSigma(genes));

		return mutations;
	}

	@Override
	protected Recombination createRecombination()
	{
		Recombination recombination = new RandomRecombination();
		return recombination;
	}

	@Override
	protected ParentSelection createParentSelection()
	{
		ParentSelectionStochastic parentSelectionStochastic = new ParentSelectionStochasticRoulette();
		ParentSelection parentSelection = new ParentFitnessSelection(parentSelectionStochastic);
		return parentSelection;
	}

	@Override
	protected SurvivorSelection createSurvivorSelection()
	{
		SurvivorSelection survivorSelection = new SurvivorFitnessSelection();
		return survivorSelection;
	}

	@Override
	protected GenoInitializer createGenoInitializer() {
		GenoInitializer genoinit = new RandomGenoInitializer();
		return genoinit;
	}

	@Override
	protected ArrayList<GenoInitializer> createAddParamsInitializer() {

		ArrayList<GenoInitializer> addparam = new ArrayList<>();
		addparam.add(new RandomGenoInitializer(0.5,0.5));
		return addparam;
	}

	@Override
	public int getPopulationSize()
	{
		return population_size;
	}

	@Override
	public int getNumberOfRecombinations()
	{
		return number_recombinations;
	}

	@Override
	public int getParentArity()
	{
		return parent_arity;
	}
}
