Please note the following limitations the program as delivered:
1. Does not support multiple threads as synchronization is not in place within the code.
2. Is not coded for internationalization.
3. Exceptions currently caught due to bad input or out of memory can be logged but are currently output to the console.

The program was compiled with SE 1.8 and is compilable using SE 1.7 since Lambda use has been avoided here.

How to execute the package:

1. Unzip the package's ZIP file (TheaterMain.zip) to a directory of your choice.
2. Make sure a JRE/JDK of 1.7 or higher is available on your system's path.
3. Execute the following in the directory you extracted the zip into to begin the process:
   java -cp TheaterMain.jar com.barclaycard.theatersolve.TheaterMain

How to test the package:

Theater data is followed by order data.
1. The theater data consists of rows of sections with their initial capacity followed by a blank line to end the theater layout.  Use zero or positive values only.
2. The order data consists of rows of a patron name (no spaces allowed) and the positive count of tickets desired.   Order data must end with a blank line.   Tickets desired must be positive.

Order processing:

Once all orders are input, orders are processed in the following order:
1. Fill as many orders (not seats) as possible.
2. Place patrons as close to the front as possible.

Example input:
--------------------------
6 6
3 5 5 3
4 6 6 4
2 8 8 2
6 6

Smith 2
Jones 5
Davis 6
Wilson 100
Johnson 3
Williams 4
Brown 8
Miller 12

--------------------------


Example output:
--------------------------
Smith Row 1 Section 2
Jones Row 2 Section 2
Davis Row 1 Section 1
Wilson Sorry, we can't handle your party.
Johnson Row 1 Section 2
Williams Row 2 Section 3
Brown Row 4 Section 2
Miller Call to split party.
--------------------------

Issues or questions may be addressed to the author via email:  astein70as@gmail.com

Thank you.