**JSON to XML converter**

To Build jar file run the build.xml in root folder which will generate ConverterApplication.jar in /target folder


To run the application pass input and output file as argument while running the jar application
```
>java -jar ConverterApplication.jar input.json output.xml
```


Sample input json
>{
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

