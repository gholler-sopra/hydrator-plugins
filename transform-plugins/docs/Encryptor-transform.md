# Encryptor Transform


Description
-----------
Encrypts one or more fields in input records using a java keystore 
that must be present on all nodes of the cluster.


Configuration
-------------
**encyrptFields** Specifies the fields to encrypt, separated by commas.

**transformation** Transformation algorithm/mode/padding. For example, AES/CBC/PKCS5Padding.

**ivHex** The initialization vector if using CBC mode.

**keystorePath** The path to the keystore on local disk. The keystore must be present on every node of the cluster.

**keystorePassword** The password for the keystore.

**keystoreType** The type of keystore. For example, JKS, or JCEKS.

**keyAlias** The alias of the key to use in the keystore.

**keyPassword** The password for the key to use in the keystore.


**Note**: Do not use sink plugins that store data in textual format because Field Encryptor converts the field values to `bytes` and text based sink plugin will convert bytes to string at the time of writing the data.
Use any columnar format like ORC, Parquet etc. 

Sample Input
-------------
The input will be a csv file which will be passed to the the Field Encryptor plugin.
We will specify the input fields which we want to encrypt in the output.

    id,test1,test2,servicetac,operstatus,itseverity
    0,testA,testB,0.0,Active,1
    1,testA,testB,1.0,Active,2
    2,testA,testB,2.0,Active,3
    3,testA,testB,3.0,Active,4
    4,testA,testB,4.0,Active,5
    5,testA,testB,5.0,Active,6

PLugin Configuration Details
----------------------------

    {
        "name": "Field Encryptor",
        "plugin": {
          "name": "Encryptor",
          "type": "transform",
          "label": "Field Encryptor",
          "properties": {
            "encryptFields": "id",
            "transformation": "RSA",
            "keystorePath": "/tmp/keystore.jks",
            "keystorePassword": "*******",
            "keystoreType": "JKS",
            "keyAlias": "security002-mst-01.cloud.in.guavus.com",
            "keyPassword": "*******"
          }
    }
    
Here we have Added ID field to be encrypted
The Output format will be 

    {
     "name": "etlSchemaBody",
     "schema": "{\"type\":\"record\",\"name\":\"etlSchemaBody\",\"fields\":[{\"name\":\"id\",\"type\":[\"bytes\",\"null\"]},{\"name\":\"test1\",\"type\":[\"string\",\"null\"]},{\"name\":\"test2\",\"type\":[\"string\",\"null\"]},{\"name\":\"servicetac\",\"type\":[\"string\",\"null\"]},{\"name\":\"operstatus\",\"type\":[\"string\",\"null\"]},{\"name\":\"manager\",\"type\":[\"string\",\"null\"]},{\"name\":\"itseverity\",\"type\":[\"string\",\"null\"]}]}"
    }   
    
Sample Output
-------------

    +--------------------+-----+-----+----------+----------+----------+
    |                  id|test1|test2|servicetac|operstatus|itseverity|
    +--------------------+-----+-----+----------+----------+----------+
    |java.nio.HeapByte...|testA|testB|       0.0|    Active|         1|
    |java.nio.HeapByte...|testA|testB|       1.0|    Active|         2|
    |java.nio.HeapByte...|testA|testB|       2.0|    Active|         3|
    |java.nio.HeapByte...|testA|testB|       3.0|    Active|         4|
    |java.nio.HeapByte...|testA|testB|       4.0|    Active|         5|
    |java.nio.HeapByte...|testA|testB|       5.0|    Active|         6|
    +--------------------+-----+-----+----------+----------+----------+