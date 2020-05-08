# import time
# import pymongo
from pymongo import MongoClient
import requests 
from bs4 import BeautifulSoup

def getTitle(url):

    r = requests.get(url)

    html_content = r.text

    soup = BeautifulSoup(html_content, 'html.parser')

    print(soup.title)


getTitle('http://ilearn.ucr.edu')

client = MongoClient('localhost', 27017)
db = client['test']
collection = db.tester
result = collection.find({})
# lister = list(result)
print(list(result))
# print(result)
# pprint.pprint(posts.find_one())
