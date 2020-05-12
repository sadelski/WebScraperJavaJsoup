package webscrape;

import java.io.IOException;
import java.util.Scanner;
import org.jsoup.Jsoup; 
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class scraper {

	public static void main(String[] args) throws IOException {
	
		String url ="https://courses.students.ubc.ca/cs/courseschedule?pname=subjarea&tname=subj-all-departments";
		int num = 0;
		int run = 0;
		int select = 0;
		int subnum = 0;
	    String[] urltemp = new String[264];
	    Document document;
	    String FacDefault = "";
	    
		Scanner sc = new Scanner(System.in);
		
		while(run == 0)
		{
			
        try {
            
        	if(select == 0) {
           
            System.out.println("Enter Faculty or School");
        	FacDefault = sc.nextLine().trim();
            System.out.println("subject number: "+ "subject code: " + "subject title: ");
        	document = Jsoup.connect(url).get();
        	
             }
        	
        	else {
        		document = Jsoup.connect(urltemp[subnum]).get();
        	 }
        	
            for (Element row : document.select("table.sortable.table.table-striped tr")) {
                
            	if (row.select("td:nth-of-type(1)").text().equals("")) {
                    continue;
                    }
            	
                else {
                   
                  String SubjectCode =row.select("td:nth-of-type(1)").text();
                  String SubjectTitle = row.select("td:nth-of-type(2)").text();
                  
                  if(select == 1)
                  {
                  System.out.println(SubjectCode);
                  System.out.println(SubjectTitle);
                  }
                  
                  else{
                	   
                   String FacultyorSchool = row.select("td:nth-of-type(3)").text().trim();
                   Elements link = row.select("td a");
                   urltemp[num] = "https://courses.students.ubc.ca" + link.attr("href");
                   num++;
                    
                    if(FacultyorSchool.equalsIgnoreCase(FacDefault)){
                	  System.out.println(num + " " + SubjectCode + " " + SubjectTitle);
                	  }
                  
                    }
                     }
            }
           
            
        }
        
        catch (Exception ex) {
            ex.printStackTrace();
        }
        
        System.out.println("do you wanna look at courses for this faculty enter 1 to do so and enter zero to look at diffrent faculty or exit press 2");
        select = sc.nextInt();
        
        if(select == 1)
        {
        System.out.println("Enter number of subject to print courses for it");
        subnum = sc.nextInt();
        }
       
        
		}
        sc.close();
		
		}
	}

