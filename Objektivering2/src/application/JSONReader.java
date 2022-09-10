package application;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JSONReader {

	static ArrayList<eachRowBean> jsonRows = new ArrayList<>();

	public void ReadJSON() throws ParseException, org.json.simple.parser.ParseException {

		JSONParser parser = new JSONParser();

		try {
			JSONArray array = (JSONArray) parser.parse(new FileReader("src/sample.json"));

			for (Object object : array) {
				String[] values = new String[8];

				JSONObject entry = (JSONObject) object;

				String orderDate = (String) entry.get("OrderDate");
				values[0] = orderDate;
				// System.out.println("Orderdate: " + orderDate);

				String region = (String) entry.get("Region");
				values[1] = region;
				// System.out.println("Region: " + region);

				String rep1 = (String) entry.get("Rep1");
				values[2] = rep1;
				// System.out.println("Rep1: " + rep1);

				String rep2 = (String) entry.get("Rep2");
				values[3] = rep2;
				// System.out.println("Rep2: " + rep2);

				String item = (String) entry.get("Item");
				values[4] = item;
				// System.out.println("Item: " + item);

				String units = String.valueOf(entry.get("Units"));
				values[5] = units;
				// System.out.println("Units: " + units);

				String unitCost = (String) entry.get("UnitCost");
				values[6] = unitCost;
				// System.out.println("Unitcost: " + unitCost);

				String total = (String) entry.get("Total");
				values[7] = total;
				// System.out.println("Total: " + total);

				addJSONRow(new eachRowBean(values[0], values[1], values[2], values[3], values[4], values[5], values[6],
						values[7]));

			}
			for (var all : jsonRows) {

				String Units = all.getUnits();

				String UnitCost = all.getUnitCost().replace(".00", "");

				float floatUnits = Float.parseFloat(Units);

				float floatUnitCost = Float.parseFloat(UnitCost);

				float theTotal = floatUnits * floatUnitCost;

				String Total = String.valueOf(theTotal);

				all.setTotal(Total);

				System.out.println(all);
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void addJSONRow(eachRowBean row) {
		jsonRows.add(row);
	}

}
