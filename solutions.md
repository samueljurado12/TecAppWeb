# Problems with decent Solutions
## Problem 1
	/home/ubuntie/git/TecAppWeb/4Bank_APP/nbproject/build-impl.xml:632: The following error occurred while executing this line:
/home/ubuntie/git/TecAppWeb/4Bank_APP/4Bank_APP-war/nbproject/build-impl.xml:784: The libs.CopyLibs.classpath property is not set up.
This property must point to 
org-netbeans-modules-java-j2seproject-copylibstask.jar file which is part
of NetBeans IDE installation and is usually located at 
<netbeans_installation>/java<version>/ant/extra folder.
Either open the project in the IDE and make sure CopyLibs library
exists or setup the property manually. For example like this:
 ant -Dlibs.CopyLibs.classpath=a/path/to/org-netbeans-modules-java-j2seproject-copylibstask.jar
BUILD FAILED (total time: 0 seconds)

## Solution
	Open: Tools->Options->Java->Ant Paste the following in Properties:
	Paste in Properties:
	libs.CopyLibs.classpath=/usr/local/netbeans-8.2/java/ant/extra/org-netbeans-modules-java-j2seproject-copylibstask.jar
	Change /usr/local/netbeans-8.2 for your local installation

## Problem 2
	Class name is wrong or classpath is not set for : com.mysql.jdbc.jdbc2.optional.MysqlDataSource

## Solution
The jdbc jar file is not correctly applied, you need to place it in glassfish libs folder

Go to http://dev.mysql.com/downloads/connector/j and with in the dropdown select "Platform Independent" then it will show you the options to download tar.gz file or zip file.

Download zip file and extract it, with in that you will find mysql-connector-XXX.jar file

Then place it in [/path/to/glassfish/installation]/glassfish/lib

# Problems with incosistent solutions but that often work.

## Problem 3
	WELD-000227: Bean identifier index inconsistency detected - the distributed container probably doesn't work with identical applications

## Solution
	Close project, then clean and build + start
