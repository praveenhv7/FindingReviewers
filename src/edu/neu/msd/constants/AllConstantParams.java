package edu.neu.msd.constants;

public interface AllConstantParams {
	
	public static final String SCHEMA="PRAVEEN.";
	
	
	//ALL SQL QUERIES
	public static final String CO_AUTHORS_SQL = "select UNION_DATA_RED.*,ROWNUM from ( "+
			"select * from ( "+
			"(SELECT author, COUNT (*) TIMES_WORKED "+
			    "FROM articles_subset "+
			   "WHERE     TITLE IN (SELECT TITLE "+
			                         "FROM articles_subset "+
			                        "WHERE UPPER (author) = UPPER (?)) "+
			         "AND UPPER (author) != UPPER (?) "+
			"GROUP BY author "+
			 ") "+
			"UNION "+
			"(SELECT author, COUNT (*) TIMES_WORKED "+
			    "FROM publications_subset "+
			   "WHERE     TITLE IN (SELECT TITLE "+
			                         "FROM publications_subset "+
			                        "WHERE UPPER (author) = UPPER (?)) "+
			         "AND UPPER (author) != UPPER (?) "+
			"GROUP BY author "+
			")  ) UNION_DATA "+
			"order by UNION_DATA.TIMES_WORKED desc ) UNION_DATA_RED "+
			"where ROWNUM <=10 ";
	
	public static final String CO_AUTHORS_SQL_JOUR="select * from articles_subset "+
			"where TITLE in (select TITLE from articles_subset where UPPER(author) =?) and UPPER(author) != ?";
	
	public static final String AUTH_BY_NAME = "select distinct Author from articles_subset where UPPER(author) like '%?%' "+
	" UNION SELECT distinct Author from publications_subset where UPPER(author) like '%?%'";
	
	
			public static final String INSERT_SHORLISTED_AUTHOR = "insert into last_selected_author values(?,?,?)";
			public static final String DELETE_SHORLISTED_AUTHOR = "delete from last_selected_author" +
					" where UPPER(author) = ?";
			public static final String DELETE_ALL_SHORLISTED_AUTHOR = "delete from last_selected_author";
			public static final String SELECT_SHORLISTED_AUTHOR = "Select * from last_selected_author";
			public static final String GET_COUNT_CONEFRENCES = "Select count(*) from publications_subset where UPPER(author) = ?";
			public static final String GET_COUNT_JOURNALS = "Select count(*) from articles_subset where UPPER(author) = ?";
	public static final String GET_AUTHOR_HOMEPAGE = "Select * from AUTHOR_WEBSITE where UPPER(name) = ?";	
	public static final String GET_AUTHOR_AFFILIATION = "Select * from AUTHOR_AFFILIATION where UPPER(name) = ?";
	//ALL SQL TYPES AND RELATED CALLS
	public static final String GET_CONFERENCE_DEATILS = "{ CALL GET_CONFERENCE_DETAILS(?,?,?,?,?) }";
	public static final String PUB_OBJ_TYPE_SQL ="PUBLICATIONS_OBJ_TYPE";
	public static final String PUB_OBJ_TAB_TYPE_SQL = "PUBLICATIONS_OBJ_TAB";
	public static final String COMMON_AUTHORS_OBJ="AUTHOR_PUBS_DETAILS_OBJ";
	public static final String COMMON_AUTHORS_OBJ_TAB="AUTHOR_PUBS_DETAILS_TAB";
	public static final String GET_JOURNAL_AUTHORS="{ call GET_JOURNAL_AUTHORS(?,?,?,?,?,?)}";
	public static final String JOURNAL_OBJ_TYPE = "JOURNAL_OBJ_TYPE";
	public static final String GET_JOURNAL_DETAILS="{ call get_journal_details(?,?,?,?,?) }";
	public static final String JOURNAL_OBJ_TAB= "JOURNAL_OBJ_TAB";
	
	
	//MAPPER CLASS
	public static final String PUBLICATION_MAPPER="edu.neu.msd.dto.PublicationMapper";
	public static final String COMMON_AUTHOR_MAPPER="edu.neu.msd.dto.CommonAuthorMapper";
	public static final String JOURNAL_MAPPER= "edu.neu.msd.dto.JournalMapper";
	
}
