# Database Batch Source


## Description
The Database accelerator is used to read from a database using a configurable SQL query. It outputs one record for each row returned by the query.


## Use Case
Consider a scenario wherein you want to read data from a database by using this source and then write to a TimePartitionedFileSet. This can be achieved by configuring the accelerator as described in the following section.


## Properties

The following pointers describe the fields as displayed in the accelerator properties dialog box.

**Reference Name:** Assign a name to this sink to uniquely identify it for lineage, annotating metadata, etc.

**Plugin Name:** Enter the name of the JDBC plugin to be used. This is the value of the 'name' key defined in the JSON file for the JDBC plugin.

**Plugin Type:** Specify the type of JDBC plugin to be used. This is the value of the 'type' key defined in the JSON file for the JDBC plugin. The default type is 'jdbc'.

**Connection String:** Specify the JDBC connection string, including the database name. (Macro-enabled)

**Import Query:** Enter the SELECT query to be used to import data from the specified table. You can specify an arbitrary number of columns to import, or you can import all columns using \*. The Query should contain the '$CONDITIONS' string. For example, 'SELECT * FROM table WHERE $CONDITIONS'.
The '$CONDITIONS' string will be replaced by 'splitBy' field limits specified by the bounding query.
The '$CONDITIONS' string is not required if numSplits is set to one. (Macro-enabled)

**Bounding Query:** Enter the Bounding Query that should return the min and max of the values of the 'splitBy' field. Both min and max are required in the query; for example, 'SELECT MIN(id),MAX(id) FROM table'. This is not required if numSplits is set to one. (Macro-enabled)

**Split-By Field Name:** Enter the field name which will be used to generate splits. This is not required if numSplits is set to one. (Macro-enabled)

**Number of Splits to Generate:** Specify the number of splits to be generated. (Macro-enabled)

**Username:** Enter the user name for connecting to the specified database. It is mandatory for databases that require authentication and optional for databases that do not require authentication. (Macro-enabled)

**Password:** Enter the password to be used to connect to the specified database. It is mandatory for databases that require authentication and optional for databases that do not require authentication. (Macro-enabled)

**Connection Arguments:** Enter a list of arbitrary string tag/value pairs as connection arguments. These arguments will be passed to the JDBC driver as connection arguments for JDBC drivers that may need additional configurations.
This should be a semicolon-separated list of key-value pairs, where each pair is separated by a equals '=' and specifies the key and value for the argument. For example, 'key1=value1;key2=value' specifies that the connection will be given arguments 'key1' mapped to 'value1' and the argument 'key2' mapped to 'value2'. (Macro-enabled)

**Enable Auto-Commit:** Choose 'True' or 'False' based on whether you want to enable auto-commit for queries run by this source. By default, 'False' is selected.
Please note that this option is important only when you are using a jdbc driver that does not support a false value for autocommit, or a driver that throws an error when auto-commit is set to false. For such drivers, you will need to set this to 'true'.

**Column Name Case:** Set the case of the column names returned from the query. The given options are ``upper`` and ``lower``. The default column names or the column names for any other input are not modified, and the names returned from the database are used as-is. Note that setting this property lends a predictability to the column name cases across different databases, but it might result in column name conflicts if multiple columns have the same names when the case is ignored (optional).

**Transaction Isolation Level:** From the drop-down list, select the transaction isolation level for queries run by this sink. By default, TRANSACTION_SERIALIZABLE is selected. See java.sql.Connection#setTransactionIsolation for more details.
The jdbc driver will throw an exception if the database does not have transactions enabled and this setting is set to True. For such drivers, this should be set to TRANSACTION_NONE.

**Schema:** Specify the schema of records output by the source. This will be used in place of whatever schema is returned from the query. However, it must match the schema that returns from the query, except that it can mark fields as nullable and can contain a subset of the fields.


## Example
This example connects to a database using the specified 'connectionString', which means it will connect to the 'prod' database of a PostgreSQL instance running on 'localhost'. It will run the 'importQuery' against the 'users' table to read four columns from the table.
The column types will be used to derive the record field types output by the source.

    {
        "name": "Database",
        "type": "batchsource",
        "properties": {
            "importQuery": "select id,name,email,phone from users where $CONDITIONS",
            "boundingQuery": "select min(id),max(id) from users",
            "splitBy": "id",
            "connectionString": "jdbc:postgresql://localhost:5432/prod",
            "user": "user123",
            "password": "password-abc",
            "jdbcPluginName": "postgres",
            "jdbcPluginType": "jdbc"
        }
    }

