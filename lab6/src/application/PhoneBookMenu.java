package application;

import java.util.Optional;

import javafx.application.Platform;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import phonebook.PhoneBook;

public class PhoneBookMenu extends MenuBar {
	private PhoneBook phoneBook;
	private NameListView nameListView;
	
	/** Creates the menu for the phone book application.
	 * @param phoneBook the phone book with names and numbers
	 * @param nameListView handles the list view for the names
	 */
	public PhoneBookMenu(PhoneBook phoneBook, NameListView nameListView) {
		this.phoneBook = phoneBook;
		this.nameListView = nameListView;

		final Menu menuPhoneBook = new Menu("PhoneBook");
		final MenuItem menuQuit = new MenuItem("Quit");
		menuQuit.setOnAction(e -> Platform.exit());
		menuPhoneBook.getItems().addAll(menuQuit);
	
		final Menu menuFind = new Menu("Find");
		final MenuItem menuShowAll = new MenuItem("Show All");
		menuShowAll.setOnAction(e -> showAll());
		menuFind.getItems().addAll(menuShowAll);
		
		final MenuItem menuFindNumbers = new MenuItem("Find number(s)");
		menuFindNumbers.setOnAction(e -> findNumbers());
		menuFind.getItems().addAll(menuFindNumbers);
		
		final MenuItem menuFindName = new MenuItem("Find name");
		menuFindName.setOnAction(e -> findName());
		menuFind.getItems().addAll(menuFindName);
		
		final MenuItem menuFindPersons = new MenuItem("Find person(s)");
		menuFindPersons.setOnAction(e -> findPerson());
		menuFind.getItems().addAll(menuFindPersons);

	    getMenus().addAll(menuPhoneBook, menuFind);
  //    setUseSystemMenuBar(true);  // if you want operating system rendered menus, uncomment this line
	}

	
	private void showAll() {
		nameListView.fillList(phoneBook.names());
		nameListView.clearSelection();
	}
	
	private void findName() {
		Optional<String> name = Dialogs.oneInputDialog("Find Name(s)","","Enter number");
		if(phoneBook.findNames(name.get()) != null){
			nameListView.fillList(phoneBook.findNames(name.get()));
			nameListView.clearSelection();
		}
	}
	
	private void findNumbers(){
		Optional<String> number = Dialogs.oneInputDialog("Find Number(s)","","Enter name");
		if(phoneBook.findNumbers(number.get()) != null){
			nameListView.fillList(phoneBook.findNumbers(number.get()));
			nameListView.clearSelection();
		}
	}
	
	private void findPerson(){
		Optional<String> letters = Dialogs.oneInputDialog("Find Person(s)","","Enter letters");
		if(phoneBook.findNames(letters.get()) != null){
			nameListView.fillList(phoneBook.findPerson(letters.get()));
			nameListView.clearSelection();
		}
	}
	
}
