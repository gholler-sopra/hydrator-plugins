# Null Field Splitter


Description
-----------
Null Field Splitter is a Guavus Enterprise Accelerator that is used when you want to split records based on whether a specific field in a record is null or not.

The records containing fields with a null value are sent to the ``null`` port while records with non-null value fields are sent to the ``nonnull`` port.

Use Case
-----------

Consider a scenario wherein you have a large number of records and you want to separate records with null values from the ones with non-null values. This can be done by configuring the accelerator as shown in the following section.

Properties
----------
**field:** This is used to specify which field should be checked for null values (Macro-enabled)

**modifySchema:** This is to specify whether you want to modify the schema for the non-null output.
If set to true, the schema for non-null output will be modified so that the field is no longer nullable.
Defaults to true.

Example
-------

```json
{
    "name": "NullFieldSplitter",
    "type": "splittertransform"
    "properties": {
        "field": "email",
        "modifySchema": "true"
    }
}
```

This example takes the split input based on whether the ``email`` field is null.
For example, if the input to the plugin is:

| id (long) | name (string) | email (nullable string)  |
| --------- | ------------- | ------------------------ |
| 0         | alice         |                          |
| 1         | bob           |                          |
| 2         | carl          | karl@example.com         |
| 3         | duncan        | duncandonuts@example.com |
| 4         | evelyn        |                          |
| 5         | frank         | frankfurter@example.com  |
| 6         | gary          | gerry@example.com        |

then records emitted to the ``null`` port will be:

| id (long) | name (string) | email (nullable string)  |
| --------- | ------------- | ------------------------ |
| 0         | alice         |                          |
| 1         | bob           |                          |
| 4         | evelyn        |                          |

and records emitted to the ``nonnull`` port will be:

| id (long) | name (string) | email (string)           |
| --------- | ------------- | ------------------------ |
| 2         | carl          | karl@example.com         |
| 3         | duncan        | duncandonuts@example.com |
| 5         | frank         | frankfurter@example.com  |
| 6         | gary          | gerry@example.com        |
