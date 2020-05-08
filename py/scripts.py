import requests
from bs4 import BeautifulSoup

def getTitle(url):

    r = requests.get(url)

    html_content = r.text

    soup = BeautifulSoup(html_content, 'html.parser')

    print(soup.title)

getTitle('http://ilearn.ucr.edu')