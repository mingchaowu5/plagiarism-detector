import gensim, sys
file_path = sys.argv[1]
model = gensim.models.KeyedVectors.load_word2vec_format(file_path, binary=True)


result = model.most_similar(positive=['the'], topn=10)
result1 = model.most_similar(positive=['army'], topn=10)
result2 = model.most_similar(positive=['received'], topn=10)
result3 = model.most_similar(positive=['famous'], topn=10)

with open('results-word2vec.txt', 'w') as f:
    f.write('\nRESULTS FOR "the" are : \n')
    f.write('\n\n')
    f.write('\n'.join('%s %s' % x for x in result))
    f.write('\n\n')
    f.write('\nRESULTS FOR "army" are : \n')
    f.write('\n\n')
    f.write('\n'.join('%s %s' % x for x in result1))
    f.write('\n\n')
    f.write('\nRESULTS FOR "received" are : \n')
    f.write('\n\n')
    f.write('\n'.join('%s %s' % x for x in result2))
    f.write('\n\n')
    f.write('\nRESULTS FOR "famous" are : \n')
    f.write('\n\n')
    f.write('\n'.join('%s %s' % x for x in result3))
    f.write('\n\n')
f.close()
