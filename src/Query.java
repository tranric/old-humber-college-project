
public enum Query {
	AUTHENTICATE("SELECT * FROM USER WHERE USERNAME='%s' AND PASSWORD='%s'"),
	SELECT_ALL_FROM("SELECT * FROM %s WHERE USERNAME='%s'"),
<<<<<<< HEAD
	SELECT_EXAMS("SELECT * FROM EXAM_BOOKINGS INNER JOIN USER_COURSES ON EXAM_BOOKINGS.COURSE_CODE = USER_COURSES.COURSE_CODE "
	+ "WHERE USER_COURSES.USERNAME='%s' ORDER BY EXAM_DATE"),
	GET_COURSES("SELECT COURSE_CODE FROM USER_COURSES WHERE USERNAME='%s'"),
	GET_ROOMS("SELECT * FROM ROOMS")
=======
	SELECT_EXAMS("SELECT * FROM EXAM_BOOKINGS INNER JOIN USER_COURSES ON EXAM_BOOKINGS.COURSE_CODE = USER_COURSES.COURSE_CODE " + "WHERE USER_COURSES.USERNAME='%s' ORDER BY EXAM_DATE"),
	SELECT_NUMBER_OF_USERS("SELECT COUNT(*) AS TOTAL FROM USER"),
	SELECT_ALL_COURSES_AVAILABLE("SELECT * FROM COURSES"),
	SELECT_INFO_OF_TEACHERS("SELECT * FROM TEACHER"),
	SELECT_INFO_OF_STUDENTS("SELECT * FROM STUDENT"),
	ADD_USER("INSERT INTO USER (USERNAME, PASSWORD, ROLE) VALUES ('%s', '%s', '%s');"),
	EDIT_USER_PASSWORD("UPDATE USER SET PASSWORD='%s' WHERE USERNAME='%s'"),
	EDIT_USER_NAME("UPDATE '%s' SET FIRSTNAME='%s', LASTNAME='%s'WHERE USERNAME='%s' "),
	EDIT_ROLE("UPDATE USER SET ROLE='%s' WHERE USERNAME='%s'")
>>>>>>> origin/devs
	;
	

	  private final String command;

	  private Query(String command) {
	    this.command = command;
	  }

	  @Override
	  public String toString() {
	     return command;
	  }
	  
}
