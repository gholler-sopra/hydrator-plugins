# Validator Transform


Description
-----------
Validates a record, writing to an error dataset if the record is invalid.
Otherwise it passes the record on to the next stage.

This table lists the methods available in CoreValidator that can be called from the ValidatorTransform:

    +==================================================================================================================+
    | function                                        | description                                                    |
    +==================================================================================================================+
    | isDate(String date)                             | Returns true if the passed param is a valid date               |
    | isCreditCard(String card)                       | Returns true if the passed param is a valid CreditCard         |
    | isBlankOrNull(String val)                       | Checks if the field is null and length of the field is greater |
    |                                                 | than zero not including whitespace                             |
    | isEmail(String email)                           | Checks if a field has a valid e-mail address                   |
    | isInRange(double value, double min, double max) | Checks if a value is within a range                            |
    | isInRange(int value, int min, int max)          | Checks if a value is within a range                            |
    | isInRange(float value, float min, float max)    | Checks if a value is within a range                            |
    | isInRange(short value, short min, short max)    | Checks if a value is within a range                            |
    | isInRange(long value, long min, long max)       | Checks if a value is within a range                            |
    | isInt(String input)                             | Checks if the value can be converted to a int primitive        |
    | isLong(String input)                            | Checks if the value can be converted to a long primitive       |
    | isShort(String input)                           | Checks if the value can be converted to a short primitive      |
    | isUrl(String input)                             | Checks if the value can be converted to a int primitive        |
    | matchRegex(String pattern, String input)        | Checks if the value matches the regular expression             |
    | maxLength(String input, int maxLength)          | Checks if the value length is less than or equal to the max    |
    | maxValue(double val, double maxVal)             | Checks if the value is less than or equal to the max           |
    | maxValue(long val, long maxVal)                 | Checks if the value is less than or equal to the max           |
    | maxValue(int val, int maxVal)                   | Checks if the value is less than or equal to the max           |
    | maxValue(float val, float maxVal)               | Checks if the value is less than or equal to the max           |
    | minValue(double val, double minVal)             | Checks if the value is greater than or equal to the min        |
    | minValue(long val, long minVal)                 | Checks if the value is greater than or equal to the min        |
    | minValue(int val, int minVal)                   | Checks if the value is greater than or equal to the min        |
    | minValue(float val, float minVal)               | Checks if the value is greater than or equal to the min        |
    | minLength(String input, int length)             | Checks if the value length is greater than or equal to the min |
    | isValidISBN(String isbn)                        | Checks if the code is either a valid ISBN-10 or ISBN-13 code   |
    | isValidInet4Address(String ipv4)                | Validates an IPv4 address                                      |
    | isValidInet6Address(String ipv6)                | Validates an IPv6 address                                      |
    | isValidIp(String ip)                            | Checks if the specified string is a valid IP address           |
    | isValidCountryCodeTid(String ccTld)             | Returns true if the input matches any IANA-defined             |
    |                                                 | country code top-level domain                                  |
    | isValidGenericTId(String gTld)                  | Returns true if the input matches any IANA-defined             |
    |                                                 | generic top-level domain                                       |
    | isValidInfrastructureTId(String iTld)           | Returns true if the input matches any IANA-defined             |
    |                                                 | infrastructure top-level domain                                |
    | isValidLocalTId(String lTld)                    | Returns true if the input matches any widely used              |
    |                                                 | local domains (localhost or localdomain)                       |
    | isValidTId(String tld)                          | Returns true if the input matches any IANA-defined             |
    |                                                 | top-level domain                                               |
    +==================================================================================================================+


Functions Description
---------------------

isDate
------

**Date Validation** and Conversion routines (`java.util.Date`).

This validator provides a number of methods for validating/converting a `String` date value to a `java.util.Date` using `java.text.DateFormat` to parse either:

*   using the default format for the default `Locale`
*   using a specified pattern with the default `Locale`
*   using the default format for a specified `Locale`
*   using a specified pattern with a specified `Locale`

For each of the above mechanisms, conversion method (i.e the `validate` methods) implementations are provided which either use the default `TimeZone` or allow the `TimeZone` to be specified.

Use one of the `isValid()` methods to just validate or one of the `validate()` methods to validate and receive a _converted_ `Date` value.

Implementations of the `validate()` method are provided to create `Date` objects for different _time zones_ if the system default is not appropriate.

Once a value has been successfully converted the following methods can be used to perform various date comparison checks:

*   `compareDates()` compares the day, month and year of two dates, returning 0, -1 or +1 indicating whether the first date is equal, before or after the second.
*   `compareWeeks()` compares the week and year of two dates, returning 0, -1 or +1 indicating whether the first week is equal, before or after the second.
*   `compareMonths()` compares the month and year of two dates, returning 0, -1 or +1 indicating whether the first month is equal, before or after the second.
*   `compareQuarters()` compares the quarter and year of two dates, returning 0, -1 or +1 indicating whether the first quarter is equal, before or after the second.
*   `compareYears()` compares the year of two dates, returning 0, -1 or +1 indicating whether the first year is equal, before or after the second.

