# CSV Parser Transform


Description
-----------
Parses an input field as a CSV Record into a Structured Record. Supports multi-line CSV Record parsing
into multiple Structured Records. Different formats of CSV Record can be parsed using this plugin.
Supports these CSV Record types: ``DEFAULT``, ``Tab Delimited``, ``Pipe Delimited``.

Configuration
-------------
**format:** Specifies the format of the CSV Record the input should be parsed as.

**delimiter:** Custom delimiter to be used for parsing the fields. The custom delimiter can only be specified by 
selecting the option 'Custom' from the format drop-down. In case of null, defaults to ",".

**field:** Specifies the input field that should be parsed as a CSV Record. 
Input records with a null input field propagate all other fields and set fields that
would otherwise be parsed by the CSVParser to null.

**schema:** Specifies the output schema of the CSV Record.

## Sample Input

    {
        "name": "CSVParser",
        "plugin": {
          "name": "CSVParser",
          "type": "transform",
          "label": "CSVParser",
          "properties": {
            "format": "DEFAULT",
            "schema": "{\"type\":\"record\",\"name\":\"etlSchemaBody\",\"fields\":[{\"name\":\"offset\",\"type\":\"long\"},{\"name\":\"body\",\"type\":\"string\"}]}",
            "field": "body"
          }
        }
        "outputSchema": [
          {
            "name": "etlSchemaBody",
            "schema": "{\"type\":\"record\",\"name\":\"etlSchemaBody\",\"fields\":[{\"name\":\"offset\",\"type\":\"long\"},{\"name\":\"body\",\"type\":\"string\"}]}"
          }
        ],
        "inputSchema": [
          {
            "name": "File",
            "schema": "{\"type\":\"record\",\"name\":\"etlSchemaBody\",\"fields\":[{\"name\":\"offset\",\"type\":\"long\"},{\"name\":\"body\",\"type\":\"string\"}]}"
          }
    }
    
## Sample Input
The input will be a csv file which will be parsed by the the CSV Parser plugin.

    id,test1,test2,servicetac,operstatus,manager,itseverity
    0,testA,testB,0.0,Active,MTTrapdProbeonkstlltcsp01,1
    1,testA,testB,1.0,Active,MTTrapdProbeonkstlltcsp02,2
    2,testA,testB,2.0,Active,MTTrapdProbeonkstlltcsp02,3
    3,testA,testB,3.0,Active,MTTrapdProbeonkstlltcsp03,4
    4,testA,testB,4.0,Active,MTTrapdProbeonkstlltcsp04,5
    5,testA,testB,5.0,Active,MTTrapdProbeonkstlltcsp05,6
    
After Parsing the Sample input by CSV parser, The output will be saved to a file in the format of json

    {"body":"id,test1,test2,servicetac,operstatus,manager,itseverity"}
    {"body":"0,testA,testB,0.0,Active,MTTrapdProbeonkstlltcsp01,1"}
    {"body":"1,testA,testB,1.0,Active,MTTrapdProbeonkstlltcsp02,2"}
    {"body":"2,testA,testB,2.0,Active,MTTrapdProbeonkstlltcsp02,3"}
    {"body":"3,testA,testB,3.0,Active,MTTrapdProbeonkstlltcsp03,4"}
    {"body":"4,testA,testB,4.0,Active,MTTrapdProbeonkstlltcsp04,5"}
    {"body":"5,testA,testB,5.0,Active,MTTrapdProbeonkstlltcsp05,6"}
