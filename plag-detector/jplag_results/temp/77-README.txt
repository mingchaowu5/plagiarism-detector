In order to run the code make sure you have python 3.6.1 installed. Also if you don’t have install os, glob, math, re, sys, tensorflow packages. Use pip install for installation of packages.

To run the naïve bayes classifier script, 

1)	Open terminal or command line
2)	Navigate to the folder where the script is
3)	type in : python3 nb.py {path_to_hw2-sa-ds}
4)	In my case it was 
python3 nb.py /Users/varunnandu/Desktop/NLP/HW02/hw2-sa-ds

The script will train the data from the training set. Build the model. Apply the model on test set. Classify the data and output the following results:

Precision value of pos is :  0.6049382716049383
Recall value  of pos is :  0.98
F1 value of pos is :  0.7480916030534351
Precision value of neg is :  0.9473684210526315
Recall value  of neg is :  0.36
F1 value of neg is :  0.5217391304347826
Average F1 value is :  0.6349153667441089

To run the multi layer perceptron script, 

1)	Open terminal or command line
2)	Navigate to the folder where the script is
3)	type in : python3 perceptron.py {path_to_hw2-sa-ds}
4)	In my case it was 
python3 perceptron.py /Users/varunnandu/Desktop/NLP/HW02/hw2-sa-ds

The script will train the data from the training set. Build the model. Apply the model on test set. Classify the data and output the following results:

Cost of first run is :  0.240464
Precision value for first run is :  0.5
Recall value for first run is :  1.0
F1 value for first run is :  0.666666666667
Cost of second run is :  0.578662
Precision value for second run is :  0.5
Recall value for second run is :  1.0
F1 value for second run  is :  0.666666666667
Cost of third run is :  0.960554
Precision value for third run is :  0.5
Recall value for third run is :  1.0
F1 value for third run is :  0.666666666667
Cost for fourth run is : 1.95592
Precision value for fourth run is :  0.5
Recall value for fourth run is :  1.0
F1 value for fourth run is :  0.666666666667