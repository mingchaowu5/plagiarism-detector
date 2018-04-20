import sys
file_path = sys.argv[1]
mydict = {}
with open(file_path, 'r') as f:
    for j in f.readlines():
        key = j.split(' ')[0]
        value = j.split(' ')[1]
        mydict[key] = value


arr = ['the', 'army', 'received', 'famous']

with open('q1_d.txt', 'w') as fw:
    p = []
    for i in arr:
        val = mydict[i]
        l = len(list(val))
        for k, v in mydict.items():
            if len(v) == l:
                p.append((k, v))
        fw.write('\nRESULTS FOR {} are : \n'.format(i))
        fw.write('\n\n')
        fw.write('\n'.join('%s %s' % x for x in p[:10]))
        fw.write('\n\n')
        p = []

fw.close()

