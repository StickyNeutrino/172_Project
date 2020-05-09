import requests
from bs4 import BeautifulSoup
import re
import sys

from pymongo import MongoClient
client = MongoClient('mongodb://root:example@mongo')
db = client.tweets

def getTitle(url):

    r = requests.get(url)

    html_content = r.text

    soup = BeautifulSoup(html_content, 'html.parser')

    return soup.title

while 1:
    all = db.texts.find({})
    for tweet in all:
        urls = tweet['tweeter']['entities']['urls']
        if 'pageTitle' in tweet:
            continue

        if len(urls) > 0:
            url = urls[0]['url']
            title = getTitle(url)

            db.texts.update_one({'_id': tweet['_id']},{'$set':{'pageTitle':title.string}})
            print(title.string)
