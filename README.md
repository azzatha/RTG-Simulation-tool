# RTG Tools (updated simulation)

You can find all the details about the tool and how to install in [RTG Tools](https://github.com/RealTimeGenomics/rtg-tools)


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
