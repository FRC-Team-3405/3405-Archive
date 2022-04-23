import csv
import re
from os import listdir, getcwd
from os.path import isfile, join
files = [f for f in listdir(getcwd()) if isfile(join(getcwd(), f))]

for csvfile in files:
    if re.match(".*path$", csvfile) != None:
        readcsvfileobj = open(csvfile, "r", newline='')
        writecsvfileobj = open("convertedpaths/" + csvfile, "w+", newline='')
        reader = csv.reader(readcsvfileobj, delimiter=',')
        writer = csv.writer(writecsvfileobj, delimiter = ',')
        for i, row in enumerate(reader):
            if i == 0:
                writer.writerow(row)
            else:
                temprow = []
                for ix, x in enumerate(row):
                    if ix < 4:
                        temprow.append(str(float(x)*0.3048))
                    else:
                        temprow.append(x)
                writer.writerow(temprow)
                    

                