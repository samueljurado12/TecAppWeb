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

## Problem 3
  Caused by: javax.validation.ConstraintViolationException: Bean Validation constraint(s) violated 
  while executing Automatic Bean Validation on callback event:'prePersist'. Please refer to 
  embedded ConstraintViolations for details.

## Solution
This is caused because on the persistency entities there is some constraint that is violated, to know exactly what constraint add this to the AbstractFacade

  public void create(T entity) {

    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();
    Set&lt;ConstraintViolation&lt;T&gt;&gt; constraintViolations = validator.validate(entity);
    if(constraintViolations.size() &gt; 0){
        Iterator&lt;ConstraintViolation&lt;T&gt;&gt; iterator = constraintViolations.iterator();
        while(iterator.hasNext()){
            ConstraintViolation&lt;T&gt; cv = iterator.next();
            System.err.println(cv.getRootBeanClass().getName()+"."+cv.getPropertyPath() + " " +cv.getMessage());

            JsfUtil.addErrorMessage(cv.getRootBeanClass().getSimpleName()+"."+cv.getPropertyPath() + " " +cv.getMessage());
        }
    }else{
        getEntityManager().persist(entity);
    }
} 

## Problem 4
  Created jdbc resources dont't appear

## Solution
  Create folder WEB-INF in project main folder
  Copy file from configuration files to that folders
  In persistence unit copy the value from that file on property "jndi-name"
# Problems with incosistent solutions but that often work.

## Problem I.1
	WELD-000227: Bean identifier index inconsistency detected - the distributed container probably doesn't work with identical applications

## Solution
	Close project, then clean and build + start
