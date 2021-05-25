package CMD_Package;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		
		
		
		Parser pars = new Parser();
		Terminal term = new Terminal();
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		
		
		
		String fCommand = new String(" ");
		String sCommand = new String(" ");
		
		while(true) {
			
			String ePath = term.currentPath();
			ePath = ePath.substring(0, ePath.length() - 1);
			
			System.out.println();
			System.out.print(ePath + " ~& ");
			
			String commandLine;
			
			
			
			
			//This Part Deals with Pipe Operator
			
			if (!fCommand.equals(" ")) {
				commandLine = sCommand;
				fCommand = " ";
				sCommand = " ";
				
			}
			
			else {
				commandLine = scan.nextLine();
			}
			
			
			
			System.out.println();
			
			pars.setParmetersToNull();
			
			
			
			boolean pipeCheck = false;
			int pipeIndx = 0;
			for (int i = 0; i < commandLine.length(); i++) {
				if (commandLine.charAt(i) == '|') {
					pipeCheck = true;
					pipeIndx = i;
					break;
				}
			}
			
			if (pipeCheck) {
				
				 fCommand = commandLine.substring(0, pipeIndx-1);
				 sCommand = commandLine.substring(pipeIndx+2, commandLine.length());
				 commandLine = fCommand;
			}
			
			
			//The End of the Pipe Operator Part
			
			
			
			
			
			pars.parse(commandLine);
			
			String command = pars.getCommand();
			String[] parms = pars.getParameters();
			
			
			
			
			
			// CD Command Line
			/*
			 * if the parameter is (..), it goes back
			 * in the directory.
			 * 
			 * it handles the short paths, if the parameter is a name of a folder 
			 * inside the current directory.
			 * 
			 * it changes the whole directory if the parameter is a complete path.
			 */
			 if (command.equals("cd")) {
				
				if (parms[0] != null && parms[0].equals("..")) {
					term.goBack();
				}
				
				else if (parms[0] != null && parms[0].charAt(1) != ':') {
					
					parms[0] = term.currentPath() + parms[0];
					
					if (pars.changeDirPars()) {
						
						term.changeDir(parms[0]);
					}	
				}
				
				else if (pars.changeDirPars()) {
					
					term.changeDir(parms[0]);
				}
			}
			
			
			
			
			
			// LS Command Line
			/*
			 * if the command line has a redirect operator (>), it overwrites a list
			 * of the directory folder into the destination file.
			 * 
			 * if the command line has a redirect operator (>), it appends a list
			 * of the directory folder into the destination file.
			 * 
			 * it lists all the folders and files inside the directory and displays them.
			 */
			else if (command.equals("ls")) {
				
				String[] lsList = term.list();
				
				if (parms[0] != null && parms[0].equals(">")) {
					
					term.overwriteRedirect1(lsList[0], parms[1]);
					for (int i = 1; i < lsList.length; i++) {
						term.appendRedirect1(lsList[i], parms[1]);
					}
				}
				
				else if (parms[0] != null && parms[0].equals(">>")) {
					
					for (int i = 1; i < lsList.length; i++) {
						term.appendRedirect1(lsList[i], parms[1]);
					}
				}
				
				else {
					
					if (pars.listParse()) {
						
						for (int i = 0; i < lsList.length; i++) {
							System.out.println(lsList[i]);
						}
					}
				}
			}
			
			
			
			
			// PWD Command Line
			 /*
			  * it displays the current directory
			  */
			else if (command.equals("pwd")) {
				
				System.out.println(term.currentPath());
			}
			
			
			
			
			
			// CP Command Line
			/*
			 * it copies a source file into a destination file after 
			 * parsing the command line.
			 */
			else if (command.equals("cp")) {
				
				if (pars.copyParse()) {

					term.copy(parms[0], parms[1]);
				}
			}
			
			
			
			
			
			
			
			// CAT Command Line
			/*
			 * if the command line has a redirect operator (>), it overwrites the source
			 * file into the destination file.
			 * 
			 * if the command line has a redirect operator (>>), it appends the source
			 * file into the destination file.
			 * 
			 * it displays the content of the file.
			 * 
			 * it echoes the user input string with spacing.
			 */
			else if (command.equals("cat")) {
				
				if (parms[1] != null && parms[1].equals(">")) {
					
					if (pars.overwriteRedirectParse()) {
						
						term.overwriteRedirect(parms[0], parms[2]);
					}
				}
				
				else if (parms[1] != null && parms[1].equals(">>")) {
					
					if (pars.appendRedirectParse()) {
						
						term.appendRedirect(parms[0], parms[2]);
					}
				}
				
				else if (parms[0] == null) {
					
					String echoString = scan.nextLine();
					do {
						term.echoCat(echoString);
						echoString = scan.nextLine();
					} while (!echoString.equals("--"));
				}
				
				else {
					
					int i = 0;
					while (parms[i] != null && pars.concatenateParse(parms[i])) {
						
						term.concatenate(parms[i]);
						i++;
					}
				}
			}
			
			
			
			
			// More Command Line
			/*
			 * it echos the input of the user.
			 * 
			 * it navigates into a content of a file.
			 */
			else if (command.equals("more")) {
				
				if (parms[0] == null) {
					
					String echoString = scan.nextLine();
					do {
						term.echoMore(echoString);
						echoString = scan.nextLine();
					} while (!echoString.equals("++"));
				}
				
				else if (pars.moreParse()) {
					
					term.more(parms[0]);
				}
			}
			
			
			
			
			
			
			// MKDIR Command Line
			/*
			 * it creates a folder or more with the user input names
			 * in a specific path.
			 */
			else if (command.equals("mkdir")) {
				
				if (pars.makeDirParse()) {
					
					term.makeDir(parms);
				}
			}
			
			
			
			
			
			// RMDIR Command Line
			/*
			 * it removes a folder from a specific path.
			 */
			else if (command.equals("rmdir")) {
				
				if (pars.removeDirParse()) {
					
					term.removeDir(parms);
				}
			}
			
			
			
			
			//MV Command Line
			/*
			 * it moves a file form a specific path to another one.
			 */
			else if (command.equals("mv")) {
				
				if (pars.moveParse()) {
					
					term.move(parms[0], parms[1]);
				}
			}
			 
			 
			 
			
			 
			 
			//RM Command Line
			 
			else if (command.equals("rm")) {
				
				if (pars.removeParse()) {
		
					term.remove(parms);
				}
			}
			
			 
			 
			 
			 
			 
			//HELP Command Line
			 
			else if (command.equals("help")) {
				
				if (pars.helpParse()) {
					
					term.help();
				}
			}
			 
			 
			 
			 
			 
			 
			 
			 
			//DATE Command Line

			else if (command.equals("date")) {
				
				if (pars.datePasrse()) {
					
					term.date();
				}
			}
			 
			 
			 
			 
			 
			//ARGS 
			 
			else if (command.equals("args")) {
				
				if (pars.argsParse()) {
					
					term.args(parms[0]);
				}
			}
			 
			 
			 
			 
			else if (command.equals("clear")) {
				
				if (pars.clearParse()) {
					
					term.clear();
				}
			}
			 
			 
			 
			
			//Error Message
			 /*
			  * this error message appears in case of wrong command.
			  */
			else {
				System.out.println("Wrong Command Line");
			}
			
			
		}
		
	}
	
}
