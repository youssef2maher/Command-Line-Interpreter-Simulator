package CMD_Package;

import java.io.File;

public class Parser {

	
	
	private String[] parameters = {null, null, null, null, null, null, null, null, null};
	private String command;
	
	
	
	
	
	
	public void setParameters(String arg, int indx) {	
		parameters[indx] = arg;
	}
	
	public void setCommand(String cmd) {
		this.command = cmd;
	}
	
	public String[] getParameters() {
		return parameters;
	}
	
	public String getCommand() {
		return command;
	}
	
	public void setParmetersToNull() {
		for (int i = 0; i < parameters.length; i++) {
			parameters[i] = null;
		}
	}
	
	
	
	
	/**
	 * This function analysis the command line and separates it 
	 * by storing the command into a variable and storing the arguments
	 * into an array of strings
	 * @param commandLine
	 */
	public void parse(String commandLine) {
		
		int numOfPars = 1;
		for (int i = 0; i < commandLine.length(); i++) {
			if (commandLine.charAt(i) == ' ') {
				numOfPars++;
			}
		}
		
		String[] pars = commandLine.split(" ", numOfPars);
		
		setCommand(pars[0]);
		
		for (int i = 1; i < numOfPars; i++) {
			setParameters(pars[i], i-1);
		}
		
	}
	
	
	
	
	
	
	/**
	 * This function checks about the existence of a file
	 * Returning true if exists, and false if not
	 * @param path
	 * @return
	 */
	public boolean pathExist(String path) {
		
		File file = new File(path);
		return file.exists();
	}
	
	
	
	
	
	/**
	 * This function parsing the change directory command
	 * it checks about the correctness of the parameters 
	 * and the existence of the entered path
	 * @return
	 */
	public boolean changeDirPars() {
		
		if (parameters[0] == null) {
			System.out.println("Wrong Arguments");
			return false;
		}
		
		if (parameters[1] != null) {
			System.out.println("Wrong Arguments");
			return false;
		}
		
		if (!pathExist(parameters[0])) {
			System.out.println("The Path Doesn't Exist");
		}
		
		return pathExist(parameters[0]);
	}
	
	
	
	
	
	public boolean listParse() {
		
		if (parameters[0] != null) {
			System.out.println("No Arguments Needed");
			return false;
		}
		return true;
	}
	
	
	
	
	
	
	public boolean makeDirParse() {
		
		int check=0;
		
		if (parameters[0] == null) {
			System.out.println("Wrong Arguments");
			return false;
		}
        
        for(int j=0;j<parameters[0].length();j++)
        {
        if(parameters[0].charAt(j)== ' ')
        {System.out.println("error ! the name of dir don't have a space ");
        return false;
        }
        }
        if(parameters[0]=="")
        {
            System.out.println("cmd mkdir shoud have one argument at least");
            return false;
        }
     
        return true;
	}
	
	
	
	
	
