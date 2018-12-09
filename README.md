# RTG Tools (updated simulation)

You can find all the details about the tool and how to install in [RTG Tools](https://github.com/RealTimeGenomics/rtg-tools)


## Recombination Rate Maps

- For the simulation we have used data files containing recombination rates for human build 37 at [Recombination maps from Bhérer, C. et al. Nature Communications](https://github.com/cbherer/Bherer_etal_SexualDimorphismRecombination)
- Since we need to find the recombination probability that helps with deciding the number of recombinations per chromosome, we converted the recombination rate Map (with recombination rate measured in cM) to recombination probability.
- After obtaining recombination probabilities for each chromosomal position in the human genome, we normalized the value and calculated the Cumulative Distribution Function (CDF), using the code [calculate_prob_cdf.py](https://github.com/azzatha/RTG-Simulation-tool/blob/master/calculate_prob_cdf.py).


## Compile / run unit tests (Updated Simulation)

    $ ant -Dregression.update="com.rtg.*" runalltests

## Build RTG Tools package

To build the RTG Tools package which can be locally installed and run:

    $ ant zip-nojre

This will create an installation zip file under `dist`.
    
-------------------------------------------    
## Run the Simulation 

You need to have one VCF file contains the genetic information of mother and father, then run the command as follow:

    $   rtg childsim --mother $motherSample --father $motherSample  -i InputFile.vcf.gz  -o Child -t referenceGenome.sdf -s Child
