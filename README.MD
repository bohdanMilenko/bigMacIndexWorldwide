# Big Mac Index

## Intro

The Big Mac Index is an index created by The Economist 
 based on the theory of purchasing power parity (PPP). 

The price of a Big Mac reflects a number of local economic factors, 
ranging from the cost of the ingredients to the cost of local production and advertising. 
For more details please reference [here.](https://www.investopedia.com/ask/answers/09/big-mac-index.asp)

## Program Flow

Apllication loads the .csv file with the data about countries, their economics and prices for BigMac in $ values.

Here is an example how does it look like:

<p align="center">
   <img src ="pictures/dataExample.png" width="700">
</p>

Afterwards, there is a static function that is called to calculate BigMac Indexes for each country, rank them and put in the LinkedHashMap.

