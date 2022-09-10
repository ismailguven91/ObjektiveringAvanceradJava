package application;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CSVReader {

	static ArrayList<eachRowBean> rows = new ArrayList<>();

	public void ReadCSV() throws FileNotFoundException {

		try (BufferedReader br = new BufferedReader(new FileReader("src/sample.csv"))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] values = line.split(",", 8);
				addRow(new eachRowBean(values[0], values[1], values[2], values[3], values[4], values[5], values[6],
						values[7]));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (var item : rows) {

			if (item.getUnits().equals("Units") && item.getUnitCost().equals("UnitCost")) {

			} else {
				String Units = item.getUnits();

				String UnitCost = item.getUnitCost().replace(".00", "");

				float floatUnits = Float.parseFloat(Units);

				float floatUnitCost = Float.parseFloat(UnitCost);

				float theTotal = floatUnits * floatUnitCost;

				String Total = String.valueOf(theTotal);

				item.setTotal(Total);
			}
			System.out.println(item);
		}
	}

	public static void addRow(eachRowBean row) {
		rows.add(row);
	}

}
