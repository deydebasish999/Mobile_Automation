package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


import org.testng.annotations.Test;
import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;



	public class ReadDataFromExcel {

		
		static ConfigFileReader reader = new ConfigFileReader();
		static Fillo fillo=null;
		static Connection conn= null;
		static Recordset recordset = null;
		
	
		public static String ReadTestData(String colName,String sheetName) throws FilloException {
			fillo =new Fillo();
			//String sheetName = "AddProductToCart";
			String testData = null;
			conn=fillo.getConnection(System.getProperty("user.dir") +"//testData//" +reader.getTestDataSheetName()+".xlsx");
			//String query = "Select * from "+sheetName1+" where Executable='"+executable+"'";
			String fieldName=null;
			String fieldValue = null;
			String query = "Select * from "+sheetName+" where Executable='Yes'";
			//System.out.println(query);
			recordset=conn.executeQuery(query);
			Map<String,String> dataMap = new HashMap<String, String>();
			while(recordset.next()) {
				List<String> colNames = recordset.getFieldNames();
				Iterator<String> dataIterator=colNames.iterator();
				while(dataIterator.hasNext()){
					for(int i=0;i<colNames.size();i++) {
						fieldName = dataIterator.next();
						fieldValue = recordset.getField(fieldName);
						dataMap.put(fieldName, fieldValue);
						if(colName.equalsIgnoreCase(fieldName)) {
							testData=dataMap.get(fieldName);
						}
						
					}
				}
				break;
			}
			
			
			return testData;

		}
		
		
		public static String ReadDeviceParameters(String colName,String sheetName) throws FilloException {
			fillo =new Fillo();
			//String sheetName = "AddProductToCart";
			String testData = null;
			conn=fillo.getConnection(System.getProperty("user.dir") +"//testData//" +reader.getTestDataSheetName()+".xlsx");
			//String query = "Select * from "+sheetName1+" where Executable='"+executable+"'";
			String fieldName=null;
			String fieldValue = null;
			String query = "Select * from "+sheetName;
			//System.out.println(query);
			recordset=conn.executeQuery(query);
			Map<String,String> dataMap = new HashMap<String, String>();
			while(recordset.next()) {
				List<String> colNames = recordset.getFieldNames();
				Iterator<String> dataIterator=colNames.iterator();
				while(dataIterator.hasNext()){
					for(int i=0;i<colNames.size();i++) {
						fieldName = dataIterator.next();
						fieldValue = recordset.getField(fieldName);
						dataMap.put(fieldName, fieldValue);
						if(colName.equalsIgnoreCase(fieldName)) {
							testData=dataMap.get(fieldName);
						}
						
					}
				}
				break;
			}
			
			
			return testData;

		}
		
		public static String getQueryFromDbSheet(){
			String query=null;
			try {
				conn = fillo.getConnection(System.getProperty("user.dir")+"//testData//" +reader.getDbDataSheetName()+".xlsx");

			} catch (FilloException e1) {
				e1.printStackTrace();
			}
			try {
				recordset = conn.executeQuery("select * from QuerySheet where Executable='Yes'");
			} catch (FilloException e1) {
				e1.printStackTrace();
			}
			try {
				while(recordset.next()) {
					query = recordset.getField("Query");
				}
			} catch (FilloException e1) {
				e1.printStackTrace();
			}
			
			return query;
		}

}


