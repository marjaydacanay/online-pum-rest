package com.ph.ibm.util;

/**
 * @author <a HREF="mailto:dacanam@ph.ibm.com">Marjay Dacanay</a>
 * @author <a HREF="mailto:balocaj@ph.ibm.com">Jerven Balocating</a>
 */
public class SqlQueries {

    //PUM YEAR REPOSITORY IMPL
    public static final String SQL_RETRIEVE_YEAR_DATE =
        "SELECT YEAR_ID, PUMYEAR, END, START, CREATEDATE, CREATEDBY, UPDATEDATE, UPDATEDBY FROM YEAR WHERE PUMYEAR = ?";

    public static final String SQL_EDIT_YEAR = "UPDATE YEAR SET START= ?, END= ? WHERE PUMYEAR= ?;";

    public static final String SQL_RETRIEVE_CURRENT_FY = "SELECT * FROM YEAR ORDER BY PUMYEAR DESC LIMIT 1";

    public static final String SQL_UPDATE_FISCAL_YEAR = " UPDATE opum.year SET start = ?, end = ? WHERE pumyear = ?; ";

    public static final String SQL_DELETE_FY_TEMPLATE = " DELETE FROM opum.fy_template WHERE year_id = ?; ";
    
    public static final String SQL_DELETE_FY_QUARTER = " DELETE FROM opum.quarter WHERE year_id = ?; ";
    
    public static final String SQL_DELETE_FY_WEEK = " DELETE FROM opum.week WHERE year_id = ?; ";

    public static final String SQL_POPULATE_FY_WEEKS =
        " INSERT INTO opum.week(QUARTER_ID, NAME, YEAR_ID, START, END) " + " Values (?,?,?,?,?); ";

    public static final String SQL_POPULATE_FY_QUARTER =
        " INSERT INTO opum.quarter(YEAR_ID, NAME, START, END) " + " Values (?,?,?,?); ";

    public static final String SQL_SAVE_YEAR = "INSERT INTO YEAR (START,END,PUMYEAR,CREATEDBY) VALUES (?,?,?,?); ";

    public static final String SQL_POPULATE_FY =
        " INSERT INTO opum.fy_template(YEAR_ID, DATE, VALUE, IS_HOLIDAY, EVENT_NAME) " + " Values (?,?,?,?,?); ";

    public static final String SQL_ADD_OR_UPDATE_HOLIDAY =
        "UPDATE opum.fy_template SET value = 0, is_holiday = 1, event_name = ? WHERE date = ? AND YEAR_ID = ?; ";

    public static final String SQL_CHECK_PUM_CYLCE = "select count(*) from opum.year where PUMYear = ?; ";

    public static final String SQL_SAVE_QUARTER =
        "INSERT INTO QUARTER (" + "START,END,PUMQUARTER) " + "VALUES (?,?,?); ";

    public static final String SQL_SAVE_MONTH = "INSERT INTO MONTH (" + "START,END,PUMMONTH) " + "VALUES (?,?,?); ";

    public static final String SQL_RETRIEVE_ALL_YEAR =
        "SELECT YEAR_ID, PUMYEAR, END, START, CREATEDATE, CREATEDBY, UPDATEDATE, UPDATEDBY FROM YEAR;";

    //UTILIZATION REPOSITORY IMPL
    public static final String SQL_GET_QUARTERLY_UTILIZATION_HOURS =
        "SELECT SUM((CASE WHEN (EL.LEAVE_TYPE = 'RC') THEN EL.HOURS" +
            "          WHEN (EL.STATUS = 'Approved') THEN 0 ELSE FT.VALUE " +
            "     END)) AS HOURS FROM QUARTER Q LEFT JOIN( " +
            "                FY_TEMPLATE FT LEFT JOIN EMPLOYEE_LEAVE EL " +
            "                    ON ((FT.DATE = EL.LEAVE_DATE) " + "                    AND EL.EMPLOYEE_ID = ? )) " +
            "                ON ( FT.DATE BETWEEN Q.START AND Q.END) WHERE FT.YEAR_ID = ? " +
            "                GROUP BY Q.QUARTER_ID " +
            "                ORDER BY DATE;";
}