For example, if the 'id' column is a primary key of the type int, and the other columns are non-nullable varchars, the output records will have this schema:

    +======================================+
    | field name     | type                |
    +======================================+
    | id             | int                 |
    | name           | string              |
    | email          | string              |
    | phone          | string              |
    +======================================+



## Notes :

`The list of supported drivers and connection details`

```
+=====================================================================================================+
| DB name  | Driver Name(class name)          |   Database URL & Example                              |
+=====================================================================================================+
| MySQL    | com.mysql.jdbc.Driver            |   jdbc:mysql://<server>:<port>/<databaseName>         |
                                                  Eg: jdbc:mysql://localhost:3306/myDBName

| Postgres | org.postgresql.Driver            |   jdbc:postgresql://<server>:<port>/<databaseName>    |
                                                  Eg: jdbc:postgresql://localhost:5432/myDBName

| H2DB     | org.h2.Driver                    |   jdbc:h2:tcp://<server>:<port>/<databasePath>        |
                                                  Eg: jdbc:h2:tcp://192.168.111.139:9092/~/test

| Oracle   | oracle.jdbc.driver.OracleDriver  |   jdbc:oracle:thin:@<host>:<port>/<serviceName>       |
                                                  Eg: jdbc:oracle:thin:@localhost:1521/orcls
+=====================================================================================================+
```

Transaction Isolation Level supports for listed dbs:

***MySql/Postgres*** :  "TRANSACTION_READ_UNCOMMITTED", "TRANSACTION_READ_COMMITTED","TRANSACTION_REPEATABLE_READ",
                        "TRANSACTION_SERIALIZABLE (default)" .

### Steps to upload database driver

In order to use this accelerator to connect supported databases, you need to upload corresponding driver in cdap.

Driver jar can be downloaded from the internet. Please refer the following table for tested driver versions:

```
+===========================+
| DB name  | Driver Version |
+===========================+
| MySQL    | 8.0.18         |
| Postgres | 9.4.1211       |
| H2DB     | 1.4.200        |
| Oracle   | 12.1.0.2       |
+===========================+
```

* Copy driver jar at any location on one of the cdap master node. For example, copied `h2-1.4.200.jar` in `/tmp` folder.
* Create a json file with below content and copy that in same directory used in above step.<br/>Name of the json file should be same as jar file with extension `.json`. For ex `h2-1.4.200.json`
```
{
 "plugins": [
    {
      "name": "<Driver Name>",
      "type": "jdbc",
      "description": <Driver description>",
      "className": "<Driver Class>"
    }
  ]
}
```

**Example:** for the h2db content of json file

```
{
 "plugins": [
    {
      "name": "h2db",
      "type": "jdbc",
      "description": "H2DB JDBC external plugin",
      "className": "org.h2.Driver"
    }
  ]
}
```
* Login to one of cdap master node
* Go to directory `/opt/cdap/master`
* Run Command `./bin/cdap cli -v false`
* Enter username and password on prompt
* Run command to load driver
`load artifact <driver-jar-path> config-file <json-path> name <connector-name> version <driver-version>`
<br/> **For ex:** 
`load artifact /tmp/h2-1.4.200.jar config-file /tmp/h2-1.4.200.json name h2db-connector-java version 1.4.200`

* Below rest API can be used to verify success of driver upload<br/>
`namespaces/default/artifacts/h2db-connector-java/versions/1.4.200`
<br/> **Expected output**

```
{
  "classes": {
    "apps": [],
    "plugins": [
      {
        "type": "jdbc",
        "name": "h2db",
        "description": "H2DB JDBC external plugin",
        "className": "org.h2.Driver",
        "properties": {},
        "endpoints": [],
        "requirements": {
          "datasetTypes": []
        }
      }
    ]
  },
  "properties": {},
  "parents": [],
  "name": "h2db-connector-java",
  "version": "1.4.200",
  "scope": "USER"
}
```

