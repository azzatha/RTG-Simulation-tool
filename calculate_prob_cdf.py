import pandas as pd
from sklearn import preprocessing
import numpy as np
import glob
import math

# Function to Convert the cM to probability of recombination
def probability (genaticMaps):
  prob = (1.0 - math.exp(-genaticMaps["cM"] / 50)) / 2.0
  return prob


# Read the files in the current directory that contains all the genetic map
for filename in glob.glob('*.txt'):
  genaticMaps = pd.read_csv(filename, sep="\t")

  # 1.  Convert the cM to probability of recombination
  genaticMaps["Recmb_Prob"] = genaticMaps.apply(probability, axis=1)

  # 2. Normalize the probability
  X_normalized = preprocessing.normalize(genaticMaps["Recmb_Prob"], norm='l1')
  df_normalized = pd.DataFrame(X_normalized)
  df1_transposed = df_normalized.T
  df1_transposed['chr'] = genaticMaps["chr"]
  df1_transposed['pos'] = genaticMaps["pos"]
  df1_transposed['prob'] = df1_transposed[0]
  del df1_transposed[0]

  #3. Calculate CDF
  df1_transposed['cdf'] = np.cumsum(df1_transposed["prob"])

  # Save the final result to a new file
  df1_transposed.to_csv("./genetic_map/CDF_"+filename, sep="\t", index=False)