	public boolean removeDirParse() {
		
		
		if (parameters[0] == null) {
			System.out.println("Wrong Arguments");
			return false;
		}
		
		int check=0;
        
        for(int j=0;j<parameters[0].length();j++)
        {
        if(parameters[0].charAt(j)== ' ')
        {System.out.println("error ! the name of dir don't have a space ");
        return false;
        }
        }
        if(parameters[0]==null)
        {
            System.out.println("cmd rmdir shoud have one argument at least");
            return false;
        }
        return  true;
	}
	
	
	
	
	
	
	/**
	 * This function parsing the copy command
	 * it checks about the correctness of the parameters 
	 * and the existence of the entered path
	 * @return
	 */
	public boolean copyParse() {
		
		if (parameters[0] == null || parameters[1] == null) {
			System.out.println("Wrong Arguments");
			return false;
		}
		
		if (parameters[2] != null) {
			System.out.println("Wrong Arguments");
			return false;
		}
		
		boolean dot = false;
		int slash = 0;
		for (int i = parameters[1].length()-1; i > 0; i--) {
			if (parameters[1].charAt(i) == '.') {
				dot = true;
			}
			
			if (parameters[1].charAt(i) == '/') {
				slash = i;
				break;
			}
		}

		
		String editPath = parameters[1];
		if (dot) {
			editPath = parameters[1].substring(0, slash);
		}
		
		if (!pathExist(parameters[0]) || !pathExist(editPath)) {
			System.out.println("The Path Doesn't Exist");
			return false;
		}
		return true;
	}
	
	
	
	
	
	
	/**
	 * This function parsing the concatenate command
	 * it checks about the existence of the entered path
	 * @return
	 */
	public boolean concatenateParse(String path) {
		
		if (!pathExist(path)) {
			System.out.println("The Path Doesn't Exist");
		}
		return pathExist(path);
	}
	
	
	
	
	/**
	 * This function parsing the more command
	 * it checks about the correctness of the parameters 
	 * and the existence of the entered path
	 * @return
	 */
	public boolean moreParse() {
		
		if (parameters[1] != null) {
			System.out.println("Wrong Arguments");
			return false;
		}
		
		if (!pathExist(parameters[0])) {
			System.out.println("The Path Doesn't Exist");
		}
		return pathExist(parameters[0]);
	}
	
	
	
	
	
	
	
	
	public boolean moveParse() {
		
		if (parameters[0] == null || parameters[1] == null) {
			System.out.println("Wrong Arguments");
			return false;
		}
		
		if (parameters[2] != null) {
			System.out.println("Wrong Arguments");
			return false;
		}
		
		for (int i = 0; i < parameters[1].length(); i++) {
			
			if (parameters[1].charAt(i) == '.') {
				
				System.out.println("Destination is not a folder");
			}
		}
		
		if (!pathExist(parameters[0]) || !pathExist(parameters[1])) {
			System.out.println("The Path Doesn't Exist");
			return false;
		}
		return true; 
	}
	
	
	
	
	
	
	public boolean removeParse() {
		
         if(parameters[2] != null)
         {
          System.out.println("rm shouid have 2 argument at most");
          return false;
         }
           if(parameters[0] == null)
         {
          System.out.println("rm shouid have 1 argument at least");
          return false;
         }
             if(parameters[1] != null)
         {
            	 return true;
         }
             
         if(parameters[2] != null)
         {
             if(parameters[0]!="-r")
             {
                 System.out.println("rm shouid have the first args -r and then the path");
                 return false;
             }
             else
             {
            	 return true;
             }
         }
		return true;  
	}
	
	
	
	
	
	
	
	/**
	 * This function parsing the overwrite operator
	 * it checks about the correctness of the parameters 
	 * and the existence of the entered paths
	 * @return
	 */
	public boolean overwriteRedirectParse() {
		
		if (parameters[3] != null) {
			System.out.println("Wrong Arguments");
			return false;
		}
		
		if (!pathExist(parameters[0]) || !pathExist(parameters[2])){
			System.out.println("The Path Doesn't Exist");
			return false;
		}
		return true;
	}
	
	
	
	
	/**
	 * This function parsing the overwrite operator
	 * it checks about the correctness of the parameters 
	 * and the existence of the entered path
	 * @return
	 */
	public boolean overwriteRedirectParse1() {
		
		if (parameters[3] != null) {
			System.out.println("Wrong Arguments");
			return false;
		}
		
		if (!pathExist(parameters[2])){
			System.out.println("The Path Doesn't Exist");
			return false;
		}
		return true;
	}
	
	
	
	
	/**
	 * This function parsing the append operator
	 * it checks about the correctness of the parameters 
	 * and the existence of the entered paths
	 * @return
	 */
	public boolean appendRedirectParse() {
		
		if (parameters[3] != null) {
			System.out.println("Wrong Arguments");
			return false;
		}
		
		if (!pathExist(parameters[0]) || !pathExist(parameters[2])){
			System.out.println("The Path Doesn't Exist");
			return false;
		}
		return true;
	}
	
	
	
	
	
	
	/**
	 * This function parsing the overwrite operator
	 * it checks about the correctness of the parameters 
	 * and the existence of the entered path
	 * @return
	 */
	public boolean appendRedirectParse1() {
		
		if (parameters[3] != null) {
			System.out.println("Wrong Arguments");
			return false;
		}
		
		if (!pathExist(parameters[2])){
			System.out.println("The Path Doesn't Exist");
			return false;
		}
		return true;
	}
	
	
	
	
	public boolean helpParse() {
		
		if(parameters[0]!=null)
        {
        System.out.println("help don't have argument");
        return false;
        }
		
		return true;
	}
	
	
	
	public boolean datePasrse() {
		
		if(parameters[0]!=null)
        {
         System.out.println("data have 1 argument at most");
         return false;
        }
		
	    return true;
	}
	
	
	
	public boolean argsParse() {
		
		if(parameters[0] == null) {
			System.out.println("Wrong Argumnts");
			return false;
		}
		
		if(parameters[1]!=null)
        {
        System.out.println("Wrong Arguments");
        return false;
        }
		
		return true;
	}
	
	
	
	public boolean clearParse() {
		
		if(parameters[0]!=null)
        {
        System.out.println("clear don't have argument");
        return false;
        }
		
		return true;
	}
	
	
}
