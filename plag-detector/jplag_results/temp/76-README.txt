In order to run the code make sure you have python 3.6.1 installed. Also if you don’t have install gensim, sys, os, glob, re, nltk, sys, time, math packages. Use pip install for installation of packages.

To run the brown clustering script, 

1)	Open terminal or command line
2)	Navigate to the folder where the script is
3)	type in : python3 brown.py {path_to_brown_dataset}
4)	In my case it was 
python3 brown.py /Users/varunnandu/Desktop/NLP/HW03/brown


To run the word2vec script, 

1)	Open terminal or command line
2)	Navigate to the folder where the script is
3)	type in : python3 word2vec.py {path_to_GoogleNews-vectors-negative300.bin}
4)	In my case it was 
python3 word2vec.py /Users/varunnandu/Desktop/NLP/HW03/GoogleNews-vectors-negative300.bin

I am also including a script to get top 10 similar words from brown clusters.txt

To run this script:

1)	Open terminal or command line
2)	Navigate to the folder where the script is
3)	type in : python3 test2.py {path_to_brownclusters.txt}
4)	In my case it was 
python3 test2.py /Users/varunnandu/Desktop/NLP/HW03/brownclusters.txt