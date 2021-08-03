import java.util.ArrayList;

public class ResourceCentre {

	/**
	 * 
	 */
	// testing commit
	// refactor extract constant : 5 -> "OPTION_QUIT"
	private static final int OPTION_QUIT = 5; 

	//Testing teammate pull 3.0


	// Testing teammates pull 2.0
	// test 123

	public static void main(String[] args) {

		// refactor rename variable : camcorderList -> "ccList"
		// refactor rename variable : chromebookList -> "cbList"
		ArrayList<Camcorder> ccList = new ArrayList<Camcorder>();
		ArrayList<Chromebook> cbList = new ArrayList<Chromebook>();

		ccList.add(new Camcorder("CC001", "Sony HDR-CX405", 35));
		ccList.add(new Camcorder("CC002", "Panasonic HC-MDH2", 10));
		cbList.add(new Chromebook("CB001", "ASUS Chromebook ", "Win 10"));
		cbList.add(new Chromebook("CB002", "HP Chromebook", "Win 10"));

		int option = 0;

		while (option != OPTION_QUIT) {

			ResourceCentre.menu();
			option = Helper.readInt("Enter an option > ");

			if (option == 1) {
				// View all items
				ResourceCentre.viewAllCamcorder(ccList);
				ResourceCentre.viewAllChromebook(cbList);

			} else if (option == 2) {
				// Add a new item
				ResourceCentre.setHeader("ADD");			
				ResourceCentre.setHeader("ITEM TYPES");
				System.out.println("1. Camcorder");
				System.out.println("2. Chromebook");
				
				int itemType = Helper.readInt("Enter option to select item type > ");

				if (itemType == 1) {
					// Add a camcorder
					Camcorder cc = inputCamcorder();
					ResourceCentre.addCamcorder(ccList, cc);

				} else if (itemType == 2) {
					// Add Chromebook
					Chromebook cb = inputChromebook();
					ResourceCentre.addChromebook(cbList, cb);

				} else {
					System.out.println("Invalid type");
				}

			} else if (option == 3) {
				// Loan item
				ResourceCentre.setHeader("LOAN");			
				ResourceCentre.setHeader("ITEM TYPES");
				System.out.println("1. Camcorder");
				System.out.println("2. Chromebook");
				
				int itemType = Helper.readInt("Enter option to select item type > ");

				if (itemType == 1) {
					// Loan camcorder
					ResourceCentre.loanCamcorder(ccList);
				} else if (itemType == 2) {
					// Loan Chromebook
					ResourceCentre.loanChromebook(cbList);
				} else {
					System.out.println("Invalid type");
				}

			} else if (option == 4) {
				// Return item
				ResourceCentre.setHeader("RETURN");				
				ResourceCentre.setHeader("ITEM TYPES");
				System.out.println("1. Camcorder");
				System.out.println("2. Chromebook");
				
				int itemType = Helper.readInt("Enter option to select item type > ");
				if (itemType == 1) {
					// Return camcorder
					ResourceCentre.returnCamcorder(ccList);
				} else if (itemType == 2) {
					// Return Chromebook
					ResourceCentre.returnChromebook(cbList);
				} else {
					System.out.println("Invalid type");
				}

			} else if (option == OPTION_QUIT) {
				System.out.println("Bye!");
			} else {
				System.out.println("Invalid option");
			}

		}

	}

	public static void menu() {
		ResourceCentre.setHeader("RESOURCE CENTRE APP");
		System.out.println("1. Display Inventory");
		System.out.println("2. Add item");
		System.out.println("3. Loan item");
		System.out.println("4. Return item");
		System.out.println("5. Quit");
		Helper.line(80, "-");

	}
	
	public static void setHeader(String header) {
		Helper.line(80, "-");
		System.out.println(header);
		Helper.line(80, "-");
	}

	public static String showAvailability(boolean isAvailable) {
		String avail;

		if (isAvailable == true) {
			avail = "Yes";
		} else {
			avail = "No";
		}
		return avail;
	}

