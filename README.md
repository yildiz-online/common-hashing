# Yildiz-Engine common-git.

This is the official repository of The Common Hashing, part of the Yildiz-Engine project.
The common hashing is a collection of classes to hash or compute checksums on files.

## Features

* MD5 checksum computing.
* Result in hexadecimal.

## Requirements

To build this module, you will need the latest java JDK, and Maven 3.

## Coding Style and other information

Project website:
https://engine.yildiz-games.be

Issue tracker:
https://yildiz.atlassian.net

Wiki:
https://yildiz.atlassian.net/wiki

Quality report:
https://sonarqube.com/overview?id=be.yildiz-games:common-hashing

## License

All source code files are licensed under the permissive MIT license
(http://opensource.org/licenses/MIT) unless marked differently in a particular folder/file.

## Build instructions

Go to your root directory, where you POM file is located.

	mvn clean install

This will compile the source code, then run the unit tests, and finally build a jar file.

## Usage

In your maven project, add the dependency

```xml
<dependency>
    <groupId>be.yildiz-games</groupId>
    <artifactId>common-hashing</artifactId>
    <version>LATEST</version>
</dependency>
```

## Contact
Owner of this repository: Grégory Van den Borre
