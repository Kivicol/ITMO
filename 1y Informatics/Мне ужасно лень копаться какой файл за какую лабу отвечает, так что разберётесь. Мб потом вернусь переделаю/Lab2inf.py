print("Введите код для проверки")
a = str(input())
ne_podh = ["2", "3", "4", "5", "6", "7", "8", "9"]
flag = True

for x in range(0, 7):
    if len(a) != 7 or not (a.isdigit()) or ne_podh[x] in a:
        print("Введите нормальный код, пож")
        flag = False
        break

if flag:
    r1 = int(a[0])
    r2 = int(a[1])
    i1 = int(a[2])
    r3 = int(a[3])
    i2 = int(a[4])
    i3 = int(a[5])
    i4 = int(a[6])
    s1 = r1 ^ i1 ^ i2 ^ i4
    s2 = r2 ^ i1 ^ i3 ^ i4
    s3 = r3 ^ i2 ^ i3 ^ i4
    wrd = list()
    for x in range(0, 7):
        wrd.append(a[x])
    if s1 == 0:
        if s2 == 0:
            if s3 == 0:
                print("Великолепный код, замечательный даже")
            else:
                print("Ошибка в 4-ом бите, правильное написание кода:")
                wrd[3] = str(1 - int(wrd[3]))
                print(''.join(wrd))
        else:
            if s3 == 0:
                print("Ошибка в 2-ом бите, правильное написание кода:")
                wrd[1] = str(1 - int(wrd[1]))
                print(''.join(wrd))
            else:
                print("Ошибка в 6-ом бите, правильное написание кода:")
                wrd[5] = str(1 - int(wrd[5]))
                print(''.join(wrd))
    else:
        if s2 == 0:
            if s3 == 0:
                print("Ошибка в 1-ом бите, правильное написание кода:")
                wrd[0] = str(1 - int(wrd[0]))
                print(''.join(wrd))
            else:
                print("Ошибка в 5-ом бите, правильное написание кода:")
                wrd[4] = str(1 - int(wrd[4]))
                print(''.join(wrd))
        else:
            if s3 == 0:
                print("Ошибка в 3-ом бите, правильное написание кода:")
                wrd[2] = str(1 - int(wrd[2]))
                print(''.join(wrd))
            else:
                print("Ошибка в 7-ом бите, правильное написание кода:")
                wrd[6] = str(1 - int(wrd[6]))
                print(''.join(wrd))
