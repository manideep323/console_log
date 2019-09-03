package com.fpix.console_log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

public class App 
{
    
    private static WebDriver driver = null;
    static FileWriter fw;
    static String fileName = "/home/manideepg/Documents/keywords_console_log.txt";    
    private static void readingPropsFile() throws IOException {
        FileReader reader=new FileReader("config.properties");  
        Properties p=new Properties(); 
        p.load(reader);
        System.out.println(p.getProperty("urlsFile"));  
    	System.exit(0);
	}
    
    public static void main(String[] args) throws IOException {
    	readingPropsFile();
    	   try {
    		   File file = new File(fileName);
    		   if(!file.exists()) file.createNewFile();
    		   fw = new FileWriter(file);
		
        String pages[] = {"file:///run/user/1000/gvfs/smb-share:server=192.168.1.65,share=protected/Math Keywords/m121018/index.html",
        		"file:///run/user/1000/gvfs/smb-share:server=192.168.1.65,share=protected/Math Keywords/m121372/index.html"}; 
        System.setProperty("webdriver.chrome.driver", "/home/manideepg/Downloads/Untitled Folder/chromedriver");
        driver = new ChromeDriver();
        
        int count = 1;
        for (String page : pages) {
			count++;
			if (count == 50) {
		        Thread.sleep(10000);
		        count = 1;
			}
			driver.get(page);
			LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
			
	        for (LogEntry entry : logEntries) {
	        	//fw.append(page +" "+ entry.getMessage()+"\n");
	        	 fw.write(page +"\t"+ entry.getMessage()+"\n");    
	               
	            //System.out.println(page +" "+ entry.getMessage());
	            //do something useful with the data
	        }
	        

	      
        }
        fw.close();
        
    	   } catch (Exception e) {
   			e.printStackTrace();
   		}  
//driver.quit();

    }
	}
