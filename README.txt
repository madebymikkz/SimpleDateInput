SimpleDateInput v. 1.0
Copyright (c) 2016 Michael Wihlborg

Description
-----------
SimpleDateInput is exactly what it says on the tin --
a very simple Swing component that allows the user to enter a date and time in
the Gregorian calendar.  These values can then be read by the program using the
component in string form.

Usage
-----
SimpleDateInput is not particularly complex.  In short:

 1. Compile the source code.

 2. Place the resulting class file on your class path.

 3. In your program, import the package "madebymikkz.simpledateinput.*".

 4. Create a new instance of the component: "SimpleDateInput s = new SimpleDateInput();"

 5. Place the component somewhere in your program's GUI.

Users can then interact with the component.  To read the entered values from
the component, use either the getDate() or getTime() methods.

For a full summary of the panels methods, consult the class' Javadoc.