So that the same mechanism used for parsing an _input_ value for validation can be used to format _output_, corresponding `format()` methods are also provided. That is you can format either:

*   using a specified pattern
*   using the format for a specified `Locale`
*   using the format for the _default_ `Locale`


isBlankOrNull
-------------

Checks if the field isn't null and length of the field is greater than zero not including whitespace.

@param value The value validation is being performed on. @return true if blank or null.


isEmail
-------

Checks if a field has a valid e-mail address.

@param email The value validation is being performed on. A `null` value is considered invalid. @return true if the email address is valid.

isInRange
---------

Check if the value is within a specified range. @param value The `Number` value to check. @param min The minimum value of the range. @param max The maximum value of the range. @return `true` if the value is within the specified range.


isInt
-----

Checks if the value can safely be converted to a int primitive.

@param value The value validation is being performed on. @return true if the value can be converted to an Integer.


isLong
------

Checks if the value can safely be converted to a long primitive.

@param value The value validation is being performed on. @return true if the value can be converted to a Long.


isShort
-------

Checks if the value can safely be converted to a short primitive.

@param value The value validation is being performed on. @return true if the value can be converted to a Short.


isUrl
-----

Checks if a field has a valid url address.

Note that the method calls #isValidAuthority() which checks that the domain is valid. @param value The value validation is being performed on. A `null` value is considered invalid. @return true if the url is valid.


matchRegex
----------

Validate a value against the set of regular expressions. @param value The value to validate. @return `true` if the value is valid otherwise `false`.

**Note : Regex should be pass in single quotes in the format like '[a-z]' for the matchRegex function.**


maxLength
---------

Checks if the value's length is less than or equal to the max.

@param value The value validation is being performed on. @param max The maximum length. @return true if the value's length is less than the specified maximum.



maxValue
--------

Checks if the value is less than or equal to the max.

@param value The value validation is being performed on. @param max The maximum numeric value. @return true if the value is <= the specified maximum.


minValue
--------

Checks if the value is greater than or equal to the min.

@param value The value validation is being performed on. @param min The minimum numeric value. @return true if the value is >= the specified minimum.


minLength
---------

Checks if the value's length is greater than or equal to the min.

@param value The value validation is being performed on. @param min The minimum length. @return true if the value's length is more than the specified minimum.


isValidISBN
-----------

If the ISBN is formatted with space or dash separators its format is validated. Then the digits in the number are weighted, summed, and divided by 11 according to the ISBN algorithm. If the result is zero, the ISBN is valid. This method accepts formatted or raw ISBN codes. @param isbn Candidate ISBN number to be validated. `null` is considered invalid. @return true if the string is a valid ISBN code.


isValidInet4Address
-------------------

Validates an IPv4 address. Returns true if valid. @param inet4Address the IPv4 address to validate @return true if the argument contains a valid IPv4 address


isValidInet6Address
-------------------

Validates an IPv6 address. Returns true if valid. @param inet6Address the IPv6 address to validate @return true if the argument contains a valid IPv6 address


isValidIp
---------

Checks if the specified string is a valid IP address. @param inetAddress the string to validate @return true if the string validates as an IP address


isValidCountryCodeTid
---------------------

Returns true if the specified `String` matches any IANA-defined country code top-level domain. Leading dots are ignored if present. The search is case-insensitive. @param ccTld the parameter to check for country code TLD status, not null @return true if the parameter is a country code TLD


isValidGenericTId
-----------------

Returns true if the specified `String` matches any IANA-defined generic top-level domain. Leading dots are ignored if present. The search is case-insensitive. @param gTld the parameter to check for generic TLD status, not null @return true if the parameter is a generic TLD


isValidInfrastructureTId
------------------------

Returns true if the specified `String` matches any IANA-defined infrastructure top-level domain. Leading dots are ignored if present. The search is case-insensitive. @param iTld the parameter to check for infrastructure TLD status, not null @return true if the parameter is an infrastructure TLD


isValidLocalTId
---------------

Returns true if the specified `String` matches any widely used "local" domains (localhost or localdomain). Leading dots are ignored if present. The search is case-insensitive. @param lTld the parameter to check for local TLD status, not null @return true if the parameter is an local TLD


isValidTId
----------

Returns true if the specified `String` matches any IANA-defined top-level domain. Leading dots are ignored if present. The search is case-insensitive. @param tld the parameter to check for TLD status, not null @return true if the parameter is a TLD


isCreditCard
------------

Checks if the field is a valid credit card number. @param card The card number to validate. @return Whether the card number is valid.


Use Case
--------
The transform is used when you need to validate records. For example, you may want to
validate records as being valid IP addresses or valid dates and log errors if they aren't
valid.
