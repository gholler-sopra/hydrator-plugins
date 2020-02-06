# Union Splitter


Description
-----------
Union Splitter is an open source accelerator that is used to split data by a union schema, so that type specific logic can be done downstream.

The union splitter will emit records to different ports depending on the schema of a particular field or of
the entire record. If no field is specified, each record will be emitted to a port named after the name of the
record schema. If a field is specified, the schema for that field must be a union of supported schemas. All schemas
except maps, arrays, unions, and enums are supported. For each input record, the value of that field will be examined
and emitted to a port corresponding to its schema in the union.

For record schemas, the output port will be the name of the record schema. For simple types, the output port will
be the schema type in lowercase ('null', 'bool', 'bytes', 'int', 'long', 'float', 'double', or 'string').


Properties
----------

The following pointers describe the fields as displayed in the accelerator properties dialog box.

**unionField:** Select the union field to split on. The schema for the field must be a union of supported schemas.
All schemas except maps, arrays, unions, and enums are supported. Note that nulls are supported,
which means all nulls will get sent to the 'null' port.

**modifySchema:** Select one of the options between 'True' and 'False' depending on whether you want to modify the output schema to remove the union. For example, suppose the field 'x' is a union of int and long. If modifySchema is true, the schema for field 'x' will be just an int for the 'int' port and just a long for the 'long' port. If modifySchema is false, the output schema for each port
will be the same as the input schema. The default selection is 'true'.


Example
-------
Suppose the union splitter is configured to split on the 'item' field:

    {
        "name": "UnionSplitter",
        "type": "splittertransform",
        "properties": {
            "field": "item",
            "modifySchema": "true"
        }
    }


Now, suppose the splitter receives records with the following schema:

    +=================================+
    | name  | type                    |
    +=================================+
    | id    | long                    |
    | user  | string                  |
    | item  | [ int, long, itemMeta ] |
    +=================================+

Along with the schema above, the 'item' field as a union of int, long and a record named 'itemMeta' is also received:

    +=================================+
    | name  | type                    |
    +=================================+
    | id    | long                    |
    | desc  | string                  |
    +=================================+

This means the union splitter will have three output ports, one for each schema in the union.

If a record contains an integer for the 'item' field, it will be emitted to the 'int' port with output schema:

    +===============================+
    | name  | type                  |
    +===============================+
    | id    | long                  |
    | user  | string                |
    | item  | int                   |
    +===============================+

If a record contains a long for the 'item' field, it will be emitted to the 'long' port with output schema:

    +===============================+
    | name  | type                  |
    +===============================+
    | id    | long                  |
    | user  | string                |
    | item  | long                  |
    +===============================+

If a record contains a StructuredRecord with the itemMeta schema for the 'item' field,
it will be emitted to the 'itemMeta' port with output schema:

    +===============================+
    | name  | type                  |
    +===============================+
    | id    | long                  |
    | user  | string                |
    | item  | itemMeta              |
    +===============================+
