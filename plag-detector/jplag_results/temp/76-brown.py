import os, glob
import re
import nltk
import sys
import time, math

def scandirs(path):
    mydict = dict()
    newdict = dict()
    pre_process_sentence = []
    sentences = []
    temp = []
    for currentFile in glob.glob(os.path.join(path, '*')):
        if currentFile == path+'/README' or currentFile == path+'/CONTENTS':
            continue
        else:
            with open(currentFile) as f:
                mylist = f.read()
                sent_tokenize_list = nltk.sent_tokenize(mylist)
                for i in sent_tokenize_list:
                    sent = i.lower()
                    sentence = sent.split(' ')
                    for s in range(len(sentence)):
                        sentence[s] = re.sub('\s+', '', sentence[s])
                        sentence[s] = sentence[s].split('/')[0]
                    pre_process_sentence.append(sentence)
    for sentence in pre_process_sentence:
        for i in sentence:
            key = i
            value = i
            mydict.setdefault(key, []).append(value)

    for key, value in mydict.items():
        v = len(value)
        mydict[key] = v
    for sentence in range(len(pre_process_sentence)):
        for i in range(len(pre_process_sentence[sentence])):
            if mydict[pre_process_sentence[sentence][i]] < 10:
                pre_process_sentence[sentence][i] = 'UNK'
    for i in pre_process_sentence:
        sentences.append(' '.join(i))
    for i in range(len(sentences)):
        sentences[i] = re.sub('[^A-Za-z0-9]+', ' ', sentences[i])

    for i in sentences:
        for j in i.split(' '):
            temp.append(j)

    for sentence in temp:
        key = sentence
        value = sentence
        newdict.setdefault(key, []).append(value)

    for key, value in newdict.items():
        if key == '':
            del newdict[key]
        else:
            v = len(value)
        newdict[key] = v




    sorted_word_list = sorted(newdict.items(), key=lambda x: (-x[1], x[0]))
    return sentences, sorted_word_list

def bigram_count(sentences):
    bidict1 = {}
    for i in range(len(sentences)):
        sen_array = sentences[i].split(' ')
        for j in range(len(sen_array) - 1):
            if sen_array[j] not in bidict1:
                bidict1[sen_array[j]] = {}
            if sen_array[j+1] not in bidict1[sen_array[j]]:
                bidict1[sen_array[j]][sen_array[j+1]] = []
            bidict1[sen_array[j]][sen_array[j+1]].append(sen_array[j+1])

    for k, v in bidict1.items():
        for key, value in v.items():
            bidict1[k][key] = len(value)

    return bidict1


