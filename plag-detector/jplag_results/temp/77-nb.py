import os, glob
import re
import math
import sys

def read_text(file):
    with open(file) as f:
        mylist = f.read().splitlines()
        for i in range(len(mylist)):
            mylist[i] = re.sub(r'[^\w]', ' ', mylist[i])
            mylist[i] = " ".join(mylist[i].split())
    return mylist

def scandirs(path):
    testdict = dict()
    traindict = dict()
    for currentFile in glob.glob(os.path.join(path, '*')):
        for j in glob.glob(os.path.join(currentFile, '*')):
            for f in glob.glob(os.path.join(j, '*')):
                a = f.split('/')
                if a[-3] == 'test':
                    key = a[-2]
                    value = path+'/'+a[-3]+'/'+a[-2]+'/' +a[-1]
                    testdict.setdefault(key, []).append(value)
                elif a[-3] == 'train':
                    key = a[-2]
                    value = path + '/' + a[-3] + '/' + a[-2] + '/' + a[-1]
                    traindict.setdefault(key, []).append(value)
    return testdict, traindict

def get_count_all_words_in_negative(negative_list):
    mydict = dict()
    sum = 0
    for i in negative_list:
        for j in i:
            for p in j.split(' '):
                key = p
                v = p
                mydict.setdefault(key, []).append(v)
    for key, value in mydict.items():
        v = len(value)
        mydict[key] = v
    for v in mydict.values():
        sum += v

    return mydict, sum

def get_count_all_words_in_positive(positive_list):
    mydict = dict()
    sum = 0
    for i in positive_list:
        for j in i:
            for p in j.split(' '):
                key = p
                v = p
                mydict.setdefault(key, []).append(v)
    for key, value in mydict.items():
        v = len(value)
        mydict[key] = v
    for v in mydict.values():
        sum += v
    return mydict, sum

def get_all_lists_train(traindict):
    positive = []
    negative = []
    for key, value in traindict.items():
        if key == 'neg':
            for v in traindict['neg']:
                negative.append(read_text(v))
        else:
            for v in traindict['pos']:
                positive.append(read_text(v))

    return negative, positive

def get_all_lists_test(testdict):
    arr = dict()
    for key, value in testdict.items():
        for v in value:
            k = v.split('/')[-1]
            p = read_text(v)
            arr[k] = p

    return arr

def sum_probability_all_words_in_negative(negative_dict, total_negative_count, total_word_count):
    mydict = dict()
    sum = 0
    for key, value in negative_dict.items():
        v = math.log((value + 1)/(total_negative_count + total_word_count), 2.0)
        mydict[key] = v
    for value in mydict.values():
        sum += value
    return sum

def sum_probability_all_words_in_positive(positive_dict, total_positive_count, total_word_count):
    mydict = dict()
    sum = 0
    for key, value in positive_dict.items():
        v = math.log((value + 1)/(total_positive_count + total_word_count), 2.0)
        mydict[key] = v
    for value in mydict.values():
        sum += value
    return sum

def choose_document_class(document, p_positive_train, p_negative_train, negative_word_dict, positive_word_dict, total_negative_count, total_positive_count, total_word_count):
    mylist = []
    s_pos = 0
    s_neg = 0
    for i in document:
        for j in i.split(' '):
            mylist.append(j)
    for i in mylist:
        if i in negative_word_dict:
            neg = math.log((negative_word_dict[i] + 1) / (total_negative_count + total_word_count), 2.0)
        else:
            neg = math.log((0 + 1) / (total_negative_count + total_word_count), 2.0)
        if i in positive_word_dict:
            pos = math.log((positive_word_dict[i] + 1) / (total_positive_count + total_word_count), 2.0)
        else:
            pos = math.log((0 + 1) / (total_positive_count + total_word_count), 2.0)

        s_pos += pos
        s_neg += neg

    c_pos = p_positive_train + s_pos
    c_neg = p_negative_train + s_neg

    if c_pos > c_neg:
        return 'pos'
    else:
        return 'neg'


test, train = scandirs(sys.argv[1])

p_positive_train = math.log(len(train['pos'])/(len(train['pos']) + len(train['neg'])), 2.0)
p_negative_train = math.log(len(train['neg'])/(len(train['pos']) + len(train['neg'])), 2.0)
negative_list, positive_list = get_all_lists_train(train)
negative_word_dict, total_negative_word_count = get_count_all_words_in_negative(negative_list)
positive_word_dict, total_positive_word_count = get_count_all_words_in_positive(positive_list)
total_word_count = total_positive_word_count + total_negative_word_count
prob_negative = sum_probability_all_words_in_negative(negative_word_dict, total_negative_word_count, total_word_count)
prob_positive = sum_probability_all_words_in_positive(positive_word_dict, total_positive_word_count, total_word_count)
positive_given = []
negative_given = []
positive_found = []
negative_found = []
total_test_documents_dict = get_all_lists_test(test)
for i, v in total_test_documents_dict.items():
    k = choose_document_class(v, p_positive_train, p_negative_train, negative_word_dict, positive_word_dict,
                              total_negative_word_count, total_positive_word_count, total_word_count)
    if k == 'pos':
        positive_found.append(i)
    else:
        negative_found.append(i)

for i, v in test.items():
    if i == 'pos':
        for j in v:
            positive_given.append(j.split('/')[-1])
    else:
        for j in v:
            negative_given.append(j.split('/')[-1])


true_positive = 0
true_negative = 0
false_positive = 0
false_negative = 0

for i in positive_found:
    if i in positive_given:
        true_positive += 1
    elif i in negative_given:
        false_positive += 1


for i in negative_found:
    if i in negative_given:
        true_negative += 1
    elif i in positive_given:
        false_negative += 1

precision = true_positive/(true_positive+false_positive)
recall = true_positive/(true_positive+false_negative)

print('Precision value of pos is : ', precision)
print('Recall value  of pos is : ', recall)

F1_pos = ((1+1)*precision*recall)/(precision + recall)
print('F1 value of pos is : ', F1_pos)



precision = true_negative / (true_negative + false_negative)
recall = true_negative / (true_negative + false_positive)

print('Precision value of neg is : ', precision)
print('Recall value  of neg is : ', recall)

F1_neg = ((1 + 1) * precision * recall) / (precision + recall)
print('F1 value of neg is : ', F1_neg)

print("Average F1 value is : ", (F1_neg+F1_pos)/2)