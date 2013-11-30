package beanpac;

import dbconnpac.DataBaseConn;

public class Bulletin{
	
	private String mes="";
	private String mesTable="bulletin";
	
	public String getMes() {
		DataBaseConn bulDBC=new DataBaseConn();
		bulDBC.execQuery("select * from "+this.mesTable);
		if(bulDBC.rsNext()){
			mes=bulDBC.rsGetString("message");
		}
		bulDBC.connClose();
		return mes;
	}
	public void setMes(String mesTemp) {
		DataBaseConn bulDBC=new DataBaseConn();
		bulDBC.execute("update "+this.mesTable+" set message='"+mesTemp+"' where id='1';");
		bulDBC.connCloseUpdate();
		
	}
	
	
}
