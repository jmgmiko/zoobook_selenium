# zoobook_selenium
Automated testing framework for Zoobook's EMR which is written in Java and uses TestNG

### The Input of the Testing Framework
This framework is fed by an Excel file which contains 2 sheets:

##### Steps
This sheet contains all of the necessary steps for the framework to follow through. Inside, there is a table with 5 columns:

- Command
  - It contains any of the framework's various commands.
- FindBy
  - It contains several methods of finding web elements: by id, class, xpath, name and css.
- Name
  - It contains either the name of the attribute given the find by method or the name of the browser.
- Custom Input
  - It contains the input of a particular command.
- Execute?
  - It determines whether the step will be executed or not.
- Status (Pass/Fail)
  - It determines the status of the step whether it passes or fails.

##### Dropdown
It houses the dropdown values of the following columns from the **Steps** column: 

- Command
- FindBy
- Execute
- Check If Exists
- Check If Element Contains
- Check If Displayed

### The Commands of the Framework
Here is the list of the framework's commands:

##### Open
It sets up a new browser session.
- FindBy (Not Used)
- Name **(Needed)** 
  - Available options: Chrome, Firefox, IE, Edge
- Custom Input **(Needed)**
  - Contains the URL for the browser to open
- Execute **(Needed)**

##### Input
It allows input into a text field.
- FindBy **(Needed)**
- Name **(Needed)** 
- Custom Input (Optional)
- Execute (Optional)

##### Click
It clicks a particular element.
- FindBy **(Needed)**
- Name **(Needed)** 
- Custom Input (Not Used)
- Execute (Optional)

##### Mouse Hover
It allows the mouse cursor to hover over an element.
- FindBy **(Needed)**
- Name **(Needed)** 
- Custom Input (Not Used)
- Execute (Optional)

##### Select Dropdown
It selects an item from a dropdown list.
- FindBy **(Needed)**
- Name **(Needed)** 
- Custom Input **(Needed)**
- Execute (Optional)

##### Check If Exists
It determines whether the given element exists or not.
- FindBy **(Needed)**
- Name **(Needed)** 
- Custom Input **(Needed)**
  - Value must be **True** or **False**.
- Execute (Optional)

##### Check If Element Contains
It checks whether the given element contains a value.
- FindBy **(Needed)**
- Name **(Needed)** 
- Custom Input **(Needed)**
- Execute (Optional)

##### Check If Displayed
It verifies if the given element is displayed.
- FindBy **(Needed)**
- Name **(Needed)** 
- Custom Input (Not Used)
- Execute (Optional)
