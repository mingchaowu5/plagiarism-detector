import os, glob
import sys
import re
import math
import tensorflow as tf
import numpy as np

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

    return c_pos, c_neg

def get_partial_data(train_dict, posdict, negdict):
    mylist = []
    for k, v in train_dict.items():

        for i in v:
            str1 = i.split('/')[-1]
            if k == 'pos':
                mylist.append([1, ''.join(re.findall('\d+', str1)), posdict[str1], negdict[str1]])
            else:
                mylist.append([0, ''.join(re.findall('\d+', str1)), posdict[str1], negdict[str1]])

    return mylist

test, train = scandirs(sys.argv[1])
p_positive_train = math.log(len(train['pos'])/(len(train['pos']) + len(train['neg'])), 2.0)
p_negative_train = math.log(len(train['neg'])/(len(train['pos']) + len(train['neg'])), 2.0)
negative_list, positive_list = get_all_lists_train(train)
negative_word_dict, total_negative_word_count = get_count_all_words_in_negative(negative_list)
positive_word_dict, total_positive_word_count = get_count_all_words_in_positive(positive_list)
total_word_count = total_positive_word_count + total_negative_word_count
prob_negative = sum_probability_all_words_in_negative(negative_word_dict, total_negative_word_count, total_word_count)
prob_positive = sum_probability_all_words_in_positive(positive_word_dict, total_positive_word_count, total_word_count)
positive_found = []
negative_found = []
total_train_documents_dict = get_all_lists_test(train)
posdict = dict()
negdict = dict()
for i, v in total_train_documents_dict.items():
    p, n = choose_document_class(v, p_positive_train, p_negative_train, negative_word_dict, positive_word_dict,
                              total_negative_word_count, total_positive_word_count, total_word_count)
    posdict[i] = p
    negdict[i] = n
x_data = np.array(get_partial_data(train, posdict, negdict))
data = get_partial_data(train, posdict, negdict)
y = []
for i in data:
    y.append([0, 1])
y_data = np.array(y)

p_positive_train = math.log(len(test['pos'])/(len(test['pos']) + len(test['neg'])), 2.0)
p_negative_train = math.log(len(test['neg'])/(len(test['pos']) + len(test['neg'])), 2.0)
negative_list, positive_list = get_all_lists_train(test)
negative_word_dict, total_negative_word_count = get_count_all_words_in_negative(negative_list)
positive_word_dict, total_positive_word_count = get_count_all_words_in_positive(positive_list)
total_word_count = total_positive_word_count + total_negative_word_count
prob_negative = sum_probability_all_words_in_negative(negative_word_dict, total_negative_word_count, total_word_count)
prob_positive = sum_probability_all_words_in_positive(positive_word_dict, total_positive_word_count, total_word_count)
positive_found = []
negative_found = []
total_train_documents_dict = get_all_lists_test(test)
posdict = dict()
negdict = dict()
for i, v in total_train_documents_dict.items():
    p, n = choose_document_class(v, p_positive_train, p_negative_train, negative_word_dict, positive_word_dict,
                              total_negative_word_count, total_positive_word_count, total_word_count)
    posdict[i] = p
    negdict[i] = n
x_data_test = np.array(get_partial_data(test, posdict, negdict))
data = get_partial_data(test, posdict, negdict)
y = []
for i in data:
    y.append([i[0]])
y_data_test = np.array(y)

