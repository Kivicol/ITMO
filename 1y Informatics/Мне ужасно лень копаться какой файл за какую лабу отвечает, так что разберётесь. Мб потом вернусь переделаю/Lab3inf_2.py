import re

stroka = str(input())
nums = re.findall('\d{1,}', stroka)
print('Закодированный результат:')
numbe = []
for x in range(len(nums)):
    numbe.append(str((int(nums[x])**2)*3+5))
    stroka = stroka.replace(nums[x], numbe[x])
print(stroka)
