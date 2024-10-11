import re

a = 'dhbawol31dxX<{Pxmao;1123'
b = 'gyadofw160t841341[4-02=1dji'
c = 'bdoadyugwX<{PX<{PX<{Pdwioyfadwa1'
d = 'qwertyuiop[]asdfghjkl;zxcvbnm,./'
e = 'dwabpdoX<{Ppauiweak73156X<{PX<{Phoie12t'
wariant = [a, b, c, d, e]
otv = ''
for x in range(5):
    m = re.findall(r'X<\{P', wariant[x])
    print(f'Кол-во искомых смаликов: {len(m)}.')