def first_run_tf(x_data, y_data, x_data_test, y_data_test):
    n_input = 4
    n_hidden = 50
    n_output = 2

    X = tf.placeholder(tf.float32)
    Y = tf.placeholder(tf.float32)

    W1 = tf.Variable(tf.random_uniform([n_input, n_hidden], -1.0, 1.0))
    W2 = tf.Variable(tf.random_uniform([n_hidden, n_output], -1.0, 1.0))

    b1 = tf.Variable(tf.zeros([n_hidden]), name='Bias1')
    b2 = tf.Variable(tf.zeros([n_output]), name='Bias2')

    L2 = tf.sigmoid(tf.matmul(X, W1) + b1)
    hy = tf.sigmoid(tf.matmul(L2, W2) + b2)

    cost = tf.reduce_mean(-Y*tf.log(hy) - (1-Y)*tf.log(1-hy))

    optimizer = tf.train.GradientDescentOptimizer(learning_rate=0.001).minimize(cost)

    init = tf.global_variables_initializer()

    with tf.Session() as session:
        session.run(init)

        for step in range(20):
            session.run(optimizer, feed_dict={X: x_data, Y: y_data})


            if step % 1000 == 0:
                print('Cost of first run is : ', session.run(cost, feed_dict={X: x_data, Y: y_data}))

        prediction = tf.argmax(y, 1)
        mypred = prediction.eval(feed_dict={X: x_data_test, Y: y_data_test})
        confusion = tf.contrib.metrics.confusion_matrix(y_data_test, mypred)
        with tf.Session():
            con = tf.Tensor.eval(confusion, feed_dict=None, session=None)
        true_positive = con[0][0]
        false_negative = con[0][1]
        false_positive = con[1][0]
        true_negative = con[1][1]

        precision = true_positive / (true_positive + false_positive)
        recall = true_positive / (true_positive + false_negative)

        print('Precision value for first run is : ', precision)
        print('Recall value for first run is : ', recall)

        F1 = ((1 + 1) * precision * recall) / (precision + recall)
        print('F1 value for first run is : ', F1)


def second_run_tf(x_data, y_data, x_data_test, y_data_test):

    n_input = 4
    n_hidden = 50
    n_output = 2

    X = tf.placeholder(tf.float32)
    Y = tf.placeholder(tf.float32)

    W1 = tf.Variable(tf.random_uniform([n_input, n_hidden], -1.0, 1.0))
    W2 = tf.Variable(tf.random_uniform([n_hidden, n_output], -1.0, 1.0))

    b1 = tf.Variable(tf.zeros([n_hidden]), name='Bias1')
    b2 = tf.Variable(tf.zeros([n_output]), name='Bias2')

    L2 = tf.sigmoid(tf.matmul(X, W1) + b1)
    hy = tf.sigmoid(tf.matmul(L2, W2) + b2)

    cost = tf.reduce_mean(-Y * tf.log(hy) - (1-Y) * tf.log(1-hy))

    optimizer = tf.train.ProximalAdagradOptimizer(learning_rate=0.00001).minimize(cost)

    init = tf.global_variables_initializer()

    with tf.Session() as session:
        session.run(init)

        for step in range(20):
            session.run(optimizer, feed_dict={X: x_data, Y: y_data})

            if step % 1000 == 0:
                print('Cost of second run is : ', session.run(cost, feed_dict={X: x_data, Y: y_data}))

        prediction = tf.argmax(y, 1)
        mypred = prediction.eval(feed_dict={X: x_data_test, Y: y_data_test})
        confusion = tf.contrib.metrics.confusion_matrix(y_data_test, mypred)
        with tf.Session():
            con = tf.Tensor.eval(confusion, feed_dict=None, session=None)
        true_positive = con[0][0]
        false_negative = con[0][1]
        false_positive = con[1][0]
        true_negative = con[1][1]

        precision = true_positive / (true_positive + false_positive)
        recall = true_positive / (true_positive + false_negative)

        print('Precision value for second run is : ', precision)
        print('Recall value for second run is : ', recall)

        F1 = ((1 + 1) * precision * recall) / (precision + recall)
        print('F1 value for second run  is : ', F1)


