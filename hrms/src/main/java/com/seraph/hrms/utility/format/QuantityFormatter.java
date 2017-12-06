package com.seraph.hrms.utility.format;

/**
 * @author  Adrian Jasper K. Chua
 * @version 1.0
 * @since   Dec 13, 2016
 */
public class QuantityFormatter {

	private QuantityFormatter() {
		
	}
	
	public static String format(Integer quantity, Integer packaging) {
		String formattedQuantity = "";
		
		int packageCount = quantity / packaging;
		int pieceCount = quantity - (packageCount * packaging);
		formattedQuantity += packageCount + "";
		if(pieceCount != 0) formattedQuantity += " & " + pieceCount + "/" + packaging;
		if(formattedQuantity.equals("")) formattedQuantity += "0";
		
		return formattedQuantity;
	}
}
