## JSON to XML converter

To Build jar file run the build.xml in root folder which will generate ConverterApplication.jar in /target folder


To run the application pass input and output file as argument while running the jar application

        java -jar ConverterApplication.jar input.json output.xml


## Example
Sample input json
```json
{
    "organization" : {
        "name" : "CyberSecurityworks",
        "type" : "Inc",
        "building_number" : 4,
        "floating" : -17.4,
        "null_test": null
    },
    "security_related" : true,
    "array_example0" : ["red", "green", "blue", "black"],
    "array_example1" : [1, "red", [{ "nested" : true}], { "obj" : false}]
}
```

Sample output json
```xml
<object>
	<object name="organization">
		<string name="name">CyberSecurityworks</string>
		<string name="type">Inc</string>
		<number name="building_number">4</number>
		<number name="floating">-17.4</number>
		<null name="null_test"/>
	</object>
	<boolean name="security_related">true</boolean>
	<array name="array_example0">
		<string>red</string>
		<string>green</string>
		<string>blue</string>
		<string>black</string>
	</array>
	<array name="array_example1">
		<number>1</number>
		<string>red</string>
		<array>
			<object>
				<boolean name="nested">true</boolean>
			</object>
		</array>
		<object>
			<boolean name="obj">false</boolean>
		</object>
	</array>
</object>
```