def brown_clusters(sorted_word_dict, bigram_model1):
    k = 200
    cluster = {}
    active_indices = [i for i in range(k)]
    inactive_indices = []
    cluster_count = {}
    index = 0
    for key, v in sorted_word_dict.items():
        cluster[index] = []
        cluster[index].append(key)
        cluster_count[index] = v
        index += 1
    total_cluster_count = sum(list(cluster_count.values())[:k])

    cluster_pair_count = {}
    Quality = {}
    bit = dict()
    for ind in cluster:
        for w in cluster[ind]:
            if w not in bit:
                bit[w] = ''

    while k < len(sorted_word_dict):
        print(k)
        for i in range(k):
            for j in range(k):
                if i == j:
                    continue
                else:
                    if i not in cluster_pair_count:
                        cluster_pair_count[i] = {}
                    if j not in cluster_pair_count[i]:
                        cluster_pair_count[i][j] = 0

                    if cluster[i][0] not in bigram_model1 or cluster[j][0] not in bigram_model1[cluster[i][0]]:
                        cluster_pair_count[i][j] = 0
                    else:
                        cluster_pair_count[i][j] = bigram_model1[cluster[i][0]][cluster[j][0]]
        total_cluster_pair_count = 0
        for key, value in cluster_pair_count.items():
            total_cluster_pair_count += sum(value.values())


        for i in range(k):
            for j in range(k):
                if i == j:
                    continue
                else:
                    if i not in Quality:
                        Quality[i] = {}
                    if j not in Quality[i]:
                        Quality[i][j] = 0

                    pij = float(cluster_pair_count[i][j])/float(total_cluster_pair_count)
                    pi = float(cluster_count[i])/float(total_cluster_count)
                    pj = float(cluster_count[j])/float(total_cluster_count)
                    if pij == 0:
                        Quality[i][j] = 0
                    else:
                        Quality[i][j] = float(pij) * math.log(float(pij)/float((pi*pj)))


        for i in range(len(sorted_word_dict)):
            if i < k:
                continue
            else:
                cluster_pair_count[i] = dict()
                cluster_pair_count[j] = dict()
                Quality[i] = dict()
                for j in active_indices:
                    if cluster[i][0] not in bigram_model1 or cluster[j][0] not in bigram_model1[cluster[i][0]]:
                        cluster_pair_count[i][j] = 0
                    else:
                        cluster_pair_count[i][j] = bigram_model1[cluster[i][0]][cluster[j][0]]

                    if cluster[j][0] not in bigram_model1 or cluster[i][0] not in bigram_model1[cluster[j][0]]:
                        cluster_pair_count[j][i] = 0
                    else:
                        cluster_pair_count[j][i] = bigram_model1[cluster[j][0]][cluster[i][0]]


                total_cluster_pair_count = 0
                for key, value in cluster_pair_count.items():
                    total_cluster_pair_count += sum(value.values())
                for j in active_indices:
                    pij = float(cluster_pair_count[i][j]) / float(total_cluster_pair_count)
                    pji = float(cluster_pair_count[j][i]) / float(total_cluster_pair_count)
                    pi = float(cluster_count[i]) / float(total_cluster_count)
                    pj = float(cluster_count[j]) / float(total_cluster_count)
                    if pij == 0:
                        Quality[i][j] = 0
                    else:
                        Quality[i][j] = float(pij) * math.log(float(pij) / float((pi * pj)))
                    if pji == 0:
                        Quality[j][i] = 0
                    else:
                        Quality[j][i] = float(pji) * math.log(float(pji) / float((pi * pj)))


            break
        for key, value in Quality.items():
            max_q = max(value.values())


        for key, value in Quality.items():
            for i, j in value.items():
                if j == max_q:
                    index1 = key
                    index2 = i

    # Merging
        if index2 != k:
            inactive_indices.append(index2)
            active_indices.remove(index2)
            active_indices.append(k)
        k += 1





        cluster_count[index1] = cluster_count[index1] + cluster_count[index2]

        for some in active_indices:
            if index1 not in cluster_pair_count:
                cluster_pair_count[index1] = {}
            if some not in cluster_pair_count[index1]:
                cluster_pair_count[index1][some] = 0
            if some not in cluster_pair_count:
                cluster_pair_count[some] = dict()
            if index1 not in cluster_pair_count[some]:
                cluster_pair_count[some][index1] = 0
            if index2 not in cluster_pair_count:
                cluster_pair_count[index2] = {}
            if some not in cluster_pair_count[index2]:
                cluster_pair_count[index2][some] = 0
            if some not in cluster_pair_count:
                cluster_pair_count[some] = dict()
            if index2 not in cluster_pair_count[some]:
                cluster_pair_count[some][index2] = 0
            cluster_pair_count[index1][some] += cluster_pair_count[index2][some]
            cluster_pair_count[some][index1] += cluster_pair_count[some][index2]

        for j in active_indices:
            Quality[j] = {}
            pij = float(cluster_pair_count[index1][j]) / float(total_cluster_pair_count)
            pji = float(cluster_pair_count[j][index1]) / float(total_cluster_pair_count)
            pi = float(cluster_count[index1]) / float(total_cluster_count)
            pj = float(cluster_count[j]) / float(total_cluster_count)
            if pij == 0:
                Quality[index1][j] = 0
            else:
                Quality[index1][j] = float(pij) * math.log(float(pij) / float((pi * pj)))
            if pji == 0:
                Quality[j][index1] = 0
            else:
                Quality[j][index1] = float(pji) * math.log(float(pji) / float((pi * pj)))




        for w in cluster[index2]:
            bit[w] += '0'

        for v in cluster[index1]:
            bit[v] += '1'



        cluster[index1] = cluster[index1] + cluster[index2]

    return cluster, bit



def main():
    start_time = time.time()
    dataset_path = sys.argv[1]
    sentences_list, sorted_word_list = scandirs(dataset_path)
    bigram_model1 = bigram_count(sentences_list)

    sorted_word_dict = dict()
    for i in sorted_word_list:
        key = i[0]
        value = i[1]
        sorted_word_dict[key] = value

    clusters, bit = brown_clusters(sorted_word_dict, bigram_model1)
    with open('results-brown.txt', 'w') as fp:
        fp.write('\n'.join('%s    %s' % x for x in sorted_word_list))

    bititems = []
    for ko, vo in bit.items():
        bititems.append((ko, vo))

    with open('brownclusters.txt', 'w') as fc:
        fc.write('\n'.join('%s    %s' % x for x in bititems))

    print("--- %s seconds ---" % (time.time() - start_time))



if __name__ == '__main__':
    main()