	//================================= Option 1 View (CRUD - Read) =================================
	public static String retrieveAllCamcorder(ArrayList<Camcorder> camcorderList) {
		String output = "";

		for (int i = 0; i < camcorderList.size(); i++) {

			String assetTagCC = camcorderList.get(i).getAssetTag();
			String descriptionCC = camcorderList.get(i).getDescription();
			String showAvailabilityCC = ResourceCentre.showAvailability(camcorderList.get(i).getIsAvailable());
			String dueDateCC = camcorderList.get(i).getDueDate();
			int opticalZoomCC = camcorderList.get(i).getOpticalZoom();
			output += String.format("%-10s %-30s %-10s %-10s %-20d\n", assetTagCC,descriptionCC, showAvailabilityCC,dueDateCC,opticalZoomCC);
		}
		return output;
	}
	public static void viewAllCamcorder(ArrayList<Camcorder> camcorderList) {
		ResourceCentre.setHeader("CAMCORDER LIST");
		String output = String.format("%-10s %-30s %-10s %-10s %-20s\n", "ASSET TAG", "DESCRIPTION",
				"AVAILABLE", "DUE DATE","OPTICAL ZOOM");
		 output += retrieveAllCamcorder(camcorderList);	
		System.out.println(output);
	}

	public static String retrieveAllChromebook(ArrayList<Chromebook> chromebookList) {
		String output = "";
		// write your code here
		for (int i = 0; i < chromebookList.size(); i++) {

			String assetTagCB = chromebookList.get(i).getAssetTag();
			String descriptionCB = chromebookList.get(i).getDescription();
			String showAvailabilityCB = ResourceCentre.showAvailability(chromebookList.get(i).getIsAvailable());
			String dueDateCB = chromebookList.get(i).getDueDate();
			String osCB = chromebookList.get(i).getOs();
			output += String.format("%-10s %-30s %-10s %-10s %-20s\n", assetTagCB,descriptionCB, showAvailabilityCB,dueDateCB,osCB);
		}
		return output;
	}
	public static void viewAllChromebook(ArrayList<Chromebook> chromebookList) {
		
		ResourceCentre.setHeader("CHROMEBOOK LIST");
		String output = String.format("%-10s %-30s %-10s %-10s %-20s\n", "ASSET TAG", "DESCRIPTION",
				 "AVAILABLE", "DUE DATE","OPERATING SYSTEM");
		 output += retrieveAllChromebook(chromebookList);
		System.out.println(output);
	}

	//================================= Option 2 Add (CRUD - Create)=================================
	public static Camcorder inputCamcorder() {
		String tag = Helper.readString("Enter asset tag > ");
		String description = Helper.readString("Enter description > ");
		int zoom = Helper.readInt("Enter optical zoom > ");

		Camcorder cc= new Camcorder(tag, description, zoom);
		return cc;
		
	}
	public static void addCamcorder(ArrayList<Camcorder> camcorderList, Camcorder cc) {
		
		camcorderList.add(cc);
		System.out.println("Camcorder added");
	}
	
	public static Chromebook inputChromebook() {
		String tag = Helper.readString("Enter asset tag > ");
		String description = Helper.readString("Enter description > ");
		String os = Helper.readString("Enter operating system > ");

		Chromebook cb= new Chromebook(tag, description, os);
		return cb;
		
	}	
	public static void addChromebook(ArrayList<Chromebook> chromebookList, Chromebook cb) {

		chromebookList.add(cb);
		System.out.println("Chromebook added");
	}
	
	//================================= Option 3 Loan (CURD- Update) =================================
	public static boolean doLoanCamcorder(ArrayList<Camcorder> camcorderList, String tag, String dueDate) {
		
		boolean isLoaned = false;

		// refactor extract method: isLoaned -> isCCLoaned
		return isCCLoaned(camcorderList, tag, dueDate, isLoaned);
	}

	/**
	 * @param camcorderList
	 * @param tag
	 * @param dueDate
	 * @param isLoaned
	 * @return
	 */
	private static boolean isCCLoaned(ArrayList<Camcorder> camcorderList, String tag, String dueDate,
			boolean isLoaned) {
		for (int i = 0; i < camcorderList.size(); i++) {
			
			String assetTag = camcorderList.get(i).getAssetTag();
			
			if (tag.equalsIgnoreCase(assetTag)				
					&& camcorderList.get(i).getIsAvailable() == true) {
				
				camcorderList.get(i).setIsAvailable(false);
				camcorderList.get(i).setDueDate(dueDate);
				
				isLoaned = true;
				
			}
		}
		return isLoaned;
	}

	public static void loanCamcorder(ArrayList<Camcorder> camcorderList) {
		ResourceCentre.viewAllCamcorder(camcorderList);
		String tag = Helper.readString("Enter asset tag > ");
		String due = Helper.readString("Enter due date > ");
		Boolean isLoaned =doLoanCamcorder(camcorderList, tag, due);
		if (isLoaned == false) {
			System.out.println("Invalid asset tag");
		} else {
			System.out.println("Camcorder " + tag + " loaned out");
		}
	}
	

