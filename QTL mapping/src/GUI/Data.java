package GUI;
/**
*This class represents data that are required for calculation 
*Every object  characterized by GeneticMapPath, GenotypePath, TraitPath, TestName, Student_Test, Permutation_Test,
* PermutationVAR_Test, F_Test, KruskalWalis_Test, ANOVA_Test, InputType, ChrLength, NumMarkers, NumMarkers, mean, 
* var, numQTL , AlleMean, AlleVar
*/
public class Data {
	public String GeneticMapPath;
	public String GenotypePath;
	public String TraitPath;
	public String TestName;
	public Boolean Student_Test;
	public Boolean Permutation_Test;
	public Boolean PermutationVAR_Test;
	public Boolean F_Test;
	public Boolean KruskalWalis_Test;
	public Boolean ANOVA_Test;
	public String InputType;
	public String ChrLength;
	public String NumMarkers;
	public String mean;
	public String var;
	public String numQTL;
	public String AlleMean;
	public String AlleVar;
	
	
	
	/**
	 * 
	 * @param geneticMapPath path for genetic map file
	 * @param genotypePath path for genotype file
	 * @param traitPath path for trait file
	 * @param testName test name
	 * @param student_Test is the test selected
	 * @param permutation_Test is the test selected
	 * @param permutationVAR_Test is the test selected
	 * @param f_Test is the test selected
	 * @param kruskalWalis_Test is the test selected
	 * @param aNOVA_Test is the test selected
	 * @param inputType simulator or file
	 * @param chrLength chromosome length
	 * @param numMarkers number of markers
	 * @param mean mean's value
	 * @param var variance's value
	 * @param numQTL QTL marker
	 * @param alleMean allele 1 mean's value
	 * @param alleVar allele 1 variance's value
	 */
	public Data(String geneticMapPath, String genotypePath, String traitPath, String testName, Boolean student_Test,
			Boolean permutation_Test, Boolean permutationVAR_Test, Boolean f_Test, Boolean kruskalWalis_Test,
			Boolean aNOVA_Test, String inputType, String chrLength, String numMarkers, String mean, String var,
			String numQTL, String alleMean, String alleVar) {
		super();
		GeneticMapPath = geneticMapPath;
		GenotypePath = genotypePath;
		TraitPath = traitPath;
		TestName = testName;
		Student_Test = student_Test;
		Permutation_Test = permutation_Test;
		PermutationVAR_Test = permutationVAR_Test;
		F_Test = f_Test;
		KruskalWalis_Test = kruskalWalis_Test;
		ANOVA_Test = aNOVA_Test;
		InputType = inputType;
		ChrLength = chrLength;
		NumMarkers = numMarkers;
		this.mean = mean;
		this.var = var;
		this.numQTL = numQTL;
		AlleMean = alleMean;
		AlleVar = alleVar;
	}
	

	
	/**
	 * @return the geneticMapPath
	 */
	public String getGeneticMapPath() {
		return GeneticMapPath;
	}



	/**
	 * @param geneticMapPath the geneticMapPath to set
	 */
	public void setGeneticMapPath(String geneticMapPath) {
		GeneticMapPath = geneticMapPath;
	}



	/**
	 * @return the genotypePath
	 */
	public String getGenotypePath() {
		return GenotypePath;
	}



	/**
	 * @param genotypePath the genotypePath to set
	 */
	public void setGenotypePath(String genotypePath) {
		GenotypePath = genotypePath;
	}



	/**
	 * @return the traitPath
	 */
	public String getTraitPath() {
		return TraitPath;
	}



	/**
	 * @param traitPath the traitPath to set
	 */
	public void setTraitPath(String traitPath) {
		TraitPath = traitPath;
	}



	/**
	 * @return the testName
	 */
	public String getTestName() {
		return TestName;
	}



	/**
	 * @param testName the testName to set
	 */
	public void setTestName(String testName) {
		TestName = testName;
	}



	/**
	 * @return the student_Test
	 */
	public Boolean getStudent_Test() {
		return Student_Test;
	}



	/**
	 * @param student_Test the student_Test to set
	 */
	public void setStudent_Test(Boolean student_Test) {
		Student_Test = student_Test;
	}



	/**
	 * @return the permutation_Test
	 */
	public Boolean getPermutation_Test() {
		return Permutation_Test;
	}



