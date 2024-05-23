# MujahedAlshayib

Transposition task

Overview
The Note Transposer Application is a Spring Boot-based service that transposes a collection of musical notes up or down
by a specified number of semitones. The application reads an input JSON file containing the notes, transposes them, and
writes the transposed notes to an output JSON file. The application uses the j4log library for logging.

**Table of Contents**

* Requirements
* Setup
* Configuration
* Usage
* Logging
* Testing

**Requirements**

* Java 17 or later
* Gradle

**Setup**

Clone the Repository

* git clone [https://github.com/codeordiehard/note-transposer.git]
* cd note-transposer

**Build the Project**
`./gradlew build`

**Run the Application**
`./gradlew bootRun`

**Configuration**

* Adding Dependencies

##### Make sure the following dependencies are included in your build.gradle file:

`dependencies {
      implementation 'org.springframework.boot:spring-boot-starter'
      implementation 'org.springframework.boot:spring-boot-starter-web'
      implementation 'com.google.code.gson:gson:2.8.8'
      implementation 'org.projectlombok:lombok:1.18.24'
      implementation 'org.apache.logging.log4j:log4j-api:2.16.0'
      implementation 'org.apache.logging.log4j:log4j-core:2.16.0'
      annotationProcessor 'org.projectlombok:lombok:1.18.24'
      testImplementation 'org.springframework.boot:spring-boot-starter-test'
      testRuntimeOnly 'org.junit.platform:junit-platform-launcher'
}`

 

**Usage**
**Endpoints**

* Transpose Notes from File

  Endpoint: /transpose/file

  Method: GET

  **Query Parameters:**

    * inputFile: The path to the input JSON file containing the notes.
    * semitone: The number of semitones to transpose the notes.
    * outputFile: The path to the output JSON file where the transposed notes will be saved.

**Example Request:**

[curl -X GET "http://localhost:8080/transpose/file?inputFile=/Users/mojo/Desktop/inputFile.json&semitone=-3&outputFile=/Users/mojo/Desktop/outputFile.json"
]

**Input File Format**

The input file should be a JSON array of note arrays. Each note is represented as an array of two
integers: [octaveNumber, noteNumber].

**Example:**
`[
[2, 1],
[2, 6],
[2, 1],
[2, 8]
]`

**Output File Format**

The output file will be a JSON array of the transposed notes, in the same format as the input file.

**Example:**
`[
[1, 10],
[2, 3],
[1, 10],
[2, 5]
]`

**Logging**

The application uses log4j for logging. Log messages are written to the file specified in the j4log.properties
configuration file.

**Log Configuration**
 

**log4j2.xml file:**

`<?xml version="1.0" encoding="UTF-8"?>`
`<Configuration status="WARN">`
` <Appenders>`
`<Console name="Console" target="SYSTEM_OUT">`
`<PatternLayout pattern="%d{yyyy-MM-dd HH:mm:ss} %-5p %c{1}:%L - %m%n"/>`
`</Console>`
`</Appenders>`
`<Loggers>`
`<Root level="info">`
`<AppenderRef ref="Console"/>`
`</Root>`
`</Loggers>`
`</Configuration>`

**Testing**

**Sample Input**

Create an input JSON file (inputFile.json) with the following content:

**json**

`[
[2, 1],
[2, 6],
[2, 1],
[2, 8]
]
`

**Run the Transposition**

Run the following command to transpose the notes by -3 semitones and save the result to outputFile.json:

`curl -X GET "http://localhost:8080/transpose/file?inputFile=/Users/mojo/Desktop/inputFile.json&semitone=-3&outputFile=/Users/mojo/Desktop/outputFile.json"
`

**Check the Output**

* The transposed notes will be saved in outputFile.json.