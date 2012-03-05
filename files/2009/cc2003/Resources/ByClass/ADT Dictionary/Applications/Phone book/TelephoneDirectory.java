/** A telephone directory of distinct names. */import java.io.*;import java.util.*; // StringTokenizerpublic class TelephoneDirectory{	private DictionaryInterface phoneBook;	private static final String DELIMITERS = " \n\r\t";	 	public TelephoneDirectory() 	{		phoneBook = new SortedLinkedDictionary();	}  // end default constructor	/** Task: Reads a text file of names and telephone numbers.	 *  @param dataFile  a text file that is open for input */	public void readFile(BufferedReader dataFile) throws IOException	{		String line = dataFile.readLine();		while (line != null)		{			StringTokenizer tokenizer = new StringTokenizer(line, DELIMITERS);						String firstName   = (String)tokenizer.nextToken();			String lastName    = (String)tokenizer.nextToken();			String phoneNumber = (String)tokenizer.nextToken();			Name fullName = new Name(firstName, lastName);						phoneBook.add(fullName, phoneNumber);						line = dataFile.readLine();		} // end while		dataFile.close();	} // end readFile	/** Task: Adds a person.	 *  @return true if added, false if person is already in dictionary */	public boolean add(Name personName, String phoneNumber)	{		boolean result = false;				if (!phoneBook.contains(personName))		{			phoneBook.add(personName, phoneNumber);			result = true;		} // end if				return result;	} // end add	/** Task: Removes a person	 *  @return phone number of removed person or null if person not found */	public String remove(Name personName)	{		return (String)phoneBook.remove(personName);	} // end remove		/** Task: Changes a person's phone number; if person is not	 *        found, adds person to directory.	 *  @return old phone number or null if not found */	public String changePhoneNumber(Name personName, String phoneNumber)	{		return (String)phoneBook.add(personName, phoneNumber);	} // end replace	/** Task: Finds the telephone number of a given person.	 * @return the desired phone number as a string, or	 *         null if no phone number is found */	public String getPhoneNumber(String firstName, String lastName)	{		Name fullName = new Name(firstName, lastName);		return (String)phoneBook.getValue(fullName);	} // end getPhoneNumber	public String getPhoneNumber(Name personName)	{		return (String)phoneBook.getValue(personName);	} // end getPhoneNumber		/** Task: Finds the telephone number of the person whose name is	 *        given by the user.	 * @return the desired phone number as a string, or	 *         null if no phone number is found, or	 *         the string "quit" if the user enters quit instead of a name */	public String getPhoneNumber() 	{		String result;				System.out.println("First and last name? ");		String line = SavitchIn.readLine();				if (line.trim().toLowerCase().equals("quit"))		{			result = "quit";		}		else		{			StringTokenizer tokenizer = new StringTokenizer(line, DELIMITERS);						String firstName = (String) tokenizer.nextToken();			String lastName = (String) tokenizer.nextToken();			Name   fullName = new Name(firstName, lastName);									result =  (String) phoneBook.getValue(fullName);		} // end if				return result;	} // end getPhoneNumber		public int getSize()	{		return phoneBook.getSize();	} // end getSize		public Iterator getKeyIterator()	{		return phoneBook.getKeyIterator();	} // end getKeyIterator		public Iterator getValueIterator()	{		return phoneBook.getValueIterator();	} // end getValueIterator}  // end TelephoneDirectory