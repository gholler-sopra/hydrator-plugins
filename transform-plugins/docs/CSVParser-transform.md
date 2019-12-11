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

The input will be a csv file which will be parsed by the the CSV Parser plugin.

    id,test1,test2,servicetac,operstatus,manager,itseverity
    0,testA,testB,0.0,Active,MTTrapdProbeonkstlltcsp01,1
    1,testA,testB,1.0,Active,MTTrapdProbeonkstlltcsp02,2
    2,testA,testB,2.0,Active,MTTrapdProbeonkstlltcsp02,3
    3,testA,testB,3.0,Active,MTTrapdProbeonkstlltcsp03,4
    4,testA,testB,4.0,Active,MTTrapdProbeonkstlltcsp04,5
    5,testA,testB,5.0,Active,MTTrapdProbeonkstlltcsp05,6
    
## PLugin Configuration Details
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
            "schema": "{\"type\":\"record\",\"name\":\"etlSchemaBody\",\"fields\":[{\"name\":\"id\",\"type\":\"string\"},{\"name\":\"test1\",\"type\":\"string\"},{\"name\":\"test2\",\"type\":\"string\"},{\"name\":\"servicetac\",\"type\":\"string\"},{\"name\":\"operstatus\",\"type\":\"string\"},{\"name\":\"manager\",\"type\":\"string\"},{\"name\":\"itseverity\",\"type\":\"string\"}]}"
          }
        ],
        "inputSchema": [
          {
            "name": "File",
            "schema": "{\"type\":\"record\",\"name\":\"etlSchemaBody\",\"fields\":[{\"name\":\"offset\",\"type\":\"long\"},{\"name\":\"body\",\"type\":\"string\"}]}"
          }
        ]
    }
    
    
## Sample Output
After Parsing the Sample input by CSV parser, The output will be saved to a file in the format of json

    {"id":"id","test1":"test1","test2":"test2","servicetac":"servicetac","operstatus":"operstatus","manager":"manager","itseverity":"itseverity"}	
    {"id":"0","test1":"testA","test2":"testB","servicetac":"0.0","operstatus":"Active","manager":"MTTrapdProbeonkstlltcsp01","itseverity":"1"}
    {"id":"1","test1":"testA","test2":"testB","servicetac":"1.0","operstatus":"Active","manager":"MTTrapdProbeonkstlltcsp02","itseverity":"2"}
    {"id":"2","test1":"testA","test2":"testB","servicetac":"2.0","operstatus":"Active","manager":"MTTrapdProbeonkstlltcsp02","itseverity":"3"}
    {"id":"3","test1":"testA","test2":"testB","servicetac":"3.0","operstatus":"Active","manager":"MTTrapdProbeonkstlltcsp03","itseverity":"4"}
    {"id":"4","test1":"testA","test2":"testB","servicetac":"4.0","operstatus":"Active","manager":"MTTrapdProbeonkstlltcsp04","itseverity":"5"}
    {"id":"5","test1":"testA","test2":"testB","servicetac":"5.0","operstatus":"Active","manager":"MTTrapdProbeonkstlltcsp05","itseverity":"6"}
