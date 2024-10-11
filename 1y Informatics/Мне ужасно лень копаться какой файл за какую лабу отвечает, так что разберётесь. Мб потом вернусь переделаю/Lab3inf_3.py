with open('schedule.json', encoding='UTF8') as sched:
    a = sched.readlines()
# print(a)
for x in range(1):
    a[x] = a[x].replace('{', '<?xml version="1.0" encoding="utf-8"?>', 1)
    print(a[x])
print('<schedule>')
for i in range(1, len(a)):
    a[i] = a[i].replace(':', '')
    a[i] = a[i].replace('"', '<', 1)
    a[i] = a[i].replace('"', '>', 1)
    a[i] = a[i].replace('"', '', 1)
    if i == 2:
        a[i] = a[i].replace('"', ' </time_start>', 1)
    if i == 3:
        a[i] = a[i].replace('"', ' </time_end>', 1)
    if i == 6:
        a[i] = a[i].replace('\n', ' </number>\n', 1)
    if i == 7:
        a[i] = a[i].replace('\n', ' </building_address>\n', 1)
    if i == 10:
        a[i] = a[i].replace('"', ' </type>', 1)
    if i == 11:
        a[i] = a[i].replace('"', ' </name>', 1)
    if i == 12:
        a[i] = a[i].replace('"', ' </format>', 1)
    if i == 14:
        a[i] = a[i].replace('"', ' </teacher>', 1)
    a[i] = a[i].replace('1000', '10:00')
    a[i] = a[i].replace('1130', '11:30')
    a[i] = a[i].replace('{', '', 1)
    a[i] = a[i].replace('}', '</time>', 1)
    a[i] = a[i].replace(',', '', 1)
    if i > 6:
        a[i] = a[i].replace('</time>', '</room>', 1)
    if i > 10:
        a[i] = a[i].replace('</room>', '</lesson>', 1)
    if i > 14:
        a[i] = a[i].replace('</lesson>', '</schedule>', 1)
    print(a[i])