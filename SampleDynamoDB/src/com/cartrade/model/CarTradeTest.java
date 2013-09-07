package com.cartrade.model;

import java.util.HashMap;
import java.util.Map;

import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.DescribeTableRequest;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;
import com.amazonaws.services.dynamodbv2.model.PutItemResult;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import com.amazonaws.services.dynamodbv2.model.TableDescription;
import com.cartrade.model.Car.FuelType;
import com.cartrade.model.Car.Transmission;

public class CarTradeTest {
	private AmazonDynamoDB	dynamoDB;

	public CarTradeTest() {
		dynamoDB = new AmazonDynamoDBClient(new ClasspathPropertiesFileCredentialsProvider());
		Region usWest2 = Region.getRegion(Regions.US_WEST_2);
		dynamoDB.setRegion(usWest2);
	}

	public TableDescription getTableDescription(String tableName) {
		DescribeTableRequest describeTableRequest = new DescribeTableRequest().withTableName(tableName);
		return dynamoDB.describeTable(describeTableRequest).getTable();
	}

	public PutItemResult insertData(PutItemRequest putItemRequest) {
		return dynamoDB.putItem(putItemRequest);
	}

	public PutItemRequest createPutItemRequest(String tableName, Map<String, AttributeValue> item) {
		return new PutItemRequest(tableName, item);
	}

	public ScanResult searchData(ScanRequest scanRequest) {
		return dynamoDB.scan(scanRequest);
	}

	public Map<String, AttributeValue> createItem(Car car) {
		Map<String, AttributeValue> item = new HashMap<String, AttributeValue>();
		item.put("company_name", new AttributeValue(car.getMaker()));
		item.put("class", new AttributeValue(car.getModel()));
		item.put("price", new AttributeValue().withN(String.valueOf(car.getPrice())));
		item.put("transmission", new AttributeValue(car.getTransmission().toString()));
		item.put("fueltype", new AttributeValue(car.getFuelType().toString()));
		item.put("fueltype", new AttributeValue(car.getMilage()));
		return item;
	}

	public static void main(String... args) {
		CarTradeTest test = new CarTradeTest();
		TableDescription tableDescription = test.getTableDescription("car");
		System.out.println(tableDescription);

		Car car = new Car();
		car.setFuelType(FuelType.PETROL);
		car.setMaker("Maruti Suzuki");
		car.setMilage("14 KMPL");
		car.setModel("Swift");
		car.setPrice(450000);
		car.setTransmission(Transmission.MANUAL);

		test.insertData(test.createPutItemRequest("car", test.createItem(car)));

	}
}