	public static boolean doLoanChromebook(ArrayList<Chromebook> chromebookList, String tag, String dueDate) {
		// write your code here
		boolean isLoaned = false;

		// refactor extract method: isLoaned -> isCBLoaned
		return isCBLoaned(chromebookList, tag, dueDate, isLoaned);
	}

	/**
	 * @param chromebookList
	 * @param tag
	 * @param dueDate
	 * @param isLoaned
	 * @return
	 */
	private static boolean isCBLoaned(ArrayList<Chromebook> chromebookList, String tag, String dueDate,
			boolean isLoaned) {
		for (int i = 0; i < chromebookList.size(); i++) {
			String assetTag = chromebookList.get(i).getAssetTag();
			
			if (tag.equalsIgnoreCase(assetTag)
					
					&& chromebookList.get(i).getIsAvailable() == true) {
				
				chromebookList.get(i).setIsAvailable(false);
				chromebookList.get(i).setDueDate(dueDate);
				
				isLoaned = true;
				
			}
		}
		return isLoaned;
	}

	public static void loanChromebook(ArrayList<Chromebook> chromebookList) {
		// write your code here
		ResourceCentre.viewAllChromebook(chromebookList);
		String tag = Helper.readString("Enter asset tag > ");
		String due = Helper.readString("Enter due date > ");
		Boolean isLoaned =doLoanChromebook(chromebookList, tag, due);
		if (isLoaned == false) {
			System.out.println("Invalid asset tag");
		} else {
			System.out.println("Chromebook " + tag + " loaned out");
		}	
		
	}
	//================================= Option 4 Return (CURD- Update)=================================
	public static boolean doReturnCamcorder(ArrayList<Camcorder> camcorderList,String tag) {
		boolean isReturned = false;

		// refactor extract method: isReturned -> isCCReturned
		return isCCReturned(camcorderList, tag, isReturned);
		
	}

	/**
	 * @param camcorderList
	 * @param tag
	 * @param isReturned
	 * @return
	 */
	private static boolean isCCReturned(ArrayList<Camcorder> camcorderList, String tag, boolean isReturned) {
		for (int i = 0; i < camcorderList.size(); i++) {
			if (tag.equalsIgnoreCase(camcorderList.get(i).getAssetTag())
					&& camcorderList.get(i).getIsAvailable() == false) {
				camcorderList.get(i).setIsAvailable(true);
				camcorderList.get(i).setDueDate("");
				isReturned = true;
				
			}
		}
		return isReturned;
	}

	public static void returnCamcorder(ArrayList<Camcorder> camcorderList) {
		ResourceCentre.viewAllCamcorder(camcorderList);
		String tag = Helper.readString("Enter asset tag > ");
		Boolean isReturned = doReturnCamcorder(camcorderList, tag);
		
		if (isReturned == false) {
			System.out.println("Invalid asset tag");
		} else {
			System.out.println("Camcorder " + tag + " returned");
		}
	}
	// write your doReturnChromebook code here
	public static boolean doReturnChromebook(ArrayList<Chromebook> chromebookList,String tag) {
		boolean isReturned = false;

		// refactor extract method: isReturned -> isCBReturned
		return isCBReturned(chromebookList, tag, isReturned);
		
	}

	/**
	 * @param chromebookList
	 * @param tag
	 * @param isReturned
	 * @return
	 */
	private static boolean isCBReturned(ArrayList<Chromebook> chromebookList, String tag, boolean isReturned) {
		for (int i = 0; i < chromebookList.size(); i++) {
			if (tag.equalsIgnoreCase(chromebookList.get(i).getAssetTag())
					&& chromebookList.get(i).getIsAvailable() == false) {
				chromebookList.get(i).setIsAvailable(true);
				chromebookList.get(i).setDueDate("");
				isReturned = true;
				
			}
		}
		return isReturned;
	}
	public static void returnChromebook(ArrayList<Chromebook> chromebookList) {
		// write your code here
		ResourceCentre.viewAllChromebook(chromebookList);
		String tag = Helper.readString("Enter asset tag > ");
		Boolean isReturned = doReturnChromebook(chromebookList, tag);
		
		if (isReturned == false) {
			System.out.println("Invalid asset tag");
		} else {
			System.out.println("Chromebook " + tag + " returned");
		}
	}


}
