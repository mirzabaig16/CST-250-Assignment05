package edu.ru.assignment05;


import java.io.File;
import java.util.Scanner;
import java.util.*;
import java.io.FileWriter;	// Import the FileWriter class
import java.io.IOException;
 import java.util.Map.Entry;
 import java.util.stream.Collectors;

public class MainClass
{
  public static void main (String[]args)
  {

    String[]words;
    Set <String> uniqueWords = new HashSet <String>();
    Map<String, Integer> m1 = new HashMap();
    try
    {
      File myFile = new File ("C:\\Users\\Tayyab\\assignment05\\CST-250-Assignment05\\Assignment05\\src\\main\\java\\edu\\ru\\assignment05\\Happy.txt");
      Scanner scanner = new Scanner (myFile);
        int c= 0;
      while (scanner.hasNextLine ())
	{
	  //words = str1.split("[\\W]+"); toLowerCase().split("[-.,:;?!~()\\s]+")
	  words =  scanner.nextLine().toLowerCase ().split ("[-.,:;(?)!~\\s]+");
	  for (int i = 0; i < words.length; i++)
	    {
	       
	        if(!words[i].isEmpty()){
	      uniqueWords.add (words[i]);
	      // Count the number of times word appear
	      if (m1.get(words[i]) == null) {
                        m1.put(words[i], 1);
                    } else {
                        int newValue = Integer.valueOf(String.valueOf(m1.get(words[i])));
                        newValue++;
                        m1.put(words[i], newValue);
                    }
	      //
	        }
	    }
	}

    }
    catch (Exception ex)
    {
      System.out.println ("Error Occured");
      System.out.println ("Error Message : " + ex.getMessage ());
      ex.printStackTrace ();
    }
   

// number of unique words
    System.out.println ("Unique Word(s) : "+uniqueWords.size ());
    // finding the number of occurence of word
    String find = "happy";
  System.out.println(find + " : "+GetUniqueWordCount(find , m1));
  // sorting the words according to occurence (Bonus work)
  SortedForm(m1);
 
  }

  public static int GetUniqueWordCount (String word, Map<String, Integer> m1)
  {
      int count = 0;
   
   for (String objectName : m1.keySet()) {
       if(objectName.toString().equalsIgnoreCase(word.toLowerCase()))
       {
          count = Integer.parseInt(m1.get(objectName).toString());
           break;
       }
 }
 return count;
  }
  
  public static Map<String,Integer> sortMap(Map map,boolean order)  
{  
//convert HashMap into List   
List<Entry<String, Integer>> list = new LinkedList<Entry<String, Integer>>(map.entrySet());  
//sorting the list elements  
Collections.sort(list, new Comparator<Entry<String, Integer>>()   
{  
public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2)   
{  
if (order)   
{  
//compare two object and return an integer  
return o1.getValue().compareTo(o2.getValue());}   
else   
{  
return o2.getValue().compareTo(o1.getValue());  
}  
}  
});  
//prints the sorted HashMap  
Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();  
for (Entry<String, Integer> entry : list)   
{  
sortedMap.put(entry.getKey(), entry.getValue());  
}  
return sortedMap;  

  }
 
  public static void SortedForm(Map m1){
  //Map<String, Integer> unsort = new TreeMap<String, Integer>(m1);
  Map<String,Integer> sortedMap = sortMap(m1,false);

    //for (Object key : sortedMap.keySet()) {
      //     System.out.println("Word: " + key + "\tCounts: " + sortedMap.get(key));
     //   }
  ///
   try {
       String fileName = "C:\\Users\\Tayyab\\assignment05\\CST-250-Assignment05\\Assignment05\\src\\main\\java\\edu\\ru\\assignment05\\sorted_words.txt";
      File myObj = new File(fileName);
      if (myObj.createNewFile()) {
        System.out.println("File created: " + myObj.getName());
      } else {
        System.out.println("File already exists.");
      }
      
       FileWriter myWriter = new FileWriter(fileName);
       	for (String key : sortedMap.keySet()) { 
           //myWriter.write(key +" : "+ sortedMap.get(key));
           myWriter.write("Word: " + key + "\tCounts: " + sortedMap.get(key));
           //myWriter.write("\n");
           myWriter.write(System.getProperty( "line.separator" ));
      }
      
      myWriter.close();
      System.out.println("Successfully wrote to the file.");
      
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }
}
