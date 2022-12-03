# **Word-Frequency-Analysis**

The following Java program will process and analyze the contents of a literary segment contained in a text file.

## Code Walkthrough
We first take the contents of the alice29.txt file and store it in a String variable. Here I used BufferedReader and StringBuilder to read through each line of the text file, and append each line to my String variable:
![image](https://user-images.githubusercontent.com/56283137/205415443-52a8c399-a39e-4a8d-a7ee-df452cf8f66e.png)
![image](https://user-images.githubusercontent.com/56283137/205415453-dfd62b3c-2d0b-4e28-8f5e-28e1bf62298f.png)

From there, we make a series of pre-processing adjustments to prep it for our analysis. First the program will scan for punctuation (commas, quotations, etc.) and remove them entirely. 
![image](https://user-images.githubusercontent.com/56283137/205415996-6172a544-1dc9-4b01-a31e-dfd847e85a01.png)

Next, we will tokenize the words to be added to an ArrayList, but for future steps, I then converted this ArrayList to an Array:
![image](https://user-images.githubusercontent.com/56283137/205416008-b0b4190f-4ee3-4581-80f4-5b00ab9a4dc2.png)

Before we remove the list of stop words, we will of course need to obtain those stop words from a stopwords.txt file and ultimately tokenize them into a String array as well. This process, with the exception of the method of reading the file into a String (because the Buffered Reader did not work), is mostly the same for Alice:
![image](https://user-images.githubusercontent.com/56283137/205416029-afc748d0-9ebd-45dc-984b-8b97a342d25e.png)

Next, we will take these stopwords and remove them from Alice. We do this by storing the array of stop words into a HashSet of strings. We check to see (one by one) if a word in Alice is NOT also in the StopWords set. If this is the case, we can add them to a new data structure in the form of a string ArrayList (which will then be output in its own text file).
![image](https://user-images.githubusercontent.com/56283137/205416056-e6d18a2f-a9f0-408b-8fee-633642cbfcd0.png)

Now that we have an ArrayList of each and every individual word (including duplicates; that’s important) from the Alice story processed, we are ready to analyze the frequency of each word string and create a HashMap that states how many instances there are of a single word (which will be outputted in its own text file).
![image](https://user-images.githubusercontent.com/56283137/205416118-36aeee28-e454-48b3-bc54-ddd8424eb346.png)

However, it is important that we sort this HashMap by order of frequency (I at least chose to do so by descending order (this will be output in its own text file).
![image](https://user-images.githubusercontent.com/56283137/205416150-c55dea4e-d948-46be-aef1-5b0ad151b93b.png)

The following is an output in a text file of the sorted HashMap, representing each unique word and its frequency.
![image](https://user-images.githubusercontent.com/56283137/205416222-feb99abe-d1de-4f77-81dd-30bbb12fa768.png)

From here, we would finally like to display some statistical results regarding the data and its changes; for there is a great value of data and even data structures themselves.
For this we are interested in 4 questions: 
1). How large was the alice29.txt file?
2). How large was the alice dataset following pre-processing (punctuations, spaces, stopwords)?
3). How many stopwords were there?
4). How many punctuation symbols were present in the original alice29.txt file?
To obtain these values, 1-3 were very simple as we are merely seeking the length() Strings and Arrays, as well as the size() of an ArrayList. For punctuation, however, this a trickier measurement to obtain. This was done through a ‘brute force’ method of checking looping through alice String (before pre-processing) and then checking for every single mentioned punctuation character (the time complexity of such is likely O(n)).
![image](https://user-images.githubusercontent.com/56283137/205416639-a6066024-d7c5-484c-a2ad-c3f8f8c86faa.png)
![image](https://user-images.githubusercontent.com/56283137/205416644-96ff68be-e70f-4ffb-a0c5-e07126973bd8.png)
