package CMD_Package;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Terminal {
	
	
	
	private String path = new String("C:/simulator/");
	
	
	
	
	
	
	
	
	/**
	 * This method changes the current directory into another one
	 * and return the new one
	 * @param path
	 */
	public void changeDir(String path) {
		
		if (path.charAt(path.length()-1) != '/') {
			path = path + "/";
		}
		this.path = path;
	}
	
	
	
	
	/**
	 * This function make the directory returns back 
	 * to the previous folder
	 */
	public void goBack() {
		
		int slash = 0;
		for (int i = path.length()-2; i > 1; i--) {
			
			if (path.charAt(i) == '/') {
				slash = i;
				break;
			}
		}
		
		if (slash - 2 >= 0) {
			
			path = path.substring(0, slash+1);
		}	
	}
	
	
	
	
	
	
	
	/**
	 * This function lists all the folders and files exist
	 * in a specific directory
	 * @return
	 */
	public String[] list() {
		
		File file = new File(path);
		return file.list();
	}
	
	
	
	
	

	/**
	 * This function copies a file into another one
	 * it creates fileInputStream and fileOutputStream objects
	 * it reads from the source file as an input integer and 
	 * stores it into the destination file
	 * then it closes both two files
	 * @param source
	 * @param destination
	 */
	public void copy(String source, String destination) {

		
		try {
			
	        FileInputStream fis = new FileInputStream(source); 
	
	        FileOutputStream fos = new FileOutputStream(destination); 
	  
	        int b; 
	        while  ((b = fis.read()) != -1) {	
	            fos.write(b); 
	        }
	  
	        fis.close(); 
	        fos.close();
			
		} catch (IOException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	
	/**
	 * This function prints a content of a file or more 
	 * @param catFile
	 */
	public void concatenate(String catFile) {
		
		File file = new File(catFile);
		
		try {
			
			@SuppressWarnings("resource")
			Scanner scan = new Scanner(file);
			
			while(scan.hasNextLine()) {
				System.out.println(scan.nextLine());
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	

	
	/**
	 * This function echoes the user input string with spacing
	 * @param catString
	 */
	public void echoCat(String catString) {
		
		for (int i = 0; i < catString.length(); i++) {
			System.out.print(' ');
		}
		System.out.println(catString);
		
	}
	
	
	
	
	
	
	/**
	 * This function echoes the user input string
	 * @param catString
	 */
	public void echoMore(String catString) {
		System.out.println(catString);
		
	}
	
	
	
	
	
	
	/**
	 * This function navigates onto a file and displays
	 * five lines periodically
	 * it opens the file and allows it to be read
	 * then it prints five lines from the file
	 * and then asks the user if displaying another five lines
	 * @param moreFile
	 */
	public void more(String moreFile) {
		
		File file = new File(moreFile);
		@SuppressWarnings("resource")
		Scanner scann = new Scanner(System.in);
		
		try {
			
			@SuppressWarnings("resource")
			Scanner scan = new Scanner(file);
			
			while(scan.hasNextLine()) {
				
				int i = 0;
				for (i = 0; i < 5; i++) {
					
					if (scan.hasNextLine()) {
						System.out.println(scan.nextLine());
					}
					else {
						break;
					}
				}
				
				int cont = scann.nextInt();
				
				if (cont == 1) {
					i = 0;
				}
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	/**
	 * This function takes a path then it creates
	 * a folder inside it
	 * @param folderName
	 */
	public void makeDir_Spare(String folderName) {
		
		File newFile = new File(path + folderName);
		newFile.mkdir();
	}
	
	
	
	
	public void makeDir (String []newpath)
    {
        String currentDirec=path;
        int j = -1;
        while(newpath[++j] != null)
        {
        	
        int check=0;
        for(int i=0;i<newpath[j].length();i++)
        {
            if(newpath[j].charAt(i)=='\\')
            {
                check=1;
            }
        }
        if(check==1)
        {
         File file= new File(newpath[j]);
         
         boolean bool=file.mkdirs();
        
         if(bool)
             System.out.println("dir created succefully");
         else if(file.exists())
         {
             System.out.println("cant create the dir " +newpath[j] +" because exited before ");
         }
         else
             System.out.println("cant create the dir "+newpath[j]+" becuase the path is wrong ! ");
        }
        else
        {
        File file= new File(currentDirec+'\\'+newpath[j]);
         boolean bool=file.mkdirs();
         if(bool)
             System.out.println("dir created succefully");
         else if(file.exists())
         {
             System.out.println("cant create the dir"+newpath[j]+" because exited before ");
         }
         else
             System.out.println("cant create the dir" +newpath[j]+ " becuase the path is wrong ! ");
        }
        }
    }
	
	
	
	
	
	
	/**
	 * This function removes a folder from a specific path
	 * @param folderName
	 */
	public void removeDir_Spare(String folderName) {
		
		File file = new File(path + folderName);
		file.delete();
	}
	
	
	
	
	
	
	public void removeDir (String []newpath)
    {
   String currentDirec=path;
   int j = -1;
   while(newpath[++j] != null)
   {
                       int check=0;
       for(int i=0;i<newpath[j].length();i++)
       {
           if(newpath[j].charAt(i)=='/')
           {
               check=1;
           }
       }
       
       if(check==1)
       {
           File f=new File(newpath[j]);
        if(f.exists()&&f.isDirectory()){
			boolean bool=f.delete();
                       if(bool)
                       {
				System.out.println(newpath[j]+"deleted");
                       }
                       else
                       {
                       System.out.print(newpath[j]+"not deleted cuz not empty");
                       }
                       
			}
			else{
            
				System.out.println(newpath[j]+"not deleted cuz not exict");
			}
       }
       else
       {
       File f=new File(currentDirec+'/'+newpath[j]);
        if(f.exists()&&f.isDirectory()){
			boolean bool=f.delete();
                       if(bool)
                       {
				System.out.println(newpath[j]+"deleted");
                       }
                       else
                       {
                       System.out.println(newpath[j]+"not empty");
                       }
                       
			}
			else{
				System.out.println(newpath[j]+"not exict");
			}
         }
      }
    }
   
	
	
	
	
	
	
	
	/**
	 * This function returns the current directory
	 * @return
	 */
	public String currentPath() {
	
		return this.path;
	}
	
	
	
	
	
	
	
	/**
	 * 
	 * @param source_path
	 */
	public void remove(String [] source_path)
    {
        if(source_path[0]!=null && source_path[1]==null)
        {
            int check=0;
        for(int i=0;i<source_path[0].length();i++)
        {
            if(source_path[0].charAt(i)=='/')
            {
                check=1;
            }
        }
        if(check==1)
        {
        File f=new File(source_path[0]);
        if(f.exists())
        {
          if(f.isFile())
          {
          f.delete();
          }
          else
          {
              System.out.println("can't delete cuz isn't a file");
          }
        }
        else
        {
            System.out.println("invalid path");
        }
            
        }
        else // short path
        {
         File f=new File(path+source_path[0]);
        if(f.exists())
        {
          if(f.isFile())
          {
          f.delete();
          }
          else
          {
              System.out.println("can't delete cuz isn't a file");
          }
        }
        else
        {
            System.out.println("invalid path");
        }
        
        }
        }
       if(source_path[1]!=null && source_path[2]==null)
       {
          int check=0;
        for(int i=0;i<source_path[0].length();i++)
        {
            if(source_path[0].charAt(i)=='/')
            {
                check=1;
            }
        }
        if(check==1)
        {
        
       File d=new File(source_path[1]);
       if(d.exists())
       {
       File[] files=d.listFiles();
      for(File file : files)
       {
           file.delete();
       }
       d.delete();
       System.out.println("deleted");
        }
       else{
       System.out.println("invalid path");
       }
        }
        else // short path
        {
         File d=new File(path+source_path[1]);
       if(d.exists())
       {
       File[] files=d.listFiles();
      for(File file : files)
       {
           file.delete();
       }
       d.delete();
       
       System.out.println("delete");
        }   
       else{
       System.out.println("invalid path");
       }
        
        } 
    
       }
    
    }
	
	
	
	
	
	
	/**
	 * This function moves a file from a specific directory into
	 * another one, by copying the file into the destination 
	 * and then deleting it from the source
	 * @param path1
	 * @param path2
	 */
	public void move(String path1, String path2) {
		
		int slash = 0;
		for (int i = path1.length()-1; i > 0; i--) {
			if (path1.charAt(i) == '/') {
				slash = i;
				break;
			}
		}
		
		String fileName = path1.substring(slash, path1.length());
		
		path2 = path2 + fileName;
		
		copy(path1, path2);
		
		String[] path = {null, null, null, null, null, null, null, null, null};
		path[0] = path1;
		remove(path);
	}
	
	
	
	
	
	
	/**
	 * This function is for the redirect operator (>) 
	 * it overwrites a specific source file into a destination file
	 * @param path1
	 * @param path2
	 */
	public void overwriteRedirect(String path1, String path2) {
		
		copy(path1, path2);
	}
	
	
	
	/**
	 * This function is for the redirect operator (>)
	 * it overwrites a string inside a destination path
	 * it open the file by using the FileWriter class, writes 
	 * the string, then it closes the file
	 * @param inputString
	 * @param path
	 */
	public void overwriteRedirect1(String inputString, String path) {
		
		try {
			
			FileWriter fw = new FileWriter(path);
			fw.write(inputString);
			fw.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	
	/**
	 * This function is for the redirect operator (>>)
	 * it appends the content of the source file to the the destination file
	 * it opens the destination file in the appending mode, reads each string
	 * from the source file, appends each one to the original content of 
	 * the destination file, the closes it.
	 * @param path1
	 * @param path2
	 */
	public void appendRedirect(String path1, String path2) {
		
		File file = new File(path1); 
	    Scanner sc;
		try {
			
			sc = new Scanner(file);
			String fileContent;
			
			while (sc.hasNextLine()) {
				
				BufferedWriter out;
				fileContent = sc.nextLine();
				
				try {
					
					out = new BufferedWriter(new FileWriter(path2, true));
					
					out.write(fileContent); 
			        out.close();
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    
	}
	
	
	
	
	
	/**
	 * This function is for the redirect operator (>>)
	 * it appends a string to the destination file
	 * it puts the file in appending mode, writes the string
	 * besides the original content, then it closes the file.
	 * @param inputString
	 * @param path
	 */
	public void appendRedirect1(String inputString, String path) {
		
		// Open given file in append mode. 
        BufferedWriter out;
		try {
			
			out = new BufferedWriter(new FileWriter(path, true));
			
			out.write(inputString); 
	        out.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        
		
	}
	
	
	
	
	
	
	
	public void help()
	{
                System.out.println("cd: Changes current working directory or back to home path");
                System.out.println("ls: List all contents of current directory");
		System.out.println("cp: Copies files");
                System.out.println("cat: concatenates files and print on the standard output");
                System.out.println("more: Let us display and scroll down the output in one direction only");
                System.out.println("mkdir: Creates a new directory");
		System.out.println("rmdir: Deletes a empty directory");
                System.out.println("mv: Moves files");
                System.out.println("rm: Deletes a file and non empty directory");
                System.out.println("args: List all commands arguments");
                System.out.println("date: Displays current date and time");
		System.out.println("help: list all commands and its functionalities");
		System.out.println("pwd: Displays the path of current directory");
		System.out.println("clear: Clears console");
	}
	
	
	
	
	
	
	
	
	public void args(String cmd)
    {
		
        if(cmd.equals("cd"))
	    {
	    System.out.println("args1: path or ~ or : or ..");
	    }  
	     if(cmd.equals("ls"))
	    {
	    System.out.println("no args");
	    }
	     
	    if(cmd.equals("cp"))
	    {
	    System.out.println("args1: source file , args2: distination file");
	    }
	    if(cmd.equals("cat"))
	    {
	    System.out.println("args1: file path");
	    }
	    if(cmd.equals("more"))
	    {
	    System.out.println("args1: file path");
	    }
	    if(cmd.equals("mkdir"))
	    {
	    System.out.println("args: name of directory being created");
	    }
	    if(cmd.equals("rmdir"))
	    {
	    System.out.println("args: name of delete empty directory");
	    }
	    if(cmd.equals("mv"))
	    {
	    System.out.println("args1: source file , args2: distination directory");
	    }
	    if(cmd.equals("rm"))
	    {
	    System.out.println("args1: file name ");
	    System.out.println("args1: -r , args2: file name");
	    }
	    if(cmd.equals("data"))
	    {
	    System.out.println("no args");
	    }
	    if(cmd.equals("help"))
	    {
	    System.out.println("no args");
	    }
	    if(cmd.equals("pwd"))
	    {
	    System.out.println("no args");
	    }
    }
	
	
	
	
	
	public void date()
    {
		LocalDateTime myObj = LocalDateTime.now();
		System.out.println(myObj);
    }
	
	
	
	
	
	
	public  void clear()
    {
          System.out.println("\n\n\n\n\n\n\n\n\n\n\n");
    }

	
	
	
	
	
	
	
	
}
