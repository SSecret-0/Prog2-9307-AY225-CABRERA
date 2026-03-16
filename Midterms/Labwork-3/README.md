## Java

# MP02 – Display First 10 Rows (Java)

This Java program prompts the user to select a CSV file and displays the first 10 valid rows in a JTable within a JFrame. It reads the dataset using BufferedReader and parses lines to locate the header row, which becomes the table’s column headers. After identifying the header, the program adds the first 10 subsequent rows as table entries, skipping any empty or irrelevant lines. Using a GUI allows the data to be clearly presented without needing console output. This provides an easy, visual overview of the top portion of the dataset.

# MP03 – Search for a Keyword (Java)

This program enables the user to select a CSV file and enter a keyword to search the dataset, showing only the matching rows in a JTable. After reading the CSV file, it identifies the header row to set the table’s columns. Each dataset row is checked for the keyword (case-insensitive), and matching rows are appended to the table model for display. Non-matching rows are ignored, making it easy to filter relevant data. The GUI table ensures a clean and readable display of search results.

# MP04 – Count Valid Rows (Java)

This Java program reads a CSV dataset selected by the user, counts all valid rows, and displays them in a JTable along with the total valid row count. The program identifies the header row to define the table columns, then iterates through the remaining rows, appending non-empty rows to the table model. Each valid row increments a counter, which is displayed above the table for quick reference. The GUI provides both a visual representation of the dataset and a numeric summary of completeness. This makes it easier to review data quality at a glance.

## JavaScript

# MP02 – Display First 10 Rows

This program reads a CSV dataset selected by the user and displays the first 10 valid rows in an HTML table. It uses JavaScript's FileReader to load the file and scans line by line to locate the header row containing column names. After identifying the header, it appends it as the table header and adds the first 10 subsequent rows to the table body. Empty or irrelevant rows before the header are ignored. This allows a clean, readable view of the top portion of the dataset.

# MP03 – Search for a Keyword

This program allows the user to search the CSV dataset for a keyword and display the matching rows in an HTML table. After loading the file with FileReader, the program locates the header row and creates a table header. It then checks each subsequent row for the presence of the keyword (case-insensitive) and inserts matching rows into the table body. Non-matching rows are skipped. This provides an easy way to filter the dataset dynamically.

# MP04 – Count Valid Rows

This program counts all valid rows in the CSV dataset and displays them in an HTML table. Using FileReader, it first finds the header row and constructs the table header. Each subsequent non-empty row is considered valid, appended to the table, and included in the row count. The total number of valid rows is displayed above the table for quick reference. This gives both a visual and numerical summary of dataset completeness.