def third_run_tf(x_data, y_data, x_data_test, y_data_test):
    x1 = np.delete(x_data, np.s_[-2:], axis=1)
    y1 = y_data
    x2 = np.delete(x_data_test, np.s_[-2:], axis=1)
    y2 = y_data_test
    n_input = 2
    n_hidden = 32
    n_output = 2

    X = tf.placeholder(tf.float32)
    Y = tf.placeholder(tf.float32)

    W1 = tf.Variable(tf.random_uniform([n_input, n_hidden], -1.0, 1.0))
    W2 = tf.Variable(tf.random_uniform([n_hidden, n_output], -1.0, 1.0))

    b1 = tf.Variable(tf.zeros([n_hidden]), name='Bias1')
    b2 = tf.Variable(tf.zeros([n_output]), name='Bias2')

    L2 = tf.sigmoid(tf.matmul(X, W1) + b1)
    hy = tf.sigmoid(tf.matmul(L2, W2) + b2)

    cost = tf.reduce_mean(-Y * tf.log(hy) - (1-Y) * tf.log(1-hy))

    optimizer = tf.train.ProximalAdagradOptimizer(learning_rate=0.00001).minimize(cost)

    init = tf.global_variables_initializer()

    with tf.Session() as session:
        session.run(init)

        for step in range(20):
            session.run(optimizer, feed_dict={X: x1, Y: y1})

            if step % 1000 == 0:
                print('Cost of third run is : ', session.run(cost, feed_dict={X: x1, Y: y1}))

        prediction = tf.argmax(y, 1)
        mypred = prediction.eval(feed_dict={X: x2, Y: y2})
        confusion = tf.contrib.metrics.confusion_matrix(y2, mypred)
        with tf.Session():
            con = tf.Tensor.eval(confusion, feed_dict=None, session=None)
        true_positive = con[0][0]
        false_negative = con[0][1]
        false_positive = con[1][0]
        true_negative = con[1][1]

        precision = true_positive / (true_positive + false_positive)
        recall = true_positive / (true_positive + false_negative)

        print('Precision value for third run is : ', precision)
        print('Recall value for third run is : ', recall)

        F1 = ((1 + 1) * precision * recall) / (precision + recall)
        print('F1 value for third run is : ', F1)


def fourth_run_tf(x_data, y_data, x_data_test, y_data_test):
    x1 = np.delete(x_data, np.s_[-1:], axis=1)
    y1 = y_data
    x2 = np.delete(x_data_test, np.s_[-1:], axis=1)
    y2 = y_data_test
    n_input = 3
    n_hidden = 32
    n_output = 2

    X = tf.placeholder(tf.float32)
    Y = tf.placeholder(tf.float32)

    W1 = tf.Variable(tf.random_uniform([n_input, n_hidden], -1.0, 1.0))
    W2 = tf.Variable(tf.random_uniform([n_hidden, n_output], -1.0, 1.0))

    b1 = tf.Variable(tf.zeros([n_hidden]), name='Bias1')
    b2 = tf.Variable(tf.zeros([n_output]), name='Bias2')

    L2 = tf.sigmoid(tf.matmul(X, W1) + b1)
    hy = tf.sigmoid(tf.matmul(L2, W2) + b2)

    cost = tf.reduce_mean(-Y * tf.log(hy) - (1-Y) * tf.log(1-hy))

    optimizer = tf.train.AdamOptimizer(learning_rate=0.0001).minimize(cost)

    init = tf.global_variables_initializer()

    with tf.Session() as session:
        session.run(init)

        for step in range(20):
            session.run(optimizer, feed_dict={X: x1, Y: y1})

            if step % 1000 == 0:
                print('Cost for fourth run is :', session.run(cost, feed_dict={X: x1, Y: y1}))

        prediction = tf.argmax(y, 1)
        mypred = prediction.eval(feed_dict={X: x2, Y: y2})
        confusion = tf.contrib.metrics.confusion_matrix(y2, mypred)
        with tf.Session():
            con = tf.Tensor.eval(confusion, feed_dict=None, session=None)
        true_positive = con[0][0]
        false_negative = con[0][1]
        false_positive = con[1][0]
        true_negative = con[1][1]

        precision = true_positive / (true_positive + false_positive)
        recall = true_positive / (true_positive + false_negative)

        print('Precision value for fourth run is : ', precision)
        print('Recall value for fourth run is : ', recall)

        F1 = ((1 + 1) * precision * recall) / (precision + recall)
        print('F1 value for fourth run is : ', F1)

first_run_tf(x_data, y_data, x_data_test, y_data_test)
second_run_tf(x_data, y_data, x_data_test, y_data_test)
third_run_tf(x_data, y_data, x_data_test, y_data_test)
fourth_run_tf(x_data, y_data, x_data_test, y_data_test)



