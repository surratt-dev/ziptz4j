# ZipTz4J

ZipTz4J is a Java library for mapping US zip codes to timezones.  It is a port of the [infused/ziptz](https://github.com/infused/ziptz) Ruby library.

![](https://img.shields.io/maven-central/v/briansurratt/ziptz4j)
![](https://img.shields.io/github/workflow/status/briansurratt/ziptz4j/Java%20CI%20with%20Gradle)
![](https://img.shields.io/github/issues/briansurratt/ziptz4j)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=briansurratt_ziptz4j&metric=alert_status)](https://sonarcloud.io/dashboard?id=briansurratt_ziptz4j)
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=briansurratt_ziptz4j&metric=vulnerabilities)](https://sonarcloud.io/dashboard?id=briansurratt_ziptz4j)

## Installation

Gradle (Kotlin) 
```
dependencies {
    implementation("dev.surratt:ziptz4j:0.1.1")
}
```

Gradle (Groovy)
```
dependencies {
    compile 'dev.surratt:ziptz4j:0.1.1' 
}
```

## Usage

```java
import dev.surratt.ziptz4j;

final ZipCode zipCode = ZipCode.getZipCode("97034-1234");

System.out.println("Zipcode 97034-1234 is in timezone " + zipCode.getTimeZone());
```

The code above produces the following output:
```
Zipcode 97034-1234 is in timezone America/Los_Angeles
```

The zip code must be in one of the following formats:
* `08402`
* `174068026`
* `98103-8634`

A String of any other format will cause an IllegalArgumentException to be thrown.

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License
[MIT](https://choosealicense.com/licenses/mit/)