	/**
	 * @param permutation_Test the permutation_Test to set
	 */
	public void setPermutation_Test(Boolean permutation_Test) {
		Permutation_Test = permutation_Test;
	}



	/**
	 * @return the permutationVAR_Test
	 */
	public Boolean getPermutationVAR_Test() {
		return PermutationVAR_Test;
	}



	/**
	 * @param permutationVAR_Test the permutationVAR_Test to set
	 */
	public void setPermutationVAR_Test(Boolean permutationVAR_Test) {
		PermutationVAR_Test = permutationVAR_Test;
	}



	/**
	 * @return the f_Test
	 */
	public Boolean getF_Test() {
		return F_Test;
	}



	/**
	 * @param f_Test the f_Test to set
	 */
	public void setF_Test(Boolean f_Test) {
		F_Test = f_Test;
	}



	/**
	 * @return the kruskalWalis_Test
	 */
	public Boolean getKruskalWalis_Test() {
		return KruskalWalis_Test;
	}



	/**
	 * @param kruskalWalis_Test the kruskalWalis_Test to set
	 */
	public void setKruskalWalis_Test(Boolean kruskalWalis_Test) {
		KruskalWalis_Test = kruskalWalis_Test;
	}



	/**
	 * @return the aNOVA_Test
	 */
	public Boolean getANOVA_Test() {
		return ANOVA_Test;
	}



	/**
	 * @param aNOVA_Test the aNOVA_Test to set
	 */
	public void setANOVA_Test(Boolean aNOVA_Test) {
		ANOVA_Test = aNOVA_Test;
	}



	/**
	 * @return the inputType
	 */
	public String getInputType() {
		return InputType;
	}



	/**
	 * @param inputType the inputType to set
	 */
	public void setInputType(String inputType) {
		InputType = inputType;
	}



	/**
	 * @return the chrLength
	 */
	public String getChrLength() {
		return ChrLength;
	}



	/**
	 * @param chrLength the chrLength to set
	 */
	public void setChrLength(String chrLength) {
		ChrLength = chrLength;
	}



	/**
	 * @return the numMarkers
	 */
	public String getNumMarkers() {
		return NumMarkers;
	}



	/**
	 * @param numMarkers the numMarkers to set
	 */
	public void setNumMarkers(String numMarkers) {
		NumMarkers = numMarkers;
	}



	/**
	 * @return the mean
	 */
	public String getMean() {
		return mean;
	}



	/**
	 * @param mean the mean to set
	 */
	public void setMean(String mean) {
		this.mean = mean;
	}



	/**
	 * @return the var
	 */
	public String getVar() {
		return var;
	}



	/**
	 * @param var the var to set
	 */
	public void setVar(String var) {
		this.var = var;
	}



	/**
	 * @return the numQTL
	 */
	public String getNumQTL() {
		return numQTL;
	}



	/**
	 * @param numQTL the numQTL to set
	 */
	public void setNumQTL(String numQTL) {
		this.numQTL = numQTL;
	}



	/**
	 * @return the alleMean
	 */
	public String getAlleMean() {
		return AlleMean;
	}



	/**
	 * @param alleMean the alleMean to set
	 */
	public void setAlleMean(String alleMean) {
		AlleMean = alleMean;
	}



	/**
	 * @return the alleVar
	 */
	public String getAlleVar() {
		return AlleVar;
	}



	/**
	 * @param alleVar the alleVar to set
	 */
	public void setAlleVar(String alleVar) {
		AlleVar = alleVar;
	}



	@Override
	public String toString() {
		return "Data [GeneticMapPath=" + GeneticMapPath + ", GenotypePath=" + GenotypePath + ", TraitPath=" + TraitPath
				+ ", TestName=" + TestName + ", Student_Test=" + Student_Test + ", Permutation_Test=" + Permutation_Test
				+ ", PermutationVAR_Test=" + PermutationVAR_Test + ", F_Test=" + F_Test + ", KruskalWalis_Test="
				+ KruskalWalis_Test + ", ANOVA_Test=" + ANOVA_Test + ", InputType=" + InputType + ", ChrLength="
				+ ChrLength + ", NumMarkers=" + NumMarkers + ", mean=" + mean + ", var=" + var + ", numQTL=" + numQTL
				+ ", AlleMean=" + AlleMean + ", AlleVar=" + AlleVar + "]";
	}



	
	
	

}
