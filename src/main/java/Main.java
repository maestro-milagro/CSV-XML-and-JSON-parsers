import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Main {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, ParseException {
        String[] columnMapping = {"id", "firstName", "lastName", "country", "age"};
        String fileName = "data.csv";
        List<Employee> list = parseCSV(columnMapping, fileName);
        String jsonString = listToJson(list);
        writeString(jsonString, "data.json");

        List<Employee> list1 = parseXML("data.xml");
        String jsonString1 = listToJson(list1);
        writeString(jsonString1, "data1.json");

        String jsonText = readString("data.json");
        System.out.println(jsonToList(jsonText));

    }

    public static List<Employee> parseCSV(String[] columnMapping, String fileName) {
        try (CSVReader csvReader = new CSVReader(new FileReader(fileName))) {
            ColumnPositionMappingStrategy<Employee> cpms = new ColumnPositionMappingStrategy<>();
            cpms.setType(Employee.class);
            cpms.setColumnMapping(columnMapping);
            CsvToBean<Employee> csvToBean = new CsvToBeanBuilder<Employee>(csvReader)
                    .withMappingStrategy(cpms)
                    .build();
            return csvToBean.parse();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static String listToJson(List<Employee> list) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        Type listType = new TypeToken<List<Employee>>() {
        }.getType();

        return gson.toJson(list, listType);

    }

    public static void writeString(String text, String path) {
        File file = new File(path);
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(text);
            fileWriter.append('\n');
            fileWriter.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public static List<Employee> parseXML(String path) throws ParserConfigurationException, IOException, SAXException {
        List<Employee> list = new ArrayList<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newDefaultInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new File(path));
        Node root = doc.getDocumentElement();
        NodeList nodeList = root.getChildNodes();

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node_ = nodeList.item(i);
            if (node_.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node_;

                Employee employee = new Employee();
                employee.setId(Long.parseLong(element.getElementsByTagName("id").item(0).getTextContent()));
                employee.setFirstName(element.getElementsByTagName("firstName").item(0).getTextContent());
                employee.setLastName(element.getElementsByTagName("lastName").item(0).getTextContent());
                employee.setCountry(element.getElementsByTagName("country").item(0).getTextContent());
                employee.setAge(Integer.parseInt(element.getElementsByTagName("age").item(0).getTextContent()));

                list.add(employee);
            }

        }
        return list;
    }

    public static String readString(String path) {
        String output = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                output += line;
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return output;
    }

    public static List<Employee> jsonToList(String text) throws ParseException {
        List<Employee> list = new ArrayList<>();
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        JsonParser parser = new JsonParser();
        JsonArray jsonArray = (JsonArray) parser.parse(text);
        for (JsonElement e : jsonArray) {
            list.add(gson.fromJson(e, Employee.class));
        }
        return list;

    }
}
